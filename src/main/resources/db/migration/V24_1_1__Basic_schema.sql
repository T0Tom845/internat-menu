create schema if not exists internat;

CREATE TABLE internat.product (
                                  id SERIAL PRIMARY KEY,
                                  name VARCHAR(255) NOT NULL,
                                  price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE internat.dish (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(255) NOT NULL
);

CREATE TABLE internat.dish_ingredient (
                                          id SERIAL PRIMARY KEY,
                                          dish_id INTEGER REFERENCES dish(id),
                                          product_id INTEGER REFERENCES product(id),
                                          quantity DECIMAL(10, 2) NOT NULL
);

CREATE TABLE internat.menu (
                               id SERIAL PRIMARY KEY,
                               date DATE NOT NULL,
                               meal_type VARCHAR(50) NOT NULL
);

CREATE TABLE internat.menu_dish (
                                    id SERIAL PRIMARY KEY,
                                    menu_id INTEGER REFERENCES menu(id),
                                    dish_id INTEGER REFERENCES dish(id)
);
