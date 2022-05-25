package top.mccat.service.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import top.mccat.service.StrengthService;

import java.util.List;

/**
 * @author Distance
 * @date 2022/5/13 17:47
 */
public class StrengthServiceImpl implements StrengthService {
    private List<String> strengthItem;
    @Override
    public boolean strengthItem(ItemStack stack) {
        if(itemCanStrength(stack)){

        }
        return false;
    }

    @Override
    public ItemStack haveStone(ItemStack[] stack) {
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
        if(type == Material.AIR){
            return false;
        }
        for(String strType : strengthItem){
            if (type.toString().equals(strType)){
                return true;
            }
        }
        return false;
    }

    public void setStrengthItem(List<String> strengthItem) {
        this.strengthItem = strengthItem;
    }
}
