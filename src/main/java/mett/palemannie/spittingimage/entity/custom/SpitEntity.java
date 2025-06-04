package mett.palemannie.spittingimage.entity.custom;

import mett.palemannie.spittingimage.entity.ModEntities;
import mett.palemannie.spittingimage.util.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

public class SpitEntity extends Projectile {

    public SpitEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    public SpitEntity(Level level, Player player){
        this(ModEntities.SPIT.get(), level);
        this.setOwner(player);
        this.setPos(player.getX(), player.getEyeY()-0.2d, player.getZ());
    }

    @Override
    public void tick() {
        super.tick();

        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }

        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();

        if (this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) {
            this.discard();
        } else if (this.isInWaterOrBubble()) {
            this.discard();
        } else {
            this.setDeltaMovement(vec3.scale(0.99f));
            if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0f, gravity, 0f));
            }

            this.setPos(d0, d1, d2);
        }

        if (this.tickCount % 9 == 0) {
            level().addParticle(ParticleTypes.SPIT, this.getX(), this.getY() + 0.2, this.getZ(), 0d, 0d, 0d);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
        super.onHitBlock(pResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        Entity entity = this.getOwner();
        Level level = this.level();

        if (entity instanceof Player player) {

            entity = pResult.getEntity();

            if (entity instanceof LivingEntity livingentity && (livingentity.hurtTime == 0 || (player.isCreative() && livingentity.hurtTime == 0))) {

                Vec3 knockback = this.getDeltaMovement().normalize().scale(0.4);
                entity.push(knockback.x, 0.4f, knockback.z);
                pResult.getEntity().hurt(level.damageSources().source(ModDamageTypes.SPIT_DAMAGE), 1f);
                this.discard();
            }

            else if (entity instanceof ItemFrame frame) {

                if (!frame.getItem().isEmpty()) {

                    if (!frame.level().isClientSide()) {

                        frame.level().addFreshEntity(new ItemEntity(
                                frame.level(),
                                frame.getX(),
                                frame.getY(),
                                frame.getZ(),
                                frame.getItem().copy()
                        ));
                    }

                    frame.setItem(ItemStack.EMPTY);

                } else {

                    this.discard();
                    ((HangingEntity) entity).dropItem(entity);
                    frame.kill();
                }
            }

            else if (entity instanceof Painting) {

                this.discard();
                ((HangingEntity) entity).dropItem(entity);
                entity.kill();
            }
        }

        this.discard();
    }

    @Override
    protected void defineSynchedData() {}

    protected double gravity = -0.05d;

    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);
        double d0 = packet.getXa();
        double d1 = packet.getYa();
        double d2 = packet.getZa();

        for(int i = 0; i < 3; ++i) {
            double d3 = 0.4 + 0.1 * (double)i;
            this.level().addParticle(ParticleTypes.SPIT, this.getX(), this.getY(), this.getZ(), d0 * d3, d1, d2 * d3);
        }

        this.setDeltaMovement(d0, d1, d2);
    }
}