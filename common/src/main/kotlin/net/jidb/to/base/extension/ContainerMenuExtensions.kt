package net.jidb.to.base.extension

import net.minecraft.world.Container
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import kotlin.math.min

fun AbstractContainerMenu.addPlayerSlots(inventory: Inventory, x: Int, y: Int, adder: (slot: Slot) -> Unit) {
    addSlots(inventory, x, y, 9, 3, adder, start = 9)
    addSlots(inventory, x, y + 58, 9, 1, adder, start = 0)
}

fun AbstractContainerMenu.addSlots(container: Container, x: Int, y: Int, w: Int, h: Int, adder: (slot: Slot) -> Unit, start: Int = 0, creator: (container: Container, index: Int, x: Int, y: Int) -> Slot = { a, b, c, d -> Slot(a, b, c, d) }) {
    for (j in 0 until h) {
        for (i in 0 until w) {
            adder(creator(container, start.plus(i).plus(j.times(w)), x + i.times(18), y + j.times(18)))
        }
    }
}

fun AbstractContainerMenu.moveItemStackToMaxStack(stack: ItemStack, startIndex: Int, endIndex: Int, fromLast: Boolean): Boolean {
    var bl = false
    var i: Int = startIndex
    if (fromLast) {
        i = endIndex - 1
    }

    var slot2: Slot
    var itemStack: ItemStack
    if (stack.isStackable) {
        while (!stack.isEmpty) {
            if (fromLast) {
                if (i < startIndex) {
                    break
                }
            } else if (i >= endIndex) {
                break
            }
            slot2 = slots[i]
            itemStack = slot2.item
            if (!itemStack.isEmpty && ItemStack.isSameItemSameTags(stack, itemStack)) {
                val j: Int = itemStack.count + stack.count
                val maxCount = min(stack.maxStackSize, slot2.getMaxStackSize(stack))
                if (j <= maxCount) {
                    stack.count = 0
                    itemStack.count = j
                    slot2.setChanged()
                    bl = true
                } else if (itemStack.count < maxCount) {
                    stack.shrink(maxCount - itemStack.count)
                    itemStack.count = maxCount
                    slot2.setChanged()
                    bl = true
                }
            }
            if (fromLast) {
                --i
            } else {
                ++i
            }
        }
    }

    if (!stack.isEmpty) {
        if (fromLast) {
            i = endIndex - 1
        } else {
            i = startIndex
        }
        while (true) {
            if (fromLast) {
                if (i < startIndex) {
                    break
                }
            } else if (i >= endIndex) {
                break
            }
            slot2 = slots[i]
            itemStack = slot2.item
            if (itemStack.isEmpty && slot2.mayPlace(stack)) {
                if (stack.count > slot2.maxStackSize) {
                    slot2.setByPlayer(itemStack.split(slot2.maxStackSize))
                } else {
                    slot2.setByPlayer(itemStack.split(itemStack.count))
                }
                slot2.setChanged()
                bl = true
                break
            }
            if (fromLast) {
                --i
            } else {
                ++i
            }
        }
    }

    return bl
}