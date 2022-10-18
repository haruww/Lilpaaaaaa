package me.haru301.lilpaaaaaa.init;

import me.haru301.lilpaaaaaa.Lilpa;
import me.haru301.lilpaaaaaa.SonicBoomParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Lilpa.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents
{
    @SubscribeEvent
    public static void initParticleFactory(final ParticleFactoryRegisterEvent event)
    {
        Minecraft.getInstance().particles.registerFactory(ModParticle.SONIC_BOOM.get(), SonicBoomParticle.Factory::new);
    }
}
