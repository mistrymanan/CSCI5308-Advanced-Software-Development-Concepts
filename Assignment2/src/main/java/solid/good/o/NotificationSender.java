package solid.good.o;

import java.util.Random;

public class NotificationSender {
    public void sendOtp(Notifier notifier,User user){
        // generates otp
        Random random=new Random();
        String otp = String.format("%04d", random.nextInt(10000));
        notifier.notify(user,otp);
    }
}
