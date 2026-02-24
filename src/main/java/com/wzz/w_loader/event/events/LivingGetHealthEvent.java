package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.entity.LivingEntity;

public class LivingGetHealthEvent extends Event {
    private final LivingEntity entity;
    private float value = -1f;

    public LivingGetHealthEvent(LivingEntity entity) {
        this.entity = entity;
    }

    public LivingEntity getEntity() { return entity; }

    public float getValue() { return value; }

    public void setValue(float value) {
        this.value = value;
    }
}