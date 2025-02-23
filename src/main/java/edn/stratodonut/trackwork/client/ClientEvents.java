package edn.stratodonut.trackwork.client;

import static com.jozufozu.flywheel.backend.Backend.isGameActive;

import edn.stratodonut.trackwork.sounds.TrackSoundScapes;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.Level;

public class ClientEvents {
    public static final ClientResourceReloadListener RESOURCE_RELOAD_LISTENER = new ClientResourceReloadListener();

    public static void register() {
        WorldRenderEvents.END.register(ClientEvents::onTick);
        ModBusEvents.registerClientReloadListeners();
    }

    public static void onTick(final WorldRenderContext renderContext) {
        if (!isGameActive())
            return;

        Level world = Minecraft.getInstance().level;

        TrackSoundScapes.tick();
    }

    public static class ModBusEvents {
        public static void registerClientReloadListeners() {
            ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(RESOURCE_RELOAD_LISTENER);
        }
    }
}
