package mett.palemannie.spittingimage.item;

import mett.palemannie.spittingimage.SpittingImage;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SpittingImage.MODID);

    public static final RegistryObject<Item> SPIT = ITEMS.register("spit",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SpittingImage.MODID, "spit")))));


    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
