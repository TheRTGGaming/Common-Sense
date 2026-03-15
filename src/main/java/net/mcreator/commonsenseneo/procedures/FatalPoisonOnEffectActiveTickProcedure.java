package net.mcreator.commonsenseneo.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;

import net.mcreator.commonsenseneo.init.CommonSenseModMobEffects;
import net.mcreator.commonsenseneo.CommonSenseMod;

public class FatalPoisonOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(CommonSenseModMobEffects.FATAL_POISON)
				&& (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(CommonSenseModMobEffects.FATAL_POISON) ? _livEnt.getEffect(CommonSenseModMobEffects.FATAL_POISON).getAmplifier() : 0) == 0) {
			for (int index0 = 0; index0 < 36; index0++) {
				CommonSenseMod.queueServerWork(40, () -> {
					entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MAGIC)), (float) 0.5);
				});
			}
		} else if (entity instanceof LivingEntity _livEnt5 && _livEnt5.hasEffect(CommonSenseModMobEffects.FATAL_POISON)
				&& (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(CommonSenseModMobEffects.FATAL_POISON) ? _livEnt.getEffect(CommonSenseModMobEffects.FATAL_POISON).getAmplifier() : 0) == 1) {
			for (int index1 = 0; index1 < 36; index1++) {
				CommonSenseMod.queueServerWork(25, () -> {
					entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MAGIC)), (float) 0.5);
				});
			}
		} else if (entity instanceof LivingEntity _livEnt10 && _livEnt10.hasEffect(CommonSenseModMobEffects.FATAL_POISON)
				&& (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(CommonSenseModMobEffects.FATAL_POISON) ? _livEnt.getEffect(CommonSenseModMobEffects.FATAL_POISON).getAmplifier() : 0) == 2) {
			for (int index2 = 0; index2 < 36; index2++) {
				CommonSenseMod.queueServerWork(20, () -> {
					entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MAGIC)), (float) 0.5);
				});
			}
		} else if (entity instanceof LivingEntity _livEnt15 && _livEnt15.hasEffect(CommonSenseModMobEffects.FATAL_POISON)
				&& (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(CommonSenseModMobEffects.FATAL_POISON) ? _livEnt.getEffect(CommonSenseModMobEffects.FATAL_POISON).getAmplifier() : 0) == 3) {
			for (int index3 = 0; index3 < 36; index3++) {
				CommonSenseMod.queueServerWork(15, () -> {
					entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MAGIC)), (float) 0.5);
				});
			}
		} else if (entity instanceof LivingEntity _livEnt20 && _livEnt20.hasEffect(CommonSenseModMobEffects.FATAL_POISON)
				&& (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(CommonSenseModMobEffects.FATAL_POISON) ? _livEnt.getEffect(CommonSenseModMobEffects.FATAL_POISON).getAmplifier() : 0) == 4) {
			for (int index4 = 0; index4 < 36; index4++) {
				CommonSenseMod.queueServerWork(5, () -> {
					entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MAGIC)), (float) 0.5);
				});
			}
		}
	}
}