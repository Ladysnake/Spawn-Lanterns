package ladysnake.spawnlanterns.common;

import ladysnake.spawnlanterns.effect.SpawnStatusEffect;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.registry.Registry;

public class SpawnLanterns implements ModInitializer {
    public static final String MODID = "spawnlanterns";

    public static Block SOOTHING_LANTERN;
    public static Block CRYING_LANTERN;

    public static StatusEffect SOOTHING;
    public static StatusEffect PROVOCATION;

    @Override
    public void onInitialize() {
        // register lanterns
        SOOTHING_LANTERN = registerBlock(new LanternBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).lightLevel(10).nonOpaque()), "soothing_lantern", ItemGroup.DECORATIONS);
        CRYING_LANTERN = registerBlock(new LanternBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).lightLevel(10).nonOpaque()), "crying_lantern", ItemGroup.DECORATIONS);

        // register status effects
        SOOTHING = registerStatusEffect(new SpawnStatusEffect(StatusEffectType.BENEFICIAL, 15747468), "soothing");
        PROVOCATION = registerStatusEffect(new SpawnStatusEffect(StatusEffectType.BENEFICIAL, 5851632), "provocation");

        // register usables on soul lanterns and their effects
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            if (world.getBlockState(blockHitResult.getBlockPos()).getBlock() == Blocks.SOUL_LANTERN) {
                if (playerEntity.getStackInHand(hand).getItem() == Items.HONEY_BOTTLE) {
                    // consume the item
                    if (!playerEntity.abilities.creativeMode) {
                        playerEntity.getStackInHand(hand).decrement(1);
                        ItemStack glassBottleStack = new ItemStack(Items.GLASS_BOTTLE);
                        if (!playerEntity.inventory.insertStack(glassBottleStack)) {
                            playerEntity.dropItem(glassBottleStack, false);
                        }
                    }

                    // change the lantern
                    world.setBlockState(blockHitResult.getBlockPos(),
                            SOOTHING_LANTERN.getDefaultState()
                                    .with(LanternBlock.HANGING, world.getBlockState(blockHitResult.getBlockPos()).get(LanternBlock.HANGING))
                                    .with(LanternBlock.field_26441, world.getBlockState(blockHitResult.getBlockPos()).get(LanternBlock.field_26441)));

                    // play sounds
                    world.playSound(playerEntity, blockHitResult.getBlockPos(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    world.playSound(playerEntity, blockHitResult.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0f, 2.0f);

                    // display particle effects
                    if (world.isClient) {
                        for(int i = 0; i < 20; ++i) {
                            double d = world.random.nextGaussian() * 0.02D;
                            double e = world.random.nextGaussian() * 0.02D;
                            double f = world.random.nextGaussian() * 0.02D;
                            double g = 10.0D;
                            world.addParticle(ParticleTypes.WITCH,
                                    (blockHitResult.getBlockPos().getX() + 0.5f) - d * 10.0D,
                                    (blockHitResult.getBlockPos().getY() + 0.5f) - e * 10.0D,
                                    (blockHitResult.getBlockPos().getZ() + 0.5f) - f * 10.0D, d, e, f);
                        }
                    }

                    // return
                    return ActionResult.SUCCESS;
                } else if (playerEntity.getStackInHand(hand).getItem() == Items.CRYING_OBSIDIAN) {
                    // consume the item
                    if (!playerEntity.abilities.creativeMode) {
                        playerEntity.getStackInHand(hand).decrement(1);
                        ItemStack obsidianStack = new ItemStack(Items.OBSIDIAN);
                        if (!playerEntity.inventory.insertStack(obsidianStack)) {
                            playerEntity.dropItem(obsidianStack, false);
                        }
                    }

                    // change the lantern
                    world.setBlockState(blockHitResult.getBlockPos(),
                            CRYING_LANTERN.getDefaultState()
                                    .with(LanternBlock.HANGING, world.getBlockState(blockHitResult.getBlockPos()).get(LanternBlock.HANGING))
                                    .with(LanternBlock.field_26441, world.getBlockState(blockHitResult.getBlockPos()).get(LanternBlock.field_26441)));

                    // play sounds
                    world.playSound(playerEntity, blockHitResult.getBlockPos(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    world.playSound(playerEntity, blockHitResult.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0f, 2.0f);

                    // display particle effects
                    if (world.isClient) {
                        for(int i = 0; i < 20; ++i) {
                            double d = world.random.nextGaussian() * 0.02D;
                            double e = world.random.nextGaussian() * 0.02D;
                            double f = world.random.nextGaussian() * 0.02D;
                            double g = 10.0D;
                            world.addParticle(ParticleTypes.WITCH,
                                    (blockHitResult.getBlockPos().getX() + 0.5f) - d * 10.0D,
                                    (blockHitResult.getBlockPos().getY() + 0.5f) - e * 10.0D,
                                    (blockHitResult.getBlockPos().getZ() + 0.5f) - f * 10.0D, d, e, f);
                        }
                    }

                    // return
                    return ActionResult.SUCCESS;
                }
                return ActionResult.PASS;
            }
            return ActionResult.PASS;
        });
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

    private static StatusEffect registerStatusEffect(StatusEffect statusEffect, String name) {
        Registry.register(Registry.STATUS_EFFECT, MODID + ":" + name, statusEffect);

        return statusEffect;
    }

}
