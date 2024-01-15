package solid.bad.l;

public class CarPilot {
    void driveTheCar(Car car){
        if(car.getCurrentGear()==0){
            car.gearUp();
        }
        car.drive();
    }
}
