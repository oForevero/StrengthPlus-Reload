package top.mccat.service;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Raven
 * @date 2022/05/05 21:22
 * 强化接口，用于实现强化逻辑
 */
public interface StrengthService {
    /**
     * 强化物品方法
     * @param player 玩家实体对象
     * @return 是否强化成功
     */
    boolean strengthItem(Player player);

    /**
     * 强化石检索方法，查看玩家是否有强化石
     * @param player 玩家实体对象
     * @return 第一个强化石物品堆对象
     */
    ItemStack haveStone(Player player);

    /**
     * 物品是否能被强化
     * @param player 玩家实体对象
     * @return 是否强化成功
     */
    boolean itemCanStrength(Player player);
}
