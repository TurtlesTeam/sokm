package net.terriwin.sokm;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.terriwin.sokm.block.ModBlocks;
import net.terriwin.sokm.fluids.ModFluidType;
import net.terriwin.sokm.fluids.ModFluids;
import net.terriwin.sokm.item.ModCreativeModTabs;
import net.terriwin.sokm.item.ModItems;
import org.joml.Vector3f;

import javax.annotation.ParametersAreNonnullByDefault;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(sokm.MOD_ID)
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class sokm {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "sokm";


    public sokm(IEventBus modEventBus, ModContainer ignoredModContainer) {
        ModCreativeModTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModFluidType.register(modEventBus);
        ModFluids.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.bundlesweets);
            event.accept(ModItems.bundledrinks);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }

        @SubscribeEvent
        public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
            event.registerFluidType(new IClientFluidTypeExtensions() {
                @Override
                public ResourceLocation getStillTexture() {
                    return ModFluidType.WATER_STILL_RL;
                }

                @Override
                public ResourceLocation getFlowingTexture() {
                    return ModFluidType.WATER_FLOWING_RL;
                }

                @Override
                public ResourceLocation getOverlayTexture() {
                    return ModFluidType.GLAZE_OVERLAY_RL;
                }

                @Override
                public int getTintColor() {
                    return 0xA1FDAAFD;
                }

                @Override
                public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level,
                                               int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                    return new Vector3f(253f / 255f, 170f / 255f, 195f / 253f);
                }

                @Override
                public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance,
                                            float partialTick, float nearDistance, float farDistance, FogShape shape) {
                    RenderSystem.setShaderFogStart(1f);
                    RenderSystem.setShaderFogEnd(6f);
                }
            }, ModFluidType.GLAZE_TYPE.get());
        }
    }
}
