## Aplikacja Forum internetowe

Nasz portal społecznościowy to forum internetowe przeznaczone do dyskusji na tematy szeroko pojętej informatyki oraz programowania
Aplikacja wzorowana jest na najpopularniejszych forach internetowych i posiadać będzie najistotniejsze funkcjonalności jakie takie
forum dyskusyjne powinno posiadać. Użytkownik ma możliwość zakładania nowych tematów w poszczególnych kategoriach, następnie inni użytkownicy
mogą w danym temacie umieszczać własne posty. Pod każdym postem jest również możliwość wstawienia krótkiego komentarza
odnoszącego się do konkretnego postu. W aplikacji jest również system oceniania poszczególnych treści po przez tzw. like lub dislike.
Na podstawie zebranych ocen w systemie znajduje się ranking najaktywniejszych użytkowników, który widoczny będzie na stronie głównej.
Forum udostępnia również wyszukiwanie tematów wg kryteriów takie jak popularność, najwyżej oceniane, czy najnowsze.
Każdy użytkownik będzie posiadał również własną stronę profilową którą będą mogli odwiedzać inni użytkownicy.

### Technologie i narzędzia

- Java
- Spring boot
- Angular

Do wykonania naszego projektu skorzystaliśmy z języka Java oraz frameworkiem Spring boot.
Wybraliśmy te technologie dlatego, że mamy w niej już wstępne doświadczenie. Spring jest platformą złożoną z wielu projektów, 
która dedykowana jest do tworzenia aplikacji w języku Java. Jego kluczowym elementem jest kontener wstrzykiwania zależności,
jednak przez lata Spring zyskał wsparcie dla wielu technologii i stanowi dziś jeden z kluczowych elementów całego ekosystemu 
Javy. Spring posiada wiele przydatnych modułów znacznie upraszczające programowanie aplikacji internetowych takich jak  
Spring Data - Projekt upraszczający dostęp do baz danych,  
Spring Security - Projekt, który pozwala na zabezpieczenie swojej aplikacji i stworzenie systemu uwierzytelnienia i autoryzacji.  
Spring boot posiada również wbudowany serwer aplikacyjny Tomcat.

### Struktura i bezpieczeństwo
Do zabezpieczenia naszej aplikacji użyty zostanie moduł Spring Security. Spring Security to framework, który służy do
zabezpieczenia aplikacji. Skupia się ona na warstwie samej aplikacji i zajmuje się dwoma zagadnieniami: uwierzytelnianiem 
(sprawdzenie kim jest użytkownik, czyli logowanie wszelkiego rodzaju) i autoryzowaniem (sprawdzeniem czy użytkownik ma 
prawa do zasobów (stron, plików, zapytań itd.)). Stał się bardzo popularny w rozwiązaniach biznesowych, ponieważ pozwalają na
prostą i szybką konfigurację tej warstwy zabezpieczeń. Framework stale jest rozwijany i uzyskuje coraz to nowsze rozwiązania. 
W trakcie pisania tego wpisu posiada już kilkanaście gotowych rozwiązań. Jednocześnie umożliwia implementacje własnych 
rozwiązań.

### User stories
Konto gościa:
- przeglądanie tematów, postów, komentarzy  

Konto użytkownika:  
- logowanie do systemu,
- zakładanie tematów,
- dodawanie postów,
- komentowanie postów,
- dodawania ocen postów/tematów,
- zbieranie punktów,
- ulepszania swojej rangi poprzez dodawanie postów,
- wyświetlania najnowszych postów, tematów,
- usuwania swoich tematów  

Konto administratora
- logowanie do systemu,
- usuwanie użytkowników,
- banowanie użytkowników


### W jaki sposób przyciągnąć użytkowników?
Przede wsystkim skorzystamy z narzędzi googla do opracowania słów kluczowych i nie tylko, aby nasza strona była lepiej 
pozcjonowana w wyszukiwarce. Reklamą będzie również polecenie portalu osobom zajmującym szeroką technologią 
i w tym kierunku tworzą filmy w serwisach wideo oraz piszą posty na swoich blogach lub innych portalach społecznościowych.

### Jak widzimy rozbudowe portalu?
W przypadku dalszej rozbudowy portalu planujemy poszerzyć go o stworzenie live chatu dla użytkowników oraz dodać możliwośc 
logowania się do naszej aplikacji za pomocą innych mediów społecznościowych takich tak facebook czy twitter. Do tego celu wykorzystać
można moduł Spring Social.






