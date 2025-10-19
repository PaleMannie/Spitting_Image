package mett.palemannie.spittingimage.event;

import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.net.ModMessages;
import mett.palemannie.spittingimage.net.packets.C2SSpitSpawnPacket;
import mett.palemannie.spittingimage.util.KeyBind;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = SpittingImage.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){

            Minecraft minecraft = Minecraft.getInstance();

            if (minecraft.player == null) return;
            if(KeyBind.SPIT_KEY.consumeClick()) {

                ModMessages.sendToServer(new C2SSpitSpawnPacket());
            }
        }

        @Mod.EventBusSubscriber(modid = SpittingImage.MODID, value = Dist.CLIENT)
        public static class ClientModBusEvents {
            @SubscribeEvent
            public static void onKeyRegister(RegisterKeyMappingsEvent event){
                event.register(KeyBind.SPIT_KEY);
            }
        }
    }
}
