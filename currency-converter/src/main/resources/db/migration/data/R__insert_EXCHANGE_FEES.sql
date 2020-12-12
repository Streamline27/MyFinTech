TRUNCATE TABLE exchange_fee;
INSERT INTO exchange_fee(from_currency, to_currency, fee) VALUES ('RUB', 'EUR', 0.01);
INSERT INTO exchange_fee(from_currency, to_currency, fee) VALUES ('EUR', 'RUB', 0.02);
INSERT INTO exchange_fee(from_currency, to_currency, fee) VALUES ('EUR', 'JPY', 0.03);
INSERT INTO exchange_fee(from_currency, to_currency, fee) VALUES ('JPY', 'EUR', 0.04);