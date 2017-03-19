package pl.atom.ekantor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.atom.ekantor.dto.CurrenciesRates;
import pl.atom.ekantor.model.Currency;
import pl.atom.ekantor.model.User;
import pl.atom.ekantor.model.UserCurrency;
import pl.atom.ekantor.respository.UserCurrencyRepository;
import pl.atom.ekantor.respository.UserRepository;
import pl.atom.ekantor.service.CurrenciesService;
import pl.atom.ekantor.service.UserService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller responsible for initial setup command of a application
 * Created by Artur on 18.03.2017.
 */
@Controller
@RequestMapping("/secret")
public class InitializeController {
    @Autowired
    private UserService userService;

    @Autowired
    CurrenciesService currenciesService;

    @Autowired
    UserCurrencyRepository userCurrencyRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/initialize")
    public String contextLoads() {

        User user = userRepository.findByUsername("exchange");
        if(user==null){
            user = new User("exchange","exchange",new BigDecimal(0));
        }
        userService.save(user);
        List<Currency> currenciesRates = currenciesService.getCurrenciesRates();
        for(Currency currency : currenciesRates){
            UserCurrency exchangeCurrency = new UserCurrency(user,currency);
            exchangeCurrency.setQuantity(10000L);
            userCurrencyRepository.save(exchangeCurrency);
        }
        return "init";
    }
}
