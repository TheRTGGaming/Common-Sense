package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import net.mcreator.commonsenseneo.procedures.CloverRightclickedOnACluffOfCloversProcedure;

public class ThreeLeafCloverItem extends Item {
	public ThreeLeafCloverItem() {
		super(new Item.Properties());
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		CloverRightclickedOnACluffOfCloversProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getPlayer());
		return InteractionResult.SUCCESS;
	}
}