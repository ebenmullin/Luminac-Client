package luminac.util.config;

import luminac.Client;
import luminac.modules.Module;
import luminac.settings.Setting;
import luminac.settings.Settings;
import luminac.settings.BooleanSetting;
import luminac.settings.KeybindSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {
	/**

    private final String name;
    private final File file;

    public Config(String name) {
        this.name = name;
        this.file = new File(Minecraft.getMinecraft().mcDataDir + "/Fan/configs/" + name + ".json");
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }

    public String serialize() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        for(Module module : Client.modules) {
            List<ConfigSetting> settings = new ArrayList<>();
            for(Settings setting : module.settings) {
                if(setting instanceof KeybindSetting)
                    continue;

                ConfigSetting cfgSetting = new ConfigSetting(null, null);
                cfgSetting.name = setting.name;
                if(setting instanceof BooleanSetting) {
                    cfgSetting.value = ((BooleanSetting) setting).isEnabled();
                }
                if(setting instanceof ModeSetting) {
                    cfgSetting.value = ((ModeSetting) setting).getMode();
                }
                if(setting instanceof NumberSetting) {
                    cfgSetting.value = ((NumberSetting) setting).getValue();
                }

                settings.add(cfgSetting);
            }
            module.configSettings = settings.toArray(new ConfigSetting[0]);
        }
        return gson.toJson(Client.modules);
        
        /*JsonArray array = new JsonArray();
        for (Module module : Client.modules) {
            JsonObject object = new JsonObject();
            JsonObject settingsObject = new JsonObject();
            object.addProperty("Name", module.getName());
            object.addProperty("Keybind", module.getKeyBind().getCode());
            object.addProperty("State", module.isToggled());
            for (Setting setting : module.getSettings()) {
                if (setting instanceof NumberSetting) {
                    NumberSetting s = (NumberSetting) setting;
                    settingsObject.addProperty(setting.name, s.getValue());
                }
                if (setting instanceof BooleanSetting) {
                    BooleanSetting s = (BooleanSetting) setting;
                    settingsObject.addProperty(setting.name, s.isEnabled());
                }
                if (setting instanceof ModeSetting) {
                    ModeSetting s = (ModeSetting) setting;
                    settingsObject.addProperty(setting.name, s.getSelected());
                }
            }
            object.add("Settings", settingsObject);
            array.add(object);
        }
        return array;
    }
    */

}
