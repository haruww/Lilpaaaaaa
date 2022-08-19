package me.haru301.lilpaaaaaa.packet;

import me.haru301.lilpaaaaaa.Lilpa;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler
{
    public static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry
            .newSimpleChannel(new ResourceLocation(Lilpa.MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void init()
    {
        int id = 0;
        INSTANCE.messageBuilder(SonicBoomPacket.class, id++, NetworkDirection.PLAY_TO_SERVER).encoder(SonicBoomPacket::encode).decoder(SonicBoomPacket::new).consumer(SonicBoomPacket::handle).add();
    }
}
