package me.haru301.lilpaaaaaa;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticle
{
    public static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Lilpa.MOD_ID);

    public static final RegistryObject<BasicParticleType> SONIC_BOOM = REGISTER.register("sonic_boom", () -> new BasicParticleType(true));
}
