package com.example.pvpvisuals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.item.BowItem;

public class PvpVisualsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.interactionManager == null) return;

            if (client.player.isUsingItem() && client.player.getActiveItem().getItem() instanceof BowItem) {
                int useTicks = client.player.getActiveItem().getMaxUseTime(client.player) - client.player.getItemUseTimeLeft();
                if (useTicks >= 1) {
                    client.interactionManager.stopUsingItem(client.player);
                }
            }
        });
    }
}
