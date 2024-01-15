package solid.good.i.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import solid.good.i.Languages;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    String code;
    Languages language;
}
