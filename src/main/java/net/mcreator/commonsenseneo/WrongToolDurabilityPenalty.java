package net.mcreator.commonsenseneo;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WrongToolDurabilityPenalty {

    // Gamerule key (assigned in CommonSenseMod.java)
    public static GameRules.Key<GameRules.BooleanValue> DURABILITY_PENALTY_RULE;

    private static final Map<UUID, Boolean> wrongToolFlag = new HashMap<>();
    private static final Map<UUID, Float> storedHardness = new HashMap<>();
    private static final Map<UUID, Item> lastToolUsed = new HashMap<>();

    // ---------------------------------------------------------
    // 1. SERVER TICK — runs every tick for every player
    // ---------------------------------------------------------
    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {

        // Skip entire system if gamerule is OFF
        if (!event.getServer().getGameRules().getBoolean(DURABILITY_PENALTY_RULE)) {
            return;
        }

        // Loop through all players
        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {

            Level level = player.level();
            if (level.isClientSide()) continue;
            if (player.isCreative()) continue;

            UUID id = player.getUUID();

            // Raytrace from eyes out to 6 blocks
            Vec3 eyePos = player.getEyePosition();
            Vec3 lookVec = player.getLookAngle();
            Vec3 reachVec = eyePos.add(lookVec.scale(6.0));

            BlockHitResult hit = level.clip(new ClipContext(
                    eyePos,
                    reachVec,
                    ClipContext.Block.OUTLINE,
                    ClipContext.Fluid.NONE,
                    player
            ));

            if (hit.getType() != HitResult.Type.BLOCK) {
                continue;
            }

            BlockPos pos = hit.getBlockPos();
            BlockState state = level.getBlockState(pos);

            ItemStack stack = player.getMainHandItem();
            if (stack.isEmpty()) {
                wrongToolFlag.put(id, false);
                storedHardness.remove(id);
                lastToolUsed.remove(id);
                continue;
            }

            Item tool = stack.getItem();

            boolean isTool =
                    tool instanceof SwordItem ||
                    tool instanceof HoeItem ||
                    tool instanceof PickaxeItem ||
                    tool instanceof ShovelItem ||
                    tool instanceof AxeItem ||
                    tool instanceof ShearsItem;

            if (!isTool) {
                wrongToolFlag.put(id, false);
                storedHardness.remove(id);
                lastToolUsed.put(id, tool);
                continue;
            }

            // Detect tool-type change
            Item lastTool = lastToolUsed.get(id);
            if (lastTool != null && lastTool != tool) {
                wrongToolFlag.put(id, false);
                storedHardness.remove(id);
            }
            lastToolUsed.put(id, tool);

            boolean correctTool = isCorrectToolType(tool, state);

            if (!correctTool) {
                wrongToolFlag.put(id, true);
                storedHardness.put(id, state.getDestroySpeed(level, pos));
            } else {
                wrongToolFlag.put(id, false);
                storedHardness.remove(id);
            }
        }
    }

    // ---------------------------------------------------------
    // 2. BLOCK BREAK — apply penalty
    // ---------------------------------------------------------
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        if (!(event.getPlayer() instanceof ServerPlayer player)) return;

        // Skip if gamerule is OFF
        if (!player.getServer().getGameRules().getBoolean(DURABILITY_PENALTY_RULE)) {
            return;
        }

        Level level = player.level();
        if (level.isClientSide()) return;
        if (player.isCreative()) return;

        UUID id = player.getUUID();

        if (!wrongToolFlag.getOrDefault(id, false)) return;

        wrongToolFlag.put(id, false);

        float hardness = storedHardness.getOrDefault(id, 0f);
        storedHardness.remove(id);

        if (hardness < 0) return;

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) return;

        int maxDur = stack.getMaxDamage();
        EquipmentSlot slot = EquipmentSlot.MAINHAND;

        // Very hard blocks → break tool instantly
        if (hardness >= 10.0f) {
            stack.hurtAndBreak(maxDur, player, slot);
            return;
        }

        float percent = hardness * 0.10f;
        int extraDamage = (int) Math.ceil(maxDur * percent);

        if (extraDamage > 0) {
            stack.hurtAndBreak(extraDamage, player, slot);
        }
    }

    // ---------------------------------------------------------
    // TOOL TYPE CHECKING
    // ---------------------------------------------------------
    private static boolean isCorrectToolType(Item tool, BlockState state) {
        String id = state.getBlock().getDescriptionId();

        if (tool instanceof PickaxeItem) {
            return state.requiresCorrectToolForDrops();
        }

        if (tool instanceof AxeItem) {
            return id.contains("log") || id.contains("wood") || id.contains("stem") ||
                   id.contains("hyphae") || id.contains("planks") ||
                   id.contains("pumpkin") || id.contains("melon");
        }

        if (tool instanceof ShovelItem) {
            return id.contains("dirt") || id.contains("grass") || id.contains("sand") ||
                   id.contains("gravel") || id.contains("clay") || id.contains("mud") ||
                   id.contains("snow");
        }

        if (tool instanceof HoeItem) {
            return id.contains("leaves") || id.contains("hay") ||
                   id.contains("moss") || id.contains("crop");
        }

        if (tool instanceof ShearsItem) {
            return id.contains("leaves") || id.contains("wool") ||
                   id.contains("web") || id.contains("vine");
        }

        if (tool instanceof SwordItem) {
            return id.contains("web");
        }

        return false;
    }
}
