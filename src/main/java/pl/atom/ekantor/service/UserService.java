package pl.atom.ekantor.service;

import pl.atom.ekantor.model.User;

/**
 * Created by Artur on 18.03.2017.
 */
public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User getLoggedUser();
}
