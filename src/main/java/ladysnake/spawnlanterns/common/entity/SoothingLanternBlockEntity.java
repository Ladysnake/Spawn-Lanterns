package ladysnake.spawnlanterns.common.entity;

import ladysnake.spawnlanterns.common.SpawnLanterns;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class SoothingLanternBlockEntity extends BlockEntity {

    public SoothingLanternBlockEntity(BlockPos pos, BlockState state) {
        super(SpawnLanterns.SOOTHING_LANTERN_BE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, SoothingLanternBlockEntity blockEntity) {
        if (world != null && !world.isClient) {
            int j = 20;
            int k = pos.getX();
            int l = pos.getY();
            int m = pos.getZ();
            Box box = (new Box(k, l, m, (k + 1), (l + 1), (m + 1))).expand(j).stretch(0.0D, world.getHeight(), 0.0D);
            List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
            list.forEach(playerEntity -> {
                if (pos.isWithinDistance(playerEntity.getBlockPos(), j)) {
                    playerEntity.addStatusEffect(new StatusEffectInstance(SpawnLanterns.SOOTHING, 110, 0, true, false, false));
                }
            });
        }
    }
}
