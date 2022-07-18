package boardgame2048.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author naveen.chauhan on 18/07/22
 */
public class BoardGameSvc {
   private String[][] board;

   public String[][] getBoard() {
      return board;
   }

   private BoardGameSvc(String[][] board) {
      this.board = board;
   }

   public static BoardGameSvc init() {
      String[][] board = new String[5][5];

      for (String[] characters: board) {
         characters = new String[5];
         Arrays.fill(characters, "-");
      }

      Random r = new Random();
      int firstRandomXIndex = r.nextInt() % 4 + 1;
      int firstRandomYIndex = r.nextInt() % 4 + 1;
      board[firstRandomXIndex][firstRandomYIndex] = "2";
      int secondRandomXIndex = r.nextInt() % 4 + 1;
      int secondRandomYIndex = r.nextInt() % 4 + 1;
      while ((secondRandomYIndex == firstRandomYIndex && secondRandomXIndex == firstRandomXIndex)) {
         secondRandomXIndex = r.nextInt() % 4 + 1;
         secondRandomYIndex = r.nextInt() % 4 + 1;
      }

      board[secondRandomXIndex][secondRandomYIndex] = "2";

      return new BoardGameSvc(board);
   }

   private boolean addRandomTiles(String[][] board) {
      if (!isPossibleToAddTiles(board)) {
         return false;
      }

      Random r = new Random();
      int firstRandomXIndex = r.nextInt() % 4 + 1;
      int firstRandomYIndex = r.nextInt() % 4 + 1;

      while (!board[firstRandomXIndex][firstRandomYIndex].equals("-")) {
         firstRandomXIndex = r.nextInt() % 4 + 1;
         firstRandomYIndex = r.nextInt() % 4 + 1;
      }
      board[firstRandomXIndex][firstRandomYIndex] = "2";
      return true;
   }

   public boolean isPossibleToAddTiles(String[][] board) {
      int count = 0;
      for (int i = 1;i<5; i++) {
         for (int j=1; j<5 ;j++) {
            if (board[i][j].equals("-")) {
               count++;
            }
         }
      }
      return (count > 0);
   }

   public boolean isPlayerWon() {
      for (int i = 1;i<5; i++) {
         for (int j=1; j<5 ;j++) {
            if (board[i][j].equals("2048")) {
               return true;
            }
         }
      }
      return false;
   }

   public void showBoard() {
      for (int i = 1; i<5; i++) {
         for(int j = 1; j<5; j++) {
            System.out.print(board[i][j] + " ");
         }
         System.out.println();
      }
   }

   public boolean moveLeft() {
      boolean isTilesMoved = false;
      for (int i = 1; i < 5; i++) {
         ArrayList<String> numbers = new ArrayList<>();
         int j = 1;
         while(j<5) {
            if (j == 4) {
               if (!board[i][j].equals("-")) {
                  numbers.add(board[i][j]);
               }
            } else {
               if (board[i][j].equals("-")) {
                  j++;
                  continue;
               } else if (board[i][j].equals(board[i][j+1])) {
                  String value = getSum(board[i][j], board[i][j+1]);
                  board[i][j+1] = "-";
                  numbers.add(value);
                  isTilesMoved = true;
               } else {
                  numbers.add(board[i][j]);
               }
            }
            j++;
         }
          j = 1;
          int counter = 0;
         for (; j<5; j++) {
            if (counter < numbers.size()) {
               board[i][j] = numbers.get(counter);
            } else {
               board[i][j] = "-";
            }
            counter++;
         }
      }
      addRandomTiles(board);
      return isTilesMoved;
   }

   private String getSum(String s1, String s2) {
      return Integer.toString(Integer.parseInt(s1) + Integer.parseInt(s2));
   }

   public boolean moveRight() {
      boolean isTilesMoved = false;
      for (int i = 1; i < 5; i++) {
         ArrayList<String> numbers = new ArrayList<>();
         int j = 4;
         while(j>0) {
            if (j == 1) {
               if (!board[i][j].equals("-")) {
                  numbers.add(board[i][j]);
               }
            } else {
               if (board[i][j].equals("-")) {
                  j--;
                  continue;
               } else if (board[i][j].equals(board[i][j-1])) {
                  String value = getSum(board[i][j], board[i][j-1]);
                  board[i][j-1] = "-";
                  numbers.add(value);
                  isTilesMoved = true;
               } else {
                  numbers.add(board[i][j]);
               }
            }
            j--;
         }
         j = 4;
         int counter = 0;
         for (; j>0; j--) {
            if (counter < numbers.size()) {
               board[i][j] = numbers.get(counter);
            } else {
               board[i][j] = "-";
            }
            counter++;
         }
      }
      addRandomTiles(board);
      return isTilesMoved;
   }

   public boolean moveTop() {
      boolean isTilesMoved = false;
      for (int i = 1; i < 5; i++) {
         ArrayList<String> numbers = new ArrayList<>();
         int j = 1;
         while(j<5) {
            if (j == 4) {
               if (!board[j][i].equals("-")) {
                  numbers.add(board[j][i]);
               }
            } else {
               if (board[j][i].equals("-")) {
                  j++;
                  continue;
               } else if (board[j][i].equals(board[j+1][i])) {
                  String value = getSum(board[j][i], board[j+1][i]);
                  board[j+1][i] = "-";
                  numbers.add(value);
                  isTilesMoved = true;
               } else {
                  numbers.add(board[j][i]);
               }
            }
            j++;
         }
         j = 1;
         int counter = 0;
         for (; j<5; j++) {
            if (counter < numbers.size()) {
               board[j][i] = numbers.get(counter);
            } else {
               board[j][i] = "-";
            }
            counter++;
         }
      }
      addRandomTiles(board);
      return isTilesMoved;
   }

   public boolean moveBottom() {
      boolean isTilesMoved = false;
      for (int i = 1; i < 5; i++) {
         ArrayList<String> numbers = new ArrayList<>();
         int j = 4;
         while(j>0) {
            if (j == 1) {
               if (!board[j][i].equals("-")) {
                  numbers.add(board[j][i]);
               }
            } else {
               if (board[j][i].equals("-")) {
                  j--;
                  continue;
               } else if (board[j][i].equals(board[j][i-1])) {
                  String value = getSum(board[j][i], board[j][i-1]);
                  board[j][i-1] = "-";
                  numbers.add(value);
                  isTilesMoved = true;
               } else {
                  numbers.add(board[j][i]);
               }
            }
            j--;
         }
         j = 4;
         int counter = 0;
         for (; j>0; j--) {
            if (counter < numbers.size()) {
               board[j][i] = numbers.get(counter);
            } else {
               board[j][i] = "-";
            }
            counter++;
         }
      }
      addRandomTiles(board);
      return isTilesMoved;
   }


   public boolean isMergingPossible() {
      int[] movex = new int[]{-1, 1, 0, 0};
      int[] movey = new int[]{0, 0, 1, -1};
      for (int i = 1; i<5; i++) {
         for (int j =1 ;j<5; j++) {
            if (!board[i][j].equals("-")) {
               for (int k = 0; k<4; k++) {
                  int adjacentX = i + movex[k];
                  int adjacentY = j + movey[k];
                  if (adjacentX > 0 && adjacentX < 5 && adjacentY > 0 && adjacentY < 5 && board[adjacentX][adjacentY].equals(board[i][j])) {
                     return true;
                  }
               }
            }
         }
      }
      return false;
   }
}
