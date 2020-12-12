import React, {useEffect, useState} from "react";
import {Spinner} from "../Spinner";
import {CurrencyExchangeApi} from "../CurrencyExchangeApi";



export function CurrencyCalculator() {
    const [ availableCurrencies, setAvailableCurrencies ] = useState([])
    const [ fromCurrency, setFromCurrency ] = useState("EUR")
    const [ toCurrency, setToCurrency ] = useState("RUB")
    const [ amount, setAmount ] = useState("0.00")
    const [ cost, setCost ] = useState("Calculator!")
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
        let timeoutId = setTimeout(()=> { setIsCurrencyExchangeAmountLoading(true)}, 100)
        function handleComputeResultingExchangeAmountSuccess(response) {
            setCost(computeCost(fromCurrency, toCurrency, response.conversion))
            setIsCurrencyExchangeAmountLoading(false)
            clearTimeout(timeoutId)
        }
        CurrencyExchangeApi.computeCurrencyExchangeAmount(fromCurrency, toCurrency, amount, handleComputeResultingExchangeAmountSuccess)
        return () => clearTimeout(timeoutId)
    }, [fromCurrency, toCurrency, amount])

    function handleAmountChange({target}) {
        setAmount(target.value)
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