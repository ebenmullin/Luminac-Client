package luminac.settings;

public class KeybindSetting extends Settings {

	public int code;
	
	public KeybindSetting(int code) {
		this.name = "keybind";
		this.code = code;
	}

	public int getkeyCode() {
		return code;
	}

	public void setKeyCode(int code) {
		this.code = code;
	}

}
