# Санкт-Петербургский Национальный Исследовательский Университет ИТМО
## Факультет Программной Инженерии и Компьютерной Техники

---

**Вариант №473397**  
**Лабораторная работа №1**  
**По дисциплине: Базы Данных**

---

**Выполнил студент:**  
Эллити Мохамед Эмад Ахмед Авад  

**Группа:** P3131  

**Преподаватель:**  
Коновалов Арсений Антонович  
Николаев Владимир Вячеславович  

**Санкт-Петербург 2026 г.**

---

## 1. Текст задания

Для выполнения лабораторной работы №1 необходимо:

1. На основе предложенной предметной области (текста) составить её описание. Из полученного описания выделить сущности, их атрибуты и связи.
2. Составить инфологическую модель.
3. Составить даталогическую модель. При описании типов данных для атрибутов должны использоваться типы из СУБД PostgreSQL.
4. Реализовать даталогическую модель в PostgreSQL. При описании и реализации даталогической модели должны учитываться ограничения целостности, которые характерны для полученной предметной области.
5. Заполнить созданные таблицы тестовыми данными.

---

## 2. Описание предметной области

> Олвин слегка поклонился в знак признательности, огромные двери снова раздвинулись перед ним, и он медленно вышел из зала. Джизирак последовал за ним и, когда створки дверей снова сомкнулись, повернулся к своему воспитаннику.

Человек находится в зале. В зале есть огромные двери, которые могут быть открыты или закрыты. Джизирак — наставник Олвина. Олвин — его воспитанник.

---

## 3. Список сущностей и их классификация

**Стержневые:**

- **Персонаж (Person)** — уникальный идентификатор (`person_id`), имя (`name`).
- **Зал (Hall)** — идентификатор (`hall_id`), название (`name`), тип (`type`), кампус (`campus_id`).
- **Кампус (Campus)** — идентификатор (`campus_id`), название (`name`).
- **Предмет (Subject)** — идентификатор (`subject_id`), название (`name`).

**Характеристические:**

- **Дверь (Door)** — идентификатор (`door_id`), размер (`size`, например «огромные»), состояние (`state`: «open» или «closed»), зал (`hall_id`).

**Ассоциативные:**

- **Наставничество (Mentorship)** — идентификатор (`mentorship_id`), наставник (`mentor_id`), воспитанник (`ward_id`), дата начала (`start_date`).
- **Преподавание (Teaching)** — идентификатор (`teaching_id`), преподаватель (`person_id`), предмет (`subject_id`).
- **Присутствие в зале (Person_Hall)** — персонаж (`person_id`), зал (`hall_id`).

**Связи:**

- Зал → Дверь (один зал может иметь несколько дверей).
- Кампус → Зал (один кампус содержит несколько залов).
- Человек → Наставничество → Человек (само-ссылающаяся связь: наставник/воспитанник).
- Человек ↔ Зал (присутствие персонажа в зале).
- Человек → Предмет (через таблицу преподавания).

---

## 4. Инфологическая модель

![Инфологическая модель](./report_Latex/Infological%20Model.png)

---

## 5. Даталогическая модель

![Даталогическая модель](./report_Latex/Datalogical:Logical%20Model.png)

---

## 6. Реализация даталогической модели на SQL

```sql
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
    (1, 'Большой актовый зал',          'актовый',       1),
    (2, 'Зал заседаний учёного совета', 'конференц-зал', 1),
    (3, 'Аудитория 325',                'лекционный',    2),
    (4, 'Аудитория 101',                'лекционный',    2);

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
```

---

## 7. Вывод

При выполнении лабораторной работы я познакомился с принципом проектирования «Top – Down», научился составлять инфологическую и даталогическую модель сущностей, по которым реализовал базу данных с помощью SQL.