package solid.good.l;

public class AudiManualCar implements ManualTransmittableCar {
    int gear=0;
    int maxGear=5;
    @Override
    public void drive() {
        System.out.println("Driving Manual Car");
    }

    @Override
    public void gearUp() {
        if(getCurrentGear()<5){
            this.gear++;
        }
    }

    @Override
    public void gearDown() {
        if(getCurrentGear()>=1){
            this.gear--;
        }
    }

    @Override
    public int getCurrentGear() {
        return this.gear;
    }
}
