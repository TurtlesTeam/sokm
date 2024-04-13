package net.terriwin.sokm.item;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.terriwin.sokm.fluids.ModFluids;
import net.terriwin.sokm.item.custom.FuelItem;
import net.terriwin.sokm.sokm;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, sokm.MOD_ID);

    public static final RegistryObject<Item> bundlesweets = ITEMS.register("bundlesweets",
            ()-> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> bundledrinks = ITEMS.register("bundledrinks",
            ()-> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> lignitecoal = ITEMS.register("lignitecoal",
            ()-> new FuelItem(new Item.Properties(), 1280 ));



    //bases

    public static final RegistryObject<Item> andensite_base = ITEMS.register("andensite_base",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> brass_base = ITEMS.register("brass_base",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> radiant_base = ITEMS.register("radiant_base",
            ()-> new Item(new Item.Properties()));

    //

    public static final RegistryObject<Item> donut = ITEMS.register("donut",
            ()-> new Item(new Item.Properties().food(ModFoods.donut).stacksTo(16)));

    public static final RegistryObject<Item> glaze_bucket = ITEMS.register("glaze_bucket",
            () -> new BucketItem(ModFluids.SOURCE_GLAZE,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
