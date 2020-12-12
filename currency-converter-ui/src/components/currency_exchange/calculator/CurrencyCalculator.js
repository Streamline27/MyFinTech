import React, {useEffect, useState} from "react";
import {Spinner} from "../Spinner";
import {CurrencyExchangeApi} from "../CurrencyExchangeApi";

const COST_PLACEHOLDER = "Calculator!"

export function CurrencyCalculator() {
    const [ availableCurrencies, setAvailableCurrencies ] = useState([])
    const [ fromCurrency, setFromCurrency ] = useState("EUR")
    const [ toCurrency, setToCurrency ] = useState("RUB")
    const [ amount, setAmount ] = useState("0.00")
    const [ cost, setCost ] = useState(COST_PLACEHOLDER)
    const [ isCurrencyExchangeAmountLoading, setIsCurrencyExchangeAmountLoading] = useState(true)
    const [ isAvailableCurrenciesLoading, setIsAvailableCurrenciesLoading] = useState(true)

    useEffect(() => {
        function handleGetAvailableCurrenciesSuccess(response) {
            setAvailableCurrencies(response["currencies"])
            setIsAvailableCurrenciesLoading(false)
        }
        CurrencyExchangeApi.getAvailableCurrencies(handleGetAvailableCurrenciesSuccess)
    }, [])

    useEffect(() => {
        if (amount === "" || amount === "-") {
            return
        }
        let timeoutId = setTimeout(()=> { setIsCurrencyExchangeAmountLoading(true) }, 100)
        function handleComputeResultingExchangeAmountSuccess(response) {
            setCost(computeCost(fromCurrency, toCurrency, response.conversion))
            setIsCurrencyExchangeAmountLoading(false)
            clearTimeout(timeoutId)
        }
        function handleComputeResultingExchangeAmountError(response) {
            setCost(response.message)
            setIsCurrencyExchangeAmountLoading(false)
            clearTimeout(timeoutId)
        }
        CurrencyExchangeApi.computeCurrencyExchangeAmount(fromCurrency, toCurrency, amount,
            handleComputeResultingExchangeAmountSuccess,
            handleComputeResultingExchangeAmountError
        )
        return () => clearTimeout(timeoutId)
    }, [fromCurrency, toCurrency, amount])

    function handleAmountChange({target}) {
        let nextAmount = target.value.trim()
        if (nextAmount === "" || nextAmount === "-" || isNumeric(nextAmount)) {
            setAmount(nextAmount)
        }
    }

    function isNumeric(str) {
        if (typeof str != "string") return false // we only process strings!
        return !isNaN(str) && // use type coercion to parse the _entirety_ of the string (`parseFloat` alone does not do this)...
            !isNaN(parseFloat(str)) // ...and ensure strings of whitespace fail
    }

    function handleFromCurrencyChange({target}) {
        setFromCurrency(target.value)
    }

    function handleToCurrencyChange({target}) {
        setToCurrency(target.value)
    }

    function computeCost(from, to, amount) {
        return `${amount} ${to}`
    }

    if (isCurrencyExchangeAmountLoading && isAvailableCurrenciesLoading) {
        return <Spinner/>
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
                    { isCurrencyExchangeAmountLoading ? <Spinner/> : <h1 className="text-center">{cost}</h1> }
                </div>
            </div>
        </div>
    )
}