package pl.atom.ekantor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.atom.ekantor.dto.TransactionStatus;
import pl.atom.ekantor.model.Currency;
import pl.atom.ekantor.model.User;
import pl.atom.ekantor.model.UserCurrency;
import pl.atom.ekantor.respository.UserCurrencyRepository;

import java.math.BigDecimal;

/**
 * SellService interface implementation
 * Created by Atom on 19.03.2017.
 */
@Service
public class SellServiceImpl implements SellService{

    @Autowired
    UserService userService;

    @Autowired
    CurrenciesService currenciesService;

    @Autowired
    UserCurrencyRepository userCurrencyRepository;

    @Autowired
    TransactionService transactionService;

    @Override
    @Transactional
    public TransactionStatus sellCurrency(User user, String currencyCode, Long units) {
        User exchangeUser = userService.findByUsername(UserService.EXCHANGE_USER);
        Currency currency = currenciesService.getCurrencyByCode(currencyCode);
        if(userHasEnoughCurrency(user,currency,units)){
            if(exchangeHasEnoughMoney(exchangeUser,currency,units)){
                transactionService.transferCurrency(user,exchangeUser,currency,units);
                return TransactionStatus.SOLD;
            } else {
                return TransactionStatus.NOT_ENOUGH_MONEY_ON_STOCK;
            }
        } else {
            return TransactionStatus.NOT_ENOUGH_UNITS;
        }
    }

    private boolean exchangeHasEnoughMoney(User exchangeUser, Currency currency, Long units) {
        return exchangeUser.getPlns().compareTo(currency.getSellPrice().multiply(new BigDecimal(units)))>=0;
    }

    private boolean userHasEnoughCurrency(User user, Currency currency, Long units) {
        UserCurrency exchangeUserCurrency = currenciesService.getUserCurrency(user,currency);
        return exchangeUserCurrency != null ? exchangeUserCurrency.getQuantity() >= units : false;
    }


}
