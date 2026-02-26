package com.wzz.w_loader.hook;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class GameLifeCycleHook {
    static Minecraft minecraft = Minecraft.getInstance();
    static MinecraftServer server = null;
    static {
        while (minecraft == null){
            minecraft = Minecraft.getInstance();
        }
    }
    public static MinecraftServer getServer(){
        return server;
    }
    public static Minecraft getMinecraft(){
        return minecraft;
    }
    public static boolean isOnClientMainThread() {
        Minecraft mc = getMinecraft();
        return mc != null && mc.isSameThread();
    }
    public static boolean isOnServerMainThread() {
        MinecraftServer server = getServer();
        return server != null && server.isSameThread();
    }
}
