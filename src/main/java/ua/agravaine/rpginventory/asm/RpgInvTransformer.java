package ua.agravaine.rpginventory.asm;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import ua.agravaine.hooklib.asm.Hook;
import ua.agravaine.hooklib.asm.ReturnCondition;
import ua.agravaine.hooklib.minecraft.HookLoader;
import ua.agravaine.rpginventory.entity.RpgPlayer;
import ua.agravaine.rpginventory.ElterionRPG;

@MCVersion("1.7.10")
@Name(ElterionRPG.MOD_ID)
@TransformerExclusions({"ua.agravaine.rpginventory.asm"})
public class RpgInvTransformer extends HookLoader{

	public void registerHooks(){
		registerHookContainer("ua.agravaine.rpginventory.asm.RpgInventoryHooks");
	}
}