Wersja, która przesz³a testy certyfikacyjne z KIR 12-09-2012.

Istotne zagadnienia
- w testach wydajnoœciowych wystepowa³y problemy z Optimistic Lock Exception oraz najprawdopodobniej po³¹czeniamy sieciowymi pomiêdzy BSB a KIR
- Optimistic Lock Exception jest rozwi¹zany za pomoc¹ mechanizmu, który ponawia 3krotnie próbê modyfikacji encji (Account)
- w aplikacji ustawiono Thread.sleep na 250 ms jako pauza pomiêdzy wysy³k¹ komunikatów (importowanych z pliku , do wys³ania), próby 500ms te¿ wykonano.
