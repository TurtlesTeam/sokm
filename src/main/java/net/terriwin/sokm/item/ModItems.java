package net.terriwin.sokm.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.terriwin.sokm.item.custom.FuelItem;
import net.terriwin.sokm.sokm;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, sokm.MOD_ID);

    public static final RegistryObject<Item> bundlesweets = ITEMS.register("bundlesweets",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> bundledrinks = ITEMS.register("bundledrinks",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> lignitecoal = ITEMS.register("lignitecoal",
            ()-> new FuelItem(new Item.Properties(), 1280 ));

    public static final RegistryObject<Item> donut = ITEMS.register("donut",
            ()-> new Item(new Item.Properties().food(ModFoods.donut)));

    



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
