package mett.palemannie.spittingimage;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

    public SpittingImage()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.register(this);
        instance = this;

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientSetup(FMLClientSetupEvent e)
    {
        KeyBinding.setup();
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
}
