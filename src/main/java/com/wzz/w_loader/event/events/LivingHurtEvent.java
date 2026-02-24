package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class LivingHurtEvent extends Event {
    private final LivingEntity entity;
    private final DamageSource damageSource;
    private float damage;

    public LivingHurtEvent(LivingEntity entity, DamageSource damageSource, float damage) {
        this.entity = entity;
        this.damageSource = damageSource;
        this.damage = damage;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }
    public DamageSource getDamageSource() { return damageSource; }
    public float getDamage() { return damage; }
    public void setDamage(float damage) { this.damage = damage; }
}