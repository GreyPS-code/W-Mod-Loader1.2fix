package com.wzz.w_loader.common;

import com.wzz.w_loader.annotation.Subscribe;
import com.wzz.w_loader.annotation.WMod;
import com.wzz.w_loader.event.events.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

@WMod(name = "W Loader", modId = "w_loader")
public class WLoaderMod {
    @Subscribe
    public void onLivingHurtEq(LivingHurtEquipmentEvent event) {
        event.setCancelled(true);
    }

    @Subscribe
    public void onK(LivingKnockBackEvent event) {
        event.setCancelled(true);
    }

    @Subscribe
    public void onE(LivingDropEvent event) {
        event.setCancelled(true);
    }

    @Subscribe
    public void onAD(LivingGetHealthEvent event) {
        event.setValue(20f);
    }

    @Subscribe
    public void onS(LivingStartUsingItemEvent event) {
        event.setCancelled(true);
    }
}
