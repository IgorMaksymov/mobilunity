swagger available at http://localhost:8080/swagger-ui.html
Press "try it out" and then "execute"

H2 available at : http://localhost:8080/h2 login/password: h2

для запуска проекта его достаточно собрать и выполнить java -jar demo-0.0.1-SNAPSHOT.jar

Поскольку HR передал мне, что я волен делать все на свое усмотрение, явно игнорируя требования ТЗ я:
- переименовал АПИ для регистрации, так мне оно выглядит более рациональным
- разнес профиль юзера и его логин/пароль по разным таблицам, я считаю что для жизни юзера логин и пароль не нужны, они нужны только на этапе логина.
- я не вижу смысла сохранять и захешированный пароль, а также голый, поэтому сохраняю только захешированный
- также, я считаю что не стоит делать AlreadyExistException checked-exception, так как, на моей практике checked-ексепшены приводили к каше в коде, которая не приносила никакой пользы.