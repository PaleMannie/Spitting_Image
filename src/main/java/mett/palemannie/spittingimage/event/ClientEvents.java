package mett.palemannie.spittingimage.event;

import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.net.ModMessages;
import mett.palemannie.spittingimage.net.packets.SpitC2SPacket;
import mett.palemannie.spittingimage.util.KeyBind;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientEvents {

    private static final Map<UUID, Long> cooldownMap = new HashMap<>();
    private static final long COOLDOWN_TIME = 150;

    @Mod.EventBusSubscriber(modid = SpittingImage.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){

            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player == null) return;
            LivingEntity player = minecraft.player;

            if(KeyBind.SPIT_KEY.consumeClick()) {
                UUID playerId = player.getUUID();
                long currentTime = System.currentTimeMillis();

                if (!cooldownMap.containsKey(playerId) || (currentTime - cooldownMap.get(playerId) >= COOLDOWN_TIME)){
                    ModMessages.sendToServer(new SpitC2SPacket());
                    cooldownMap.put(playerId, currentTime);
                }
            }
        }

        @Mod.EventBusSubscriber(modid = SpittingImage.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ClientModBusEvents {
            @SubscribeEvent
            public static void onKeyRegister(RegisterKeyMappingsEvent event){
                event.register(KeyBind.SPIT_KEY);
            }
        }
    }
}
