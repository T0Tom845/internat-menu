
CREATE TABLE product (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE dish (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE dish_ingredient (
                                  id SERIAL PRIMARY KEY,
                                  dish_id INTEGER REFERENCES dishes(id),
                                  product_id INTEGER REFERENCES products(id),
                                  quantity DECIMAL(10, 2) NOT NULL
);

CREATE TABLE menu (
                       id SERIAL PRIMARY KEY,
                       date DATE NOT NULL,
                       meal_type VARCHAR(50) NOT NULL
);

CREATE TABLE menu_dish (
                             id SERIAL PRIMARY KEY,
                             menu_id INTEGER REFERENCES menus(id),
                             dish_id INTEGER REFERENCES dishes(id)
);
