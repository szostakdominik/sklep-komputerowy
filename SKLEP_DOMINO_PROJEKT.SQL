/*
Created: 03.02.2018
Modified: 03.02.2018
Project: Sklep komputerowy
Model: Model sklepu komputerowego
Company: PWSZ w Tarnowie
Author: Dominik Stasik, Dominik Szostak, Witold Wątroba
Version: 0.1
Database: PostgreSQL 9.5
*/

-- Create schema section -------------------------------------------------

CREATE SCHEMA "public";

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE "id_pracownika_seq" INCREMENT BY 1 START WITH 1 NO MAXVALUE NO MINVALUE CACHE 1;
CREATE SEQUENCE "id_laptopa_seq" INCREMENT BY 1 START WITH 1 NO MAXVALUE NO MINVALUE CACHE 1;
CREATE SEQUENCE "id_podzespolu_seq" INCREMENT BY 1 START WITH 1 NO MAXVALUE NO MINVALUE CACHE 1;
CREATE SEQUENCE "id_utargu_seq" INCREMENT BY 1 START WITH 1 NO MAXVALUE NO MINVALUE CACHE 1;
CREATE SEQUENCE "id_usera_seq" INCREMENT BY 1 START WITH 1 NO MAXVALUE NO MINVALUE CACHE 1;
CREATE SEQUENCE "id_zamowienia_seq" INCREMENT BY 1 START WITH 1 NO MAXVALUE NO MINVALUE CACHE 1;

-- Create tables section -------------------------------------------------

-- Table users_work

CREATE TABLE "users_work"(
 "id_pracownika" Integer DEFAULT NEXTVAL('id_pracownika_seq') NOT NULL,
 "login" Character varying(40),
 "haslo" Character varying(40)
);

-- Add keys for table users_work

ALTER TABLE "users_work" ADD CONSTRAINT "ID Pracownika" PRIMARY KEY ("id_pracownika");

-- Table laptopy

CREATE TABLE "laptopy"(
 "id_laptopa" Integer DEFAULT NEXTVAL('id_laptopa_seq') NOT NULL,
 "nazwa_firmy" Character varying(40),
 "model" Character varying(40),
 "cena" Double precision
);

-- Add keys for table laptopy

ALTER TABLE "laptopy" ADD CONSTRAINT "ID Laptopa" PRIMARY KEY ("id_laptopa");

-- Table utarg

CREATE TABLE "utarg"(
 "id_utargu" Integer DEFAULT NEXTVAL('id_utargu_seq') NOT NULL,
 "id_laptopa" Integer,
 "id_podzespolu" Integer,
 "kwota" Double precision
);

-- Create indexes for table utarg

CREATE INDEX "id_laptopa_idx" ON "utarg" ("id_laptopa");
CREATE INDEX "id_podzespolu_idx" ON "utarg" ("id_podzespolu");

-- Add keys for table utarg

ALTER TABLE "utarg" ADD CONSTRAINT "ID Utargu" PRIMARY KEY ("id_utargu");

-- Table podzespoly_pc

CREATE TABLE "podzespoly_pc"(
 "id_podzespolu" Integer DEFAULT NEXTVAL('id_podzespolu_seq') NOT NULL,
 "typ" Character varying(40),
 "nazwa_firmy" Text,
 "model" Character varying(40),
 "cena" Double precision
);

-- Add keys for table podzespoly_pc

ALTER TABLE "podzespoly_pc" ADD CONSTRAINT "ID Podzespolu" PRIMARY KEY ("id_podzespolu");

-- Table users

CREATE TABLE "users"(
 "id_usera" Integer DEFAULT NEXTVAL('id_usera_seq') NOT NULL,
 "login" Character varying(40),
 "haslo" Character varying(40),
 "imie" Character varying(40),
 "nazwisko" Character varying(40)
);

-- Add keys for table users

ALTER TABLE "users" ADD CONSTRAINT "ID Usera" PRIMARY KEY ("id_usera");

-- Table zamowienia

CREATE TABLE "zamowienia"(
 "id_zamowienia" Integer DEFAULT NEXTVAL('id_zamowienia_seq') NOT NULL,
 "id_usera" Integer,
 "id_laptopa" Integer,
 "id_podzespolu" Integer,
 "kwota" Double precision
);

-- Create indexes for table zamowienia

CREATE INDEX "id_usera_idx" ON "zamowienia" ("id_usera");
CREATE INDEX "id_podzespolu_idx2" ON "zamowienia" ("id_podzespolu");
CREATE INDEX "id_laptopa_idx2" ON "zamowienia" ("id_laptopa");

-- Add keys for table zamowienia

ALTER TABLE "zamowienia" ADD CONSTRAINT "ID Zamowienia" PRIMARY KEY ("id_zamowienia");

-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE "utarg" ADD CONSTRAINT "kosztowały" FOREIGN KEY ("id_laptopa") REFERENCES "laptopy" ("id_laptopa") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "utarg" ADD CONSTRAINT "miały cenę" FOREIGN KEY ("id_podzespolu") REFERENCES "podzespoly_pc" ("id_podzespolu") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "zamowienia" ADD CONSTRAINT "dokonują zamówień" FOREIGN KEY ("id_usera") REFERENCES "users" ("id_usera") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "zamowienia" ADD CONSTRAINT "będą zamówione" FOREIGN KEY ("id_podzespolu") REFERENCES "podzespoly_pc" ("id_podzespolu") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "zamowienia" ADD CONSTRAINT "zostały zamówione" FOREIGN KEY ("id_laptopa") REFERENCES "laptopy" ("id_laptopa") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Insert data section ------------------------------------------------- 

INSERT INTO users_work VALUES (DEFAULT, 'admin', 'root');

INSERT INTO laptopy VALUES (DEFAULT, 'ASUS', ' ZenBook Flip UX360CA', 1999.99);

INSERT INTO podzespoly_pc VALUES (DEFAULT, 'płyta główna', 'Lenovo', 'AAAC45', 299.99);

INSERT INTO utarg VALUES (DEFAULT, 1, 1, 299.99);

INSERT INTO users VALUES (DEFAULT, 'klient', 'user', 'Adam', 'Adamowski');

INSERT INTO zamowienia VALUES (DEFAULT, 1, 1, 1, 2199.99);