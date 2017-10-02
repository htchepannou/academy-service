-- update
INSERT INTO T_COURSE(id, title, level_fk, status_fk) VALUES(100, 'test', 1, 1);

-- status
INSERT INTO T_COURSE(id, title, level_fk, status_fk) VALUES(200, 'draft', 1, 1);
INSERT INTO T_COURSE(id, title, level_fk, status_fk, published_datetime, updated_datetime) VALUES(201, 'published -> archived', 1, 2, '2017-02-01 10:00:00', '2017-02-01 10:00:00');
INSERT INTO T_COURSE(id, title, level_fk, status_fk, published_datetime, updated_datetime) VALUES(202, 'draft -> published (V2)', 1, 1, '2017-02-01 10:00:00', '2017-02-01 10:00:00');
INSERT INTO T_COURSE(id, title, level_fk, status_fk, published_datetime, updated_datetime) VALUES(203, 'published -> published', 1, 2, '2017-02-01 10:00:00', '2017-02-01 10:00:00');

-- findById - findSegmentById
INSERT INTO T_COURSE(id, level_fk, status_fk, language, title, summary, description, published_datetime, segment_count) VALUES(300, 3, 2, 'en', 'Title of...', 'Summary of...', 'Description of...', '2017-01-02 03:30:00', 4);
INSERT INTO T_LESSON(id, course_fk, rank, title) VALUES(310, 300, 1, 'Introduction');
INSERT INTO T_LESSON(id, course_fk, rank, title) VALUES(302, 300, 2, 'Querying the database');
INSERT INTO T_LESSON(id, course_fk, rank, title) VALUES(303, 300, 3, 'Conclusion');

INSERT INTO T_VIDEO(id, type_fk, video_id) VALUES(3101, 1, '3101');
INSERT INTO T_VIDEO(id, type_fk, video_id) VALUES(3102, 1, '3102');
INSERT INTO T_VIDEO(id, type_fk, video_id) VALUES(3104, 1, '3104');
INSERT INTO T_VIDEO(id, type_fk, video_id) VALUES(3021, 1, '3021');
INSERT INTO T_VIDEO(id, type_fk, video_id) VALUES(3031, 1, '3031');

INSERT INTO T_QUIZ(id, type_fk, video_fk, question) VALUES(3104, 2, null, 'Who might be a potencial end user of documentation?');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(31041, 3104, 1, true, 'You');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(31042, 3104, 2, true, 'Your coworkers');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(310413, 3104, 3, true, 'Your users');

INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, rank, duration_second, title, summary, description) VALUES(3101, 310, 1, 3101, 1, 52, 'Welcome', 'Greeting from author', 'Presentation of the course and objectives from the author <b>Ray Sponsible</b>');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, rank, duration_second, title) VALUES(3102, 310, 1, 3102, 2, 63, 'What is a database?');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(3103, 310, 2, 3, 'SQL cheat sheet');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, quiz_fk, rank, title) VALUES(3104, 310, 3, 3104, 3104, 4, 'Quiz: Search data');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, rank, duration_second, title) VALUES(3021, 302, 1, 3101, 1, 240, 'Understanding SELECT');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, duration_second, title) VALUES(3022, 302, 2, 2, 440, 'Filter with WHERE');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, rank, duration_second, title) VALUES(3031, 303, 1, 3101, 1, 40, 'Conclusion');

INSERT INTO T_INSTRUCTOR(id, course_fk, role_fk) VALUES(301, 300, 1);
INSERT INTO T_INSTRUCTOR(id, course_fk, role_fk) VALUES(302, 300, 2);

-- addLeg/updateLeg
INSERT INTO T_COURSE(id, level_fk, status_fk, language, title, summary, description, published_datetime) VALUES(400, 3, 2, 'en', 'Title of...', 'Summary of...', 'Description of...', '2017-01-02 03:30:00');
INSERT INTO T_LESSON(id, course_fk, rank, title) VALUES(401, 400, 1, 'lesson #1');
