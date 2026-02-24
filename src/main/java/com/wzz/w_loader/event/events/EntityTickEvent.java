package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.entity.Entity;

public class EntityTickEvent extends Event {
    private final Entity entity;

    public EntityTickEvent(Entity entity) {
        this.entity = entity;
    }

    @Override
    public boolean isCancellable() { return true; }

    public Entity getEntity() { return entity; }
}