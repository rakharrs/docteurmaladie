create table medicament(
    id char(7) primary key ,
    designation varchar(50)
);

create table symptome(
  id char(7) primary key ,
  designation varchar(50)
);

-- Soin medicament_symptome
create table soin(
    id char(7) primary key,
    id_medicament char(7) references medicament(id),
    id_symptome char(7) references symptome(id),
    min double precision,     -- valeur de soin
    max double precision,
    check ( min <= max )
);

create table patient(
    id char(7) primary key ,
    nom varchar(50),
    datenaissance date
);

create table maladie(
    id char(7) primary key ,
    designation varchar(50)
);

create table maladie_symptome(
    id char(7) primary key ,
    id_maladie char(7) references maladie(id),
    id_symptome char(7) references symptome(id),
    min double precision,
    max double precision
);
