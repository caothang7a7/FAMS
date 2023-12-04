INSERT INTO tbl_user_permission (permission_id, role, syllabus, training_program, class, learning_material, user_management)
VALUES (1, 'SUPER_ADMIN', 'FULL_ACCESS', 'FULL_ACCESS','FULL_ACCESS','FULL_ACCESS','FULL_ACCESS' ),
       (2, 'CLASS_ADMIN', 'CREATE', 'CREATE','CREATE','CREATE','CREATE' ),
       (3, 'TRAINER', 'VIEW','VIEW','VIEW','CREATE','VIEW' );

INSERT INTO tbl_learning_objective(code, name, type, description)
VALUES ('A01', 'Demonstrate a positive', 'Attitude', 'The learner will demonstrate a positive and enthusiastic attitude towards learning Java.'),
       ('A02', 'Demonstrate responsibility', 'Attitude', 'The learner will demonstrate responsibility and complete assigned tasks.'),
       ('S01', 'Write basic PythonCode code', 'Skill', 'The learner will be able to write basic Java code, including control statements, loops, and methods.'),
       ('K01', 'Basic concepts of C# programming.', 'Knowledge', 'The learner will understand the basic concepts of Java programming, including variables, data types, operators, and expressions.'),
       ('H01', 'Practice regularly', 'Habit', 'The learner will practice regularly to improve Java programming skills.'),
       ('S04', 'Use exception handling in Java.', 'Skill', 'The learner will be able to use exception handling in Java.'),
       ('K04', 'Java collections', 'Knowledge', 'The learner will understand Java collections, including lists, sets, and maps.'),
       ('H03', 'Contribute to open source projects.', 'Habit', 'The learner will contribute to open source projects.'),
       ('K05', 'Java database access', 'Knowledge', 'The learner will understand Java database access, including JDBC and ORM.'),
       ('S05', 'Use threads and concurrency', 'Skill', 'The learner will be able to use threads and concurrency in Java.'),
       ('S012', 'Use threads and concurrency', 'Skill', 'The learner will be able to use threads and concurrency in Java.'),
       ('S013', 'Use threads and concurrency', 'Skill', 'The learner will be able to use threads and concurrency in Java.'),
       ('S08', 'Use threads and concurrency', 'Skill', 'The learner will be able to use threads and concurrency in Java.'),
       ('S07', 'Use threads and concurrency', 'Skill', 'The learner will be able to use threads and concurrency in Java.'),
('S011', 'Use threads and concurrency', 'Skill', 'The learner will be able to use threads and concurrency in Java.');


