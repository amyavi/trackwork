package edn.stratodonut.trackwork;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class TrackSounds {
    public static final SoundEvent SUSPENSION_CREAK = registerSoundEvents("suspension_creak");
//    public static final SoundEvent TRACK_CREAK = registerSoundEvents("suspension_creak");
    public static final SoundEvent POWER_TOOL = registerSoundEvents("power_wrench");
    public static final SoundEvent SPRING_TOOL = registerSoundEvents("spring_tool");

    private static SoundEvent registerSoundEvents(String name) {
        final ResourceLocation id =TrackworkMod.getResource(name);

        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void register() {}
}
