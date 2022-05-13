package top.mccat.service.impl;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import top.mccat.service.StrengthService;

/**
 * @author Distance
 * @date 2022/5/13 17:47
 */
public class StrengthServiceImpl implements StrengthService {
    @Override
    public boolean strengthItem(Player player) {
        return false;
    }

    @Override
    public ItemStack haveStone(Player player) {
        return null;
    }

    @Override
    public boolean itemCanStrength(Player player){
         return false;
    }
}
