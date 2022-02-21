package fr.aldraziel.aldracore.utils;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class NbtUtils {

    public static <T> T readNbt(ItemStack stack, String key, Class<T> as) {
        return new NBTItem(stack).getObject(key, as);
    }

    public static <T> void writeNbt(ItemStack stack, String key, T value) {
        final NBTItem nbt = new NBTItem(stack);
        nbt.setObject(key, value);
        nbt.applyNBT(stack);
    }
}
