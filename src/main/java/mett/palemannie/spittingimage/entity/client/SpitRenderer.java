package mett.palemannie.spittingimage.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.entity.custom.SpitEntity;
import mett.palemannie.spittingimage.util.SpittingImageConfig;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class SpitRenderer extends EntityRenderer<SpitEntity> {

    private static final ResourceLocation SPIT_LOCATION = new ResourceLocation(SpittingImage.MODID,"textures/entity/spit/spit.png");
    private final SpitModel<SpitEntity> model;

    public SpitRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SpitModel<>(context.bakeLayer(SpitModel.SPIT_LAYER));
    }

    public void render(SpitEntity spitEntity, float v1, float v2, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(SpittingImageConfig.COMMON.spitModel.get()){
            poseStack.pushPose();

            poseStack.translate(0.0F, 0.1f, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(v2, spitEntity.yRotO, spitEntity.getYRot()) - 90.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(v2, spitEntity.xRotO, spitEntity.getXRot())));

            this.model.setupAnim(spitEntity, v2, 0.0F, -0.1F, 0.0F, 0.0F);
            VertexConsumer $$6 = bufferSource.getBuffer(this.model.renderType(SPIT_LOCATION));
            this.model.renderToBuffer(poseStack, $$6, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();
            super.render(spitEntity, v1, v2, poseStack, bufferSource, packedLight);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SpitEntity spit) { return SPIT_LOCATION; }

}