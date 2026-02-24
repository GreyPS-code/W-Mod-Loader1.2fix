package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

public class ItemEntityTouchEvent extends Event {
    private final ItemEntity itemEntity;
    private final Player player;

    public ItemEntityTouchEvent(ItemEntity itemEntity, Player player) {
        this.itemEntity = itemEntity;
        this.player = player;
    }

    @Override
    public boolean isCancellable() {
        return true;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }
}
