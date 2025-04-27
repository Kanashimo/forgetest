//package com.example.examplemod;
//
//import net.minecraft.init.Blocks;
//import cpw.mods.fml.common.Mod;
//import cpw.mods.fml.common.Mod.EventHandler;
//import cpw.mods.fml.common.event.FMLInitializationEvent;
//
//@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
//public class ExampleMod
//{
//    public static final String MODID = "examplemod";
//    public static final String VERSION = "1.0";
//
//    @EventHandler
//    public void init(FMLInitializationEvent event)
//    {
//		// some example code
//        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
//    }
//}

package com.example.examplemod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";

    private boolean hasSentMessage = false;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft mc = Minecraft.getMinecraft();

            if (mc.thePlayer != null && mc.theWorld != null && !hasSentMessage) {
                mc.thePlayer.addChatMessage(new ChatComponentText("Hello, World!"));
                hasSentMessage = true;
            }

            if (mc.theWorld == null) {
                hasSentMessage = false; // Reset when leaving world
            }
        }
    }
}
