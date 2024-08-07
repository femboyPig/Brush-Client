package ru.femboypig.modules.misc;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import ru.femboypig.config.BrushCC;
import ru.femboypig.modules.Func;

public class ElytraSwap extends Func {
    public ElytraSwap() {
        super("Elytra Swap", "Allows you to quickly change from breastplate to elytras and vice versa.");
    }

    public static TypedActionResult<ItemStack> equipAndSwap(Item item, World world, PlayerEntity user, Hand hand) {
        if (BrushCC.CONFIG.instance().elytraswap) {
            ItemStack stackInHand = user.getStackInHand(hand);

            EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(stackInHand);
            ItemStack equippedStack = user.getEquippedStack(equipmentSlot);

            if (EnchantmentHelper.hasBindingCurse(equippedStack) || ItemStack.areEqual(stackInHand, equippedStack)) {
                return TypedActionResult.fail(stackInHand);
            }

            user.equipStack(equipmentSlot, stackInHand.copy());
            if (!world.isClient()) {
                user.incrementStat(Stats.USED.getOrCreateStat(item));
            }

            if (equippedStack.isEmpty()) {
                stackInHand.setCount(0);
            } else {
                user.setStackInHand(hand, equippedStack.copy());
            }

            return TypedActionResult.success(stackInHand, world.isClient());
        }
        return null;
    }
}