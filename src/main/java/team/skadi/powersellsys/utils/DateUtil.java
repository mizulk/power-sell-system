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

}
