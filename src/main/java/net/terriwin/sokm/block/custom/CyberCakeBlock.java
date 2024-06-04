package net.terriwin.sokm.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class CyberCakeBlock extends CakeBlock {

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack $$6 = pPlayer.getItemInHand(pHand);
        Item $$7 = $$6.getItem();
        if ($$6.is(ItemTags.CANDLES) && (Integer)pState.getValue(BITES) == 0) {
            Block $$8 = Block.byItem($$7);
            if ($$8 instanceof CandleBlock) {
                if (!pPlayer.isCreative()) {
                    $$6.shrink(1);
                }

                pLevel.playSound((Player)null, pPos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.setBlockAndUpdate(pPos, CandleCakeBlock.byCandle($$8));
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_CHANGE, pPos);
                pPlayer.awardStat(Stats.ITEM_USED.get($$7));
                return InteractionResult.SUCCESS;
            }
        }

        if (pLevel.isClientSide) {
            if (eat(pLevel, pPos, pState, pPlayer).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if ($$6.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return eat(pLevel, pPos, pState, pPlayer);
    }

    protected static InteractionResult eat(LevelAccessor pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pPlayer.canEat(true)) {
            return InteractionResult.PASS;
        } else {
            pPlayer.awardStat(Stats.EAT_CAKE_SLICE);
            pPlayer.getFoodData().eat(20, 50F);
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 6000, 2, false, false));
            int $$4 = (Integer)pState.getValue(BITES);
            pLevel.gameEvent(pPlayer, GameEvent.EAT, pPos);
            if ($$4 < 6) {
                pLevel.setBlock(pPos, (BlockState)pState.setValue(BITES, $$4 + 1), 3);
            } else {
                pLevel.removeBlock(pPos, false);
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_DESTROY, pPos);
            }

            return InteractionResult.SUCCESS;
        }
    }


    public CyberCakeBlock(Properties pProperties) {
        super(pProperties);
    }



}
