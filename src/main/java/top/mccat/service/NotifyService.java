package top.mccat.service;

import org.bukkit.entity.Player;

/**
 * @author Distance
 * @date 2022/5/13 17:49
 * 插件通知接口服务
 */
public interface NotifyService {
    /**
     * 强化成功时执行的通知
     * @param player 玩家对象
     * @param level 物品等级
     */
    void strengthSuccessNotify(Player player, String level);

    /**
     * 强化失败时执行的通知
     * @param player 玩家对象
     * @param level 物品等级
     */
    void strengthFailNotify(Player player, String level);

    /**
     * 执行高阶强化成功后的广播
     * @param player 玩家对象
     * @param level 物品等级
     */
    void strengthSuccessBroadcast(Player player, String level);

    /**
     * 执行高阶强化失败但保护后的广播
     * @param player 玩家对象
     * @param level 物品等级
     */
    void strengthSafeBroadcast(Player player, String level);

    /**
     * 执行高阶强化失败后的广播
     * @param player 玩家对象
     * @param level 物品等级
     */
    void strengthFailBroadcast(Player player, String level);
}
