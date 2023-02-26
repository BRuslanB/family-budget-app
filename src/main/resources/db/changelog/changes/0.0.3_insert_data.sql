INSERT INTO expense_category (name, description)
VALUES ('Обязательные', 'обязательные платежи: кредиты, комм.услуги, питание и т.д.'),
       ('Произвольные', 'разовые платежи'),
       ('Непредвиденные', 'непредвиденные расходы');

INSERT INTO type_expense (expense_category_id, name, description, is_valid)
VALUES (1, 'Ипотека', 'ежемесяно', true),
       (1, 'Потребительский кредит', 'ежемесяно', true),
       (1, 'Коммунальные услуги', 'ежемесяно', true),
       (1, 'Продукты и хоз.товары', 'ежедневно', true),
       (1, 'Оплата за садик, доп.занятия', 'ежемесяно', true),
       (2, 'Оплата за кружки', 'анг.язык, баскетбол, рисование, гимнастика', true),
       (2, 'Сбор средств на подарок', 'в садике, в школе, на работе', true),
       (3, 'Ремонт', 'авто-запчасти, строй. и расх.материалы, услуги', true);

INSERT INTO type_income (name, description, is_valid)
VALUES ('Зарпалата и бонусы', 'основная работа', true),
       ('Сдача аренда квартиры', 'средства от арендаторов', true),
       ('Фриланс', 'дополнительная работа', true);

INSERT INTO budget (type_income_id)
VALUES (1), (2);

INSERT INTO purchase (type_expense_id)
VALUES (1), (3), (4), (5), (8);

INSERT INTO checks (value, date, note, budget_id, purchase_id)
VALUES (500000, '2023-01-31', 'зарплата', 1, null),
       (200000, '2023-01-03', 'аренда квартиры', 2, null),
       (250000, '2023-01-06', 'ипотека', null, 1),
       (25000, '2023-01-25', 'ком.услуги', null, 2),
       (50000, '2023-01-01', 'продукты', null, 3),
       (50000, '2023-01-10', 'хоз.товары', null, 3),
       (25000, '2023-01-01', 'дет.садик', null, 4),
       (10000, '2023-01-15', 'замена смесителя', null, 5);

INSERT INTO role (name, role)
VALUES ('ADMINISTRATOR', 'ROLE_ADMIN'),
       ('USER', 'ROLE_USER');

INSERT INTO users (email, firstname, lastname, birth_day, password)
VALUES ('admin@gmail.com', 'Иванов', 'Иван', '1986-01-01', '$2a$10$7VB4nxLa.hxOjL5/NafMLuFPndVGkRZ7FbNfOk44ktBS8h8aqxTsS'),
       ('abramov@gmail.com', 'Абрамов', 'Никита', '2000-02-24', '$2a$10$7VB4nxLa.hxOjL5/NafMLuFPndVGkRZ7FbNfOk44ktBS8h8aqxTsS'),
       ('omarov@gmail.com', 'Омаров', 'Ержан', '1984-03-22', '$2a$10$7VB4nxLa.hxOjL5/NafMLuFPndVGkRZ7FbNfOk44ktBS8h8aqxTsS'),
       ('achmetov@gmail.com', 'Ахметов', 'Берик', '1977-07-07', '$2a$10$7VB4nxLa.hxOjL5/NafMLuFPndVGkRZ7FbNfOk44ktBS8h8aqxTsS');

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2);