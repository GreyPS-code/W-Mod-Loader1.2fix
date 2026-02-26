package com.wzz.w_loader.common;

import com.wzz.w_loader.ModLoader;
import com.wzz.w_loader.annotation.Subscribe;
import com.wzz.w_loader.annotation.WMod;
import com.wzz.w_loader.event.events.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.level.entity.EntityLookup;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

@WMod(name = "W Loader", modId = "w_loader")
public class WLoaderMod {
    // 每一秒检测并锁定标题，防止被原版 createTitle() 改回去
    @Subscribe
    public void onClientTick(ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        // 确保窗口已经创建
        if (mc.getWindow() != null) {
            String customTitle = "Minecraft 26.1 - [W-Loader]";
            mc.getWindow().setTitle(customTitle);
        }
    }
    @Subscribe
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("loader")
                .then(Commands.literal("entity")
                        .executes(context -> {
                            ServerLevel world = context.getSource().getLevel();
                            try {
                                context.getSource().sendSystemMessage(Component.literal("§6[W-Loader]§f 当前实体列表:"));
                                context.getSource().sendSystemMessage(
                                        Component.literal("")
                                );
                                context.getSource().sendSystemMessage(
                                        Component.literal("")
                                );
                                Field manager = ServerLevel.class.getDeclaredField("entityManager");
                                manager.setAccessible(true);
                                PersistentEntitySectionManager entityManager = (PersistentEntitySectionManager) manager.get(world);
                                Field lookUp = PersistentEntitySectionManager.class.getDeclaredField("visibleEntityStorage");
                                lookUp.setAccessible(true);
                                EntityLookup entityLookup = (EntityLookup) lookUp.get(entityManager);
                                Field byId = EntityLookup.class.getDeclaredField("byId");
                                byId.setAccessible(true);
                                Int2ObjectMap<EntityAccess> byIdMap = (Int2ObjectMap<EntityAccess>) byId.get(entityLookup);
                                Field byUuid = EntityLookup.class.getDeclaredField("byUuid");
                                byUuid.setAccessible(true);
                                java.util.Map<java.util.UUID, EntityAccess> byUuidMap = (java.util.Map<java.util.UUID, EntityAccess>) byUuid.get(entityLookup);
                                AtomicInteger count = new AtomicInteger();
                                byIdMap.forEach((entityId,entityAccess) ->{
                                    count.getAndIncrement();
                                });
                                        context.getSource().sendSystemMessage(
                                        Component.literal("byId中有"+count.get()+"个实体")
                                );
                                byIdMap.forEach((entityId,entityAccess) -> {
                                    Entity entity = (Entity)entityAccess;
                                    String entityOutput = "type:"+String.valueOf(entity.getType())+" id:"+ entityId +" uuid:"+ entity.getUUID();
                                    System.out.println(entityOutput);
                                    context.getSource().sendSystemMessage(
                                            Component.literal(entityOutput)
                                    );
                                });
                                context.getSource().sendSystemMessage(
                                        Component.literal("")
                                );
                                count.set(0);
                                byIdMap.forEach((entityId,entityAccess) ->{
                                    count.getAndIncrement();
                                });
                                context.getSource().sendSystemMessage(
                                        Component.literal("byUuid中有"+count.get()+"个实体")
                                );
                                byUuidMap.forEach((entityUuid,entityAccess) -> {
                                    Entity entity = (Entity)entityAccess;
                                    String entityOutput = "type:"+String.valueOf(entity.getType())+" id:"+ entity.getId() +" uuid:"+ entityUuid;
                                    System.out.println(entityOutput);
                                    context.getSource().sendSystemMessage(
                                            Component.literal(entityOutput)
                                    );
                                });
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            return 1;
                        }))
                .then(Commands.literal("mod")
                        .executes(context -> {
                            var loadedMods = ModLoader.INSTANCE.getLoadedMods();
                            context.getSource().sendSystemMessage(Component.literal("§6[W-Loader]§f 已加载模组列表:"));
                            if (loadedMods.isEmpty()) {
                                context.getSource().sendSystemMessage(Component.literal("§7  (无外部模组)"));
                            } else {
                                loadedMods.forEach(pm -> {
                                    String msg = String.format("§a[●] §f%s §7(%s) §8- %s",
                                            pm.metadata().name, pm.metadata().version, pm.metadata().modId);
                                    context.getSource().sendSystemMessage(Component.literal(msg));
                                });
                            }
                            return 1;
                        })
        ));
    }
}
