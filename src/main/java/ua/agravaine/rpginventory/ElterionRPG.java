package ua.agravaine.rpginventory;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import ua.agravaine.rpginventory.armor.MorgulArmor;
import ua.agravaine.rpginventory.armor.explorer_glasses;
import ua.agravaine.rpginventory.armor.reinforcedArmor;
import ua.agravaine.rpginventory.block.bush;
import ua.agravaine.rpginventory.event.EventsClient;
import ua.agravaine.rpginventory.event.RpgInvEventListener;
import ua.agravaine.rpginventory.item.EnumBaubleType;
import ua.agravaine.rpginventory.item.ItemAmulet;
import ua.agravaine.rpginventory.item.ItemBackpack;
import ua.agravaine.rpginventory.item.ItemBaubleArmor;
import ua.agravaine.rpginventory.item.ItemHealtRing;
import ua.agravaine.rpginventory.item.ItemPat;
import ua.agravaine.rpginventory.item.ItemSpeedRing;
import ua.agravaine.rpginventory.item.ItemStrengthRing;
import ua.agravaine.rpginventory.item.random_amulet;
import ua.agravaine.rpginventory.item.random_ringOfHealt;
import ua.agravaine.rpginventory.item.random_ringOfSpeed;
import ua.agravaine.rpginventory.item.random_ringOfStrength;
import ua.agravaine.rpginventory.network.NetworkManager;
import ua.agravaine.rpginventory.network.RpgInvGuiHandler;
import ua.agravaine.rpginventory.network.RpgInvPacketHandler;
import ua.agravaine.rpginventory.proxy.CommonProxy;
import ua.agravaine.rpginventory.weapon.dwarven_axe_lg;
import ua.agravaine.rpginventory.weapon.dwarven_axe_rr;
import ua.agravaine.rpginventory.weapon.dwarven_axe_st;
import ua.agravaine.rpginventory.weapon.sword_bastard_lg;
import ua.agravaine.rpginventory.weapon.sword_bastard_rr;
import ua.agravaine.rpginventory.weapon.sword_bastard_st;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


	@Mod(modid = ElterionRPG.MOD_ID, name = ElterionRPG.MOD_NAME, version = ElterionRPG.MOD_VERSION)
	public class ElterionRPG {

		@Instance(ElterionRPG.MOD_ID)
		public static ElterionRPG instance;

		public static final String MOD_ID = "ElterionRPG";
		public static final String MOD_NAME = "Elterion RPG";
		public static final String MOD_VERSION = "1.0";

		public static final Logger logger = LogManager.getLogger(MOD_NAME);

		public static NetworkManager network;

		@SidedProxy(clientSide = "ua.agravaine.rpginventory.proxy.ClientProxy", serverSide = "ua.agravaine.rpginventory.proxy.CommonProxy")
	    public static CommonProxy proxy;
		
		public static Item pat;
		public static Item amulet;
		public static Item ringOfHealt;
		public static Item ringOfSpeed;
		public static Item ringOfStrength;
		public static Item backpack;
		public static Item shoulder;
		public static Item glove;
		public static Item random_amulet;
		public static Item random_ringOfSpeed;
		public static Item random_ringOfStrength;
		public static Item random_ringOfHealt;
		public static CreativeTabs RpgTab = new RpgTab("Mod_ID");
		public static CreativeTabs RpgItemTab = new RpgItemTab("Mod_ID");
		public static Item Gold_coin;
		public static Item Silver_coin;
		public static Item Bronze_coin;
		public static Item dwarven_axe_st;
		public static Item dwarven_axe_rr;
		public static Item dwarven_axe_lg;
		public static Item sword_bastard_st;
		public static Item sword_bastard_rr;
		public static Item sword_bastard_lg;
		
		public static Item termite;
		public static Item rope;
		public static Item goldRing;
		public static Item corn;
		public static Item raspberry;
		public static Item gemsbokHide;
		public static Item turnip;
		public static Item cornStalk;
		public static Item ceramicMug;
		public static Item cherry;
		public static Item cherryPie;
		
		public static Item helmetMorgul;
		public static Item reinforcedhelmet;
		public static Item reinforcedplate;
		public static Item reinforcedpants;
		public static Item reinforcedboots;
		public static Item explorer_glasses;
		
		public static Block bush;





		@EventHandler
		public void preInit(FMLPreInitializationEvent event){	
			proxy.preInit(event);
			logger.info("Loading...");
			logger.info("Create network system.");
			network = NetworkManager.registerPacketHandler(new RpgInvPacketHandler());
			logger.info("Create items.");
			Display.setTitle("TinyProject");
			amulet = new ItemAmulet("amulet", "standart", "rare", "legendary", "superLegendary");
			ringOfHealt = new ItemHealtRing("ringOfHealt", "standart", "rare", "legendary");
			ringOfSpeed = new ItemSpeedRing("ringOfSpeed", "standart", "rare", "legendary");
			ringOfStrength = new ItemStrengthRing("ringOfStrength", "standart", "rare", "legendary");
			backpack = new ItemBackpack("backpack");
			pat = new ItemPat("dog");
			shoulder = new ItemBaubleArmor("shoulder", EnumBaubleType.SHOULDER, "cloth", "chain", "iron", "diamond");
			glove = new ItemBaubleArmor("glove", EnumBaubleType.GLOVE, "cloth", "chain", "iron", "diamond");
			random_amulet = new random_amulet();GameRegistry.registerItem(random_amulet, "random_amulet");
			random_ringOfSpeed = new random_ringOfSpeed();GameRegistry.registerItem(random_ringOfSpeed, "random_ringOfSpeed");
			random_ringOfStrength = new random_ringOfStrength();GameRegistry.registerItem(random_ringOfStrength, "random_ringOfStrength");
			random_ringOfHealt = new random_ringOfHealt();GameRegistry.registerItem(random_ringOfHealt, "random_ringOfHealt");
			
		
			
			Gold_coin = new Item().setUnlocalizedName("Gold_coin").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:Gold_coin");GameRegistry.registerItem(Gold_coin, "Gold_coin");
			Silver_coin = new Item().setUnlocalizedName("Silver_coin").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:Silver_coin");GameRegistry.registerItem(Silver_coin, "Silver_coin");
			Bronze_coin = new Item().setUnlocalizedName("Bronze_coin").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:Bronze_coin");GameRegistry.registerItem(Bronze_coin, "Bronze_coin");
			
			termite = new Item().setUnlocalizedName("termite").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:termite");GameRegistry.registerItem(termite, "termite");
			rope = new Item().setUnlocalizedName("rope").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:rope");GameRegistry.registerItem(rope, "rope");
			goldRing = new Item().setUnlocalizedName("goldRing").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:goldRing");GameRegistry.registerItem(goldRing, "goldRing");
			gemsbokHide = new Item().setUnlocalizedName("gemsbokHide").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:gemsbokHide");GameRegistry.registerItem(gemsbokHide, "gemsbokHide");
			ceramicMug = new Item().setUnlocalizedName("ceramicMug").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:ceramicMug");GameRegistry.registerItem(ceramicMug, "ceramicMug");
			cornStalk = new Item().setUnlocalizedName("cornStalk").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:cornStalk");GameRegistry.registerItem(cornStalk, "cornStalk");

			turnip = new ItemFood(3, false).setUnlocalizedName("turnip").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:turnip");GameRegistry.registerItem(turnip, "turnip");
			raspberry = new ItemFood(1, false).setUnlocalizedName("raspberry").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:raspberry");GameRegistry.registerItem(raspberry, "raspberry");
			corn = new ItemFood(2, false).setUnlocalizedName("corn").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:corn");GameRegistry.registerItem(corn, "corn");
			cherry = new ItemFood(1, false).setUnlocalizedName("cherry").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:cherry");GameRegistry.registerItem(cherry, "cherry");
			cherryPie = new ItemFood(5, false).setUnlocalizedName("cherryPie").setCreativeTab(ElterionRPG.RpgItemTab).setTextureName("elterionrpg:cherryPie").setMaxStackSize(4);GameRegistry.registerItem(cherryPie, "cherryPie");
		
			dwarven_axe_st = new dwarven_axe_st(null);GameRegistry.registerItem(dwarven_axe_st, "dwarven_axe_st");
			dwarven_axe_rr = new dwarven_axe_rr(null);GameRegistry.registerItem(dwarven_axe_rr, "dwarven_axe_rr");
			dwarven_axe_lg = new dwarven_axe_lg(null);GameRegistry.registerItem(dwarven_axe_lg, "dwarven_axe_lg");
			sword_bastard_st = new sword_bastard_st(null);GameRegistry.registerItem(sword_bastard_st, "sword_bastard_st");
			sword_bastard_rr = new sword_bastard_rr(null);GameRegistry.registerItem(sword_bastard_rr, "sword_bastard_rr");
			sword_bastard_lg = new sword_bastard_lg(null);GameRegistry.registerItem(sword_bastard_lg, "sword_bastard_lg");
			
			logger.info("Register gui handler.");
			NetworkRegistry.INSTANCE.registerGuiHandler(instance, new RpgInvGuiHandler());
			logger.info("Register event listener.");
			RpgInvEventListener listener = new RpgInvEventListener();
			MinecraftForge.EVENT_BUS.register(listener);

			FMLCommonHandler.instance().bus().register(listener);
			logger.info("Successfully loaded!");
	}
		
		   @SideOnly(Side.CLIENT)
		   @Mod.EventHandler
		    public void preInitClient(FMLPreInitializationEvent event) {
		        EventsClient e = new EventsClient();
		        MinecraftForge.EVENT_BUS.register(e);
		        FMLCommonHandler.instance().bus().register(e);
		    }
		
		  @EventHandler
		    public void init(FMLInitializationEvent event) {
		        proxy.init();
		    }
		   
		   @EventHandler
			public void preLoad(FMLPreInitializationEvent event){
			   helmetMorgul = new MorgulArmor(null, 0, 0).setUnlocalizedName("helmetMorgul").setTextureName("elterionrpg:helmetMorgul");
			   reinforcedhelmet = new reinforcedArmor(null, 0, 0).setUnlocalizedName("reinforcedhelmet").setTextureName("elterionrpg:reinforcedhelmet");
			   reinforcedplate = new reinforcedArmor(null, 0, 1).setUnlocalizedName("reinforcedplate").setTextureName("elterionrpg:reinforcedplate");
			   reinforcedpants  = new reinforcedArmor(null, 0,2).setUnlocalizedName("reinforcedpants").setTextureName("elterionrpg:reinforcedpants");
			   reinforcedboots = new reinforcedArmor(null, 0, 3).setUnlocalizedName("reinforcedboots").setTextureName("elterionrpg:reinforcedboots");
			   explorer_glasses = new explorer_glasses(null, 0, 0).setCreativeTab(ElterionRPG.RpgTab).setTextureName("elterionrpg:explorer_glasses").setUnlocalizedName("explorer_glasses").setTextureName("elterionrpg:explorer_glasses");
			   
			   GameRegistry.registerItem(explorer_glasses, " explorer_glasses");
			   GameRegistry.registerItem(helmetMorgul, " helmetMorgul");
			   GameRegistry.registerItem(reinforcedhelmet, " reinforcedhelmet");
			   GameRegistry.registerItem(reinforcedplate, "reinforcedplate");
			   GameRegistry.registerItem(reinforcedpants, "reinforcedpants");
			   GameRegistry.registerItem(reinforcedboots, "reinforcedboots");
			   
			   bush = new bush().setBlockTextureName("bush").setBlockTextureName("elterionrpg:bush");GameRegistry.registerBlock(bush, "bush");

		   }
	}

