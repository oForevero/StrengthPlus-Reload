package top.mccat.enums.msg;

import top.mccat.enums.EnumMsg;

/**
 * @author Raven
 * @date 2022/05/12 16:54
 */
public enum YamlConfigMsg implements EnumMsg {
    /**
     * 对应config下的error级别的消息
     */
    ConfigStrengthPlusLoadError(2,"错误，config.yml下的strengthPlus数据不存在！"),
    ConfigNotifyLoadError(2,"错误，config.yml下的notify数据不存在！"),
    ConfigBroadcastLoadError(2,"错误，config.yml下的broadcast数据不存在！"),
    /**
     * 对应strength-level下的strength-level参数信息
     */
    ConfigStrengthLevelLoadError(2,"错误，strength-level.yml下的strength_level数据不存在！"),
    /**
     * 对应strength-stone下的error级别的信息
     */
    ConfigStrengthStoneLoadError(2,"错误，strength-stone.yml下的strength_stone数据不存在！"),
    /**
     * 对应strength-item下的strength-item参数信息
     */
    ConfigStrengthItemLoadError(2,"错误，strength-item.yml下的strength_item数据不存在！"),
    /**
     * 对应strength-extra下面的参数
     */
    ConfigStrengthExtraLoadError(2,"错误，strength-extra.yml下的strength-extra数据不存在！"),
    ConfigDamageLoadError(2,"错误，strength-extra.yml下的damage数据不存在！"),
    ConfigDefenceLoadError(2,"错误，strength-extra.yml下的defence数据不存在！"),
    /**
     * 对应strength-menu下面的参数
     */
    ConfigStrengthMenuLoadError(2,"错误，strength-menu.yml下的strength-menu数据不存在！")
    ;

    private final int levelCode;
    private final String msg;

    YamlConfigMsg(int levelCode, String msg) {
        this.levelCode = levelCode;
        this.msg = msg;
    }

    public int getLevelCode() {
        return levelCode;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "YamlConfigMsg{" +
                "levelCode=" + levelCode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
