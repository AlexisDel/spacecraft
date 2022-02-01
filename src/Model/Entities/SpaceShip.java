package Model.Entities;

public class SpaceShip extends Buildings{

    public SpaceShip(){
        this.setCapacity(3);
        this.initInhabitants(this.getCapacity());
    }
}
