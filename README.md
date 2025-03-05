## Opis:
Jest to aplikacja oparta na architekturze mikroserwisów, która łączy frontend w Angularze z mikroserwisami napisanymi w Javie oraz Gateway, który pełni rolę bramki API do komunikacji pomiędzy frontendem a backendem.

<h2>Technologie użyte w projekcie:</h2>
<ul>
	<li>Frontend: Angular (TypeScript, HTML, CSS)</il>
	<li>Backend: Mikroserwisy w Java (Spring Boot)</li>
	<li>Gateway: Spring Cloud Gateway</il>
	<li>Baza danych: H2</il>
	<li>Gateway: Spring Cloud Gateway</il>
</ul>

## Opis struktury:
<ul>
	<li>Frontend (Angular): Interaktywny interfejs użytkownika zbudowany w Angularze, zapewniający dynamiczne renderowanie i komunikację z backendem przez API Gateway.</il>
	<li>Mikroserwisy (Java): Zestaw mikroserwisów napisanych w Javie z wykorzystaniem Spring Boot. Każdy mikroserwis ma określoną funkcjonalność i może być skalowany niezależnie.</li>
	<li>API Gateway (Spring Cloud Gateway): Centralna bramka, która przekierowuje zapytania z frontendu do odpowiednich mikroserwisów, zarządza routowaniem.</il>
</ul>

## Funkcjonalności:
<ul>
	<li>Interaktywne panele użytkownika z danymi pobieranymi z mikroserwisów.</il>
	<li>Dynamiczne aktualizowanie danych bez przeładowywania strony.</li>
	<li>Skalowalność i rozdzielność poszczególnych mikroserwisów.</il>
</ul>

## Uruchamianie aplikacji:
<ol>
	<li>Skonfiguruj backend:</il>
	<ul>
		<li>Uruchom mikroserwisy w Javie (Author, Book, Gateway)</li>
	</ul>
	<li>Uruchom frontend:</li>
	<ul>
		<li>Zainstaluj Angular CLI: npm install -g @angular/cli</li>
		<li>Zainstaluj zależności: npm install</li>
		<li>Zainstaluj Uruchom aplikację: ng serve</li>
	</ul>
	<li>Aplikacja frontendowa powinna być dostępna pod adresem http://localhost:4200.</il>
</ol>

## Przykładowe zdjęcia z aplikacji

![image](https://github.com/user-attachments/assets/10a4779d-37a5-4c78-8182-ade8d5ec7c2f)
![image](https://github.com/user-attachments/assets/44fecf3b-e1a0-4fd4-8e17-720665e0310d)
![image](https://github.com/user-attachments/assets/12d6b684-a699-4438-86ef-d341dde2708b)
![image](https://github.com/user-attachments/assets/647732f1-d0ca-4923-a3fb-5a2782993a00)

## Wymagania:
<ul>
	<li>Java 11+ (lub nowsza)</il>
	<li>Node.js i npm</li>
	<li>Angular CLI</il>
</ul>



