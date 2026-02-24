package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.entity.Entity;

public class EntitySetPosEvent extends Event {
    private final Entity entity;
    private double x, y, z;
    private boolean isModify;

    public EntitySetPosEvent(Entity entity, double x, double y, double z) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean isCancellable() {
        return true;
    }

    public Entity getEntity() {
        return entity;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setPos(double x, double y, double z) {
        if (x != 0D) {
            this.x = x;
            this.isModify = true;
        }
        if (y != 0D) {
            this.y = y;
            this.isModify = true;
        }
        if (z != 0D) {
            this.z = z;
            this.isModify = true;
        }
    }

    public boolean isModify() {
        return isModify;
    }
}
