Wersja, kt�ra przesz�a testy certyfikacyjne z KIR 12-09-2012.

Istotne zagadnienia
- w testach wydajno�ciowych wystepowa�y problemy z Optimistic Lock Exception oraz najprawdopodobniej po��czeniamy sieciowymi pomi�dzy BSB a KIR
- Optimistic Lock Exception jest rozwi�zany za pomoc� mechanizmu, kt�ry ponawia 3krotnie pr�b� modyfikacji encji (Account)
- w aplikacji ustawiono Thread.sleep na 250 ms jako pauza pomi�dzy wysy�k� komunikat�w (importowanych z pliku , do wys�ania), pr�by 500ms te� wykonano.
