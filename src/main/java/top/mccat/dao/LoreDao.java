package top.mccat.dao;

import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author Distance
 * @date 2022/5/24 14:36
 */
public interface LoreDao {
    /**
     * 清除该ItemMeta中的所有lore
     * @param meta 物品对象
     * @return ItemMeta 对象
     */
    ItemMeta clearLore(ItemMeta meta);

    /**
     * 仅清除属于该插件的的lore
     * @param meta 物品对象
     * @return ItemMeta 对象
     */
    ItemMeta clearStrengthLore(ItemMeta meta);

    /**
     * 将loreList中的参数覆盖掉ItemMeta 中的所有lore参数
     * @param meta 物品对象
     * @param loreList String list列表
     * @return ItemMeta 对象
     */
    ItemMeta setLore(ItemMeta meta, List<String> loreList);

    /**
     * 将loreList中的参数覆盖表ItemMeta 中的强化lore参数
     * @param meta 物品对象
     * @param loreList
     * @return
     */
    ItemMeta setStrengthLore(ItemMeta meta, List<String> loreList);
}
