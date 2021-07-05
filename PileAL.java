package piles;
import java.util.ArrayList;

public class PileAL<T> implements Pile<T> {
    private int top = -1;
    private ArrayList<T> tabPile;
    public PileAL() {
        tabPile = new ArrayList<T>();
    }

    @Override
    public boolean vide() {
        return this.top==-1;
    }

    @Override
    public void empiler(T o) {
        tabPile.add(o);
        top++;
    }

    @Override
    public void depiler() {
        tabPile.remove(top);
        top--;
    }

    @Override
    public T sommet() {
        return tabPile.get(top);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tabPile.size() ; i++) {
            if (i == tabPile.size()-1) s.append(tabPile.get(i));
            else s.append(tabPile.get(i)).append("||");
        }
        return "[" + s.toString() + "]";
    }

    public void inverser(){  // this est inversé
        PileAL<T> p1 = new 	PileAL<T>();
        PileAL<T> p2 = new 	PileAL<T>();
        // 1 er transvasement depuis this vers p1
        while(!this.vide()){
            p1.empiler(this.sommet());
            this.depiler();
        }// this est vide

        // 2 e transvasement depuis p1 vers p2
        while(!p1.vide()){
            p2.empiler(p1.sommet());
            p1.depiler();
        }// p2 est identique à this initial

        // 3 e transvasement depuis p2 vers this
        while(!p2.vide()){
            this.empiler(p2.sommet());
            p2.depiler();
        }

    }

    public PileAL<T> inverserT(){ // this est intact et son inverse est retourné
        PileAL<T> p1 = new 	PileAL<T>();
        PileAL<T> p2 = new 	PileAL<T>();
        // 1er transvasement depuis this vers p1
        while(!this.vide()){
            p1.empiler(this.sommet());
            this.depiler();
        }// this est vide
        // 2e transvasement depuis p1 vers p2 et vers this
        while(!p1.vide()){
            p2.empiler(p1.sommet());
            this.empiler(p1.sommet());
            p1.depiler();
        }// p1 est vide, this et p2 seront identiques à this initial
        // 3e transvasement depuis p2 vers p1
        while(!p2.vide()){
            p1.empiler(p2.sommet());
            p2.depiler();
        }
        return p1;

    }

    public void supprime(T val ){
        int c = 0;
        PileAL<T> p= new PileAL<T>();
        while ( !this.vide()) {
            if( this.sommet() == val) {c++; this.depiler();}
            else {p.empiler(this.sommet()) ; this.depiler(); }
            }

        while (!p.vide()) {
            this.empiler(p.sommet()) ;
            p.depiler();
        }
    }
}



