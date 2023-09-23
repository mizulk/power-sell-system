package team.skadi.powersellsys.router;

/**
 * 该枚举类是用于切换不同面板
 */
public enum PanelName {
	LOGIN_PANEL("Login"), SELECT_LOGIN_PANEL("selectLogin");

	final String value;

	PanelName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
