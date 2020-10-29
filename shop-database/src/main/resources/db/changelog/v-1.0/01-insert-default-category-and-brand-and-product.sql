INSERT INTO `category` (`name`)
    VALUE ('Телевозоры'), ('Мониторы');
GO

INSERT INTO `brand` (`name`)
    VALUE ('LG'), ('SAMSUNG');
GO

INSERT INTO `product` (`name`, `cost`, `brand_id`)
VALUES ('S27R750QEI [LS27R750QEIXCI]', '25977', (SELECT id FROM `brand` WHERE `name` = 'SAMSUNG'));
GO

INSERT INTO `product` (`name`, `cost`, `brand_id`)
VALUES ('34WN650-W [34WN650-W.ARUZ]', '25999', (SELECT id FROM `brand` WHERE `name` = 'LG'));
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'S27R750QEI [LS27R750QEIXCI]'), (SELECT id FROM `category` WHERE `name` = 'Телевозоры')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = '34WN650-W [34WN650-W.ARUZ]'), (SELECT id FROM `category` WHERE `name` = 'Мониторы');
GO
