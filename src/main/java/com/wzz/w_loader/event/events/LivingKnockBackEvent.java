package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class LivingKnockBackEvent extends Event {
    private final LivingEntity entity;
    private double power;
    private double xd;
    private double zd;

    public LivingKnockBackEvent(LivingEntity entity, double power, double xd, double zd) {
        this.entity = entity;
        this.power = power;
        this.xd = xd;
        this.zd = zd;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }

    public void setPower(double power) {
        this.power = power;
    }

    public void setXd(double xd) {
        this.xd = xd;
    }

    public void setZd(double zd) {
        this.zd = zd;
    }

    public double getPower() {
        return power;
    }

    public double getXd() {
        return xd;
    }

    public double getZd() {
        return zd;
    }
}