package mett.palemannie.spittingimage.server;

import mett.palemannie.spittingimage.entity.client.ClientSpitData;
import mett.palemannie.spittingimage.entity.custom.SpitEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class ServerPlayHandler {

    private static final Map<UUID, Long> spitCooldowns = new HashMap<>();

    public static void handleSpitting(ServerPlayer player) {

        ServerLevel sevel = player.level();
        Random rdm = new Random();
        Level lvl = player.level();

        double posX = player.getX();
        double posY = player.getY();
        double posZ = player.getZ();
        float r = 0.8f + lvl.random.nextFloat() * 0.3f;

        long currentTick = player.level().getGameTime();
        long cooldown = ClientSpitData.getCooldown();
        long lastUsed = spitCooldowns.getOrDefault(player.getUUID(), -cooldown - 1);

        if (currentTick - lastUsed < cooldown) {

            player.displayClientMessage(Component.translatable("spittingimage.spitcooldown").withStyle(ChatFormatting.RED), true);
            return;
        }

        if (currentTick - lastUsed >= cooldown) {

            ///Entity
            spitCooldowns.put(player.getUUID(), currentTick);

            float speed = (float) rdm.nextInt(4500, 5000) / 10000f;
            float inaccuracy = 1.0f;

            SpitEntity spit = new SpitEntity(sevel, player);
            spit.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, speed, inaccuracy);
            sevel.addFreshEntity(spit);

            ///Sound
            lvl.playSound(null, posX, posY, posZ, SoundEvents.LLAMA_SPIT, SoundSource.BLOCKS, 1f, r);

        }
    }
}
