BEGIN;

CREATE TABLE person (
    person_id SERIAL        PRIMARY KEY,
    name      VARCHAR(100)  NOT NULL
        CONSTRAINT person_name_not_empty CHECK (TRIM(name) <> '')
);

CREATE TABLE campus (
    campus_id SERIAL        PRIMARY KEY,
    name      VARCHAR(100)  NOT NULL
        CONSTRAINT campus_name_not_empty CHECK (TRIM(name) <> '')
);

CREATE TABLE hall (
    hall_id   SERIAL        PRIMARY KEY,
    name      VARCHAR(100)  NOT NULL
        CONSTRAINT hall_name_not_empty CHECK (TRIM(name) <> ''),
    type      VARCHAR(50),
    campus_id INTEGER       NOT NULL
        REFERENCES campus(campus_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE door (
    door_id SERIAL        PRIMARY KEY,
    size    VARCHAR(50)   NOT NULL
        CONSTRAINT door_size_not_empty CHECK (TRIM(size) <> ''),
    state   VARCHAR(10)   NOT NULL
        CONSTRAINT door_state_valid CHECK (state IN ('open', 'closed')),
    hall_id INTEGER       NOT NULL
        REFERENCES hall(hall_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE subject (
    subject_id SERIAL        PRIMARY KEY,
    name       VARCHAR(100)  NOT NULL
        CONSTRAINT subject_name_not_empty CHECK (TRIM(name) <> '')
);

CREATE TABLE mentorship (
    mentorship_id SERIAL   PRIMARY KEY,
    mentor_id     INTEGER  NOT NULL
        REFERENCES person(person_id) ON DELETE CASCADE ON UPDATE CASCADE,
    ward_id       INTEGER  NOT NULL
        REFERENCES person(person_id) ON DELETE CASCADE ON UPDATE CASCADE,
    start_date    DATE     DEFAULT CURRENT_DATE,
    CONSTRAINT mentorship_no_self_ref CHECK (mentor_id <> ward_id),
    CONSTRAINT mentorship_unique_pair UNIQUE (mentor_id, ward_id)
);

CREATE TABLE teaching (
    teaching_id SERIAL   PRIMARY KEY,
    person_id   INTEGER  NOT NULL
        REFERENCES person(person_id)   ON DELETE CASCADE ON UPDATE CASCADE,
    subject_id  INTEGER  NOT NULL
        REFERENCES subject(subject_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT teaching_unique_pair UNIQUE (person_id, subject_id)
);

CREATE TABLE person_hall (
    person_id INTEGER  NOT NULL
        REFERENCES person(person_id) ON DELETE CASCADE ON UPDATE CASCADE,
    hall_id   INTEGER  NOT NULL
        REFERENCES hall(hall_id)     ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT person_hall_pk PRIMARY KEY (person_id, hall_id)
);

CREATE INDEX idx_hall_campus       ON hall(campus_id);
CREATE INDEX idx_door_hall         ON door(hall_id);
CREATE INDEX idx_mentorship_mentor ON mentorship(mentor_id);
CREATE INDEX idx_mentorship_ward   ON mentorship(ward_id);
CREATE INDEX idx_teaching_person   ON teaching(person_id);
CREATE INDEX idx_teaching_subject  ON teaching(subject_id);
CREATE INDEX idx_person_hall_hall  ON person_hall(hall_id);

INSERT INTO person (person_id, name) VALUES
    (1, 'Коновалов Арсений Антонович'),
    (2, 'Клименков Сергей Викторович'),
    (3, 'Рыбинская Злата Владиславовна'),
    (4, 'Зайцева Ирина Сергеевна'),
    (5, 'Бобрусь Александр Владимирович');

INSERT INTO campus (campus_id, name) VALUES
    (1, 'Кронверкский проспект, 49'),
    (2, 'Ломоносова, 9');

INSERT INTO hall (hall_id, name, type, campus_id) VALUES
    (1, 'Большой актовый зал',              'актовый',       1),
    (2, 'Зал заседаний учёного совета',     'конференц-зал', 1),
    (3, 'Аудитория 325',                    'лекционный',    2),
    (4, 'Аудитория 101',                    'лекционный',    2);

INSERT INTO door (door_id, size, state, hall_id) VALUES
    (1, 'огромные', 'closed', 1),
    (2, 'огромные', 'open',   1),
    (3, 'средние',  'closed', 2),
    (4, 'малые',    'open',   3),
    (5, 'средние',  'closed', 4);

INSERT INTO subject (subject_id, name) VALUES
    (1, 'Базы данных'),
    (2, 'Математический анализ'),
    (3, 'Философия'),
    (4, 'Программная инженерия');

INSERT INTO mentorship (mentorship_id, mentor_id, ward_id, start_date) VALUES
    (1, 2, 1, '2024-09-01'),
    (2, 3, 4, '2024-09-01'),
    (3, 3, 5, '2024-02-01');

INSERT INTO teaching (teaching_id, person_id, subject_id) VALUES
    (1, 2, 1),
    (2, 2, 3),
    (3, 3, 2),
    (4, 3, 4),
    (5, 4, 3);

INSERT INTO person_hall (person_id, hall_id) VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 3),
    (5, 4);

COMMIT;