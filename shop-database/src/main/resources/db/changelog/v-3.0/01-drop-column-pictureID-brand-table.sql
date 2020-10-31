alter table `brand`
    drop foreign key brand_picture_id_fk;
GO

ALTER TABLE `brand`
    DROP COLUMN `picture_id`;
GO
