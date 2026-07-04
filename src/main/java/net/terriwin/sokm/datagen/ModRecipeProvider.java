package net.terriwin.sokm.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.terriwin.sokm.block.ModBlocks;
import net.terriwin.sokm.item.ModItems;
import net.terriwin.sokm.sokm;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ParametersAreNonnullByDefault
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> lignitecoal_smeltables = List.of(ModBlocks.lignitecoal_ore.get(), ModBlocks.deepslate_lignitecoal_ore.get());

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        oreBlasting(pRecipeOutput, lignitecoal_smeltables, RecipeCategory.MISC, ModItems.lignitecoal.get(), 0.25f, 100, "lignitecoal");
        oreSmelting(pRecipeOutput, lignitecoal_smeltables, RecipeCategory.MISC, ModItems.lignitecoal.get(), 0.25f, 200, "lignitecoal");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.lignitecoal_block.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', ModItems.lignitecoal.get())
                .unlockedBy(getHasName(ModItems.lignitecoal.get()), has(ModItems.lignitecoal.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.lignitecoal.get(), 9)
                .requires(ModBlocks.lignitecoal_block.get())
                .unlockedBy(getHasName(ModBlocks.lignitecoal_block.get()), has(ModBlocks.lignitecoal_block.get()))
                .save(pRecipeOutput);
    }


    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> pFactory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer, pFactory)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(sokm.MOD_ID, getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike)));
        }
    }
}
