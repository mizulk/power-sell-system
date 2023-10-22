package team.skadi.powersellsys.store;

public record UserStore(String account) {

	public Integer id() {
		return Integer.parseInt(account);
	}
}
