package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class LivingHurtEquipmentEvent extends Event {
    private final LivingEntity entity;
    private float value;
    private DamageSource damageSource;
    private final EquipmentSlot[] equipmentSlots;

    public LivingHurtEquipmentEvent(LivingEntity entity, float value, DamageSource damageSource, EquipmentSlot... equipmentSlots) {
        this.entity = entity;
        this.value = value;
        this.damageSource = damageSource;
        this.equipmentSlots = equipmentSlots;
    }

    @Override
    public boolean isCancellable() { return true; }

    public LivingEntity getEntity() { return entity; }
    public float getValue() { return value; }
    public void setValue(float value) { this.value = value; }

    public EquipmentSlot[] getEquipmentSlots() {
        return equipmentSlots;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }

    public void setDamageSource(DamageSource damageSource) {
        this.damageSource = damageSource;
    }
}