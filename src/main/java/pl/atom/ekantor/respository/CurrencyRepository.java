package pl.atom.ekantor.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.atom.ekantor.model.Currency;

/**
 * Created by Artur on 18.03.2017.
 */
public interface CurrencyRepository extends JpaRepository<Currency,Long> {
    Currency findByName(String name);
    Currency findByCode(String code);
}
