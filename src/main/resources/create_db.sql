CREATE DATABASE city_info_bot_schema;

USE city_info_bot_schema;

CREATE TABLE city
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    city_name VARCHAR(255) UNIQUE
);

CREATE TABLE city_fact
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fact VARCHAR(255),
    city_id BIGINT,
    CONSTRAINT city_city_fact_fk
        FOREIGN KEY (city_id) REFERENCES city (id) ON DELETE CASCADE
);

INSERT city(city_name)
VALUES
('Москва'),
('Минск');

INSERT city_fact(fact, city_id)
VALUES
    ('Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))', 1),
    ('Зимой на ВДНХ отличный каток. Must to be!', 1),
    ('По воскресеньям опасно ходить по улице даже в магазин=(', 2);


DROP TABLE city_fact;
DROP TABLE city;