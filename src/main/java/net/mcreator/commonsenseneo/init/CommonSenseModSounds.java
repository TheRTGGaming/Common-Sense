/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.commonsenseneo.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.commonsenseneo.CommonSenseMod;

public class CommonSenseModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, CommonSenseMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> SWITCHOFF = REGISTRY.register("switchoff", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("common_sense", "switchoff")));
}