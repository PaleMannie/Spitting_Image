package mett.palemannie.spittingimage.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBind {public static final String KEY_CATEGORY_SPITTINGIMAGE = "spittingimage";
    public static final String KEY_SPITTING = "spittingimage.key.spit";

    public static final KeyMapping SPIT_KEY = new KeyMapping(KEY_SPITTING, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_COMMA, KeyMapping.Category.register(Identifier.parse(KEY_CATEGORY_SPITTINGIMAGE)), 0);
}
