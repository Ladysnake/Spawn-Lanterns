package ladysnake.spawnlanterns.common.block;

import ladysnake.spawnlanterns.common.entity.CryingLanternBlockEntity;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CryingLanternBlock extends LanternBlock implements BlockEntityProvider {
    public CryingLanternBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new CryingLanternBlockEntity();
    }
}
