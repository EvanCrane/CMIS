package Utils;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class WebUtils {

    public static String toString(User user){
        // used to pass credentials to spring security core for user authorization

        StringBuilder sb = new StringBuilder();

        sb.append("UserName:").append(user.getUsername());

        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if(authorities != null && !authorities.isEmpty()){
            sb.append(" (");
            boolean first = true;
            for(GrantedAuthority a : authorities){
                if(first){
                    sb.append(a.getAuthority());
                    first = false;
                }
                else{
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }
}
