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
import net.minecraft.client.renderer.entity.state.LlamaSpitRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SpitRenderer extends EntityRenderer<SpitEntity, LlamaSpitRenderState> {

    private static final ResourceLocation SPIT_LOCATION = ResourceLocation.fromNamespaceAndPath(SpittingImage.MODID,"textures/entity/spit/spit.png");
    private final SpitModel model;

    public SpitRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SpitModel(context.bakeLayer(SpitModel.LAYER_LOCATION));
    }


    public void render(LlamaSpitRenderState pRenderState, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight) {

        if(SpittingImageConfig.COMMON.spitModel.get()){
            pPoseStack.pushPose();

            pPoseStack.translate(0f, 0.1f, 0f);

            pPoseStack.mulPose(Axis.YP.rotationDegrees(pRenderState.yRot));
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-pRenderState.xRot + 180f));

            this.model.setupAnim(pRenderState);
            VertexConsumer vertexconsumer = pBufferSource.getBuffer(this.model.renderType(SPIT_LOCATION));
            this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY);
            pPoseStack.popPose();
            super.render(pRenderState, pPoseStack, pBufferSource, pPackedLight);
        }
    }

    public LlamaSpitRenderState createRenderState() {
        return new LlamaSpitRenderState();
    }

    public void extractRenderState(SpitEntity pEntity, LlamaSpitRenderState renderState, float pPartialTick) {
        super.extractRenderState(pEntity, renderState, pPartialTick);
        renderState.xRot = pEntity.getXRot(pPartialTick);
        renderState.yRot = pEntity.getYRot(pPartialTick);
    }
}