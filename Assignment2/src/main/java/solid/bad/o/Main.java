package solid.bad.o;

import solid.bad.o.EmailNotifier;
import solid.good.o.User;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random=new Random();
        String otp = String.format("%04d", random.nextInt(10000));
        User user=new User("mananmistry10","manan.mistry@dal.ca","782885538");
        NotificationSender notificationSender=new NotificationSender();
        notificationSender.sendOtp(user,otp);
    }
}
