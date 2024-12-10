package mett.palemannie.spittingimage.net;

import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.net.packets.SpitC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.*;

public class ModMessages {
    private static int PacketID = 0;
    private static int id(){
        return PacketID++;
    }
    final static int version = 1;

    private static final SimpleChannel INSTANCE = ChannelBuilder.named(ResourceLocation.fromNamespaceAndPath(SpittingImage.MODID, "messages"))
            .networkProtocolVersion(version)
            .clientAcceptedVersions(((status, version1) -> true))
            .serverAcceptedVersions(((status, version1) -> true))
            .simpleChannel();

    public static void register(){
        INSTANCE.messageBuilder(SpitC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SpitC2SPacket::new)
                .encoder(SpitC2SPacket::encode)
                .consumerMainThread(SpitC2SPacket::handle)
                .add();
    }

    public static void sendToServer(Object message){
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }

}
