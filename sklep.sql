-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 15 Kwi 2019, 21:58
-- Wersja serwera: 10.1.33-MariaDB
-- Wersja PHP: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `sklep`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `administator`
--

CREATE TABLE `administator` (
  `id` int(11) NOT NULL,
  `login` text COLLATE utf8_polish_ci NOT NULL,
  `haslo` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `administator`
--

INSERT INTO `administator` (`id`, `login`, `haslo`) VALUES
(1, 'piotr', 'zaq1@WSX');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `adres`
--

CREATE TABLE `adres` (
  `id` int(11) NOT NULL,
  `nazwa` text COLLATE utf8_polish_ci NOT NULL,
  `ulica` text COLLATE utf8_polish_ci NOT NULL,
  `miejscowosc` text COLLATE utf8_polish_ci NOT NULL,
  `wojewodztwo` text COLLATE utf8_polish_ci NOT NULL,
  `kod` text COLLATE utf8_polish_ci NOT NULL,
  `id_kraju` int(11) NOT NULL,
  `id_klienta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `adres`
--

INSERT INTO `adres` (`id`, `nazwa`, `ulica`, `miejscowosc`, `wojewodztwo`, `kod`, `id_kraju`, `id_klienta`) VALUES
(1, 'domowy', 'ul. Zielona 1/a', 'Kraków', 'małopolskie', '30-300', 1, 1),
(2, 'firmowy', 'ul. Niebieska 666', 'Kraków', 'małopolskie', '30-400', 1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kategoria`
--

CREATE TABLE `kategoria` (
  `id` int(11) NOT NULL,
  `nazwa` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `kategoria`
--

INSERT INTO `kategoria` (`id`, `nazwa`) VALUES
(1, 'Smartfon'),
(2, 'Tablet'),
(3, 'Smartwatch');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `id` int(11) NOT NULL,
  `imie` text COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` text COLLATE utf8_polish_ci NOT NULL,
  `email` text COLLATE utf8_polish_ci NOT NULL,
  `nr_telefonu` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `klient`
--

INSERT INTO `klient` (`id`, `imie`, `nazwisko`, `email`, `nr_telefonu`) VALUES
(1, 'Piotr', 'Kowalski', 'piotr@kowalski.pl', '123456789');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kraj`
--

CREATE TABLE `kraj` (
  `id` int(11) NOT NULL,
  `nazwa` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `kraj`
--

INSERT INTO `kraj` (`id`, `nazwa`) VALUES
(1, 'Polska'),
(2, 'Niemcy');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `magazyn`
--

CREATE TABLE `magazyn` (
  `id_produktu` int(11) NOT NULL,
  `ilosc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `magazyn`
--

INSERT INTO `magazyn` (`id_produktu`, `ilosc`) VALUES
(1, 12),
(2, 413),
(3, 53),
(4, 0),
(5, 133),
(6, 32);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `producent`
--

CREATE TABLE `producent` (
  `id` int(11) NOT NULL,
  `nazwa` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `producent`
--

INSERT INTO `producent` (`id`, `nazwa`) VALUES
(1, 'Huawei'),
(2, 'Honor'),
(3, 'Sony'),
(4, 'Samsung'),
(5, 'LG'),
(6, 'Motorola');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkt`
--

CREATE TABLE `produkt` (
  `id` int(11) NOT NULL,
  `id_producenta` int(11) NOT NULL,
  `id_kategorii` int(11) NOT NULL,
  `nazwa` text COLLATE utf8_polish_ci NOT NULL,
  `opis` text COLLATE utf8_polish_ci NOT NULL,
  `cena` double NOT NULL,
  `id_zdjecia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `produkt`
--

INSERT INTO `produkt` (`id`, `id_producenta`, `id_kategorii`, `nazwa`, `opis`, `cena`, `id_zdjecia`) VALUES
(1, 2, 1, 'Honor 10', 'Honor 10 128 GB niebieski jest niesamowicie szybki, intrygująco piękny, intuicyjny w obsłudze i pozwala robić profesjonalne zdjęcia zawsze wtedy, gdy masz na to ochotę. Urzekający design tworzą dwie tafle szkła, spięte w jedność metalową ramą w jedną. Front wypełnia niema w całości panoramiczny ekran FullView FHD+ o przekątnej 5,84”. Tuż nad nim czeka genialna kamera selfie 24 Mpix. Z tyłu znajdziesz podwójny aparat z systemem inteligentnych algorytmów, który zaskoczy Cię paletą fotograficznych możliwości. \r\n\r\nSprawdź, jak Honor 10 128 GB niebieski wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo, aby obrócić produkt, lub skorzystaj z przycisków nawigacyjnych.', 1749, 1),
(2, 2, 1, 'Honor View 20', 'Nadchodzi nowa era technologii, a jej zwiastunem jest Honor View 20 niebieski - pierwszy smartfon wyposażony w aparat 3D. Jego aluminiowa ramka szczelnie obejmuje szklany ekran All-View z osadzonym w nim obiektywem aparatu selfie. Wewnątrz pracuje inteligentny procesor Kirin 980 z podwójnym układem NPU. Całość przykrywa panel ze szkła wzbogaconego nanopowłoką Aurora, który mieści obiektywy rewolucyjnego i inteligentnego aparatu.\r\n\r\nSprawdź, jak produkt wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo aby obrócić produkt lub skorzystaj z przycisków nawigacyjnych', 2699, 1),
(3, 1, 1, 'Huawei Mate 20 Twilight', 'Istotą fotografii jest zaawansowany aparat. Huawei Mate 20 Twilight ma genialny aparat – potrójny aparat, stworzony przez firmę Leica. Ma także ogromną moc, pochodzącą z procesora Kirin 980. Jest to pierwszy procesor wyprodukowany w procesie technologicznym 7nm. Wyróżnia go olbrzymia wydajność oraz fenomenalna szybkość. Wszystko co robisz odbywa się w imponującym tempie. Tym bardziej, że każdy proces wspierają sztuczna inteligencja, system Android 9.0 Pie oraz nakładka EMUI 9. Odkryj Huawei Mate 20.\r\n\r\nSprawdź, jak Huawei Mate 20 Twilight wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo, aby obrócić produkt, lub skorzystaj z przycisków nawigacyjnych.', 2699, 1),
(4, 1, 1, 'Huawei P30 Opal', 'Huawei P30 128 GB Opal to absolutny unikat. Designerski majstersztyk, który dzięki subtelnie zaokrąglonym krawędziom prezentuje się genialnie. Front zajmuje ekran OLED 6,1” z paletą żywych barw. U góry znajduje się minimalistyczne wcięcie DewDrop, u dołu skaner linii papilarnych w całości zintegrowany z wyświetlaczem. Dzięki niemu Huawei P30 odblokujesz w ułamku sekundy. Imponujące tempo towarzyszy też ładowaniu baterii, która osiąga 25% w zaledwie 10 minut.\r\n\r\nSprawdź, jak produkt wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo aby obrócić produkt lub skorzystaj z przycisków nawigacyjnych.', 2999, 1),
(5, 1, 2, 'Huawei MediaPad M5 10 WIFI Kirin960s/4GB/64GB/8.0 szary', 'Poznaj szarego Huawei MediaPad M5 WiFi, którego mocne podzespoły zamknięto w eleganckiej obudowie ze szkła i metalu. Aluminiową konstrukcję o zaokrąglonych brzegach wykończono w procesie piaskowania co nadało jej przyjemnej w dotyku faktury. MediaPad M5 wyposażono także w cztery głośniki legendarnej firmy Harman Kardon, zachwycające dźwiękiem Hi-Res Audio. Ekran tabletu pokryto odpornym na zarysowania szkłem 2.5D, które dodatkowo usztywniło konstrukcję. Wewnątrz obudowy kryje się moc ośmiordzeniowego procesora, zasilana ogromną baterią 7500 mAh, dzięki której możesz cieszyć się multimediami nieprzerwanie przez cały dzień.', 1449, 1),
(6, 4, 3, 'Samsung Galaxy Watch R800 46mm Silver\r\n', 'Samsung Galaxy Watch 46 mm srebrny swoimi funkcjami daleko wykracza poza ramy smartwatcha. To urządzenie, które łączy Cię ze światem. Osobisty asystent o wielu twarzach, który będzie przy Tobie na treningu, w biurze i będzie Ci towarzyszył w podróży. Jest niebywale odporny, a przy tym elegancki. Galaxy Watch wygląda jak klasyczny, analogowy zegarek klasy premium, lecz wystarczy jeden dotyk, by aktywować jego inteligentne funkcje.\r\n\r\nSprawdź, jak Galaxy Watch 46 mm srebrny wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo aby obrócić produkt lub skorzystaj z przycisków nawigacyjnych.', 1399, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienie`
--

CREATE TABLE `zamowienie` (
  `id` int(11) NOT NULL,
  `id_klienta` int(11) NOT NULL,
  `id_adresu` int(11) NOT NULL,
  `data_zlozenia` date NOT NULL,
  `zrealizowane` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `zamowienie`
--

INSERT INTO `zamowienie` (`id`, `id_klienta`, `id_adresu`, `data_zlozenia`, `zrealizowane`) VALUES
(1, 1, 2, '2019-04-15', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienie_produkt`
--

CREATE TABLE `zamowienie_produkt` (
  `id_zamowienia` int(11) NOT NULL,
  `id_produktu` int(11) NOT NULL,
  `ilosc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `zamowienie_produkt`
--

INSERT INTO `zamowienie_produkt` (`id_zamowienia`, `id_produktu`, `ilosc`) VALUES
(1, 3, 1),
(1, 4, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zdjecie`
--

CREATE TABLE `zdjecie` (
  `id` int(11) NOT NULL,
  `nazwa` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `zdjecie`
--

INSERT INTO `zdjecie` (`id`, `nazwa`) VALUES
(1, 'zdjecie0');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `administator`
--
ALTER TABLE `administator`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `adres`
--
ALTER TABLE `adres`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kraju` (`id_kraju`),
  ADD KEY `id_klienta` (`id_klienta`);

--
-- Indeksy dla tabeli `kategoria`
--
ALTER TABLE `kategoria`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `kraj`
--
ALTER TABLE `kraj`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `magazyn`
--
ALTER TABLE `magazyn`
  ADD PRIMARY KEY (`id_produktu`);

--
-- Indeksy dla tabeli `producent`
--
ALTER TABLE `producent`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `produkt`
--
ALTER TABLE `produkt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producenta` (`id_producenta`),
  ADD KEY `id_kategorii` (`id_kategorii`),
  ADD KEY `id_zdjecia` (`id_zdjecia`);

--
-- Indeksy dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_klienta` (`id_klienta`),
  ADD KEY `id_adresu` (`id_adresu`);

--
-- Indeksy dla tabeli `zamowienie_produkt`
--
ALTER TABLE `zamowienie_produkt`
  ADD PRIMARY KEY (`id_zamowienia`,`id_produktu`),
  ADD KEY `id_produktu` (`id_produktu`);

--
-- Indeksy dla tabeli `zdjecie`
--
ALTER TABLE `zdjecie`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `administator`
--
ALTER TABLE `administator`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `adres`
--
ALTER TABLE `adres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `kategoria`
--
ALTER TABLE `kategoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `klient`
--
ALTER TABLE `klient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `kraj`
--
ALTER TABLE `kraj`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `magazyn`
--
ALTER TABLE `magazyn`
  MODIFY `id_produktu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `producent`
--
ALTER TABLE `producent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `produkt`
--
ALTER TABLE `produkt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `zdjecie`
--
ALTER TABLE `zdjecie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `adres`
--
ALTER TABLE `adres`
  ADD CONSTRAINT `adres_ibfk_1` FOREIGN KEY (`id_kraju`) REFERENCES `kraj` (`id`),
  ADD CONSTRAINT `adres_ibfk_2` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id`);

--
-- Ograniczenia dla tabeli `magazyn`
--
ALTER TABLE `magazyn`
  ADD CONSTRAINT `magazyn_ibfk_1` FOREIGN KEY (`id_produktu`) REFERENCES `produkt` (`id`);

--
-- Ograniczenia dla tabeli `produkt`
--
ALTER TABLE `produkt`
  ADD CONSTRAINT `produkt_ibfk_1` FOREIGN KEY (`id_producenta`) REFERENCES `producent` (`id`),
  ADD CONSTRAINT `produkt_ibfk_2` FOREIGN KEY (`id_kategorii`) REFERENCES `kategoria` (`id`),
  ADD CONSTRAINT `produkt_ibfk_3` FOREIGN KEY (`id_zdjecia`) REFERENCES `zdjecie` (`id`);

--
-- Ograniczenia dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD CONSTRAINT `zamowienie_ibfk_1` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id`),
  ADD CONSTRAINT `zamowienie_ibfk_2` FOREIGN KEY (`id_adresu`) REFERENCES `adres` (`id`);

--
-- Ograniczenia dla tabeli `zamowienie_produkt`
--
ALTER TABLE `zamowienie_produkt`
  ADD CONSTRAINT `zamowienie_produkt_ibfk_1` FOREIGN KEY (`id_produktu`) REFERENCES `produkt` (`id`),
  ADD CONSTRAINT `zamowienie_produkt_ibfk_2` FOREIGN KEY (`id_zamowienia`) REFERENCES `zamowienie` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
