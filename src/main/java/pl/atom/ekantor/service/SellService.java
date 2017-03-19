package pl.atom.ekantor.service;

import pl.atom.ekantor.dto.TransactionStatus;
import pl.atom.ekantor.model.User;

/**
 * Created by Atom on 19.03.2017.
 */
public interface SellService {
    TransactionStatus sellCurrency(User user, String currencyCode, Long units);
}
