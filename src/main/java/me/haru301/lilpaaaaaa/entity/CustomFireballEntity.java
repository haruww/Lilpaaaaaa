package me.haru301.lilpaaaaaa.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CustomFireballEntity extends AbstractFireballEntity
{
    public int explosionPower = 1;

    public CustomFireballEntity(EntityType<? extends CustomFireballEntity> p_i50163_1_, World p_i50163_2_) {
        super(p_i50163_1_, p_i50163_2_);
    }

    @OnlyIn(Dist.CLIENT)
    public CustomFireballEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(EntityType.FIREBALL, x, y, z, accelX, accelY, accelZ, worldIn);
    }

    public CustomFireballEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(EntityType.FIREBALL, shooter, accelX, accelY, accelZ, worldIn);
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            this.remove();
        }
    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        if (!this.world.isRemote) {
            Entity entity = result.getEntity();
            Entity entity1 = this.getShooter();
            entity.attackEntityFrom(DamageSource.causeOnFireDamage(this, entity1), 10.0F);
            if (entity1 instanceof LivingEntity) {
                this.applyEnchantments((LivingEntity)entity1, entity);
            }

        }
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("ExplosionPower", this.explosionPower);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("ExplosionPower", 99)) {
            this.explosionPower = compound.getInt("ExplosionPower");
        }

    }
}
