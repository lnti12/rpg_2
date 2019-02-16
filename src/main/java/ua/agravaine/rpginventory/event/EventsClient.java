package ua.agravaine.rpginventory.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class EventsClient {

    static final Minecraft mc = Minecraft.getMinecraft();
    static int renderTick = -1;
    static float damage = 0F;

    @SubscribeEvent
    public void tickClient(TickEvent.ClientTickEvent event) {
        if (renderTick > 0) --renderTick;
    }

    @SubscribeEvent
    public void damage(LivingHurtEvent event) {
        Entity from = event.source.getSourceOfDamage();
        EntityLivingBase to = event.entityLiving;
        if (from instanceof EntityPlayer) {
            damage = event.ammount;
            renderTick = 20 * 3;
        }
    }

    @SubscribeEvent
    public void drawText(RenderGameOverlayEvent.Post event) {
    	EntityPlayer player = mc.thePlayer;
    	if(player.capabilities.isCreativeMode){
    	if (renderTick <= 0) return;
        String dmg = Float.toString(damage);
        switch(event.type) {
        case TEXT:
            mc.fontRenderer.drawStringWithShadow("Урон по ентити:"+dmg,
                    (event.resolution.getScaledWidth() - mc.fontRenderer.getStringWidth(dmg)) / 2,
                    (event.resolution.getScaledHeight() - 25) / 2,
                    0xFFFFFF);
            break;
        default:break;
        }
    	}
    }
    
}