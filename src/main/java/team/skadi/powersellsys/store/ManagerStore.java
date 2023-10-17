package team.skadi.powersellsys.store;

/**
 * 记录类，java16 新增加的类型，所有变量都写在括号里面，并且都是只读的<br><br>
 * 所以每次想要更新数据都需要从新new一个
 * @param jobNumber
 */
public record ManagerStore(short jobNumber) {

}
