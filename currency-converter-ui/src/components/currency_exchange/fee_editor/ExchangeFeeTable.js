import React from "react";
import {ExchangeFee} from "./ExchangeFee";

export function ExchangeFeeTable(props) {
    const { currencyFees, onRemoveFee } = props

    return (
        <tbody>
        <tr>
            <td colSpan="5">
                <hr/>
            </td>
        </tr>
        { currencyFees.map(it =>
            <tr key={it.id}>
                <td>{it.from}</td>
                <td>{it.to}</td>
                <td>{it.fee}</td>
                <td>
                    <button
                        onClick={() => onRemoveFee(new ExchangeFee(it.from, it.to, it.fee))}
                        type="submit"
                        className="btn btn-danger">
                        Remove
                    </button>
                </td>
            </tr>
        )}
        </tbody>
    )
}