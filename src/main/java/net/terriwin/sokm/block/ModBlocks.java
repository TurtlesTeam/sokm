package net.terriwin.sokm.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.terriwin.sokm.block.custom.CyberCakeBlock;
import net.terriwin.sokm.block.custom.FuelBlock;
import net.terriwin.sokm.fluids.ModFluids;
import net.terriwin.sokm.item.ModItems;
import net.terriwin.sokm.sokm;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(sokm.MOD_ID);

    public static final DeferredBlock<Block> lignitecoal_block = registryFuelObject("lignitecoal_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_BLOCK)), 12800);
    public static final DeferredBlock<Block> lignitecoal_ore = registryObject("lignitecoal_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_ORE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> deepslate_lignitecoal_ore = registryObject("deepslate_lignitecoal_ore",
            () -> new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COAL_ORE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> glaze = registryObject("glaze",
            () -> new LiquidBlock(ModFluids.SOURCE_GLAZE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).noLootTable()));


    public static final DeferredBlock<Block> cybercake = registryObject("cybercake",
            () -> new CyberCakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).noLootTable()));


    private static <T extends Block> DeferredBlock<T> registryObject(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registryFuelObject(String name, Supplier<T> block, int burnTime) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerFuelBlockItem(name, toReturn, burnTime);
        return toReturn;
    }


    public static final DeferredBlock<LiquidBlock> GLAZE_BLOCK = BLOCKS.register("soap_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_GLAZE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));


    private static <T extends Block> void registryBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> void registerFuelBlockItem(String name, DeferredBlock<T> block, int burnTime) {
        ModItems.ITEMS.register(name, () -> new FuelBlock(block.get(), new Item.Properties(), burnTime));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
