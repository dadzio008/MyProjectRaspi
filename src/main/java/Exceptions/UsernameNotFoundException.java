package Exceptions;

public class UsernameNotFoundException extends ClassNotFoundException{
    public UsernameNotFoundException(String username) {
    }

    public String UsernameNotFoundException(String username) {
        return ("User not found");
    }


}
