package top.mccat.domain;

import org.bukkit.inventory.ItemStack;

/**
 * @author Distance
 * @date 2022/5/31 10:59
 */
public class StrengthResult {
    private boolean result;
    private ItemStack itemStack;
    private String msg;

    public StrengthResult() {
    }

    public StrengthResult(boolean result, ItemStack itemStack, String msg) {
        this.result = result;
        this.itemStack = itemStack;
        this.msg = msg;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "StrengthResult{" +
                "result=" + result +
                ", itemStack=" + itemStack +
                ", msg='" + msg + '\'' +
                '}';
    }
}
