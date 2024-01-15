package solid.good.o;

public class EmailNotifier implements Notifier {
    @Override
    public void notify(User user,String message) {
        System.out.println("Sending Email Notification ->"+user.getEmail());
        System.out.println("Message->"+message);
    }
}
