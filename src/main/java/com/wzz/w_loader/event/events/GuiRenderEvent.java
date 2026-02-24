package com.wzz.w_loader.event.events;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;

public class GuiRenderEvent extends GuiEvent{
    private final GuiGraphics guiGraphics;
    private final DeltaTracker deltaTracker;
    public GuiRenderEvent(Gui gui, GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        super(gui);
        this.guiGraphics = guiGraphics;
        this.deltaTracker = deltaTracker;
    }

    public GuiGraphics getGuiGraphics() {
        return guiGraphics;
    }

    public DeltaTracker getDeltaTracker() {
        return deltaTracker;
    }

    public static class Pre extends GuiRenderEvent {
        public Pre(Gui gui, GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
            super(gui, guiGraphics, deltaTracker);
        }

        @Override
        public boolean isCancellable() {
            return true;
        }
    }

    public static class Post extends GuiRenderEvent {
        public Post(Gui gui, GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
            super(gui, guiGraphics, deltaTracker);
        }
    }
}
