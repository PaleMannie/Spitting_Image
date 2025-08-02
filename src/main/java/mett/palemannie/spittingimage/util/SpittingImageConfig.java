package mett.palemannie.spittingimage.util;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SpittingImageConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {
        public final ForgeConfigSpec.DoubleValue spitDamage;
        public final ForgeConfigSpec.BooleanValue spitModel;
        public final ForgeConfigSpec.IntValue spitCooldown;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("Spitting Image");

            spitDamage = builder
                    .comment("How much damage the spit deals (default: 1.0)")
                    .defineInRange("spitDamage", 1.0, 0.0, Float.MAX_VALUE);

            spitModel = builder.comment("Enables/Disables the player spit model").define("spitModel", true);

            spitCooldown = builder.comment("Cooldown in ticks between spitting (20 ticks = 1 second)").
                    defineInRange("spitCooldown", 3, 1, Integer.MAX_VALUE-1);

            builder.pop();
        }
    }
}