package net.terriwin.sokm.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.terriwin.sokm.sokm;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, sokm.MOD_ID  );

    public  static  final RegistryObject<Block> lignitecoal_block = registryObject("lignitecoal_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK)));

    public  static  final RegistryObject<Block> lignitecoal_ore = registryObject("lignitecoal_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_ORE)));

    public  static  final RegistryObject<Block> lignitecoal_deepslate_ore = registryObject("lignitecoal_deepslate_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COAL_ORE)));






    private  static <T extends Block> RegistryObject<T> registryObject(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties()));


    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
