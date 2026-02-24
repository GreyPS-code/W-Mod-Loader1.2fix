package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;
import net.minecraft.client.gui.Gui;

class GuiEvent extends Event {
    private final Gui gui;

    protected GuiEvent(Gui gui) {
        this.gui = gui;
    }

    public Gui getGui() {
        return gui;
    }
}
