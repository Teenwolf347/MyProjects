select CURRENCY, count(CURRENCY) sum
from CURRENCY_RATES
group by CURRENCY
having count(CURRENCY) >= 9;
