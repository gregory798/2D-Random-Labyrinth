package piles;

public interface Pile<T> {
    boolean vide();
    void empiler (T o);
    void depiler();
    T sommet();
    void inverser();
    PileAL<T> inverserT();
    void supprime(T val);
    String toString();
}