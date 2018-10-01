package Services;

import java.util.ArrayList;
import java.util.List;

import Daos.*;
import Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDAO userDao;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Users mUser = this.userDao.findUserAccount(userName);

        if(mUser == null){
            System.out.println("Users not found! "+ userName);
            throw new UsernameNotFoundException("Users " + userName + "was not found in the database");
        }

        System.out.println("Found Users: " + mUser);

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
