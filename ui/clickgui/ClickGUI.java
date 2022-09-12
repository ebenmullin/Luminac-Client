package luminac.ui.clickgui;

import luminac.Client;
import luminac.modules.Module;
import luminac.modules.render.ClickGui;
import luminac.settings.SettingsManager;
import luminac.ui.clickgui.elements.Element;
import luminac.ui.clickgui.elements.ModuleButton;
import luminac.ui.clickgui.elements.menu.ElementValue;
import luminac.util.ColorUtil;
import luminac.util.font.TTFFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClickGUI extends GuiScreen {

	public Minecraft mc = Minecraft.getMinecraft();
	public ScaledResolution sr = new ScaledResolution(mc);

	public static ArrayList<Panel> panels;
	public static ArrayList<Panel> rpanels;
	private ModuleButton mb = null;
	public SettingsManager settingsManager;

	public ClickGUI() {
		settingsManager = Client.settingsManager;

		panels = new ArrayList<>();
		double pwidth = 80;
		double pheight = 15;
//		double px = sr.getScaledWidth() - 10 - pwidth;
		double px = 10;
		double py = 10;
		double pyplus = pheight + 10;

		for(Module.Category c : Module.Category.values()) {
			String category = Character.toUpperCase(c.name().toLowerCase().charAt(0)) + c.name().toLowerCase().substring(1);
			List<Module> modules =  Client.getModulesByCategory(c);
			ClickGUI.panels.add(new Panel(category, px, py, pwidth, pheight, false, this) {
				@Override
				public void setup() {
					for(Module m : modules) {
						System.out.println("category module test " + m.name + " this is the categoty: " + m.category);
						if(!m.category.equals(c))continue;
							System.out.println("being a little rat");
							this.Elements.add(new ModuleButton(m, this));
					}
				}
			});
			py += pyplus;
		}

		rpanels = new ArrayList<Panel>();
		for(Panel p : panels) {
			rpanels.add(p);
		}
		Collections.reverse(rpanels);

	}


	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		TTFFontRenderer fr = Client.fontManager.getFont("comfortaa 18");
		ScaledResolution sr = new ScaledResolution(mc);

  		if(ClickGui.blur.isEnabled()) {
  			drawGradientRect(0, 0, width, height, 0, ColorUtil.getRainbow(5, 0.6f, 0.5f));
  		}

		for(Panel p : panels) {
			p.drawScreen(mouseX, mouseY, partialTicks);
		}

		mb = null;

		listen:
		for(Panel p : panels) {
			if(p != null && p.visible && p.expanded && p.Elements != null && p.Elements.size() > 0) {
				for(ModuleButton e : p.Elements) {
					if(e.listening) {
						mb = e;
						break listen;
					}
				}
			}
		}

		for(Panel panel : panels) {
			if(panel.expanded && panel.visible && panel.Elements != null) {
				for(ModuleButton b : panel.Elements) {
					if(b.expanded && b.menuelements != null && !b.menuelements.isEmpty()) {
						double off = 0;
						Color temp = luminac.util.ColorUtil.clickGuiColor();
						int outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170).getRGB();

						for(Element e : b.menuelements) {
							e.offset = off;
							e.update();
							e.drawScreen(mouseX, mouseY, partialTicks);
							off += e.height;
						}
					}
				}
			}

		}

		if(mb != null) {
			drawRect(0, 0, this.width, this.height, 0x88101010);
			GL11.glPushMatrix();
	        GL11.glTranslatef((sr.getScaledWidth() / 2), (sr.getScaledHeight() / 2), 0.0F);
	        GL11.glScalef(4.0F, 4.0F, 0.0F);
			fr.drawCenteredString("Listening...", 0, -10, -1);
			drawCenteredString(mc.fontRendererObj, "Listening...", 0, -10, -1);
	        GL11.glScalef(0.5F, 0.5F, 0.0F);
			fr.drawCenteredString("Press 'ESCAPE' to unbind " + mb.module.name + ((mb.module.getKey() > -1) ? (" (" + Keyboard.getKeyName(mb.module.getKey()) + ")") : ""), 0, 0, -1);
			drawCenteredString(mc.fontRendererObj, "Press 'ESCAPE' to unbind " + mb.module.name + ((mb.module.getKey() > -1) ? (" (" + Keyboard.getKeyName(mb.module.getKey()) + ")") : ""), 0, 0, -1);
	        GL11.glScalef(0.25F, 0.25F, 0.0F);
			fr.drawCenteredString(Client.clientName + " v" + Client.clientVersion, 0, 20, -1);
			drawCenteredString(mc.fontRendererObj,Client.clientName + " v" + Client.clientVersion, 0, 20, -1);
	        GL11.glPopMatrix();
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}


	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		for(Module.Category c : Module.Category.values()) {
			for(Module module : Client.getModulesByCategory(c)) {
				System.out.println("category module test " + module.name + " this is the categoty: " + module.category);
			}
		}

		if(mb != null)return;

		for(Panel panel : rpanels) {
			if(panel.expanded && panel.visible && panel.Elements != null) {
				for(ModuleButton b : panel.Elements) {
					if(b.expanded) {
						for(Element e : b.menuelements) {
							if(e.mouseClicked(mouseX, mouseY, mouseButton))
								return;
						}
					}
				}
			}
		}

		for(Panel p : rpanels) {
			if(p.mouseClicked(mouseX, mouseY, mouseButton))
				return;
		}

		try {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void mouseReleased(int mouseX, int mouseY, int state) {

		if(mb != null)return;

		for(Panel panel : rpanels) {
			if(panel.expanded && panel.visible && panel.Elements != null) {
				for(ModuleButton b : panel.Elements) {
					if(b.expanded) {
						for(Element e : b.menuelements) {
							e.mouseReleased(mouseX, mouseY, state);
						}
					}
				}
			}
		}

		for(Panel p : rpanels) {
			p.mouseReleased(mouseX, mouseY, state);
		}

		super.mouseReleased(mouseX, mouseY, state);
	}


	protected void keyTyped(char typedChar, int keyCode) {

		for(Panel p : rpanels) {
			if(p != null && p.visible && p.expanded && p.Elements != null && p.Elements.size() > 0) {
				for(ModuleButton e : p.Elements) {
					try {
						if(e.keyTyped(typedChar, keyCode))return;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		try {
			super.keyTyped(typedChar, keyCode);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public void initGui() {
		if(ClickGui.blur.isEnabled()) {
			if(OpenGlHelper.shadersSupported && mc.getRenderViewEntity() instanceof EntityPlayer) {
				if(mc.entityRenderer.theShaderGroup != null) {
					mc.entityRenderer.theShaderGroup.deleteShaderGroup();

				}
				mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
			}
		}
	}

	public void onGuiClosed() {
		//file.saveConfig();
		if(mc.entityRenderer.theShaderGroup != null) {
			mc.entityRenderer.theShaderGroup.deleteShaderGroup();
			mc.entityRenderer.theShaderGroup = null;
		}

		for(Panel panel : ClickGUI.rpanels) {
			if(panel.expanded && panel.visible && panel.Elements != null) {
				for(ModuleButton b : panel.Elements) {
					if(b.expanded) {
						for(Element e : b.menuelements) {
							if(e instanceof ElementValue) {
								((ElementValue)e).dragging = false;
							}
						}
					}
				}
			}
		}
	}

	public void closeAllSettings() {
		for(Panel p : rpanels) {
			if(p != null && p.visible && p.expanded && p.Elements != null
					&& p.Elements.size() > 0) {
				for(ModuleButton e : p.Elements) {
					//e.expanded = false;
				}
			}
		}
	}
}




