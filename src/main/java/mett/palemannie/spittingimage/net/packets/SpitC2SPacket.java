package mett.palemannie.spittingimage.net.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpitC2SPacket {
    public SpitC2SPacket(){
    }
    public SpitC2SPacket(FriendlyByteBuf buf){
    }
    public void toBytes(FriendlyByteBuf buf){
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){

        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> {

            ServerPlayer player = context.getSender();
            if(player == null) return;
            if(player.isSpectator()) return;

            //

        });
        return true;
    }
}
