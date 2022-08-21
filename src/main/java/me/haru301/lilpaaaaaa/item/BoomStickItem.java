package me.haru301.lilpaaaaaa.item;

import me.haru301.lilpaaaaaa.init.ModSounds;
import me.haru301.lilpaaaaaa.packet.PacketHandler;
import me.haru301.lilpaaaaaa.packet.SonicBoomPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BoomStickItem extends Item
{
    public BoomStickItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        if(world.isRemote())
        {
            shootSonicBoom(player.getEyePosition(0), player.getLookVec());
            player.playSound(ModSounds.LILPA.get(), SoundCategory.VOICE, 1, 1);
        }
        return super.onItemRightClick(world, player, hand);
    }

    private void shootSonicBoom(Vector3d eyeVec3, Vector3d lookVec3)
    {
        Vector3d vec3 = eyeVec3;
        for(int i = 1; i < 100; i++)
        {
            Vector3d vec33 = vec3.add(lookVec3.scale(i));
            PacketHandler.INSTANCE.sendToServer(new SonicBoomPacket(vec33.x, vec33.y, vec33.z));
            /*for(Entity e : world.getEntitiesWithinAABBExcludingEntity(p, new AxisAlignedBB(vec33.subtract(1, 1, 1), vec33.add(1, 1, 1))))
                e.attackEntityFrom(new DamageSource(Lilpa.MOD_ID + "_sonic_boom"), 100);
            world.addParticle(ModParticle.SONIC_BOOM.get(), true, vec33.x, vec33.y, vec33.z, 1, 0, 0);*/
        }
    }
}
