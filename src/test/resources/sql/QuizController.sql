INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(101031, 1, 'U7sEUIMH0yI', 37);

INSERT INTO T_QUIZ(id, type_fk, video_fk, question, description) VALUES(10103, 2, 101031, 'Who might be a potential end user of documentation?', 'This is the description');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101031, 10103, 1, false, 'You');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101032, 10103, 2, true, 'Your coworkers');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101033, 10103, 3, true, 'Your users');
