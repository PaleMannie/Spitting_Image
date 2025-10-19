package mett.palemannie.spittingimage.event;


import mett.palemannie.spittingimage.entity.client.ClientSpitData;
import mett.palemannie.spittingimage.net.ModMessages;
import mett.palemannie.spittingimage.net.packets.S2CSyncSpitCooldownPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            int cooldown = ClientSpitData.getCooldown();
            ModMessages.sendToPlayer(new S2CSyncSpitCooldownPacket(cooldown), player);
        }
    }
}
