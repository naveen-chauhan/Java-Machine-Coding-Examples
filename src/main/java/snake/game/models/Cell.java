package snake.game.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author naveen.chauhan on 10/08/22
 */

@Getter
@AllArgsConstructor
public class Cell {
	private int rowIndex;
	private int colIndex;

	@Setter
	private CellType cellType;

	public enum CellType {
		FOOD,
		SNAKE,
		NONE;
	}
}
