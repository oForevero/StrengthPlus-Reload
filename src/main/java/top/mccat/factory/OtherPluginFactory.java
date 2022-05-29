package top.mccat.factory;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.milkbowl.vault.economy.Economy;

/**
 * @author Distance
 * @date 2022/5/27 10:13
 */
public final class OtherPluginFactory {
    private static Economy economy;
    private static PlaceholderExpansion placeholderExpansion;

    /**
     *
     * @return
     */
    public static boolean BuildEconomy(){
        return false;
    }

    /**
     * 进行papi变量引用
     * @return 是否成功
     */
    public static boolean BuildPlaceHolderExpansion(){
        placeholderExpansion = new StrengthPlusPlaceHolderExpansion();
        return placeholderExpansion.register();
    }
}
