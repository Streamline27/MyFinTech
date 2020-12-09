
export class CurrencyExchangeApi { }

CurrencyExchangeApi.getAvailableCurrencies = function (onSuccess) {
    fetch("http://localhost:3000/currencies.json")
        .then(res => res.json())
        .then(onSuccess)
}

CurrencyExchangeApi.getFees = function (onSuccess) {
    fetch("http://localhost:3000/exchange-fees.json")
        .then(res => res.json())
        .then(onSuccess)
}

CurrencyExchangeApi.removeFee = function (fee, onSuccess) {
    fetch("http://localhost:3000/exchange-fees.json")
        .then(res => res.json())
        .then(onSuccess)
}

CurrencyExchangeApi.addFee = function (fee, onSuccess) {
    fetch("http://localhost:3000/exchange-fees.json")
        .then(res => res.json())
        .then(onSuccess)
}

CurrencyExchangeApi.computeCurrencyExchangeAmount = function (from, to, amount, onSuccess) {
    fetch("http://localhost:3000/exchange-fees.json")
        .then(res => res.json())
        .then(onSuccess)
}