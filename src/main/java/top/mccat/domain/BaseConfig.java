package top.mccat.domain;

/**
 * @author Distance
 * @date 2022/5/19 16:18
 * config 基类
 */
public abstract class BaseConfig {
    /**
     * 配置文件名
     * @return 自己的配置文件名
     */
    abstract String configFileName();

    /**
     * 当出现加载失败的错误消息
     * @return 错误消息枚举
     */
    abstract String loadErrorMsg();
}
