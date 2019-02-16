package ua.agravaine.rpginventory.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import ua.agravaine.rpginventory.ElterionRPG;
import ua.agravaine.rpginventory.event.DamageEvent;
import ua.agravaine.rpginventory.event.EventPat;
import ua.agravaine.rpginventory.event.EventsArmor;
import ua.agravaine.rpginventory.hud.Hud;
import ua.agravaine.rpginventory.inventory.GuiInv_stats;
import ua.agravaine.rpginventory.model.RenderItemBastard;

public class ClientProxy extends CommonProxy{

	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new Hud());
		 FMLCommonHandler.instance().bus().register(new GuiInv_stats());
		 MinecraftForge.EVENT_BUS.register(new DamageEvent());
		 MinecraftForge.EVENT_BUS.register(new EventsArmor());
		 MinecraftForge.EVENT_BUS.register(new EventPat());
		 
        super.preInit(event);
    }
	
	
	public void init() {
		 super.init();
		 MinecraftForgeClient.registerItemRenderer(ElterionRPG.sword_bastard_st, new RenderItemBastard());
		MinecraftForgeClient.registerItemRenderer(ElterionRPG.sword_bastard_rr, new RenderItemBastard());
		MinecraftForgeClient.registerItemRenderer(ElterionRPG.sword_bastard_lg, new RenderItemBastard());

	}
	
}
