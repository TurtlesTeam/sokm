package net.terriwin.sokm.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import net.terriwin.sokm.item.ModItems;
import net.terriwin.sokm.sokm;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, sokm.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.lignitecoal);

        simpleItem(ModItems.donut);
        simpleItem(ModItems.glaze_bucket);
        simpleItem(ModItems.bundlesweets);
        simpleItem(ModItems.bundledrinks);
        simpleItem(ModItems.cyberpizza);


        //bases
        simpleItem(ModItems.andensite_base);
        simpleItem(ModItems.brass_base);
        simpleItem(ModItems.radiant_base);
        //
    }

    private void simpleItem(DeferredItem<Item> item) {
        withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(sokm.MOD_ID, "item/" + item.getId().getPath()));
    }

}
