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
 * Created by Atom on 19.03.2017.
 */
@Service
public class BuyServiceImpl implements BuyService {

    @Autowired
    UserService userService;

    @Autowired
    CurrenciesService currenciesService;

    @Autowired
    UserCurrencyRepository userCurrencyRepository;

    @Autowired
    TransactionService transactionService;

    @Transactional
    @Override
    public TransactionStatus buyCurrency(User user, String currencyCode, Long units){
        User exchangeUser = userService.findByUsername(UserService.EXCHANGE_USER);
        Currency currency = currenciesService.getCurrencyByCode(currencyCode);
        if(userHasEnoughMoney(user,currency,units)){
            if(exchangeHasEnoughCurrency(exchangeUser,currency,units)){
                transactionService.transferCurrency(exchangeUser,user,currency,units);
                return TransactionStatus.BOUGHT;
            } else {
                return TransactionStatus.NOT_ENOUGH_UNITS_ON_STOCK;
            }
        } else {
            return TransactionStatus.NO_ENOUGH_MONEY;
        }
    }

    private boolean exchangeHasEnoughCurrency(User exchangeUser, Currency currency, Long units) {
        UserCurrency exchangeUserCurrency = currenciesService.getUserCurrency(exchangeUser, currency);
        return exchangeUserCurrency.getQuantity() >= units;
    }



    private boolean userHasEnoughMoney(User user, Currency currency, Long units) {
        return user.getPlns().compareTo(currency.getPurchasePrice().multiply(new BigDecimal(units)))>=0;
    }
}
