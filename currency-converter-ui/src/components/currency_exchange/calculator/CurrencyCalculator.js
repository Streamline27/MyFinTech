import React, {useEffect, useState} from "react";
import {Spinner} from "../Spinner";
import {CurrencyExchangeApi} from "../CurrencyExchangeApi";

function computeCost(from, to, amount) {
    return from + " " + to + " " + amount
}

export function CurrencyCalculator() {
    const [ availableCurrencies, setAvailableCurrencies ] = useState([])
    const [ fromCurrency, setFromCurrency ] = useState("EUR")
    const [ toCurrency, setToCurrency ] = useState("RUB")
    const [ amount, setAmount ] = useState("0.00")
    const [ cost, setCost ] = useState("Calculator!")
    const [ isLoading, setIsLoading ] = useState(true)

    useEffect(() => CurrencyExchangeApi.getAvailableCurrencies(handleGetAvailableCurrenciesSuccess), [setAvailableCurrencies])
    useEffect(() => CurrencyExchangeApi.computeCurrencyExchangeAmount(fromCurrency, toCurrency, amount, handleComputeResultingExchangeAmountSuccess), [fromCurrency, toCurrency, amount])

    function handleGetAvailableCurrenciesSuccess(response) {
        setAvailableCurrencies(response["currencies"])
        setIsLoading(false)
    }

    function handleComputeResultingExchangeAmountSuccess(response) {
        setCost(computeCost(fromCurrency, toCurrency, amount))
        setIsLoading(false)
    }

    function handleAmountChange({target}) {
        setAmount(target.value)
    }

    function handleFromCurrencyChange({target}) {
        setFromCurrency(target.value)
    }

    function handleToCurrencyChange({target}) {
        setToCurrency(target.value)
    }

    return (
        <div>
            <form>
                <div className="form-row p-3">
                    <div className="form-group col-md-4">
                        <label>Amount</label>
                        <input type="text" className="form-control" value={amount} onChange={handleAmountChange}/>
                    </div>
                    <div className="form-group col-md-4">
                        <label>From</label>
                        <select className="form-control" value={fromCurrency} onChange={handleFromCurrencyChange}>
                            { availableCurrencies.map(currency =>
                                <option key={currency}>
                                    {currency}
                                </option>)
                            }
                        </select>
                    </div>
                    <div className="form-group col-md-4">
                        <label>To</label>
                        <select className="form-control" value={toCurrency} onChange={handleToCurrencyChange}>
                            { availableCurrencies.map(currency =>
                                <option key={currency}>
                                    {currency}
                                </option>)
                            }
                        </select>
                    </div>
                </div>
            </form>
            <div className="row p-3">
                <div className="form-group col-md-12">
                    { isLoading ? <Spinner/> : <h1 className="text-center">{cost}</h1> }
                </div>
            </div>
        </div>
    )
}