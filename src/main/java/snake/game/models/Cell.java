package snake.game.models;

/**
 * @author naveen.chauhan on 10/08/22
 */
public class Cell {
	private int rowIndex;
	private int colIndex;
	private CellType cellType;

	public enum CellType {
		FOOD,
		SNAKE,
		NONE;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
}
