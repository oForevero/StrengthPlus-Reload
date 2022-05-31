package top.mccat.dao.impl;

import org.bukkit.inventory.meta.ItemMeta;
import top.mccat.dao.LoreDao;
import top.mccat.domain.config.EssentialsConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Distance
 * @date 2022/5/24 14:35
 */
public class LoreDaoImpl implements LoreDao {
    private EssentialsConfig essentialsConfig;
    private String divider;
    public LoreDaoImpl(){}

    public LoreDaoImpl(EssentialsConfig essentialsConfig) {
        this.essentialsConfig = essentialsConfig;
        divider = essentialsConfig.getDivider();
    }

    @Override
    public ItemMeta clearLore(ItemMeta meta) {
        meta.setLore(null);
        return meta;
    }

    @Override
    public ItemMeta clearStrengthLore(ItemMeta meta) {
        List<String> lore = meta.getLore();
        if(lore == null){
            return null;
        }
        int startIndex = lore.indexOf(divider);
        int endIndex = lore.lastIndexOf(divider);
        lore.removeAll(lore.subList(startIndex,endIndex));
        meta.setLore(lore);
        return meta;
    }

    @Override
    public ItemMeta setLore(ItemMeta meta, List<String> loreList) {
        meta.setLore(loreList);
        return meta;
    }

    @Override
    public ItemMeta setStrengthLore(ItemMeta meta, List<String> loreList) {
        ArrayList<String> lore = (ArrayList<String>) meta.getLore();
        if(lore == null){
            return null;
        }
        int startIndex = lore.indexOf(divider);
        int endIndex = lore.lastIndexOf(divider);
        lore.removeAll(lore.subList(startIndex+1,endIndex-1));
        lore.addAll(startIndex,loreList);
        meta.setLore(lore);
        return meta;
    }

    public void setEssentialsConfig(EssentialsConfig essentialsConfig) {
        this.essentialsConfig = essentialsConfig;
    }
}
