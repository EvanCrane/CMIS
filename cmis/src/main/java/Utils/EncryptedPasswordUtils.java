package Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncryptedPasswordUtils {

    //Encrypt password with BCryptPasswordEncoder
    public static String encryptPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args){
        String password = "123";
        String encryptedPassword = encryptPassword(password);

        System.out.println("Encrypted Password:   "+ encryptedPassword);
    }

}
