insert into ROLE (role) values('Software Developer');
insert into ROLE (role) values('QA Engineer');
insert into ROLE (role) values('Project Manager');
insert into ROLE (role) values('Fasttrack Instructor');

insert into PERSON (first_name,middle_name,last_name,gender,birthdate,employed,gwa) values('asd','asd','asd','MALE','1212-12-12',1,2.1);

insert into ADDRESS (person_id, street, house_no, barangay, subdivision, city, zip_code) values((select id from PERSON where id='1'),'qwe',123,'qwe','qwe','qwe','qwe');

insert into CONTACT (type,value,person_id) values('MOBILE','123123',(select id from PERSON where id='1'));
insert into CONTACT (type,value,person_id) values('PHONE','45454545',(select id from PERSON where id='1'));

insert into PERSON_ROLE (person_id, role_id) select PERSON.id, ROLE.id from PERSON join ROLE;
