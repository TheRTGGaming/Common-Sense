package net.mcreator.commonsenseneo.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.commonsenseneo.entity.CompressedTNTPrimedEntity;
import net.mcreator.commonsenseneo.client.model.animations.compressedtntAnimation;
import net.mcreator.commonsenseneo.client.model.Modelcompressedtnt;

public class CompressedTNTPrimedRenderer extends MobRenderer<CompressedTNTPrimedEntity, Modelcompressedtnt<CompressedTNTPrimedEntity>> {
	public CompressedTNTPrimedRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelcompressedtnt.LAYER_LOCATION)), 1f);
	}

	@Override
	public ResourceLocation getTextureLocation(CompressedTNTPrimedEntity entity) {
		return ResourceLocation.parse("common_sense:textures/entities/compressed_tnt_entity_texture.png");
	}

	private static final class AnimatedModel extends Modelcompressedtnt<CompressedTNTPrimedEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<CompressedTNTPrimedEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(CompressedTNTPrimedEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, compressedtntAnimation.compressedtntprimedanimation, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(CompressedTNTPrimedEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}