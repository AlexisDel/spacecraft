package Model.Entities;
/**class la plus globale pour les éléments du jeu*/
public abstract class Entity {
    /*attribut désignant la vie de l'entité*/
    private int life;
    /*méthode décrémentant la vie*/
    public void decrementLife(int n){
        this.life -= n;
    }
    /*getter de life*/
    public int getLife() {
        return life;
    }
}
