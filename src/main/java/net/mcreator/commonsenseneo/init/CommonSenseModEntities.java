/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.commonsenseneo.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

import net.mcreator.commonsenseneo.entity.*;
import net.mcreator.commonsenseneo.CommonSenseMod;

@EventBusSubscriber
public class CommonSenseModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, CommonSenseMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<SlingShotProjectileEntity>> SLING_SHOT_PROJECTILE = register("sling_shot_projectile",
			EntityType.Builder.<SlingShotProjectileEntity>of(SlingShotProjectileEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<ChargedEntity>> CHARGED = register("charged",
			EntityType.Builder.<ChargedEntity>of(ChargedEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<EnderPearlthrowEntity>> ENDER_PEARLTHROW = register("ender_pearlthrow",
			EntityType.Builder.<EnderPearlthrowEntity>of(EnderPearlthrowEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<SplashPotionThrowEntity>> SPLASH_POTION_THROW = register("splash_potion_throw",
			EntityType.Builder.<SplashPotionThrowEntity>of(SplashPotionThrowEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<ThrownDirtBallEntity>> THROWN_DIRT_BALL = register("thrown_dirt_ball",
			EntityType.Builder.<ThrownDirtBallEntity>of(ThrownDirtBallEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<CompressedTNTPrimedEntity>> COMPRESSED_TNT_PRIMED = register("compressed_tnt_primed",
			EntityType.Builder.<CompressedTNTPrimedEntity>of(CompressedTNTPrimedEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(1).setUpdateInterval(3).fireImmune()

					.sized(1f, 1f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		CompressedTNTPrimedEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(COMPRESSED_TNT_PRIMED.get(), CompressedTNTPrimedEntity.createAttributes().build());
	}
}