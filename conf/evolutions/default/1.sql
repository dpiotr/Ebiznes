# --- !Ups

CREATE TABLE "Producer"
(
    "id"   integer not null primary key autoincrement,
    "name" varchar not null
);

CREATE TABLE "Category"
(
    "id"   integer not null primary key autoincrement,
    "name" varchar not null
);

CREATE TABLE "Photo"
(
    "id"   integer not null primary key autoincrement,
    "name" varchar not null
);

CREATE TABLE "Product"
(
    "id"          integer not null primary key autoincrement,
    "id_producer" integer not null,
    "id_category" integer not null,
    "id_photo"    integer not null,
    "name"        varchar not null,
    "description" varchar not null,
    "price"       double  not null,
    foreign key ("id_producer") references "Producer" ("id"),
    foreign key ("id_category") references "Category" ("id"),
    foreign key ("id_photo") references "Photo" ("id")
);

CREATE TABLE "Depot"
(
    "id"         integer not null primary key autoincrement,
    "id_product" integer not null,
    "quantity"   integer not null,
    foreign key ("id_product") references "Product" ("id")
);

CREATE TABLE "Accounts"
(
    "id"       integer not null primary key autoincrement,
    "login"    varchar not null,
    "password" varchar not null
);

CREATE TABLE "Country"
(
    "id"   integer not null primary key autoincrement,
    "name" varchar not null
);

CREATE TABLE "Client"
(
    "id"        integer not null primary key autoincrement,
    "name"      varchar not null,
    "surname"   varchar not null,
    "email"     varchar not null,
    "telephone" varchar not null
);

CREATE TABLE "Address"
(
    "id"          integer not null primary key autoincrement,
    "id_country"  integer not null,
    "id_client"   integer not null,
    "name"        varchar not null,
    "street"      varchar not null,
    "city"        varchar not null,
    "state"       varchar not null,
    "postal_code" varchar not null,
    foreign key ("id_country") references "Country" ("id"),
    foreign key ("id_client") references "Client" ("id")
);

CREATE TABLE "Order"
(
    "id"         integer not null primary key autoincrement,
    "id_client"  integer not null,
    "id_address" integer not null,
    "date"       date    not null,
    "is_done"    integer not null,
    foreign key ("id_client") references "Client" ("id"),
    foreign key ("id_address") references "Address" ("id")
);

CREATE TABLE "Order_Product"
(
    "id_order"   integer not null,
    "id_product" integer not null,
    "quantity"   integer not null,
    primary key ("id_order", "id_product"),
    foreign key ("id_product") references "Product" ("id"),
    foreign key ("id_order") references "Order" ("id")
);

INSERT INTO "Producer" ("id", "name")
VALUES (NULL, 'Huawei'),
       (NULL, 'Honor'),
       (NULL, 'Sony'),
       (NULL, 'Samsung'),
       (NULL, 'LG'),
       (NULL, 'Motorola');

INSERT INTO "Category" ("id", "name")
VALUES (NULL, 'Smartfon'),
       (NULL, 'Tablet'),
       (NULL, 'Smartwatch');

INSERT INTO "Photo" ("id", "name")
VALUES (NULL, 'zdjecie0');

