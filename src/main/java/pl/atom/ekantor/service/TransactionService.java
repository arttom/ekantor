package pl.atom.ekantor.service;

import pl.atom.ekantor.model.Currency;
import pl.atom.ekantor.model.User;
import pl.atom.ekantor.model.UserCurrency;

/**
 * Created by Atom on 19.03.2017.
 */
public interface TransactionService {
    void transferCurrency(User from, User to, Currency currency,Long units);
}
