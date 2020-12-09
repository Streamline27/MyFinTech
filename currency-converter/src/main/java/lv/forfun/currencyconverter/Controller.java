package lv.forfun.currencyconverter;

import lombok.RequiredArgsConstructor;
import lv.forfun.currencyconverter.api.CurrencyCalculatorResponse;
import lv.forfun.currencyconverter.api.currency.CurrencyListResponse;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeePutRequest;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeeResponse;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeesResponse;
import lv.forfun.currencyconverter.feature.CurrencyCalculatorService;
import lv.forfun.currencyconverter.feature.CurrencyListService;
import lv.forfun.currencyconverter.feature.FeeEditorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final CurrencyListService currencyListService;
    private final FeeEditorService feeEditorService;
    private final CurrencyCalculatorService currencyCalculatorService;

    @GetMapping("/currencies")
    public CurrencyListResponse getCurrencies() {
        return currencyListService.getCurrencies();
    }

    @GetMapping("/exchangeFees")
    public ExchangeFeesResponse getExchangeFees() {
        return feeEditorService.getExchangeFees();
    }

    @PutMapping("/exchangeFees")
    public ExchangeFeeResponse putExchangeFee(@RequestBody ExchangeFeePutRequest request) {
        return feeEditorService.save(request);
    }

    @DeleteMapping("/exchnageFees")
    public ExchangeFeeResponse deleteExchangeFee(String from, String to) {
        return feeEditorService.delete(from, to);
    }

    @GetMapping("/conversion")
    public CurrencyCalculatorResponse computeConversion(String from, String to, double amount) {
        return currencyCalculatorService.getConversion(from, to, amount);
    }
}
