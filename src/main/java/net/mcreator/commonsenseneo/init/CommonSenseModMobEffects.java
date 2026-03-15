/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.commonsenseneo.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.commonsenseneo.potion.FlightMobEffect;
import net.mcreator.commonsenseneo.potion.FatalPoisonMobEffect;
import net.mcreator.commonsenseneo.CommonSenseMod;

public class CommonSenseModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, CommonSenseMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> FLIGHT = REGISTRY.register("flight", () -> new FlightMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> FATAL_POISON = REGISTRY.register("fatal_poison", () -> new FatalPoisonMobEffect());
}