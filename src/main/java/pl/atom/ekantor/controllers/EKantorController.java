package pl.atom.ekantor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.atom.ekantor.dto.CurrenciesRates;
import pl.atom.ekantor.dto.TransactionStatus;
import pl.atom.ekantor.model.Currency;
import pl.atom.ekantor.model.User;
import pl.atom.ekantor.model.UserCurrency;
import pl.atom.ekantor.service.BuyService;
import pl.atom.ekantor.service.CurrenciesService;
import pl.atom.ekantor.service.SellService;
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

    @Autowired
    private BuyService buyService;

    @Autowired
    private SellService sellService;

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
    @RequestMapping(value = "getCurrentExchangeRates/{currencyCode}", method = RequestMethod.GET)
    public String getCurrentExchangeRates(Model model,@PathVariable String currencyCode){
        Currency currency = currenciesService.getCurrencyByCode(currencyCode);
        model.addAttribute("currencyCode",currency.getCode());
        model.addAttribute("currencyPrice",currency.getPurchasePrice());
        model.addAttribute("currencyUpdateDate",currency.getUpdateDate());
        return "currency";
    }

    @RequestMapping(value = "getUserWallet", method = RequestMethod.GET)
    public String getUserWallet(Model model){
        User loggedUser = userService.getLoggedUser();
        model.addAttribute("username",loggedUser.getUsername());
        model.addAttribute("userMoney",loggedUser.getPlns());
        List<UserCurrency> userCurrencies = new ArrayList<>(loggedUser.getUserCurrencies());
        model.addAttribute("userCurrencies",userCurrencies);
        return "userWallet";
    }

    @RequestMapping(value="/ekantor/buy/{currencyCode}", method = RequestMethod.GET)
    public String buyCurrency(Model model, @PathVariable String currencyCode){
        Currency currency = currenciesService.getCurrencyByCode(currencyCode);
        model.addAttribute("currencyCode",currency.getCode());
        model.addAttribute("currencyPrice",currency.getPurchasePrice());
        model.addAttribute("currencyUpdateDate",currency.getUpdateDate());
        model.addAttribute("currencyUnit",currency.getUnit());
        return "buyCurrency";
    }

    @RequestMapping(value="buyCurrency")
    public String buyCurrency(Model model, @RequestParam("currencyCode") String currencyCode, @RequestParam("units") Long units){
        TransactionStatus transactionStatus = buyService.buyCurrency(userService.getLoggedUser(), currencyCode, units);
        model.addAttribute("transactionStatus",transactionStatus.toString());
        model.addAttribute("currencyCode",currencyCode);
        model.addAttribute("units",units);
        return "buyResult";
    }

    @RequestMapping(value="/ekantor/sell/{currencyCode}", method = RequestMethod.GET)
    public String sellCurrency(Model model, @PathVariable String currencyCode){
        Currency currency = currenciesService.getCurrencyByCode(currencyCode);
        model.addAttribute("currencyCode",currency.getCode());
        model.addAttribute("currencyPrice",currency.getPurchasePrice());
        model.addAttribute("currencyUpdateDate",currency.getUpdateDate());
        model.addAttribute("currencyUnit",currency.getUnit());
        return "sellCurrency";
    }

    @RequestMapping(value="sellCurrency", method = RequestMethod.POST)
    public String sellCurrency(Model model, @RequestParam("currencyCode") String currencyCode, @RequestParam("units") Long units){
        TransactionStatus transactionStatus = sellService.sellCurrency(userService.getLoggedUser(), currencyCode, units);
        model.addAttribute("transactionStatus",transactionStatus.toString());
        model.addAttribute("currencyCode",currencyCode);
        model.addAttribute("units",units);
        return "sellResult";
    }

    @RequestMapping(value="getUserCurrencyInfo/{currencyCode}")
    public String getUserCurrencyInfo(Model model, @PathVariable String currencyCode){
        Currency currency = currenciesService.getCurrencyByCode(currencyCode);
        User loggedUser = userService.getLoggedUser();
        UserCurrency userCurrency = currenciesService.getUserCurrency(loggedUser, currency);
        model.addAttribute("currencyCode",userCurrency.getCurrency().getCode());
        model.addAttribute("currencyPrice",userCurrency.getCurrency().getSellPrice());
        model.addAttribute("currencyUpdateDate",userCurrency.getCurrency().getUpdateDate());
        model.addAttribute("units",userCurrency.getQuantity());
        return "userCurrency";
    }


}
