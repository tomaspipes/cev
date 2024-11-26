package src;

import java.util.Scanner;

public class Main {
      public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String game = sc.nextLine();
        
        GameFactory gc = new GameFactory();        
        
        gc.getGame(game);
        sc.close();
    }
}
