package com.tizio.infinitepotion.mixin;

import com.tizio.infinitepotion.InfinitePotion;
import com.tizio.infinitepotion.interfaces.IEffectDuration;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Consumer;

@Mixin(PotionContents.class)
public abstract class DurationModifier {

    @Shadow public abstract void forEachEffect(final Consumer<MobEffectInstance> consumer, final float durationScale);

    /**
     * @author thetizio
     * @reason modify potion duration
     */
    @Overwrite
    public void applyToLivingEntity(final LivingEntity entity, final float durationScale){
        Level var4 = entity.level();
        if (var4 instanceof ServerLevel serverLevel) {
            Player var10000;
            if (entity instanceof Player playerEntity) {
                var10000 = playerEntity;
            } else {
                var10000 = null;
            }

            Player player = var10000;
            this.forEachEffect((effect) -> {
                if (((MobEffect)effect.getEffect().value()).isInstantaneous()) {
                    ((MobEffect)effect.getEffect().value()).applyInstantaneousEffect(serverLevel, player, player, entity, effect.getAmplifier(), (double)1.0F);
                } else {
                    ((IEffectDuration)(effect)).setMultiplier(InfinitePotion.durationMultiplier);
                    entity.addEffect(effect);
                }

            }, durationScale);
        }
    }
}