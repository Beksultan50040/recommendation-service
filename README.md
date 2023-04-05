# recommendation-service

В application properties поменяйте 8ю строчку spring.datasource.password на ваш пароль от локального PostgreSQL database

Запустите сервис

В браузере вставьте http://localhost:8080/swagger-ui/index.html#/ 
для удобного тестирование RESP API через swagger-ui

В поисковике введите /recommendation/api-docs

Используйте эндпоинт /register для регистарции нового пользователя

![image](https://user-images.githubusercontent.com/86729456/230057275-629c7940-b392-434a-8676-1ee13f129b46.png)

В response придет ответ с данными пользователя + поле "token" нужно скопировать его значение и вствавить в поле авторизации 

![image](https://user-images.githubusercontent.com/86729456/230057581-3257ac9c-6fae-447d-9d26-1047489860ae.png)

Все приложения готово для тестирования. 

Так как только админы могут создавать записи по фильмам. В базе автоматически будет создан дефолтный админ 

Сredentials:

"email" : "example@gmail.com", 

"password" : "123"


Нужно залогинитья под этим пользвателем для использования эндпоинтов с доступом ADMIN

Остальное все по стандарту ;)

