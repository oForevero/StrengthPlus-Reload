package top.mccat.dao;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author Raven
 * @date 2022/05/05 21:23
 */
public interface StrengthDao {
    /**
     * 强化物品方法
     * @param strengthItem 需要被强化的物品
     * @param lore lore参数
     * @return ItemMeta
     */
    ItemMeta strengthItem(ItemStack strengthItem, List<String> lore);
}
