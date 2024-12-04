package mett.palemannie.spittingimage.server;

import mett.palemannie.spittingimage.entity.custom.SpitEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ServerPlayHandler {

    public static void handleSpitting(ServerPlayer player){

        ServerLevel sevel = player.serverLevel();
        Random rdm = new Random();

        ///Entity
        SpitEntity spit = new SpitEntity(sevel, player);
        float re = (float)rdm.nextInt(4500,5000)/10000;
        float ye = player.getYRot();
        float xe = player.getXRot();
        float ze = 0f;
        spit.shootFromRotation(player, xe, ye, ze, re, 1f);
        sevel.addFreshEntity(spit);

        ///Sound
        Level lvl = player.level();

        double posX = player.getX();
        double posY = player.getY();
        double posZ = player.getZ();
        float r = 0.8f + lvl.random.nextFloat() * 0.3f;

        lvl.playSound(null, posX, posY, posZ, SoundEvents.LLAMA_SPIT, SoundSource.BLOCKS, 1f, r);

        ///Particle
        Vec3 vec3 = player.getViewVector(1f);
        Vec3 MousePos = player.getEyePosition();

        double x = player.getX() + vec3.x/4;
        double y = MousePos.y + vec3.y/4;
        double z = player.getZ() + vec3.z/4;

        if(lvl instanceof ServerLevel slevel) {
            slevel.sendParticles(ParticleTypes.SPIT, x, y, z, 3, 0d, 0d, 0d,0.15d);
        }
    }
}
