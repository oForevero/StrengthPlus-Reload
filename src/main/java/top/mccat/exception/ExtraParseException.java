package top.mccat.exception;

/**
 * @author Distance
 * @date @date 2022/05/12 16:54
 */
public class ExtraParseException extends Exception{

    public ExtraParseException() {
        super("数据转换失败，请确认数据格式是否正确！");
    }

    public ExtraParseException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
