package pl.atom.ekantor.service;

import pl.atom.ekantor.model.Currency;

import java.util.List;

/**
 * Created by Artur on 18.03.2017.
 */
public interface CurrenciesService {

    List<Currency> getCurrenciesRates();
}
