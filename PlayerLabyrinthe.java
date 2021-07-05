package piles;

import java.util.Scanner;

public class PlayerLabyrinthe {
    public PlayerLabyrinthe() {
        Labyrinthe l = new Labyrinthe();
        System.out.println("Treasure at " + l.getTreasureIndex());

        while(!l.isGameOver()) {
            System.out.println(l);
            switch (this.getInput()) {
                case "u" -> l.moveUp();
                case "d" -> l.moveDown();
                case "l" -> l.moveLeft();
                case "r" -> l.moveRight();
            }
            l.isTreasureTakenPiece();
        }
        System.out.println("Congratulations !");
    }

    public String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
