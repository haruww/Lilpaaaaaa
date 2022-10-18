package me.haru301.lilpaaaaaa.item;

import me.haru301.lilpaaaaaa.entity.CustomFireballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class FireballStickItem extends Item
{

    public FireballStickItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        Vector3d vector3d = playerIn.getLook(1.0F);

        for(int i=0 ; i<360 ; i++)
        {
            if(i % 15 == 0)
            {
                vector3d = vector3d.add(Math.sin(i), 0, Math.cos(i));
                worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH, SoundCategory.NEUTRAL, 1, 1);
                CustomFireballEntity fireballentity = new CustomFireballEntity(worldIn, playerIn, vector3d.x, vector3d.y, vector3d.z);
                fireballentity.explosionPower = 0;
                fireballentity.noClip = true;
                fireballentity.setPosition(playerIn.getPosX(), playerIn.getPosY(), fireballentity.getPosZ());
                worldIn.addEntity(fireballentity);
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
