package models.hh;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
public class Salary {
    @Expose
    int from;
    @Expose
    int to;
    @Expose
    String currency;
}
