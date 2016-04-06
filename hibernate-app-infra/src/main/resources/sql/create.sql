create table PERSON (id int not null auto_increment, first_name varchar(255), middle_name varchar(255), last_name varchar(255), gender varchar(255), birthdate date, employed char(1), gwa float, street varchar(255), house_no int, barangay varchar(255), subdivision varchar(255), city varchar(255), zip_code varchar(255), primary key (id));

create table CONTACT (id int not null auto_increment, type varchar(255), value varchar(255), person_id int not null, primary key (id), foreign key (person_id) references PERSON(id));

create table ROLE (id int not null auto_increment, role varchar(255) not null, primary key (id));

create table PERSON_ROLE (person_id int not null, role_id int not null, primary key (person_id, role_id), foreign key (person_id) references PERSON(id), foreign key (role_id) references ROLE(id));

insert into ROLE (role) values('Software Developer');
insert into ROLE (role) values('QA Engineer');
insert into ROLE (role) values('Project Manager');
insert into ROLE (role) values('Fasttrack Instructor');
