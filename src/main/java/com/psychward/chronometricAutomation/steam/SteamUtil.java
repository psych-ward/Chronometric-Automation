package com.psychward.chronometricAutomation.steam;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SteamUtil {
    public static void equalizeSteam(World world, Steam source, BlockPos pos, Direction side) {
        for (int i = 0; i < Direction.values().length; i++) {
            directionalEqualizeSteam(world, source, pos, side, Direction.values()[i]);
        }
    }

    public static void directionalEqualizeSteam(World world, Steam source, BlockPos pos, Direction side, Direction out) {
        BlockState be = world.getBlockState(pos.offset(out));
        if (be instanceof Steam) {
            float outFraction = ((Steam) be).getPressure(out.getOpposite());
            float sourceFraction = source.getPressure(side);
            float totalFraction = (sourceFraction - outFraction);
            if (totalFraction > 0) {
                ((Steam) be).addSteam(out.getOpposite(),
                        source.removeSteam(side, (int) (source.getSteamAmount(side) * totalFraction)));
            } else {
                source.addSteam(side, ((Steam) be).removeSteam(out.getOpposite(),
                        (int) (((Steam) be).getSteamAmount(side) * -totalFraction)));
            }
        }
    }
}
