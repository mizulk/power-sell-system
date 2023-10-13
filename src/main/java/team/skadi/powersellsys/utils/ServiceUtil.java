package team.skadi.powersellsys.utils;

import team.skadi.powersellsys.service.Service;

import java.util.HashMap;

public class ServiceUtil {

	private static final HashMap<Class<? extends Service>, Service> map = new HashMap<>();

	/**
	 * 获得指定的service实现类，使用方法如下：
	 * <blockquote><pre>
	 *     UserService userService = ServiceUtil.getService(UserService.class);
	 *     userService.getAllUser();// 使用方法
	 * </pre></blockquote>
	 *
	 * @param serviceClass 实现了{@link Service}的接口
	 * @return 指定接口的实现类
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Service> T getService(Class<T> serviceClass) {
		T t = (T) map.get(serviceClass);
		if (t == null) {
			StringBuilder sb = new StringBuilder();
			sb.append(serviceClass.getPackageName());
			sb.append(".impl.");
			sb.append(serviceClass.getSimpleName());
			sb.append("Impl");
			try {
				Class<T> aClass = (Class<T>) Class.forName(sb.toString());
				t = aClass.getDeclaredConstructor().newInstance();
				map.put(serviceClass, t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}
}
