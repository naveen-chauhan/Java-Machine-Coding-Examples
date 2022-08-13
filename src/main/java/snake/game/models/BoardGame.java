package snake.game.models;

/**
 * @author naveen.chauhan on 10/08/22
 */
public class BoardGame {
	private final int rowSize;
	private final int colSize;
	private static final int RIGHT = 1, LEFT = 2, UP = 3, DOWN = 4, NONE = 0;

	private final Cell[][] board;

	private Snake snake;


	public BoardGame(int rowSize, int colSize, Snake snake) {
		this.rowSize = rowSize;
		this.colSize = colSize;
		this.board = new Cell[rowSize][colSize];
		this.snake = snake;
	}

	//move

	//generate food
	public void generateFood() {
		int rowPosition = (int) (Math.random() * rowSize);
		int colPosition = (int) (Math.random() * colSize);

		while (!isFreeForFoodPlacement(rowPosition, colPosition)) {
			rowPosition = (int) (Math.random() * rowSize);
			colPosition = (int) (Math.random() * colSize);
		}

		//Now we have got the food placement position, update the boardGame

		board[rowPosition][colPosition].setCellType(Cell.CellType.FOOD);
	}

	private boolean isFreeForFoodPlacement(int rowPosition, int colPosition) {
		if (board[rowPosition][colPosition].getCellType().equals(Cell.CellType.SNAKE)) {
			return false;
		}
		return true;
	}

	//update

	//update

	private void update(int direction) {

		int nextRowPosition = snake.getHead().getRowIndex();
		int nextColPosition = snake.getHead().getColIndex();
		//here based on the direction value, we would move the snake in that direction
		switch (direction) {
			case RIGHT:
				nextColPosition++;
				break;
			case LEFT:
				nextColPosition--;
				break;
			case UP:
				nextRowPosition--;
				break;
			case DOWN:
				nextRowPosition++;
				break;
			case NONE:
				break;
		}

		if (checkCrash(board[nextRowPosition][nextColPosition])) {
			System.out.println("Game Over");
			return;
		}


		snake.move(board[nextRowPosition][nextColPosition]);
		board[nextColPosition][nextColPosition].setCellType(Cell.CellType.SNAKE);

	}

	private boolean checkCrash(Cell nextCell) {
		for (Cell cell : snake.getSnakeNodes()) {
			if (nextCell == cell) {
				return true;
			}
		}
		return false;
	}


}