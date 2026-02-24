package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EntityKillEvent extends Event {
    private final Entity entity;

    public EntityKillEvent(Entity entity) {
        this.entity = entity;
    }

    @Override
    public boolean isCancellable() { return true; }

    public Entity getEntity() { return entity; }
}