INSERT INTO "Product" ("id", "id_producer", "id_category", "name", "description", "price", "id_photo")
VALUES (NULL, '2', '1', 'Honor 10',
        'Honor 10 128 GB niebieski jest niesamowicie szybki, intrygująco piękny, intuicyjny w obsłudze i pozwala robić profesjonalne zdjęcia zawsze wtedy, gdy masz na to ochotę. Urzekający design tworzą dwie tafle szkła, spięte w jedność metalową ramą w jedną. Front wypełnia niema w całości panoramiczny ekran FullView FHD+ o przekątnej 5,84”. Tuż nad nim czeka genialna kamera selfie 24 Mpix. Z tyłu znajdziesz podwójny aparat z systemem inteligentnych algorytmów, który zaskoczy Cię paletą fotograficznych możliwości. \r\n\r\nSprawdź, jak Honor 10 128 GB niebieski wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo, aby obrócić produkt, lub skorzystaj z przycisków nawigacyjnych.',
        '1749.00', 1),
       (NULL, '2', '1', 'Honor View 20',
        'Nadchodzi nowa era technologii, a jej zwiastunem jest Honor View 20 niebieski - pierwszy smartfon wyposażony w aparat 3D. Jego aluminiowa ramka szczelnie obejmuje szklany ekran All-View z osadzonym w nim obiektywem aparatu selfie. Wewnątrz pracuje inteligentny procesor Kirin 980 z podwójnym układem NPU. Całość przykrywa panel ze szkła wzbogaconego nanopowłoką Aurora, który mieści obiektywy rewolucyjnego i inteligentnego aparatu.\r\n\r\nSprawdź, jak produkt wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo aby obrócić produkt lub skorzystaj z przycisków nawigacyjnych',
        '2699.0', 1),
       (NULL, '1', '1', 'Huawei Mate 20 Twilight',
        'Istotą fotografii jest zaawansowany aparat. Huawei Mate 20 Twilight ma genialny aparat – potrójny aparat, stworzony przez firmę Leica. Ma także ogromną moc, pochodzącą z procesora Kirin 980. Jest to pierwszy procesor wyprodukowany w procesie technologicznym 7nm. Wyróżnia go olbrzymia wydajność oraz fenomenalna szybkość. Wszystko co robisz odbywa się w imponującym tempie. Tym bardziej, że każdy proces wspierają sztuczna inteligencja, system Android 9.0 Pie oraz nakładka EMUI 9. Odkryj Huawei Mate 20.\r\n\r\nSprawdź, jak Huawei Mate 20 Twilight wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo, aby obrócić produkt, lub skorzystaj z przycisków nawigacyjnych.',
        '2699.0', 1),
       (NULL, '1', '1', 'Huawei P30 Opal',
        'Huawei P30 128 GB Opal to absolutny unikat. Designerski majstersztyk, który dzięki subtelnie zaokrąglonym krawędziom prezentuje się genialnie. Front zajmuje ekran OLED 6,1” z paletą żywych barw. U góry znajduje się minimalistyczne wcięcie DewDrop, u dołu skaner linii papilarnych w całości zintegrowany z wyświetlaczem. Dzięki niemu Huawei P30 odblokujesz w ułamku sekundy. Imponujące tempo towarzyszy też ładowaniu baterii, która osiąga 25% w zaledwie 10 minut.\r\n\r\nSprawdź, jak produkt wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo aby obrócić produkt lub skorzystaj z przycisków nawigacyjnych.',
        '2999.0', 1),
       (NULL, '1', '2', 'Huawei MediaPad M5 10 WIFI Kirin960s/4GB/64GB/8.0 szary',
        'Poznaj szarego Huawei MediaPad M5 WiFi, którego mocne podzespoły zamknięto w eleganckiej obudowie ze szkła i metalu. Aluminiową konstrukcję o zaokrąglonych brzegach wykończono w procesie piaskowania co nadało jej przyjemnej w dotyku faktury. MediaPad M5 wyposażono także w cztery głośniki legendarnej firmy Harman Kardon, zachwycające dźwiękiem Hi-Res Audio. Ekran tabletu pokryto odpornym na zarysowania szkłem 2.5D, które dodatkowo usztywniło konstrukcję. Wewnątrz obudowy kryje się moc ośmiordzeniowego procesora, zasilana ogromną baterią 7500 mAh, dzięki której możesz cieszyć się multimediami nieprzerwanie przez cały dzień.',
        '1449.0', 1),
       (NULL, '4', '3', 'Samsung Galaxy Watch R800 46mm Silver\r\n',
        'Samsung Galaxy Watch 46 mm srebrny swoimi funkcjami daleko wykracza poza ramy smartwatcha. To urządzenie, które łączy Cię ze światem. Osobisty asystent o wielu twarzach, który będzie przy Tobie na treningu, w biurze i będzie Ci towarzyszył w podróży. Jest niebywale odporny, a przy tym elegancki. Galaxy Watch wygląda jak klasyczny, analogowy zegarek klasy premium, lecz wystarczy jeden dotyk, by aktywować jego inteligentne funkcje.\r\n\r\nSprawdź, jak Galaxy Watch 46 mm srebrny wygląda w rzeczywistości. Chwyć zdjęcie poniżej i przeciągnij je w lewo lub prawo aby obrócić produkt lub skorzystaj z przycisków nawigacyjnych.',
        '1399.0', 1);

INSERT INTO "Depot" ("id", "id_product", "quantity")
VALUES (NULL, 1, 12),
       (NULL, 2, 413),
       (NULL, 3, 53),
       (NULL, 4, 0),
       (NULL, 5, 133),
       (NULL, 6, 32);

INSERT INTO "Country" ("id", "name")
VALUES (NULL, 'Polska'),
       (NULL, 'Niemcy');

INSERT INTO "Client" ("id", "name", "surname", "email", "telephone")
VALUES (NULL, 'Piotr', 'Kowalski', 'piotr@kowalski.pl', '123456789');

INSERT INTO "Address" ("id", "name", "street", "city", "state", "postal_code", "id_country", "id_client")
VALUES (NULL, 'domowy', 'ul. Zielona 1/a', 'Kraków', 'małopolskie', '30-300', '1', '1'),
       (NULL, 'firmowy', 'ul. Niebieska 666', 'Kraków', 'małopolskie', '30-400', '1', '1');

INSERT INTO "Order" ("id", "id_client", "id_address", "date", "is_done")
VALUES (NULL, '1', '2', '2019-04-15', 0);

INSERT INTO "Order_Product" ("id_order", "id_product", "quantity")
VALUES ('1', '3', '1'),
       ('1', '4', '1');

INSERT INTO "Accounts" ("id", "login", "password")
VALUES (NULL, 'piotr', 'zaq1@WSX');

# --- !Downs

DROP TABLE IF EXISTS "Order";

DROP TABLE IF EXISTS "Order_Product";

DROP TABLE IF EXISTS "Address";

DROP TABLE IF EXISTS "Country";

DROP TABLE IF EXISTS "Client";

DROP TABLE IF EXISTS "Depot";

DROP TABLE IF EXISTS "Accounts";

DROP TABLE IF EXISTS "Product";

DROP TABLE IF EXISTS "Category";

DROP TABLE IF EXISTS "Photo";

DROP TABLE IF EXISTS "Producer";