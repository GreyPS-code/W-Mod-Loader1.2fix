package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class EntitySetLevelEvent extends Event {
    private final Entity entity;
    private Level newLevel;

    public EntitySetLevelEvent(Entity entity, Level level) {
        this.entity = entity;
        this.newLevel = level;
    }

    public Entity getEntity() {
        return entity;
    }

    public Level getNewLevel() {
        return newLevel;
    }

    public void setNewLevel(Level newLevel) {
        this.newLevel = newLevel;
    }
}
