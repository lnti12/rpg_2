package ua.agravaine.rpginventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class RPGButton extends GuiButton {

    public RPGButton(int id, int x, int y, int width, int height, String name)
    {

        super(id, x, y, width, height, name);
        this.width = 10;
        this.height = 9;
        this.xPosition = x;
        this.yPosition = y;

    }
@Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY){}

}
