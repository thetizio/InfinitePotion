package com.tizio.infinitepotion.mixin;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PotionItem.class)
public class KeepPotion extends Item {

    public KeepPotion(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(final ItemStack itemStack, final Level level, final LivingEntity entity){
        Consumable consumable = (Consumable)itemStack.get(DataComponents.CONSUMABLE);
        itemStack.setCount(itemStack.getCount()+1);
        return consumable != null ? consumable.onConsume(level, entity, itemStack) : itemStack;
    }

}