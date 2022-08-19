package me.haru301.lilpaaaaaa;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds
{
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Lilpa.MOD_ID);

    public static final RegistryObject<SoundEvent> LILPA = register("lilpa");

    private static RegistryObject<SoundEvent> register(String key)
    {
        return REGISTER.register(key, () -> new SoundEvent(new ResourceLocation(Lilpa.MOD_ID, key)));
    }
}
