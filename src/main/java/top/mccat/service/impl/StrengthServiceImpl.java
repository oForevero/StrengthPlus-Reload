package top.mccat.service.impl;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import top.mccat.dao.StrengthDao;
import top.mccat.domain.StrengthStone;
import top.mccat.service.StrengthService;
import top.mccat.utils.ItemCheckerUtils;

import java.util.List;

/**
 * @author Distance
 * @date 2022/5/13 17:47
 */
public class StrengthServiceImpl implements StrengthService {
    private List<String> strengthItem;
    private List<StrengthStone> strengthStones;
    private StrengthDao strengthDao;

    public StrengthServiceImpl(List<String> strengthItem, List<StrengthStone> strengthStones, StrengthDao strengthDao) {
        this.strengthItem = strengthItem;
        this.strengthStones = strengthStones;
        this.strengthDao = strengthDao;
    }

    @Override
    public boolean strengthItem(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack haveStone(Inventory chestInventory) {
        ItemStack leftStrengthStone = chestInventory.getItem(13);
        if(!ItemCheckerUtils.isAirMaterial(leftStrengthStone)){
            for (StrengthStone strengthStone : strengthStones){
                if(sameLoreChecker(leftStrengthStone, strengthStone.getLore())){
                    return leftStrengthStone;
                }
            }
        }
        ItemStack rightStrengthStone = chestInventory.getItem(14);
        if(!ItemCheckerUtils.isAirMaterial(rightStrengthStone)){
            for (StrengthStone strengthStone : strengthStones){
                if(sameLoreChecker(rightStrengthStone, strengthStone.getLore())){
                    return leftStrengthStone;
                }
            }
        }
        return null;
    }

    @Override
    public ItemStack haveStone(PlayerInventory playerInventory) {
        return null;
    }

    @Override
    public boolean itemCanStrength(ItemStack stack){
        if(stack!=null){
            return false;
        }
        Material type = stack.getType();
        if(ItemCheckerUtils.isAirMaterial(stack)){
            return false;
        }
        for(String strType : strengthItem){
            if (type.toString().equals(strType)){
                return true;
            }
        }
        return false;
    }

    /**
     * 检测两个强化物品的lore是否相同
     * @param item 被检测物品
     * @param lore 物品lore
     * @return
     */
    private boolean sameLoreChecker(@NotNull ItemStack item, List<String> lore){
        ItemMeta itemMeta = item.getItemMeta();
        if(ItemCheckerUtils.isAirMaterial(item)){
           return false;
        }
        List<String> itemLore = itemMeta.getLore();
        return lore.equals(itemLore);
    }

    public void setStrengthItem(List<String> strengthItem) {
        this.strengthItem = strengthItem;
    }

    public void setStrengthStone(List<StrengthStone> strengthStones) {
        this.strengthStones = strengthStones;
    }
}
