package top.mccat.ui.inventory;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Distance
 * @date 2022/5/17 17:56
 */
public class StrengthChestInventory implements DoubleChestInventory {

    @Override
    public Inventory getLeftSide() {
        return null;
    }

    @Override
    public Inventory getRightSide() {
        return null;
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public int getMaxStackSize() {
        return 0;
    }

    @Override
    public void setMaxStackSize(int i) {

    }

    @Override
    public ItemStack getItem(int i) {
        return null;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {

    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... itemStacks) throws IllegalArgumentException {
        return null;
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... itemStacks) throws IllegalArgumentException {
        return null;
    }

    @Override
    public ItemStack[] getContents() {
        return new ItemStack[0];
    }

    @Override
    public void setContents(ItemStack[] itemStacks) throws IllegalArgumentException {

    }

    @Override
    public ItemStack[] getStorageContents() {
        return new ItemStack[0];
    }

    @Override
    public void setStorageContents(ItemStack[] itemStacks) throws IllegalArgumentException {

    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean contains(ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean contains(Material material, int i) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean contains(ItemStack itemStack, int i) {
        return false;
    }

    @Override
    public boolean containsAtLeast(ItemStack itemStack, int i) {
        return false;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        return null;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack itemStack) {
        return null;
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public int first(ItemStack itemStack) {
        return 0;
    }

    @Override
    public int firstEmpty() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {

    }

    @Override
    public void remove(ItemStack itemStack) {

    }

    @Override
    public void clear(int i) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<HumanEntity> getViewers() {
        return null;
    }

    @Override
    public InventoryType getType() {
        return null;
    }

    @Override
    public DoubleChest getHolder() {
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return null;
    }

    @Override
    public ListIterator<ItemStack> iterator(int i) {
        return null;
    }

    @Override
    public Location getLocation() {
        return null;
    }
}
