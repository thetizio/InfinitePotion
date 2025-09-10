package com.tizio.infinitepotion.mixin;

import com.tizio.infinitepotion.Config;
import com.tizio.infinitepotion.interfaces.DurationInterface;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public class InfiniteModifier {

    @Inject(method = "finishUsingItem",at = @At("HEAD"), cancellable = true)
    public void finishUsing(ItemStack stack, Level level, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir){

        Player player = entity instanceof Player ? (Player)entity : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, stack);
        }

        if (!level.isClientSide) {
            for(MobEffectInstance mobeffectinstance : PotionUtils.getMobEffects(stack)) {
                if (mobeffectinstance.getEffect().isInstantenous()) {
                    mobeffectinstance.getEffect().applyInstantenousEffect(player, player, entity, mobeffectinstance.getAmplifier(), 1.0D);
                } else {
                    ((DurationInterface)mobeffectinstance).setMultiplier(Config.DRINKMULTIPLIER.get());
                    entity.addEffect(new MobEffectInstance(mobeffectinstance));
                    ((DurationInterface)mobeffectinstance).setMultiplier(1/Config.DRINKMULTIPLIER.get());
                }
            }
        }

        cir.setReturnValue(stack);

    }
}