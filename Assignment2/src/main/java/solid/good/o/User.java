package solid.good.o;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    String userName;
    String email;
    String phoneNumber;
}
