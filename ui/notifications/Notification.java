package luminac.ui.notifications;

import luminac.Client;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Notification {
	public Minecraft mc = Minecraft.getMinecraft();
	FontRenderer fr = mc.fontRendererObj;
	private NotificationType type;
	private String title;
	private String message;
	private long start;
	private long fadedIn;
	private long fadeOut;
	private long end;
	private int yOffset;
  
	public Notification(NotificationType type, String title, String message, int length) {
		this.type = type;
		this.title = title;
		this.message = message;
		this.fadedIn = (200 * length);
		this.fadeOut = this.fadedIn + (500 * length);
		this.end = this.fadeOut + this.fadedIn;
	  }
	  
	  public void show() {
	    this.start = System.currentTimeMillis();
	  }
	  
	  public void resetOffset() {
	    this.yOffset = Client.notificationManager.getIndex(this) * 40;
	  }
	  
	  public void updateOffset() {
	    int changeOffset = 0;
	    if (getTime() > this.fadeOut) {
	      changeOffset = 40 - (int)(Math.tanh(3.0D - (getTime() - this.fadeOut) / (this.end - this.fadeOut) * 3.0D) * 40.0D);
	    } else {
	      changeOffset = 0;
	    } 
	    for (Notification notification : Client.notificationManager.getNotifications()) {
	      if (Client.notificationManager.getIndex(notification) > Client.notificationManager
	        .getIndex(this))
	        notification.changeOffset(Math.min(changeOffset, 40)); 
	    	} 
	  }
	  
	  public void changeOffset(int offset) {
	    this.yOffset -= offset;
	  }
	  
	  public boolean isShown() {
	    return (getTime() <= this.end);
	  }
	  
	  public int fadingOutProgress() {
	    if (getTime() > this.fadeOut && this.end - getTime() != 0L)
	      return 40 - (int)(Math.tanh(3.0D - (getTime() - this.fadeOut) / (this.end - this.fadeOut) * 3.0D) * 40.0D); 
	    return 0;
	  }
	  
	  private long getTime() {
	    return System.currentTimeMillis() - this.start;
	  }
	  
	  public void render() {
		  Color color1;
		  if (!isShown()) {
			  Client.notificationManager.removeFromList(this);
			  return;
		    } 
		    if (Client.notificationManager.getIndex(this) == 0)
		      Client.notificationManager.setLastNotif(this); 
		    double offset = 0.0D;
		    int width = 120;
		    int height = 30;
		    long time = getTime();
		    if (time < this.fadedIn) {
		      offset = Math.tanh(time / this.fadedIn * 3.0D) * width;
		    } else if (time > this.fadeOut) {
		      offset = Math.tanh(3.0D - (time - this.fadeOut) / (this.end - this.fadeOut) * 3.0D) * width;
		    } else {
		      offset = width;
		    } 
		    Color color = new Color(0, 0, 0, 220);
		    if (this.type == NotificationType.INFO) {
		      color1 = new Color(0, 26, 169);
		    } else if (this.type == NotificationType.WARNING) {
		      color1 = new Color(204, 193, 0);
		    } else {
		      color1 = new Color(204, 0, 18);
		      int i = Math.max(0, Math.min(255, (int)(Math.sin(time / 100.0D) * 255.0D / 2.0D + 127.5D)));
		      color = new Color(i, 0, 0, 220);
		    } 
		    Gui.drawRect(width - offset, (height - 5 - height - this.yOffset), width, (
		        height - 5 - this.yOffset), color.getRGB());
		    Gui.drawRect(width - offset, (height - 5 - height - this.yOffset), width - offset + 4.0D, (
		        height - 5 - this.yOffset), color1.getRGB());
		    GL11.glPushMatrix();
		    GlStateManager.translate(width - offset + 8.0D, (height - 2 - height - this.yOffset), 0.0D);
		    GlStateManager.scale(Math.min(108.0D / fr.getStringWidth(this.title), 1.0D), 
		        Math.min(108.0D / fr.getStringWidth(this.title), 1.0D), 1.0D);
		    GlStateManager.translate(-(width - offset + 8.0D), -(height - 2 - height - this.yOffset), 0.0D);
		    fr.drawString(this.title, (int)(width - offset + 8.0D), (height - 2 - height - this.yOffset), 
		        -1);
		    GL11.glPopMatrix();
		    GL11.glPushMatrix();
		    GlStateManager.translate(width - offset + 8.0D, (height + 12 - height - this.yOffset), 0.0D);
		    GlStateManager.scale(Math.min(108.0D / fr.getStringWidth(this.message), 1.0D), 
		        Math.min(108.0D / fr.getStringWidth(this.message), 1.0D), 1.0D);
		    GlStateManager.translate(-(width - offset + 8.0D), -(height + 12 - height - this.yOffset), 0.0D);
		    fr.drawString(this.message, (int)(width - offset + 8.0D), (height + 12 - height - this.yOffset), 
		        -1);
	    GL11.glPopMatrix();
	  }
	}
