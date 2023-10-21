package team.skadi.powersellsys.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateUtil {

	public static int getTimeSpan(LocalDateTime t1, LocalDateTime t2) {
		Duration duration = Duration.between(t1, t2);
		int min = (int) duration.toMinutes();
		int hour = (int) duration.toHours();
		return min % 60 > 45 ? ++hour : hour;
	}

	public static String replaceT(LocalDateTime time) {
		return time.toString().replace('T', ' ');
	}

	/**
	 * @param text string like "2007-12-03 10:15:30"
	 * @return {@link LocalDateTime}
	 * @see LocalDateTime#parse(CharSequence)
	 */
	public static LocalDateTime parse(String text) {
		return LocalDateTime.parse(text.replace(' ', 'T'));
	}

}
