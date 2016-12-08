-- -----------------------------------------------------
-- Schema souninja
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `souninja` DEFAULT CHARACTER SET utf8 ;
USE `souninja` ;

-- -----------------------------------------------------
-- Table `souninja`.`cliente_regime_tributario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `souninja`.`cliente_regime_tributario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `souninja`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `souninja`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rasao_social` VARCHAR(45) NOT NULL,
  `cnpj` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cliente_regime_tributario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_cliente_cliente_regime_tributario1_idx` (`cliente_regime_tributario_id` ASC),
  CONSTRAINT `fk_cliente_cliente_regime_tributario1`
    FOREIGN KEY (`cliente_regime_tributario_id`)
    REFERENCES `souninja`.`cliente_regime_tributario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `souninja`.`anexo_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `souninja`.`anexo_tipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `aliquota_imposto` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `souninja`.`nota_fiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `souninja`.`nota_fiscal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` INT NOT NULL,
  `data_emissao` DATE NOT NULL,
  `valor` DOUBLE NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `cliente_id` INT NOT NULL,
  `anexo_tipo_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_nota_fiscal_cliente_idx` (`cliente_id` ASC),
  INDEX `fk_nota_fiscal_anexo_tipo1_idx` (`anexo_tipo_id` ASC),
  CONSTRAINT `fk_nota_fiscal_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `souninja`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_fiscal_anexo_tipo1`
    FOREIGN KEY (`anexo_tipo_id`)
    REFERENCES `souninja`.`anexo_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `souninja`.`imposto_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `souninja`.`imposto_tipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `souninja`.`imposto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `souninja`.`imposto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vencimento` DATE NOT NULL,
  `valor` FLOAT NOT NULL,
  `referencia` DATE NOT NULL,
  `pago` TINYINT(1) NOT NULL,
  `imposto_tipo_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_imposto_imposto_tipo1_idx` (`imposto_tipo_id` ASC),
  INDEX `fk_imposto_cliente1_idx` (`cliente_id` ASC),
  CONSTRAINT `fk_imposto_imposto_tipo1`
    FOREIGN KEY (`imposto_tipo_id`)
    REFERENCES `souninja`.`imposto_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_imposto_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `souninja`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `souninja`.`cliente_anexo_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `souninja`.`cliente_anexo_tipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT NOT NULL,
  `anexo_tipo_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cliente_enexo_tipo_cliente1_idx` (`cliente_id` ASC),
  INDEX `fk_cliente_enexo_tipo_anexo_tipo1_idx` (`anexo_tipo_id` ASC),
  CONSTRAINT `fk_cliente_enexo_tipo_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `souninja`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_enexo_tipo_anexo_tipo1`
    FOREIGN KEY (`anexo_tipo_id`)
    REFERENCES `souninja`.`anexo_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
