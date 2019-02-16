package ua.agravaine.rpginventory.asm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import ua.agravaine.hooklib.asm.Hook;
import ua.agravaine.hooklib.asm.Hook.ReturnValue;
import ua.agravaine.hooklib.asm.ReturnCondition;
import ua.agravaine.rpginventory.entity.RpgPlayer;
import ua.agravaine.rpginventory.item.IBauble;
import ua.agravaine.rpginventory.item.IProtectableBauble;

public class RpgInventoryHooks {

	@Hook(targetMethod = "func_146977_a")
	public static void fixSlotLightingPre(GuiContainer gui, Slot slot){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Hook(targetMethod = "func_147044_g")
	public static void fixEffectsRendererPre(InventoryEffectRenderer gui){
		if(!gui.mc.thePlayer.getActivePotionEffects().isEmpty()){
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		}
	}

	@Hook(targetMethod = "func_147044_g", injectOnExit = true)
	public static void fixEffectsRendererPost(InventoryEffectRenderer gui){
		if(!gui.mc.thePlayer.getActivePotionEffects().isEmpty()){
			GL11.glDisable(GL11.GL_BLEND);
		}
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void onPlayerStoppedUsing(ItemBow bow, ItemStack itemStack, World world, EntityPlayer player, int duration){
		int charge = bow.getMaxItemUseDuration(itemStack) - duration;

		ArrowLooseEvent event = new ArrowLooseEvent(player, itemStack, charge);
		MinecraftForge.EVENT_BUS.post(event);
		if(event.isCanceled()){
			return;
		}
		charge = event.charge;

		ItemStack arrow = player.inventory.getStackInSlot(37);
		boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemStack) > 0;

		if(flag || (arrow != null && arrow.getItem() == Items.arrow)){
			float f = charge / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if(f < 0.1D){
				return;
			}

			if(f > 1.0F){
				f = 1.0F;
			}

			EntityArrow entityarrow = new EntityArrow(world, player, f * 2.0F);

			if(f == 1.0F){
				entityarrow.setIsCritical(true);
			}

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);

			if(k > 0){
				entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D);
			}

			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);

