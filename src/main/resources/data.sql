
INSERT INTO Instructor(first_Name,last_Name,department) 
VALUES ('Jack','Hopkins','FAST'),
       ('Matha','Stewart','FAST'),
       ('Sekay','Tims','FAST'); 


INSERT INTO Staff(first_Name,last_Name,user_name,encrypted_Password, enabled) 
VALUES ('Oliver','Twist','olivertw','$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1),
       ('Wendy', 'Dyson','wendydy','$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1),
       ('Tom', 'Gump','tomgu','$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);


INSERT INTO Course(name,department)
VALUES ('Microbiology','FAST'),
       ('Pharmacology','FAST'),
       ('Calculus','FAST');


INSERT INTO Role(rolename)
VALUES('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO STAFF_ROLES(STAFF_ID, ROLES_ID)
VALUES(1, 1), (1, 2), (2, 1), (2, 2), (3, 1), (3, 2);


INSERT INTO Student(first_Name,last_Name,phone_Number,user_name,encrypted_Password,enabled, role_id)
VALUES ('Ben','Smith','647-647-0000','bensm','$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1, 2),
       ('Rob','Lowe','431-111-0000','roblo','$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1, 2),
       ('Paul','Crans','705-222-0000','paulcr','$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1, 2),
       ('Sarah','Lewis','905-222-0000','sarahle','$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1, 2);

INSERT INTO Review(title, review_Body,date_Reviewed,rating, is_Anonymous, COURSE_COURSE_ID, INSTRUCTOR_ID, STUDENT_ID)
VALUES ('Good','The course was awesome, and the professor was amazing!','2020-04-01',5, 0, 1, 2, 1),
       ('Good','The professor was OK!','2020-04-04',3, 0, 1, 1, 3),
       ('Bad','Please refund my money, please!!!','2020-04-10',1, 1, 1, 3, 3),
       ('Good','Please refund my money, please!!!','2020-04-10',1, 1, 2, 2, 2),
       ('Bad','Please refund my money, please!!!','2020-04-10',1, 1, 3, 1, 1);
       
       
Insert into Course_Instructors(courses_course_id, instructors_id)
values (1, 1),(1, 2), (2, 1), (2, 2), (3,1);

Insert Into College(name)
values ('Sheridan College'),
       ('Humber College'),
       ('Seneca College');
       
Insert into campus(name, college_id)
values('Davis', 1),('HMC',1),('Trafalgar',1);


Insert into Instructor_Colleges(colleges_college_id, instructors_id)
values (1, 1),(1, 2), (1, 3);


Insert into Student_Courses(students_id, courses_course_id)
values (1, 1),(1, 2), (2, 3),(2, 2), (3,1),(3,3);
