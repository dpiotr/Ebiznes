# --- !Ups

CREATE TABLE `Producent` ( `id` INT NOT NULL AUTO_INCREMENT , `nazwa` TEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `Kategoria` ( `id` INT NOT NULL AUTO_INCREMENT , `nazwa` TEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `Zdjecie` ( `id` INT NOT NULL AUTO_INCREMENT , `nazwa` TEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `Produkt` ( `id` INT NOT NULL AUTO_INCREMENT , `id_producenta` INT NOT NULL , `id_kategorii` INT NOT NULL , `nazwa` TEXT NOT NULL , `opis` TEXT NOT NULL , `cena` DOUBLE NOT NULL , `id_zdjecia` INT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `Magazyn` ( `id_produktu` INT NOT NULL AUTO_INCREMENT , `ilosc` INT NOT NULL , PRIMARY KEY (`id_produktu`)) ENGINE = InnoDB;

CREATE TABLE `Administator` ( `id` INT NOT NULL AUTO_INCREMENT , `login` TEXT NOT NULL , `haslo` TEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `kraj` ( `id` INT NOT NULL AUTO_INCREMENT , `nazwa` TEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `klient` ( `id` INT NOT NULL AUTO_INCREMENT , `imie` TEXT NOT NULL , `nazwisko` TEXT NOT NULL , `email` TEXT NOT NULL , `nr_telefonu` TEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `adres` ( `id` INT NOT NULL AUTO_INCREMENT , `nazwa` TEXT NOT NULL , `ulica` TEXT NOT NULL , `miejscowosc` TEXT NOT NULL , `wojewodztwo` TEXT NOT NULL , `kod` TEXT NOT NULL , `id_kraju` INT NOT NULL , `id_klienta` INT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `zamowienie` ( `id` INT NOT NULL AUTO_INCREMENT , `id_klienta` INT NOT NULL , `id_adresu` INT NOT NULL , `data_zlozenia` DATE NOT NULL , `zrealizowane` BOOLEAN NOT NULL DEFAULT FALSE , PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE `zamowienie_produkt` ( `id_zamowienia` INT NOT NULL , `id_produktu` INT NOT NULL , `ilosc` INT NOT NULL , PRIMARY KEY (`id_zamowienia`, `id_produktu`)) ENGINE = InnoDB;



ALTER TABLE `adres` ADD FOREIGN KEY (`id_kraju`) REFERENCES `Kraj`(`id`);

ALTER TABLE `adres` ADD FOREIGN KEY (`id_klienta`) REFERENCES `Klient`(`id`);

ALTER TABLE `produkt` ADD FOREIGN KEY (`id_producenta`) REFERENCES `Producent`(`id`);

ALTER TABLE `produkt` ADD FOREIGN KEY (`id_kategorii`) REFERENCES `Kategoria`(`id`);

ALTER TABLE `produkt` ADD FOREIGN KEY (`id_zdjecia`) REFERENCES `Zdjecie`(`id`);

ALTER TABLE `magazyn` ADD FOREIGN KEY (`id_produktu`) REFERENCES `Produkt`(`id`);

ALTER TABLE `zamowienie` ADD FOREIGN KEY (`id_klienta`) REFERENCES `Klient`(`id`);

ALTER TABLE `zamowienie` ADD FOREIGN KEY (`id_adresu`) REFERENCES `Adres`(`id`);

ALTER TABLE `zamowienie_produkt` ADD FOREIGN KEY (`id_produktu`) REFERENCES `Produkt`(`id`);

ALTER TABLE `zamowienie_produkt` ADD FOREIGN KEY (`id_zamowienia`) REFERENCES `Zamowienie`(`id`);


--
-- INSERT INTO `Producent` (`id`, `nazwa`) VALUES (NULL, 'Huawei'), (NULL, 'Honor'), (NULL, 'Sony'), (NULL, 'Samsung'), (NULL, 'LG'), (NULL, 'Motorola');
--
-- INSERT INTO `Kategoria` (`id`, `nazwa`) VALUES (NULL, 'Smartfon'), (NULL, 'Tablet'), (NULL, 'Smartwatch');
--
-- INSERT INTO `Zdjecie` (`id`, `nazwa`) VALUES (NULL, 'zdjecie0');
--
-- INSERT INTO `Produkt` (`id`, `id_producenta`, `id_kategorii`, `nazwa`, `opis`, `cena`, `id_zdjecia`) VALUES (NULL, '2', '1', 'Honor 10', 'Honor 10 128 GB niebieski jest niesamowicie szybki, intrygująco piękny, intuicyjny w obsłudze i pozwala robić profesjonalne zdjęcia zawsze wtedy, gdy masz na to ochotę. Urzekający design tworzą dwie tafle szkła, spięte w jedność metalową ramą w jedną. Front wypełnia niema w całości panoramiczny ekran FullView FHD+ o przekątnej 5,84”. Tuż nad nim czeka genialna kamera selfie 24 Mpix. Z tyłu znajdziesz podwójny aparat z systemem inteligentnych algorytmów, który zaskoczy Cię paletą fotograficznych możliwości. \r\n\r\nSprawdź, jak Honor 10 128 GB niebieski wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo, aby obrócić produkt, lub skorzystaj z przycisków nawigacyjnych.', '1749.00', 1);
--
-- INSERT INTO `Produkt` (`id`, `id_producenta`, `id_kategorii`, `nazwa`, `opis`, `cena`, `id_zdjecia`) VALUES (NULL, '2', '1', 'Honor View 20', 'Nadchodzi nowa era technologii, a jej zwiastunem jest Honor View 20 niebieski - pierwszy smartfon wyposażony w aparat 3D. Jego aluminiowa ramka szczelnie obejmuje szklany ekran All-View z osadzonym w nim obiektywem aparatu selfie. Wewnątrz pracuje inteligentny procesor Kirin 980 z podwójnym układem NPU. Całość przykrywa panel ze szkła wzbogaconego nanopowłoką Aurora, który mieści obiektywy rewolucyjnego i inteligentnego aparatu.\r\n\r\nSprawdź, jak produkt wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo aby obrócić produkt lub skorzystaj z przycisków nawigacyjnych', '2699.0', 1);
--
-- INSERT INTO `Produkt` (`id`, `id_producenta`, `id_kategorii`, `nazwa`, `opis`, `cena`, `id_zdjecia`) VALUES (NULL, '1', '1', 'Huawei Mate 20 Twilight', 'Istotą fotografii jest zaawansowany aparat. Huawei Mate 20 Twilight ma genialny aparat – potrójny aparat, stworzony przez firmę Leica. Ma także ogromną moc, pochodzącą z procesora Kirin 980. Jest to pierwszy procesor wyprodukowany w procesie technologicznym 7nm. Wyróżnia go olbrzymia wydajność oraz fenomenalna szybkość. Wszystko co robisz odbywa się w imponującym tempie. Tym bardziej, że każdy proces wspierają sztuczna inteligencja, system Android 9.0 Pie oraz nakładka EMUI 9. Odkryj Huawei Mate 20.\r\n\r\nSprawdź, jak Huawei Mate 20 Twilight wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo, aby obrócić produkt, lub skorzystaj z przycisków nawigacyjnych.', '2699.0', 1);
--
-- INSERT INTO `Produkt` (`id`, `id_producenta`, `id_kategorii`, `nazwa`, `opis`, `cena`, `id_zdjecia`) VALUES (NULL, '1', '1', 'Huawei P30 Opal', 'Huawei P30 128 GB Opal to absolutny unikat. Designerski majstersztyk, który dzięki subtelnie zaokrąglonym krawędziom prezentuje się genialnie. Front zajmuje ekran OLED 6,1” z paletą żywych barw. U góry znajduje się minimalistyczne wcięcie DewDrop, u dołu skaner linii papilarnych w całości zintegrowany z wyświetlaczem. Dzięki niemu Huawei P30 odblokujesz w ułamku sekundy. Imponujące tempo towarzyszy też ładowaniu baterii, która osiąga 25% w zaledwie 10 minut.\r\n\r\nSprawdź, jak produkt wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo aby obrócić produkt lub skorzystaj z przycisków nawigacyjnych.', '2999.0', 1);
--
-- INSERT INTO `Produkt` (`id`, `id_producenta`, `id_kategorii`, `nazwa`, `opis`, `cena`, `id_zdjecia`) VALUES (NULL, '1', '2', 'Huawei MediaPad M5 10 WIFI Kirin960s/4GB/64GB/8.0 szary', 'Poznaj szarego Huawei MediaPad M5 WiFi, którego mocne podzespoły zamknięto w eleganckiej obudowie ze szkła i metalu. Aluminiową konstrukcję o zaokrąglonych brzegach wykończono w procesie piaskowania co nadało jej przyjemnej w dotyku faktury. MediaPad M5 wyposażono także w cztery głośniki legendarnej firmy Harman Kardon, zachwycające dźwiękiem Hi-Res Audio. Ekran tabletu pokryto odpornym na zarysowania szkłem 2.5D, które dodatkowo usztywniło konstrukcję. Wewnątrz obudowy kryje się moc ośmiordzeniowego procesora, zasilana ogromną baterią 7500 mAh, dzięki której możesz cieszyć się multimediami nieprzerwanie przez cały dzień.', '1449.0', 1);
--
-- INSERT INTO `Produkt` (`id`, `id_producenta`, `id_kategorii`, `nazwa`, `opis`, `cena`, `id_zdjecia`) VALUES (NULL, '4', '3', 'Samsung Galaxy Watch R800 46mm Silver\r\n', 'Samsung Galaxy Watch 46 mm srebrny swoimi funkcjami daleko wykracza poza ramy smartwatcha. To urządzenie, które łączy Cię ze światem. Osobisty asystent o wielu twarzach, który będzie przy Tobie na treningu, w biurze i będzie Ci towarzyszył w podróży. Jest niebywale odporny, a przy tym elegancki. Galaxy Watch wygląda jak klasyczny, analogowy zegarek klasy premium, lecz wystarczy jeden dotyk, by aktywować jego inteligentne funkcje.\r\n\r\nSprawdź, jak Galaxy Watch 46 mm srebrny wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo aby obrócić produkt lub skorzystaj z przycisków nawigacyjnych.', '1399.0', 1);
--
-- INSERT INTO `Magazyn` (`id_produktu`, `ilosc`) VALUES (1, 12), (2,413), (3, 53), (4, 0), (5, 133), (6, 32);
--
-- INSERT INTO `kraj` (`id`, `nazwa`) VALUES (NULL, 'Polska'), (NULL, 'Niemcy');
--
-- INSERT INTO `klient` (`id`, `imie`, `nazwisko`, `email`, `nr_telefonu`) VALUES (NULL, 'Piotr', 'Kowalski', 'piotr@kowalski.pl', '123456789');
--
-- INSERT INTO `adres` (`id`, `nazwa`, `ulica`, `miejscowosc`, `wojewodztwo`, `kod`, `id_kraju`, `id_klienta`) VALUES (NULL, 'domowy', 'ul. Zielona 1/a', 'Kraków', 'małopolskie', '30-300', '1', '1'), (NULL, 'firmowy', 'ul. Niebieska 666', 'Kraków', 'małopolskie', '30-400', '1', '1');
--
-- INSERT INTO `zamowienie` (`id`, `id_klienta`, `id_adresu`, `data_zlozenia`, `zrealizowane`) VALUES (NULL, '1', '2', '2019-04-15', '0');
--
-- INSERT INTO `zamowienie_produkt` (`id_zamowienia`, `id_produktu`, `ilosc`) VALUES ('1', '3', '1'), ('1', '4', '1');
--
-- INSERT INTO `Administator` (`id`, `login`, `haslo`) VALUES (NULL, 'piotr', 'zaq1@WSX');

# --- !Downs

DROP TABLE IF EXISTS `Magazyn`;

DROP TABLE IF EXISTS `Administator`;

DROP TABLE IF EXISTS `zamowienie`;

DROP TABLE IF EXISTS `zamowienie_produkt`;

DROP TABLE IF EXISTS `Adres`;

DROP TABLE IF EXISTS `Kraj`;

DROP TABLE IF EXISTS `Klient`;

DROP TABLE IF EXISTS `Produkt`;

DROP TABLE IF EXISTS `Kategoria`;

DROP TABLE IF EXISTS `Zdjecie`;

DROP TABLE IF EXISTS `Producent`;