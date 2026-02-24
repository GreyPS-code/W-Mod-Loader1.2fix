package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class LivingDropEvent extends Event {
    private final LivingEntity entity;
    private final ServerLevel serverLevel;
    private final DamageSource damageSource;

    public LivingDropEvent(LivingEntity entity, ServerLevel serverLevel, DamageSource damageSource) {
        this.entity = entity;
        this.serverLevel = serverLevel;
        this.damageSource = damageSource;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }

    public ServerLevel getServerLevel() {
        return serverLevel;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }
}