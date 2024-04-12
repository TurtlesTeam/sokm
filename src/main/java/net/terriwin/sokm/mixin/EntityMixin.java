package net.terriwin.sokm.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.extensions.IForgeEntity;
import net.terriwin.sokm.fluids.ModFluidType;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public abstract class EntityMixin implements IForgeEntity {
    private static final Logger LOGGER = LogUtils.getLogger();
    @Shadow public abstract Level level();

    @Shadow public abstract BlockPos blockPosition();

    @Shadow public boolean noPhysics;



    @Shadow public abstract double getX();

    @Shadow public abstract double getY();

    @Shadow public abstract double getZ();

    @Shadow public abstract void setPos(double p_20210_, double p_20211_, double p_20212_);

    @Shadow public boolean wasOnFire;

    @Shadow public abstract boolean isOnFire();

    @Shadow protected abstract Vec3 limitPistonMovement(Vec3 pPos);

    @Shadow protected Vec3 stuckSpeedMultiplier;

    @Shadow public abstract void setDeltaMovement(Vec3 pDeltaMovement);

    @Shadow protected abstract Vec3 maybeBackOffFromEdge(Vec3 pVec, MoverType pMover);

    @Shadow protected abstract Vec3 collide(Vec3 pVec);

    @Shadow public float fallDistance;

    @Shadow public abstract Vec3 position();

    @Shadow public abstract void resetFallDistance();

    @Shadow public boolean horizontalCollision;

    @Shadow public boolean verticalCollision;

    @Shadow public boolean verticalCollisionBelow;

    @Shadow public boolean minorHorizontalCollision;

    @Shadow protected abstract boolean isHorizontalCollisionMinor(Vec3 pDeltaMovement);

    @Shadow public abstract void setOnGroundWithKnownMovement(boolean pOnGround, Vec3 pMovement);

    @Shadow @Deprecated public abstract BlockPos getOnPosLegacy();

    @Shadow public abstract boolean onGround();

    @Shadow protected abstract void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos);

    @Shadow public abstract boolean isRemoved();

    @Shadow public abstract Vec3 getDeltaMovement();

    @Shadow public abstract void setDeltaMovement(double pX, double pY, double pZ);

    @Shadow protected abstract Entity.MovementEmission getMovementEmission();

    @Shadow public abstract boolean isPassenger();

    @Shadow public float flyDist;

    @Shadow public abstract BlockPos getOnPos();

    @Shadow protected abstract boolean isStateClimbable(BlockState pState);

    @Shadow public float walkDist;

    @Shadow public float moveDist;

    @Shadow protected abstract float nextStep();

    @Shadow protected abstract boolean vibrationAndSoundEffectsFromBlock(BlockPos pPos, BlockState pState, boolean pPlayStepSound, boolean pBroadcastGameEvent, Vec3 p_286448_);

    @Shadow private float nextStep;

    @Shadow public abstract boolean isInWater();

    @Shadow protected abstract void waterSwimSound();

    @Shadow public abstract void gameEvent(GameEvent pEvent);

    @Shadow protected abstract void processFlappingMovement();

    @Shadow protected abstract void tryCheckInsideBlocks();

    @Shadow protected abstract float getBlockSpeedFactor();

    @Shadow public abstract AABB getBoundingBox();

    @Shadow private int remainingFireTicks;

    @Shadow public abstract void setRemainingFireTicks(int pRemainingFireTicks);

    @Shadow protected abstract int getFireImmuneTicks();

    @Shadow public boolean isInPowderSnow;

    @Shadow public abstract boolean isInWaterRainOrBubble();

    @Shadow public abstract boolean isInFluidType();

    @Shadow protected abstract void playEntityOnFireExtinguishedSound();

    @Shadow private Level level;

    @Inject(method = "move",at = @At("HEAD"),cancellable = true)
    public void onMove(MoverType pType, Vec3 pPos, CallbackInfo ci){
        Vec3 pPos1 = pPos.multiply(0.5f,0.5f,0.5f);
        //LOGGER.warn(""+this.level().getFluidState(this.blockPosition()).getFluidType());
        if (this.level().getFluidState(this.blockPosition()).getFluidType() == ModFluidType.GLAZE_TYPE.get()){
            if (this.noPhysics) {
                this.setPos(this.getX() + pPos1.x, this.getY() + pPos1.y, this.getZ() + pPos1.z);
            } else {
                this.wasOnFire = this.isOnFire();
                if (pType == MoverType.PISTON) {
                    pPos1 = this.limitPistonMovement(pPos1);
                    if (pPos1.equals(Vec3.ZERO)) {
                        return;
                    }
                }

                this.level().getProfiler().push("move");
                if (this.stuckSpeedMultiplier.lengthSqr() > 1.0E-7D) {
                    pPos1 = pPos1.multiply(this.stuckSpeedMultiplier);
                    this.stuckSpeedMultiplier = Vec3.ZERO;
                    this.setDeltaMovement(Vec3.ZERO);
                }

                pPos1 = this.maybeBackOffFromEdge(pPos1, pType);
                Vec3 vec3 = this.collide(pPos1);
                double d0 = vec3.lengthSqr();
                if (d0 > 1.0E-7D) {
                    if (this.fallDistance != 0.0F && d0 >= 1.0D) {
                        BlockHitResult blockhitresult = this.level().clip(new ClipContext(this.position(), this.position().add(vec3), ClipContext.Block.FALLDAMAGE_RESETTING, ClipContext.Fluid.WATER, null));
                        if (blockhitresult.getType() != HitResult.Type.MISS) {
                            this.resetFallDistance();
                        }
                    }

                    this.setPos(this.getX() + vec3.x, this.getY() + vec3.y, this.getZ() + vec3.z);
                }

                this.level().getProfiler().pop();
                this.level().getProfiler().push("rest");
                boolean flag4 = !Mth.equal(pPos1.x, vec3.x);
                boolean flag = !Mth.equal(pPos1.z, vec3.z);
                this.horizontalCollision = flag4 || flag;
                this.verticalCollision = pPos1.y != vec3.y;
                this.verticalCollisionBelow = this.verticalCollision && pPos1.y < 0.0D;
                if (this.horizontalCollision) {
                    this.minorHorizontalCollision = this.isHorizontalCollisionMinor(vec3);
                } else {
                    this.minorHorizontalCollision = false;
                }

                this.setOnGroundWithKnownMovement(this.verticalCollisionBelow, vec3);
                BlockPos blockpos = this.getOnPosLegacy();
                BlockState blockstate = this.level().getBlockState(blockpos);
                this.checkFallDamage(vec3.y, this.onGround(), blockstate, blockpos);
                if (this.isRemoved()) {
                    this.level().getProfiler().pop();
                } else {
                    if (this.horizontalCollision) {
                        Vec3 vec31 = this.getDeltaMovement();
                        this.setDeltaMovement(flag4 ? 0.0D : vec31.x, vec31.y, flag ? 0.0D : vec31.z);
                    }






                    Entity.MovementEmission entity$movementemission = this.getMovementEmission();
                    if (entity$movementemission.emitsAnything() && !this.isPassenger()) {
                        double d1 = vec3.x;
                        double d2 = vec3.y;
                        double d3 = vec3.z;
                        this.flyDist = (float)((double)this.flyDist + vec3.length() * 0.6D);
                        BlockPos blockpos1 = this.getOnPos();
                        BlockState blockstate1 = this.level().getBlockState(blockpos1);
                        boolean flag1 = this.isStateClimbable(blockstate1);
                        if (!flag1) {
                            d2 = 0.0D;
                        }

                        this.walkDist += (float)vec3.horizontalDistance() * 0.6F;
                        this.moveDist += (float)Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3) * 0.6F;
                        if (this.moveDist > this.nextStep() && !blockstate1.isAir()) {
                            boolean flag2 = blockpos1.equals(blockpos);
                            boolean flag3 = this.vibrationAndSoundEffectsFromBlock(blockpos, blockstate, entity$movementemission.emitsSounds(), flag2, pPos1);
                            if (!flag2) {
                                flag3 |= this.vibrationAndSoundEffectsFromBlock(blockpos1, blockstate1, false, entity$movementemission.emitsEvents(), pPos1);
                            }

                            if (flag3) {
                                this.nextStep = this.nextStep();
                            } else if (this.isInWater()) {
                                this.nextStep = this.nextStep();
                                if (entity$movementemission.emitsSounds()) {
                                    this.waterSwimSound();
                                }

                                if (entity$movementemission.emitsEvents()) {
                                    this.gameEvent(GameEvent.SWIM);
                                }
                            }
                        } else if (blockstate1.isAir()) {
                            this.processFlappingMovement();
                        }
                    }

                    this.tryCheckInsideBlocks();
                    float f = this.getBlockSpeedFactor();
                    this.setDeltaMovement(this.getDeltaMovement().multiply((double)f, 1.0D, (double)f));
                    if (this.level().getBlockStatesIfLoaded(this.getBoundingBox().deflate(1.0E-6D)).noneMatch((p_20127_) -> {
                        return p_20127_.is(BlockTags.FIRE) || p_20127_.is(Blocks.LAVA);
                    })) {
                        if (this.remainingFireTicks <= 0) {
                            this.setRemainingFireTicks(-this.getFireImmuneTicks());
                        }

                        if (this.wasOnFire && (this.isInPowderSnow || this.isInWaterRainOrBubble() || this.isInFluidType((fluidType, height) -> this.canFluidExtinguish(fluidType)))) {
                            this.playEntityOnFireExtinguishedSound();
                        }
                    }

                    if (this.isOnFire() && (this.isInPowderSnow || this.isInWaterRainOrBubble() || this.isInFluidType((fluidType, height) -> this.canFluidExtinguish(fluidType)))) {
                        this.setRemainingFireTicks(-this.getFireImmuneTicks());
                    }

                    this.level.getProfiler().pop();
                }
            }
            ci.cancel();
        }
    }
}
