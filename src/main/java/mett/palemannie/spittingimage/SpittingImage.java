package mett.palemannie.spittingimage;

import com.mojang.logging.LogUtils;
import mett.palemannie.spittingimage.entity.ModEntities;
import mett.palemannie.spittingimage.item.ModItems;
import mett.palemannie.spittingimage.net.ModMessages;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SpittingImage.MODID)
public class SpittingImage
{
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "spittingimage";
    public static SpittingImage instance;

    public SpittingImage() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
        eventBus.register(this);
        instance = this;
        ModItems.register(eventBus);
        ModEntities.register(eventBus);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
        {
            eventBus.addListener(KeyBinding::registerKeys);
        });

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientSetup(FMLClientSetupEvent e)
    {
        KeyBinding.setup();
        EntityRenderers.register(ModEntities.SPIT.get(), ThrownItemRenderer::new);

    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModMessages::register);
    }
}
