insert INTO CURRENCY (ID, CURRENCY, DESCRIPTION)
values (NULL, 'RUR', 'Российский рубль'),
	(NULL, 'USD', 'Доллар США'),
    (NULL, 'EUR', 'Евро'),
	(NULL, 'ZWL', 'Зимбабвийский доллар');


insert INTO  CURRENCY_RATES (ID, CURRENCY, RATE, ID_CURRENCY, DATE)
values (NULL, 'RUR', 1.0000, 1, '1970-01-01'),
	(NULL, 'RUR', 1.0600, 1, '1971-01-01'),
	(NULL, 'RUR', 1.0080, 1, '1972-01-01'),
	(NULL, 'RUR', 1.0000, 1, '1973-01-01'),
	(NULL, 'RUR', 1.0500, 1, '1974-01-01'),
	(NULL, 'RUR', 1.0300, 1, '1975-01-01'),
	(NULL, 'RUR', 1.0020, 1, '1976-01-01'),
	(NULL, 'RUR', 1.0700, 1, '1977-01-01'),
	(NULL, 'RUR', 1.0100, 1, '2015-07-10'),
	(NULL, 'USD', 65.3200, 2, '2015-01-10'),
	(NULL, 'USD', 65.3560, 2, '2015-07-10'),
	(NULL, 'USD', 64.6500, 2, '2015-02-10'),
	(NULL, 'USD', 64.3100, 2, '2015-05-10'),
	(NULL, 'ZWL', 0.0100, 4, '2015-01-01'),
	(NULL, 'ZWL', 0.0120, 4, '2015-01-03');
