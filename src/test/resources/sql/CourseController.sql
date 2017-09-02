-- update
INSERT INTO T_COURSE(id, title, level_fk, status_fk) VALUES(100, 'test', 1, 1);

-- status
INSERT INTO T_COURSE(id, title, level_fk, status_fk) VALUES(200, 'draft', 1, 1);
INSERT INTO T_COURSE(id, title, level_fk, status_fk, published_datetime, updated_datetime) VALUES(201, 'published -> archived', 1, 2, '2017-02-01 10:00:00', '2017-02-01 10:00:00');
INSERT INTO T_COURSE(id, title, level_fk, status_fk, published_datetime, updated_datetime) VALUES(202, 'draft -> published (V2)', 1, 1, '2017-02-01 10:00:00', '2017-02-01 10:00:00');
INSERT INTO T_COURSE(id, title, level_fk, status_fk, published_datetime, updated_datetime) VALUES(203, 'published -> published', 1, 2, '2017-02-01 10:00:00', '2017-02-01 10:00:00');

-- findById
INSERT INTO T_COURSE(id, level_fk, status_fk, language, title, summary, description, published_datetime) VALUES(300, 3, 2, 'en', 'Title of...', 'Summary of...', 'Description of...', '2017-01-02 03:30:00');
INSERT INTO T_LEG(id, course_fk, rank, title) VALUES(310, 300, 1, 'Introduction');
INSERT INTO T_LEG(id, course_fk, rank, title) VALUES(302, 300, 2, 'Querying the database');
INSERT INTO T_LEG(id, course_fk, rank, title) VALUES(303, 300, 3, 'Conclusion');

INSERT INTO T_VIDEO(id, type_fk, video_id) VALUES(3101, 1, '3101');
INSERT INTO T_VIDEO(id, type_fk, video_id) VALUES(3102, 1, '3102');
INSERT INTO T_VIDEO(id, type_fk, video_id) VALUES(3021, 1, '3021');
INSERT INTO T_VIDEO(id, type_fk, video_id) VALUES(3031, 1, '3031');

INSERT INTO T_SEGMENT(id, course_fk, leg_fk, type_fk, video_fk, rank, title, summary) VALUES(3101, 300, 310, 1, 3101, 1, 'Welcome', 'Greeting from author');
INSERT INTO T_SEGMENT(id, course_fk, leg_fk, type_fk, video_fk, rank, title) VALUES(3102, 300, 310, 1, 3102, 2, 'What is a database?');
INSERT INTO T_SEGMENT(id, course_fk, leg_fk, type_fk, rank, title) VALUES(3103, 300, 310, 2, 3, 'Quiz #1');

INSERT INTO T_SEGMENT(id, course_fk, leg_fk, type_fk, video_fk, rank, title) VALUES(3029, 300, 302, 1, 3021, 1, 'Using SELECT');
INSERT INTO T_SEGMENT(id, course_fk, leg_fk, type_fk, rank, title) VALUES(3022, 300, 302, 2, 2, 'Quiz #2');

INSERT INTO T_SEGMENT(id, course_fk, leg_fk, type_fk, video_fk, rank, title) VALUES(3031, 300, 303, 1, 3031, 1, 'Project');

-- addLeg/updateLeg
INSERT INTO T_COURSE(id, level_fk, status_fk, language, title, summary, description, published_datetime) VALUES(400, 3, 2, 'en', 'Title of...', 'Summary of...', 'Description of...', '2017-01-02 03:30:00');
INSERT INTO T_LEG(id, course_fk, rank, title) VALUES(401, 400, 1, 'leg #1');
