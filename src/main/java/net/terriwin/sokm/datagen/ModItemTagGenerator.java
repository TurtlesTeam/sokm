package net.terriwin.sokm.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.terriwin.sokm.block.ModBlocks;
import net.terriwin.sokm.item.ModItems;
import net.terriwin.sokm.sokm;
import net.terriwin.sokm.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_,
                               @Nullable ExistingFileHelper existingFileHelper) {

        super(p_275343_, p_275729_, p_275322_, sokm.MOD_ID, existingFileHelper);

    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(ModTags.Items.creativble)
                .add(ModItems.donut.get()).addTag(ModTags.Items.creativble);

        this.tag(ItemTags.COALS)
                .add(ModItems.lignitecoal.get());

    }

}
