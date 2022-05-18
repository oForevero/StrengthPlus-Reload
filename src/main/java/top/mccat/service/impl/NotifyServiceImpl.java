package top.mccat.service.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mccat.dao.NotifyDao;
import top.mccat.dao.impl.NotifyDaoImpl;
import top.mccat.domain.config.EssentialsConfig;
import top.mccat.enums.YamlVariable;
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
    private final NotifyDao notifyDao = new NotifyDaoImpl();
    public NotifyServiceImpl() {
    }

    public NotifyServiceImpl(EssentialsConfig essentialsConfig) {
        this.essentialsConfig = essentialsConfig;
    }

    @Override
    public void strengthSuccessNotify(Player player, String level) {
        String msg = replaceVariableExtra(essentialsConfig.getSuccessNotify(),player,level);
        notifyDao.notifyPlayer(msg, player);
    }

    @Override
    public void strengthFailNotify(Player player, String level) {
        String msg = replaceVariableExtra(essentialsConfig.getFailBroadcast(),player,level);
        notifyDao.notifyPlayer(msg, player);
    }

    @Override
    public void strengthSuccessBroadcast(Player player, String level) {
        String msg = replaceVariableExtra(essentialsConfig.getSuccessBroadcast(),player,level);
        notifyDao.notifyPlayer(msg, player);
    }

    @Override
    public void strengthSafeBroadcast(Player player, String level) {
        String msg = replaceVariableExtra(essentialsConfig.getSafeBroadcast(),player,level);
        notifyDao.notifyPlayer(msg, player);
    }

    @Override
    public void strengthFailBroadcast(Player player, String level) {
        String msg = replaceVariableExtra(essentialsConfig.getFailBroadcast(),player,level);
        notifyDao.notifyPlayer(msg, player);
    }

    /**
     * 把变量数据转换为生产数据
     * @param strExtra str参数
     * @param player 玩家对象
     * @param level 等级参数
     * @return
     */
    private String replaceVariableExtra(String strExtra, Player player, String level){
        if(strExtra.contains(YamlVariable.PlayerVariable.getVariable())){
            strExtra.replace(YamlVariable.PlayerVariable.getVariable(), player.getName());
        }
        if(strExtra.contains(YamlVariable.WeaponLevelVariable.getVariable())){
            strExtra.replace(YamlVariable.WeaponLevelVariable.getVariable(), level);
        }
        return ColorUtils.getColorStr(strExtra);
    }

    public void setEssentialsConfig(EssentialsConfig essentialsConfig) {
        this.essentialsConfig = essentialsConfig;
    }
}
