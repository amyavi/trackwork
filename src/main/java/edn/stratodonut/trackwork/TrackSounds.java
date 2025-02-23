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

    public static final SoundEvent TRACK_AMBIENT_SPROCKET = registerSoundEvents("track_ambient_sprocket");

    public static final SoundEvent TRACK_AMBIENT_GROUND_1 = registerSoundEvents("track_ambient_ground_1");
    public static final SoundEvent TRACK_AMBIENT_GROUND_2 = registerSoundEvents("track_ambient_ground_2");
    public static final SoundEvent TRACK_GROUND_SLIP = registerSoundEvents("track_ground_slip");

    public static final SoundEvent WHEEL_ROCKTOSS = registerSoundEvents("wheel_rocktoss");

    public static final SoundEvent WHEEL_AMBIENT_GROUND_1 = registerSoundEvents("wheel_ambient_ground_1");
    public static final SoundEvent WHEEL_AMBIENT_GROUND_2 = registerSoundEvents("wheel_ambient_ground_2");
    public static final SoundEvent WHEEL_GROUND_SLIP = registerSoundEvents("wheel_ground_slip");

    private static SoundEvent registerSoundEvents(String name) {
        final ResourceLocation id = TrackworkMod.getResource(name);

        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void register() {}
}
