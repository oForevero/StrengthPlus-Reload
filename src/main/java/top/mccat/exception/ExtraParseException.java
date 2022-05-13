package top.mccat.exception;

/**
 * @author Distance
 * @date
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
