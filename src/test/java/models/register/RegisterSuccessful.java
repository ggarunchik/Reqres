package models.register;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class RegisterSuccessful {
    //вешаю тут Expose только потому что пример статичен. Так эти поля динамичны должны быть
    @Expose
    int id;
    @Expose
    String token;
}
