package com.starfish_studios.naturalist.core.registry.fabric;

import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.core.registry.NaturalistRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class NaturalistCreativeModeFabric {

    @SuppressWarnings("unused")
    public static final CreativeModeTab ITEM_GROUP = register("item_group", FabricItemGroup.builder().icon(NaturalistRegistry.BUG_NET.get()::getDefaultInstance).title(Component.translatable("itemGroup.naturalist.tab")).displayItems((featureFlagSet, output) -> {

                output.accept(NaturalistRegistry.BUG_NET.get());

            }).build()
    );

    public static void addToVanillaCreativeTabs() {
//        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COLORED_BLOCKS).register(entries ->
//                entries.addAfter(Items.PINK_BANNER,
//
//                        CAGE_PANEL, WHITE_CAGE_PANEL, LIGHT_GRAY_CAGE_PANEL, GRAY_CAGE_PANEL, BLACK_CAGE_PANEL, BROWN_CAGE_PANEL,
//                        RED_CAGE_PANEL, ORANGE_CAGE_PANEL, YELLOW_CAGE_PANEL, LIME_CAGE_PANEL, GREEN_CAGE_PANEL, CYAN_CAGE_PANEL,
//                        LIGHT_BLUE_CAGE_PANEL, BLUE_CAGE_PANEL, PURPLE_CAGE_PANEL, MAGENTA_CAGE_PANEL, PINK_CAGE_PANEL,
//
//                        WHITE_HAMSTER_BOWL, LIGHT_GRAY_HAMSTER_BOWL, GRAY_HAMSTER_BOWL, BLACK_HAMSTER_BOWL, BROWN_HAMSTER_BOWL,
//                        RED_HAMSTER_BOWL, ORANGE_HAMSTER_BOWL, YELLOW_HAMSTER_BOWL, LIME_HAMSTER_BOWL, GREEN_HAMSTER_BOWL, CYAN_HAMSTER_BOWL,
//                        LIGHT_BLUE_HAMSTER_BOWL, BLUE_HAMSTER_BOWL, PURPLE_HAMSTER_BOWL, MAGENTA_HAMSTER_BOWL, PINK_HAMSTER_BOWL,
//
//                        WHITE_HAMSTER_BOTTLE, LIGHT_GRAY_HAMSTER_BOTTLE, GRAY_HAMSTER_BOTTLE, BLACK_HAMSTER_BOTTLE, BROWN_HAMSTER_BOTTLE,
//                        RED_HAMSTER_BOTTLE, ORANGE_HAMSTER_BOTTLE, YELLOW_HAMSTER_BOTTLE, LIME_HAMSTER_BOTTLE, GREEN_HAMSTER_BOTTLE, CYAN_HAMSTER_BOTTLE,
//                        LIGHT_BLUE_HAMSTER_BOTTLE, BLUE_HAMSTER_BOTTLE, PURPLE_HAMSTER_BOTTLE, MAGENTA_HAMSTER_BOTTLE, PINK_HAMSTER_BOTTLE
//                )
//        );
    }

    @SuppressWarnings("all")
    private static CreativeModeTab register(String id, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(Naturalist.MOD_ID, id), tab);
    }

    public static void init() {
    }
}