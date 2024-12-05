package mett.palemannie.spittingimage.net.packets;

import mett.palemannie.spittingimage.server.ServerPlayHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class SpitC2SPacket {
    public SpitC2SPacket(){
    }
    public SpitC2SPacket(FriendlyByteBuf buf){
    }
    public void encode(FriendlyByteBuf buf){
    }

    public boolean handle(CustomPayloadEvent.Context context){
        context.enqueueWork(()-> {
            ServerPlayer player = context.getSender();

            if(player == null) return;
            if(player.isSpectator()) return;

            ServerPlayHandler.handleSpitting(player);

        });
        return true;
    }
}
