<h2>Opis:</h2>
Jest to aplikacja oparta na architekturze mikroserwisów, która łączy frontend w Angularze z mikroserwisami napisanymi w Javie oraz Gateway, który pełni rolę bramki API do komunikacji pomiędzy frontendem a backendem.

<h2>Technologie użyte w projekcie:</h2>
<ul>
	<li>Frontend: Angular (TypeScript, HTML, CSS)</il>
	<li>Backend: Mikroserwisy w Java (Spring Boot)</li>
	<li>Gateway: Spring Cloud Gateway</il>
	<li>Baza danych: H2</il>
	<li>Gateway: Spring Cloud Gateway</il>
</ul>

<h2>Opis struktury:</h2>
<ul>
	<li>Frontend (Angular): Interaktywny interfejs użytkownika zbudowany w Angularze, zapewniający dynamiczne renderowanie i komunikację z backendem przez API Gateway.</il>
	<li>Mikroserwisy (Java): Zestaw mikroserwisów napisanych w Javie z wykorzystaniem Spring Boot. Każdy mikroserwis ma określoną funkcjonalność i może być skalowany niezależnie.</li>
	<li>API Gateway (Spring Cloud Gateway): Centralna bramka, która przekierowuje zapytania z frontendu do odpowiednich mikroserwisów, zarządza routowaniem.</il>
</ul>

<h2>Funkcjonalności:</h2>
<ul>
	<li>Interaktywne panele użytkownika z danymi pobieranymi z mikroserwisów.</il>
	<li>Dynamiczne aktualizowanie danych bez przeładowywania strony.</li>
	<li>Skalowalność i rozdzielność poszczególnych mikroserwisów.</il>
</ul>

<h2>Uruchamianie aplikacji:</h2>
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

<h2>Przykładowe zdjęcia z aplikacji</h2>
1.![image](https://github.com/user-attachments/assets/344a7e32-0f36-48b9-bd2c-32f47870ab0c)
2.![image](https://github.com/user-attachments/assets/0d129abb-5a39-4cc5-8967-875dc7d5a3e8)
3.![image](https://github.com/user-attachments/assets/4219e045-127c-416e-9a06-4fee6da79846)
4.![image](https://github.com/user-attachments/assets/6f6b617f-2d18-46a3-aaae-372f97247ec9)

<h2>Wymagania:</h2>
<ul>
	<li>Java 11+ (lub nowsza)</il>
	<li>Node.js i npm</li>
	<li>Angular CLI</il>
</ul>

