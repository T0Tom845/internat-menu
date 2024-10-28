Alter Table dish_ingredient
add column id serial;

ALTER TABLE dish_ingredient
drop constraint dish_ingredient_pkey;

ALTER TABLE dish_ingredient
ADD CONSTRAINT dish_ingredient_pkey PRIMARY KEY (id);
