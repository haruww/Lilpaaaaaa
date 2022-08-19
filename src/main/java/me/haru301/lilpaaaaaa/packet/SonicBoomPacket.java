package me.haru301.lilpaaaaaa.packet;

import me.haru301.lilpaaaaaa.Lilpa;
import me.haru301.lilpaaaaaa.ModParticle;
import me.haru301.lilpaaaaaa.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SonicBoomPacket
{
    private double x;
    private double y;
    private double z;

    public SonicBoomPacket(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
    }

    //decode
    public SonicBoomPacket(PacketBuffer buffer)
    {
        this.x = buffer.readDouble();
        this.y = buffer.readDouble();
        this.z = buffer.readDouble();
    }

    public static void handle(SonicBoomPacket msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity sender = ctx.get().getSender();
            Vector3d vec3 = new Vector3d(msg.x, msg.y, msg.z);

            for(Entity e : sender.getServerWorld().getEntitiesWithinAABBExcludingEntity(sender, new AxisAlignedBB(vec3.subtract(1, 1, 1), vec3.add(1, 1, 1))))
                e.attackEntityFrom(new DamageSource(Lilpa.MOD_ID + "_sonic_boom"), 100);
            sender.getServerWorld().spawnParticle(sender, ModParticle.SONIC_BOOM.get(), true, vec3.x, vec3.y, vec3.z, 1, 0, 0,0, 0);
            sender.getServerWorld().playSound(sender, sender.getPosition(), ModSounds.SERVERSIDE.get(), SoundCategory.VOICE, 2, 1);
            //sender.getServerWorld().addParticle(ModParticle.SONIC_BOOM.get(), true, vec3.x, vec3.y, vec3.z, 1, 0, 0);
        });
        ctx.get().setPacketHandled(true);
    }
}
