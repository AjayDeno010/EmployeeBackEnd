package BackEnd.service.security;


import BackEnd.entity.User;
import BackEnd.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationManager implements AuthenticationManager {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String userName= authentication.getName();
    String  password=   authentication.getCredentials().toString();
    if (ValidateUserNameAndPassword(userName,password)){
        return new UsernamePasswordAuthenticationToken(userName,password);
    }
        throw  new AuthenticationException("Invalid Credential"){

        };
    }

   public boolean ValidateUserNameAndPassword(String userName,String password){
      User user= userRepository.findByUserNameOrEmail(userName,userName)
              .orElseThrow(()-> new UsernameNotFoundException("UsernameNotFoundException"));
      boolean equalUserName= user.getUserName().equals(userName);
    boolean equalPassword=  passwordEncoder.matches(password,user.getPassword());
    return equalPassword & equalUserName;
    }
}


