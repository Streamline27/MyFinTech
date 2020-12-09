import React from "react";
import {Navigation} from "./components/Navigation";
import {CurrencyExchange} from "./components/currency_exchange/CurrencyExchange";

function App() {
    return (
        <div>
            <Navigation/>
            <div className="container">
                <div className="my-3 p-3 bg-white rounded shadow-sm">
                    <CurrencyExchange/>
                </div>
            </div>
        </div>
    );
}

export default App;
