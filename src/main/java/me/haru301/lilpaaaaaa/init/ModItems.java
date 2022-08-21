package me.haru301.lilpaaaaaa.init;

import me.haru301.lilpaaaaaa.item.BoomStickItem;
import me.haru301.lilpaaaaaa.Lilpa;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Lilpa.MOD_ID);

    public static final RegistryObject<Item> BOOM_STICK = REGISTER.register( "boom_stick", () -> new BoomStickItem(new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1)));
}
