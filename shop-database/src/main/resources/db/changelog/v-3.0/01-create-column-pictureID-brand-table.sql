ALTER TABLE `brand`
    ADD COLUMN `picture_id` INT NULL AFTER `name`;
GO

ALTER TABLE brand
    ADD CONSTRAINT brand_picture_id_fk
        FOREIGN KEY (picture_id)
            REFERENCES picture (id)
GO