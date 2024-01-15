package solid.bad.l;

public class ManualCar extends Car {

    int gear=0;
    int maxGear=5;

    @Override
    void drive() {
        System.out.println("Driving the Manual Car");
    }

    @Override
    void gearUp() {
        if(getCurrentGear()<5){
            this.gear++;
        }
    }

    @Override
    void gearDown() {
        if(getCurrentGear()>=1){
            this.gear--;
        }
    }

    @Override
    int getCurrentGear() {
        return gear;
    }
}
