package mett.palemannie.spittingimage;

import mett.palemannie.spittingimage.net.ModMessages;
import mett.palemannie.spittingimage.net.packets.SpitC2SPacket;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class KeyBinding {

    private static final KeyMapping SPIT_KEY = new KeyMapping("spittingimage.key.spit", GLFW.GLFW_KEY_COMMA, "key.categories.spittingimage");

    public static void setup()
    {
        ClientRegistry.registerKeyBinding(SPIT_KEY);
    }

    @SubscribeEvent
    public static void onKeyEvent(InputEvent.KeyInputEvent event)
    {
        if(SPIT_KEY.consumeClick())
        {
            ModMessages.sendToServer(new SpitC2SPacket());
        }
    }
}
