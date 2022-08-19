package me.haru301.lilpaaaaaa;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BoomStickItem extends Item
{
    public BoomStickItem(Properties properties)
    {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        shootSonicBoom(world, player.getEyePosition(0), player.getLookVec(), player);
        player.playSound(ModSounds.LILPA.get(), SoundCategory.MASTER, 1, 1);
        return super.onItemRightClick(world, player, hand);
    }

    private void shootSonicBoom(World world, Vector3d eyeVec3, Vector3d lookVec3, Entity p)
    {
        Vector3d vec3 = eyeVec3;
        for(int i = 1; i < 50; i++)
        {
            Vector3d vec33 = vec3.add(lookVec3.scale(i));
            for(Entity e : world.getEntitiesWithinAABBExcludingEntity(p, new AxisAlignedBB(vec33.subtract(1, 1, 1), vec33.add(1, 1, 1))))
                e.attackEntityFrom(new DamageSource(Lilpa.MOD_ID + "_sonic_boom"), 50);
            world.addParticle(ModParticle.SONIC_BOOM.get(), true, vec33.x, vec33.y, vec33.z, 1, 0, 0);
        }
    }
}