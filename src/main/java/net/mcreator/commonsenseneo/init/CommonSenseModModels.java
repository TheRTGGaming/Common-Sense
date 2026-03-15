/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.commonsenseneo.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.commonsenseneo.client.model.Modeldirtball;
import net.mcreator.commonsenseneo.client.model.Modelcompressedtnt;
import net.mcreator.commonsenseneo.client.model.ModelIronSpearthrown;

@EventBusSubscriber(Dist.CLIENT)
public class CommonSenseModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modeldirtball.LAYER_LOCATION, Modeldirtball::createBodyLayer);
		event.registerLayerDefinition(Modelcompressedtnt.LAYER_LOCATION, Modelcompressedtnt::createBodyLayer);
		event.registerLayerDefinition(ModelIronSpearthrown.LAYER_LOCATION, ModelIronSpearthrown::createBodyLayer);
	}
}