package solid.good.o;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        User user=new User("mananmistry10","mananmistry10@gmail.com","7828825538");
        AppNotifier appNotifier=new AppNotifier();
        EmailNotifier emailNotifier=new EmailNotifier();
        SMSNotifier smsNotifier=new SMSNotifier();

        NotificationSender notificationSender=new NotificationSender();
        notificationSender.sendOtp(appNotifier,user);
        notificationSender.sendOtp(emailNotifier,user);
        notificationSender.sendOtp(smsNotifier,user);

    }
}
