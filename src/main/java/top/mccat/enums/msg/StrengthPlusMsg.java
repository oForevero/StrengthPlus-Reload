package top.mccat.enums.msg;

import top.mccat.enums.EnumMsg;

/**
 * @author Distance
 * @date 2022/5/26 13:12
 */
public enum StrengthPlusMsg implements EnumMsg {
    /**
     * 强化结束信息，当强化结束
     */
    StrengthNotFinish("您当前正在进行强化操作，请等待强化结束后再进行强化",1);

    StrengthPlusMsg(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }

    private String msg;
    private Integer status;

    @Override
    public String getMsg() {
        return msg;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "StrengthPlusMsg{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
