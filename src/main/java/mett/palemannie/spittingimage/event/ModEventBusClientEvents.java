package mett.palemannie.spittingimage.event;

import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.entity.client.SpitModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpittingImage.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(SpitModel.SPIT_LAYER, SpitModel::createBodyLayer);
    }
}