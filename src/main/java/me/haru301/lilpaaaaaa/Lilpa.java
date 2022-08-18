package me.haru301.lilpaaaaaa;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("lilpaaaaaa")
public class Lilpa
{
    public static final String MOD_ID = "lilpaaaaaa";
    public static final Logger LOGGER = LogManager.getLogger();

    public Lilpa()
    {
        IEventBus bus =FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onCommonSetup);
        ModParticle.REGISTER.register(bus);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
    }
}
