CREATE TABLE product_category (
                                  id SERIAL PRIMARY KEY,
                                  name VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         category_id INTEGER,
                         unit_price DECIMAL(10, 2) NOT NULL,
                         FOREIGN KEY (category_id) REFERENCES product_category(id) ON DELETE CASCADE
);

CREATE TABLE ingredient (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(50) NOT NULL,
                            quantity DECIMAL(10, 2) NOT NULL
);



CREATE TABLE menu (
                      id SERIAL PRIMARY KEY,
                      date_menu DATE NOT NULL
);
CREATE TABLE dish (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      menu_id INTEGER,
                      FOREIGN KEY (menu_id) REFERENCES menu(id) ON DELETE CASCADE
);
CREATE TABLE menu_dish (
                                 menu_id INTEGER,
                                 dish_id INTEGER,
                                 PRIMARY KEY (dish_id, menu_id),
                                 FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE,
                                 FOREIGN KEY (menu_id) REFERENCES menu(id) ON DELETE CASCADE
);
CREATE TABLE dish_ingredient (
                                 dish_id INTEGER,
                                 ingredient_id INTEGER,
                                 PRIMARY KEY (dish_id, ingredient_id),
                                 FOREIGN KEY (dish_id) REFERENCES dish(id) ON DELETE CASCADE,
                                 FOREIGN KEY (ingredient_id) REFERENCES ingredient(id) ON DELETE CASCADE
);