package snakeandladder.models;

/**
 * @author naveen.chauhan on 01/07/22
 */
public class PlayerInfo {
   private final String name;
   private int currentPosition;

   public PlayerInfo(String name) {
      this.name = name;
      this.currentPosition = 0;
   }

   public void setCurrentPosition(int currentPosition) {
      this.currentPosition = currentPosition;
   }

   public String getName() {
      return name;
   }

   public int getCurrentPosition() {
      return currentPosition;
   }

   public boolean isPlayerWon() {
      return (currentPosition == 100);
   }
}
