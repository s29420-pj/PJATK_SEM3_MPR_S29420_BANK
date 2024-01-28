# S29420_BANK

## POLECENIE

Napisz prostą aplikację, która będzie reprezentowała bank. Nazwij ją {nr_indeksu}_Bank, przykładowo
s99999_Bank. Aplikacja musi być napisana z wykorzystaniem Spring boot i powinna pozwalać na
przeprowadzenie operacji:

- rejestracja konta -> w trakcie rejestracji podajemy pesel, saldo początkowe, walute, imie i nazwisko
właściciela. Wszystkie pola są wymagane.
- pobranie danych z konkretnego konta -> do odczytu potrzebujemy jedynie identyfikatora konta.
- pobranie danych wszystkich kont posiadających saldo większe niż X
Założenia:
- Saldo może być tylko dodatnie
- Tylko uproszczona walidacja peselu (Nie sprawdzamy sumy kontrolnej)
- Wspierane waluty: PLN, EUR, USD

Do powstałej logiki napisz testy jednostkowe (jeżeli jest taka potrzeba to wykorzystaj mocki) oraz
testy integracyjne.

Dokumentacja do swaggera: https://springdoc.org/
Dokumentacja do lomboka: https://projectlombok.org/
