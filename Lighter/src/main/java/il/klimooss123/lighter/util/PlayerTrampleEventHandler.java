package il.klimooss123.lighter.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerTrampleEventHandler {

    @SubscribeEvent
    public static void onFarmlandTrample(BlockEvent.FarmlandTrampleEvent event) {
        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;
        if (player != null && !player.getCommandSenderWorld().isClientSide()) {
            ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
            if (!boots.isEmpty() && hasFeatherFallingEnchantment(boots)) {
                event.setCanceled(true);
            }
        }
    }
    private static boolean hasFeatherFallingEnchantment(ItemStack boots) {
        return EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FALL_PROTECTION, boots) > 0;
    }

}