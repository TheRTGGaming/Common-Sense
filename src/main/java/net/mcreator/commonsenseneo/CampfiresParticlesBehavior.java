package net.mcreator.commonsenseneo;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class CampfiresParticlesBehavior {

    private static final String RESIST_KEY = "RainResistTicks";
    private static final int RESIST_SOFT_CAP = 24000;

    private static int tickCounter = 0;

    // ---------------------------------------------------------
    // WORLD TICK — runs every 10 ticks (0.5 seconds)
    // ---------------------------------------------------------
    @SubscribeEvent
    public static void onWorldTick(ServerTickEvent.Post event) {
        if (!(event.getServer().overworld() instanceof ServerLevel server)) return;

        tickCounter++;
        if (tickCounter < 10) return; // run every 10 ticks
        tickCounter = 0;

        for (Player player : server.players()) {
            BlockPos center = player.blockPosition();
            int radius = 16;

            BlockPos.betweenClosedStream(
                    center.offset(-radius, -radius, -radius),
                    center.offset(radius, radius, radius)
            ).forEach(pos -> {
                BlockState state = server.getBlockState(pos);

                boolean isCampfire = state.is(Blocks.CAMPFIRE) || state.is(Blocks.SOUL_CAMPFIRE);
                if (!isCampfire) return;

                boolean lit = state.getValue(BlockStateProperties.LIT);
                boolean raining = server.isRainingAt(pos.above());
                boolean sky = server.canSeeSky(pos.above());

                BlockEntity be = server.getBlockEntity(pos);
                if (be == null) return;

                int resist = be.getPersistentData().getInt(RESIST_KEY);
                int seconds = resist / 20;
                boolean lowTime = seconds <= 30 && seconds > 0;

                // ---------------------------------------------------------
                // 1. DRIPPING WATER (unlit wet OR lit with low time)
                // ---------------------------------------------------------
                boolean unlitWet = !lit && raining && sky && resist <= 0;
                boolean litLow = lit && raining && sky && lowTime;

                if (unlitWet || litLow) {
                    server.sendParticles(
                            ParticleTypes.DRIPPING_WATER,
                            pos.getX() + 0.5,
                            pos.getY() + 0.2,
                            pos.getZ() + 0.5,
                            3,
                            0.3,
                            0.1,
                            0.3,
                            0.01
                    );
                }

                // ---------------------------------------------------------
                // 2. BIGGER FLAME (scales with resistance)
                // ---------------------------------------------------------
                if (lit && resist > 0) {
                    spawnBiggerFlame(server, pos, resist);
                }
            });
        }
    }

    // ---------------------------------------------------------
    // 3. CLOUD PUFF WHEN FIRE GOES OUT
    // ---------------------------------------------------------
    public static void spawnExtinguishCloud(Level level, BlockPos pos) {
        if (level instanceof ServerLevel server) {
            server.sendParticles(
                    ParticleTypes.CLOUD,
                    pos.getX() + 0.5,
                    pos.getY() + 0.8,
                    pos.getZ() + 0.5,
                    8,
                    0.25,
                    0.1,
                    0.25,
                    0.02
            );
        }
    }

    // ---------------------------------------------------------
    // 4. FLAME / SOUL FLAME WHEN ADDING FUEL
    // ---------------------------------------------------------
    public static void spawnFuelParticles(Level level, BlockPos pos, boolean soul) {
        if (level instanceof ServerLevel server) {
            server.sendParticles(
                    soul ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME,
                    pos.getX() + 0.5,
                    pos.getY() + 0.7,
                    pos.getZ() + 0.5,
                    10,
                    0.3,
                    0.15,
                    0.3,
                    0.02
            );
        }
    }

    // ---------------------------------------------------------
    // 5. CRIT PARTICLES WHEN FULL
    // ---------------------------------------------------------
    public static void spawnFullParticles(Level level, BlockPos pos) {
        if (level instanceof ServerLevel server) {
            server.sendParticles(
                    ParticleTypes.CRIT,
                    pos.getX() + 0.5,
                    pos.getY() + 0.7,
                    pos.getZ() + 0.5,
                    12,
                    0.4,
                    0.2,
                    0.4,
                    0.02
            );
        }
    }

    // ---------------------------------------------------------
    // 6. BIGGER FLAME EFFECT (SCALES WITH RESISTANCE)
    // ---------------------------------------------------------
public static void spawnBiggerFlame(ServerLevel server, BlockPos pos, int resist) {
    int seconds = resist / 20;
    
    int count;
    double spreadXZ;
    double spreadY;
    double ySpeed;

    if (seconds > 120) {      // Giga Flame
        count = 16;           // High density
        spreadXZ = 0.45;      // Wider base
        spreadY = 0.6;        // Tall flames
        ySpeed = 0.08;        // Shooting upwards
    } else if (seconds > 60) { // Medium Flame
        count = 10;
        spreadXZ = 0.35;
        spreadY = 0.4;
        ySpeed = 0.05;
    } else if (seconds > 30) { // Small Flame
        count = 5;
        spreadXZ = 0.25;
        spreadY = 0.2;
        ySpeed = 0.02;
    } else return;

    server.sendParticles(
            ParticleTypes.FLAME,
            pos.getX() + 0.5,
            pos.getY() + 0.8, 
            pos.getZ() + 0.5,
            count,
            spreadXZ, spreadY, spreadXZ, // The "size" of the flame aura
            ySpeed                       // The "roar" speed
        );
    }
}
