package me.haru301.lilpaaaaaa.init;

import me.haru301.lilpaaaaaa.Lilpa;
import me.haru301.lilpaaaaaa.entity.CustomFireballEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, Lilpa.MOD_ID);

    public static final RegistryObject<EntityType<CustomFireballEntity>> CUSTOM_FIREBALL = REGISTER.register("custom_fireball", () -> EntityType.Builder.<CustomFireballEntity>create(CustomFireballEntity::new, EntityClassification.MISC).size(1.0f, 1.0f).build("custom_fireball"));
}
