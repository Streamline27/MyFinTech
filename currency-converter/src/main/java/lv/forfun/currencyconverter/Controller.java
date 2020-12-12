package lv.forfun.currencyconverter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lv.forfun.currencyconverter.api.calculator.CurrencyCalculatorRequest;
import lv.forfun.currencyconverter.api.calculator.CurrencyCalculatorResponse;
import lv.forfun.currencyconverter.api.currency.CurrencyListResponse;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeeDeleteRequest;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeePutRequest;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeeResponse;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeesResponse;
import lv.forfun.currencyconverter.feature.CurrencyCalculatorService;
import lv.forfun.currencyconverter.feature.CurrencyListService;
import lv.forfun.currencyconverter.feature.FeeEditorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
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
    public ExchangeFeeResponse putExchangeFee(@Valid @RequestBody ExchangeFeePutRequest request) {
        return feeEditorService.upsert(request);
    }

    @DeleteMapping("/exchangeFees")
    public ExchangeFeeResponse deleteExchangeFee(@Valid @RequestBody ExchangeFeeDeleteRequest request) {
        return feeEditorService.delete(request);
    }

    @GetMapping("/conversion")
    public CurrencyCalculatorResponse computeConversion(@Valid CurrencyCalculatorRequest request) {
        return currencyCalculatorService.getConversion(request.getFrom(), request.getTo(), request.getAmount());
    }
}
