package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class LivingFallEvent extends Event {
    private final LivingEntity entity;
    private double distance;
    private float damageModifier;
    private DamageSource damageSource;

    public LivingFallEvent(LivingEntity entity, double value, float damageModifier, DamageSource damageSource) {
        this.entity = entity;
        this.distance = value;
        this.damageModifier = damageModifier;
        this.damageSource = damageSource;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }
    public double getDistance() { return distance; }
    public void setDistance(double value) { this.distance = value; }

    public float getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(float damageModifier) {
        this.damageModifier = damageModifier;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }

    public void setDamageSource(DamageSource damageSource) {
        this.damageSource = damageSource;
    }
}