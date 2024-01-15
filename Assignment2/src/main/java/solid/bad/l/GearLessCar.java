package solid.bad.l;

public class GearLessCar extends Car {
    @Override
    void drive() {
        System.out.println("Driving GearLessAutomatic Car");
    }

    @Override
    void gearUp() {
        // as car is Gear less, no need to implement this method
    }

    @Override
    void gearDown() {
        // as car is Gear less, no need to implement this method
    }

    @Override
    int getCurrentGear() {
        // as car is Gear less, no need to implement this method
        return 0;
    }
}
