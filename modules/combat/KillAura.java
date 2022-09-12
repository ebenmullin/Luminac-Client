package luminac.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.events.listeners.EventRender3d;
import luminac.events.listeners.EventRenderGUI;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.movement.Jesus;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.ui.GuiDrag;
import luminac.util.ColorUtil;
import luminac.util.Timer;
import luminac.util.font.TTFFontRenderer;
import luminac.util.render.RenderUtils;
import luminac.util.render.ShapeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C16PacketClientStatus;

public class KillAura extends Module {

	/** Credit to TouchPotato 2020 **/
	
	public static float visualPitchRotation;
    public static float prevVisualPitchRotation;
    public static boolean hasTarget;
    public static float visualYawRotation;
    public static float prevVisualYawRotation;
    public static boolean shouldRotate;
	public static boolean Info;
	
	public Timer timer = new Timer();
	public NumberSetting range = new NumberSetting("Range", 6, 1, 6, 0.5);
	public NumberSetting aps = new NumberSetting("APS", 20, 1, 20, 1);
	public BooleanSetting noSwing = new BooleanSetting("No Swing", false); 
	public BooleanSetting tracker = new BooleanSetting("Tracker", false);
	public BooleanSetting disable = new BooleanSetting("Disable on death", true);
	public BooleanSetting gui = new BooleanSetting("Target HUD", true);
	public BooleanSetting box = new BooleanSetting("Target Box", true);
	
	//public BooleanSetting player = new BooleanSetting("Player", true);
	//public BooleanSetting animal = new BooleanSetting("Animal", true);
	//public BooleanSetting mob = new BooleanSetting("Mob", true);
	
	
	public KillAura() {
		super("KillAura", Keyboard.KEY_X, Category.COMBAT);
		this.addSettings(range, aps, noSwing, tracker, gui, box, disable);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
        shouldRotate = false;
        hasTarget = false;
    }
	
