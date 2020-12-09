import React, {useState} from "react";
import {CurrencyExchangeNavBar} from "./CurrencyExchangeNavBar";
import {ExchangeFeeEditor} from "./fee_editor/ExchangeFeeEditor";
import {CurrencyCalculator} from "./calculator/CurrencyCalculator";

export const tab = {
    CALCULATOR: "calculator",
    FEE_EDITOR: "fee_editor"
}

export function CurrencyExchange() {
    const [ selectedTab, setSelectedTab ] = useState(tab.CALCULATOR)

    function toggleBar(nextBar) {
        setSelectedTab(nextBar)
    }

    return (
        <div>
            <CurrencyExchangeNavBar currentTab={selectedTab} onTabChange={toggleBar}/>
            { selectedTab === tab.CALCULATOR && <CurrencyCalculator/> }
            { selectedTab === tab.FEE_EDITOR && <ExchangeFeeEditor/> }
        </div>
    )
}