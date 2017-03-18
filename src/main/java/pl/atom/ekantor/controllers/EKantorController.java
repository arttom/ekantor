package pl.atom.ekantor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.atom.ekantor.dto.CurrenciesRates;
import pl.atom.ekantor.model.Currency;
import pl.atom.ekantor.model.User;
import pl.atom.ekantor.model.UserCurrency;
import pl.atom.ekantor.service.CurrenciesService;
import pl.atom.ekantor.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur on 18.03.2017.
 */
@Controller
public class EKantorController {

    @Autowired
    private CurrenciesService currenciesService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ekantor", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "getCurrentExchangeRates", method = RequestMethod.GET)
    public String getCurrentExchangeRates(Model model){
        List<Currency> currenciesRates = currenciesService.getCurrenciesRates();
        model.addAttribute("currenciesRates",currenciesRates);
        return "currencies";
    }

    @RequestMapping(value = "getUserWallet", method = RequestMethod.GET)
    public String getUserWallet(Model model){
        User loggedUser = userService.getLoggedUser();
        model.addAttribute("username",loggedUser.getUsername());
        List<UserCurrency> userCurrencies = new ArrayList<>(loggedUser.getUserCurrencies());
        model.addAttribute("userCurrencies",userCurrencies);
        return "userWallet";
    }
}
