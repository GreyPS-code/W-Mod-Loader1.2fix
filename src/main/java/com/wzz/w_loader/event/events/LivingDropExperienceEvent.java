package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class LivingDropExperienceEvent extends Event {
    private final LivingEntity entity;
    private final ServerLevel serverLevel;
    private final Entity killer;

    public LivingDropExperienceEvent(LivingEntity entity, ServerLevel serverLevel, Entity killer) {
        this.entity = entity;
        this.serverLevel = serverLevel;
        this.killer = killer;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }

    public ServerLevel getServerLevel() {
        return serverLevel;
    }

    public Entity getKiller() {
        return killer;
    }
}