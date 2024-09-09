package org.carbonit.jmeritte.entity;

public abstract class Case {

    int x;
    int y;

    boolean visitable = true;

    protected Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void activerEffetCaseSurPersonnage(Personnage personnage) {
        // A Implémenter
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisitable() {
        return visitable;
    }

    public void setVisitable(boolean visitable) {
        this.visitable = visitable;
    }
}
