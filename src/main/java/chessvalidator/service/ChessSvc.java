package chessvalidator.service;

import chessvalidator.validators.ChessMoveValidator;

/**
 * @author naveen.chauhan on 16/07/22
 */
public class ChessSvc {
	private String[][] chessBoard;
	private ChessMoveValidator chessMoveValidator;
	private static final String[] CHESS_PIECES = new String[]{"R", "N", "B", "Q", "K", "P"};

	public String[][] getChessBoard() {
		return chessBoard;
	}

	private ChessSvc(String[][] chessBoard) {
		this.chessBoard = chessBoard;
		this.chessMoveValidator = new ChessMoveValidator();
	}

	public static ChessSvc initiateBoard() {
		String[][] board = new String[9][9];
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				String playerIdentifer = (i < 3) ? "B" : "W";
				String pieceIdentifier = "-";
				if (i == 1 || i == 8) {
					pieceIdentifier = (j < 6) ? CHESS_PIECES[j - 1] : CHESS_PIECES[9 - j - 1];
					board[i][j] = playerIdentifer + pieceIdentifier;
				} else if (i == 2 || i == 7) {
					pieceIdentifier = CHESS_PIECES[5];
					board[i][j] = playerIdentifer + pieceIdentifier;
				} else {
					board[i][j] = "--";
				}
			}
		}
		return new ChessSvc(board);
	}

	public void printBoard() {
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				System.out.print(chessBoard[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void movePiece(String[] commands, String currentPlayer) {
		String pieceCoordinate = commands[0];
		String nextPositionCoordinate = commands[1];
		int j1 = (pieceCoordinate.charAt(0) - 'a') + 1;
		int j2 = (nextPositionCoordinate.charAt(0) - 'a') + 1;
		int i1 = 8 - Integer.parseInt(String.valueOf(pieceCoordinate.charAt(1))) + 1;
		int i2 = 8 - Integer.parseInt(String.valueOf(nextPositionCoordinate.charAt(1))) + 1;

		if (chessMoveValidator.validateMove(chessBoard, i1, j1, i2, j2, currentPlayer)) {
			String piece = chessBoard[i1][j1];
			chessBoard[i1][j1] = "--";
			chessBoard[i2][j2] = piece;
			printBoard();
		} else {
			System.out.println("Invalid Move");
		}
	}
}
