package models.users;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class SingleUser {

    @Expose
    User data;
}
