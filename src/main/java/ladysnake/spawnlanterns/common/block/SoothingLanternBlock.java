package ladysnake.spawnlanterns.common.block;

import ladysnake.spawnlanterns.common.SpawnLanterns;
import ladysnake.spawnlanterns.common.entity.SoothingLanternBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SoothingLanternBlock extends LanternBlockEntity {
    public SoothingLanternBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SoothingLanternBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, SpawnLanterns.SOOTHING_LANTERN_BE, SoothingLanternBlockEntity::tick);
    }
}
