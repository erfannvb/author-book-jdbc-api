DROP TABLE IF EXISTS "tbl_author";
DROP TABLE IF EXISTS "tbl_book";

CREATE TABLE "tbl_author"
(
    "id"   serial PRIMARY KEY NOT NULL,
    "name" varchar(100),
    "age"  int
);

CREATE TABLE "tbl_book"
(
    "id"        serial PRIMARY KEY NOT NULL,
    "isbn"      varchar(100),
    "title"     varchar(150),
    "author_id" int,
    CONSTRAINT "fk_author" FOREIGN KEY (author_id) REFERENCES tbl_author (id)
);
