-- doneShouldCreateSegmentAttendance
INSERT INTO T_COURSE(id, level_fk, status_fk, language, title, summary, description, published_datetime) VALUES(300, 3, 2, 'en', 'Title of...', 'Summary of...', 'Description of...', '2017-01-02 03:30:00');
INSERT INTO T_LESSON(id, course_fk, rank, title) VALUES(301, 300, 1, 'Introduction');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(3011, 301, 1, 1, 'Welcome1');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(3012, 301, 1, 2, 'Welcome2');

-- doneShouldNotModifyExistingSegmentAttendance
INSERT INTO T_COURSE(id, level_fk, status_fk, language, title, summary, description, published_datetime) VALUES(400, 3, 2, 'en', 'Title of...', 'Summary of...', 'Description of...', '2017-01-02 03:30:00');
INSERT INTO T_LESSON(id, course_fk, rank, title) VALUES(401, 400, 1, 'Introduction');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(4011, 401, 1, 1, 'Welcome1');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, rank, title) VALUES(4012, 401, 1, 2, 'Welcome2');

INSERT INTO T_COURSE_ATTENDANCE(id, student_fk, course_fk, current_segment_fk, attendance_datetime) VALUES(400, 1, 400, 4012, '2017-01-02 03:30:00');
INSERT INTO T_SEGMENT_ATTENDANCE(id, course_attendance_fk, segment_fk, attendance_datetime) VALUES (401, 400, 4011, '2017-01-02 03:30:00');
