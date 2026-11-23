package com.mythicrealms;

import com.mythicrealms.entity.UnicornEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MythicRealms implements ModInitializer {
    public static final String MOD_ID = "mythicrealms";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Item FRAGMENTO_ESTELAR = item("fragmento_estelar");
    public static final Item ESCAMA_PETRIFICADA = item("escama_petrificada");
    public static final Item PLUMA_CELESTIAL = item("pluma_celestial");
    public static final Item NUCLEO_MINERAL = item("nucleo_mineral");
    public static final Item LLAVE_FUNGICA = item("llave_fungica");
    public static final Item PELO_UNICORNIO = item("pelo_unicornio");

    public static final EntityType<UnicornEntity> UNICORN = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier.of(MOD_ID, "unicornio"),
        EntityType.Builder.create(UnicornEntity::new, SpawnGroup.CREATURE)
            .dimensions(1.39648f, 1.6f)
            .maxTrackingRange(10)
            .build("unicornio")
    );

    public static final Item UNICORN_EGG = Registry.register(
        Registries.ITEM,
        Identifier.of(MOD_ID, "huevo_unicornio"),
        new SpawnEggItem(UNICORN, 0xF9F6F9, 0xDBA5F5, new Item.Settings())
    );

    private static Item item(String name) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new Item(new Item.Settings()));
    }

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(UNICORN, HorseEntity.createBaseHorseAttributes());
        SpawnRestriction.register(UNICORN, SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, UnicornEntity::canSpawnNaturally);
        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.MEADOW),
            SpawnGroup.CREATURE, UNICORN, 3, 1, 2
        );
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(FRAGMENTO_ESTELAR);
            entries.add(ESCAMA_PETRIFICADA);
            entries.add(PLUMA_CELESTIAL);
            entries.add(NUCLEO_MINERAL);
            entries.add(LLAVE_FUNGICA);
            entries.add(PELO_UNICORNIO);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> entries.add(UNICORN_EGG));
        LOGGER.info("Mythic Realms Alpha 0.2.4: unicornio y ocho pelajes registrados");
    }
}
