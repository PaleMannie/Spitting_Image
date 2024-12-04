package mett.palemannie.spittingimage;

import mett.palemannie.spittingimage.net.ModMessages;
import mett.palemannie.spittingimage.net.packets.SpitC2SPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class KeyBinding {
    private static final Map<UUID, Long> cooldownMap = new HashMap<>();
    private static final long COOLDOWN_TIME = 150;

    private static final KeyMapping SPIT_KEY = new KeyMapping("spittingimage.key.spit", GLFW.GLFW_KEY_COMMA, "key.categories.spittingimage");

    public static void setup()
    {
        MinecraftForge.EVENT_BUS.addListener(KeyBinding::onKeyEvent);
    }

    public static void registerKeys(RegisterKeyMappingsEvent evt)
    {
        evt.register(SPIT_KEY);
    }

    @SubscribeEvent
    public static void onKeyEvent(InputEvent.Key event)
    {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;

        LivingEntity player = minecraft.player;


        if(SPIT_KEY.consumeClick())
        {
            UUID playerId = player.getUUID();
            long currentTime = System.currentTimeMillis();

            if (!cooldownMap.containsKey(playerId) || (currentTime - cooldownMap.get(playerId) >= COOLDOWN_TIME)){
                ModMessages.sendToServer(new SpitC2SPacket());
                cooldownMap.put(playerId, currentTime);
            }
        }
    }
}
