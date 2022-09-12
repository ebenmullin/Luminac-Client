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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
/**
    private static final List<Config> configs = new ArrayList<>();
    private final File file = new File(Minecraft.getMinecraft().mcDataDir, "/Fan/configs");
    public File config = new File(Minecraft.getMinecraft().mcDataDir + "/Fan/Config.json");
    String[] pathnames;

    public ConfigManager() {
        file.mkdirs();
    }

    public static Config getConfigByName(String name) {
        for(Config config : configs) {
            if(config.getName().equalsIgnoreCase(name)) return config;
        }
        return null;
    }

    public boolean load(String name) {
        Config config = new Config(name);
        try {
            String configString = new String(Files.readAllBytes(config.getFile().toPath()));
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
            Module[] modules = gson.fromJson(configString, Module[].class);

            for(Module module : Client.modules) {
                for(Module configModule : modules) {
                    if(module.name.equals(configModule.name)) {
                        try {
                            if(configModule.isEnabled() && !module.isEnabled())
                                module.toggled();
                            else if(!configModule.isEnabled() && module.isEnabled())
                                module.toggled = false;
                            	module.keyPressed.setKeyCode(configModule.keyCode);

                            for(Settings setting : module.settings) {
                                for(ConfigSetting configSetting : configModule.configSettings) {
                                    if(setting.name.equals(configSetting.name)) {
                                        if(setting instanceof BooleanSetting) {
                                            ((BooleanSetting) setting).setEnabled((boolean) configSetting.value);
                                        }
                                        if(setting instanceof ModeSetting) {
                                            ((ModeSetting) setting).setMode((String) configSetting.value);
                                        }
                                        if(setting instanceof NumberSetting) {
                                            ((NumberSetting) setting).setValue((double) configSetting.value);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadConfig() {
        try {
            String configString = new String(Files.readAllBytes(config.toPath()));
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
            Module[] modules = gson.fromJson(configString, Module[].class);

            for(Module module : Client.modules) {
                for(Module configModule : modules) {
                    if(module.name.equals(configModule.name)) {
                        try {
                            if(configModule.isEnabled() && !module.isEnabled())
                                module.toggled();
                            else if(!configModule.isEnabled() && module.isEnabled())
                                module.toggled = false;
                            	module.keyPressed.setKeyCode(configModule.keyCode);

                            for(Settings setting : module.settings) {
                                for(ConfigSetting configSetting : configModule.configSettings) {
                                    if(setting.name.equals(configSetting.name)) {
                                        if(setting instanceof BooleanSetting) {
                                            ((BooleanSetting) setting).setEnabled((boolean) configSetting.value);
                                        }
                                        if(setting instanceof ModeSetting) {
                                            ((ModeSetting) setting).setMode((String) configSetting.value);
                                        }
                                        if(setting instanceof NumberSetting) {
                                            ((NumberSetting) setting).setValue((double) configSetting.value);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(String name) {
       /* Config config = getConfigByName(name);
        if(config == null) {
            System.out.println("Cannot find " + name);
            return false;
        }


        Config config = new Config(name);

        try {
            //FileWriter fw = new FileWriter(config.getFile());
            //fw.write(config.serialize());
            //fw.close();
            config.getFile().getParentFile().mkdirs();
            Files.write(config.getFile().toPath(), config.serialize().getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save " + config);
            return false;
        }
    }

    public void saveConfig() {
        try {
            config.getParentFile().mkdirs();
            Files.write(config.toPath(), serialize().getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save " + config);
        }
    }

    public String serialize() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        for(Module module : Client.modules) {
            List<ConfigSetting> settings = new ArrayList<>();
            for(Settings setting : module.settings) {
                if(setting instanceof KeybindSetting)
                    continue;

                ConfigSetting configSetting = new ConfigSetting(null, null);
                configSetting.name = setting.name;
                if(setting instanceof BooleanSetting) {
                    configSetting.value = ((BooleanSetting) setting).isEnabled();
                }
                if(setting instanceof ModeSetting) {
                    configSetting.value = ((ModeSetting) setting).getMode();
                }
                if(setting instanceof NumberSetting) {
                    configSetting.value = ((NumberSetting) setting).getValue();
                }

                settings.add(configSetting);
            }
            module.configSettings = settings.toArray(new ConfigSetting[0]);
        }
        return gson.toJson(Client.modules);
    }

    public boolean save(Config config) {
        return this.save(config);
    }

    public void saveAll() {
        configs.forEach(config -> save(config.getName()));
    }

    public void loadConfigs() {
        for(File file : file.listFiles()) {
            configs.add(new Config(file.getName().replace(".json", "")));
        }
    }

    public List<Config> getConfigs() {
        return configs;
    }

    public void list() {
        pathnames = file.list();
        for(String pathname : pathnames) {
        	Client.addChatMessage("does something");
        }
    }

    public void delete(String configName) {
        Config config = new Config(configName);
        try {
            Files.deleteIfExists(config.getFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}
