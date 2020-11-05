alter table `product_picture` drop FOREIGN KEY `FKh3amnci4cl7xcl1al140xw79e`;
GO

-- сначала нужно удалить внешний ключ
alter table `picture` drop FOREIGN KEY `FKe9cv52k04xoy6cj8xy308gnw3`;
GO

-- потом уникальность, которую это внешний ключ использовал
alter table `picture` drop key `UK_ehsu2tyinopypjox1ijxt3g3c`;
GO

alter table `product_picture` drop FOREIGN KEY `FKloucf8ggy74nmdej2jmvttvi4`;
GO

drop table `picture`;
GO

drop table `product_picture`;
GO

drop table `picture_data`;
GO