package top.mccat.enums;
/**
 * @author Raven
 * @date 2022/05/12 16:54
 */
public enum YamlConfigMessage {
    /**
     * 对应config下的error级别的消息
     */
    ConfigStrengthPlusLoadError(2,"错误，config.yml下的strengthPlus数据不存在或文件不存在！"),
    ConfigNotifyLoadError(2,"错误，config.yml下的notify数据不存在或文件不存在！"),
    ConfigBroadcastLoadError(2,"错误，config.yml下的broadcast数据不存在或文件不存在！"),
    ConfigDamageLoadError(2,"错误，config.yml下的damage数据不存在或文件不存在！"),
    ConfigDefenceLoadError(2,"错误，config.yml下的defence数据不存在或文件不存在！"),
    ;

    private int levelCode;
    private String message;

    YamlConfigMessage(int levelCode, String message) {
        this.levelCode = levelCode;
        this.message = message;
    }

    public int getLevelCode() {
        return levelCode;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "YamlConfigMessage{" +
                "levelCode=" + levelCode +
                ", message='" + message + '\'' +
                '}';
    }
}
