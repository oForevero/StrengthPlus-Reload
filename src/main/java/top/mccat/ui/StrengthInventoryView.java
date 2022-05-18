package top.mccat.ui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import top.mccat.ui.inventory.StrengthChestInventory;

/**
 * @author Raven
 * @date 2022/05/05 21:19
 * 强化菜单
 */
public class StrengthInventoryView extends InventoryView {
    private Player player;
    private final StrengthChestInventory chestInventory = new StrengthChestInventory();


    @Override
    public Inventory getTopInventory() {
        return chestInventory;
    }

    @Override
    public Inventory getBottomInventory() {
        return player.getInventory();
    }

    @Override
    public HumanEntity getPlayer() {
        return player;
    }

    @Override
    public InventoryType getType() {
        return InventoryType.CHEST;
    }

    @Override
    public String getTitle() {
        return null;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
