package pl.atom.ekantor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.atom.ekantor.model.Currency;
import pl.atom.ekantor.model.User;
import pl.atom.ekantor.model.UserCurrency;
import pl.atom.ekantor.respository.UserCurrencyRepository;

import java.math.BigDecimal;

/**
 * Created by Atom on 19.03.2017.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CurrenciesService currenciesService;

    @Autowired
    UserCurrencyRepository userCurrencyRepository;

    @Autowired
    UserService userService;


    @Transactional
    public void transferCurrency(User from, User to, Currency currency, Long units) {
        UserCurrency fromUserCurrency = currenciesService.getUserCurrency(from, currency);
        UserCurrency toUserCurrency = currenciesService.getUserCurrency(to, currency);
        if(toUserCurrency ==null){
            toUserCurrency = new UserCurrency(to,currency);
        }
        toUserCurrency.setQuantity(toUserCurrency.getQuantity()+units);
        fromUserCurrency.setQuantity(fromUserCurrency.getQuantity()-units);
        userCurrencyRepository.save(fromUserCurrency);
        userCurrencyRepository.save(toUserCurrency);
        BigDecimal transactionValue=currency.getPurchasePrice().multiply(new BigDecimal(units));
        to.setPlns(to.getPlns().subtract(transactionValue));
        from.setPlns(from.getPlns().add(transactionValue));
        userService.update(from);
        userService.update(to);
    }

}
