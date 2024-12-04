package mett.palemannie.spittingimage.net.packets;

import mett.palemannie.spittingimage.server.ServerPlayHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpitC2SPacket {
    public SpitC2SPacket(){
    }
    public SpitC2SPacket(FriendlyByteBuf buf){
    }
    public void toBytes(FriendlyByteBuf buf){
    }

    public boolean handle(Supplier<CustomPayloadEvent.Context> supplier){

        CustomPayloadEvent.Context context = supplier.get();
        context.enqueueWork(()-> {

            ServerPlayer player = context.getSender();
            if(player == null) return;
            if(player.isSpectator()) return;

            ServerPlayHandler.handleSpitting(player);

        });
        return true;
    }
}
