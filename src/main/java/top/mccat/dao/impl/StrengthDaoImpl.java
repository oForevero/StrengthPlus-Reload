package top.mccat.dao.impl;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.mccat.dao.LoreDao;
import top.mccat.dao.StrengthDao;
import top.mccat.domain.StrengthExtra;
import top.mccat.domain.config.EssentialsConfig;

import java.util.List;

/**
 * @author Distance
 * @date 2022/5/13 17:47
 */
public class StrengthDaoImpl implements StrengthDao {
    private EssentialsConfig essentialsConfig;
    private LoreDao loreDao;
    public StrengthDaoImpl(EssentialsConfig essentialsConfig) {
        this.essentialsConfig = essentialsConfig;
        loreDao = new LoreDaoImpl(essentialsConfig);
    }

    @Override
    public ItemMeta strengthItem(ItemStack strengthItem, List<String> lore) {
        ItemMeta itemMeta = strengthItem.getItemMeta();
        return itemMeta == null || !itemMeta.hasLore() ? loreDao.setLore(itemMeta, lore) : loreDao.setStrengthLore(itemMeta ,lore);
    }

    public void setEssentialsConfig(EssentialsConfig essentialsConfig) {
        this.essentialsConfig = essentialsConfig;
    }
}
