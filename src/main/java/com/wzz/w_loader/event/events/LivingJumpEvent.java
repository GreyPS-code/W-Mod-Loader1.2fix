package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.Fluid;

public class LivingJumpEvent extends Event {
    private final LivingEntity entity;
    private final float value;
    private final JumpMode jumpMode;
    private final TagKey<Fluid> tagKey;
    private final double oldY;

    public LivingJumpEvent(LivingEntity entity, float value, JumpMode jumpMode, TagKey<Fluid> tagKey, double oldY) {
        this.entity = entity;
        this.value = value;
        this.jumpMode = jumpMode;
        this.tagKey = tagKey;
        this.oldY = oldY;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }

    public float getPower() { return value; }

    public JumpMode getJumpMode() {
        return jumpMode;
    }

    public TagKey<Fluid> getTagKey() {
        return tagKey;
    }

    public double getOldY() {
        return oldY;
    }

    public enum JumpMode {
        JUMP_IN_LIQUID, JUMP_FORM_GROUND, JUMP_OUT_OF_LIQUID
    }
}