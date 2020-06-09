package models.hh;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Employer {
    @Expose
    String name;
    @Expose
    boolean trusted;
}
