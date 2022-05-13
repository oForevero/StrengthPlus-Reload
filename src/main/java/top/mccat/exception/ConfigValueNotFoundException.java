package top.mccat.exception;

/**
 * @author Raven
 * @date 2022/05/12 16:54
 */
public class ConfigValueNotFoundException extends Exception{

    public ConfigValueNotFoundException() {
        super("错误，未能找到本地yml配置文件！");
    }

    public ConfigValueNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
