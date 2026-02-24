package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class EntityJoinLevelEvent extends Event {
    private final ServerLevel serverLevel;
    private final Entity entity;

    public EntityJoinLevelEvent(ServerLevel serverLevel, Entity entity) {
        this.serverLevel = serverLevel;
        this.entity = entity;
    }

    @Override
    public boolean isCancellable() { return true; }

    public Entity getEntity() { return entity; }

    public ServerLevel getLevel() { return serverLevel; }
}
