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
    public boolean sameLoreChecker(ItemStack item1, ItemStack item2){
        ItemMeta itemMeta1 = item1.getItemMeta();
        if(itemMeta1 == null){
            return false;
        }
        if(ItemCheckerUtils.isAirMaterial(item1)){
            return false;
        }
        ItemMeta itemMeta2 = item2.getItemMeta();
        if(itemMeta2 == null){
            return false;
        }
        if(ItemCheckerUtils.isAirMaterial(item2)){
            return false;
        }
        List<String> lore1 = itemMeta1.getLore();
        List<String> lore2 = itemMeta2.getLore();
        if(lore1 == null || lore2 == null){
            return false;
        }
        return lore1.equals(lore2);
    }
}
