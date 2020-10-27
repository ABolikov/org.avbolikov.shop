alter table product
    drop foreign key product_brand_id_fk;
GO

alter table product_category
    drop foreign key product_category_product_id_fk;
GO

alter table product_category
    drop foreign key product_category_category_id_fk;
GO

drop table product;
GO

drop table brand;
GO

drop table category;
GO

drop table product_category;
GO

