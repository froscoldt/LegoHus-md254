SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';




drop schema if exists `mydb`;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

drop table if exists `mydb`.`Order`;
drop table if exists `mydb`.`Users`;
drop table if exists `mydb`.`LegoHus`;

CREATE TABLE IF NOT EXISTS `mydb`.`Users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(135) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`LegoHus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`LegoHus` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `width` INT NOT NULL,
  `length` INT NOT NULL,
  `height` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Orders` (
  `order_id` INT(11) NOT NULL auto_increment,
  `order_status` VARCHAR(45) NULL DEFAULT 'Not Sent',
  `Users_id` INT(11) NOT NULL,
  `LegoHus_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `LegoHus_id`),
  INDEX `fk_Orders_Users_idx` (`Users_id` ASC),
  INDEX `fk_Orders_LegoHus1_idx` (`LegoHus_id` ASC),
  CONSTRAINT `fk_Orders_Users`
    FOREIGN KEY (`Users_id`)
    REFERENCES `mydb`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Orders_LegoHus1`
    FOREIGN KEY (`LegoHus_id`)
    REFERENCES `mydb`.`LegoHus` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
