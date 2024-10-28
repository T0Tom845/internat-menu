Alter Table dish
drop column menu_id;
ALTER TABLE ingredient
    ADD COLUMN product_id BIGINT,
    ADD CONSTRAINT fk_product
        FOREIGN KEY (product_id) REFERENCES product(id);