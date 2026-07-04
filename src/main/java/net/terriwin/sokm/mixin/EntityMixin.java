package net.terriwin.sokm.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.terriwin.sokm.fluids.ModFluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Slows entity movement while inside the glaze fluid, giving it a thick, viscous feel.
 *
 * <p>The original 1.20.1 mixin copied the whole {@code Entity#move} body and cancelled the vanilla
 * call. That approach is extremely fragile across Minecraft versions. Here we instead scale the
 * movement vector at the head of {@code move}, which preserves the "slow in glaze" gameplay effect
 * while staying robust against internal changes to {@code move}.
 */
@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract Level level();

    @Shadow
    public abstract BlockPos blockPosition();

    @ModifyVariable(method = "move", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private Vec3 sokm$slowInGlaze(Vec3 movement) {
        if (this.level().getFluidState(this.blockPosition()).getFluidType() == ModFluidType.GLAZE_TYPE.get()) {
            return movement.multiply(0.5D, 0.5D, 0.5D);
        }
        return movement;
    }
}
