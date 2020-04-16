/* database creation */
DROP database IF EXISTS storage;
CREATE database storage;
USE storage;

/* begin tables creation */
DROP TABLE IF EXISTS utente;
CREATE TABLE utente (
	username varchar(30) primary key,
    userpass varchar(30) not NULL,
    nome varchar(20) not NULL,
    cognome varchar(20) not NULL,
    datanascita date not NULL,
    numcarta char(16) not NULL,
    email varchar(50) not NULL,
    isAdmin boolean not NULL default 0
);

DROP TABLE IF EXISTS abbonamento;
CREATE TABLE abbonamento (
	numeroabb int primary key AUTO_INCREMENT,
    datainizio date not NULL,
    datafine date not NULL,
    username varchar(30) not NULL,
    foreign key (username) references utente(username)
    on delete cascade
);

DROP TABLE IF EXISTS fattura;
CREATE TABLE fattura (
	numfattura int not NULL AUTO_INCREMENT,
    username varchar(30) not NULL,
    dataemissione date not NULL,
    importo decimal(5,2),
    primary key (numfattura, username),
    foreign key (username) references utente(username)
    on delete cascade
);

DROP TABLE IF EXISTS contenuti;
CREATE TABLE contenuti (
	codice varchar(10) primary key,
    titolo varchar (50) not NULL,
    regista varchar(50),
    anno year,
    genere varchar(20),
    urlimg varchar(70)
);

DROP TABLE IF EXISTS recensioni;
CREATE TABLE recensioni (
	id int not NULL AUTO_INCREMENT,
    testo varchar (500) not NULL,
    voto int not NULL,
    codice varchar(10) not NULL,
    primary key (id, codice),
    foreign key (codice) references contenuti(codice)
    on delete cascade
);
/* end tables creation */

/* begin data population */

/* utente data */
insert into utente (username, userpass, nome, cognome, datanascita, numcarta, email)
values ('alessandro1994', 'alex94', 'Alessandro', 'Carleo', '1991-10-07', '4587123569841457', 'alessandrocarleo@gmail.com');
insert into utente (username, userpass, nome, cognome, datanascita, numcarta, email)
values ('lucas1989', 'luca89', 'Luca', 'Ruggiero', '1989-02-15', '2387123369741257', 'lucaruggi89@live.it');
insert into utente (username, userpass, nome, cognome, datanascita, numcarta, email, isAdmin)
values ('giuse75', 'giu75', 'Giuseppe', 'Adinolfi', '1975-05-22', '2342127365731258', 'adinolfig1975@libero.it', 1);
insert into utente (username, userpass, nome, cognome, datanascita, numcarta, email)
values ('marcoooos', 'marco96', 'Marco', 'Senatore', '1996-11-24', '5447157265644412', 'marcosenat96@hotmail.it');
insert into utente (username, userpass, nome, cognome, datanascita, numcarta, email, isAdmin)
values ('andrefilm', 'andrea80', 'Andrea', 'Rossi', '1980-12-12', '4978223954781225', 'andreros1980@gmail.com', 1);

/* abbonamento data */
insert into abbonamento (datainizio, datafine, username)
values ('2017-10-01', '2020-05-01', 'alessandro1994');
insert into abbonamento (datainizio, datafine, username)
values ('2018-01-12', '2020-05-12', 'lucas1989');
insert into abbonamento (datainizio, datafine, username)
values ('2018-02-14', '2020-05-14', 'marcoooos');
insert into abbonamento (datainizio, datafine, username)
values ('2017-12-15', '2019-12-15', 'giuse75');
insert into abbonamento (datainizio, datafine, username)
values ('2017-08-13', '2020-05-13', 'andrefilm');

/* fattura data */
insert into fattura (username, dataemissione, importo)
values ('alessandro1994', '2017-10-01', 11.99);
insert into fattura (username, dataemissione, importo)
values ('lucas1989', '2018-01-12', 11.99);
insert into fattura (username, dataemissione, importo)
values ('marcoooos', '2018-02-14', 11.99);
insert into fattura (username, dataemissione, importo)
values ('giuse75', '2017-12-15', 11.99);
insert into fattura (username, dataemissione, importo)
values ('alessandro1994', '2017-11-01', 11.99);
insert into fattura (username, dataemissione, importo)
values ('lucas1989', '2018-02-12', 11.99);
insert into fattura (username, dataemissione, importo)
values ('marcoooos', '2018-03-14', 11.99);
insert into fattura (username, dataemissione, importo)
values ('giuse75', '2018-01-15', 11.99);
insert into fattura (username, dataemissione, importo)
values ('andrefilm', '2017-08-13', 0);

/* contenuti data */
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('F14', 'Inception', 'Cristopher Nolan', '2010', 'Thriller', 'images/catalog/inception.jpg');
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('F25', 'Interstellar', 'Cristopher Nolan', '2014', 'Fantascienza', 'images/catalog/interstellar.jpg');
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('F32', 'Jurassic World', 'Colin Trevorrow', '2015', 'Fantascienza', 'images/catalog/jurassic.jpg');
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('F54', 'Minions', 'Pierre Coffin', '2015', 'Animazione', 'images/catalog/minions.jpg');
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('F47', 'Harry Potter e la pietra filosofale', 'Chris Columbus', '2000', 'Fantasy', 'images/catalog/harry.jpg');
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('F50', 'Il Signore degli Anelli', 'Peter Jackson', '2001', 'Avventura', 'images/catalog/signore.jpg');
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('S11', 'Stranger Things', 'The Duffer Brothers', '2016', 'Fantasy', 'images/catalog/stranger.jpg');
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('S25', 'The Big Bang Theory', 'Chuck Lorre', '2007', 'Commedia', 'images/catalog/bigbang.jpg');
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('F12', 'La teoria del Tutto', 'James Marsh', '2014', 'Biografico', 'images/catalog/teoria.jpg');
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('F40', 'American Sniper', 'Clint Eastwood', '2014', 'Guerra', 'images/catalog/sniper.jpg');
insert into contenuti (codice, titolo, regista, anno, genere, urlimg)
values ('S27', 'Game of Thrones', 'Tim Van Patten', '2011', 'Fantastico', 'images/catalog/trono.jpg');

/* recensioni data */
insert into recensioni (testo, voto, codice)
values ('Film stupendo, ricco di emozionanti colpi di scena', 5, 'F14');
insert into recensioni (testo, voto, codice)
values ('Serie tv stupenda, ricca di emozionanti colpi di scena', 5, 'S11');

/* end data population */