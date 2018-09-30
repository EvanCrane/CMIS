package Services;

import java.util.ArrayList;
import java.util.List;

import Daos.*;
import Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDAO userDao;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User mUser = this.userDao.findUserAccount(userName);

        if(mUser == null){
            System.out.println("User not found! "+ userName);
            throw new UsernameNotFoundException("User " + userName + "was not found in the database");
        }

        System.out.println("Found User: " + mUser);

        // [ROLE_USER, ROLE_ADMIN, ..]
        List<String> roleNames = this.roleDAO.getRoleNames(mUser.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if(roleNames != null){
            for(String role: roleNames){
                //ROLE_READER, ROLE_ADMIN, ROLE_MANAGER, ...
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(mUser.getUserName(), //
            mUser.getEncryptedPassword(), grantList);

        return userDetails;
    }
}
