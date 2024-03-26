package net.terriwin.sokm.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.terriwin.sokm.sokm;
import org.joml.Vector3f;

public class ModFluidType {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation(sokm.MOD_ID,"misc/glaze");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation(sokm.MOD_ID,"misc/glaze");
    public static final ResourceLocation GLAZE_OVERLAY_RL = new ResourceLocation(sokm.MOD_ID,"misc/glaze");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, sokm.MOD_ID);

    public static final RegistryObject<FluidType> GLAZE_TYPE = register("glaze_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));



    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, GLAZE_OVERLAY_RL,
                0xA1FDAAFD, new Vector3f(253f / 255f, 170f / 255f, 195f / 253f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}