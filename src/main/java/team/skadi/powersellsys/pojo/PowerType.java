package team.skadi.powersellsys.pojo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PowerType {
	private Integer id;
	private String value;

	@Override
	public String toString() {
		return value;
	}
}