	public void onEvent(Event e) {
        if(e instanceof EventMotion) {
            if(e.isPre()) {
            	
            	if(disable.isEnabled()) {
	            	if(mc.thePlayer.isDead) {
	            		this.toggled();
	            		Client.addChatMessage("KillAura disabled on death");
	            	}
            	}
            	
                EventMotion event = (EventMotion)e;

                List<Entity> targets = mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());

                targets = targets.stream().filter(entity -> entity.getDistanceToEntity(mc.thePlayer) < range.getValue() && entity != mc.thePlayer && !entity.isDead && ((EntityLivingBase) entity).getHealth() > 0).collect(Collectors.toList());

                targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase)entity).getDistanceToEntity(mc.thePlayer)));
                
                //targets = targets.stream().filter(EntityOtherPlayerMP.class::isInstance).collect(Collectors.toList());
                
                if(!targets.isEmpty()) {
                	shouldRotate = true;
                    hasTarget = true;
                    Entity target = targets.get(0);
                    
                    if(tracker.isEnabled()) {
                        mc.thePlayer.rotationYaw = (getRotations(target)[0]);
                        mc.thePlayer.rotationPitch = (getRotations(target)[1]);
                    } else {
                    	mc.thePlayer.renderYawOffset = getRotations(target)[0];
                    	
                        event.setYaw(getRotations(target)[0]);
                        event.setPitch(getRotations(target)[1]);
                    }

                    if(timer.hasTimeElapsed((long) (1000/aps.getValue()*2.5), true)) {
                    	mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, Action.ATTACK));
                        if(noSwing.isEnabled()) {
                            mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
                        } else {
                            mc.thePlayer.swingItem();
                        }
                    }
                } else {
                    shouldRotate = false;
                    hasTarget = false;
                }
            }
        } else if(e instanceof EventRenderGUI) {
        
        	if(gui.isEnabled()) {
	        	ScaledResolution sr = new ScaledResolution(mc);
	        	
	        	TTFFontRenderer fr = Client.fontManager.getFont("comfortaa 18");
				
	        	List<Entity> targets = mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
	    		
	    		targets = targets.stream().filter(entity -> entity.getDistanceToEntity(mc.thePlayer) < range.getValue() + 1 && entity != mc.thePlayer).collect(Collectors.toList());
	    		
	    		targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase)entity).getDistanceToEntity(mc.thePlayer)));
	        	
	    		if(!targets.isEmpty()) {
	    			
	    			EntityLivingBase target = (EntityLivingBase) targets.get(0);
	    	    	
	    			if(target.getHealth() != 0) {
	    				
	    				double health = (target.getHealth()) * 10;
	    					
	    		    	GuiDrag.drawRoundedRect(sr.getScaledWidth() - 223, sr.getScaledHeight() - 123,
	    		    		sr.getScaledWidth() - 23, sr.getScaledHeight() - 46, 0x90000000);
	    		    	
	    		    	GuiDrag.drawRoundedRect(sr.getScaledWidth() - 223, sr.getScaledHeight() - 48,
		    		    		sr.getScaledWidth() - 223 + health, sr.getScaledHeight() - 46, ColorUtil.getRainbow(5, 0.6f, 1));
	    		    	
	    		    	//Shows Armor health as Line
	    		    	Gui.drawRect(sr.getScaledWidth() - 223, sr.getScaledHeight() - 48,
		    		    		sr.getScaledWidth() - 223 + (target.getTotalArmorValue() * 10), sr.getScaledHeight() - 50, -1);
	    		    	
	    		    	
	    		    	fr.drawString(target.getName(), sr.getScaledWidth() - 23 - fr.getWidth(target.getName()) - 4, sr.getScaledHeight() - 120, ColorUtil.getRainbow(5, 0.6f, 0.9f));
	    		    	
						fr.drawString(target.onGround ? " On Ground | Distance: " + Math.round(mc.thePlayer.getDistanceToEntity(target)) : " Off Ground | Distance: "
								+ Math.round(mc.thePlayer.getDistanceToEntity(target)), sr.getScaledWidth() - 177, sr.getScaledHeight() - 100, -1);
	    		    		
	    		    	fr.drawString("Health: " + Math.round(target.getHealth() * 100)/100 + " | Armor: " + target.getTotalArmorValue(), sr.getScaledWidth() - 177, sr.getScaledHeight() - 80, -1);
	    		    	
	    		    	GuiInventory.drawEntityOnScreen(sr.getScaledWidth() - 200, sr.getScaledHeight() - 54, 30, target.getRotationYawHead() + 20, 20, (EntityLivingBase) target);	
	    		    	
	    		    	if(target.getHeldItem() != null) {
	    		    		//IDK\\
	    		    		//mc.renderItem.func_175049_a(target.getHeldItem(), mc.thePlayer, TransformType.GUI);
	    		    		//mc.renderItem(target.getHeldItem() sr.getScaledWidth() - 200, sr.getScaledHeight() - 54);
    		    		}
    				}
    			}
        	}
        } else if(e instanceof EventRender3d) {
        	for(Object o : mc.theWorld.getLoadedEntityList()){
				if(o instanceof EntityLivingBase && o != mc.thePlayer && ((Entity) o).getDistanceToEntity(mc.thePlayer) < range.getValue() + 1) {
					EntityLivingBase entity = (EntityLivingBase) o;
					
					double posX = entity.posX - mc.getRenderManager().renderPosX - entity.width / 2;
					double posY = entity.posY - mc.getRenderManager().renderPosY;
					double posZ = entity.posZ - mc.getRenderManager().renderPosZ - entity.width / 2;
					
					double posX2 = entity.posX - mc.getRenderManager().renderPosX + entity.width / 2;
					double posY2 = entity.posY - mc.getRenderManager().renderPosY + entity.height;
					double posZ2 = entity.posZ - mc.getRenderManager().renderPosZ + entity.width / 2;
					
					if(box.isEnabled()) {
						//RenderUtils.drawFilledBox(true, posX, posY, posZ, posX2, posY2, posZ2, 0.4, 1, 0.5, 0.2f, 0.4, 1, 0.5, 1);
					}
				}
			}
        }
	}
	
	public float[] getRotations(Entity e) {
		double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX,
		       deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
		       deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
		       distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
		
		float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
			  pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
		
		if(deltaX < 0 && deltaZ < 0) {
			yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ - deltaX)));
		}else if(deltaX > 0 && deltaZ < 0) {
			yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ - deltaX)));
		}
		if(visualYawRotation != yaw) {
			prevVisualYawRotation = visualYawRotation;
		}
		if (visualYawRotation != yaw) {
            prevVisualYawRotation = visualYawRotation;
        }
        visualYawRotation = yaw;
        if (visualPitchRotation != pitch) {
            prevVisualPitchRotation = visualPitchRotation;
        }
        visualPitchRotation = pitch;
		return new float[] { yaw, pitch };
	}
}
