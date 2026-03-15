package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class EndFragmentItem extends Item {
	public EndFragmentItem() {
		super(new Item.Properties().stacksTo(16).fireResistant().rarity(Rarity.EPIC));
	}
}