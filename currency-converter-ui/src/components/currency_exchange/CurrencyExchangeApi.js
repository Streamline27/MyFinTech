import {API_HOST} from "../../App";

export class CurrencyExchangeApi { }

CurrencyExchangeApi.getAvailableCurrencies = function (onSuccess, onError) {
    fetch(API_HOST + "/currency-calculator/currencies")
        .then(res => handleResponse(res, onSuccess, onError))
}

CurrencyExchangeApi.getFees = function (onSuccess, onError) {
    fetch(API_HOST + "/currency-calculator/exchangeFees")
        .then(res => handleResponse(res, onSuccess, onError))
}

CurrencyExchangeApi.removeFee = function (fee, onSuccess, onError) {
    let body = { exchangeFee: fee }
    let url = API_HOST + "/currency-calculator/exchangeFees?" + new URLSearchParams({ from: fee.from, to: fee.to})
    fetch(url, { method: "DELETE", body: JSON.stringify(body), headers: { "content-type": "application/json", "accept": "application/json"} })
        .then(res => handleResponse(res, onSuccess, onError))
}

CurrencyExchangeApi.addFee = function (fee, onSuccess, onError) {
    let body = { exchangeFee: fee }
    let url = API_HOST + "/currency-calculator/exchangeFees"
    fetch(url, { method: "PUT", body: JSON.stringify(body), headers: { "content-type": "application/json", "accept": "application/json"} })
        .then(res => handleResponse(res, onSuccess, onError))
}

CurrencyExchangeApi.computeCurrencyExchangeAmount = function (from, to, amount, onSuccess, onError) {
    let url = API_HOST + "/currency-calculator/conversion?" + new URLSearchParams({ amount: amount, from: from, to: to})
    fetch(url)
        .then(res => handleResponse(res, onSuccess, onError))
}

function handleResponse(response, onSuccess, onError) {
    response.json().then(data => {
        if (response.status < 400) {
            onSuccess && onSuccess(data)
        } else {
            onError && onError(data)
        }
    })
}