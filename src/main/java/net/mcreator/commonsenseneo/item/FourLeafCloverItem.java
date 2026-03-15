package net.mcreator.commonsenseneo.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;

import net.mcreator.commonsenseneo.procedures.WhenFourLeafCloverIsInInventoryProcedure;
import net.mcreator.commonsenseneo.procedures.FourLeafCloverRightclickedOnBlockProcedure;

public class FourLeafCloverItem extends Item {
	public FourLeafCloverItem() {
		super(new Item.Properties().stacksTo(1));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		FourLeafCloverRightclickedOnBlockProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getPlayer());
		return InteractionResult.SUCCESS;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		WhenFourLeafCloverIsInInventoryProcedure.execute(entity);
	}
}