CREATE TABLE product
(
    id       INT            NOT NULL auto_increment,
    name     VARCHAR(255)   NOT NULL,
    cost     DECIMAL(65, 2) NOT NULL,
    brand_id INT(11)        NULL,
    PRIMARY KEY (id)
) engine = InnoDB;
GO

CREATE TABLE brand
(
    id   INT          NOT NULL auto_increment,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) engine = InnoDB;
GO

ALTER TABLE product
    ADD CONSTRAINT product_brand_id_fk
        FOREIGN KEY (brand_id)
            REFERENCES brand (id)
GO

CREATE TABLE category
(
    id   INT          NOT NULL auto_increment,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) engine = InnoDB;
GO

CREATE TABLE product_category
(
    product_id  INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (product_id, category_id)
) engine = InnoDB;
GO

ALTER TABLE product_category
    ADD CONSTRAINT product_category_product_id_fk
        FOREIGN KEY (product_id)
            REFERENCES product (id)
GO

ALTER TABLE product_category
    ADD CONSTRAINT product_category_category_id_fk
        FOREIGN KEY (category_id)
            REFERENCES category (`id`)
GO