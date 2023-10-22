package team.skadi.powersellsys.store;

public record SupplierStore(String account) {

    public Integer id(){
        return Integer.parseInt(account);
    }
}