INSERT INTO tbl_user (modified_version, name, email, phone, dob, gender,permission_id, status, created_by, created_date, modified_by, modified_date,password)
VALUES  ( 0,'Trương Xuân Hoa', 'xh.demo@gmail.com', '0125634701', '2002-12-15', 'FEMALE', 1, True, 'admin' ,'2023-9-29', NULL,NULL,'123456'),
        ( 0,'Quách Gia', 'qg.demo@gmail.com', '0125634702', '2002-12-16', 'MALE', 2, True, 'admin' ,'2023-9-29', NULL,NULL,'123456'),
        ( 0,'Chu Thái', 'ct.demo@gmail.com', '0365892147', '2002-12-17', 'MALE', 3, True, 'admin' ,'2023-9-29', NULL,NULL,'123456'),
        ( 0,'John Doe', 'john.doe@example.com', '0254369803', '1980-01-01', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Jane Doe', 'jane.doe@example.com', '0254369804', '1985-02-02', 'FEMALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Peter Smith', 'peter.smith@example.com', '025436905', '1990-03-03', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Mary Johnson', 'mary.johnson@example.com', '0254369806', '1995-04-04', 'FEMALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'David Jones', 'david.jones@example.com', '0254369807', '2000-05-05', 'MALE', 2, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Susan Brown', 'susan.brown@example.com', '0254369808', '2005-06-06', 'FEMALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Michael Williams', 'michael.williams@example.com', '0254369809', '2010-07-07', 'MALE', 2, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Thomas Anderson', 'thomas.anderson@example.com', '0912345610', '1980-01-01', 'MALE', 2, true, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Neo', 'neo@example.com', '0923456711', '1985-02-02', 'FEMALE', 3, true, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Morpheus', 'morpheus@example.com', '0934567812', '1990-03-03', 'MALE', 2, true, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Trinity', 'trinity@example.com', '0945678913', '1995-04-04', 'FEMALE', 3, true, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Cypher', 'cypher@example.com', '0956789014', '2000-05-05', 'MALE', 2, true, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Agent Smith', 'agent.smith@example.com', '0967890115', '2005-06-06', 'FEMALE', 3, true, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Oracle', 'oracle@example.com', '0978901216', '2010-07-07', 'MALE', 3, true, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Merovingian', 'merovingian@example.com', '0989012317', '2015-08-08', 'FEMALE', 2, true, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Nicodemus', 'nicodemus@example.com', '0999012318', '2020-09-09', 'MALE', 3, true, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Trinity 2', 'trinity2@example.com', '0901234519', '2025-10-10', 'FEMALE', 2, true, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Ari Gold', 'ari.gold@example.com', '0254369820', '1970-01-01', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Entourage', 'entourage@example.com', '0254369821', '1975-02-02', 'FEMALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Vincent Chase', 'vincent.chase@example.com', '0254369822', '1980-03-03', 'MALE', 2, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Eric Murphy', 'eric.murphy@example.com', '0254369823', '1985-04-04', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Turtle', 'turtle@example.com', '0254369824', '1990-05-05', 'MALE', 2, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Johnny Drama', 'johnny.drama@example.com', '0254369825', '2005-06-06', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Mrs. Ari Gold', 'mrs.ari.gold@example.com', '0254369826', '2010-07-07', 'FEMALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Lloyd Lee', 'lloyd.lee@example.com', '0254369827', '2015-08-08', 'MALE', 2, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Scott Lavin', 'scott.lavin@example.com', '02543698728', '2020-09-09', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Billy Walsh', 'billy.walsh@example.com', '0254369829', '2025-10-10', 'MALE', 2, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Don Draper', 'don.draper@example.com', '0254369830', '1960-01-01', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Peggy Olson', 'peggy.olson@example.com', '0254369831', '1965-02-02', 'FEMALE', 2, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Joan Holloway', 'joan.holloway@example.com', '0254369832', '1970-03-03', 'FEMALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Roger Sterling', 'roger.sterling@example.com', '0254369833', '1975-04-04', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Bert Cooper', 'bert.cooper@example.com', '0254369834', '1980-05-05', 'MALE', 2, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Pete Campbell', 'pete.campbell@example.com', '0254369835', '2005-06-06', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Midge Daniels', 'midge.daniels@example.com', '0254369436', '2010-07-07', 'FEMALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Harry Crane', 'harry.crane@example.com', '0254369837', '2015-08-08', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Lane Pryce', 'lane.pryce@example.com', '0254369838', '2020-09-09', 'MALE', 2, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Sal Romano', 'sal.romano@example.com', '0254369839', '2025-10-10', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Walter White', 'walter.white@example.com', '0254369840', '1950-01-01', 'MALE', 3, True, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Skyler White', 'skyler.white@example.com', '0254369841', '1955-02-02', 'FEMALE', 2, False, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Jesse Pinkman', 'jesse.pinkman@example.com', '0254369842', '1960-03-03', 'MALE', 3, False, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Hank Schrader', 'hank.schrader@example.com', '0254369843', '1965-04-04', 'MALE', 3, False, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Marie Schrader', 'marie.schrader@example.com', '0254369844', '1970-05-05', 'FEMALE', 2, False, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Gus Fring', 'gus.fring@example.com', '0254369855', '1975-06-06', 'MALE', 3, False, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Mike Ehrmantraut', 'mike.ehrmantraut@example.com', '0254369854', '1980-07-07', 'MALE', 2, False, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Tuco Salamanca', 'tuco.salamanca@example.com', '0254369865', '1985-08-08', 'MALE', 2, False, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Todd Alquist', 'todd.alquist@example.com', '025439834', '1990-09-09', 'MALE', 3, False, 'admin', '2023-09-30', NULL, NULL,'123456'),
        ( 0,'Jane Margolis', 'jane.margolis@example.com', '0254368345', '1995-10-10', 'FEMALE', 2, False, 'admin', '2023-09-30', NULL, NULL,'123456');

INSERT INTO tbl_training_program(duration,training_program_code, name, user_id, start_time , status, created_by, created_date, modified_by, modified_date,learning_objective_code)
VALUES (1,'JLB','Java Language Basic', 2 , '2023-9-29','ACTIVE','Quách Gia','2023-9-29', NULL,NULL,'A01'),
       (2,'PLB','Python Language Basic', 8 , '2023-9-29','ACTIVE','David Jones','2023-9-29', NULL,NULL,'A01'),
       (3,'CLB','C# Language Basic', 10 , '2023-9-29', 'ACTIVE','Michael Williams','2023-9-29', NULL,NULL,'A02'),
       (4,'JSB','JavaScrip Basic', 8 , '2023-9-29','ACTIVE','Michael Williams','2023-9-29', NULL,NULL,'A02'),
       (5,'CLP','C# language Programing', 18 , '2023-9-29','ACTIVE','Morpheus','2023-9-29', NULL,NULL,'S01'),
       (6,'FDN','Fullstack .NET Develop', 15 , '2023-9-29', 'ACTIVE','Michael Williams','2023-9-29', NULL,NULL,'K01'),
       (7,'FJW','Fullstack JavaWeb Develop', 8 , '2023-9-29','ACTIVE','Cypher','2023-9-29', NULL,NULL,'H03');

INSERT INTO tbl_syllabus(topic_code, topic_name, technical_group, version, training_audience, topic_outline, training_material, training_principal, priority, public_status, created_by, created_date, modified_by, modified_date,user_id, quiz, assignment, final, final_theory, final_practice, gpa, level)
VALUES ('A01', 'Java Language Basic','Trainer PC need to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0',10, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','Quách Gia','2023-9-28', 'NVQ','2023-11-16',2, 15, 15, 30 ,20 ,20 , 70, 'BEGINNER'),
       ('A02', 'C# Language Basic','Trainer PC need to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 10, 'outline', 'learning material', '📚 Engaging presentation:
My aim would be to make the training session interactive and engaging. I would rely on visual aids like charts, videos, and infographics to convey information effectively. Throughout the presentation, I would encourage questions and discussions to ensure everyone feels involved.

🤝 Group work and discussions:
To encourage active participation, I would divide the staff into small groups for discussions and reflection. This would allow them to share their experiences and exchange ideas. I''d provide guiding questions and facilitate these group discussions to promote meaningful dialogue.

🎉 Recognition and celebration:
Throughout the session, I would recognize and acknowledge the achievements and contributions of the staff. I might use 🏆 emojis or small tokens of appreciation to show gratitude and motivate everyone to strive for excellence.','priority','INACTIVE','David Jones','2023-9-29', NULL,NULL,8, 10, 10, 40 ,10 ,30 , 50, 'BEGINNER'),
       ('S01', 'Python Language Basic','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 12, 'outline', 'learning material', '🔍 Closing reflection:
To wrap up the training, I would allocate time for individuals to reflect on what they have learned and how they plan to implement it in their daily practices. Their key takeaways and action plans would be shared in a collaborative document or through an online platform.

🙌 Closing remarks:
In my closing remarks, I would express my gratitude for their active participation and reaffirm my commitment to supporting their professional growth. I would encourage ongoing communication and invite feedback to continuously improve future training sessions.
','priority','DRAFT','David Jones','2023-9-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ADVANCED'),
       ('S02', 'C# language Programing','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 9, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Heres an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, Id start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','INACTIVE','David Jones','2023-9-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 80, 'ALL_LEVEL'),
       ('K01', 'DevOps Foundation','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 12, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-9-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 50, 'ADVANCED'),
       ('H01', 'Azure DevOps Foundation','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-9-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ALL_LEVEL'),
       ('H03', 'C# DevOps Foundation','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-10-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ALL_LEVEL'),
       ('H02', 'Java DevOps Foundation','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-9-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'BEGINNER'),
       ('H04', 'C++ DevOps Foundation','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-9-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ALL_LEVEL'),
       ('H05', 'Java Advanced Language','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-9-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ADVANCED'),
       ('H06', 'Testing Subject','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-10-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ALL_LEVEL'),
       ('S03', 'Testing By Selenium','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-10-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40,'BEGINNER'),
       ('S04', 'NUnit Testing','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-10-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ALL_LEVEL'),
       ('S05', 'Testify by Go','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-3-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'BEGINNER'),
       ('S06', '.NET MSTest','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-3-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40,'ALL_LEVEL'),
       ('S07', 'Cypress automation','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-3-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ADVANCED'),
       ('S08', 'PHP Unit','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-3-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ALL_LEVEL'),
       ('S09', 'Advanced PHP Unit 2','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-5-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'BEGINNER'),
       ('S10', 'Testing By Selenium 2','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-5-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ALL_LEVEL'),
       ('S011', 'Testing By Selenium 3','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-5-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'BEGINNER'),
       ('S012', 'Testing By Selenium 4','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-5-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ALL_LEVEL'),
       ('S013', 'Testing By Selenium 5','Trainer PC nêd to have following software: /nMicrosoftSQL/n Microsoft Visual Studio Code/n JDK 11 ','1.0', 11, 'outline', 'learning material', '🤓 Welcome to our school! As the training principal, my 🎯 top priority is to create a positive and inclusive learning environment for all students and staff. Here''s an example of how I would approach a training session:

🗓️ Before the session:
I would carefully plan the training, ensuring it aligns with the school''s goals and addresses the specific needs of our staff. I''d send out invitations along with a 😊 friendly reminder to build excitement.

📣 Opening the session:
To set the tone, I''d start with an energetic welcome, encouraging everyone to participate actively. Maybe I would use a 🌟 metaphor of a journey to highlight our collective growth and remind everyone of the importance of continuous learning.

🌐 Icebreaker activities:
To foster a sense of camaraderie and break the ice, I might begin with a fun icebreaker activity that promotes teamwork and collaboration. This could involve a game like "Two Truths and a Lie" or a collaborative puzzle-solving exercise.','priority','ACTIVE','David Jones','2023-5-29', NULL,NULL,8, 10, 20, 30 ,20 ,20 , 40, 'ADVANCED');


INSERT INTO tbl_cLass(training_program_code, class_name, class_code, duration, status, location, fsu, start_date, end_date, created_by, created_date, modified_by, modified_date)
VALUES ( 'JLB','Java Basic_01','HCM23_JLB01', 31, 'OPENING', 'Ho Chi Minh',1, '2023-10-1','2023-11-1','Quách Gia','2023-9-29', NULL, NULL),
       ( 'JLB','Java Basic_02','CT23_JLB01', 31, 'OPENING', 'Can Tho', 4, '2023-10-1','2023-11-1','Quách Gia','2023-9-29', NULL, NULL),
       ( 'JLB','Java Basic_03','HN23_JLB01', 31, 'OPENING', 'Ha Noi', 1, '2023-10-1','2023-11-1','Quách Gia','2023-9-29', NULL, NULL),
       ( 'PLB','Python Language Basic_01','HN23_PLB01', 31, 'COMPLETED', 'Ha Noi', 1, '2023-10-1','2023-11-1','David Jones','2023-9-29', NULL, NULL),
       ( 'PLB','Python Language Basic_02','DN23_PLB01', 31, 'COMPLETED', 'Da Nang', 3, '2023-10-1','2023-11-1','David Jones','2023-9-29', NULL, NULL),
       ( 'JSB','JavaScrip Basic 01','HCM23_JSB01', 31, 'COMPLETED', 'Ho Chi Minh', 1, '2023-10-1','2023-11-1','Michael Williams','2023-9-29', NULL, NULL),
       ( 'JSB','JavaScrip Basic 02','HCM23_JSB02', 31, 'SCHEDULED', 'Ho Chi Minh', 1, '2023-10-1','2023-11-1','Michael Williams','2023-9-29', NULL, NULL),
       ( 'CLP','C# Language Basic 1','HN23_CLP01', 31, 'SCHEDULED', 'Ha Noi', 2, '2023-10-1','2023-11-1','Morpheus','2023-9-29', NULL, NULL),
       ( 'CLP','C# Language Basic2','HN23_CLP02', 31, 'SCHEDULED', 'Ha Noi', 2, '2023-10-1','2023-11-1','Morpheus','2023-9-29', NULL, NULL),
       ( 'FDN','Fullstack .NET Develop','HCM23_FDN01', 31, 'PLANNING', 'Ho Chi Minh', 1, '2023-10-1','2023-11-1','Michael Williams','2023-9-29', NULL, NULL),
       ( 'FJW','Fullstack JavaWeb Develop_01','HCM23_FJW01', 31, 'PLANNING', 'Ho Chi Minh', 1, '2023-10-1','2023-11-1','Cypher','2023-9-29', NULL, NULL),
       ( 'FJW','Fullstack JavaWeb Develop_02','HCM23_FJW02', 31, 'PLANNING', 'Ho Chi Minh', 1, '2023-10-1','2023-11-1','Cypher','2023-9-29', NULL, NULL);

INSERT INTO tbl_training_unit(unit_code, unit_name, day_number, topic_code)
VALUES ('A01', 'Java basic', 1, 'A01'),
       ('A02', 'Java basic 2', 2, 'A01'),
       ('A03', 'C# Language Basic 1', 3, 'A02'),
       ('A04', 'C# Language Basic 2', 4, 'A02'),
       ('A05', 'Python Language Basic 1', 5, 'S01'),
       ('A06', 'Python Language Basic 2', 6, 'S01'),
       ('A07', 'Python Language Basic 3', 7, 'S01'),
       ('A08', 'Python Language Basic 4', 8, 'S01'),
       ('A09', 'C# language Programing', 9, 'S02'),
       ('A010', 'C# language Programing', 9, 'S02'),
       ('A011', 'DevOps Foundation', 9, 'K01'),
       ('A012', 'DevOps Foundation', 9, 'K01'),
       ('A013', 'Azure DevOps Foundation', 9, 'H01'),
       ('A014', 'Azure DevOps Foundation', 9, 'H01'),
       ('A015', 'Java Language Basic', 9, 'A01'),
       ('A016', 'Testing By Selenium 4', 9, 'S012'),
       ('A017', 'Testing By Selenium 3', 3, 'S011'),
       ('A018', 'PHP Unit', 3, 'S08'),
       ('A019', 'Cypress automation', 3, 'S07'),
        ('A020', 'Testing By Selenium 5', 9, 'S013');

INSERT INTO tbl_training_content (content, learning_objective_code,type, duration, note,training_content_id,unit_code,trainingformat,deliverytype)
VALUES ('Java Language Basic 1','A01','Assignment',7,'This training content will introduce learners to advanced concepts of Java programming.',1,'A01','ONLINE','Assignment_Lab'),
       ('Java Language Basic 2','A01','Quiz',10,'This training content will introduce learners to advanced concepts of Java programming.',2,'A01','ONLINE','Assignment_Lab'),
       ('C# Language Basic 1','A02','Assignment',14,'This training content will introduce learners to advanced concepts of Java programming.',3,'A02','ONLINE','Assignment_Lab'),
       ('C# Language Basic 2','A02','Assignment',7,'This training content will introduce learners to advanced concepts of Java programming.',4,'A02','ONLINE','Concept_Lecture'),
       ('Python Language Basic 1','S01','Guides',7,'This training content will introduce learners to advanced concepts of Java programming.',5,'A05','ONLINE','Concept_Lecture'),
       ('Python Language Basic 2','S01','Assignment',10,'This training content will introduce learners to advanced concepts of Java programming.',6,'A05','ONLINE','Assignment_Lab'),
       ('Python Language Basic 3','S01','Quiz',14,'This training content will introduce learners to advanced concepts of Java programming.',7,'A06','ONLINE','Assignment_Lab'),
       ('C# language Programing 1','K01','Assignment',10,'This training content will introduce learners to advanced concepts of Java programming.',8,'A05','ONLINE','Assignment_Lab'),
       ('Azure DevOps Foundation','S04','Guides',10,'This training content will introduce learners to advanced concepts of Java programming.',10,'A07','ONLINE','Assignment_Lab'),
       ('Azure DevOps Foundation','H03','Guides',10,'This training content will introduce learners to advanced concepts of Java programming.',11,'A08','ONLINE','Assignment_Lab'),
       ('Azure DevOps Foundation','K05','Guides',10,'This training content will introduce learners to advanced concepts of Java programming.',12,'A09','ONLINE','Assignment_Lab'),
       ('Azure DevOps Foundation','S05','Guides',10,'This training content will introduce learners to advanced concepts of Java programming.',13,'A012','ONLINE','Assignment_Lab'),
       ('Testing By Selenium 4','S012','Guides',10,'This training content will introduce learners to advanced concepts of Java programming.',15,'A016','ONLINE','Assignment_Lab'),
       ('Testing By Selenium 3','S011','Guides',10,'This training content will introduce learners to advanced concepts of Java programming.',16,'A016','ONLINE','Assignment_Lab'),
       ('PHP Unit','S08','Guides',10,'This training content will introduce learners to advanced concepts of Java programming.',17,'A019','ONLINE','Assignment_Lab'),
       ('Cypress automation','S07','Guides',10,'This training content will introduce learners to advanced concepts of Java programming.',18,'A020','ONLINE','Assignment_Lab'),
       ('Testing By Selenium 5','S013','Guides',10,'This training content will introduce learners to advanced concepts of Java programming.',14,'A016','ONLINE','Assignment_Lab');



INSERT INTO tbl_class_user(user_id, class_id,user_type)
VALUES (3,1,'Trainer'),
       (6,1,'Trainer'),
       (7,1,'Trainer'),
       (9,1,'Trainer'),
       (12,1,'Trainer'),
       (17,1,'Trainer'),
       (19,1,'Trainer'),
       (21,1,'Trainer'),
       (31,1,'Trainer'),
       (33,1,'Trainer'),
       (5,2,'Trainer'),
       (12,2,'Trainer'),
       (26,2,'Trainer'),
       (27,2,'Trainer'),
       (34,2,'Trainer'),
       (36,2,'Trainer'),
       (37,2,'Trainer'),
       (7,3,'Trainer'),
       (9,3,'Trainer'),
       (17,3,'Trainer'),
       (19,3,'Trainer'),
       (24,3,'Trainer'),
       (29,3,'Trainer'),
       (31,3,'Trainer'),
       (41,3,'Trainer'),
       (17,4,'Trainer'),
       (19,4,'Trainer'),
       (21,4,'Trainer'),
       (22,4,'Trainer'),
       (34,4,'Trainer'),
       (36,4,'Trainer'),
       (37,4,'Trainer'),
       (3,4,'Trainer'),
       (7,4,'Trainer'),
       (9,4,'Trainer'),
       (3,5,'Trainer'),
       (4,5,'Trainer'),
       (5,5,'Trainer'),
       (6,5,'Trainer'),
       (7,5,'Trainer'),
       (19,5,'Trainer'),
       (21,5,'Trainer'),
       (22,6,'Trainer'),
       (12,6,'Trainer'),
       (14,6,'Trainer'),
       (16,6,'Trainer'),
       (24,6,'Trainer'),
       (26,7,'Trainer'),
       (27,7,'Trainer'),
       (29,7,'Trainer'),
       (31,7,'Trainer'),
       (33,8,'Trainer'),
       (34,8,'Trainer'),
       (36,8,'Trainer'),
       (37,8,'Trainer'),
       (6,9,'Trainer'),
       (7,9,'Trainer'),
       (9,9,'Trainer'),
       (12,9,'Trainer'),
       (38,10,'Trainer'),
       (40,10,'Trainer'),
       (41,10,'Trainer'),
       (43,10,'Trainer'),
       (26,11,'Trainer'),
       (27,11,'Trainer'),
       (29,11,'Trainer'),
       (44,11,'Trainer'),
       (12,12,'Trainer'),
       (14,12,'Trainer'),
       (16,12,'Trainer'),
       (17,12,'Trainer'),
       (2,1,'classroom_controller Admin'),
       (8,2,'classroom_controller Admin'),
       (10,3,'classroom_controller Admin'),
       (11,4,'classroom_controller Admin'),
       (13,5,'classroom_controller Admin'),
       (13,6,'classroom_controller Admin'),
       (15,7,'classroom_controller Admin'),
       (18,8,'classroom_controller Admin'),
       (20,9,'classroom_controller Admin'),
       (23,10,'classroom_controller Admin'),
       (23,11,'classroom_controller Admin'),
       (25,12,'classroom_controller Admin');

INSERT INTO tbl_training_program_syllabus(topic_code, training_program_code)
VALUES ('A01','JLB'),
       ('A02','CLB'),
       ('S01','PLB'),
       ('S02','JSB'),
       ('K01','CLP'),
       ('H01','FDN'),
       ('A01','FJW');

INSERT INTO tbl_syllabus_objective(topic_code, objective_code)
VALUES ('A01','A01'),
       ('A01','A02'),
       ('A02','S01'),
       ('A02','K01'),
       ('S01','H01'),
       ('S01','S04'),
       ('S01','K04'),
       ('S02','H03'),
       ('K01','K05'),
       ('H01','S05');