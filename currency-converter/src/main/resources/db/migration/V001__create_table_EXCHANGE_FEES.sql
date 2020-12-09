CREATE TABLE exchange_fee(
    id            SERIAL PRIMARY KEY NOT NULL,
    from_currency VARCHAR(3)         NOT NULL,
    to_currency   VARCHAR(3)         NOT NULL,
    fee           NUMERIC            NOT NULL,
    UNIQUE(from_currency, to_currency)
)