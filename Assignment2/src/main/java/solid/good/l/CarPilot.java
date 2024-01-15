package solid.good.l;

import solid.good.l.Car;

public class CarPilot {

    void driveTheCar(ManualTransmittableCar car){
        if(car.getCurrentGear()==0){
            car.gearUp();
        }
        car.drive();
    }

}
