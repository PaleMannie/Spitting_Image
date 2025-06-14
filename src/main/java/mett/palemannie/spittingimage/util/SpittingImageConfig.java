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

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("spitting");

            spitDamage = builder
                    .comment("How much damage the spit deals (default: 1.0)")
                    .defineInRange("spitDamage", 1.0, 0.0, Float.MAX_VALUE);

            builder.pop();
        }
    }
}