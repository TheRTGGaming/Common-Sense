package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class RawAncientOreItem extends Item {
	public RawAncientOreItem() {
		super(new Item.Properties().fireResistant().rarity(Rarity.EPIC));
	}
}