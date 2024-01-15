package solid.good.o;

public class SMSNotifier  implements Notifier {
    @Override
    public void notify(User user,String message) {
        System.out.println("Sending SMS to ->"+ user.getUserName()+" on -> Phone->"+user.getPhoneNumber());
        System.out.println("Message->"+message);
    }
}
