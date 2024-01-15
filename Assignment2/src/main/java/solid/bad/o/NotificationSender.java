package solid.bad.o;

import solid.bad.o.EmailNotifier;
import solid.good.o.User;

public class NotificationSender {
    public void sendOtp(User user,String message){
        EmailNotifier emailNotifier=new EmailNotifier();
        emailNotifier.notify(user.getEmail(),message);
    }
}
