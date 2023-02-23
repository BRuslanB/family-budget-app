INSERT INTO category_expense (name, description)
VALUES ('Обязательные', 'обязательные платежи: кредиты, комм.услуги, питание и т.д.'),
       ('Произвольные', 'разовые платежи'),
       ('Непредвиденные', 'непредвиденные расходы');

INSERT INTO type_expense (category_expense_id, name, description)
VALUES (1, 'Ипотека', 'ежемесяно'),
       (1, 'Потребительский кредит', 'ежемесяно'),
       (1, 'Коммунальные услуги', 'ежемесяно'),
       (1, 'Продукты и хоз.товары', 'ежедневно'),
       (2, 'Оплата за кружки', 'анг.язык, баскетбол, рисование, гимнастика'),
       (2, 'Сбор средств на подарок', 'в садике, в школе, на работе'),
       (3, 'Ремонт', 'авто-запчасти, строй. и расх.материалы, услуги');

INSERT INTO type_income (name, description)
VALUES ('Зарпалата и бонусы', 'основная работа'),
       ('Сдача аренда квартиры', 'средства от арендаторов'),
       ('Фриланс', 'дополнительная работа');

INSERT INTO type_receipt (name, description)
VALUES ('Приход', 'поступление средств'),
       ('Расход', 'расход средств');

INSERT INTO budget (type_income_id)
VALUES (1), (2);

INSERT INTO purchase (type_expense_id)
VALUES (1), (3), (4), (4), (6), (7);

INSERT INTO checks (type_receipt_id, value, date, note)
VALUES (1, 500000, '2023-01-31', 'зарплата'),
       (1, 200000, '2023-01-03', 'аренда квартиры'),
       (2, 250000, '2023-01-06', 'ипотека'),
       (2, 25000, '2023-01-25', 'ком.услуги'),
       (2, 50000, '2023-01-01', 'продукты'),
       (2, 50000, '2023-01-10', 'хоз.товары'),
       (2, 25000, '2023-01-01', 'дет.садик'),
       (2, 10000, '2023-01-15', 'замена смесителя');

INSERT INTO budget_checks (budget_id, checks_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO purchase_checks (purchase_id, checks_id)
VALUES (1, 3),
       (2, 4),
       (3, 5),
       (4, 6),
       (5, 7),
       (6, 8);

INSERT INTO role (name, role)
VALUES ('ADMINISTRATOR', 'ROLE_ADMIN'),
       ('USER', 'ROLE_USER');

INSERT INTO users (email, firstname, lastname, birth_day, password)
VALUES ('admin@gmail.com', 'Иванов', 'Иван', '1986-01-01', '$2a$10$yB0LGDa9YDED1H2iOhpqge5vYo4FRKC32WTt'),
       ('abramov@gmail.com', 'Абрамов', 'Никита', '2000-02-24', '$2a$10$yB0LGDa9YDED1H2iOhpqge5vYo4FRKC32WTt'),
       ('omarov@gmail.com', 'Омаров', 'Ержан', '1984-03-22', '$2a$10$yB0LGDa9YDED1H2iOhpqge5vYo4FRKC32WTt'),
       ('achmetov@gmail.com', 'Ахметов', 'Берик', '1977-07-07', '$2a$10$yB0LGDa9YDED1H2iOhpqge5vYo4FRKC32WTt');

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2);