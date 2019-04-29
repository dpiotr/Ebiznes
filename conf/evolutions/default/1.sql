# --- !Ups

CREATE TABLE "Producent"
(
    "id"    integer not null primary key autoincrement,
    "nazwa" varchar not null
);

CREATE TABLE "Kategoria"
(
    "id"    integer not null primary key autoincrement,
    "nazwa" varchar not null
);

CREATE TABLE "Zdjecie"
(
    "id"    integer not null primary key autoincrement,
    "nazwa" varchar not null
);

CREATE TABLE "Produkt"
(
    "id"            integer not null primary key autoincrement,
    "id_producenta" integer not null,
    "id_kategorii"  integer not null,
    "nazwa"         varchar not null,
    "opis"          varchar not null,
    "cena"          double  not null,
    "id_zdjecia"    integer not null,
    foreign key ("id_producenta") references "Producent" ("id"),
    foreign key ("id_kategorii") references "Kategoria" ("id"),
    foreign key ("id_zdjecia") references "Zdjecie" ("id")
);

CREATE TABLE "Magazyn"
(
    "id_produktu" integer not null primary key autoincrement,
    "ilosc"       integer not null,
    foreign key ("id_produktu") references "Produkt" ("id")
);

CREATE TABLE "Administator"
(
    "id"    integer not null primary key autoincrement,
    "login" varchar not null,
    "haslo" varchar not null
);

CREATE TABLE "Country"
(
    "id"   integer not null primary key autoincrement,
    "name" varchar not null
);

CREATE TABLE "klient"
(
    "id"          integer not null primary key autoincrement,
    "imie"        varchar not null,
    "nazwisko"    varchar not null,
    "email"       varchar not null,
    "nr_telefonu" varchar not null
);

CREATE TABLE "adres"
(
    "id"          integer not null primary key autoincrement,
    "nazwa"       varchar not null,
    "ulica"       varchar not null,
    "miejscowosc" varchar not null,
    "wojewodztwo" varchar not null,
    "kod"         varchar not null,
    "id_kraju"    integer not null,
    "id_klienta"  integer not null,
    foreign key ("id_kraju") references "Country" ("id"),
    foreign key ("id_klienta") references "Klient" ("id")
);

CREATE TABLE "zamowienie"
(
    "id"            integer not null primary key autoincrement,
    "id_klienta"    integer not null,
    "id_adresu"     integer not null,
    "data_zlozenia" date    not null,
    "zrealizowane"  integer not null,
    foreign key ("id_klienta") references "Klient" ("id"),
    foreign key ("id_adresu") references "Adres" ("id")
);

CREATE TABLE "zamowienie_produkt"
(
    "id_zamowienia" integer not null,
    "id_produktu"   integer not null,
    "ilosc"         integer not null,
    primary key ("id_zamowienia", "id_produktu"),
    foreign key ("id_produktu") references "Produkt" ("id"),
    foreign key ("id_zamowienia") references "Zamowienie" ("id")
);

# --- !Downs

DROP TABLE IF EXISTS "zamowienie";

DROP TABLE IF EXISTS "zamowienie_produkt";

DROP TABLE IF EXISTS "Adres";

DROP TABLE IF EXISTS "Country";

DROP TABLE IF EXISTS "Klient";

DROP TABLE IF EXISTS "Magazyn";

DROP TABLE IF EXISTS "Administator";

DROP TABLE IF EXISTS "Produkt";

DROP TABLE IF EXISTS "Kategoria";

DROP TABLE IF EXISTS "Zdjecie";

DROP TABLE IF EXISTS "Producent";