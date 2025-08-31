package com.tizio.infinitepotion.mixin;

import com.tizio.infinitepotion.interfaces.DurationInterface;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StatusEffectInstance.class)
public abstract class SetDuration implements DurationInterface{

    @Shadow
    private int duration;

    public void setMultiplier(double x){
        this.duration*=x;
    }
}