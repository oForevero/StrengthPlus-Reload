package top.mccat.domain;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author Raven
 * @date 2022/05/06 17:59
 * 强化石对象
 */
public class StrengthStone {
    private Material stoneMaterial;
    private List<String> lore;
    private boolean isSafe;
    private boolean isSuccess;
    private boolean isAdmin;
    private Integer strengthExtra;
    public StrengthStone() {
    }

    public StrengthStone(Material stoneName, List<String> lore, boolean isSafe, boolean isSuccess, boolean isAdmin, Integer strengthExtra) {
        this.stoneMaterial = stoneName;
        this.lore = lore;
        this.isSafe = isSafe;
        this.isSuccess = isSuccess;
        this.isAdmin = isAdmin;
        this.strengthExtra = strengthExtra;
    }

    public Material getStoneMaterial() {
        return stoneMaterial;
    }

    public void getStoneMaterial(String stoneName) {
        this.stoneMaterial = Material.getMaterial(stoneName);
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Integer getStrengthExtra() {
        return strengthExtra;
    }

    public void setStrengthExtra(Integer strengthExtra) {
        this.strengthExtra = strengthExtra;
    }

    @Override
    public String toString() {
        return "StrengthStone{" +
                "stoneName='" + stoneMaterial + '\'' +
                ", lore=" + lore +
                ", isSafe=" + isSafe +
                ", isSuccess=" + isSuccess +
                ", isAdmin=" + isAdmin +
                ", strengthExtra=" + strengthExtra +
                '}';
    }
}
