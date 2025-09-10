package com.tizio.infinitepotion.mixin;

import com.tizio.infinitepotion.InfinitePotion;
import com.tizio.infinitepotion.interfaces.DurationInterface;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public class InfiniteModifier {

	@Inject(at = @At("HEAD"), method = "finishUsing", cancellable = true)
	private void init(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {

		PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
		if (playerEntity instanceof ServerPlayerEntity) {
			Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
		}

		if (!world.isClient) {
			for(StatusEffectInstance statusEffectInstance : PotionUtil.getPotionEffects(stack)) {
				if (statusEffectInstance.getEffectType().isInstant()) {
					statusEffectInstance.getEffectType().applyInstantEffect(playerEntity, playerEntity, user, statusEffectInstance.getAmplifier(), (double)1.0F);
				} else {
					((DurationInterface)statusEffectInstance).setMultiplier(InfinitePotion.durationMultiplier);
					user.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
					((DurationInterface)statusEffectInstance).setMultiplier(1/InfinitePotion.durationMultiplier);
				}
			}
		}

		cir.setReturnValue(stack);

	}
}