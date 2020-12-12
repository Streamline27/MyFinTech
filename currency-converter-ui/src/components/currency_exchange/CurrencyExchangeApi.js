
export class CurrencyExchangeApi { }

CurrencyExchangeApi.getAvailableCurrencies = function (onSuccess) {
    fetch("http://localhost:8090/currency-calculator/currencies")
        .then(res => res.json())
        .then(onSuccess)
}

CurrencyExchangeApi.getFees = function (onSuccess) {
    fetch("http://localhost:8090/currency-calculator/exchangeFees")
        .then(res => res.json())
        .then(onSuccess)
}

CurrencyExchangeApi.removeFee = function (fee, onSuccess) {
    let url = "http://localhost:8090/currency-calculator/exchangeFees?" + new URLSearchParams({ from: fee.from, to: fee.to})
    fetch(url, { method: "DELETE" })
        .then(res => res.json())
        .then(onSuccess)
}

CurrencyExchangeApi.addFee = function (fee, onSuccess) {

    let body = {
        exchangeFee: {
            from: fee.from,
            to: fee.to,
            fee: fee.fee
        }
    }
    fetch("http://localhost:8090/currency-calculator/exchangeFees", { method: "PUT", body: JSON.stringify(body), headers: { "content-type": "application/json", "accept": "application/json"} })
        .then(res => res.json())
        .then(onSuccess)
}

CurrencyExchangeApi.computeCurrencyExchangeAmount = function (from, to, amount, onSuccess) {
    let url = "http://localhost:8090/currency-calculator/conversion?" + new URLSearchParams({ amount: amount, from: from, to: to})
    fetch(url)
        .then(res => res.json())
        .then(onSuccess)
}