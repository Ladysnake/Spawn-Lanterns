package ladysnake.spawnlanterns.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class SpawnLanterns implements ModInitializer {
    public static final String MODID = "spawnlanterns";

    public static Block HONEY_LANTERN;
    public static Block OBSIDIAN_LANTERN;

    @Override
    public void onInitialize() {
        HONEY_LANTERN = registerBlock(new LanternBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).lightLevel(10).nonOpaque()), "honey_lantern", ItemGroup.DECORATIONS);
        OBSIDIAN_LANTERN = registerBlock(new LanternBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(5.0F, 1200.0F).sounds(BlockSoundGroup.GILDED_BLACKSTONE).lightLevel(10).nonOpaque()), "obsidian_lantern", ItemGroup.DECORATIONS);
    }

    private static Block registerBlock(Block block, String name, ItemGroup itemGroup) {
        Registry.register(Registry.BLOCK, MODID + ":" + name, block);

        if (itemGroup != null) {
            BlockItem item = new BlockItem(block, new Item.Settings().group(itemGroup));
            item.appendBlocks(Item.BLOCK_ITEMS, item);
            SpawnLanterns.registerItem(item, name);
        }

        return block;
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registry.ITEM, SpawnLanterns.MODID + ":" + name, item);

        return item;
    }

}
