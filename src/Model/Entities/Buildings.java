package Model.Entities;

public abstract class Buildings extends Entity {
    // Capacité d'accueuil du bâtiment
    private int capacity;

    //Liste d'entité logeant dans le bâtiment
    private Entity[] inhabitants;

    // Setter de capacity
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    //Getter de capacity
    public int getCapacity() {
        return capacity;
    }

    // Initialiseur de inhabitants
    public void initInhabitants(int n){
        this.inhabitants = new Entity[n];
    }
    // Getter de inhabitants
    public Entity[] getInhabitants() {
        return inhabitants;
    }
}
