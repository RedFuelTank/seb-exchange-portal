package com.seb.exchangerates.currency.infrastructure;
import com.seb.exchangerates.currency.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService service;
    @GetMapping("/codes")
    Iterable<Currency.Type> getCodes() {
        return service.getCodes();
    }
}
