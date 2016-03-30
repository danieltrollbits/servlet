create table PERSON (id int not null auto_increment, first_name varchar(255), middle_name varchar(255), last_name varchar(255), gender varchar(255), birthdate date, employed tinyint, gwa float, primary key (id));

create table ADDRESS (person_id int not null auto_increment, street varchar(255), house_no int, barangay varchar(255), subdivision varchar(255), city varchar(255), zip_code varchar(255), primary key (person_id), foreign key(person_id) references PERSON(id));

create table CONTACT (id int not null auto_increment, type varchar(255), value varchar(255), person_id int not null, primary key (id), foreign key (person_id) references PERSON(id));

create table ROLE (id int not null auto_increment, role varchar(255) not null, primary key (id));

create table PERSON_ROLE (person_id int not null, role_id int not null, primary key (person_id, role_id), foreign key (person_id) references PERSON(id), foreign key (role_id) references ROLE(id));
