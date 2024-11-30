package mett.palemannie.spittingimage.entity;

import mett.palemannie.spittingimage.SpittingImage;
import mett.palemannie.spittingimage.entity.custom.SpitEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, SpittingImage.MODID);

    public static final RegistryObject<EntityType<SpitEntity>> SPIT =
            ENTITY_TYPES.register("spit", () -> EntityType.Builder.<SpitEntity>of(SpitEntity::new, MobCategory.MISC)
                    .sized(0.2f, 0.2f)
                    .fireImmune()
                    .build("spit"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
