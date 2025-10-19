package mett.palemannie.spittingimage;

import mett.palemannie.spittingimage.entity.ModEntities;
import mett.palemannie.spittingimage.entity.client.SpitRenderer;
import mett.palemannie.spittingimage.net.ModMessages;
import mett.palemannie.spittingimage.util.SpittingImageConfig;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(SpittingImage.MODID)
public class SpittingImage {

    public static final String MODID = "spittingimage";

    public SpittingImage(FMLJavaModLoadingContext context) {

        var modBusGroup = context.getModBusGroup();
        FMLCommonSetupEvent.getBus(modBusGroup).addListener(SpittingImage::commonSetup);

        ModEntities.register(modBusGroup);
        SpittingImageConfig.register();
    }

    private static void commonSetup(final FMLCommonSetupEvent event) {

        event.enqueueWork( ()-> {
            ModMessages.register();
        });
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            EntityRenderers.register(ModEntities.SPIT.get(), SpitRenderer::new);
        }
    }
}
