package fr.aldraziel.aldracore.utils;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static void setBonusAttribute(Player player, Attribute attribute, double value) {
        final AttributeInstance att = player.getAttribute(attribute);
        if (att != null)
            att.setBaseValue(att.getBaseValue() + value);
    }

    public static void setAttribute(Player player, Attribute attribute, double value) {
        final AttributeInstance att = player.getAttribute(attribute);
        if (att != null)
            att.setBaseValue(value);
    }
}
