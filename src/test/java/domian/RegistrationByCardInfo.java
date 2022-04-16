package domian;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationByCardInfo {

   private final String city;
   private final String name;
   private final String phoneNumber;
}
