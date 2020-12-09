import {ExchangeFeeForm} from "./ExchangeFeeForm";
import {ExchangeFeeTable} from "./ExchangeFeeTable";
import {ExchangeFee} from "./ExchangeFee";
import React, {useEffect, useState} from "react";
import {Spinner} from "../Spinner";
import {CurrencyExchangeApi} from "../CurrencyExchangeApi";

export function ExchangeFeeEditor() {
    const [ availableCurrencies, setAvailableCurrencies ] = useState([])
    const [ exchangeFees, setExchangeFees ] = useState([])
    const [ isExchangeFeesLoading, setIsExchangeFeesLoading ] = useState(true)
    const [ isAvailableCurrenciesLoading, setIsAvailableCurrenciesLoading ] = useState(true)

    useEffect(() => CurrencyExchangeApi.getAvailableCurrencies(handleGetAvailableCurrenciesSuccess), [availableCurrencies])
    useEffect(() => CurrencyExchangeApi.getFees(handleGetFeesSuccess), [exchangeFees])

    function handleRemoveFee(fee) {
        CurrencyExchangeApi.removeFee(fee, handleRemoveFeeSuccess)
    }

    function handleAddFee(fee) {
        CurrencyExchangeApi.addFee(fee, handleAddFeeSuccess)
    }

    function handleGetAvailableCurrenciesSuccess(response) {
        setAvailableCurrencies(response["currencies"])
        setIsAvailableCurrenciesLoading(false)
    }

    function handleGetFeesSuccess(response) {
        setExchangeFees(response["exchangeFees"].map(it => ExchangeFee.from(it)))
        setIsExchangeFeesLoading(false)
    }

    function handleRemoveFeeSuccess(fee) {
        setExchangeFees(prevFees => prevFees.filter(it => !it.id !== fee.id))
    }

    function handleAddFeeSuccess(fee) {
        setExchangeFees(prevFees => [fee, ...prevFees])
    }

    if (isExchangeFeesLoading && isAvailableCurrenciesLoading) {
        return <Spinner/>
    }
    return (
        <table className="table table-borderless">
            <ExchangeFeeForm
                availableCurrencies={availableCurrencies}
                onAddFee={handleAddFee}
            />
            <ExchangeFeeTable
                currencyFees={exchangeFees}
                onRemoveFee={handleRemoveFee}
            />
        </table>
    )
}