package mett.palemannie.spittingimage.net;

import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.net.packets.C2SSpitSpawnPacket;
import mett.palemannie.spittingimage.net.packets.S2CSyncSpitCooldownPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;

public class ModMessages {
    private static int PacketID = 0;
    private static int id(){
        return PacketID++;
    }
    final static int version = 1;

    public static final SimpleChannel INSTANCE = ChannelBuilder.named(ResourceLocation.fromNamespaceAndPath(SpittingImage.MODID, "messages"))
            .networkProtocolVersion(version)
            .clientAcceptedVersions(((status, version1) -> true))
            .serverAcceptedVersions(((status, version1) -> true))
            .simpleChannel();

    public static void register(){
        INSTANCE.messageBuilder(C2SSpitSpawnPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SSpitSpawnPacket::new)
                .encoder(C2SSpitSpawnPacket::encode)
                .consumerMainThread(C2SSpitSpawnPacket::handle)
                .add();

        INSTANCE.messageBuilder(S2CSyncSpitCooldownPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CSyncSpitCooldownPacket::new)
                .encoder(S2CSyncSpitCooldownPacket::toBytes)
                .consumerMainThread(S2CSyncSpitCooldownPacket::handle)
                .add();
    }

    public static void sendToServer(Object message){
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(message, PacketDistributor.PLAYER.with(player));
    }

}
