package pl.atom.ekantor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.atom.ekantor.dto.CurrenciesRates;
import pl.atom.ekantor.model.Currency;
import pl.atom.ekantor.respository.CurrencyRepository;

import java.util.List;

/**
 * Created by Artur on 18.03.2017.
 */
@Component
public class CurrenciesServiceImpl implements CurrenciesService {

    @Value("${fp.currencies.url}")
    private String externalCurrenciesServiceURL;

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    @Scheduled(initialDelay = 5000, fixedDelay=20000)
    public List<Currency> getCurrenciesRates(){
        List<Currency> currenciesRates = currencyRepository.findAll();
        return currenciesRates;
    }

    @Scheduled(initialDelay = 5000, fixedDelay=20000)
    private void updateCurrenciesRates(){
        RestTemplate restTemplate=new RestTemplate();
        CurrenciesRates currenciesRates=restTemplate.getForObject(externalCurrenciesServiceURL, CurrenciesRates.class);
        List<Currency> items = currenciesRates.getItems();
        for(Currency currency : items){
            currency.setUpdateDate(currenciesRates.getPublicationDate());
            Currency dbCurrency = currencyRepository.findByName(currency.getName());
            if(dbCurrency == null){
                currencyRepository.save(currency);
            }
            else {
                dbCurrency.setAveragePrice(currency.getAveragePrice());
                dbCurrency.setPurchasePrice(currency.getPurchasePrice());
                dbCurrency.setSellPrice(currency.getSellPrice());
                dbCurrency.setUpdateDate(currency.getUpdateDate());
                currencyRepository.save(dbCurrency);
            }
        }
    }

}
