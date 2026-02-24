package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class LivingSwingEvent extends Event {
    private final LivingEntity entity;
    private boolean sendToSwingingEntity;

    public LivingSwingEvent(LivingEntity entity, boolean sendToSwingingEntity) {
        this.entity = entity;
        this.sendToSwingingEntity = sendToSwingingEntity;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }

    public boolean isSendToSwingingEntity() {
        return sendToSwingingEntity;
    }

    public void setSendToSwingingEntity(boolean sendToSwingingEntity) {
        this.sendToSwingingEntity = sendToSwingingEntity;
    }
}