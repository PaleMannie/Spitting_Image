package mett.palemannie.spittingimage.entity.custom;

import mett.palemannie.spittingimage.entity.ModEntities;
import mett.palemannie.spittingimage.item.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class SpitEntity extends ThrowableItemProjectile {

    public SpitEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public SpitEntity(Level pLevel) {
        super(ModEntities.SPIT.get(), pLevel);
    }
    public SpitEntity(Level pLevel, LivingEntity pLivingEntity) {
        super(ModEntities.SPIT.get(), pLivingEntity, pLevel);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isInWaterOrBubble()) {
            this.discard();
        } else if (this.level.getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) {
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
        super.onHitBlock(pResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.hurt(DamageSource.thrown(this, this.getOwner()), 1f);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SPIT.get();
    }

}
