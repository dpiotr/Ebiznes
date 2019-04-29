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
    "id_product" integer not null primary key autoincrement,
    "quantity"   integer not null,
    foreign key ("id_product") references "Product" ("id")
);

CREATE TABLE "Administration"
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

# --- !Downs

DROP TABLE IF EXISTS "Order";

DROP TABLE IF EXISTS "Order_Product";

DROP TABLE IF EXISTS "Address";

DROP TABLE IF EXISTS "Country";

DROP TABLE IF EXISTS "Client";

DROP TABLE IF EXISTS "Depot";

DROP TABLE IF EXISTS "Administration";

DROP TABLE IF EXISTS "Product";

DROP TABLE IF EXISTS "Category";

DROP TABLE IF EXISTS "Photo";

DROP TABLE IF EXISTS "Producer";