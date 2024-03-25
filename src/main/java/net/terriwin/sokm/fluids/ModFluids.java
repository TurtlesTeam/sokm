package net.terriwin.sokm.fluids;


import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.terriwin.sokm.block.ModBlocks;
import net.terriwin.sokm.item.ModItems;
import net.terriwin.sokm.sokm;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, sokm.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_GLAZE = FLUIDS.register("soap_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.glaze_fluid_properties));
    public static final RegistryObject<FlowingFluid> FLOWING_GLAZE = FLUIDS.register("flowing_soap_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.glaze_fluid_properties));


    public static final ForgeFlowingFluid.Properties glaze_fluid_properties = new ForgeFlowingFluid.Properties(
            ModFluidType.GLAZE_TYPE, SOURCE_GLAZE, FLOWING_GLAZE)
            .slopeFindDistance(4).levelDecreasePerBlock(4).block(ModBlocks.GLAZE_BLOCK)
            .bucket(ModItems.glaze_bucket);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}