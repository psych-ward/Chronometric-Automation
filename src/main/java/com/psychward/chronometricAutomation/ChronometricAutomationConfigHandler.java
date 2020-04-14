package com.psychward.chronometricAutomation;

import net.minecraftforge.common.ForgeConfigSpec;

public class ChronometricAutomationConfigHandler {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    static final General GENERAL = new General(BUILDER);
    static final ForgeConfigSpec spec = BUILDER.build();

    public static class General {
        General(ForgeConfigSpec.Builder builder) {
            builder.push("General");

            builder.pop();
        }
    }
}
