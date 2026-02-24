package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class LivingDeathEvent extends Event {
    private final LivingEntity entity;
    private final DamageSource damageSource;

    public LivingDeathEvent(LivingEntity entity, DamageSource damageSource) {
        this.entity = entity;
        this.damageSource = damageSource;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity()       { return entity; }
    public DamageSource getDamageSource() { return damageSource; }
}