package snakeandladder;

import snakeandladder.models.Item;
import snakeandladder.models.PositionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author naveen.chauhan on 01/07/22
 */
public class SnakeAndLadderGameBoard {
	private List<PositionEntity> snakePosition;
	private List<PositionEntity> ladderPosition;

	public Item isItemPresent(int position) {
		if (position == 100) {
			return Item.NOTHING;
		}

		for (PositionEntity positionEntity : snakePosition) {
			if (positionEntity.getStartPosition() == position) {
				return Item.SNAKE;
			}
		}

		for (PositionEntity positionEntity : ladderPosition) {
			if (positionEntity.getStartPosition() == position) {
				return Item.LADDER;
			}
		}

		return Item.NOTHING;
	}

	public SnakeAndLadderGameBoard() {
		this.snakePosition = new ArrayList<>();
		this.ladderPosition = new ArrayList<>();
	}

	public List<PositionEntity> getSnakePosition() {
		return snakePosition;
	}

	public List<PositionEntity> getLadderPosition() {
		return ladderPosition;
	}

	public int getEndPosition(Item item, int startPosition) {
		if (Item.LADDER.equals(item)) {
			Optional<PositionEntity> positionEntity = ladderPosition
					.stream()
					.filter(p -> p.getStartPosition() == startPosition)
					.findAny();
			return positionEntity.map(PositionEntity::getEndPosition).orElse(startPosition);
		} else if (Item.SNAKE.equals(item)) {
			Optional<PositionEntity> positionEntity = snakePosition
					.stream()
					.filter(p -> p.getStartPosition() == startPosition)
					.findAny();
			return positionEntity.map(PositionEntity::getEndPosition).orElse(startPosition);
		}
		return startPosition;
	}
}
