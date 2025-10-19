package mett.palemannie.spittingimage.net.packets;

import mett.palemannie.spittingimage.entity.client.ClientSpitData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class S2CSyncSpitCooldownPacket {

    private final int cooldown;

    public S2CSyncSpitCooldownPacket(int cooldown) {
        this.cooldown = cooldown;
    }

    public S2CSyncSpitCooldownPacket(FriendlyByteBuf buf) {
        this.cooldown = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(cooldown);
    }

    public static void handle(S2CSyncSpitCooldownPacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(()-> {

            ClientSpitData.setCooldown(packet.cooldown);
        });
    }
}
