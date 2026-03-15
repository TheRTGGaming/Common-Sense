package net.mcreator.commonsenseneo.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelIronSpearthrown<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("common_sense", "model_iron_spearthrown"), "main");
	public final ModelPart group;
	public final ModelPart Spearhead;
	public final ModelPart bb_main;

	public ModelIronSpearthrown(ModelPart root) {
		this.group = root.getChild("group");
		this.Spearhead = root.getChild("Spearhead");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition group = partdefinition.addOrReplaceChild("group",
				CubeListBuilder.create().texOffs(14, 87).addBox(-1.76F, -26.24F, -0.88F, 3.52F, 0.88F, 1.76F, new CubeDeformation(0.0F)).texOffs(14, 87).addBox(-2.2F, -26.68F, -0.44F, 0.44F, 1.32F, 0.88F, new CubeDeformation(0.0F)).texOffs(14, 87)
						.addBox(1.76F, -26.68F, -0.44F, 0.44F, 1.32F, 0.88F, new CubeDeformation(0.0F)).texOffs(14, 87).addBox(0.88F, -25.36F, -0.44F, 1.32F, 0.22F, 0.88F, new CubeDeformation(0.0F)).texOffs(14, 87)
						.addBox(-2.2F, -25.36F, -0.44F, 1.32F, 0.22F, 0.88F, new CubeDeformation(0.0F)).texOffs(14, 87).addBox(-0.88F, -25.36F, -0.66F, 1.76F, 0.22F, 1.32F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition Spearhead = partdefinition.addOrReplaceChild("Spearhead",
				CubeListBuilder.create().texOffs(18, 14).addBox(-1.43F, -28.44F, -0.33F, 2.86F, 0.88F, 0.66F, new CubeDeformation(0.0F)).texOffs(18, 14).addBox(-1.32F, -27.56F, -0.22F, 2.64F, 0.44F, 0.44F, new CubeDeformation(0.0F)).texOffs(18, 14)
						.addBox(-0.88F, -27.12F, -0.22F, 1.76F, 0.44F, 0.44F, new CubeDeformation(0.0F)).texOffs(18, 14).addBox(-0.66F, -26.68F, -0.22F, 1.32F, 0.44F, 0.44F, new CubeDeformation(0.0F)).texOffs(18, 14)
						.addBox(-1.32F, -29.32F, -0.22F, 2.64F, 0.88F, 0.44F, new CubeDeformation(0.0F)).texOffs(18, 14).addBox(-0.88F, -30.2F, -0.11F, 1.76F, 0.88F, 0.22F, new CubeDeformation(0.0F)).texOffs(18, 14)
						.addBox(-0.44F, -31.08F, -0.0572F, 0.88F, 0.88F, 0.11F, new CubeDeformation(0.0F)).texOffs(18, 14).addBox(-0.22F, -31.96F, -0.0308F, 0.44F, 0.88F, 0.066F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 49).addBox(-0.44F, -25.36F, -0.44F, 0.88F, 25.36F, 0.88F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 1000, 1000);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		group.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		Spearhead.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}