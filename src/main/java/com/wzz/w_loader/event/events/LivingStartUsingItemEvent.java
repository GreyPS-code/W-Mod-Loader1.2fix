package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class LivingStartUsingItemEvent extends Event {
    private final LivingEntity entity;
    private final InteractionHand hand;
    private final ItemStack item;

    public LivingStartUsingItemEvent(LivingEntity entity, InteractionHand hand, ItemStack item) {
        this.entity = entity;
        this.hand = hand;
        this.item = item;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }

    public InteractionHand getHand() {
        return hand;
    }

    public ItemStack getItemStack() {
        return item;
    }
}