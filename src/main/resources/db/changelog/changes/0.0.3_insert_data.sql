INSERT INTO expense_category (name, description)
VALUES ('Обязательные', 'обязательные платежи: кредиты, комм.услуги, питание и т.д.'),
       ('Произвольные', 'разовые платежи'),
       ('Непредвиденные', 'непредвиденные расходы');

INSERT INTO purchase (category_id, expense, description)
VALUES (1, 'Ипотека', 'ежемесяно'),
       (1, 'Потребительский кредит', 'ежемесяно'),
       (1, 'Коммунальные услуги', 'ежемесяно'),
       (1, 'Продукты и хоз.товары', 'ежедневно'),
       (1, 'Оплата за садик, доп.занятия', 'ежемесяно'),
       (2, 'Оплата за кружки', 'анг.язык, баскетбол, рисование, гимнастика'),
       (2, 'Сбор средств на подарок', 'в садике, в школе, на работе'),
       (3, 'Ремонт', 'авто-запчасти, строй. и расх.материалы, услуги');

INSERT INTO budget (income, description)
VALUES ('Зарпалата и бонусы', 'основная работа'),
       ('Сдача аренда квартиры', 'средства от арендаторов'),
       ('Фриланс', 'дополнительная работа');

INSERT INTO checks (val, date, note, budget_id, purchase_id)
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