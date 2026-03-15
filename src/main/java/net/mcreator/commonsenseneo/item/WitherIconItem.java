package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;

import net.mcreator.commonsenseneo.procedures.WitherIconHitProcedure;

public class WitherIconItem extends Item {
	public WitherIconItem() {
		super(new Item.Properties());
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		WitherIconHitProcedure.execute(entity);
		return retval;
	}
}