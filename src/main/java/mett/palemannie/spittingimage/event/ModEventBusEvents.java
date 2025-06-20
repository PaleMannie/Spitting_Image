package mett.palemannie.spittingimage.event;

import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.entity.client.SpitModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpittingImage.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(SpitModel.LAYER_LOCATION, SpitModel::createBodyLayer);
    }
}