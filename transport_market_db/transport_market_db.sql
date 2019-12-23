SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `transport_market` ;
CREATE SCHEMA IF NOT EXISTS `transport_market` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
SHOW WARNINGS;
USE `transport_market` ;

-- -----------------------------------------------------
-- Table `transport_market`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transport_market`.`users` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `transport_market`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `profile_picture_link` VARCHAR(512) NOT NULL,
  `isProvider` TINYINT(1) NOT NULL DEFAULT False,
  `isAdmin` TINYINT(1) NOT NULL DEFAULT False,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `transport_market`.`jobs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transport_market`.`jobs` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `transport_market`.`jobs` (
  `id` INT NOT NULL DEFAULT 1,
  `description` TEXT NOT NULL,
  `price` INT NOT NULL DEFAULT 0,
  `route` TEXT NOT NULL,
  `tags` TEXT NOT NULL,
  `postDate` TIMESTAMP NOT NULL DEFAULT '2019-12-15 00:00:00.0000',
  `departureDate` TIMESTAMP NOT NULL DEFAULT '2019-12-15 00:00:00.0000',
  `arrivalDate` TIMESTAMP NOT NULL DEFAULT '2019-12-15 00:00:00.0000',
  `sale` TINYINT NOT NULL DEFAULT 0,
  `ownerId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK2_USERS_4_idx` (`ownerId` ASC),
  CONSTRAINT `FK2_USERS_4`
    FOREIGN KEY (`ownerId`)
    REFERENCES `transport_market`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `transport_market`.`job_photos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transport_market`.`job_photos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `transport_market`.`job_photos` (
  `id` INT NOT NULL DEFAULT 1,
  `link` VARCHAR(512) NOT NULL,
  `jobId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK1_JOBS_idx` (`jobId` ASC),
  CONSTRAINT `FK1_JOBS`
    FOREIGN KEY (`jobId`)
    REFERENCES `transport_market`.`jobs` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `transport_market`.`wallets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transport_market`.`wallets` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `transport_market`.`wallets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `balance` INT NOT NULL DEFAULT 0,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idtable1_UNIQUE` (`id` ASC),
  INDEX `FK1_USERS_idx` (`userId` ASC),
  UNIQUE INDEX `userId_UNIQUE` (`userId` ASC),
  CONSTRAINT `FK1_USERS`
    FOREIGN KEY (`userId`)
    REFERENCES `transport_market`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `transport_market`.`messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transport_market`.`messages` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `transport_market`.`messages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NOT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT '2019-12-15 00:00:00.0000',
  `senderId` INT NOT NULL,
  `receiverId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK1_USERS_1_idx` (`senderId` ASC),
  INDEX `FK2_USERS_2_idx` (`receiverId` ASC),
  CONSTRAINT `FK1_USERS_1`
    FOREIGN KEY (`senderId`)
    REFERENCES `transport_market`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK2_USERS_2`
    FOREIGN KEY (`receiverId`)
    REFERENCES `transport_market`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `transport_market`.`user_reviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transport_market`.`user_reviews` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `transport_market`.`user_reviews` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `rate` TINYINT NOT NULL DEFAULT 0,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idreviews_UNIQUE` (`id` ASC),
  INDEX `FK1_USERS_3_idx` (`userId` ASC),
  CONSTRAINT `FK1_USERS_3`
    FOREIGN KEY (`userId`)
    REFERENCES `transport_market`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `transport_market`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transport_market`.`orders` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `transport_market`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `jobId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK1_USERS_4_idx` (`userId` ASC),
  INDEX `FK2_JOBS_2_idx` (`jobId` ASC),
  CONSTRAINT `FK1_USERS_4`
    FOREIGN KEY (`userId`)
    REFERENCES `transport_market`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK2_JOBS_2`
    FOREIGN KEY (`jobId`)
    REFERENCES `transport_market`.`jobs` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
