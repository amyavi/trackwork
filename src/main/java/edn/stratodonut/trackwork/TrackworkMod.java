package edn.stratodonut.trackwork;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class TrackworkMod implements ModInitializer, ClientModInitializer
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final String MOD_ID = "trackwork";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    static {
        REGISTRATE.setTooltipModifierFactory(item ->
                new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE));
    }

    @Override
    public void onInitialize() {
        TrackworkConfigs.register();
        TrackCreativeTabs.register();
        TrackworkItems.register();
        TrackBlocks.register();
        TrackBlockEntityTypes.register();
        TrackEntityTypes.register();
        TrackPackets.registerPackets();
        TrackSounds.register();

        REGISTRATE.register();

        TrackPackets.getChannel().initServerListener();
    }

    @Override
    public void onInitializeClient() {
        TrackPonders.register();
        TrackworkPartialModels.init();
        TrackworkSpriteShifts.init();

        TrackPackets.getChannel().initClientListener();
    }

    public static void warn(String format, Object arg) {
        LOGGER.warn(format, arg);
    }

    public static void warn(String format, Object... args) {
        LOGGER.warn(format, args);
    }

    public static ResourceLocation getResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
