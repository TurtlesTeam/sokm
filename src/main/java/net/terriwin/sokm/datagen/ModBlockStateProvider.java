package net.terriwin.sokm.datagen;


import net.minecraft.world.level.block.Block;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.terriwin.sokm.block.ModBlocks;
import net.terriwin.sokm.sokm;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, sokm.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.lignitecoal_block);


        blockWithItem(ModBlocks.glaze);


        blockWithItem(ModBlocks.lignitecoal_ore);
        blockWithItem(ModBlocks.deepslate_lignitecoal_ore);
    }

    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
