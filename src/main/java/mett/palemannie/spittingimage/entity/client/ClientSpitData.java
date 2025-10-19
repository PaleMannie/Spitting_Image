package mett.palemannie.spittingimage.entity.client;

import mett.palemannie.spittingimage.util.SpittingImageConfig;

public class ClientSpitData {
    private static int cooldown = SpittingImageConfig.SERVER.spitCooldown.get();

    public static void setCooldown(int value) {
        cooldown = value;
    }

    public static int getCooldown() {
        return cooldown;
    }
}
