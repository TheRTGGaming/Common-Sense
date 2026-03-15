package net.mcreator.commonsenseneo.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;

import net.mcreator.commonsenseneo.procedures.GoldChocolateBarPlayerFinishesUsingItemProcedure;

public class GoldChocolateBarItem extends Item {
	public GoldChocolateBarItem() {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).food((new FoodProperties.Builder()).nutrition(8).saturationModifier(3f).alwaysEdible().build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		GoldChocolateBarPlayerFinishesUsingItemProcedure.execute(entity);
		return retval;
	}
}