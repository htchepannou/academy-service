INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(101, 1, 'zYyRDFx3e28', 52);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(102, 1, 'IgOMI4_wQ54', 56);

INSERT INTO T_COURSE(id, status_fk, level_fk, title, summary, description, language, published_datetime, updated_datetime)
  VALUES(100, 2, 1, 'How to write README', 'Learn the importance of well documented code and see how to craft meaningful READMEs.', null, 'en', now(), now());

INSERT INTO T_LESSON(id, course_fk, title, rank) VALUE(101, 100, 'Writing READMEs', 1);

INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10101, 101, 1, 101, 'Welcome', 1);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, description) VALUES(10102, 101, 1, 102, 'What Is Documentation?', 2, 'Check out the <a href="https://github.com/udacity/ud777-writing-readmes">Writing READMEs</a> README over on GitHub.');
