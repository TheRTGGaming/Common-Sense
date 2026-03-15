package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class SawBladeItem extends Item {
	public SawBladeItem() {
		super(new Item.Properties().durability(50).rarity(Rarity.UNCOMMON));
	}
}