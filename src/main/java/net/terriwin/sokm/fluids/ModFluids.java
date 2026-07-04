package net.terriwin.sokm.fluids;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.terriwin.sokm.block.ModBlocks;
import net.terriwin.sokm.item.ModItems;
import net.terriwin.sokm.sokm;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, sokm.MOD_ID);

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> SOURCE_GLAZE = FLUIDS.register("source_glaze",
            () -> new BaseFlowingFluid.Source(ModFluids.glaze_fluid_properties));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_GLAZE = FLUIDS.register("flowing_glaze",
            () -> new BaseFlowingFluid.Flowing(ModFluids.glaze_fluid_properties));


    public static final BaseFlowingFluid.Properties glaze_fluid_properties = new BaseFlowingFluid.Properties(
            ModFluidType.GLAZE_TYPE, SOURCE_GLAZE, FLOWING_GLAZE)
            .slopeFindDistance(4).levelDecreasePerBlock(4).block(ModBlocks.GLAZE_BLOCK)
            .bucket(ModItems.glaze_bucket);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
