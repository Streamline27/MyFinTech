import React from "react";
import {Navigation} from "./Navigation";
import {CurrencyExchange} from "./currency_exchange/CurrencyExchange";

export function HomePage(props) {
    const { onLogout } = props

    return (
        <div>
            <Navigation onLogout={onLogout}/>
            <div className="container">
                <div className="my-3 p-3 bg-white rounded shadow-sm">
                    <CurrencyExchange/>
                </div>
            </div>
        </div>
    );
}