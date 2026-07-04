package net.terriwin.sokm.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.terriwin.sokm.item.ModItems;
import net.terriwin.sokm.sokm;
import net.terriwin.sokm.util.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags,
                               @Nullable ExistingFileHelper existingFileHelper) {

        super(pOutput, pLookupProvider, pBlockTags, sokm.MOD_ID, existingFileHelper);

    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        this.tag(ModTags.Items.creativble)
                .add(ModItems.donut.get());

        this.tag(ItemTags.COALS)
                .add(ModItems.lignitecoal.get());

    }

}
