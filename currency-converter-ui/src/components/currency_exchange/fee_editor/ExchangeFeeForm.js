import {useState} from "react";
import {ExchangeFee} from "./ExchangeFee";

export function ExchangeFeeForm(props) {
    const { availableCurrencies, onAddFee } = props
    const [ selectedFromCurrency, setSelectedFromCurrency ] = useState(availableCurrencies[0])
    const [ selectedToCurrency, setSelectedToCurrency ] = useState(availableCurrencies[1])
    const [ fee, setFee ] = useState(0.05)

    function handleFromCurrencyChange({target}) {
        setSelectedFromCurrency(target.value)
    }

    function handleToCurrencyChange({target}) {
        setSelectedToCurrency(target.value)
    }

    function handleFeeChange({target}) {
        setFee(target.value)
    }

    function handleSubmitButtonClick() {
        onAddFee(new ExchangeFee(selectedFromCurrency, selectedToCurrency, fee))
    }

    return (
        <thead>
        <tr>
            <td>
                <label>From</label>
                <select className="form-control" value={selectedFromCurrency} onChange={handleFromCurrencyChange}>
                    {availableCurrencies.map(currency =>
                        <option key={currency}>
                            {currency}
                        </option>)
                    }
                </select>
            </td>
            <td>
                <label>To</label>
                <select className="form-control" value={selectedToCurrency} onChange={handleToCurrencyChange}>
                    {availableCurrencies.map(currency =>
                        <option key={currency}>
                            {currency}
                        </option>)
                    }
                </select>
            </td>
            <td>
                <label>Fee</label>
                <input type="number" step=".01" className="form-control" value={fee} onChange={handleFeeChange}/>
            </td>
            <td className="td-button">
                <label>&nbsp;</label>
                <br/>
                <button type="submit" className="btn btn-primary" onClick={handleSubmitButtonClick}>Submit</button>
            </td>
        </tr>
        </thead>
    )
}