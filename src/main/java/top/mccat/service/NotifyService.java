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
     */
    void strengthSuccessNotify(Player player);

    /**
     * 强化失败时执行的通知
     * @param player 玩家对象
     */
    void strengthFailNotify(Player player);

    /**
     * 执行高阶强化成功后的广播
     */
    void StrengthSuccessBroadcast();

    /**
     * 执行高阶强化失败但保护后的广播
     */
    void StrengthSafeBroadcast();

    /**
     * 执行高阶强化失败后的广播
     */
    void StrengthFailBroadcast();
}
