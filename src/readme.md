# Currency Calculator
Check currencies rates from NBP API.

### Commands
- `-m` `--Money` select amount of money (zl) to calculate (default: 100)
- `-c` `--Currency` add currency to check (default: usd, eur, gbp, chf)
- `-e` `--Earn` earn option, how many you could earn from selected day using selected currency
- `-da` `--DaysAgo` select how many days ago you have bought money (default: 0)
- `-ma` `--MonthsAgo` select how many months ago you have bought money (default: 1)

### Examples
- `-m 200` check today's rates of usd, eur, gbp, chf; and how many usd, eur, gbp, chf you could buy spending 200 zl
- `-c "usd"` check today's rate of usd; and how many usd you could buy spending 100 zl
- `-e -m 300 -c "eur"` how many you could earn buying 1 month ago usd, spending 300 zl
- `-e -ma 2 -da 23` how many you could earn buying 2 months and 23 days ago usd, eur, gbp, chf, spending 100 zl