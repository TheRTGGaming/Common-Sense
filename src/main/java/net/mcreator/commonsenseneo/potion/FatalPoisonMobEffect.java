package net.mcreator.commonsenseneo.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.commonsenseneo.procedures.FatalPoisonOnEffectActiveTickProcedure;

public class FatalPoisonMobEffect extends MobEffect {
	public FatalPoisonMobEffect() {
		super(MobEffectCategory.HARMFUL, -16751104);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		FatalPoisonOnEffectActiveTickProcedure.execute(entity.level(), entity);
		return super.applyEffectTick(entity, amplifier);
	}
}