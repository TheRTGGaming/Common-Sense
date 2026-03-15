package net.mcreator.commonsenseneo;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import net.minecraft.network.chat.Component;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class CampfiresGoOutWhileRaining {

    private static int tickCounter = 0;

    private static final String RESIST_KEY = "RainResistTicks";

    // Fuel values (ticks)
    // Breeze Rod / Blaze Rod = 1800 ticks (90 seconds)
    // Wind Charge = 450 ticks (22.5 seconds)
    // Blaze Powder = 900 ticks (45 seconds)
    private static final int FUEL_SMALL_NORMAL = 450; // Wind Charge
    private static final int FUEL_SMALL_SOUL = 900;   // Blaze Powder
    private static final int FUEL_LARGE = 1800;       // Rods (Breeze / Blaze)

    private static final int RESIST_SOFT_CAP = 24000;
    private static final int RESIST_DRAIN = 80;

    // ---------------------------------------------------------
    // WORLD TICK — rain extinguish + resistance drain
    // ---------------------------------------------------------
    @SubscribeEvent
    public static void onWorldTick(ServerTickEvent.Post event) {
        if (!(event.getServer().overworld() instanceof ServerLevel serverLevel)) return;

        tickCounter++;
        if (tickCounter < 80) return;
        tickCounter = 0;

        boolean raining = serverLevel.isRaining();
        boolean thundering = serverLevel.isThundering();

        if (!raining && !thundering) return;

        for (Player player : serverLevel.players()) {
            BlockPos center = player.blockPosition();
            int radius = 16;

            BlockPos.betweenClosedStream(
                    center.offset(-radius, -radius, -radius),
                    center.offset(radius, radius, radius)
            ).forEach(pos -> {
                BlockState state = serverLevel.getBlockState(pos);

                if (!(state.is(Blocks.CAMPFIRE) || state.is(Blocks.SOUL_CAMPFIRE))) return;
                if (!state.hasProperty(BlockStateProperties.LIT)) return;
                if (!state.getValue(BlockStateProperties.LIT)) return;

                if (!serverLevel.canSeeSky(pos.above())) return;
                if (!serverLevel.isRainingAt(pos.above()) && !thundering) return;

                BlockEntity be = serverLevel.getBlockEntity(pos);
                if (be == null) return;

                int resist = be.getPersistentData().getInt(RESIST_KEY);

                if (resist > 0) {
                    resist -= RESIST_DRAIN;
                    if (resist < 0) resist = 0;
                    be.getPersistentData().putInt(RESIST_KEY, resist);
                    if (resist > 0) return;
                }

                // Fire goes out
                CampfiresParticlesBehavior.spawnExtinguishCloud(serverLevel, pos);

                serverLevel.setBlock(pos, state.setValue(BlockStateProperties.LIT, false), 3);

                serverLevel.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.6f, 1.0f);

                serverLevel.sendParticles(
                        ParticleTypes.SMOKE,
                        pos.getX() + 0.5,
                        pos.getY() + 0.7,
                        pos.getZ() + 0.5,
                        6,
                        0.2,
                        0.1,
                        0.2,
                        0.01
                );
            });
        }
    }

    // ---------------------------------------------------------
    // RIGHT CLICK — add fuel
    // ---------------------------------------------------------
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        if (level.isClientSide()) return;

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();

        boolean isCampfire = state.is(Blocks.CAMPFIRE);
        boolean isSoulCampfire = state.is(Blocks.SOUL_CAMPFIRE);

        if (!isCampfire && !isSoulCampfire) return;

        // Prevent Wind Charge / Blaze Powder from doing their normal behavior when used as fuel
        if (stack.is(Items.WIND_CHARGE) || stack.is(Items.BLAZE_POWDER)) {
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }

        // Add fuel
        if (isCampfire && (stack.is(Items.BREEZE_ROD) || stack.is(Items.WIND_CHARGE))) {
            addResistance(level, pos, player, stack, true);
            return;
        }

        if (isSoulCampfire && (stack.is(Items.BLAZE_ROD) || stack.is(Items.BLAZE_POWDER))) {
            addResistance(level, pos, player, stack, false);
            return;
        }
    }

    // ---------------------------------------------------------
    // ADD RESISTANCE (fuel)
    // ---------------------------------------------------------
