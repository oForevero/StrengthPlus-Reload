package top.mccat.factory;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Distance
 * @date 2022/5/27 11:27
 * Papi变量对象
 */
public class StrengthPlusPlaceHolderExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "strengthplus";
    }

    @Override
    public @NotNull String getAuthor() {
        return "raven";
    }

    @Override
    public @NotNull String getVersion() {
        return "2.1.0-Alpha";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        switch (params){
            case "item":
                if(player == null){
                    break;
                }
                if(!(player.getPlayer() instanceof Player)){
                    break;
                }
                InventoryView openInventory = player.getPlayer().getOpenInventory();
                if(openInventory == null){
                    break;
                }
                ItemStack item = openInventory.getItem(19);

                if(item == null || item.getType() == Material.AIR){
                    return "";
                }
                ItemMeta meta = item.getItemMeta();
                if(meta.hasDisplayName()){
                    return meta.getDisplayName();
                }else{
                    return "装备";
                }
            case "player_name":
                return player.getPlayer().getName();
            default:
                return "undefined";
        }
        return null;
    }
}
