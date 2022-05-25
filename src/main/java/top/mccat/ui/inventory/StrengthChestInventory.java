package top.mccat.ui.inventory;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * @author Distance
 * @date 2022/5/17 17:56
 */
public class StrengthChestInventory implements DoubleChestInventory {
    private int maxStackSize;
    private List<ItemStack> itemStacks;
    private final ItemStack airStack = new ItemStack(Material.AIR);

    public StrengthChestInventory(){
        itemStacks = new ArrayList<>();
        itemStacks.add(airStack);
    }

    public StrengthChestInventory(List<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
    }

    /**
     * 未使用
     */
    @Override
    public Inventory getLeftSide() {
        return null;
    }

    /**
     * 未使用
     */
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
        return maxStackSize;
    }

    @Override
    public void setMaxStackSize(int size) {
        maxStackSize = size;
    }

    @Override
    public ItemStack getItem(int i) {
        return itemStacks.get(i);
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        itemStacks.set(i,itemStack);
    }

    /**
     * 未使用
     */
    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... itemStacks) throws IllegalArgumentException {
        return null;
    }

    /**
     * 未使用
     */
    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... itemStacks) throws IllegalArgumentException {
        return null;
    }

    @Override
    public ItemStack[] getContents() {
        ItemStack[] itemStackArray = new ItemStack[54];
        return itemStacks.toArray(itemStackArray);
    }

    @Override
    public void setContents(ItemStack[] itemStacks) throws IllegalArgumentException {
        this.itemStacks = Arrays.asList(itemStacks);
    }

    @Override
    public ItemStack[] getStorageContents() {
        ItemStack[] stacks;
        if(!itemStacks.contains(airStack)){
            stacks = new ItemStack[0];
            return stacks;
        }else {
            List<ItemStack> stackList = new ArrayList<>();
            for(ItemStack stack : itemStacks){
                if (stack.getType() == Material.AIR){
                    stackList.add(stack);
                }
            }
            stacks = new ItemStack[stackList.size()];
            return stackList.toArray(stacks);
        }
    }

    @Override
    public void setStorageContents(ItemStack[] itemStacks) throws IllegalArgumentException {

    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        ItemStack stack = new ItemStack(material);
        if(itemStacks.contains(stack)){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(ItemStack itemStack) {
        return contains(itemStack.getType());
    }

    @Override
    public boolean contains(Material material, int amount) throws IllegalArgumentException {
        ItemStack stack = new ItemStack(material);
        if(itemStacks.contains(stack)){
            if(stack.getAmount() == amount){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(ItemStack itemStack, int amount) {
        return contains(itemStack.getType(),amount);
    }

    @Override
    public boolean containsAtLeast(ItemStack itemStack, int i) {
        return false;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        HashMap<Integer, ItemStack> map = new HashMap<>(54);
        for(int i = 0; i < itemStacks.size(); i++){
            ItemStack stack = itemStacks.get(i);
            if(stack.getType() == material) {
                map.put(i,stack);
            }
        }
        return map;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack itemStack) {
        HashMap<Integer, ItemStack> map = new HashMap<>(54);
        for(int i = 0; i < itemStacks.size(); i++){
            ItemStack stack = itemStacks.get(i);
            if(stack.equals(itemStack)) {
                map.put(i,stack);
            }
        }
        return map;
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        for(int i = 0; i < itemStacks.size(); i++){
            ItemStack stack = itemStacks.get(i);
            if(stack.getType() == material){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int first(ItemStack itemStack) {
        for(int i = 0; i < itemStacks.size(); i++){
            ItemStack stack = itemStacks.get(i);
            if(stack.equals(itemStack)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int firstEmpty() {
        for(int i = 0; i < itemStacks.size(); i++){
            ItemStack stack = itemStacks.get(i);
            if(stack.equals(airStack)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if(itemStacks == null || itemStacks.size()==0) {
            return true;
        }
        return false;
    }

    /**
     * 未使用
     */
    @Override
    public void remove(Material material) throws IllegalArgumentException {

    }

    /**
     * 未使用
     */
    @Override
    public void remove(ItemStack itemStack) {

    }

    @Override
    public void clear(int i) {
        itemStacks.set(i, airStack);
    }

    @Override
    public void clear() {
        itemStacks.clear();
    }

    @Override
    public List<HumanEntity> getViewers() {
        return null;
    }

    @Override
    public InventoryType getType() {
        return InventoryType.CHEST;
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

    public void setItemStacks(List<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
    }
}
