package mett.palemannie.spittingimage.server;

import mett.palemannie.spittingimage.entity.custom.SpitEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ServerPlayHandler {

    public static void handleSpitting(ServerPlayer player){

        ServerLevel sevel = player.serverLevel();
        Random rdm = new Random();
        Level lvl = player.level();

        ///Entity
        float speed = (float) rdm.nextInt(4500, 5000) / 10000f;
        float inaccuracy = 1.0f;

        SpitEntity spit = new SpitEntity(sevel, player);
        spit.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, speed, inaccuracy);
        sevel.addFreshEntity(spit);

        ///Sound
        double posX = player.getX();
        double posY = player.getY();
        double posZ = player.getZ();
        float r = 0.8f + lvl.random.nextFloat() * 0.3f;
        lvl.playSound(null, posX, posY, posZ, SoundEvents.LLAMA_SPIT, SoundSource.BLOCKS, 1f, r);
    }
}