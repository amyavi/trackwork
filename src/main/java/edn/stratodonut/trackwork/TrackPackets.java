package edn.stratodonut.trackwork;

import com.simibubi.create.foundation.networking.SimplePacketBase;
import edn.stratodonut.trackwork.tracks.network.SimpleWheelPacket;
import edn.stratodonut.trackwork.tracks.network.SuspensionWheelPacket;
import edn.stratodonut.trackwork.tracks.network.ThrowTrackPacket;
import io.github.fabricators_of_create.porting_lib.util.NetworkDirection;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

import static io.github.fabricators_of_create.porting_lib.util.NetworkDirection.PLAY_TO_CLIENT;

public enum TrackPackets {
    SUSPENSION_WHEEL(SuspensionWheelPacket.class, SuspensionWheelPacket::new, PLAY_TO_CLIENT),
    SIMPLE_WHEEL(SimpleWheelPacket.class, SimpleWheelPacket::new, PLAY_TO_CLIENT),
    THROW_TRACK(ThrowTrackPacket.class, ThrowTrackPacket::new, PLAY_TO_CLIENT);

    // DO NOT TOUCH ANYTHING BELOW THIS LINE, THANKS CREATE

    public static final ResourceLocation CHANNEL_NAME = new ResourceLocation(TrackworkMod.MOD_ID, "main");
    public static final int NETWORK_VERSION = 3;
    public static final String NETWORK_VERSION_STR = String.valueOf(NETWORK_VERSION);
    private static SimpleChannel channel;

    private final PacketType<?> packetType;

    <T extends SimplePacketBase> TrackPackets(Class<T> type, Function<FriendlyByteBuf, T> factory,
                                            NetworkDirection direction) {
        packetType = new TrackPackets.PacketType<>(type, factory, direction);
    }

    public static void registerPackets() {
        channel = new SimpleChannel(CHANNEL_NAME);
        for (TrackPackets packet : values())
            packet.packetType.register();
    }

    public static SimpleChannel getChannel() {
        return channel;
    }

    private static class PacketType<T extends SimplePacketBase> {
        private static int index = 0;

        private Function<FriendlyByteBuf, T> decoder;
        private Class<T> type;
        private NetworkDirection direction;

        private PacketType(Class<T> type, Function<FriendlyByteBuf, T> factory, NetworkDirection direction) {
            decoder = factory;
            this.type = type;
            this.direction = direction;
        }

        private void register() {
            switch (direction) {
                case PLAY_TO_CLIENT -> getChannel().registerS2CPacket(type, index++, decoder);
                case PLAY_TO_SERVER -> getChannel().registerC2SPacket(type, index++, decoder);
            }
        }
    }
}
