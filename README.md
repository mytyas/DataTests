# DataTests
Ovo je skup testova koji vrše razne operacije sa podacima sa web-a, napisanih u POM frameworku. Bazne stranice se nalaze u src/main/java, a odgovarajući testovi u src/test/java. Ukratko o testovima:
1) src/test/java/ComputerDatabaseTests/ComputerDatabaseTests.java vrši niz testova na stranici https://computer-database.gatling.io/computers kao što je upis novog računara u bazu, brisanje, skidanje kompletne baze kao xlsx fajla itd. Uz svaki @Test je dat kratak opis u obliku komentara.
2) src/test/java/GoDaddyTests/GoDaddyTests.java - takođe vrši niz testova verifikacije na stranici GoDaddy, bliži opis testova je takođe dat u obliku komentara u svakom testu. Vrlo je moguće da više ne rade, budući da sajt GoDaddy stalno vrši izmene da bi se zaštitio od sakupljača podataka.
3) https://github.com/mytyas/DataTests/blob/main/src/test/java/GooglePageTests/GoogleTests.java - jednostavan test koji vraća sve ajax podatke google pretraživanja po ključnoj reči
4) src/test/java/PhpTravelsTests/BrokenLinksTests.java - test koji proverava broken linkove na stranici PhpTravels
5) src/test/java/TableTests - sadrži testove prikupljanja podataka iz statičkih i dinamičkih tabela
