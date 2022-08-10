package snake.game.models;

import java.util.LinkedList;

/**
 * @author naveen.chauhan on 10/08/22
 */
public class Snake {
	//maintain the snake as linkedList

	private LinkedList<Cell> snakeNodes;
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
		Cell removedNode = snakeNodes.poll();
		nextFrontHead.setCellType(Cell.CellType.SNAKE);
		snakeNodes.add(nextFrontHead);
		head = nextFrontHead;
		removedNode.setCellType(Cell.CellType.NONE);
		return removedNode;
	}

}
