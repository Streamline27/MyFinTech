import {tab} from "./CurrencyExchange";

export function CurrencyExchangeNavBar(props) {
    const { onTabChange, currentTab } = props

    const handleClickCalculator = () => onTabChange(tab.CALCULATOR)
    const handleClickFeeEditor = () => onTabChange(tab.FEE_EDITOR)

    return (
        <div className="row">
            <div className="col-md-12">
                <ul className="nav nav-tabs">
                    <li className="nav-item" onClick={handleClickCalculator}>
                        <a className={ "nav-link" + (currentTab === tab.CALCULATOR ? " active" : "") } href="/#">Calculator</a>
                    </li>
                    <li className="nav-item" onClick={handleClickFeeEditor}>
                        <a className={ "nav-link" + (currentTab === tab.FEE_EDITOR ? " active" : "") } href="/#">Fee editor</a>
                    </li>
                </ul>
            </div>
        </div>
    )
}