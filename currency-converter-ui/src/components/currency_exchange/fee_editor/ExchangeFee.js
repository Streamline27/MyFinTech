
export class ExchangeFee {
    constructor(from, to, fee) {
        this.from = from;
        this.to = to;
        this.fee = fee
    }

    get id() {
        return this.from + "_" + this.to
    }
}

ExchangeFee.from = function (o) {
    return new ExchangeFee(o.from, o.to, o.fee)
}