package ladysnake.spawnlanterns.common.block;

import ladysnake.spawnlanterns.common.SpawnLanterns;
import ladysnake.spawnlanterns.common.entity.CryingLanternBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CryingLanternBlock extends LanternBlockEntity {
    public CryingLanternBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CryingLanternBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, SpawnLanterns.CRYING_LANTERN_BE, CryingLanternBlockEntity::tick);
    }
}
