package me.haru301.lilpaaaaaa.init;

import me.haru301.lilpaaaaaa.Lilpa;
import me.haru301.lilpaaaaaa.commands.LilpaCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Lilpa.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents
{
    @SubscribeEvent
    public static void onCommandInit(RegisterCommandsEvent event)
    {
        new LilpaCommand(event.getDispatcher());
    }
}
