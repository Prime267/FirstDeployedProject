drop database authordatabase;
create database authordatabase;
use authordatabase;


create table author_table(
author_id integer primary key auto_increment,
author_name varchar(50),
country varchar(20));

create table genre_table(
genre_id integer primary key auto_increment,
genre_name varchar(30));


INSERT INTO `authordatabase`.`genre_table` (`genre_name`) VALUES 
('������������'),('������� ����������'),('������'),('�����'),('���������'),('���������'),('���������'),
('������������'),('�����'),('���������'),('�������� �����'),('�������'),('����'),('��������')
,('�������'),('������'),('��������� ���������');



create table book_table(
book_id INTEGER primary key auto_increment,
the_name varchar(30),
year_perform varchar(10),
price double,
count int,
author_id integer,
genre_id integer,
constraint authorFK foreign key (author_id) references author_table(author_id),
constraint genreFK foreign key (genre_id) references genre_table(genre_id));

create table sold_books_table(
book_id integer primary key auto_increment,
the_name varchar(30),
year_perform varchar(10),
price double,
count int,
sale_date varchar(30),
author_id integer,
genre_id integer,
constraint authorFK foreign key (author_id) references author_table(author_id),
constraint genreFK foreign key (genre_id) references genre_table(genre_id));





set SQL_SAFE_UPDATES = 0;
