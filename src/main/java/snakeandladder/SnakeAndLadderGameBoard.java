package snakeandladder;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import snakeandladder.models.Item;
import snakeandladder.models.PositionEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author naveen.chauhan on 01/07/22
 */
@Getter
@Slf4j
public class SnakeAndLadderGameBoard {

	//Here instead of using List, We could have used Map<Integer (startPosition), Integer (endPosition)> snakePositionMap and similar DS for ladder

	private final HashMap<Integer, Integer> snakePositionMap;
	private final HashMap<Integer, Integer> ladderPositionMap;

	public SnakeAndLadderGameBoard() {
		this.ladderPositionMap = new HashMap<>();
		this.snakePositionMap = new HashMap<>();
	}

	public void addLadder(int startPosition, int endPosition) {
		//couple of sanity check. start should not be 0
		if ((startPosition < 2 || endPosition > 100) || startPosition >= endPosition)  {
			log.error("Ladder cannot be positioned here");
		}

		ladderPositionMap.put(startPosition, endPosition);
	}

	public void addSnake(int startPosition, int endPosition) {
		if ((startPosition < 2 || endPosition > 100) || startPosition <= endPosition)  {
			log.error("Snake cannot be positioned here");
		}
		snakePositionMap.put(startPosition, endPosition);
	}

	public Item isItemPresent(int position) {
		if (position == 100) {
			return Item.NOTHING;
		}

		if (snakePositionMap.containsKey(position)) {
			return Item.SNAKE;
		}

		if (ladderPositionMap.containsKey(position)) {
			return Item.LADDER;
		}

		return Item.NOTHING;
	}

	public int getEndPosition(Item item, int startPosition) {
		if (Item.LADDER.equals(item)) {
			return ladderPositionMap.get(startPosition);
		} else if (Item.SNAKE.equals(item)) {
			return snakePositionMap.get(startPosition);
		}
		return startPosition;
	}
}
