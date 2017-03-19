package pl.atom.ekantor.dto;

import pl.atom.ekantor.model.Currency;

import java.util.Date;
import java.util.List;

/**
 * DTO which holds data from external webservice.
 * Created by Artur on 18.03.2017.
 */
public class CurrenciesRates {

    private Date publicationDate;
    private List<Currency> items;

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Currency> getItems() {
        return items;
    }

    public void setItems(List<Currency> items) {
        this.items = items;
    }
}
