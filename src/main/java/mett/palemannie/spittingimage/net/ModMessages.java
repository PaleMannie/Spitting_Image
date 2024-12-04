package mett.palemannie.spittingimage.net;

import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.net.packets.SpitC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int PacketID = 0;
    private static int id(){
        return PacketID++;
    }
    final static int version = 1;

    public static void register(){
        SimpleChannel net = ChannelBuilder.named(new ResourceLocation(SpittingImage.MODID, "messages"))
                .networkProtocolVersion(version).clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(SpitC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SpitC2SPacket::new)
                .encoder(SpitC2SPacket::toBytes)
                .consumerMainThread(SpitC2SPacket::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->player), message);
    }
}
