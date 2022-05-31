package top.mccat.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Distance
 * @date 2022/5/31 10:19
 */
public class ItemCheckerUtils {
    /**
     * 检查Materials 是否不为空气
     * @param itemStack 物品堆
     * @return 是否为空气Materials
     */
    public static boolean isAirMaterial(ItemStack itemStack){
        return Material.AIR.equals(itemStack.getType());
    }

    /**
     * 检测两个强化物品的lore是否相同
     * @param item1 物品1
     * @param item2 物品2
     * @return 是否相同
     */
    private boolean sameLoreChecker(@NotNull ItemStack item1, @NotNull ItemStack item2){
        ItemMeta itemMeta1 = item1.getItemMeta();
        if(ItemCheckerUtils.isAirMaterial(item1)){
            return false;
        }
        ItemMeta itemMeta2 = item2.getItemMeta();
        if(ItemCheckerUtils.isAirMaterial(item2)){
            return false;
        }
        List<String> lore1 = itemMeta1.getLore();
        List<String> lore2 = itemMeta2.getLore();
        return lore1.equals(lore2);
    }
}
