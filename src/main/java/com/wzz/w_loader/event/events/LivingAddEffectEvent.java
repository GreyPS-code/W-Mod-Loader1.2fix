package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class LivingAddEffectEvent extends Event {
    private final LivingEntity entity;
    private final MobEffectInstance effectInstance;
    private final Entity source;
    private final boolean isForce;

    public LivingAddEffectEvent(LivingEntity entity, MobEffectInstance effectInstance, Entity source, boolean isForce) {
        this.entity = entity;
        this.effectInstance = effectInstance;
        this.source = source;
        this.isForce = isForce;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }

    public MobEffectInstance getEffectInstance() {
        return effectInstance;
    }

    public Entity getSource() {
        return source;
    }

    public boolean isForce() {
        return isForce;
    }
}