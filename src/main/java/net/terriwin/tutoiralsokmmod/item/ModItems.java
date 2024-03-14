package net.terriwin.tutoiralsokmmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.terriwin.tutoiralsokmmod.Tutorial_SOKM_mod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial_SOKM_mod.MOD_ID);

    public static final RegistryObject<Item> bundlesweets = ITEMS.register("bundlesweets",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> bundledrinks = ITEMS.register("bundledrinks",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> lignitecoal = ITEMS.register("lignitecoal",
            ()-> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
