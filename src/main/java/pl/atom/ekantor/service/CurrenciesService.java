package pl.atom.ekantor.service;

import pl.atom.ekantor.model.Currency;
import pl.atom.ekantor.model.User;
import pl.atom.ekantor.model.UserCurrency;

import java.util.List;

/**
 * Created by Artur on 18.03.2017.
 */
public interface CurrenciesService {

    List<Currency> getCurrenciesRates();

    Currency getCurrencyByCode(String code);

    UserCurrency getUserCurrency(User user, Currency currency);
}
