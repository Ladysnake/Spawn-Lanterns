package ladysnake.spawnlanterns.common.entity;

import ladysnake.spawnlanterns.common.SpawnLanterns;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Box;

import java.util.Iterator;
import java.util.List;

public class CryingLanternBlockEntity extends BlockEntity implements Tickable {
    private final TargetPredicate PLAYERS_IN_RANGE_PREDICATE = new TargetPredicate().setBaseMaxDistance(64.0D);

    public CryingLanternBlockEntity() {
        super(SpawnLanterns.CRYING_LANTERN_BE);
    }

    @Override
    public void tick() {
        if (this.world != null && !this.world.isClient) {
            int j = 16;
            int k = this.pos.getX();
            int l = this.pos.getY();
            int m = this.pos.getZ();
            Box box = (new Box((double) k, (double) l, (double) m, (double) (k + 1), (double) (l + 1), (double) (m + 1))).expand((double) j).stretch(0.0D, (double) this.world.getHeight(), 0.0D);
            List<PlayerEntity> list = this.world.getNonSpectatingEntities(PlayerEntity.class, box);
            list.forEach(playerEntity -> {
                if (this.pos.isWithinDistance(playerEntity.getBlockPos(), (double) j)) {
                    playerEntity.addStatusEffect(new StatusEffectInstance(SpawnLanterns.PROVOCATION, 110, 0, true, false, false));
                }
            });
        }
    }
}
