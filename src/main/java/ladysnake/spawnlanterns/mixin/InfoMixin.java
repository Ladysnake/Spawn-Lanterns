package ladysnake.spawnlanterns.mixin;


import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import ladysnake.spawnlanterns.common.ExtendedSpawnInfo;
import ladysnake.spawnlanterns.common.SpawnLanterns;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnHelper.Info.class)
public abstract class InfoMixin implements ExtendedSpawnInfo {
    @Shadow @Final
    Object2IntOpenHashMap<SpawnGroup> groupToCount;

    @Shadow @Final private int spawningChunkCount;
    private PlayerEntity closestPlayer;

    @Override
    public void setClosestPlayer(PlayerEntity player) {
        this.closestPlayer = player;
    }

    @Inject(method = "isBelowCap", at = @At("RETURN"), cancellable = true)
    private void isBelowCap(SpawnGroup group, CallbackInfoReturnable<Boolean> ci) {
        if (!ci.getReturnValueZ() && (closestPlayer != null && closestPlayer.hasStatusEffect(SpawnLanterns.PROVOCATION))) {
            int i = group.getCapacity() * this.spawningChunkCount / SpawnHelperAccessor.getChunkArea();
            ci.setReturnValue(this.groupToCount.getInt(group) < (i * 2));
        }
    }
}