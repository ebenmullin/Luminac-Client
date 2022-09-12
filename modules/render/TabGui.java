package luminac.modules.render;

import java.awt.Color;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventKey;
import luminac.events.listeners.EventRenderGUI;
import luminac.modules.Module;
import luminac.modules.misc.GuiSettings;
import luminac.settings.BooleanSetting;
import luminac.settings.KeybindSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.settings.Settings;
import luminac.util.ColorUtil;
import luminac.util.font.TTFFontRenderer;
import luminac.util.render.ShapeUtils;

public class TabGui extends Module {
	
	public int currentTab, moduleIndex;
	public boolean expanded;

	public static NumberSetting red = new NumberSetting("Red", 0, 0, 255, 1);
	public static NumberSetting green = new NumberSetting("Green", 144, 0, 255, 1);
	public static NumberSetting blue = new NumberSetting("Blue", 255, 0, 255, 1);
	public static NumberSetting opacity = new NumberSetting("Opacity", 144, 0, 255, 1);
	public static ModeSetting color = new ModeSetting("Color", "Rainbow", "Rainbow", "Custom");
	
	public TabGui() {
		super("TabGUI", Keyboard.KEY_APOSTROPHE, Category.RENDER);
		this.addSettings(color, red, green, blue, opacity);
		toggled = true;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {
			TTFFontRenderer fr = Client.fontManager.getFont("comfortaa 18");

			Color temp = ColorUtil.tabGuiColor();
		    int customColor = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), (int) TabGui.opacity.getValue())).getRGB();
		    Double color = ClickGui.opacity.getValue(); 
		    int mainColor = 0x90000000;
		    int highlight = ((TabGui.color.getMode() == "Rainbow") ? ColorUtil.getRainbow(5, 0.6f, 0.9f) :
		    	(TabGui.color.getMode() == "Custom") ? customColor : 0);
		    
		    int highlight2 = ((TabGui.color.getMode() == "Rainbow") ? ColorUtil.getRainbow(5, 0.6f, 0.6f) :
		    	(TabGui.color.getMode() == "Custom") ? customColor : 0);
			
			GL11.glScaled(GuiSettings.size.getValue() / GuiSettings.size.getValue(), GuiSettings.size.getValue() / GuiSettings.size.getValue(), GuiSettings.size.getValue() / GuiSettings.size.getValue());
			
			ShapeUtils.drawRoundedRect(7, 27, 76, 30 + Category.values().length * 14 - 2, 3, mainColor);
			ShapeUtils.drawRoundedRect(8, 28 + currentTab * 14, 75,  28 + currentTab * 14 + 13, 3, highlight);
		
			int count = 0;
			for(Category c : Category.values()) 	{
				
				fr.drawString(c.name, 10, 31 + count * 14, -1);
	
				count++;
				
			}
			
			if(expanded) {
				Category category = Category.values()[currentTab];
				List<Module> modules =  Client.getModulesByCategory(category);
				
				if(modules.size() == 0)
					return;
				
				ShapeUtils.drawRoundedRect(77, 27, 146, 30 + modules.size() * 14 - 2, 3, mainColor);
				ShapeUtils.drawRoundedRect(78, 28 + category.moduleIndex * 14, 145, 28 + category.moduleIndex * 14 + 13, 3, highlight);
				
				count = 0;
				for(Module m : modules) {
					fr.drawString(m.name, 80, 31 + count * 14, -1);
					if(count == category.moduleIndex && m.expanded) {
						
						int index = 0, maxLength = 0;
						for(Settings setting : m.settings) {
							if(setting instanceof BooleanSetting) {
								BooleanSetting bool = (BooleanSetting) setting;
								if(maxLength < fr.getWidth(setting.name + ": " + (bool.enabled ? "on" : "off"))) {
									maxLength = (int) fr.getWidth(setting.name + ": " + (bool.enabled ? "on" : "off"));
								}
							}
							
							if(setting instanceof NumberSetting) {
								NumberSetting number = (NumberSetting) setting;
								if(maxLength < fr.getWidth(setting.name + ": " + number.getValue())) {
									maxLength = (int) fr.getWidth(setting.name + ": " + number.getValue());
								}
							}
							
							if(setting instanceof ModeSetting) {
								ModeSetting mode = (ModeSetting) setting;
								if(maxLength < fr.getWidth(setting.name + ": " + mode.getMode())) {
									maxLength = (int) fr.getWidth(setting.name + ": " + mode.getMode());
								}
							}
							
							if(setting instanceof KeybindSetting) {
								KeybindSetting keyBind = (KeybindSetting) setting;
								if(maxLength < fr.getWidth(setting.name + ": " + Keyboard.getKeyName(keyBind.code))) {
									maxLength = (int) fr.getWidth(setting.name + ": " + Keyboard.getKeyName(keyBind.code));
								}
							}
							index++;
						}
						ShapeUtils.drawRoundedRect(147, 27, 147 + maxLength + 5	, 30 + m.settings.size() * 14 - 2, 3, mainColor);
						ShapeUtils.drawRoundedRect(148, 28 + m.index * 14, 146 + maxLength + 5, 28 + m.index * 14 + 13, 3, m.settings.get(m.index).focused ? highlight2 : highlight);
						
						index = 0;
						for(Settings setting : m.settings) {
							if(setting instanceof BooleanSetting) {
								BooleanSetting bool = (BooleanSetting) setting;
								fr.drawString(setting.name + ": " + (bool.enabled ? "on" : "off"), 150, 31 + index * 14, -1);
							}
							
							if(setting instanceof NumberSetting) {
								NumberSetting number = (NumberSetting) setting;
								fr.drawString(setting.name + ": " + number.getValue(), 150, 31 + index * 14, -1);
							}
							
							if(setting instanceof ModeSetting) {
								ModeSetting mode = (ModeSetting) setting;
								fr.drawString(setting.name + ": " + mode.getMode(), 150, 31 + index * 14, -1);
							}
							
							if(setting instanceof KeybindSetting) {
								KeybindSetting keyBind = (KeybindSetting) setting;
								fr.drawString(setting.name + ": " + Keyboard.getKeyName(keyBind.code), 150, 31 + index * 14, -1);
							}
							index++;
							}
						}
				count++;
				
				
			}
		}	
		GL11.glScaled(GuiSettings.size.getValue(), GuiSettings.size.getValue(), GuiSettings.size.getValue());
	}
	if(e instanceof EventKey) {
		int code = ((EventKey)e).code;
	
		Category category = Category.values()[currentTab];
		List<Module> modules =  Client.getModulesByCategory(category);
		
		if(expanded && !modules.isEmpty() && modules.get(category.moduleIndex).expanded) {
			Module module =modules.get(category.moduleIndex);
			
			if(!module.settings.isEmpty() && module.settings.get(module.index).focused && module.settings.get(module.index) instanceof KeybindSetting) {
				if(code != Keyboard.KEY_RETURN && code != Keyboard.KEY_UP && code != Keyboard.KEY_DOWN && code != Keyboard.KEY_LEFT && code != Keyboard.KEY_RIGHT && code != Keyboard.KEY_ESCAPE) {
					if(code == Keyboard.KEY_BACKSLASH) {
						KeybindSetting keyBind = (KeybindSetting)module.settings.get(module.index);
						keyBind.code = Keyboard.KEY_Z;
					}else {
						KeybindSetting keyBind = (KeybindSetting)module.settings.get(module.index);
					
						keyBind.code = code;
						keyBind.focused = false;
					
						return;
					}
				}	
			}
		}
		
		if(code == Keyboard.KEY_UP ) {
			if(expanded) {
				if(expanded && !modules.isEmpty() && modules.get(category.moduleIndex).expanded) {
					Module module = modules.get(category.moduleIndex);
					
					if(!module.settings.isEmpty()) {
						if(module.settings.get(module.index).focused) {
							Settings setting = module.settings.get(module.index);
							
							if(setting instanceof NumberSetting) {
								((NumberSetting)setting).increment(true);
							}
						}else {
							if(module.index <= 0) {
								module.index = module.settings.size() - 1;
							}else
								module.index --;
						}
					}	
				}else {
					if(category.moduleIndex <= 0) {
						category.moduleIndex = modules.size() - 1;
					}else
						category.moduleIndex --;
				}
			} else {
				if(currentTab <= 0) {
					currentTab = Category.values().length - 1;
				}else
					currentTab--;
			}
		}
		if(code == Keyboard.KEY_DOWN) {
			if(expanded) {
				if(expanded && !modules.isEmpty() && modules.get(category.moduleIndex).expanded) {
					Module module = modules.get(category.moduleIndex);
					
					if(!module.settings.isEmpty()) {
						if (module.settings.get(module.index).focused) {
						Settings setting = module.settings.get(module.index);
							
							if(setting instanceof NumberSetting) {
								((NumberSetting)setting).increment(false);
							}
							
						}else {
							if(module.index >= module.settings.size() - 1) {
								module.index = 0;
							}else
								module.index++;
						}
					}	
				}else {
					if(category.moduleIndex >= modules.size() - 1) {
						category.moduleIndex = 0;
					}else
						category.moduleIndex++;
				}
			} else {
				if(currentTab >= Category.values().length - 1) {
					currentTab = 0;
				}else
					currentTab++;
			}
		}
		
		
		if(code == Keyboard.KEY_RETURN) {
			if(expanded && modules.size() != 0) {
				Module module = modules.get(category.moduleIndex);
				
					if(!module.expanded && !module.settings.isEmpty())
						module.expanded = true;
					
					else if(module.expanded && !module.settings.isEmpty()) {
						module.settings.get(module.index).focused = !module.settings.get(module.index).focused;
					}
			}		
		}
		
		if(code == Keyboard.KEY_RIGHT) {
			if(expanded && modules.size() != 0) {
				Module module = modules.get(category.moduleIndex);
				
				if(expanded && !modules.isEmpty() && module.expanded) {
					if(!module.settings.isEmpty()) {
						if (module.settings.get(module.index).focused) {
							Settings setting = module.settings.get(module.index);
								
							if(setting instanceof NumberSetting) {
								((NumberSetting)setting).increment(true);
							}
						
							if(setting instanceof BooleanSetting) {
								((BooleanSetting)setting).toggle();
							}
							
							if(setting instanceof ModeSetting) {
								((ModeSetting)setting).cycle("right");
							}
						}
					}	
				}else {
					if(!module.name.equals("Change"))
						module.toggled();
				}
			
			} else {;
				expanded = true;
			}	
		}
		
		if(code == Keyboard.KEY_LEFT) {
			Module module = modules.get(category.moduleIndex);
			if(expanded && !modules.isEmpty() && modules.get(category.moduleIndex).expanded) {
				
				if(!module.settings.isEmpty()) {
					if (module.settings.get(module.index).focused) {
						Settings setting = module.settings.get(module.index);
							
						if(setting instanceof NumberSetting) {
							((NumberSetting)setting).increment(false);
						}
						
						if(setting instanceof BooleanSetting) {
							((BooleanSetting)setting).toggle();
						}
						
						if(setting instanceof ModeSetting) {
							((ModeSetting)setting).cycle("left");
						}
						
					}else {
					 	modules.get(category.moduleIndex).expanded = false;
					}
				}	
			} else 
				expanded = false;
			}	
		}
	}
}

