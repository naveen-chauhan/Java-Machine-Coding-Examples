package snakeandladder.models;

/**
 * @author naveen.chauhan on 01/07/22
 */
public class PositionEntity {
    private int startPosition;
    private int endPosition;

    public PositionEntity(int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }
}