private static void addResistance(Level level, BlockPos pos, Player player, ItemStack stack, boolean normalCampfire) {
    if (!(level instanceof ServerLevel serverLevel)) return;

    BlockEntity be = serverLevel.getBlockEntity(pos);
    if (be == null) return;

    int current = be.getPersistentData().getInt(RESIST_KEY);

    int add = 0;

    if (normalCampfire) {
        if (stack.is(Items.BREEZE_ROD)) add = FUEL_LARGE;
        else if (stack.is(Items.WIND_CHARGE)) add = FUEL_SMALL_NORMAL;
    } else {
        if (stack.is(Items.BLAZE_ROD)) add = FUEL_LARGE;
        else if (stack.is(Items.BLAZE_POWDER)) add = FUEL_SMALL_SOUL;
    }

    if (add == 0) return;

    // If already at max, do NOT consume item
    if (current >= RESIST_SOFT_CAP) {
        player.displayClientMessage(
                Component.literal("§9Campfire is already at maximum resistance"),
                true
        );
        return;
    }

    int newValue = current + add;
    if (newValue > RESIST_SOFT_CAP) newValue = RESIST_SOFT_CAP;

    be.getPersistentData().putInt(RESIST_KEY, newValue);

    // Only shrink item AFTER confirming fuel was added
    if (!player.isCreative()) stack.shrink(1);

    // Fuel particles
    CampfiresParticlesBehavior.spawnFuelParticles(serverLevel, pos, !normalCampfire);

    // Sound
    if (normalCampfire) {
        serverLevel.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
    } else {
        serverLevel.playSound(null, pos, SoundEvents.BLAZE_SHOOT, SoundSource.BLOCKS, 1.0f, 1.0f);
    }
}


    // ---------------------------------------------------------
    // BLOCK BREAK — return fuel based on remaining time
    // ---------------------------------------------------------
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        // BreakEvent gives LevelAccessor, not Level
        var accessor = event.getLevel();
        if (!(accessor instanceof ServerLevel level)) return;

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        boolean isCampfire = state.is(Blocks.CAMPFIRE);
        boolean isSoulCampfire = state.is(Blocks.SOUL_CAMPFIRE);

        if (!isCampfire && !isSoulCampfire) return;

        BlockEntity be = level.getBlockEntity(pos);
        if (be == null) return;

        int resistTicks = be.getPersistentData().getInt(RESIST_KEY);
        if (resistTicks <= 0) return;

        // Convert ticks to seconds as float to preserve fractions
        float totalSeconds = resistTicks / 20.0f;

        // Fuel items
        Item smallFuel = isCampfire ? Items.WIND_CHARGE : Items.BLAZE_POWDER;
        Item largeFuel = isCampfire ? Items.BREEZE_ROD : Items.BLAZE_ROD;

        // Base units in seconds
        float largeUnitSeconds = 90.0f;                         // rods
        float smallUnitSeconds = isCampfire ? 22.5f : 45.0f;    // wind charge or blaze powder

        // Large fuel count
        int largeCount = (int) (totalSeconds / largeUnitSeconds);
        float leftoverAfterLarge = totalSeconds - (largeCount * largeUnitSeconds);

        // Small fuel count
        int smallCount = (int) (leftoverAfterLarge / smallUnitSeconds);
        float leftoverSmallSeconds = leftoverAfterLarge - (smallCount * smallUnitSeconds);

        // Drop guaranteed large fuel
        if (largeCount > 0) {
            net.minecraft.world.level.block.Block.popResource(
                    level,
                    pos,
                    new ItemStack(largeFuel, largeCount)
            );
        }

        // Drop guaranteed small fuel
        if (smallCount > 0) {
            net.minecraft.world.level.block.Block.popResource(
                    level,
                    pos,
                    new ItemStack(smallFuel, smallCount)
            );
        }

        // Fractional chance for one extra small fuel
        if (leftoverSmallSeconds > 0.0f) {
            float chance = leftoverSmallSeconds / smallUnitSeconds;
            if (chance > 0.0f && level.random.nextFloat() < chance) {
                net.minecraft.world.level.block.Block.popResource(
                        level,
                        pos,
                        new ItemStack(smallFuel, 1)
                );
            }
        }
    }

    // ---------------------------------------------------------
    // SNEAK TIMER CHECK
    // ---------------------------------------------------------
    @SubscribeEvent
    public static void onSneakCheck(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        if (level.isClientSide()) return;

        Player player = event.getEntity();
        if (!player.isShiftKeyDown()) return;
        if (!event.getItemStack().isEmpty()) return;

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        boolean isCampfire = state.is(Blocks.CAMPFIRE) || state.is(Blocks.SOUL_CAMPFIRE);
        if (!isCampfire) return;

        BlockEntity be = level.getBlockEntity(pos);
        if (be == null) return;

        int ticks = be.getPersistentData().getInt(RESIST_KEY);

        int seconds = ticks / 20;
        int minutes = seconds / 60;
        int sec = seconds % 60;

        String time = String.format("%02d:%02d", minutes, sec);

        boolean frozen = !level.isRaining() && !level.isThundering();
        String color = frozen ? "§9" : "§c";

        player.displayClientMessage(
                Component.literal(color + "Time remaining: " + time),
                true
        );

        event.setCanceled(true);
    }

    // ---------------------------------------------------------
    // LOOK-AT MESSAGE
    // ---------------------------------------------------------
    @SubscribeEvent
    public static void onPlayerTick(net.neoforged.neoforge.event.tick.PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (level.isClientSide()) return;

        if (player.isShiftKeyDown()) return;

        double reach = 5.0;
        var hit = player.pick(reach, 0, false);
        if (hit == null || hit.getType() != net.minecraft.world.phys.HitResult.Type.BLOCK) return;

        BlockPos pos = BlockPos.containing(hit.getLocation());
        BlockState state = level.getBlockState(pos);

        boolean isCampfire = state.is(Blocks.CAMPFIRE);
        boolean isSoulCampfire = state.is(Blocks.SOUL_CAMPFIRE);

        if (!isCampfire && !isSoulCampfire) return;
        if (!player.getMainHandItem().isEmpty()) return;

        String msg = isCampfire
                ? "§eAdd Breeze Rod or Wind Charge for weather resistance"
                : "§eAdd Blaze Rod or Blaze Powder for weather resistance";

        player.displayClientMessage(Component.literal(msg), true);
    }

    // ---------------------------------------------------------
    // CANCEL WIND CHARGE THROW WHEN LOOKING AT CAMPFIRE
    // ---------------------------------------------------------
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        if (level.isClientSide()) return;

        ItemStack stack = event.getItemStack();

        if (!stack.is(Items.WIND_CHARGE)) return;

        var hit = player.pick(5.0, 0, false);
        if (hit == null || hit.getType() != net.minecraft.world.phys.HitResult.Type.BLOCK) return;

        BlockPos pos = BlockPos.containing(hit.getLocation());
        BlockState state = level.getBlockState(pos);

        boolean isCampfire = state.is(Blocks.CAMPFIRE) || state.is(Blocks.SOUL_CAMPFIRE);
        if (!isCampfire) return;

        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);
    }
}
