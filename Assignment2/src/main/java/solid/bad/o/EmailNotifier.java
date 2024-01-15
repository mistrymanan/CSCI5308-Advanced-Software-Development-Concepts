package solid.bad.o;

public class EmailNotifier{
    public void notify(String email,String message){
        System.out.println("Sending Email Notification to ->"+email);
        System.out.println("Message->"+message);
    }
}
