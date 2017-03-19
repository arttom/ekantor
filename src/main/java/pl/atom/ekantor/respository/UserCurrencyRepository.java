package pl.atom.ekantor.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.atom.ekantor.model.User;
import pl.atom.ekantor.model.UserCurrency;

import java.util.List;

/**
 * Repository for UserCurrency entity class
 * Created by Artur on 18.03.2017.
 */
public interface UserCurrencyRepository extends JpaRepository<UserCurrency,Long>{
    List<UserCurrency> getByUser(User user);
}
