package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

public class LivingRemoveEffectEvent extends Event {
    private final LivingEntity entity;
    private final Holder<MobEffect> effectHolder;
    private final boolean isNoUpdate;

    public LivingRemoveEffectEvent(LivingEntity entity, Holder<MobEffect> effectHolder, boolean isNoUpdate) {
        this.entity = entity;
        this.effectHolder = effectHolder;
        this.isNoUpdate = isNoUpdate;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }

    public Holder<MobEffect> getEffectHolder() {
        return effectHolder;
    }

    public boolean isNoUpdate() {
        return isNoUpdate;
    }
}