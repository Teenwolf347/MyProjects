select *
 from CURRENCY_RATES
 where YEAR(DATE) = '2015' 
	and DAYNAME(DATE) != "Saturday" or "Sunday"
    LIMIT 2;