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

    useEffect(() => {
        function handleGetAvailableCurrenciesSuccess(response) {
            setAvailableCurrencies(response["currencies"])
            setIsAvailableCurrenciesLoading(false)
        }
        CurrencyExchangeApi.getAvailableCurrencies(handleGetAvailableCurrenciesSuccess)
    }, [])

    useEffect(() => {
        function handleGetFeesSuccess(response) {
            setExchangeFees(response["exchangeFees"].map(it => ExchangeFee.from(it)))
            setIsExchangeFeesLoading(false)
        }
        CurrencyExchangeApi.getFees(handleGetFeesSuccess)
    }, [])

    function handleRemoveFee(fee) {
        CurrencyExchangeApi.removeFee(fee, handleRemoveFeeSuccess)
    }

    function handleAddFee(fee) {
        CurrencyExchangeApi.addFee(fee, handleAddFeeSuccess)
    }

    function handleRemoveFeeSuccess(response) {
        let fee = ExchangeFee.from(response.exchangeFee)
        setExchangeFees(prevFees => prevFees.filter(it => it.id !== fee.id))
    }

    function handleAddFeeSuccess(response) {
        let fee = ExchangeFee.from(response.exchangeFee)
        setExchangeFees(prevFees => [ExchangeFee.from(response.exchangeFee), ...prevFees.filter(it => it.id !== fee.id)])
    }

    if (isExchangeFeesLoading || isAvailableCurrenciesLoading) {
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