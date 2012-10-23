SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `graduates_schema` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `graduates_schema` ;

-- -----------------------------------------------------
-- Table `graduates_schema`.`grad_words`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `graduates_schema`.`grad_words` (
  `word_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `english` VARCHAR(45) NOT NULL ,
  `description` TEXT NOT NULL ,
  PRIMARY KEY (`word_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `graduates_schema`.`grad_users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `graduates_schema`.`grad_users` (
  `user_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `user_name` VARCHAR(20) NOT NULL ,
  `user_pass` VARCHAR(25) NULL ,
  PRIMARY KEY (`user_id`) ,
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `graduates_schema`.`grad_user_phases`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `graduates_schema`.`grad_user_phases` (
  `phase_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `user_id` BIGINT NOT NULL ,
  `phase_time` BIGINT NOT NULL ,
  PRIMARY KEY (`phase_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `graduates_schema`.`grad_dictionaries`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `graduates_schema`.`grad_dictionaries` (
  `dictionary_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`dictionary_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `graduates_schema`.`grad_dictionary_words`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `graduates_schema`.`grad_dictionary_words` (
  `dictionary_word_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `dictionary_id` INT NOT NULL ,
  `word_id` BIGINT NOT NULL ,
  PRIMARY KEY (`dictionary_word_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `graduates_schema`.`grad_user_unfamiliar_words`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `graduates_schema`.`grad_user_unfamiliar_words` (
  `unfamiliar_word_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `user_id` BIGINT NOT NULL ,
  `word_id` BIGINT NOT NULL ,
  `phase_id` BIGINT NOT NULL ,
  `entry_time` BIGINT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`unfamiliar_word_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `graduates_schema`.`grad_user_familiar_words`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `graduates_schema`.`grad_user_familiar_words` (
  `familiar_word_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `user_id` BIGINT NOT NULL ,
  `word_id` BIGINT NOT NULL ,
  PRIMARY KEY (`familiar_word_id`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
