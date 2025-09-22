Эндпоинты:
POST /api/v1/parking/entry

Регистрация въезда автомобиля

Тело запроса: {"carNumber": "AA000A", "carType": "CAR"}

POST /api/v1/parking/exit

Регистрация выезда автомобиля

Тело запроса: {"carNumber": "AA000A"}

GET /api/v1/parking/report?start_date=2025-01-20T00:00:00&end_date=2025-01-31T23:59:59

Получение отчета за период

