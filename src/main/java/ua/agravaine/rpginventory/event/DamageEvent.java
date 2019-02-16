package ua.agravaine.rpginventory.event;


import ua.agravaine.rpginventory.ElterionRPG;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DamageEvent {

	static final Minecraft mc = Minecraft.getMinecraft();


	@SubscribeEvent
	public void damage(LivingHurtEvent event) {
		int a = 2;
		int b = 3;
		int c = 4;
		int d = 5;
		int e = 6;
		int f = 7;
		int g = 8;
		int k = 8;

		int diff1 = e - b;//урон 2-5
		int diff2 = f - c;//урон 3-6
		int diff3 = g - d;//урон 4-7
		int diff4 = k - e;//урон 5-8
		int diff5 = f - d;//урон 6-4

		int i1 = diff1 + event.entityLiving.worldObj.rand.nextInt(e);
		int i11 = diff1 + event.entityLiving.worldObj.rand.nextInt(b);
		int i2 = diff2 + event.entityLiving.worldObj.rand.nextInt(c);
		int i21 = diff2 + event.entityLiving.worldObj.rand.nextInt(c);
		int i3 = diff3 + event.entityLiving.worldObj.rand.nextInt(d);
		int i4 = diff4 + event.entityLiving.worldObj.rand.nextInt(e);
		int i5 = diff5 + event.entityLiving.worldObj.rand.nextInt(d);
		int i51 = diff5 + event.entityLiving.worldObj.rand.nextInt(d);
		int c3 = event.entityLiving.worldObj.rand.nextInt(100);
		if (c3 < 15) {
			i1 = (int) ((float) i1 * 1.5F);
			i2 = (int) ((float) i2 * 1.5F);
			i5 = (int) ((float) i5 * 1.5F);
		}

		
		
			Entity from = event.source.getSourceOfDamage();//Кто наносит урон.
			EntityLivingBase to = event.entityLiving;//Кому наносят урон.
			if (from instanceof EntityPlayer) {
				ItemStack is = ((EntityPlayer)from).getCurrentEquippedItem();
				if (is != null) {
					if (is.getItem() == ElterionRPG.sword_bastard_st)
						event.ammount = i1;
					if (is.getItem() == ElterionRPG.sword_bastard_rr)
						event.ammount = i2;
					if (is.getItem() == ElterionRPG.sword_bastard_lg)
						event.ammount = i5;
					if (is.getItem() == ElterionRPG.dwarven_axe_st)
						event.ammount = i11;
					if (is.getItem() == ElterionRPG.dwarven_axe_rr)
						event.ammount = i21;
					if (is.getItem() == ElterionRPG.dwarven_axe_lg)
						event.ammount = i51;

				}

			}

		}
	}


