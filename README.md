Opis
Jest to aplikacja oparta na architekturze mikroserwisów, która łączy frontend w Angularze z mikroserwisami napisanymi w Javie oraz Gateway, który pełni rolę bramki API do komunikacji pomiędzy frontendem a backendem.
Technologie użyte w projekcie:
•	Frontend: Angular (TypeScript, HTML, CSS)
•	Backend: Mikroserwisy w Java (Spring Boot)
•	Gateway: Spring Cloud Gateway
•	Baza danych: Możliwość konfiguracji (np. MySQL, PostgreSQL, MongoDB)
•	Autoryzacja: OAuth2, JWT
Opis struktury:
•	Frontend (Angular): Interaktywny interfejs użytkownika zbudowany w Angularze, zapewniający dynamiczne renderowanie i komunikację z backendem przez API Gateway.
•	Mikroserwisy (Java): Zestaw mikroserwisów napisanych w Javie z wykorzystaniem Spring Boot. Każdy mikroserwis ma określoną funkcjonalność i może być skalowany niezależnie.
•	API Gateway (Spring Cloud Gateway): Centralna bramka, która przekierowuje zapytania z frontendu do odpowiednich mikroserwisów, zarządza routowaniem oraz obsługą autoryzacji.
Funkcjonalności:
•	Rejestracja i logowanie użytkowników (obsługa OAuth2, JWT).
•	Interaktywne panele użytkownika z danymi pobieranymi z mikroserwisów.
•	Dynamiczne aktualizowanie danych bez przeładowywania strony.
•	Skalowalność i rozdzielność poszczególnych mikroserwisów.
Uruchamianie aplikacji:
1.	Skonfiguruj backend:
o	Uruchom mikroserwisy w Javie (Author, Book, Gateway)
2.	Uruchom frontend:
o	Zainstaluj Angular CLI: npm install -g @angular/cli
o	Zainstaluj zależności: npm install
o	Uruchom aplikację: ng serve
3.	Aplikacja frontendowa powinna być dostępna pod adresem http://localhost:4200.
Przykładowe zdjęcia z aplikacji
1.	![image](https://github.com/user-attachments/assets/7158a2f4-fa9f-4e5a-a12c-fe6858a2f9cb)
2.	![image](https://github.com/user-attachments/assets/69238a32-f722-45f8-a9c9-2513a78cde81)
3.  ![image](https://github.com/user-attachments/assets/b9592e52-ff25-4b99-a19e-d178d88d334f)
4.  ![image](https://github.com/user-attachments/assets/04e81538-f69e-4b1a-abcc-4e441f567416)
Wymagania:
•	Java 11+ (lub nowsza)
•	Node.js i npm
•	Angular CLI

