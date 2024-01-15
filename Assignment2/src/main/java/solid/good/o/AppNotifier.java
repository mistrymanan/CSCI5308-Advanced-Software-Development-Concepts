package solid.good.o;

public class AppNotifier implements Notifier{
    @Override
    public void notify(User user,String message) {
        System.out.println("Sending Notification to"+user.getUserName()+" on Application");
        System.out.println("Message->"+message);
    }
}
