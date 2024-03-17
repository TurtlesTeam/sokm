package net.terriwin.sokm.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
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

        simpleItem(ModItems.bundlesweets);
        simpleItem(ModItems.bundledrinks);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return  withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(sokm.MOD_ID, "item/"+ item.getId().getPath()));
    }

}
