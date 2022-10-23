package me.haru301.lilpaaaaaa.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.haru301.lilpaaaaaa.Lilpa;
import me.haru301.lilpaaaaaa.init.ModParticle;
import me.haru301.lilpaaaaaa.init.ModSounds;
import me.haru301.lilpaaaaaa.item.BoomStickItem;
import me.haru301.lilpaaaaaa.packet.PacketHandler;
import me.haru301.lilpaaaaaa.packet.SonicBoomPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.Vec3Argument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class LilpaCommand
{
    public LilpaCommand(CommandDispatcher<CommandSource> dispatcher)
    {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("lilpa");
        builder.then(Commands.argument("vec", Vec3Argument.vec3()).then(Commands.argument("target", EntityArgument.players()).executes((source) -> {
            ServerPlayerEntity target = EntityArgument.getPlayer(source, "target");
            Vector3d playerPos = target.getEyePosition(0);
            Vector3d boomPos = Vec3Argument.getVec3(source, "vec");
            Vector3d normalized = playerPos.subtract(boomPos).normalize();
            ServerWorld world = target.getServerWorld();

            for(int i = 1; i < 100; i++)
            {
                Vector3d vec33 = boomPos.add(normalized.scale(i));

                for(Entity e : target.getServerWorld().getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(vec33.subtract(1, 1, 1), vec33.add(1, 1, 1))))
                {
                    if(e instanceof ItemEntity)
                        continue;
                    e.attackEntityFrom(new DamageSource(Lilpa.MOD_ID + "_sonic_boom"), 15);
                }
                for(ServerPlayerEntity entity : target.getServer().getPlayerList().getPlayers())
                    world.spawnParticle(entity, ModParticle.SONIC_BOOM.get(), true, vec33.x, vec33.y, vec33.z, 1, 0, 0,0, 0);
            }
            world.playSound(null, new BlockPos(boomPos), ModSounds.SERVERSIDE.get(), SoundCategory.HOSTILE, 7, 1);

            return 0;
        })));
        dispatcher.register(builder);
    }
}
