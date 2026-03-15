package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class AncientAxeHeadItem extends Item {
	public AncientAxeHeadItem() {
		super(new Item.Properties().fireResistant().rarity(Rarity.EPIC));
	}
}