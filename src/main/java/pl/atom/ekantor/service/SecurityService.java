package pl.atom.ekantor.service;

/**
 * Created by Artur on 18.03.2017.
 */
public interface SecurityService {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
