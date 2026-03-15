/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.commonsenseneo.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.mcreator.commonsenseneo.client.renderer.ThrownDirtBallRenderer;
import net.mcreator.commonsenseneo.client.renderer.CompressedTNTPrimedRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class CommonSenseModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(CommonSenseModEntities.SLING_SHOT_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(CommonSenseModEntities.CHARGED.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(CommonSenseModEntities.ENDER_PEARLTHROW.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(CommonSenseModEntities.SPLASH_POTION_THROW.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(CommonSenseModEntities.THROWN_DIRT_BALL.get(), ThrownDirtBallRenderer::new);
		event.registerEntityRenderer(CommonSenseModEntities.COMPRESSED_TNT_PRIMED.get(), CompressedTNTPrimedRenderer::new);
	}
}