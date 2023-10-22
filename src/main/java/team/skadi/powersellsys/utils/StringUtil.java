package team.skadi.powersellsys.utils;

import java.text.DecimalFormat;

public class StringUtil {

	public static final DecimalFormat INTEGER_FORMAT = new DecimalFormat("###,###,###,###");
	public static final DecimalFormat FLOAT_FORMAT = new DecimalFormat("###,###,###,###.###");

	public static String formatDiscountPrice(Float price, Byte discount) {
		return discount != 0 ?
				String.format("%s (%s)",
						FLOAT_FORMAT.format(price * (1 - discount * 0.01)),
						INTEGER_FORMAT.format(price))
				: FLOAT_FORMAT.format(price);
	}

	public static String formatInt(Integer integer) {
		return INTEGER_FORMAT.format(integer);
	}

	public static String formatDiscount(Byte discount) {
		return discount == 0 ? "无" : String.format("-%d%%OFF", discount);
	}
}
