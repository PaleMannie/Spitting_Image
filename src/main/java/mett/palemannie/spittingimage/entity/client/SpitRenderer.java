package mett.palemannie.spittingimage.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.entity.custom.SpitEntity;
import mett.palemannie.spittingimage.util.SpittingImageConfig;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LlamaSpitRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class SpitRenderer extends EntityRenderer<SpitEntity, LlamaSpitRenderState> {

    private static final Identifier SPIT_LOCATION = Identifier.fromNamespaceAndPath(SpittingImage.MODID,"textures/entity/spit/spit.png");
    private final SpitModel model;

    public SpitRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SpitModel(context.bakeLayer(SpitModel.LAYER_LOCATION));
    }

    @Override
    public void submit(LlamaSpitRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {


        if(SpittingImageConfig.COMMON.spitModel.get()) {

            poseStack.pushPose();
            poseStack.translate(0.0F, 0.15F, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(renderState.yRot - 90.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(renderState.xRot));
            nodeCollector.submitModel(this.model, renderState, poseStack, this.model.renderType(SPIT_LOCATION), renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.outlineColor, (ModelFeatureRenderer.CrumblingOverlay) null);
            poseStack.popPose();
            super.submit(renderState, poseStack, nodeCollector, cameraRenderState);
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