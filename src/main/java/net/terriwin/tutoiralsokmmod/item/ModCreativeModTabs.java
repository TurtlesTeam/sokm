package net.terriwin.tutoiralsokmmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.terriwin.tutoiralsokmmod.Tutorial_SOKM_mod;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tutorial_SOKM_mod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SOKM_TAB = CREATIVE_MODE_TABS.register("sokm_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.bundlesweets.get()))
                    .title(Component.translatable("creativetab.sokm_tab"))
                    .displayItems((qParameters, pOutput)-> {
                        pOutput.accept(ModItems.bundlesweets.get());
                        pOutput.accept(ModItems.bundledrinks.get());
                        pOutput.accept(ModItems.lignitecoal.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