			if(l > 0){
				entityarrow.setKnockbackStrength(l);
			}

			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemStack) > 0){
				entityarrow.setFire(100);
			}

			itemStack.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (world.rand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if(flag){
				entityarrow.canBePickedUp = 2;
			}else{
				if(--arrow.stackSize <= 0){
					player.inventory.setInventorySlotContents(37, null);
				}
			}

			if(!world.isRemote){
				world.spawnEntityInWorld(entityarrow);
			}
		}
	}

	// @Hook(returnCondition = ReturnCondition.ALWAYS)
	// public static boolean consumeInventoryItem(InventoryPlayer inventory, Item item){
	// int slot = -1;
	// try{
	// slot = (int)ReflectionHelper.findMethod(InventoryPlayer.class, inventory, new String[]{"func_146029_c"}, Item.class).invoke(inventory, item);
	// }catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
	// e.printStackTrace();
	// }
	//
	// if(slot < 0){
	// return false;
	// }else{
	// if(--getSlotByIndex(inventory, slot).stackSize <= 0){
	// setSlotByIndex(inventory, slot, null);
	// }
	//
	// return true;
	// }
	// }
	//
	// @Hook(targetMethod = "func_146029_c", returnCondition = ReturnCondition.ALWAYS, injectOnExit = true)
	// public static int getSlotFromItem(InventoryPlayer inventory, Item item, @Hook.ReturnValue int count){
	// if(count != -1){
	// RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inventory.player);
	// for(int i = 0; i < rpgPlayer.baublesInventory.getSizeInventory(); ++i){
	// ItemStack itemStack = rpgPlayer.baublesInventory.getStackInSlot(i);
	// if(itemStack != null && itemStack.getItem() == item){
	// return i;
	// }
	// }
	// }
	//
	// return -1;
	// }

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static float ApplyArmor(ArmorProperties parent, EntityLivingBase entity, ItemStack[] inventory, DamageSource source, double damage){
		boolean DEBUG = ReflectionHelper.getPrivateValue(ArmorProperties.class, null, "DEBUG");
		if(DEBUG){
			System.out.println("Start: " + damage + " " + (damage * 25));
		}
		if(entity instanceof EntityPlayer){
			RpgPlayer rpgPlayer = RpgPlayer.forPlayer((EntityPlayer)entity);
			ItemStack[] baublesItems = new ItemStack[rpgPlayer.baublesInventory.getSizeInventory()];
			for(int i = 0; i < baublesItems.length; i++){
				baublesItems[i] = rpgPlayer.baublesInventory.getStackInSlot(i);
			}
			inventory = ArrayUtils.addAll(inventory, baublesItems);
		}
		damage *= 25;
		ArrayList<ArmorProperties> dmgVals = new ArrayList<>();
		for(int x = 0; x < inventory.length; x++){
			ItemStack stack = inventory[x];
			if(stack == null){
				continue;
			}
			ArmorProperties prop = null;
			if(stack.getItem() instanceof ISpecialArmor){
				ISpecialArmor armor = (ISpecialArmor)stack.getItem();
				prop = armor.getProperties(entity, stack, source, damage / 25D, x).copy();
			}else if(stack.getItem() instanceof ItemArmor && !source.isUnblockable()){
				ItemArmor armor = (ItemArmor)stack.getItem();
				prop = new ArmorProperties(0, armor.damageReduceAmount / 25D, armor.getMaxDamage() + 1 - stack.getItemDamage());
			}else if(entity instanceof EntityPlayer && stack.getItem() instanceof IProtectableBauble && !source.isUnblockable()){
				IProtectableBauble bauble = (IProtectableBauble)stack.getItem();
				int protection = bauble.getProtection((EntityPlayer)entity, stack, source, damage);
				prop = new ArmorProperties(0, protection / 25D, stack.getMaxDamage() + 1 - stack.getItemDamage());
			}
			if(prop != null){
				prop.Slot = x;
				dmgVals.add(prop);
			}
		}
		if(dmgVals.size() > 0){
			ArmorProperties[] props = dmgVals.toArray(new ArmorProperties[dmgVals.size()]);
			try{
				ReflectionHelper.findMethod(ArmorProperties.class, null, new String[]{"StandardizeList"}, ArmorProperties[].class, double.class).invoke(null, props, damage);
			}catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
				e.printStackTrace();
			}
			// StandardizeList(props, damage);
			int level = props[0].Priority;
			double ratio = 0;
			for(ArmorProperties prop : props){
				if(level != prop.Priority){
					damage -= (damage * ratio);
					ratio = 0;
					level = prop.Priority;
				}
				ratio += prop.AbsorbRatio;

				double absorb = damage * prop.AbsorbRatio;
				if(absorb > 0){
					ItemStack stack = inventory[prop.Slot];
					int itemDamage = (int)(absorb / 25D < 1 ? 1 : absorb / 25D);
					if(stack.getItem() instanceof ISpecialArmor){
						((ISpecialArmor)stack.getItem()).damageArmor(entity, stack, source, itemDamage, prop.Slot);
					}else{
						if(DEBUG){
							System.out.println("Item: " + stack.toString() + " Absorbed: " + (absorb / 25D) + " Damaged: " + itemDamage);
						}
						stack.damageItem(itemDamage, entity);
					}
					if(stack.stackSize <= 0){
						/*
						 * if (entity instanceof EntityPlayer) { stack.onItemDestroyedByUse((EntityPlayer)entity); }
						 */
						inventory[prop.Slot] = null;
					}
				}
			}
			damage -= (damage * ratio);
		}
		if(DEBUG){
			System.out.println("Return: " + (int)(damage / 25.0F) + " " + damage);
		}
		return (float)(damage / 25.0F);
	}

	@Hook(injectOnExit = true, returnCondition = ReturnCondition.ALWAYS)
    public static int getTotalArmorValue(ForgeHooks fh, EntityPlayer player, @ReturnValue int returnValue){
		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(player);
		for(int i = 0; i < rpgPlayer.baublesInventory.getSizeInventory(); i++){
			ItemStack itemStack = rpgPlayer.baublesInventory.getStackInSlot(i);
			if(itemStack != null && itemStack.getItem() instanceof IProtectableBauble){
				returnValue += ((IProtectableBauble)itemStack.getItem()).getDisplayProtection(player, itemStack);
			}
		}
        return returnValue;
    }

	@Hook(injectOnExit = true)
	public static void copyInventory(InventoryPlayer inventory, InventoryPlayer copy){
		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inventory.player);
		RpgPlayer copyPlayer = RpgPlayer.forPlayer(copy.player);
		for(int i = 0; i < rpgPlayer.baublesInventory.getSizeInventory(); i++){
			ItemStack copyStack = ItemStack.copyItemStack(copyPlayer.baublesInventory.getStackInSlot(i));
			rpgPlayer.baublesInventory.setInventorySlotContents(i, copyStack);
		}
	}

	@Hook(injectOnExit = true)
	public static boolean hasItemStack(InventoryPlayer inventory, ItemStack has){
		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inventory.player);
		for(int i = 0; i < rpgPlayer.baublesInventory.getSizeInventory(); i++){
			ItemStack itemStack = rpgPlayer.baublesInventory.getStackInSlot(i);
			if(itemStack != null && itemStack.isItemEqual(has)){
				return true;
			}
		}

		return false;
	}

	@Hook(injectOnExit = true)
	public static void dropAllItems(InventoryPlayer inventory){
		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inventory.player);
		for(int i = 0; i < rpgPlayer.baublesInventory.getSizeInventory(); i++){
			ItemStack itemStack = rpgPlayer.baublesInventory.getStackInSlot(i);
			if(itemStack != null){
				inventory.player.func_146097_a(itemStack, true, false);
				rpgPlayer.baublesInventory.setInventorySlotContents(i, null);
			}
		}
	}

	@Hook(injectOnExit = true)
	public static void decrementAnimations(InventoryPlayer inventory){
		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inventory.player);
		for(int i = 0; i < rpgPlayer.baublesInventory.getSizeInventory(); i++){
			ItemStack itemStack = rpgPlayer.baublesInventory.getStackInSlot(i);
			if(itemStack != null && itemStack.getItem() instanceof IBauble){
				IBauble bauble = (IBauble)itemStack.getItem();
				bauble.onTick(inventory.player, itemStack, i);
			}
		}
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS, injectOnExit = true)
	public static int clearInventory(InventoryPlayer inventory, Item item, int metadata, @Hook.ReturnValue int count){
		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inventory.player);
		for(int i = 0; i < rpgPlayer.baublesInventory.getSizeInventory(); i++){
			ItemStack itemStack = rpgPlayer.baublesInventory.getStackInSlot(i);

			if(itemStack != null && (item == null || itemStack.getItem() == item) && (metadata <= -1 || itemStack.getItemDamage() == metadata)){
				count += itemStack.stackSize;
				rpgPlayer.baublesInventory.setInventorySlotContents(i, null);
			}
		}
		return count;
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static ItemStack decrStackSize(InventoryPlayer inventory, int slot, int count){
		ItemStack itemStack = getSlotByIndex(inventory, slot);

		if(itemStack != null){
			ItemStack itemstack;

			if(itemStack.stackSize <= count){
				setSlotByIndex(inventory, slot, null);
				return itemStack;
			}

			ItemStack value = itemStack.splitStack(count);
			if(itemStack.stackSize == 0){
				setSlotByIndex(inventory, slot, null);
			}
			return value;
		}

		return null;
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static ItemStack getStackInSlotOnClosing(InventoryPlayer inventory, int slot){
		ItemStack value = getSlotByIndex(inventory, slot);
		setSlotByIndex(inventory, slot, null);
		return value;
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void setInventorySlotContents(InventoryPlayer inventory, int slot, ItemStack itemStack){
		setSlotByIndex(inventory, slot, itemStack);
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static ItemStack getStackInSlot(InventoryPlayer inventory, int slot){
		return getSlotByIndex(inventory, slot);
	}

	@Hook(targetMethod = "<init>", injectOnExit = true)
	public static void initPlayerInventory(InventoryPlayer inventory, EntityPlayer player){
		inventory.mainInventory = new ItemStack[29];
	}

	@Hook(injectOnExit = true)
	public static int getSizeInventory(InventoryPlayer inventory, @Hook.ReturnValue int size){
		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inventory.player);
		return size + rpgPlayer.baublesInventory.getSizeInventory();
	}

	@Hook(targetMethod = "writeToNBT", returnCondition = ReturnCondition.ALWAYS)
	public static NBTTagList writePlayerInventoryToNBT(InventoryPlayer inventory, NBTTagList nbt){
		for(int i = 0; i < inventory.mainInventory.length; ++i){
			if(inventory.mainInventory[i] != null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				inventory.mainInventory[i].writeToNBT(tag);
				nbt.appendTag(tag);
			}
		}

		for(int i = 0; i < inventory.armorInventory.length; ++i){
			if(inventory.armorInventory[i] != null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)(i + 100));
				inventory.armorInventory[i].writeToNBT(tag);
				nbt.appendTag(tag);
			}
		}

		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inventory.player);
		for(int i = 0; i < rpgPlayer.baublesInventory.getSizeInventory(); ++i){
			ItemStack itemStack = rpgPlayer.baublesInventory.getStackInSlot(i);
			if(itemStack != null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)(i + 150));
				itemStack.writeToNBT(tag);
				nbt.appendTag(tag);
			}
		}

		return nbt;
	}

	@Hook(targetMethod = "readFromNBT", returnCondition = ReturnCondition.ALWAYS)
	public static void readPlayerInventoryFromNBT(InventoryPlayer inventory, NBTTagList nbt){
		inventory.mainInventory = new ItemStack[29];
		inventory.armorInventory = new ItemStack[4];

		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inventory.player);

		for(int i = 0; i < nbt.tagCount(); ++i){
			NBTTagCompound tag = nbt.getCompoundTagAt(i);
			int slot = tag.getByte("Slot") & 255;
			ItemStack itemStack = ItemStack.loadItemStackFromNBT(tag);

			if(itemStack != null){
				if(slot >= 0 && slot < inventory.mainInventory.length){
					inventory.mainInventory[slot] = itemStack;
				}

				if(slot >= 100 && slot < inventory.armorInventory.length + 100){
					inventory.armorInventory[slot - 100] = itemStack;
				}

				if(slot >= 150 && slot < rpgPlayer.baublesInventory.getSizeInventory() + 150){
					rpgPlayer.baublesInventory.setInventorySlotContents(slot - 150, itemStack);
				}
			}
		}
	}

	private static void setSlotByIndex(InventoryPlayer inv, int index, ItemStack itemStack){
		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inv.player);

		if(index < inv.mainInventory.length){
			inv.mainInventory[index] = itemStack;
		}else if(index < inv.mainInventory.length + inv.armorInventory.length){
			inv.armorInventory[index - inv.mainInventory.length] = itemStack;
		}else if(index < inv.mainInventory.length + inv.armorInventory.length + rpgPlayer.baublesInventory.getSizeInventory()){
			rpgPlayer.baublesInventory.setInventorySlotContents(index - inv.mainInventory.length - inv.armorInventory.length, itemStack);
		}
	}

	private static ItemStack getSlotByIndex(InventoryPlayer inv, int index){
		RpgPlayer rpgPlayer = RpgPlayer.forPlayer(inv.player);

		if(index < inv.mainInventory.length){
			return inv.mainInventory[index];
		}else if(index < inv.mainInventory.length + inv.armorInventory.length){
			return inv.armorInventory[index - inv.mainInventory.length];
		}else if(index < inv.mainInventory.length + inv.armorInventory.length + rpgPlayer.baublesInventory.getSizeInventory()){
			return rpgPlayer.baublesInventory.getStackInSlot(index - inv.mainInventory.length - inv.armorInventory.length);
		}

		return null;
	}
	
	
}