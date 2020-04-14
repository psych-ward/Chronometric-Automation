package com.psychward.chronometricAutomation.steam;

import net.minecraft.util.Direction;

public interface Steam {
    /**
     * Good place to sort out steam mechanics. 1 unit of steam = 1600
     */
    public static final int UNIT = 1600;

    /**
     * Returns the amount of steam in the tank linked to the given facing
     */
    public int getSteamAmount(Direction dir);

    /**
     * Sets the amount of steam in the tank linked to the given facing
     */
    public void setSteamAmount(Direction dir, int amount);

    /**
     * Returns the maximum amount of steam in the tank linked to the given facing
     */
    public int getMaxSteamAmount(Direction dir);

    /**
     * If a pipe can visually connect to a given side
     */
    default public boolean canPipeConnect(Direction dir) {
        return true;
    }

    /**
     * Gets the pressure for the steam tank linked to the given side
     */
    default public float getPressure(Direction dir) {
        return getSteamAmount(dir) / (float) getMaxSteamAmount(dir);
    }

    /**
     * Gets the pressure for the steam tank linked to the given side in PSB
     */
    default public int getPressurePSB(Direction dir) {
        return (int) (getPressure(dir) * 100.0f);
    }

    /**
     * Only used by things that display steam to the player. Override for special
     * handling for those things
     */
    default public int getPressurePSBForReadout(Direction dir) {
        return getPressurePSB(dir);
    }

    /**
     * Calls add or remove as needed
     */
    default public int addOrRemoveSteam(Direction dir, int amount) {
        if (amount > 0) {
            return addSteam(dir, amount);
        } else {
            return removeSteam(dir, amount * -1) * -1;
        }
    }

    /**
     * Adds steam to the tank linked to the given facing
     */
    default public int addSteam(Direction dir, int amount) {
        if (amount + getSteamAmount(dir) <= getMaxSteamAmount(dir)) {
            setSteamAmount(dir, amount + getSteamAmount(dir));
            return amount;
        } else {
            int acceptedAmount = getMaxSteamAmount(dir) - getSteamAmount(dir);
            setSteamAmount(dir, getMaxSteamAmount(dir));
            return acceptedAmount;
        }
    }

    /**
     * Removes steam from the tank linked to the given facing
     */
    default public int removeSteam(Direction dir, int amount) {
        if (getSteamAmount(dir) - amount < 0) {
            int removedAmount = getSteamAmount(dir);
            setSteamAmount(dir, 0);
            return removedAmount;
        } else {
            setSteamAmount(dir, getSteamAmount(dir) - amount);
            return amount;
        }
    }
}
