package edn.stratodonut.trackwork;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Components;
import com.tterrag.registrate.fabric.RegistryObject;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static edn.stratodonut.trackwork.TrackworkMod.REGISTRATE;

public class TrackCreativeTabs {
    public static final CreativeModeTab BASE_CREATIVE_TAB = register("base",
            () -> FabricItemGroup.builder()
                    .title(Components.translatable("itemGroup.trackwork"))
                    .icon(AllBlocks.BELT::asStack)
                    .displayItems((displayParams, output) -> {
                        for (RegistryEntry<Item> entry : REGISTRATE.getAll(Registries.ITEM)) {
                            if (CreateRegistrate.isInCreativeTab(entry, AllCreativeModeTabs.BASE_CREATIVE_TAB.key()))
                                output.accept(entry.get());
                        }
                    })
                    .build());

    private static CreativeModeTab register(String name, Supplier<CreativeModeTab> supplier) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TrackworkMod.getResource(name), supplier.get());
    }

    public static void register() {
    }
}
