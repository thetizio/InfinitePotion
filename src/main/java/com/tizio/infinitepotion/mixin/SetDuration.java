package com.tizio.infinitepotion.mixin;

import com.tizio.infinitepotion.interfaces.DurationInterface;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(MobEffectInstance.class)
public abstract class SetDuration implements DurationInterface {

    @Shadow
    private int duration;

    public void setMultiplier(double x){
        this.duration*=x;
    }
}