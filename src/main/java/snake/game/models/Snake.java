package snake.game.models;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author naveen.chauhan on 10/08/22
 */
public class Snake {
	//maintain the snake as linkedList

	private final LinkedList<Cell> snakeNodes;
	private Cell head;

	public Snake() {
		this.snakeNodes = new LinkedList<>();
		head = null;
	}

	public Cell getHead() {
		return head;
	}

	public LinkedList<Cell> getSnakeNodes() {
		return snakeNodes;
	}

	public void grow(Cell nextHead) {
		//add in the front
		snakeNodes.add(nextHead);
		head = nextHead;
	}

	public Cell move(Cell nextFrontHead) {
		//remove the last
		Cell removedNode = snakeNodes.poll();
		Objects.requireNonNull(removedNode).setCellType(Cell.CellType.NONE);

		//add new head
		nextFrontHead.setCellType(Cell.CellType.SNAKE);
		snakeNodes.add(nextFrontHead);
		head = nextFrontHead;

		return removedNode;
	}

	public boolean checkCrash(Cell nextCell) {
		for (Cell cell :snakeNodes) {
			if (nextCell.equals(cell)) {
				return true;
			}
		}
		return false;
	}

}
