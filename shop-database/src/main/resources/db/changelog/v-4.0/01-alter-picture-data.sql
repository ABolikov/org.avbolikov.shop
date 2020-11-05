ALTER TABLE `picture_data`
    ADD COLUMN `file_name` VARCHAR(255) NULL AFTER `data`;
GO

alter table `picture_data` modify `data` longblob NULL;
GO
