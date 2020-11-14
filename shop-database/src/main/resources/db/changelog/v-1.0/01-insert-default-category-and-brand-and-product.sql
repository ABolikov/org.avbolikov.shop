INSERT INTO `category` (`name`)
    VALUE ('Телевозоры'), ('Мониторы'), ('Телефоны'), ('Стиральные машины'), ('Холодильники'), ('Мелкая бытовая техника'), ('Мойки выского давления');
GO

INSERT INTO `brand` (`name`)
    VALUE ('LG'), ('Samsung'), ('Bosch'), ('Sony'), ('Siemens'), ('Karcher'), ('Ariston'), ('Electrolux'), ('Braun'), ('Zanussi'), ('Xiaomi');
GO

INSERT INTO `product` (`name`, `cost`, `brand_id`)
VALUES ('Samsung S27R750QEI', '25977', (SELECT id FROM `brand` WHERE `name` = 'SAMSUNG')),
       ('LG 34WN650-W', '25999', (SELECT id FROM `brand` WHERE `name` = 'LG')),
       ('NatureCool KGV39XL2AR', '37560', (SELECT id FROM `brand` WHERE `name` = 'Bosch')),
       ('Sony Xperia XZ2', '25977', (SELECT id FROM `brand` WHERE `name` = 'Sony')),
       ('Siemens SL10', '590', (SELECT id FROM `brand` WHERE `name` = 'Siemens')),
       ('KARCHER K 3', '14570', (SELECT id FROM `brand` WHERE `name` = 'Karcher')),
       ('test', '23455', (SELECT id FROM `brand` WHERE `name` = 'Ariston')),
       ('test2', '1233', (SELECT id FROM `brand` WHERE `name` = 'Electrolux')),
       ('test3', '64566', (SELECT id FROM `brand` WHERE `name` = 'Braun')),
       ('test4', '9345', (SELECT id FROM `brand` WHERE `name` = 'Zanussi')),
       ('test5', '12576', (SELECT id FROM `brand` WHERE `name` = 'Xiaomi')),
       ('test6', '2345', (SELECT id FROM `brand` WHERE `name` = 'SAMSUNG')),
       ('test7', '345', (SELECT id FROM `brand` WHERE `name` = 'LG')),
       ('test8', '34534', (SELECT id FROM `brand` WHERE `name` = 'Bosch')),
       ('test9', '76822', (SELECT id FROM `brand` WHERE `name` = 'Sony')),
       ('test10', '234268', (SELECT id FROM `brand` WHERE `name` = 'Siemens')),
       ('test11', '234689', (SELECT id FROM `brand` WHERE `name` = 'Karcher')),
       ('test12', '78987', (SELECT id FROM `brand` WHERE `name` = 'Ariston')),
       ('test13', '678678', (SELECT id FROM `brand` WHERE `name` = 'Electrolux')),
       ('test14', '12334', (SELECT id FROM `brand` WHERE `name` = 'Braun')),
       ('test15', '8734', (SELECT id FROM `brand` WHERE `name` = 'Zanussi')),
       ('test16', '23467', (SELECT id FROM `brand` WHERE `name` = 'Xiaomi'));
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'Samsung S27R750QEI'),
       (SELECT id FROM `category` WHERE `name` = 'Телевозоры')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'LG 34WN650-W'),
       (SELECT id FROM `category` WHERE `name` = 'Мониторы');
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'NatureCool KGV39XL2AR'),
       (SELECT id FROM `category` WHERE `name` = 'Холодильники')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'Sony Xperia XZ2'),
       (SELECT id FROM `category` WHERE `name` = 'Телефоны');

GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'Siemens SL10'),
       (SELECT id FROM `category` WHERE `name` = 'Телефоны')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'KARCHER K 3'),
       (SELECT id FROM `category` WHERE `name` = 'Мойки выского давления');
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'test'),
       (SELECT id FROM `category` WHERE `name` = 'Стиральные машины')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'test'),
       (SELECT id FROM `category` WHERE `name` = 'Телефоны');
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'test2'),
       (SELECT id FROM `category` WHERE `name` = 'Мониторы')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'test3'),
       (SELECT id FROM `category` WHERE `name` = 'Мониторы');
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'test4'),
       (SELECT id FROM `category` WHERE `name` = 'Телевозоры')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'test5'),
       (SELECT id FROM `category` WHERE `name` = 'Мойки выского давления');
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'test6'),
       (SELECT id FROM `category` WHERE `name` = 'Телефоны')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'test7'),
       (SELECT id FROM `category` WHERE `name` = 'Телевозоры');
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'test8'),
       (SELECT id FROM `category` WHERE `name` = 'Телевозоры')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'test9'),
       (SELECT id FROM `category` WHERE `name` = 'Холодильники');
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'test10'),
       (SELECT id FROM `category` WHERE `name` = 'Мониторы')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'test11'),
       (SELECT id FROM `category` WHERE `name` = 'Холодильники');
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'test12'),
       (SELECT id FROM `category` WHERE `name` = 'Стиральные машины')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'test13'),
       (SELECT id FROM `category` WHERE `name` = 'Холодильники');
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'test14'),
       (SELECT id FROM `category` WHERE `name` = 'Мойки выского давления')
UNION ALL
SELECT (SELECT id FROM `product` WHERE `name` = 'test15'),
       (SELECT id FROM `category` WHERE `name` = 'Мелкая бытовая техника');
GO

INSERT INTO `product_category` (`product_id`, `category_id`)
SELECT (SELECT id FROM `product` WHERE `name` = 'test16'),
       (SELECT id FROM `category` WHERE `name` = 'Мониторы');
GO
