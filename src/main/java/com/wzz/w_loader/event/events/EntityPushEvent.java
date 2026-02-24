package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.entity.Entity;

public class EntityPushEvent extends Event {
    private final Entity entity;
    private final Entity target;

    public EntityPushEvent(Entity entity, Entity target) {
        this.entity = entity;
        this.target = target;
    }

    @Override
    public boolean isCancellable() { return true; }

    public Entity getEntity() { return entity; }

    public Entity getTarget() {
        return target;
    }
}