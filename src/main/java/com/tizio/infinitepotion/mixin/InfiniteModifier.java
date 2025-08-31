package com.tizio.infinitepotion.mixin;

import com.tizio.infinitepotion.InfinitePotion;
import com.tizio.infinitepotion.interfaces.DurationInterface;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public class InfiniteModifier {

	@Inject(at = @At("HEAD"), method = "finishUsing",cancellable = true)
	private void init(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {

		PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
		if (playerEntity instanceof ServerPlayerEntity) {
			Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
		}

		if (!world.isClient) {
			PotionContentsComponent potionContentsComponent = (PotionContentsComponent)stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
			potionContentsComponent.forEachEffect((effect) -> {
				if (((StatusEffect)effect.getEffectType().value()).isInstant()) {
					((StatusEffect)effect.getEffectType().value()).applyInstantEffect(playerEntity, playerEntity, user, effect.getAmplifier(), (double)1.0F);
				} else {
					((DurationInterface)effect).setMultiplier(InfinitePotion.durationMultiplier);
					user.addStatusEffect(effect);
				}

			});
		}

		cir.setReturnValue(stack);

	}
}