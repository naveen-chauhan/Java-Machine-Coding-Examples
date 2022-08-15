package chessvalidator.validators;

import java.util.Objects;

/**
 * @author naveen.chauhan on 16/07/22
 */
public class ChessMoveValidator {

	public boolean validateMove(String[][] chessBoard, int i1, int j1, int i2, int j2, String currentPlayer) {
		if (isInvalidCoordinate(i1, j1) ||
				isInvalidCoordinate(i2, j2) ||
				Objects.equals(chessBoard[i1][j1], "--") ||
				chessBoard[i1][j1].charAt(0) != currentPlayer.charAt(0) ||
				chessBoard[i1][j1].charAt(0) == chessBoard[i2][j2].charAt(0) ||
				(i1 == i2 && j1 == j2)) {
			return false;
		}
		String currentPiece = chessBoard[i1][j1];

		switch (currentPiece.charAt(1)) {
			case 'R':
				return isRooksMoveAllowed(i1, j1, i2, j2);
			case 'N':
				return isKnightsMoveAllowed(i1, j1, i2, j2);
			case 'B':
				return isBishopMoveAllowed(i1, j1, i2, j2);
			case 'Q':
				return isQueenMoveAllowed(i1, j1, i2, j2);
			case 'K':
				return isKnightMoveAllowed(i1, j1, i2, j2);
			case 'P':
				return isPawnMoveAllowed(chessBoard, i1, j1, i2, j2, currentPlayer);
		}

		return true;
	}

	private boolean isKnightMoveAllowed(int i1, int j1, int i2, int j2) {
		return (Math.abs(i1 - i2) < 2 && Math.abs(j1 - j2) < 2);
	}

	private boolean isQueenMoveAllowed(int i1, int j1, int i2, int j2) {
		return isRooksMoveAllowed(i1, j1, i2, j2) || isBishopMoveAllowed(i1, j1, i2, j2);
	}

	private boolean isBishopMoveAllowed(int i1, int j1, int i2, int j2) {
		return (Math.abs(i1 - i2) == Math.abs(j1 - j2));
	}

	private boolean isKnightsMoveAllowed(int i1, int j1, int i2, int j2) {
		return
				//8 Directions
				((i1 - 2 == i2 || i1 + 2 == i2) && j1 - 1 == j2) ||
						((i1 - 2 == i2 || i1 + 2 == i2) && j1 + 1 == j2) ||
						((j1 - 2 == j2 || j1 + 2 == j2) && i1 - 1 == i2) ||
						((j1 - 2 == j2 || j1 + 2 == j2) && i1 + 1 == i2);
	}

	private boolean isRooksMoveAllowed(int i1, int j1, int i2, int j2) {
		return (i1 != i2 && j1 == j2) || (i1 == i2 && j1 != j2);
	}

	private boolean isPawnMoveAllowed(String[][] chessBoard, int i1, int j1, int i2, int j2, String currentPlayer) {
		//If Next Slot not empty, Then Pawn needs to make cross Move else it can go only straight
		if (!chessBoard[i2][j2].equals("--")) {
			if (currentPlayer.equals("B")) {
				return (i1 + 1 == i2 && (j1 + 1 == j2 || j1 - 1 == j2));
			} else {
				return (i1 - 1 == i2 && (j1 + 1 == j2 || j1 - 1 == j2));
			}
		} else {
			if (currentPlayer.equals("B")) {
				if (i1 == 2) {
					return (i1 + 2 == i2 && j1 == j2);
				}
				return (i1 + 1 == i2 && j1 == j2);
			} else {
				if (i1 == 7) {
					return (i1 - 2 == i2 && j1 == j2);
				}
				return (i1 - 1 == i2 && j1 == j2);
			}
		}
	}

	private boolean isInvalidCoordinate(int i, int j) {
		return (i < 1 || i > 8 || j < 1 || j > 8);
	}


}
