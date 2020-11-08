package ladysnake.spawnlanterns.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import ladysnake.spawnlanterns.common.ExtendedSpawnInfo;
import ladysnake.spawnlanterns.common.SpawnLanterns;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnHelper.class)
public abstract class SpawnHelperMixin implements ExtendedSpawnInfo {
    @Shadow @Final
    private static int CHUNK_AREA;

    @Inject(at = @At(value = "RETURN"), method = "canSpawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/SpawnGroup;Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/world/biome/SpawnSettings$SpawnEntry;Lnet/minecraft/util/math/BlockPos$Mutable;D)Z", cancellable = true)
    private static void canSpawn(ServerWorld world, SpawnGroup group, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, SpawnSettings.SpawnEntry spawnEntry, BlockPos.Mutable pos, double squaredDistance, CallbackInfoReturnable<Boolean> ci) {
        PlayerEntity closestPlayer = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), squaredDistance*2, false);
        if (closestPlayer != null && closestPlayer.hasStatusEffect(SpawnLanterns.SOOTHING)) {
            if (group.equals(SpawnGroup.MONSTER) && world.getLightLevel(pos) > 0) {
                ci.setReturnValue(false);
            }
        }
    }

    @Inject(method = "spawn", at = @At("HEAD"))
    private static void spawn(ServerWorld world, WorldChunk chunk, SpawnHelper.Info info, boolean spawnAnimals, boolean spawnMonsters, boolean shouldSpawnAnimals, CallbackInfo ci) {
        ((ExtendedSpawnInfo)info).setClosestPlayer(world.getClosestPlayer(chunk.getPos().getStartPos().getX(), chunk.getPos().getStartPos().getY(), chunk.getPos().getStartPos().getZ(), -1.0, false));
    }

}