package net.terriwin.sokm.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.terriwin.sokm.sokm;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class ModFluidType {
    public static final ResourceLocation WATER_STILL_RL = ResourceLocation.fromNamespaceAndPath(sokm.MOD_ID, "block/glaze");
    public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.fromNamespaceAndPath(sokm.MOD_ID, "block/glazeanim");
    public static final ResourceLocation GLAZE_OVERLAY_RL = ResourceLocation.fromNamespaceAndPath(sokm.MOD_ID, "block/glaze");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, sokm.MOD_ID);

    public static final Supplier<FluidType> GLAZE_TYPE = register("glaze_fluid",
            FluidType.Properties.create().density(1400).viscosity(2000).motionScale(0.008).canSwim(false).supportsBoating(true)
                    .sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));


    private static Supplier<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, GLAZE_OVERLAY_RL,
                0xA1FDAAFD, new Vector3f(253f / 255f, 170f / 255f, 195f / 253f), properties));
    }


    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
