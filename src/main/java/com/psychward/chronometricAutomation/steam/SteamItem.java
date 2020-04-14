package com.psychward.chronometricAutomation.steam;

import com.psychward.chronometricAutomation.ChronometricAutomation;
import net.minecraft.item.ItemStack;

public interface SteamItem {

    /**
     * Good place to sort out steam mechanics. 1 unit of steam = 1600
     */
    public static final int UNIT = Steam.UNIT;

    /**
     * The key to store steam amount
     */
    public static final String TAG_KEY = ChronometricAutomation.MOD_ID + "steam";

    /**
     * Returns the amount of steam in the tank linked to the given itemstack
     */
    public int getSteamAmount(ItemStack stack);

    /**
     * Sets the amount of steam in the tank linked to the given itemstack
     */
    public void setSteamAmount(ItemStack stack, int amount);

    /**
     * Returns the maximum amount of steam in the tank linked to the given itemstack
     */
    public int getMaxSteamAmount(ItemStack stack);

    default public void validateSteam(ItemStack stack) {
        int steam = getSteamAmount(stack);
        setSteamAmount(stack, 0);
        addSteam(stack, steam);
    }

    /**
     * Gets the pressure for the steam tank linked to the given side
     */
    default public float getPressure(ItemStack stack) {
        return getSteamAmount(stack) / (float) getMaxSteamAmount(stack);
    }

    /**
     * Gets the pressure for the steam tank linked to the given side in PSB
     */
    default public int getPressurePSB(ItemStack stack) {
        return (int) (getPressure(stack) * 100.0f);
    }

    /**
     * Calls add or remove as needed
     */
    default public int addOrRemoveSteam(ItemStack stack, int amount) {
        if (amount > 0) {
            return addSteam(stack, amount);
        } else {
            return removeSteam(stack, amount * -1) * -1;
        }
    }

    /**
     * Adds steam to the tank linked to the given itemstack
     */
    default public int addSteam(ItemStack stack, int amount) {
        if (amount + getSteamAmount(stack) <= getMaxSteamAmount(stack)) {
            setSteamAmount(stack, amount + getSteamAmount(stack));
            return amount;
        } else {
            int acceptedAmount = getMaxSteamAmount(stack) - getSteamAmount(stack);
            setSteamAmount(stack, getMaxSteamAmount(stack));
            return acceptedAmount;
        }
    }

    /**
     * Removes steam from the tank linked to the given itemstack
     */
    default public int removeSteam(ItemStack stack, int amount) {
        if (getSteamAmount(stack) - amount < 0) {
            int removedAmount = getSteamAmount(stack);
            setSteamAmount(stack, 0);
            return removedAmount;
        } else {
            setSteamAmount(stack, getSteamAmount(stack) - amount);
            return amount;
        }
    }
}
