package net.undertaker.grimtales.entity.client;// Made with Blockbench 4.11.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.undertaker.grimtales.entity.animations.ModAnimationDefinition;
import net.undertaker.grimtales.entity.custom.Capybara;

public class CapybaraModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart EarL;
	private final ModelPart EarR;
	private final ModelPart LegBR;
	private final ModelPart LegBL;
	private final ModelPart LegFL;
	private final ModelPart LegFR;
	private final ModelPart Fur;

	@Override
	public ModelPart root() {
		return Body;
	}
	public CapybaraModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Head = Body.getChild("Head");
		this.EarL = Head.getChild("EarL");
		this.EarR = Head.getChild("EarR");
		this.LegBR = Body.getChild("LegBR");
		this.LegBL = Body.getChild("LegBL");
		this.LegFL = Body.getChild("LegFL");
		this.LegFR = Body.getChild("LegFR");
		this.Fur = Body.getChild("Fur");
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.0F, -9.0F, 10.0F, 10.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 12.05F, 0.35F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 28).addBox(-3.0F, -3.0F, -9.5F, 6.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.9F, -8.0F));

		PartDefinition EarL = Head.addOrReplaceChild("EarL", CubeListBuilder.create(), PartPose.offset(2.7F, -2.9F, -2.5F));

		PartDefinition EarL_r1 = EarL.addOrReplaceChild("EarL_r1", CubeListBuilder.create().texOffs(0, 1).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.7F, 0.0F, 0.0369F, -0.2062F, -0.1784F));

		PartDefinition EarR = Head.addOrReplaceChild("EarR", CubeListBuilder.create(), PartPose.offset(-2.7F, -2.9F, -2.5F));

		PartDefinition EarR_r1 = EarR.addOrReplaceChild("EarR_r1", CubeListBuilder.create().texOffs(0, 3).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.7F, 0.0F, 0.0369F, 0.2062F, 0.1784F));

		PartDefinition LegBR = Body.addOrReplaceChild("LegBR", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.8F, 5.1F, 5.9F));

		PartDefinition LegBL = Body.addOrReplaceChild("LegBL", CubeListBuilder.create().texOffs(28, 40).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.8F, 5.1F, 5.9F));

		PartDefinition LegFL = Body.addOrReplaceChild("LegFL", CubeListBuilder.create().texOffs(32, 28).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.8F, 5.1F, -4.7F));

		PartDefinition LegFR = Body.addOrReplaceChild("LegFR", CubeListBuilder.create().texOffs(38, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.8F, 5.1F, -4.7F));

		PartDefinition Fur = Body.addOrReplaceChild("Fur", CubeListBuilder.create(), PartPose.offset(-0.7429F, 1.5536F, -3.0429F));

		PartDefinition cube_r1 = Fur.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(25, 40).mirror().addBox(-2.0F, -4.0F, -8.0F, 0.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.4571F, 6.7464F, 2.5429F, 0.0F, 0.0F, 0.0524F));

		PartDefinition cube_r2 = Fur.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(25, 40).mirror().addBox(-2.0F, -4.0F, -8.0F, 0.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0571F, 5.9464F, 2.1429F, 0.0F, 0.0F, 0.3316F));

		PartDefinition cube_r3 = Fur.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(25, 40).mirror().addBox(-2.0F, -4.0F, -8.0F, 0.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0571F, 5.1464F, 3.7429F, 0.0F, 0.0F, 0.2269F));

		PartDefinition cube_r4 = Fur.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(25, 40).addBox(2.0F, -4.0F, -8.0F, 0.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5429F, 5.9464F, 2.5429F, 0.0F, 0.0F, -0.3316F));

		PartDefinition cube_r5 = Fur.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(25, 40).mirror().addBox(-2.0F, -4.0F, -8.0F, 0.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.4571F, 7.5464F, 2.5429F, 0.0F, 0.0F, 0.1571F));

		PartDefinition cube_r6 = Fur.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(25, 40).addBox(2.0F, -4.0F, -8.0F, 0.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5429F, 5.1464F, 3.5429F, 0.0F, 0.0F, -0.2269F));

		PartDefinition cube_r7 = Fur.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(25, 40).addBox(2.0F, -4.0F, -8.0F, 0.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9429F, 6.7464F, 2.5429F, 0.0F, 0.0F, -0.0524F));

		PartDefinition cube_r8 = Fur.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(36, 58).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9571F, -6.6536F, -3.0571F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r9 = Fur.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(36, 58).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6429F, -6.6536F, -3.0571F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r10 = Fur.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(36, 58).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1571F, -6.6536F, -4.4571F, 0.2356F, 0.0F, 0.0F));

		PartDefinition cube_r11 = Fur.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(36, 58).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6429F, -6.6536F, -4.0571F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r12 = Fur.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(36, 58).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1571F, -6.6536F, -3.4571F, 0.4102F, 0.0F, 0.0F));

		PartDefinition cube_r13 = Fur.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(36, 58).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9571F, -6.6536F, -4.0571F, 0.0F, 0.0F, 0.0F));

		PartDefinition cube_r14 = Fur.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(25, 40).addBox(2.0F, -4.0F, -8.0F, 0.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9429F, 7.5464F, 2.5429F, 0.0F, 0.0F, -0.1571F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Body.getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinition.walk, limbSwing, limbSwingAmount, 4f, 2.5f);
		this.animate(((Capybara)entity).idleAnimationState, ModAnimationDefinition.idle, ageInTicks, 2f);
	}

	private void applyHeadRotation(float netHeadYaw, float headPitch, float ageInTicks){
		netHeadYaw = Mth.clamp(netHeadYaw, -30.0f, 30.0f);
		headPitch = Mth.clamp(headPitch, -25.0f, 25.0f);

		this.Head.yRot = netHeadYaw * ((float) Math.PI / 180f);
		this.Head.xRot = headPitch * ((float) Math.PI / 180f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}


}