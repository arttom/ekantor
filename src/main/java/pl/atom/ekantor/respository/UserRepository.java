package pl.atom.ekantor.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.atom.ekantor.model.User;

/**
 * Created by Artur on 18.03.2017.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
