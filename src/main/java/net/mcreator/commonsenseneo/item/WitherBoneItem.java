package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class WitherBoneItem extends Item {
	public WitherBoneItem() {
		super(new Item.Properties().fireResistant().rarity(Rarity.EPIC));
	}
}