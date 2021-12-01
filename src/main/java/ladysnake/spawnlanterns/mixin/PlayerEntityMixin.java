package ladysnake.spawnlanterns.mixin;

import ladysnake.spawnlanterns.common.SpawnLanterns;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract boolean isCreative();

    @Inject(at = @At("RETURN"), method = "tick")
    public void tick(CallbackInfo ci) {
        if (this.hasStatusEffect(SpawnLanterns.PROVOCATION)) {
            this.getEntityWorld().getEntitiesByClass(LivingEntity.class, this.getBoundingBox().expand(32.0D), EntityPredicates.VALID_LIVING_ENTITY).forEach(entity -> {
                if (entity instanceof Angerable && ((Angerable) entity).getTarget() == null && !this.isCreative()) {
                    if (!(entity instanceof TameableEntity) || !((TameableEntity) entity).isTamed()) {
                        ((Angerable) entity).setAngryAt(this.getUuid());
                        ((Angerable) entity).setTarget(this);
                    }
                }
                if (entity instanceof HostileEntity && ((HostileEntity) entity).getTarget() == null && !this.isCreative()) {
                    ((HostileEntity) entity).setTarget(this);
                }
            });
        }
    }
}
