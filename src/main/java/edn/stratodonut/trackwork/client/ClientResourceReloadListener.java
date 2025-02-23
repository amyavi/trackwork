package edn.stratodonut.trackwork.client;

import edn.stratodonut.trackwork.TrackworkMod;
import edn.stratodonut.trackwork.sounds.TrackSoundScapes;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

public class ClientResourceReloadListener implements ResourceManagerReloadListener, IdentifiableResourceReloadListener {
    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        TrackSoundScapes.invalidateAll();
    }

    @Override
    public ResourceLocation getFabricId() {
        return TrackworkMod.getResource("client_reload_listener");
    }
}
