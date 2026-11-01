package com.tizio.infinitepotion.mixin;

import com.tizio.infinitepotion.interfaces.IEffectDuration;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobEffectInstance.class)
public class DurationModifierMethod implements IEffectDuration {

    @Shadow
    private int duration;

    public void setMultiplier(double multiplier) {
        this.duration*=multiplier;
    }
}