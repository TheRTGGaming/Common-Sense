package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class NetheriteAxeHeadItem extends Item {
	public NetheriteAxeHeadItem() {
		super(new Item.Properties().fireResistant().rarity(Rarity.RARE));
	}
}