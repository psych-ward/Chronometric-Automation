package com.psychward.chronometricAutomation.init;

import com.psychward.chronometricAutomation.ChronometricAutomation;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ChronometricAutomation.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(ChronometricAutomation.MOD_ID)
public class BlockInit {

    public static final Block steam_generator = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry()
                .register(
                        new Block(Block.Properties
                                .create(Material.IRON)
                                .hardnessAndResistance(2.7f, 15f)
                                .sound(SoundType.METAL)
                        ).setRegistryName("steam_generator")
                );
    }

    @SubscribeEvent
    public static void RegisterBlockItems(final RegistryEvent.Register<Item> event){
        event.getRegistry()
                .register(
                        new BlockItem(steam_generator, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("steam_generator")
                );
    }
}
