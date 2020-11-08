package ladysnake.spawnlanterns.common.entity;

import ladysnake.spawnlanterns.common.SpawnLanterns;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Box;

import java.util.Iterator;
import java.util.List;

public class SoothingLanternBlockEntity extends BlockEntity implements Tickable {
    public SoothingLanternBlockEntity() {
        super(SpawnLanterns.SOOTHING_LANTERN_BE);
    }

    @Override
    public void tick() {
        if (this.world != null && !this.world.isClient) {
            int j = 20;
            int k = this.pos.getX();
            int l = this.pos.getY();
            int m = this.pos.getZ();
            Box box = (new Box((double) k, (double) l, (double) m, (double) (k + 1), (double) (l + 1), (double) (m + 1))).expand((double) j).stretch(0.0D, (double) this.world.getHeight(), 0.0D);
            List<PlayerEntity> list = this.world.getNonSpectatingEntities(PlayerEntity.class, box);
            list.forEach(playerEntity -> {
                if (this.pos.isWithinDistance(playerEntity.getBlockPos(), (double) j)) {
                    playerEntity.addStatusEffect(new StatusEffectInstance(SpawnLanterns.SOOTHING, 110, 0, true, false, false));
                }
            });
        }
    }
}
