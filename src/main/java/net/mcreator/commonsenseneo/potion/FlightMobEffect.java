package net.mcreator.commonsenseneo.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.commonsenseneo.procedures.FlightEffectStartedappliedProcedure;

public class FlightMobEffect extends MobEffect {
	public FlightMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -8678223);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		FlightEffectStartedappliedProcedure.execute(entity);
	}
}