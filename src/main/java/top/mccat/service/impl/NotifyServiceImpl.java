package top.mccat.service.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mccat.domain.config.EssentialsConfig;
import top.mccat.service.NotifyService;
import top.mccat.utils.ColorUtils;

/**
 * @author Distance
 * @date 2022/5/16 15:46
 * 通知实现类
 */
public class NotifyServiceImpl implements NotifyService {
    private EssentialsConfig essentialsConfig;
    private CommandSender commandSender;
    public NotifyServiceImpl() {
    }

    public NotifyServiceImpl(EssentialsConfig essentialsConfig) {
        this.essentialsConfig = essentialsConfig;
    }

    @Override
    public void strengthSuccessNotify(Player player) {
        player.sendMessage(ColorUtils.getColorStr(essentialsConfig.getSuccessNotify()));
    }

    @Override
    public void strengthFailNotify(Player player) {
        player.sendMessage(ColorUtils.getColorStr(essentialsConfig.getFailNotify()));
    }

    @Override
    public void StrengthSuccessBroadcast() {
        commandSender.sendMessage(ColorUtils.getColorStr(essentialsConfig.getSuccessBroadcast()));
    }

    @Override
    public void StrengthSafeBroadcast() {
        commandSender.sendMessage(ColorUtils.getColorStr(essentialsConfig.getSafeBroadcast()));
    }

    @Override
    public void StrengthFailBroadcast() {
        commandSender.sendMessage(ColorUtils.getColorStr(essentialsConfig.getFailBroadcast()));
    }

    public void setEssentialsConfig(EssentialsConfig essentialsConfig) {
        this.essentialsConfig = essentialsConfig;
    }
}
