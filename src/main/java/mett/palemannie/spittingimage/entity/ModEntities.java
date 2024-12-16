package mett.palemannie.spittingimage.entity;

import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.entity.custom.SpitEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), SpittingImage.MODID);

    public static final RegistryObject<EntityType<SpitEntity>> SPIT =
            ENTITY_TYPES.register("spit", () -> build(EntityType.Builder.<SpitEntity>of(SpitEntity::new, MobCategory.MISC)
                    .sized(0.2f, 0.2f)
                    .fireImmune(), "spit"));

    private static <T extends Entity> EntityType<T> build(EntityType.Builder<T> builder, String type) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SpittingImage.MODID, type));
        return builder.build(key);
    }

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
