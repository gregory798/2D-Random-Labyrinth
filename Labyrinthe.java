package piles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Attention:
 * x = colonnes
 * y = lignes
 * x = 7
 * y = 6
 *  = 7,6
 */

public class Labyrinthe {
    private int taille = 20;
    private int[][] plateau;
    private int treasureX;
    private int treasureY;
    private int pieceX;
    private int pieceY;
    private int explorerX;
    private int explorerY;
    private boolean treasureTakenPiece;
    private boolean treasureTakenExplorer;
    private List<String> marked = new ArrayList<>();
    private PileAL<String> path;
    public Labyrinthe() {
        plateau = new int[taille][taille];
        path = new PileAL<>();
        for (int i = 0; i < plateau.length ; i++) {
            for (int j = 0; j < plateau.length; j++) {
                plateau[i][j] = 1;
            }
        }
        this.generateObstacle();
        this.generateTreasure();
        this.generatePiece();
        this.generateExplorer();
        this.getPath();

    }

    public boolean isMarked(String index) {
        return marked.contains(index);
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int[] ints : this.plateau) {
            s.append("|\t");
            for (int j = 0; j < this.plateau.length; j++) {
                s.append(ints[j]).append("\t");
            }
            s.append("|\n");
        }
        return s.toString();
    }

    public void generateObstacle() {
        Random r = new Random();
        for (int i = 0; i < this.plateau.length ; i++) {
            for (int j = 0; j < this.plateau.length; j++) {
                int x = r.nextInt(5);
                if (x > 3) this.setAtIndex(i,j,0);
            }
        }
    }

    public void generateTreasure() {
        Random r = new Random();
        int x = r.nextInt(this.plateau.length);
        int y = r.nextInt(this.plateau.length);
        this.setAtIndex(x,y,2);
        this.treasureX = x;
        this.treasureY = y;
    }

    public void generatePiece() {
        this.setAtIndex(0,0,3);
        this.setPieceIndex(0,0);
    }

    public void generateExplorer() {
        this.setAtIndex(0,0,4);
        this.setExplorer(0,0,4);
    }

    public void setPieceIndex(int x, int y) {
        this.pieceX = x;
        this.pieceY = y;
    }

    public int getPieceX() {
        return this.pieceX;
    }

    public int getPieceY() {
        return this.pieceY;
    }

    public int getExplorerX() {
        return this.explorerX;
    }

    public int getExplorerY() {
        return this.explorerY;
    }


    public int getAtIndex(int x, int y) {
        return this.plateau[x][y];
    }

    public String getTreasureIndex() {
        return this.treasureX + "," + this.treasureY;
    }

    public String getExplorerIndex() {
        return this.explorerX + "," + this.explorerY;
    }

    public String getPieceIndex() {
        return this.pieceX + "," + this.pieceY;
    }

    public void setAtIndex(int x, int y, int z) {
        this.plateau[x][y] = z;
    }

    public void setExplorer(int x, int y, int z) {
        this.plateau[x][y] = z;
        this.explorerX = x;
        this.explorerY = y;
    }

    public boolean isGameOver() {
        if (treasureTakenPiece) {
            return this.getPieceIndex().equals("0,0");
        }
        else {
            return false;
        }
    }

    public void isTreasureTakenPiece() {
        if (this.getPieceIndex().equals(getTreasureIndex())) treasureTakenPiece = true;
    }

    public void isTreasureTakenExplorer() {
        if (this.getExplorerIndex().equals(getTreasureIndex())) treasureTakenExplorer = true;
    }

    public void moveUp() {
        if (this.getPieceX() == 0) System.out.println("Already UP !");
        else {
            this.setAtIndex(this.getPieceX() - 1 , this.getPieceY(), 3);
            this.setAtIndex(this.getPieceX(), this.getPieceY(), 1);
            this.setPieceIndex(this.getPieceX() - 1,this.getPieceY());
        }
    }

    public void moveDown() {
        if (this.getPieceX() > this.taille - 2) System.out.println("Already DOWN !");
        else {
            this.setAtIndex(this.getPieceX() + 1, this.getPieceY(), 3);
            this.setAtIndex(this.getPieceX(), this.getPieceY(), 1);
            this.setPieceIndex(this.getPieceX() + 1,this.getPieceY());
        }
    }

    public void moveLeft() {
        if (this.getPieceY() == 0) System.out.println("Already LEFT !");
        else {
            this.setAtIndex(this.getPieceX(), this.getPieceY() - 1, 3);
            this.setAtIndex(this.getPieceX(), this.getPieceY(), 1);
            this.setPieceIndex(this.getPieceX(), this.getPieceY() - 1);
        }
    }

    public void moveRight() {
        if (this.getPieceY() > this.taille - 2) System.out.println("Already RIGHT !");
        else {
            this.setAtIndex(this.getPieceX(), this.getPieceY() + 1, 3);
            this.setAtIndex(this.getPieceX(), this.getPieceY(), 1);
            this.setPieceIndex(this.getPieceX(), this.getPieceY() + 1);
        }
    }

    public String inString(int x, int y) {
        return x + "," + y;
    }


    public boolean pMoveUp() {
        if (!(this.getExplorerX() == 0)) {
            if (!(this.getAtIndex(this.getExplorerX() - 1, getExplorerY()) == 0)) {
                if (!this.isMarked(this.inString(this.getExplorerX() - 1, this.getExplorerY()))) {
                    this.marked.add(this.getExplorerIndex());
                    this.setExplorer(this.getExplorerX() - 1, this.getExplorerY(), 4);
                    this.setAtIndex(this.getExplorerX(), this.getExplorerY(), 5);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pMoveDown() {
        if (!(this.getExplorerX() == this.taille - 1)) {
            if (!(this.getAtIndex(this.getExplorerX() + 1, getExplorerY()) == 0)) {
                if (!this.isMarked(this.inString(this.getExplorerX() + 1, this.getExplorerY()))) {
                    this.marked.add(this.getExplorerIndex());
                    this.setExplorer(this.getExplorerX() + 1, this.getExplorerY(), 4);
                    this.setAtIndex(this.getExplorerX(), this.getExplorerY(), 5);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean pMoveRight() {
        if (!(this.getExplorerY() == this.taille - 1)) {
            if (!(this.getAtIndex(this.getExplorerX(), getExplorerY() + 1) == 0)) {
                if (!this.isMarked(this.inString(this.getExplorerX() , this.getExplorerY() + 1))) {
                    this.marked.add(this.getExplorerIndex());
                    this.setExplorer(this.getExplorerX(), this.getExplorerY() + 1, 4);
                    this.setAtIndex(this.getExplorerX(), this.getExplorerY(), 5);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pMoveLeft() {
        if (!(this.getExplorerY() == 0)) {
            if (!(this.getAtIndex(this.getExplorerX(), getExplorerY() - 1) == 0)) {
                if (!this.isMarked(this.inString(this.getExplorerX() , this.getExplorerY() - 1))) {
                    this.marked.add(this.getExplorerIndex());
                    this.setExplorer(this.getExplorerX(), this.getExplorerY() - 1, 4);
                    this.setAtIndex(this.getExplorerX(), this.getExplorerY(), 5);
                    return true;
                }
            }
        }
        return false;
    }

    public int xToInt(String s) {
        String[] sp = s.split(",");
        return Integer.parseInt((sp[0]));
    }

    public int yToInt(String s) {
        String[] sp = s.split(",");
        return Integer.parseInt((sp[1]));
    }



    public void getPath() {

        for (int i = 0; i < taille*taille; i++) {
            this.isTreasureTakenExplorer();
            if (treasureTakenExplorer) break;

            if (this.pMoveUp()) {
                this.path.empiler(this.getExplorerIndex());
            }
            else if(this.pMoveRight()) {
                this.path.empiler(this.getExplorerIndex());
            }
            else if(this.pMoveDown()) {
                this.path.empiler(this.getExplorerIndex());
            }
            else if(this.pMoveLeft()) {
                this.path.empiler(this.getExplorerIndex());
            }
            else {
                this.marked.add(this.getExplorerIndex());
                this.path.depiler();
                this.setExplorer(this.xToInt(this.path.sommet()), this.yToInt(this.path.sommet()), 5);
            }
        }
        System.out.println(path);
    }
}
