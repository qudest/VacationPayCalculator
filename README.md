Приложение "Калькулятор отпускных".  
Микросервис на SpringBoot + Java 11 c одним API:
GET "/calculate"

Приложение принимает среднюю зарплату за 12 месяцев и количество дней отпуска - отвечает суммой отпускных, которые придут сотруднику.  
GET /api/v1/calculate?averageSalary={average salary for 12 months}&days={count of vacation days}

При запросе также можно указать точные дни ухода в отпуск, тогда будет проводиться рассчет отпускных с учётом праздников и выходных.  
GET /api/v1/calculate?averageSalary={average salary for 12 months}&days={count of vacation days}&date={dd-MM-yyyy}
