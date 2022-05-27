package top.mccat.factory;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
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
        return null;
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
            default:
                return null;
        }
    }
}
