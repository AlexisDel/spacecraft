package Model.Entities;

public class Mountain extends Buildings{
    public Mountain(){
        this.setCapacity(0);
        this.initInhabitants(this.getCapacity());
    }
}
