package net.terriwin.sokm.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.terriwin.sokm.sokm;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> creativbleblock = tag("creativbleblock");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(sokm.MOD_ID, name));
        }
    }
    public  static class Items {


        public static final TagKey<Item> creativble = tag("creativble");


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(sokm.MOD_ID, name));
        }

    }
}
