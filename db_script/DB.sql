drop database authordatabase;
create database authordatabase;
use authordatabase;


create table author_table(
author_id bigint primary key auto_increment,
author_name varchar(50),
country varchar(20));

create table genre_table(
genre_id integer primary key auto_increment,
genre_name varchar(30));


INSERT INTO `authordatabase`.`genre_table` (`genre_name`) VALUES 
('Документалізм'),('Наукова фантастика'),('Фентезі'),('Казка'),('Психологія'),('Езотерика'),('Біографія'),
('Автобіографія'),('Утопія'),('Антиутопія'),('Любовний роман'),('Триллер'),('Жахи'),('Детектив')
,('Пригоди'),('Історія'),('Прикладна література');



create table book_table(
book_id bigint primary key auto_increment,
the_name varchar(30),
date_perform date,
price double,
author_id bigint,
genre_id integer,
constraint authorFK foreign key (author_id) references author_table(author_id),
constraint genreFK foreign key (genre_id) references genre_table(genre_id));
