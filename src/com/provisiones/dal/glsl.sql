-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.5.31-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para glsl
CREATE DATABASE IF NOT EXISTS `glsl` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `glsl`;


-- Volcando estructura para tabla glsl.ac_activos_tbl
CREATE TABLE IF NOT EXISTS `ac_activos_tbl` (
  `activo_coaces_id` int(9) unsigned NOT NULL,
  `cod_datos` int(11) unsigned NOT NULL,
  `cod_comunidad` varchar(10) COLLATE latin1_spanish_ci NOT NULL,
  `cod_referencia` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`activo_coaces_id`),
  KEY `cod_datos` (`cod_datos`),
  KEY `cod_comunidad` (`cod_comunidad`),
  KEY `cod_referencia` (`cod_referencia`),
  CONSTRAINT `ac_activos_tbl_ibfk_1` FOREIGN KEY (`cod_datos`) REFERENCES `ac_datos_tbl` (`ac_datos_id`) ON DELETE CASCADE,
  CONSTRAINT `ac_activos_tbl_ibfk_2` FOREIGN KEY (`cod_comunidad`) REFERENCES `e3_referencias_tbl` (`nurcat_id`),
  CONSTRAINT `ac_activos_tbl_ibfk_3` FOREIGN KEY (`cod_referencia`) REFERENCES `e3_referencias_tbl` (`nurcat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.ac_activos_tbl: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ac_activos_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `ac_activos_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.ac_datos_tbl
CREATE TABLE IF NOT EXISTS `ac_datos_tbl` (
  `ac_datos_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nuinmu` int(9) unsigned NOT NULL,
  `cod_cosopa` tinyint(2) unsigned NOT NULL,
  `cod_coenae` tinyint(2) unsigned NOT NULL,
  `cod_coesen` tinyint(2) unsigned NOT NULL,
  `novias` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  `nupoac` varchar(17) COLLATE latin1_spanish_ci NOT NULL,
  `nuesac` varchar(5) COLLATE latin1_spanish_ci NOT NULL,
  `nupiac` varchar(11) COLLATE latin1_spanish_ci NOT NULL,
  `nupuac` varchar(17) COLLATE latin1_spanish_ci NOT NULL,
  `nomuin` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `cod_coprae` tinyint(2) unsigned NOT NULL,
  `noprac` varchar(18) COLLATE latin1_spanish_ci NOT NULL,
  `copoin` mediumint(5) unsigned NOT NULL,
  `fereap` int(8) unsigned NOT NULL,
  `cod_coreae` mediumint(5) unsigned NOT NULL,
  `feinau` int(8) unsigned NOT NULL,
  `fesopo` int(8) unsigned NOT NULL,
  `fesepo` int(8) unsigned NOT NULL,
  `ferepo` int(8) unsigned NOT NULL,
  `feadac` int(8) unsigned NOT NULL,
  `cod_codiju` tinyint(2) unsigned NOT NULL,
  `cod_cosjup` tinyint(2) unsigned NOT NULL,
  `cod_costli` tinyint(2) unsigned NOT NULL,
  `cod_coscar` tinyint(2) unsigned NOT NULL,
  `cod_coesve` tinyint(2) unsigned NOT NULL,
  `cod_cotsin` varchar(4) COLLATE latin1_spanish_ci NOT NULL,
  `nufire` varchar(9) COLLATE latin1_spanish_ci NOT NULL,
  `nuregp` smallint(3) unsigned NOT NULL,
  `nomui0` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `nulibe` mediumint(6) unsigned NOT NULL,
  `nutome` mediumint(6) unsigned NOT NULL,
  `nufole` mediumint(6) unsigned NOT NULL,
  `nuinsr` int(8) unsigned NOT NULL,
  `cod_cosocu` tinyint(2) unsigned NOT NULL,
  `cod_coxpro` mediumint(5) unsigned NOT NULL,
  `fesola` int(8) unsigned NOT NULL,
  `fesela` int(8) unsigned NOT NULL,
  `ferela` int(8) unsigned NOT NULL,
  `ferlla` int(8) unsigned NOT NULL,
  `caspre` bigint(11) unsigned NOT NULL,
  `casutr` bigint(11) unsigned NOT NULL,
  `casutc` bigint(11) unsigned NOT NULL,
  `casutg` bigint(11) unsigned NOT NULL,
  `cod_biarre` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cadorm` smallint(4) unsigned NOT NULL,
  `cabano` smallint(4) unsigned NOT NULL,
  `cod_bigapa` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cagapa` mediumint(5) unsigned NOT NULL,
  `casute` int(9) unsigned NOT NULL,
  `cod_bilipo` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cod_biliac` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bilius` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cod_biboin` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bicefi` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `casucb` int(9) unsigned NOT NULL,
  `casucs` int(9) unsigned NOT NULL,
  `feacon` smallint(4) unsigned NOT NULL,
  `idauto` varchar(10) COLLATE latin1_spanish_ci NOT NULL,
  `fedema` int(8) unsigned NOT NULL,
  `ynocur` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `obreco` varchar(6) COLLATE latin1_spanish_ci NOT NULL,
  `ynolec` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `nolojz` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `ferede` int(8) unsigned NOT NULL,
  `poprop` mediumint(6) unsigned NOT NULL,
  `cod_cograp` tinyint(2) unsigned NOT NULL,
  `fepreg` int(8) unsigned NOT NULL,
  `fephac` int(8) unsigned NOT NULL,
  `fefoac` int(8) unsigned NOT NULL,
  `fevact` int(8) unsigned NOT NULL,
  `imvact` bigint(15) unsigned NOT NULL,
  `nufipr` int(9) unsigned NOT NULL,
  `cod_cotpet` tinyint(2) unsigned NOT NULL,
  `feempt` int(8) unsigned NOT NULL,
  `fesorc` int(8) unsigned NOT NULL,
  `fesode` int(8) unsigned NOT NULL,
  `fereac` int(8) unsigned NOT NULL,
  `cod_coxsia` tinyint(2) unsigned NOT NULL,
  `nujuzd` tinyint(2) unsigned NOT NULL,
  `nurcat` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `nomprc` varchar(55) COLLATE latin1_spanish_ci NOT NULL,
  `nutprc` varchar(14) COLLATE latin1_spanish_ci NOT NULL,
  `nomadc` varchar(55) COLLATE latin1_spanish_ci NOT NULL,
  `nutadc` varchar(14) COLLATE latin1_spanish_ci NOT NULL,
  `impcoo` bigint(15) unsigned NOT NULL,
  `coenor` mediumint(5) unsigned NOT NULL,
  `cod_cospat` mediumint(5) unsigned NOT NULL,
  `cod_cospas` mediumint(5) unsigned NOT NULL,
  `idcol3` varchar(4) COLLATE latin1_spanish_ci NOT NULL,
  `cod_biobnu` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `pobrar` mediumint(6) unsigned NOT NULL,
  PRIMARY KEY (`ac_datos_id`),
  KEY `cod_cosopa` (`cod_cosopa`),
  KEY `cod_coenae` (`cod_coenae`),
  KEY `cod_coesen` (`cod_coesen`),
  KEY `cod_coprae` (`cod_coprae`),
  KEY `cod_coreae` (`cod_coreae`),
  KEY `cod_codiju` (`cod_codiju`),
  KEY `cod_cosjup` (`cod_cosjup`),
  KEY `cod_costli` (`cod_costli`),
  KEY `cod_coscar` (`cod_coscar`),
  KEY `cod_coesve` (`cod_coesve`),
  KEY `cod_cotsin` (`cod_cotsin`),
  KEY `cod_cosocu` (`cod_cosocu`),
  KEY `cod_coxpro` (`cod_coxpro`),
  KEY `cod_biarre` (`cod_biarre`),
  KEY `cod_bigapa` (`cod_bigapa`),
  KEY `cod_bilipo` (`cod_bilipo`),
  KEY `cod_biliac` (`cod_biliac`),
  KEY `cod_bilius` (`cod_bilius`),
  KEY `cod_biboin` (`cod_biboin`),
  KEY `cod_bicefi` (`cod_bicefi`),
  KEY `cod_cograp` (`cod_cograp`),
  KEY `cod_cotpet` (`cod_cotpet`),
  KEY `cod_coxsia` (`cod_coxsia`),
  KEY `cod_cospat` (`cod_cospat`),
  KEY `cod_cospas` (`cod_cospas`),
  KEY `cod_biobnu` (`cod_biobnu`),
  CONSTRAINT `ac_datos_tbl_ibfk_1` FOREIGN KEY (`cod_cosopa`) REFERENCES `cosopa_tbl` (`cosopa_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_10` FOREIGN KEY (`cod_coesve`) REFERENCES `coesve_tbl` (`coesve_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_11` FOREIGN KEY (`cod_cotsin`) REFERENCES `cotsin_tbl` (`cotsin_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_12` FOREIGN KEY (`cod_cosocu`) REFERENCES `cosocu_tbl` (`cosocu_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_13` FOREIGN KEY (`cod_coxpro`) REFERENCES `coxpro_tbl` (`coxpro_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_14` FOREIGN KEY (`cod_biarre`) REFERENCES `binaria_tbl` (`binaria_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_15` FOREIGN KEY (`cod_bigapa`) REFERENCES `binaria_tbl` (`binaria_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_16` FOREIGN KEY (`cod_bilipo`) REFERENCES `binaria_tbl` (`binaria_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_17` FOREIGN KEY (`cod_biliac`) REFERENCES `binaria_tbl` (`binaria_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_18` FOREIGN KEY (`cod_bilius`) REFERENCES `binaria_tbl` (`binaria_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_19` FOREIGN KEY (`cod_biboin`) REFERENCES `binaria_tbl` (`binaria_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_2` FOREIGN KEY (`cod_coenae`) REFERENCES `coenae_tbl` (`coenae_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_20` FOREIGN KEY (`cod_bicefi`) REFERENCES `binaria_tbl` (`binaria_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_21` FOREIGN KEY (`cod_cograp`) REFERENCES `cograp_tbl` (`cograp_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_22` FOREIGN KEY (`cod_cotpet`) REFERENCES `cotpet_tbl` (`cotpet_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_23` FOREIGN KEY (`cod_coxsia`) REFERENCES `coxsia_tbl` (`coxsia_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_24` FOREIGN KEY (`cod_cospat`) REFERENCES `cospat_tbl` (`cospat_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_25` FOREIGN KEY (`cod_cospas`) REFERENCES `cospat_tbl` (`cospat_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_26` FOREIGN KEY (`cod_biobnu`) REFERENCES `biobnu_tbl` (`biobnu_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_3` FOREIGN KEY (`cod_coesen`) REFERENCES `coesen_tbl` (`coesen_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_4` FOREIGN KEY (`cod_coprae`) REFERENCES `coprae_tbl` (`coprae_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_5` FOREIGN KEY (`cod_coreae`) REFERENCES `coreae_tbl` (`coreae_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_6` FOREIGN KEY (`cod_codiju`) REFERENCES `codiju_tbl` (`codiju_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_7` FOREIGN KEY (`cod_cosjup`) REFERENCES `cosjup_tbl` (`cosjup_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_8` FOREIGN KEY (`cod_costli`) REFERENCES `costli_tbl` (`costli_id`),
  CONSTRAINT `ac_datos_tbl_ibfk_9` FOREIGN KEY (`cod_coscar`) REFERENCES `coscar_tbl` (`coscar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.ac_datos_tbl: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ac_datos_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `ac_datos_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.biauto_tbl
CREATE TABLE IF NOT EXISTS `biauto_tbl` (
  `biauto_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`biauto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.biauto_tbl: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `biauto_tbl` DISABLE KEYS */;
INSERT INTO `biauto_tbl` (`biauto_id`, `descripcion`) VALUES
	('0', 'SIN INFORMAR'),
	('1', 'AUTORIZADO'),
	('2', 'NO AUTORIZADO');
/*!40000 ALTER TABLE `biauto_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.binaria_tbl
CREATE TABLE IF NOT EXISTS `binaria_tbl` (
  `binaria_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(3) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`binaria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.binaria_tbl: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `binaria_tbl` DISABLE KEYS */;
INSERT INTO `binaria_tbl` (`binaria_id`, `descripcion`) VALUES
	('#', 'N/D'),
	('N', 'NO'),
	('S', 'SI');
/*!40000 ALTER TABLE `binaria_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.biobnu_tbl
CREATE TABLE IF NOT EXISTS `biobnu_tbl` (
  `biobnu_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`biobnu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.biobnu_tbl: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `biobnu_tbl` DISABLE KEYS */;
INSERT INTO `biobnu_tbl` (`biobnu_id`, `descripcion`) VALUES
	('#', 'NO ES OBRA NUEVA'),
	('0', 'NO ES OBRA NUEVA'),
	('1', 'OBRA NUEVA EN CURSO'),
	('2', 'OBRA NUEVA'),
	('3', 'OBRA EN CURSO PARADA');
/*!40000 ALTER TABLE `biobnu_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coacci_e1_tbl
CREATE TABLE IF NOT EXISTS `coacci_e1_tbl` (
  `coacci_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(70) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coacci_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coacci_e1_tbl: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `coacci_e1_tbl` DISABLE KEYS */;
INSERT INTO `coacci_e1_tbl` (`coacci_id`, `descripcion`) VALUES
	('A', 'Solicitada alta de la comunidad. Puede traer un numero de activo'),
	('B', 'Solicitada baja de la comunidad'),
	('E', 'Solicitada baja de activo en la comunidad'),
	('M', 'Solicitada modificación de la comunidad'),
	('X', 'Solicitada inclusión de activos en la comunidad');
/*!40000 ALTER TABLE `coacci_e1_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coacci_e2_tbl
CREATE TABLE IF NOT EXISTS `coacci_e2_tbl` (
  `coacci_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coacci_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coacci_e2_tbl: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `coacci_e2_tbl` DISABLE KEYS */;
INSERT INTO `coacci_e2_tbl` (`coacci_id`, `descripcion`) VALUES
	('A', 'Solicitada alta de cuota'),
	('B', 'Solicitada baja de cuota'),
	('M', 'Solicitada modificación de cuota');
/*!40000 ALTER TABLE `coacci_e2_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coacci_e3_tbl
CREATE TABLE IF NOT EXISTS `coacci_e3_tbl` (
  `coacci_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coacci_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coacci_e3_tbl: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `coacci_e3_tbl` DISABLE KEYS */;
INSERT INTO `coacci_e3_tbl` (`coacci_id`, `descripcion`) VALUES
	('A', 'Alta'),
	('B', 'Baja'),
	('M', 'Modificación');
/*!40000 ALTER TABLE `coacci_e3_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coacci_e4_tbl
CREATE TABLE IF NOT EXISTS `coacci_e4_tbl` (
  `coacci_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coacci_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coacci_e4_tbl: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `coacci_e4_tbl` DISABLE KEYS */;
INSERT INTO `coacci_e4_tbl` (`coacci_id`, `descripcion`) VALUES
	('A', 'Solicitada alta de cuota'),
	('B', 'Solicitada baja de cuota'),
	('M', 'Solicitada modificación de cuota');
/*!40000 ALTER TABLE `coacci_e4_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cocldo_tbl
CREATE TABLE IF NOT EXISTS `cocldo_tbl` (
  `cocldo_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cocldo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cocldo_tbl: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `cocldo_tbl` DISABLE KEYS */;
INSERT INTO `cocldo_tbl` (`cocldo_id`, `descripcion`) VALUES
	('1', 'D.N.I.'),
	('2', 'C.I.F.'),
	('3', 'Tarjeta Residente.'),
	('4', 'Pasaporte'),
	('5', 'C.I.F país extranjero.'),
	('7', 'D.N.I país extranjero.'),
	('8', 'Tarj. identif. diplomática.'),
	('9', 'Menor.'),
	('F', 'Otros persona física.'),
	('J', 'Otros persona jurídica.');
/*!40000 ALTER TABLE `cocldo_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.codiju_tbl
CREATE TABLE IF NOT EXISTS `codiju_tbl` (
  `codiju_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`codiju_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.codiju_tbl: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `codiju_tbl` DISABLE KEYS */;
INSERT INTO `codiju_tbl` (`codiju_id`, `descripcion`) VALUES
	(1, 'DISPONIBLE'),
	(2, 'NO DISPONIBLE'),
	(3, 'DISPONIBLE CON CARGAS'),
	(4, 'DISPONIBLE SIN POSIBILIDAD DE POSESIÓN'),
	(5, 'DISPONIBLE SIN POSIBILIDAD DE POSESIÓN CON CARGAS'),
	(6, 'DISPONIBLE SIN POSIBILIDAD DE INSCRIPCION'),
	(7, 'DISPONIBLE SIN POSIBILIDAD DE INSCRIPCION CON CARGAS');
/*!40000 ALTER TABLE `codiju_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.codtrn_tbl
CREATE TABLE IF NOT EXISTS `codtrn_tbl` (
  `codtrn_id` varchar(4) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`codtrn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.codtrn_tbl: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `codtrn_tbl` DISABLE KEYS */;
INSERT INTO `codtrn_tbl` (`codtrn_id`, `descripcion`) VALUES
	('EE41', 'Comunidades'),
	('EE42', 'Cuotas'),
	('EE43', 'Referencias Catastrales'),
	('EE44', 'Impuestos y Recursos');
/*!40000 ALTER TABLE `codtrn_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coenae_tbl
CREATE TABLE IF NOT EXISTS `coenae_tbl` (
  `coenae_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coenae_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coenae_tbl: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `coenae_tbl` DISABLE KEYS */;
INSERT INTO `coenae_tbl` (`coenae_id`, `descripcion`) VALUES
	(1, 'ADJUDICACION'),
	(2, 'DACION'),
	(3, 'COMPRA VENTA'),
	(4, 'DISOLUCION PROINDIVISO'),
	(5, 'ABSORCION'),
	(6, 'CESION EN PAGO'),
	(7, 'COMPRA VENTA JUDICIAL'),
	(8, 'OTROS'),
	(9, 'INTERMEDIACION'),
	(10, 'TRASPASO');
/*!40000 ALTER TABLE `coenae_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coesen_tbl
CREATE TABLE IF NOT EXISTS `coesen_tbl` (
  `coesen_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coesen_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coesen_tbl: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `coesen_tbl` DISABLE KEYS */;
INSERT INTO `coesen_tbl` (`coesen_id`, `descripcion`) VALUES
	(1, 'EN TRAMITE'),
	(2, 'FORMALIZADA'),
	(3, 'NO EFECTUADO'),
	(4, 'PERDIDO'),
	(5, 'EN EXCLUSIVA'),
	(6, 'NOTA ENCARGO'),
	(7, 'DESISTIDO'),
	(8, 'NULIDAD DE ACTUACIONES'),
	(9, 'EXPROPIADO'),
	(10, 'TRASPASO');
/*!40000 ALTER TABLE `coesen_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coesve_tbl
CREATE TABLE IF NOT EXISTS `coesve_tbl` (
  `coesve_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coesve_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coesve_tbl: ~13 rows (aproximadamente)
/*!40000 ALTER TABLE `coesve_tbl` DISABLE KEYS */;
INSERT INTO `coesve_tbl` (`coesve_id`, `descripcion`) VALUES
	(1, 'OFERTAS'),
	(2, 'COMPROMETIDO'),
	(3, 'RESERVA'),
	(4, 'VENDIDO'),
	(5, 'OFERTAS POR LOTES'),
	(6, 'COMPROMISO POR LOTES'),
	(7, 'VENDIDO POR LOTE'),
	(8, 'CONTRATACION'),
	(9, 'SIN OFERTAS'),
	(10, 'VENDIDO ANTIGUO'),
	(11, 'VENDIDO, ENVIADO A 600'),
	(13, 'RESERVA POR LOTES'),
	(14, 'CONTRATACION POR LOTES');
/*!40000 ALTER TABLE `coesve_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cograp_tbl
CREATE TABLE IF NOT EXISTS `cograp_tbl` (
  `cograp_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cograp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cograp_tbl: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `cograp_tbl` DISABLE KEYS */;
INSERT INTO `cograp_tbl` (`cograp_id`, `descripcion`) VALUES
	(0, 'SIN INFORMACION'),
	(1, 'CONSOLIDADA'),
	(2, 'USUFRUCTO'),
	(3, 'NUDA PROPIEDAD'),
	(4, 'SERVIDUMBRE'),
	(5, 'USO'),
	(6, 'OTROS');
/*!40000 ALTER TABLE `cograp_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cogrug_tbl
CREATE TABLE IF NOT EXISTS `cogrug_tbl` (
  `cogrug_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cogrug_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cogrug_tbl: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `cogrug_tbl` DISABLE KEYS */;
INSERT INTO `cogrug_tbl` (`cogrug_id`, `descripcion`) VALUES
	(1, 'Compraventa'),
	(2, 'Pendientes'),
	(3, 'Acciones');
/*!40000 ALTER TABLE `cogrug_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coimpt_tbl
CREATE TABLE IF NOT EXISTS `coimpt_tbl` (
  `coimpt_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coimpt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coimpt_tbl: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `coimpt_tbl` DISABLE KEYS */;
INSERT INTO `coimpt_tbl` (`coimpt_id`, `descripcion`) VALUES
	(0, 'SIN IMPUESTO'),
	(1, 'IVA'),
	(2, 'IGIC'),
	(3, 'IPSI'),
	(4, 'IRPF');
/*!40000 ALTER TABLE `coimpt_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.comona_tbl
CREATE TABLE IF NOT EXISTS `comona_tbl` (
  `comona_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`comona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.comona_tbl: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `comona_tbl` DISABLE KEYS */;
INSERT INTO `comona_tbl` (`comona_id`, `descripcion`) VALUES
	(0, 'NINGUNO'),
	(1, 'DECISION DEL AREA');
/*!40000 ALTER TABLE `comona_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coprae_tbl
CREATE TABLE IF NOT EXISTS `coprae_tbl` (
  `coprae_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(18) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coprae_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coprae_tbl: ~52 rows (aproximadamente)
/*!40000 ALTER TABLE `coprae_tbl` DISABLE KEYS */;
INSERT INTO `coprae_tbl` (`coprae_id`, `descripcion`) VALUES
	(1, 'ALAVA'),
	(2, 'ALBACETE'),
	(3, 'ALICANTE'),
	(4, 'ALMERIA'),
	(5, 'AVILA'),
	(6, 'BADAJOZ'),
	(7, 'BALEARES'),
	(8, 'BARCELONA'),
	(9, 'BURGOS'),
	(10, 'CACERES'),
	(11, 'CADIZ'),
	(12, 'CASTELLON'),
	(13, 'CIUDAD REAL'),
	(14, 'CORDOBA'),
	(15, 'A CORUÑA'),
	(16, 'CUENCA'),
	(17, 'GIRONA'),
	(18, 'GRANADA'),
	(19, 'GUADALAJARA'),
	(20, 'GUIPUZCOA'),
	(21, 'HUELVA'),
	(22, 'HUESCA'),
	(23, 'JAEN'),
	(24, 'LEON'),
	(25, 'LLEIDA'),
	(26, 'RIOJA'),
	(27, 'LUGO'),
	(28, 'MADRID'),
	(29, 'MALAGA'),
	(30, 'MURCIA'),
	(31, 'NAVARRA'),
	(32, 'OURENSE'),
	(33, 'ASTURIAS'),
	(34, 'PALENCIA'),
	(35, 'PALMAS  LAS'),
	(36, 'PONTEVEDRA'),
	(37, 'SALAMANCA'),
	(38, 'SANTA C TENERIFE'),
	(39, 'CANTABRIA'),
	(40, 'SEGOVIA'),
	(41, 'SEVILLA'),
	(42, 'SORIA'),
	(43, 'TARRAGONA'),
	(44, 'TERUEL'),
	(45, 'TOLEDO'),
	(46, 'VALENCIA'),
	(47, 'VALLADOLID'),
	(48, 'VIZCAYA'),
	(49, 'ZAMORA'),
	(50, 'ZARAGOZA'),
	(51, 'CEUTA'),
	(52, 'MELILLA');
/*!40000 ALTER TABLE `coprae_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coreae_tbl
CREATE TABLE IF NOT EXISTS `coreae_tbl` (
  `coreae_id` mediumint(5) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coreae_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coreae_tbl: ~13 rows (aproximadamente)
/*!40000 ALTER TABLE `coreae_tbl` DISABLE KEYS */;
INSERT INTO `coreae_tbl` (`coreae_id`, `descripcion`) VALUES
	(0, 'SIN INFORMACION'),
	(1, 'COBIAN'),
	(2, 'CASES'),
	(3, 'GTREZ.LABRADOR'),
	(4, 'VALDARNO'),
	(5, 'GICSA'),
	(6, 'OTROS'),
	(7, 'PROCURADOR'),
	(8, 'MONTALVO'),
	(9, 'MUNDIGESTION'),
	(10, 'GARSA'),
	(11, 'UNIGES'),
	(12, 'PINOS XXI');
/*!40000 ALTER TABLE `coreae_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cosbga_t11_tbl
CREATE TABLE IF NOT EXISTS `cosbga_t11_tbl` (
  `cosbga_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cosbga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cosbga_t11_tbl: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `cosbga_t11_tbl` DISABLE KEYS */;
INSERT INTO `cosbga_t11_tbl` (`cosbga_id`, `descripcion`) VALUES
	(0, 'Plusvalia'),
	(1, 'Notaría'),
	(50, 'Devolucion Plusvalia'),
	(51, 'Devolucion Notaría');
/*!40000 ALTER TABLE `cosbga_t11_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cosbga_t21_tbl
CREATE TABLE IF NOT EXISTS `cosbga_t21_tbl` (
  `cosbga_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cosbga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cosbga_t21_tbl: ~14 rows (aproximadamente)
/*!40000 ALTER TABLE `cosbga_t21_tbl` DISABLE KEYS */;
INSERT INTO `cosbga_t21_tbl` (`cosbga_id`, `descripcion`) VALUES
	(0, 'Impuestos e IBIS'),
	(1, 'IBIS'),
	(2, 'Tasas basura'),
	(3, 'Tasas alcantarillado'),
	(4, 'Tasas agua'),
	(5, 'Contribuciones especiales'),
	(6, 'Otras tasas'),
	(50, 'Devolucion Impuestos e IBIS'),
	(51, 'Devolucion IBIS'),
	(52, 'Devolucion Tasas basura'),
	(53, 'Devolucion Tasas alcantarillado'),
	(54, 'Devolucion Tasas agua'),
	(55, 'Devolucion Contribuciones especiales'),
	(56, 'Devolucion Otras tasas');
/*!40000 ALTER TABLE `cosbga_t21_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cosbga_t22_tbl
CREATE TABLE IF NOT EXISTS `cosbga_t22_tbl` (
  `cosbga_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cosbga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cosbga_t22_tbl: ~12 rows (aproximadamente)
/*!40000 ALTER TABLE `cosbga_t22_tbl` DISABLE KEYS */;
INSERT INTO `cosbga_t22_tbl` (`cosbga_id`, `descripcion`) VALUES
	(0, 'Comunidades'),
	(1, 'Ordinaria'),
	(2, 'Extras Comunidad'),
	(3, 'Mancomunidad'),
	(4, 'Extras Mancomunidad'),
	(5, 'Obras comunidad'),
	(50, 'Devolucion Comunidades'),
	(51, 'Devolucion Ordinaria'),
	(52, 'Devolucion Extras Comunidad'),
	(53, 'Devolucion Mancomunidad'),
	(54, 'Devolucion Extras Mancomunidad'),
	(55, 'Devolucion Obras comunidad');
/*!40000 ALTER TABLE `cosbga_t22_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cosbga_t23_tbl
CREATE TABLE IF NOT EXISTS `cosbga_t23_tbl` (
  `cosbga_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cosbga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cosbga_t23_tbl: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `cosbga_t23_tbl` DISABLE KEYS */;
INSERT INTO `cosbga_t23_tbl` (`cosbga_id`, `descripcion`) VALUES
	(0, 'Suministros'),
	(1, 'Suministro luz'),
	(2, 'Suministro agua'),
	(3, 'Suministro gas'),
	(50, 'Devolucion Suministros'),
	(51, 'Devolucion Suministro luz'),
	(52, 'Devolucion Suministro agua'),
	(53, 'Devolucion Suministro gas');
/*!40000 ALTER TABLE `cosbga_t23_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cosbga_t32_tbl
CREATE TABLE IF NOT EXISTS `cosbga_t32_tbl` (
  `cosbga_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cosbga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cosbga_t32_tbl: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `cosbga_t32_tbl` DISABLE KEYS */;
INSERT INTO `cosbga_t32_tbl` (`cosbga_id`, `descripcion`) VALUES
	(0, 'Honorarios Colaboradores'),
	(1, 'Prescripción'),
	(2, 'Colaboración'),
	(3, 'Otros honorarios'),
	(4, 'Servicios varios');
/*!40000 ALTER TABLE `cosbga_t32_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coscar_tbl
CREATE TABLE IF NOT EXISTS `coscar_tbl` (
  `coscar_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coscar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coscar_tbl: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `coscar_tbl` DISABLE KEYS */;
INSERT INTO `coscar_tbl` (`coscar_id`, `descripcion`) VALUES
	(0, 'SIN INICIAR TRAMITES'),
	(1, 'PENDIENTE DE ANALIZAR '),
	(2, 'SIN CARGAS'),
	(3, 'CON CARGAS'),
	(4, 'CARGAS CANCELADAS'),
	(5, 'CARGAS QUE NO SE VAN A CANCELAR');
/*!40000 ALTER TABLE `coscar_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cosiga_tbl
CREATE TABLE IF NOT EXISTS `cosiga_tbl` (
  `cosiga_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cosiga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cosiga_tbl: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `cosiga_tbl` DISABLE KEYS */;
INSERT INTO `cosiga_tbl` (`cosiga_id`, `descripcion`) VALUES
	(1, 'ESTIMADO'),
	(2, 'CONOCIDO'),
	(3, 'AUTORIZADO'),
	(4, 'PAGADO'),
	(5, 'PAGADO PARCIALMENTE'),
	(6, 'ESPERA DE PAGO'),
	(7, 'PAGADO CONEXION');
/*!40000 ALTER TABLE `cosiga_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cosjup_tbl
CREATE TABLE IF NOT EXISTS `cosjup_tbl` (
  `cosjup_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cosjup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cosjup_tbl: ~17 rows (aproximadamente)
/*!40000 ALTER TABLE `cosjup_tbl` DISABLE KEYS */;
INSERT INTO `cosjup_tbl` (`cosjup_id`, `descripcion`) VALUES
	(0, 'DESCONOCIDO'),
	(1, 'ACTIVO EN TRAMITE. SIN SEÑALAR SUBASTAS'),
	(2, 'ACTIVO EN TRAMITE. SEÑALADAS SUBASTAS'),
	(3, 'ACTA DE SUBASTA APROBANDO REMATE SIN AUTO'),
	(4, 'SE DESCONOCE SITUACION. SIN AUTO/TITULO'),
	(5, 'AUTO/TITULO PENDIENTE DE INSCRIPCION'),
	(6, 'TITULO FIRME. NO ES NECESARIO POSESION LANZAMIENTO'),
	(7, 'AUTO FIRME. PENDIENTE POSESION'),
	(8, 'AUTO FIRME. SEÑALADO POSESION'),
	(9, 'AUTO FIRME CON POSESION. NO NECESARIO LANZAMIENTO'),
	(10, 'AUTO FIRME CON POSESION. PENDIENTE LANZAMIENTO'),
	(11, 'AUTO FIRME CON POSESION. SEÑALADO LANZAMIENTO'),
	(12, 'AUTO FIRME CON POSESION Y LANZAMIENTO'),
	(13, 'AUTO FIRME. CONTRATO DE ARRENDAMIENTO'),
	(14, 'AUTO FIRME. ARRENDAMIENTO RECURRIDO'),
	(15, 'AUTO FIRME SIN POSIBILIDAD DE POSESION'),
	(16, '--Pendiente--');
/*!40000 ALTER TABLE `cosjup_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cosocu_tbl
CREATE TABLE IF NOT EXISTS `cosocu_tbl` (
  `cosocu_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cosocu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cosocu_tbl: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `cosocu_tbl` DISABLE KEYS */;
INSERT INTO `cosocu_tbl` (`cosocu_id`, `descripcion`) VALUES
	(0, 'SIN DATOS'),
	(1, 'USUFRUCTUARIO'),
	(2, 'PRECARISTA'),
	(3, 'INQUILINO'),
	(4, 'PROP./USUARIO'),
	(5, 'DESOCUPADA'),
	(6, 'OTROS'),
	(7, 'DESCONOCIDO');
/*!40000 ALTER TABLE `cosocu_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cosopa_tbl
CREATE TABLE IF NOT EXISTS `cosopa_tbl` (
  `cosopa_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cosopa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cosopa_tbl: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `cosopa_tbl` DISABLE KEYS */;
INSERT INTO `cosopa_tbl` (`cosopa_id`, `descripcion`) VALUES
	(0, 'NO INFORMADO'),
	(1, 'CAJA MADRID'),
	(2, 'ALTAE'),
	(3, 'INCOMSA'),
	(4, 'DECOMSA'),
	(5, 'CISMISA'),
	(6, 'MAPFRE'),
	(7, 'INFODIRECCION');
/*!40000 ALTER TABLE `cosopa_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cospat_tbl
CREATE TABLE IF NOT EXISTS `cospat_tbl` (
  `cospat_id` mediumint(5) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cospat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cospat_tbl: ~331 rows (aproximadamente)
/*!40000 ALTER TABLE `cospat_tbl` DISABLE KEYS */;
INSERT INTO `cospat_tbl` (`cospat_id`, `descripcion`) VALUES
	(1, 'BANKIA'),
	(2, 'ALTAE'),
	(3, 'INCOMSA'),
	(4, 'DECOMSA'),
	(5, 'CISMISA'),
	(6, 'MAPFRE'),
	(7, 'INFODIRECCION'),
	(8, 'MADRID LEASING CORPORACION'),
	(9, 'FINANMADRID'),
	(11, 'MBS BANCAJA 8 FTA'),
	(12, 'MBS BANCAJA 7 FTA'),
	(13, 'FTPYME BANCAJA 8'),
	(14, 'FTGENVAL BANCAJA 1 FTA'),
	(15, 'BANCAJA-BVA VPO 1 FTA'),
	(16, 'MBS BANCAJA 6 FTA'),
	(17, 'FINANCIACION BANCAJA 1'),
	(18, 'BANCAJA 13 FTA'),
	(19, 'PYME BANCAJA 7'),
	(20, 'CISA 2011'),
	(21, 'BANKIA HABITAT'),
	(22, 'BANCAJA'),
	(23, 'TRÉBOL HABITAT'),
	(24, 'FUENTES DE CHIVA'),
	(25, 'GOLF SAN GREGORI'),
	(26, 'URBANIZADORA MADRIGAL'),
	(27, 'ANSOGASA'),
	(28, 'MARENYS'),
	(29, 'URBANIZADORA NORTE PEÑISCOLA'),
	(30, 'CAJA AVILA'),
	(31, 'INVERÁVILA S.A.'),
	(32, 'VEHÍCULOS DE TENENCIA Y GESTIÓN 4'),
	(33, 'SOCIEDAD PATRIMONIAL CAJA AVILA 4'),
	(34, 'SOCIEDAD PATRIMONIAL CAJA AVILA 5'),
	(35, 'EUROMIESKANIA'),
	(36, 'CIVITAS'),
	(37, 'DICUMAR BALEAR'),
	(38, 'BANCAJA 11 FTA'),
	(39, 'FTPYME BANCAJA 6'),
	(40, 'CAJA SEGOVIA'),
	(41, 'VALLENAVA INVERSIONES'),
	(42, 'SOCIEDAD PATRIMONIAL CAJA SEGOVIA'),
	(43, 'SOCIEDAD PATRIMONIAL CAJA SEGOVIA'),
	(44, 'SOCIEDAD PATRIMONIAL CAJA SEGOVIA'),
	(45, 'MBS BANCAJA 3 FTA'),
	(46, 'CONSUMO BANCAJA 1 FTA'),
	(47, 'PYME BANCAJA 5 FTA'),
	(48, 'BANCAJA 10 FTA'),
	(49, 'MBS BANCAJA 4 FTA'),
	(50, 'ADQUIRENT INMOBLES, S.L.'),
	(51, 'ANALISIS Y VERIFICACIÓN CONTROL T'),
	(52, 'INVERSORA BURRIAC, S.L.'),
	(53, 'MOVIOLA ASOCIADOS 21, S.L.'),
	(54, 'CAIXA LAIETANA'),
	(55, 'FTPYME BANCAJA 3 FTA'),
	(56, 'BANCAJA 8 FTA'),
	(57, 'MBS BANCAJA 2 FTA'),
	(58, 'CM BANCAJA 1 FTA'),
	(59, 'BANCAJA 9 FTA'),
	(60, 'CAJA RIOJA'),
	(61, 'ACTIVOS 26001, S.L.'),
	(62, 'SUELOS 26001, S.L.'),
	(63, 'PROMOCIONES Y OBRAS 26001, S.L.'),
	(64, 'ARRENDAMIENTOS 26001, S.L.'),
	(65, 'MONTIS LOCARE'),
	(66, 'SUELOS 26002, S.L.'),
	(67, 'BANCAJA 6 FTA'),
	(68, 'MBS BANCAJA 1 FTA'),
	(69, 'BANCAJA 7 FTA'),
	(70, 'CAJA CANARIAS'),
	(71, 'ESINCA, S.A.'),
	(72, 'SOCIEDAD PATRIMONIAL CAJA CANARIA'),
	(73, 'SOCIEDAD PATRIMONIAL CAJA CANARIA'),
	(74, 'SOCIEDAD PATRIMONIAL CAJA CANARIA'),
	(75, 'BANCAJA 3 FTA'),
	(76, 'BANCAJA 4 FTH'),
	(77, 'BANCAJA 5 FTA'),
	(78, 'FTPYME BANCAJA 2'),
	(79, 'AYT'),
	(80, 'RMBS I'),
	(81, 'RMBS II'),
	(82, 'RMBS III'),
	(83, 'RMBS IV'),
	(84, 'MADRID ICO-FTVPO'),
	(85, 'MADRID RESIDENCIAL I'),
	(86, 'MADRID RESIDENCIAL II'),
	(87, 'MADRID FTPYME I'),
	(88, 'MADRID CONSUMO I'),
	(89, 'MADRID CONSUMO II'),
	(90, 'ALIANCIA ZERO S.L.'),
	(91, 'ALIANCIA INVERS.EN INMUEBLES DOS SL'),
	(92, 'URBAPINAR S.L.'),
	(93, 'GESTORA DEL SUELO DE LEVANTE'),
	(94, 'Residencial_II_20100122'),
	(95, 'PRUEBA CARTERA FOLLETO'),
	(96, 'AYT2, FONDO DE TITULIZACION HIPOTECARIA'),
	(97, 'FTPYME_II_CFOLL_20111013_CU'),
	(98, 'BANCAJA LEASING 1 FTA'),
	(99, 'BFA'),
	(100, 'ACINELAV INVERSIONES 2006, S.L.'),
	(101, 'ADAMAR SECTORS, S.L'),
	(102, 'ALIANZA LOGÍSTICA MAFORT-HABITAT, S.L.'),
	(103, 'ALQUILER JOVENES DE VIVIENDAS EN COLMENAR VIEJO'),
	(104, 'ALTAFULLA LIFE RESORT, S.L.'),
	(105, 'ALTER INMUEBLES, S.L.'),
	(106, 'ANALYS INVIERTE 21, S.L.'),
	(107, 'ANZA PROYECTOS S.L.'),
	(108, 'ÁREA LOGROÑO ACTUACIONES RESIDENCIALES Y APARCAMIENTO'),
	(109, 'ASENTIS CONSULTORIA S.L.U'),
	(110, 'ASENTIS PROMOCION S.A.'),
	(111, 'ASSET FOUNDS'),
	(112, 'AUDET PROMOCIONS, S.L'),
	(113, 'AVANZA MADRID V.J., S.L.'),
	(114, 'AZORA EUROPA I, S.A.'),
	(115, 'AZORA EUROPA II, S.A.'),
	(116, 'BAJA CALIFORNIA INVESTMENT. B.V'),
	(117, 'BANEASA SOL, S.L'),
	(118, 'BENIDORM COMPLEJO DE VIDA Y GOLF'),
	(119, 'BETA GROUP S.R.L.'),
	(120, 'CAMI LA MAR DE SAGUNTO'),
	(121, 'CAREY VALUE ADDED, S.L.'),
	(122, 'CERAMICAS LA FLORIDA, S.L.'),
	(123, 'CIA DESARROLLOS CIUDAD DE HISPALIS'),
	(124, 'CISTERCAM ALQUILERES PROTEGIDOS, S.L.'),
	(125, 'COBIMANSA'),
	(126, 'COLMENAR DESARROLLOS RESIDENCIALES S.L.'),
	(127, 'COMPAÑÍA PAI, S.L.'),
	(128, 'COMPLEJO INMOBILIARIO CASTELLANA 200'),
	(129, 'COMTAL ESTRUC'),
	(130, 'COSTA BELLVER, S.A.'),
	(131, 'COSTA EBORIS'),
	(132, 'COSTA VERDE HABITAT, S.L.'),
	(133, 'CREACION DEL SUELO E INFRAESTRUCTURAS S.L.'),
	(134, 'CSJ DESARROLLOS RESIDENCIALES S.L.'),
	(135, 'CUATRO ESTACIONES INMOBILIARIA SXXI'),
	(136, 'D. U. MIRAPLANA, S.L.'),
	(137, 'DEPROINMED, S.L.'),
	(138, 'DESARROLLOS DE PALMA SACV'),
	(139, 'DESARROLLOS INMOBILIARIOS CAMPOTEJAR S.L.'),
	(140, 'DESARROLLOS INMOBILIARIOS SALAMANCA S.L.'),
	(141, 'DESARROLLOS TORREBLANCA, S.L.'),
	(142, 'DESARROLLOS URBANISTICOS DE SEGOVIA S.A.'),
	(143, 'DESARROLLOS URBANISTICOS LOS CASTAÑOS'),
	(144, 'DESARROLLOS URBANISTICOS NUEVOS ESPACIOS, S.L.'),
	(145, 'DESARROLLOS URBANISTICOS VALDEAVERUELO'),
	(146, 'EBROSA PARTICIPACIONES, S.L.'),
	(147, 'ECOEDI 2002 SA'),
	(148, 'EDICTA SERVICIOS S.A.'),
	(150, 'EE SPAIN'),
	(151, 'EGICAM PLAN JOVEN, S.L.'),
	(152, 'EJIDO DESARROLLOS URBANOS, S.L.'),
	(153, 'EMERALD'),
	(154, 'ENCINA LOS MONTEROS, S.L.'),
	(155, 'ENSENADA DE SAN MIGUEL URBANIZADORA, S.L.'),
	(156, 'ESPACIO JOVEN HOGARES, S.L.'),
	(157, 'ESPAI COMERCIAL VILA-REIAL, S.L.'),
	(158, 'EUROPEA DE DESARROLLOS URBANOS'),
	(159, 'EVERN INVESTS HUNGARY, K.F.T.'),
	(160, 'FERROCARRIL  I&P, S.L.'),
	(161, 'FERULEN, S.L.'),
	(162, 'FIBEL 2005, S.L.'),
	(163, 'FINCAS Y GESTIÓN INMOBILIARIA 26001, S.L.'),
	(164, 'FROZEN ASSETS'),
	(165, 'GEBER URBANA, S.L.'),
	(166, 'GEOBUL  2006, OOD'),
	(167, 'GEOBULGARIA ESPAÑOLA, S.L'),
	(168, 'GEOINVERS, S.A'),
	(169, 'GEOPORTUGAL IMOBILIARIA, L.D.A'),
	(170, 'GERENS HILL GESTION DE ACTIVOS'),
	(171, 'GERENS HILL INTERNACIONAL'),
	(172, 'GESNOVA GESTIÓN INMOBILIARIA INTEGRAL, S.L.'),
	(173, 'GESTECAM VIVIENDA JOVEN, S.L.'),
	(174, 'GESTIÓN DE INICIATIVAS EMPRESARIALES TECNOLÓGICAS (GE'),
	(175, 'GESTORA CASTELLANA DEL SUELO SAU'),
	(176, 'GESTORA DE DESARROLLOS Y ARRENDAMIENTOS, S.L. (GEDASA'),
	(177, 'GOLF PEÑISCOLA'),
	(178, 'GRAN HOTEL XIRIVELLA, SL'),
	(179, 'GRAND CORAL PROPERTY & FACILITY MANGEMENT'),
	(180, 'GRUPO EMPRESARIAL PINAR, S.L.'),
	(181, 'GRUPO INMOBILIARIO FERROCARRIL, S.A.'),
	(182, 'GRUPO LAR DESARROLLOS URBANISTICOS'),
	(183, 'GRUPO VALENCIANO DE ALQUILER PROTEGIDO, S.L.'),
	(184, 'HABITAT 2018'),
	(185, 'HABITAT RESORTS, S.L.'),
	(186, 'HABITAT SON VALENTI'),
	(187, 'HABITAT USA CORPORATION (EMERALD PLACE)'),
	(188, 'HABITAT VIDA Y RESORTS,'),
	(189, 'HARMONIA PLA DE PONENT S.L.'),
	(190, 'HERCECAM VIVIENDA JOVEN S.L.'),
	(191, 'HERCECAM VIVIENDA TORREJON S.L.'),
	(192, 'HERCESA INTERMEDIACIÓN Y PATRIMONIOS'),
	(193, 'HERCESA INTERNACIONAL, S.L.'),
	(194, 'HILL PROPERTIES PTE LTD'),
	(195, 'HOGAR Y PATRIMONIOS VIVIENDA JOVEN, S.L.'),
	(196, 'HOTEL ALAMEDA VALENCIA, S.L.'),
	(197, 'HOTEL BARCELONA GOLF, S.L.'),
	(198, 'I.A.F. CHEQUIA S.R.O.'),
	(199, 'IB INVESTMENTS GMBH (RESTAURA BERLIN GmbH)'),
	(200, 'IBIZA SOL, S.L'),
	(201, 'ICONO MEDITERRANEO S.L.U'),
	(202, 'IMASINTER VIVIENDA JOVEN'),
	(203, 'INICIATIVAS GESTIOMAT, SL'),
	(204, 'INMACOR DESARROLLOS SACV'),
	(205, 'INMOBILIARIA PIEDRAS BOLAS SACV'),
	(206, 'INMOCAM VIVIENDA JOVEN'),
	(207, 'INMOVEMU SL'),
	(208, 'INMOVIST INVERSIONES INMOBILIARIAS, S.L.'),
	(209, 'INPAFER VIVIENDA JOVEN, S.L.'),
	(210, 'INTERISOLUX ALCORCÓN VIVIENDA JOVEN, S.L.'),
	(211, 'INTERISOLUX TORREJÓN VIVIENDA JOVEN, S.L.'),
	(212, 'INTERMEDIACIÓN Y PATRIMONIOS'),
	(213, 'INTERNOVA VIVIENDA JOVEN, S.L'),
	(214, 'INURBE IBERICA DE CV'),
	(215, 'INVERPORFOLIO INTERNACIONAL, S.A.'),
	(216, 'INVERSION EN ALQUILER DE VIVIENDAS S.L.'),
	(217, 'INVERSIONES EN RESORTS DEL MEDITERRANEO'),
	(218, 'INVERSIONES Y DESARROLLOS 2069 MADRID, S.L.'),
	(219, 'INVERSIONES Y DESARROLLOS 2069 VALLADOLID, S.L.'),
	(220, 'JARDÍ RESIDENCIAL LA GARRIGA, S.L.'),
	(221, 'JUVIGOLF'),
	(222, 'LARCAVILLA PROMOCIONES SL'),
	(223, 'LAS LOMAS DE POZUELO'),
	(224, 'LAVARALDA, S.L.'),
	(225, 'LAZORA'),
	(226, 'LAZORA II, S.A.'),
	(227, 'LEADERMAN INVESTMENT GROUP S.L.'),
	(228, 'LICASA'),
	(229, 'LOGIS URBA, S.L.'),
	(230, 'LOMAS DE EL PINO, S.L.'),
	(231, 'MACARTHUR PATTON  Y ASOCIADOS, S.L'),
	(232, 'MACLA 2005, S.L.'),
	(233, 'MAIMONA GOLF'),
	(234, 'MALILLA 2000, S.A.'),
	(235, 'MAMMOTH CAVE SPAIN, S.L.'),
	(236, 'MAS DE PEIRON, S.L.'),
	(237, 'MASIA DE MONTE SANO, S.L.'),
	(238, 'MATARO LLAR, S.L.'),
	(239, 'MEDITERRANEA DE ACTUACIONES INTEGRADAS'),
	(240, 'MEGO INVERSIONES S.L.'),
	(241, 'NARMAR, S.A.'),
	(242, 'NAVES Y VIVIENDAS, S.A.'),
	(243, 'NAVICOAS ASTURIAS S.L.'),
	(244, 'NESCAM 2006, S.L.'),
	(245, 'NEWCOVAL'),
	(246, 'NLBH PARQUES COMERCIALES'),
	(247, 'NORDIC RESIDENTIAL, S.L.'),
	(248, 'NORDIC SOL COMMERCIAL, S.L.'),
	(249, 'NOVA PANORÁMICA'),
	(250, 'NUEVAS ACTIVIDADES URBANAS, S.L.'),
	(251, 'NUMZAAN, S.L.'),
	(252, 'OCEANIC CENTER, S.L.'),
	(253, 'OCIO LOS MONTEROS, S.L.'),
	(254, 'ONCISA INICIATIVAS DE DESARROLLO S.L.'),
	(255, 'ORCHID COSTA PRIVATE LTD'),
	(256, 'ORCHID INVESTMENTS,B.V.'),
	(257, 'OSITO PARK, SL'),
	(258, 'PARAJE CAUTIVADOR, S.L.'),
	(259, 'PARK MISTRAL, S.L.'),
	(260, 'PARQUE CENTRAL AGENTE URBANIZADOR'),
	(261, 'PEÑISCOLA GREEN, S.L.'),
	(262, 'PINAR CAPITOL S.L.'),
	(263, 'PINAR DEL SURESTE S.L.'),
	(264, 'PINAR HABITAT, S.L'),
	(265, 'PINARCAM VIVIENDA JOVEN, S.L.'),
	(266, 'PINARGES S.L.'),
	(267, 'PLAYA CARACOL, S.L.'),
	(268, 'PLAYA PARAISO MAYA SACV'),
	(269, 'POL INWEST'),
	(270, 'POLSAR CORPORATION,S.L'),
	(271, 'PORTUNA INVESTMENTS B.V'),
	(272, 'PRISOLES MEDITERRANEO, S.A'),
	(273, 'PROMOCIONES AL DESARROLLO BUMARI, S.L.'),
	(274, 'PROMOCIONES EL PEDRAZO'),
	(275, 'PROMOCIONES GUADAVILA, S.L.'),
	(276, 'PROMOCIONES LLANOS DE MASPALOMAS'),
	(277, 'PROMOCIONES PARCELA H1 DOMINICANA'),
	(278, 'PROMOCIONES Y PROPIEDADES ESPACIO-HABITAT, S.L.'),
	(279, 'PROMOMAR VALENCIA, S.L.'),
	(280, 'PROMOPUERTO 2006 SA'),
	(281, 'PROMOTORA DE VIVIENDAS LAMIRA, S.L.'),
	(282, 'PROMO-SERVEIS DAMSEL, SL'),
	(283, 'PROYECTO INMOBILIARIO VALIANT, S.L'),
	(284, 'PROYECTOS Y DESARROLLOS HISPANOMEXICANOS SACV'),
	(285, 'PRYGECAM ARROYOMOLINOS V.J., S.L.'),
	(286, 'PRYGECAM MÓSTOLES V.J., S.L.'),
	(287, 'RADION IBERKAT, S.L.'),
	(288, 'REALES ATARAZANAS'),
	(289, 'RENLOVI, S.L'),
	(290, 'RESIDENCIA FONTSANA, S.L'),
	(291, 'RESIDENCIAL ADEMUZ, S.L.'),
	(292, 'RESIDENCIAL CAN MARTORELL, S.L'),
	(293, 'RESIDENCIAL GOLF MAR, S.L.'),
	(294, 'RESIDENCIAL LA MAIMONA'),
	(295, 'RESIDENCIAL LLOMA DEL MAS, S.L.'),
	(296, 'RESIDENCIAL NAQUERA GOLF'),
	(297, 'RESIDENCIAL PARC CAN RATES, S.L.'),
	(298, 'RESIDENCIAL PLA DE SANT JOAN, S.L'),
	(299, 'RESTAURA INVERSIONS, S.L.'),
	(300, 'RESTAURA MARATON GARDENS Sp. zo.o.'),
	(301, 'RESTAURA NOWOGRODZKA, Sp.zo.o'),
	(302, 'RESTAURA WISLANA Sp. z o.o.'),
	(303, 'RIOJA ARAGÓN DESARROLLOS URBANÍSTICOS, S.A. (RIARSA)'),
	(304, 'RIVIERA MAYA INVESTMENTS, B.V.'),
	(305, 'ROYACTURA, S.L.'),
	(306, 'SALDAÑUELA RESIDENCIAL S.L.'),
	(307, 'SAN MIGUEL MAR URBANIZADORA, S.L.'),
	(308, 'OLESA BLAVA SL'),
	(309, 'SANTA POLA GREEN, S.L.'),
	(310, 'SANTA POLA LIFE RESORT'),
	(311, 'SAVOLYI TERMALCENTRUM, K.F.T.'),
	(312, 'SECTOR RESIDENCIAL LA MAIMONA'),
	(313, 'SEGOBRIDA DEL ERESMA S.A.'),
	(314, 'SEGOVIANA DE GESTION 2007 S.A.'),
	(315, 'SERTORIUM, S.L.'),
	(316, 'SHARE CAPITAL, S.L.'),
	(317, 'SHARE REAL ESTATE, K.F.T.'),
	(318, 'SHARE REAL ESTATE ROM, S.R.L.'),
	(319, 'SIERRA CORTINA 2000, S.L.'),
	(320, 'SUELABULA'),
	(321, 'TERINZA 26, S.L.'),
	(322, 'TERRENYS DE BEGUDA ALTA, S.L.'),
	(323, 'TERRENYS DEL PENEDES, S.L.'),
	(324, 'TEULAVER, S.L.'),
	(325, 'TORRELUGANO, S.L.'),
	(326, 'TORRENTO DE CAN GELAT, S.A'),
	(327, 'TRIANGULUM EUROTRUST, S.L'),
	(328, 'UNCRO S.L.'),
	(329, 'URABITAT RESIDENCIAL, S.L.'),
	(330, 'URBAN HABITAT, S.R.L'),
	(331, 'URBANISMO NUEVO SIGLO, S.L.'),
	(332, 'URBANIZADORA EXPERIENCIA INMOBILIARIA'),
	(333, 'URBANIZADORA FUENTE SAN LUIS, S.L.'),
	(334, 'URBANIZADORA LA VIÑA DEL MAR, S.L.'),
	(335, 'URBANIZADORA MARINA DE COPE, S.L.'),
	(336, 'URBANIZADORA PARQUE AZUL'),
	(337, 'URBANIZADORA TORREMAR, S.A.'),
	(338, 'URBILAND INVERSORA, S.L.U'),
	(339, 'VALDEMONTE PROYECTOS, S.L.'),
	(340, 'VALENCIA NATURA PARK, S.L.'),
	(341, 'VALENCIANA DE VIVIENDAS 2010, S.L.'),
	(342, 'VALLE CONDOMINA, S.L.'),
	(343, 'VALLE LEVANTE, S.L.'),
	(344, 'VALLE Y PAISAJE'),
	(345, 'VALLEMAR RESIDENCIAL, S.L.'),
	(346, 'VALMUR GESTION Y DESARROLLO URBANO'),
	(347, 'VARAMITRA REAL ESTATES, B.V.'),
	(348, 'VEGA CULLERA, S.L.'),
	(349, 'VEHICULO DE TENENCIA Y GESTIÓN 9, S.L.'),
	(350, 'VILADECAVALLS PARK, CENTRO INDUSTRIAL-LOGISTICO Y COM'),
	(351, 'VIMODESARROLLOS S.L'),
	(352, 'VIP CARTERA'),
	(353, 'VISTABELLA HABITAT'),
	(354, 'VISTAHERMOSA CIUDAD, S.L.'),
	(355, 'VIVIENDA JOVEN INTERBIGECO II, S.L.'),
	(356, 'VIVIENDA JOVEN INTERBIGECO, S.L.'),
	(357, 'VIVIENDAS DEL MEDITERRANEO ORVI, S.L.'),
	(358, 'VIVIENDAS EN ALQUILER DE MOSTOLES S.L.'),
	(359, 'VIVIENDAS SOCIALES DEL MEDITERRANEO, S.L.'),
	(360, 'XADAY PROYECTOS Y APLICACIONES, S.L'),
	(361, 'ZAPHIR LOGISTIC PROPERTIES, S.A.'),
	(362, 'BENETESA, S.A.'),
	(363, 'COMPLEJO CAPRI GAVA MAR, S.A.'),
	(364, 'FISSER INVERSIONES 2007, S.L.'),
	(365, 'TECNOPARC SANT CUGAT, S.L.'),
	(366, 'ZAPATER SANCHO, S.L.'),
	(367, 'INVERSIONES OCRE ROJO'),
	(368, 'VARALLO COMERCIAL'),
	(5000, 'PROYECTOS VIALTO S.L.'),
	(5001, 'CONSULTING 2001 INMOBILIARIA, S.L'),
	(5002, 'CRUNOR, SA'),
	(5003, 'GRUPO EMPRESARIAL CANALES, S.L.'),
	(5004, 'INMOLINCE, S.L.'),
	(5005, 'COMUNIDADES CASTELLANAS, SA'),
	(5006, 'ARCO 2000 TERRENOS Y EDIFICACIONES, SLU'),
	(5007, 'NORSIERRA PARTNERS'),
	(5008, 'RESIDENCIAL AJC SRL'),
	(5009, 'MARGON GESTIRED S L'),
	(5010, 'SAN SEGUNDO GRUPO EMPRESARIAL SL'),
	(5011, 'INBISA GRUPO EMPRESARIAL S.L.'),
	(5012, 'RESIDENCIAL AJC SRL'),
	(5013, 'PARQUE CENTRAL RIO PARK S.L.'),
	(5014, 'SERVICIOS INMOBILIARIOS DE PALENCIA S.L.'),
	(5015, 'GARCIPREA S.L.'),
	(5016, 'PRONORTE UNO, S.A.'),
	(5017, 'PROMOTORA INMOBILIARIA LOS JARDINES DE A.S.R.,  S.A.'),
	(5018, 'PROMOTORA INMOBILIARIA LA MEZQUITA S.A.'),
	(5019, 'BLASPERT, S.L.'),
	(5020, 'NEWAR- SIERRA ELVIRA UTE'),
	(5021, 'GESTESA DESARROLLOS URBANOS SL'),
	(5022, 'TRANSACCIONES INMOBILIARIAS RICARD, S.L.'),
	(5023, 'PROMOCIONES LLAMAS 03, SL.'),
	(5024, 'GRUPO INMOBILIARIO HISPANIA-EUROPA S.A.'),
	(5025, 'GRUPOMASDEMONT, S.L.'),
	(5026, 'LAS TERRAZAS DE ALHAURIN, S.A.'),
	(5027, 'CONSTRUCCIONES MARTÍ, S.A.'),
	(5028, 'CONSTRUCCIONES LANCEA, S.L.U.'),
	(5029, 'INMOBILIARIA GUADALMEDINA S.A'),
	(5030, 'HUERTOS Y JARDINES SANTA ANA, S.L.'),
	(5031, 'COORDINADORA DE PROYECTOS GENERALES, S.L.'),
	(5032, 'PROMOCIONES Y CONSTRUCCIONES PERCAN, S.L.'),
	(5033, 'INMUEBLES ATLANTE, S.L.'),
	(5034, 'GRUPO INMOBILIARIO DELTA, S.A.'),
	(5035, 'PROMOCIONES LIMITE MAXIMO S.L.'),
	(5036, 'PROMOCIONES SOLMAR 2001, S.L.'),
	(5037, 'TORRECABAÑAS, S.L.'),
	(5038, 'ÁREAS DE CONSTRUCCIÓN Y PROMOCIÓN LEVEL, S.L.'),
	(5039, 'NARRO PRO-INM, S.L.'),
	(5040, 'URBAZO'),
	(5041, 'EL VEROL PANORÁMICA, S.L.'),
	(5042, 'JUMÓN, S.A.'),
	(5043, 'INVERSIONES RIFOSA, S.L.'),
	(5044, 'GOMSERRA, S.L.'),
	(5045, 'TETUANONCE, S.L.'),
	(5046, 'PROMOCIONES OLIVERAS 2006, S.L.'),
	(5047, 'PROSILCA, S.L.'),
	(5048, 'PIEDRAS DEL PIRINEO, S.A.'),
	(5049, 'GESTION DE ACTIVOS CASTELLANA 40 S.L.'),
	(5050, 'INVERSIONES MARINA DE CASARES'),
	(5051, 'SERVICIOS TURÍSTICOS LYNCIS, S.L.'),
	(5052, 'DANTE PUERTO, S.L.'),
	(5053, 'PROMOSASTRE, S.L.'),
	(5054, 'CONSTRUCCIONES CARDÍN Y LUENGO, S.L.'),
	(5055, 'DARSENA PATRIMONIOS, SL'),
	(5056, 'PROURBANORTE, S.L.'),
	(5057, 'CONSTRUCCIONES SALDAÑA, S.A.'),
	(5058, 'PROMOCION Y GESTION PROYGE, S.A.'),
	(5059, 'EDIFICIOS ALBACETE, S.L.'),
	(5060, 'ARQUITECTURAS GONZALEZ, S.L.'),
	(5061, 'CONSTRUCCIONES, EDIFICACIONES E INMUEBLES, S.A'),
	(5062, 'PROMELCAN XX, S.L.'),
	(5063, 'KEY MARE INVERSIONES Y PARTICIPACIONES, S.L.'),
	(5064, 'PALGC-00, S.L.'),
	(5065, 'ALZA RESIDENCIAL, S.L.'),
	(5066, 'UTE TAMARÁN ARQUITECTOS, S.L. Y LA BOCANA 4 INVERSION'),
	(5067, 'MEDI PROYECTOS INMOBILIARIOS, S.L.'),
	(5068, 'AM 70 S.A.'),
	(5069, 'CAN TAMARINDO, S.A.'),
	(5070, 'PROMOCIONES ESLAZA, S.L.'),
	(5071, 'INVERHAUS XXI, S.L.'),
	(5072, 'PROMOCIONES LA FUENTE DE PARLA, S.L.'),
	(5073, 'LARDERO 2001, S.L.'),
	(5074, 'GOLF RIOJA ALTA, S.L.'),
	(5075, 'INMOBILIARIA OSUNA, S.L.U.'),
	(5076, 'GRUPO DE INVERSIONES NOGA, S.L.U.'),
	(5077, 'INOSA, S.L.U.'),
	(5078, 'ALMONEDA GALICIA,, S.L'),
	(5079, 'GRUPO INMOBILIARIO ALYPE, S.L'),
	(5080, 'ATENAS REALIZACIONES INMOBILIARIAS, S.L.'),
	(5081, 'BELTRAN ZANGRONIZ PROMOCIONES, S.L'),
	(5082, 'CONSTRUCCIONES COOMONTE, S.L'),
	(5083, 'EDIFICACIONES M.C. CANTON, S.A.'),
	(5084, 'URBE RIOJA, S.L.'),
	(5085, 'EL NARANJAL DEL CASTILLO, S.L.'),
	(5086, 'DOSERVAL, S.L.'),
	(5087, 'PROCAMUR, S.L.'),
	(5088, 'VALLEHERMOSO DIVISIÓN PROMOCIÓN, S.A.U.'),
	(5089, 'DESARROLLOS URBANÍSTICOS ENSANCHE, S.L.'),
	(5090, 'J.F.L. DORLE, S.L.'),
	(5091, 'REGAL XXI, S.L.'),
	(5092, 'ASOCIACION PROMOCIONAL ASTURIANA, S.L.'),
	(5093, 'MONTHISA RESIDENCIAL, S.A.'),
	(5094, 'TOBRASA, SA'),
	(5095, 'PRORADA TERUEL, S.L.'),
	(5096, 'SUELO XXI S.A.'),
	(5097, 'BR DISEÑOS Y PROMOCIONES S.L.'),
	(5098, 'HERCESA INMOBILIARIA, S.A.'),
	(5099, 'URBAGARRY, S.L.'),
	(9999, 'SAREB');
/*!40000 ALTER TABLE `cospat_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.costli_tbl
CREATE TABLE IF NOT EXISTS `costli_tbl` (
  `costli_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`costli_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.costli_tbl: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `costli_tbl` DISABLE KEYS */;
INSERT INTO `costli_tbl` (`costli_id`, `descripcion`) VALUES
	(0, 'SIN INICIAR TRAMITES'),
	(1, 'AUTO O TITULO LIQUIDADO E INSCRITO'),
	(2, 'PENDIENTE LIQUIDACION IMPUESTOS A HACIENDA'),
	(3, 'PENDIENTE DEVOLUCION TITULO INSCRITO'),
	(4, 'AUTO NULO, TITULO NO INSCRIBIBLE'),
	(5, 'SUBSANACION DEFECTOS DE INSCRIPCION'),
	(6, 'AUTO EN EL PROCURADOR PARA ADICIONAR'),
	(7, 'PENDIENTE PRESENTACION EN EL REGISTRO'),
	(8, 'AUTO O TITULO SIN POSIBILIDAD DE INSCRIPCION');
/*!40000 ALTER TABLE `costli_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cotdor_e1_tbl
CREATE TABLE IF NOT EXISTS `cotdor_e1_tbl` (
  `cotdor_id` smallint(3) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(90) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cotdor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cotdor_e1_tbl: ~32 rows (aproximadamente)
/*!40000 ALTER TABLE `cotdor_e1_tbl` DISABLE KEYS */;
INSERT INTO `cotdor_e1_tbl` (`cotdor_id`, `descripcion`) VALUES
	(0, 'CORRECTO, SIN ERRORES'),
	(1, 'CODIGO DE ACCION DEBE SER A,M,B,X,E'),
	(2, 'NO EXISTE LA ENTIDAD'),
	(3, 'NO EXISTE EL ACTIVO'),
	(4, 'CIF DE LA COMUNIDAD NO PUEDE SER BLANCO, NULO O ZEROS'),
	(5, 'NO TIENE NOMBRE LA COMUNIDAD'),
	(6, 'FALTAN DATOS DE LA CUENTA BANCARIA'),
	(7, 'CONTROL OBSERVACIONES DEBE SER A,M,B'),
	(8, 'EL ACTIVO EXISTE EN OTRA COMUNIDAD'),
	(9, 'YA EXISTE ESTA COMUNIDAD'),
	(10, 'EL ACTIVO YA EXISTE PARA ESTA COMUNIDAD'),
	(11, 'LA COMUNIDAD NO EXISTE. ACTIVO NO SE PUEDE DAR DE ALTA'),
	(12, 'LA COMUNIDAD NO EXISTE. NO SE PUEDE MODIFICAR'),
	(13, 'EL NOMBRE DE LA COMUNIDAD TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(14, 'DIRECCION MAIL COMUNIDAD TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(15, 'NOMBRE PRESIDENTE COMUNIDAD TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(16, 'Nº TEL PRESIDENTE COMUNIDAD TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(17, 'NOMBRE ADMINISTRADOR COMUNIDAD TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(18, 'Nº TEL ADMINISTRADOR COMUNIDAD TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(19, 'MAIL ADMINISTRADOR COMUNIDAD TIENE DATOS Y SU CAMPO DE  CONTROL N O ES S'),
	(20, 'TIENE DATOS DE LA CUENTA Y SU CAMPO DE CONTROL NO ES S'),
	(21, 'ACTIVO TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(22, 'NO SE PUEDE DAR ALTA SI CONTROL DE ACTIVO NO ES S'),
	(23, 'NO SE PUEDE DAR DE ALTA EL TEXTO PORQUE YA EXITE TEXTO'),
	(24, 'NO SE PUEDE ACTUALIZAR EL TEXTO PORQUE NO EXISTE'),
	(25, 'NO SE PUEDE ELIMINAR EL TEXTO PORQUE NO EXISTE'),
	(26, 'LA COMUNIDAD NO EXISTE, NO SE PUEDE DAR DE BAJA'),
	(27, 'NO SE PUEDE DAR DE BAJA LA COMUNIDAD PORQUE TIENE CUOTAS'),
	(28, 'EL CODIGO DE TRANSACCION DEBE SER EE41'),
	(29, 'EL CODIGO DEL EMISOR IDPROV DEBE EXISTIR EN AE51'),
	(30, 'LA CLASE DE DOCUMENTO DEBE SER UN CIF (2,5,J)'),
	(31, 'NUMERO DE DOCUMENTO CIF ERRONEO');
/*!40000 ALTER TABLE `cotdor_e1_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cotdor_e2_tbl
CREATE TABLE IF NOT EXISTS `cotdor_e2_tbl` (
  `cotdor_id` smallint(3) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(90) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cotdor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cotdor_e2_tbl: ~28 rows (aproximadamente)
/*!40000 ALTER TABLE `cotdor_e2_tbl` DISABLE KEYS */;
INSERT INTO `cotdor_e2_tbl` (`cotdor_id`, `descripcion`) VALUES
	(0, 'CORRECTO, SIN ERRORES'),
	(1, 'CODIGO DE ACCION DEBE SER A,M,B'),
	(2, 'NO EXISTE LA ENTIDAD'),
	(3, 'NO EXISTE EL ACTIVO'),
	(4, 'CIF DE LA COMUNIDAD NO PUEDE SER BLANCO O NULO'),
	(7, 'OBSERVACIONES DEBE SER A,M,B'),
	(23, 'NO SE PUEDE DAR DE ALTA EL TEXTO PORQUE YA EXITE TEXTO'),
	(24, 'NO SE PUEDE ACTUALIZAR EL TEXTO PORQUE NO EXISTE'),
	(25, 'NO SE PUEDE ELIMINAR EL TEXTO PORQUE NO EXISTE'),
	(28, 'EL CODIGO DE TRANSACCION DEBE SER EE42'),
	(29, 'EL CODIGO DEL EMISOR IDPROV DEBE EXISTIR EN AE51'),
	(30, 'EL GRUPO DE GASTO DEBE SER 2'),
	(31, 'EL TIPO DE ACCION DEBE SER 2'),
	(32, 'EL SUBTIPO DE ACCION NO EXISTE'),
	(33, 'LA FECHA DE PRIMER PAGO DEBE SER LOGICA Y OBLIGATORIA'),
	(34, 'LA FECHA DE ULTIMO PAGO DEBE SER LOGICA Y OBLIGATORIA'),
	(35, 'LA FECHA DE ULTIMO PAGO DEBE SER MAYOR QUE LA FECHA DE PRIMER PAGO'),
	(36, 'IMPORTE DE CUOTA TIENE QUE SER MAYOR DE CERO'),
	(37, 'FECHA 1º PAGO TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(38, 'FECHA ULTIMO PAGO TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(39, 'EL IMPORTE CUOTA TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(40, 'FECHA DEL ACTA TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(41, 'LA COMUNIDAD NO EXISTE EN LA TABLA DE COMUNIDADES GMAE10'),
	(42, 'LA RELACION ACTIVO-COMUNIDAD YA EXISTE EN GMAE12. NO SE PUEDE REALIZAR EL ALTA'),
	(43, 'LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA MODIFICACION'),
	(44, 'NO EXISTE PERIOCIDAD DE PAGO'),
	(45, 'LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA BAJA'),
	(46, 'LA FECHA DEL ACTA DEBE SER LOGICA Y OBLIGATORIA');
/*!40000 ALTER TABLE `cotdor_e2_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cotdor_e3_tbl
CREATE TABLE IF NOT EXISTS `cotdor_e3_tbl` (
  `cotdor_id` smallint(3) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(90) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cotdor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cotdor_e3_tbl: ~18 rows (aproximadamente)
/*!40000 ALTER TABLE `cotdor_e3_tbl` DISABLE KEYS */;
INSERT INTO `cotdor_e3_tbl` (`cotdor_id`, `descripcion`) VALUES
	(0, 'CORRECTO, SIN ERRORES'),
	(1, 'CODIGO DE ACCION DEBE SER A,M,B'),
	(2, 'NO EXISTE LA ENTIDAD'),
	(3, 'NO EXISTE EL ACTIVO'),
	(7, 'CONTROL OBSERVACIONES DEBE SER A,M,B'),
	(23, 'NO SE PUEDE DAR DE ALTA EL TEXTO PORQUE YA EXISTE TEXTO'),
	(24, 'NO SE PUEDE ACTUALIZAR EL TEXTO PORQUE NO EXISTE'),
	(25, 'NO SE PUEDE ELIMINAR EL TEXTO PORQUE NO EXISTE'),
	(28, 'EL CODIGO DE TRANSACCION DEBE SER EE43'),
	(29, 'EL CODIGO DEL EMISOR IDPROV DEBE EXISTIR EN AE51'),
	(47, 'TITULAR CATASTRAL TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(48, 'ENTIDAD EMISORA IMPUESTOS TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(49, 'LA REFERENCIA CATASTRAL YA EXISTE NO SE PUEDE DAR DE ALTA'),
	(50, 'LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE MODIFICAR'),
	(51, 'LA REFERENCIA CATASTRAL NO EXISTE NO SE PUEDE DAR DE BAJA'),
	(52, 'TITULAR CATASTRAL OBLIGATORIO. NO SE PUEDE DAR DE ALTA'),
	(53, 'EXISTEN DATOS EN GMAE57. NO SE PUEDE REALIZAR LA BAJA'),
	(54, 'LA REFERENCIA CATASTRAL ES OBLIGATORIA');
/*!40000 ALTER TABLE `cotdor_e3_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cotdor_e4_tbl
CREATE TABLE IF NOT EXISTS `cotdor_e4_tbl` (
  `cotdor_id` smallint(3) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(90) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cotdor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cotdor_e4_tbl: ~41 rows (aproximadamente)
/*!40000 ALTER TABLE `cotdor_e4_tbl` DISABLE KEYS */;
INSERT INTO `cotdor_e4_tbl` (`cotdor_id`, `descripcion`) VALUES
	(0, 'CORRECTO, SIN ERRORES'),
	(1, 'CODIGO DE ACCION DEBE SER A,M,B'),
	(2, 'NO EXISTE LA ENTIDAD'),
	(3, 'NO EXISTE EL ACTIVO'),
	(7, 'OBSERVACIONES DEBE SER A,M,B'),
	(23, 'NO SE PUEDE DAR DE ALTA EL TEXTO PORQUE YA EXITE TEXTO'),
	(24, 'NO SE PUEDE ACTUALIZAR EL TEXTO PORQUE NO EXISTE'),
	(25, 'NO SE PUEDE ELIMINAR EL TEXTO PORQUE NO EXISTE'),
	(28, 'EL CODIGO DE TRANSACCION DEBE SER EE44'),
	(29, 'EL CODIGO DEL EMISOR IDPROV DEBE EXISTIR EN AE51'),
	(30, 'EL GRUPO DE GASTO DEBE SER 2'),
	(31, 'EL TIPO DE ACCION DEBE SER 2'),
	(32, 'EL SUBTIPO DE ACCION NO EXISTE'),
	(54, 'LA REFERENCIA CATASTRAL ES OBLIGATORIA'),
	(55, 'LA FECHA PRESENTACION DE RECURSO DEBE SER LOGICA Y OBLIGATORIA'),
	(56, 'LA FECHA RESOLUCION RECURSO DEBE SER LOGICA Y OBLIGATORIA'),
	(57, 'LA FECHA DEVOLUCION INGRESO DEBE SER LOGICA Y OBLIGATORIA'),
	(58, 'FECHA PRESENTACION RECURSO TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(59, 'FECHA RESOLUCION RECURSO TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(60, 'FECHA DEVOLUCION INGRESO TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(61, 'NO SE PUEDE REALIZAR EL ALTA PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13'),
	(62, 'INDICADOR SOLICITUD DEVOLUCION DEBE SER S,N'),
	(63, 'INDICADOR RESOLUCION DEBE SER F,D,BLANCO'),
	(64, 'NO SE PUEDE REALIZAR EL ALTA PORQUE YA EXISTE EL REGISTRO EN GMAE57'),
	(66, 'NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE EL REGISTRO EN GMAE57'),
	(67, 'NO SE PUEDE ACTUALIZAR PORQUE NO EXISTE REFERENCIA CATASTRAL EN GMAE13'),
	(68, 'NO SE PUEDE ELIMINAR PORQUE NO EXISTE REGISTRO EN GMAE57'),
	(69, 'INDICADOR SOLICITUD DEVOLUCION TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(70, 'INDICADOR RESOLUCION TIENE DATOS Y SU CAMPO DE CONTROL NO ES S'),
	(101, 'TIENE F.PRESENTACION, TIPO RESOLUCION Y NO F.RESOLUCION'),
	(102, 'TIENE F.PRESENTACION, F.RESOLUCION Y NO TIPO RESOLUCION'),
	(103, 'NO TIENE F.PRESENTACION Y SI TIPO RESOLUCION'),
	(104, 'NO TIENE F.PRESENTACION, TIPO RESOLUCION Y SI F.RESOLUCION'),
	(105, 'NO TIENE S.DEVOLUCION, Y SI F.DEVOLUCION'),
	(106, 'NO TIENE F.PRESENTACION, Y SI F.DEVOLUCION'),
	(107, 'NO TIENE TIPO RESOLUCION, Y SI F.DEVOLUCION'),
	(108, 'NO TIENE F.PRESENTACION, Y SI S.DEVOLUCION'),
	(109, 'EL TIPO RESOLUCION ES DESFAVORABLE Y TIENE F.DEVOLUCION'),
	(110, 'LA F.RESOLUCION ES MENOR A LA F.PRESENTACION'),
	(111, 'LA F.DEVOLUCION ES MENOR A LA F.PRESENTACION'),
	(112, 'LA F.DEVOLUCION ES MENOR A LA F.RESOLUCION');
/*!40000 ALTER TABLE `cotdor_e4_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coterr_tbl
CREATE TABLE IF NOT EXISTS `coterr_tbl` (
  `coterr_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(400) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coterr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coterr_tbl: ~39 rows (aproximadamente)
/*!40000 ALTER TABLE `coterr_tbl` DISABLE KEYS */;
INSERT INTO `coterr_tbl` (`coterr_id`, `descripcion`) VALUES
	(0, 'Correcto, sin errores'),
	(1, 'Agrupación no es del tipo provisión de fondos'),
	(2, 'Llega fecha de anulación y no existe gasto en la tabla'),
	(3, 'Llega un abono de un gasto que NO está pagado'),
	(4, 'Descuento mayor que importe nominal del gasto'),
	(5, 'Retorno del servidor erróneo'),
	(6, 'La provisión ya está cerrada'),
	(7, 'Error en grupo / tipo / subtipo de acción'),
	(8, 'No existe el activo en la base corporativa'),
	(9, 'Periodicidad varios periodos y no hay fecha fin periodo'),
	(10, 'Periodicidad <> varios periodos y llega fecha fin periodo'),
	(11, 'Llega un abono de un gasto con distintos valores en fichero de entrada y tabla de gastos'),
	(12, 'Llega un abono de un gasto que está anulado'),
	(13, 'Llega un abono de un gasto que ya está abonado, o bien está en la misma provisión sin anular.'),
	(14, 'CONTACTAR CON INFORMATICA'),
	(15, 'CONTACTAR CON INFORMATICA'),
	(16, 'CONTACTAR CON INFORMATICA'),
	(17, 'CONTACTAR CON INFORMATICA'),
	(18, 'Llega un gasto en una provisión y ya existe sin anular en otra provisión.'),
	(19, 'Periodicidad del gasto es cero o espacios'),
	(20, 'Error DB2 en acceso a la tabla de Activos'),
	(21, 'Error DB2 en acceso a la tabla de Gastos'),
	(22, 'Error DB2 en la tabla de relación gasto / provisión'),
	(23, 'Llega anulación de un gasto que YA está pagado'),
	(24, 'Llega modificación de un gasto que YA está pagado'),
	(25, 'Se trata de una anulación y los datos de la tabla son distintos de los datos del fichero de entrada'),
	(26, 'Llega un gasto sin provisión y en la tabla si la tiene'),
	(27, 'Llega una anulación de un gasto de una provisión y en la tabla está el gasto sin provisión.'),
	(28, 'Código de moneda invalido.'),
	(40, 'El importe del impuesto no se ajusta a ningún tipo de impuesto válido.'),
	(61, 'La provisión ya está cerrada pero se ha actualizado la fecha de pago a proveedor.'),
	(62, 'Llega una devolución con importe positivo.'),
	(63, 'Llega una devolución sin número de provisión.'),
	(64, 'Faltan datos de la CONEXIÓN'),
	(65, 'Gasto por CONEXIÓN sin provisión'),
	(66, 'Gasto de Entidad distinta al resto de la provisión'),
	(67, 'La sociedad patrimonial del Activo no se encuentra en la lista de sociedades patrimoniales que se pueden facturar.'),
	(68, 'Los gastos de Activos con Sociedad Patrimonial titulizada tienen que ir agrupados en provisiones exclusivas por Sociedad Patrimonial.'),
	(69, 'Los gastos de Activos a facturar a Sareb, deben de agruparse a nivel de provisión además de por la entidad por el tipo de Activo, según la clasificación de Arrendados, Suelos y/o obra en curso y Producto acabado.');
/*!40000 ALTER TABLE `coterr_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cotneg_tbl
CREATE TABLE IF NOT EXISTS `cotneg_tbl` (
  `cotneg_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cotneg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cotneg_tbl: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `cotneg_tbl` DISABLE KEYS */;
INSERT INTO `cotneg_tbl` (`cotneg_id`, `descripcion`) VALUES
	(0, 'SIN INFORMAR'),
	(1, 'CLIENTE'),
	(2, 'CAJA');
/*!40000 ALTER TABLE `cotneg_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cotpet_tbl
CREATE TABLE IF NOT EXISTS `cotpet_tbl` (
  `cotpet_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cotpet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cotpet_tbl: ~28 rows (aproximadamente)
/*!40000 ALTER TABLE `cotpet_tbl` DISABLE KEYS */;
INSERT INTO `cotpet_tbl` (`cotpet_id`, `descripcion`) VALUES
	(0, 'NO INFORMADO'),
	(1, 'LLAVES'),
	(2, 'TITULARES'),
	(3, 'IMPUESTOS'),
	(4, 'FACTURA PROFORMA'),
	(5, 'OTROS'),
	(6, 'TITULO ORIGINAL'),
	(10, 'NOTA SIMPLE'),
	(11, 'NOTA EXTENSA'),
	(12, 'CERTIFICACION REGISTRAL'),
	(20, 'INSCRIPCION TITULO'),
	(21, 'CANCELACION CARGAS'),
	(22, 'POSESION'),
	(23, 'EMISION AUTO'),
	(30, 'ARRENDAMIENTOS'),
	(40, 'CERTIFICADO FIN DE OBRA'),
	(41, 'CEDULA HABITABILIDAD'),
	(42, 'LICENCIA'),
	(43, 'NATURALEZA DEL ACTIVO'),
	(44, 'ESTADO CONSTRUCTIVO'),
	(45, 'DEUDAS COMUNIDAD'),
	(46, 'DEUDAS IMPUESTOS'),
	(47, 'PLUSVALIA'),
	(48, 'TRAMITE DE FIRMAS'),
	(50, 'ASESORAMIENTO FISCAL'),
	(51, 'ASESORAMIENTO JURIDICO'),
	(98, 'TASACIONES'),
	(99, 'TRAMITE GENERICO');
/*!40000 ALTER TABLE `cotpet_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cotpga_g1_tbl
CREATE TABLE IF NOT EXISTS `cotpga_g1_tbl` (
  `cotpga_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cotpga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cotpga_g1_tbl: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `cotpga_g1_tbl` DISABLE KEYS */;
INSERT INTO `cotpga_g1_tbl` (`cotpga_id`, `descripcion`) VALUES
	(1, 'Plusvalia'),
	(2, 'Notaría');
/*!40000 ALTER TABLE `cotpga_g1_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cotpga_g2_tbl
CREATE TABLE IF NOT EXISTS `cotpga_g2_tbl` (
  `cotpga_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cotpga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cotpga_g2_tbl: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `cotpga_g2_tbl` DISABLE KEYS */;
INSERT INTO `cotpga_g2_tbl` (`cotpga_id`, `descripcion`) VALUES
	(1, 'Tasas/Impuestos'),
	(2, 'Comunidades'),
	(3, 'Suministros');
/*!40000 ALTER TABLE `cotpga_g2_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cotpga_g3_tbl
CREATE TABLE IF NOT EXISTS `cotpga_g3_tbl` (
  `cotpga_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cotpga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cotpga_g3_tbl: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `cotpga_g3_tbl` DISABLE KEYS */;
INSERT INTO `cotpga_g3_tbl` (`cotpga_id`, `descripcion`) VALUES
	(2, 'Honorarios'),
	(3, 'Obtención de Licencias');
/*!40000 ALTER TABLE `cotpga_g3_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.cotsin_tbl
CREATE TABLE IF NOT EXISTS `cotsin_tbl` (
  `cotsin_id` varchar(4) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cotsin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.cotsin_tbl: ~29 rows (aproximadamente)
/*!40000 ALTER TABLE `cotsin_tbl` DISABLE KEYS */;
INSERT INTO `cotsin_tbl` (`cotsin_id`, `descripcion`) VALUES
	('CO01', 'COMERCIAL LOCAL'),
	('CO02', 'COMERCIAL OFICINA'),
	('CO03', 'COMERCIAL ALMACEN'),
	('ED01', 'EDIFICIO/COLECTIVO RESIDENCIAL'),
	('ED02', 'EDIFICIO/COLECTIVO COMERCIAL'),
	('ED03', 'EDIFICIO/COLECTIVO INDUSTRIAL'),
	('ED04', 'EDIFICIO/COLECTIVO GARAJE'),
	('ED05', 'EDIFICIO/COLECTIVO DOTACIONAL'),
	('ED06', 'EDIFICIO/COLECTIVO HOTELERO'),
	('ED07', 'EDIFICIO/COLECTIVO OFICINAS'),
	('IN01', 'INDUSTRIAL NAVE ADOSADA'),
	('IN02', 'INDUSTRIAL NAVE AISLADA'),
	('IN03', 'INDUSTRIAL NAVE EN EDIFICIO INDUSTRIAL'),
	('IN04', 'INDUSTRIAL NAVE EN VARIAS PLANTAS'),
	('OT01', 'VARIOS APARCAMIENTO'),
	('OT02', 'VARIOS GARAJE'),
	('OT03', 'VARIOS TRASTERO'),
	('OT04', 'VARIOS REGIMEN HOTELERO'),
	('OT05', 'VARIOS OTROS'),
	('SU01', 'SUELOS NO URBANIZABLE'),
	('SU02', 'SUELOS URBANIZABLE NO PROGRAMADO'),
	('SU03', 'SUELOS URBANIZABLE PROGRAMADO'),
	('SU04', 'SUELOS URBANO/SOLAR/PARCELA'),
	('VI00', '--Pendiente--'),
	('VI01', 'VIVIENDA PISO'),
	('VI02', 'VIVIENDA PISO DUPLEX'),
	('VI03', 'VIVIENDA CHALET AISLADO'),
	('VI04', 'VIVIENDA CHALET ADOSADO'),
	('VI05', 'VIVIENDA CASA UNIFAMILIAR');
/*!40000 ALTER TABLE `cotsin_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coxpro_tbl
CREATE TABLE IF NOT EXISTS `coxpro_tbl` (
  `coxpro_id` mediumint(5) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coxpro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coxpro_tbl: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `coxpro_tbl` DISABLE KEYS */;
INSERT INTO `coxpro_tbl` (`coxpro_id`, `descripcion`) VALUES
	(0, 'NO INFORMADO'),
	(1, 'V.P.O.'),
	(2, 'V.P.O. ANT. 78'),
	(3, 'V.P.O. PRIVADA'),
	(4, 'V.P.O. PUBLICA'),
	(5, 'VENTA LIBRE'),
	(6, 'V.P.T.'),
	(7, 'SUBVENCIONADA');
/*!40000 ALTER TABLE `coxpro_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.coxsia_tbl
CREATE TABLE IF NOT EXISTS `coxsia_tbl` (
  `coxsia_id` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`coxsia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.coxsia_tbl: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `coxsia_tbl` DISABLE KEYS */;
INSERT INTO `coxsia_tbl` (`coxsia_id`, `descripcion`) VALUES
	(0, 'SIN INFORMACION'),
	(1, 'AUTO FIRME'),
	(2, 'AUTO NO FIRME');
/*!40000 ALTER TABLE `coxsia_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.e1_comunidades_tbl
CREATE TABLE IF NOT EXISTS `e1_comunidades_tbl` (
  `cod_codtrn` varchar(4) COLLATE latin1_spanish_ci NOT NULL,
  `cod_cotdor` smallint(3) unsigned NOT NULL,
  `idprov` int(9) unsigned NOT NULL,
  `cod_coacci` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `coengp` mediumint(5) unsigned NOT NULL,
  `cod_cocldo` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `nudcom_id` varchar(10) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc10` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc01` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `nomcoc` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc02` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `nodcco` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc03` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `nomprc` varchar(55) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc04` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `nutprc` varchar(14) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc05` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `nomadc` varchar(55) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc06` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `nutadc` varchar(14) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc07` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `nodcad` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc08` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `nuccen` smallint(4) unsigned NOT NULL,
  `nuccof` smallint(4) unsigned NOT NULL,
  `nuccdi` tinyint(2) unsigned NOT NULL,
  `nuccnt` bigint(10) unsigned NOT NULL,
  `cod_bitc09` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `obtexc` varchar(110) COLLATE latin1_spanish_ci NOT NULL,
  `obdeer` varchar(80) COLLATE latin1_spanish_ci NOT NULL,
  `cod_validado` char(1) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`nudcom_id`),
  KEY `cod_codtrn` (`cod_codtrn`),
  KEY `cod_cotdor` (`cod_cotdor`),
  KEY `cod_coacci` (`cod_coacci`),
  KEY `cod_cocldo` (`cod_cocldo`),
  KEY `cod_bitc10` (`cod_bitc10`),
  KEY `cod_bitc01` (`cod_bitc01`),
  KEY `cod_bitc02` (`cod_bitc02`),
  KEY `cod_bitc03` (`cod_bitc03`),
  KEY `cod_bitc04` (`cod_bitc04`),
  KEY `cod_bitc05` (`cod_bitc05`),
  KEY `cod_bitc06` (`cod_bitc06`),
  KEY `cod_bitc07` (`cod_bitc07`),
  KEY `cod_bitc08` (`cod_bitc08`),
  KEY `cod_bitc09` (`cod_bitc09`),
  KEY `cod_validado` (`cod_validado`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_1` FOREIGN KEY (`cod_codtrn`) REFERENCES `codtrn_tbl` (`codtrn_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_2` FOREIGN KEY (`cod_cotdor`) REFERENCES `cotdor_e1_tbl` (`cotdor_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_3` FOREIGN KEY (`cod_coacci`) REFERENCES `coacci_e1_tbl` (`coacci_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_4` FOREIGN KEY (`cod_cocldo`) REFERENCES `cocldo_tbl` (`cocldo_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_5` FOREIGN KEY (`cod_bitc10`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_6` FOREIGN KEY (`cod_bitc01`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_7` FOREIGN KEY (`cod_bitc02`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_8` FOREIGN KEY (`cod_bitc03`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_9` FOREIGN KEY (`cod_bitc04`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_10` FOREIGN KEY (`cod_bitc05`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_11` FOREIGN KEY (`cod_bitc06`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_12` FOREIGN KEY (`cod_bitc07`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_13` FOREIGN KEY (`cod_bitc08`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_14` FOREIGN KEY (`cod_bitc09`) REFERENCES `ternaria_tbl` (`ternaria_id`),
  CONSTRAINT `e1_comunidades_tbl_ibfk_15` FOREIGN KEY (`cod_validado`) REFERENCES `validacion_datos_tbl` (`validado_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.e1_comunidades_tbl: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `e1_comunidades_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `e1_comunidades_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.e2_cuotas_tbl
CREATE TABLE IF NOT EXISTS `e2_cuotas_tbl` (
  `e2_cuotas_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cod_codtrn` varchar(4) COLLATE latin1_spanish_ci NOT NULL,
  `cod_cotdor` smallint(3) unsigned NOT NULL,
  `idprov` varchar(9) COLLATE latin1_spanish_ci NOT NULL,
  `cod_coacci` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cod_cocldo` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `nudcom` varchar(10) COLLATE latin1_spanish_ci NOT NULL,
  `coengp` varchar(5) COLLATE latin1_spanish_ci NOT NULL,
  `cod_coaces` int(9) unsigned NOT NULL,
  `cogrug` tinyint(2) unsigned NOT NULL,
  `cotaca` tinyint(2) unsigned NOT NULL,
  `cod_cosbac` tinyint(2) unsigned NOT NULL,
  `cod_bitc11` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `fipago` int(8) unsigned NOT NULL,
  `cod_bitc12` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `ffpago` int(8) unsigned NOT NULL,
  `cod_bitc13` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `imcuco` bigint(15) unsigned NOT NULL,
  `cod_bitc14` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `faacta` int(8) unsigned NOT NULL,
  `cod_bitc15` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `ptpago` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc09` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `obtexc` varchar(110) COLLATE latin1_spanish_ci NOT NULL,
  `obdeer` varchar(80) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`e2_cuotas_id`),
  KEY `cod_codtrn` (`cod_codtrn`),
  KEY `cod_cotdor` (`cod_cotdor`),
  KEY `cod_coacci` (`cod_coacci`),
  KEY `cod_cocldo` (`cod_cocldo`),
  KEY `cod_coaces` (`cod_coaces`),
  KEY `cod_cosbac` (`cod_cosbac`),
  KEY `cod_bitc11` (`cod_bitc11`),
  KEY `cod_bitc12` (`cod_bitc12`),
  KEY `cod_bitc13` (`cod_bitc13`),
  KEY `cod_bitc14` (`cod_bitc14`),
  KEY `cod_bitc15` (`cod_bitc15`),
  KEY `cod_bitc09` (`cod_bitc09`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_1` FOREIGN KEY (`cod_codtrn`) REFERENCES `codtrn_tbl` (`codtrn_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_2` FOREIGN KEY (`cod_cotdor`) REFERENCES `cotdor_e2_tbl` (`cotdor_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_3` FOREIGN KEY (`cod_coacci`) REFERENCES `coacci_e2_tbl` (`coacci_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_4` FOREIGN KEY (`cod_cocldo`) REFERENCES `cocldo_tbl` (`cocldo_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_5` FOREIGN KEY (`cod_coaces`) REFERENCES `ac_activos_tbl` (`activo_coaces_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_6` FOREIGN KEY (`cod_cosbac`) REFERENCES `cosbga_t22_tbl` (`cosbga_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_7` FOREIGN KEY (`cod_bitc11`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_8` FOREIGN KEY (`cod_bitc12`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_9` FOREIGN KEY (`cod_bitc13`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_10` FOREIGN KEY (`cod_bitc14`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_11` FOREIGN KEY (`cod_bitc15`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e2_cuotas_tbl_ibfk_12` FOREIGN KEY (`cod_bitc09`) REFERENCES `ternaria_tbl` (`ternaria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.e2_cuotas_tbl: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `e2_cuotas_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `e2_cuotas_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.e3_referencias_tbl
CREATE TABLE IF NOT EXISTS `e3_referencias_tbl` (
  `cod_codtrn` varchar(4) COLLATE latin1_spanish_ci NOT NULL,
  `cod_cotdor` smallint(3) unsigned NOT NULL,
  `idprov` int(9) unsigned NOT NULL,
  `cod_coacci` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `coengp` mediumint(5) unsigned NOT NULL,
  `nurcat_id` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc16` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `tircat` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc17` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `enemis` varchar(60) COLLATE latin1_spanish_ci NOT NULL,
  `cotexa` bigint(10) unsigned NOT NULL,
  `cod_bitc09` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `obtexc` varchar(110) COLLATE latin1_spanish_ci NOT NULL,
  `obdeer` varchar(80) COLLATE latin1_spanish_ci NOT NULL,
  `cod_validado` char(1) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`nurcat_id`),
  KEY `cod_codtrn` (`cod_codtrn`),
  KEY `cod_cotdor` (`cod_cotdor`),
  KEY `cod_coacci` (`cod_coacci`),
  KEY `cod_bitc16` (`cod_bitc16`),
  KEY `cod_bitc17` (`cod_bitc17`),
  KEY `cod_bitc09` (`cod_bitc09`),
  KEY `cod_validado` (`cod_validado`),
  CONSTRAINT `e3_referencias_tbl_ibfk_1` FOREIGN KEY (`cod_codtrn`) REFERENCES `codtrn_tbl` (`codtrn_id`),
  CONSTRAINT `e3_referencias_tbl_ibfk_2` FOREIGN KEY (`cod_cotdor`) REFERENCES `cotdor_e3_tbl` (`cotdor_id`),
  CONSTRAINT `e3_referencias_tbl_ibfk_3` FOREIGN KEY (`cod_coacci`) REFERENCES `coacci_e3_tbl` (`coacci_id`),
  CONSTRAINT `e3_referencias_tbl_ibfk_4` FOREIGN KEY (`cod_bitc16`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e3_referencias_tbl_ibfk_5` FOREIGN KEY (`cod_bitc17`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e3_referencias_tbl_ibfk_6` FOREIGN KEY (`cod_bitc09`) REFERENCES `ternaria_tbl` (`ternaria_id`),
  CONSTRAINT `e3_referencias_tbl_ibfk_7` FOREIGN KEY (`cod_validado`) REFERENCES `validacion_datos_tbl` (`validado_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.e3_referencias_tbl: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `e3_referencias_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `e3_referencias_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.e4_impuestos_tbl
CREATE TABLE IF NOT EXISTS `e4_impuestos_tbl` (
  `e4_impuestos_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cod_codtrn` varchar(4) COLLATE latin1_spanish_ci NOT NULL,
  `cod_cotdor` smallint(3) unsigned NOT NULL,
  `idprov` int(9) unsigned NOT NULL,
  `cod_coacci` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `coengp` mediumint(5) unsigned NOT NULL,
  `cod_coaces` int(9) unsigned NOT NULL,
  `nurcat` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `cod_cogruc` tinyint(2) unsigned NOT NULL,
  `cod_cotaca` tinyint(2) unsigned NOT NULL,
  `cod_cosbac` tinyint(2) unsigned NOT NULL,
  `cod_bitc18` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `feprre` int(8) unsigned NOT NULL,
  `cod_bitc19` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `ferere` int(8) unsigned NOT NULL,
  `cod_bitc20` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `fedein` int(8) unsigned NOT NULL,
  `cod_bitc21` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bisode` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bitc22` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cod_bireso` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `cotexa` bigint(10) unsigned NOT NULL,
  `cod_bitc09` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `obtexc` varchar(110) COLLATE latin1_spanish_ci NOT NULL,
  `obdeer` varchar(80) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`e4_impuestos_id`),
  KEY `cod_codtrn` (`cod_codtrn`),
  KEY `cod_cotdor` (`cod_cotdor`),
  KEY `cod_coacci` (`cod_coacci`),
  KEY `cod_coaces` (`cod_coaces`),
  KEY `cod_cosbac` (`cod_cosbac`),
  KEY `cod_bitc18` (`cod_bitc18`),
  KEY `cod_bitc19` (`cod_bitc19`),
  KEY `cod_bitc20` (`cod_bitc20`),
  KEY `cod_bitc21` (`cod_bitc21`),
  KEY `cod_bisode` (`cod_bisode`),
  KEY `cod_bitc22` (`cod_bitc22`),
  KEY `cod_bireso` (`cod_bireso`),
  KEY `cod_bitc09` (`cod_bitc09`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_1` FOREIGN KEY (`cod_codtrn`) REFERENCES `codtrn_tbl` (`codtrn_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_2` FOREIGN KEY (`cod_cotdor`) REFERENCES `cotdor_e3_tbl` (`cotdor_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_3` FOREIGN KEY (`cod_coacci`) REFERENCES `coacci_e3_tbl` (`coacci_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_4` FOREIGN KEY (`cod_coaces`) REFERENCES `ac_activos_tbl` (`activo_coaces_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_5` FOREIGN KEY (`cod_cosbac`) REFERENCES `cosbga_t21_tbl` (`cosbga_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_6` FOREIGN KEY (`cod_bitc18`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_7` FOREIGN KEY (`cod_bitc19`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_8` FOREIGN KEY (`cod_bitc20`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_9` FOREIGN KEY (`cod_bitc21`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_10` FOREIGN KEY (`cod_bisode`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_11` FOREIGN KEY (`cod_bitc22`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_12` FOREIGN KEY (`cod_bireso`) REFERENCES `unaria_tbl` (`unaria_id`),
  CONSTRAINT `e4_impuestos_tbl_ibfk_13` FOREIGN KEY (`cod_bitc09`) REFERENCES `ternaria_tbl` (`ternaria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.e4_impuestos_tbl: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `e4_impuestos_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `e4_impuestos_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.ga_gastos_tbl
CREATE TABLE IF NOT EXISTS `ga_gastos_tbl` (
  `ga_gastos_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cogrug` tinyint(2) unsigned NOT NULL,
  `cotpga` tinyint(2) unsigned NOT NULL,
  `cosbga` tinyint(2) unsigned NOT NULL,
  `cod_ptpago` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `fedeve` int(8) unsigned NOT NULL,
  `ffgtvp` int(8) unsigned NOT NULL,
  `fepaga` int(8) unsigned NOT NULL,
  `felipg` int(8) unsigned NOT NULL,
  `cod_cosiga` tinyint(2) unsigned NOT NULL,
  `feeesi` int(8) unsigned NOT NULL,
  `feecoi` int(8) unsigned NOT NULL,
  `feeaui` int(8) unsigned NOT NULL,
  `feepai` int(8) unsigned NOT NULL,
  `imngas` bigint(15) unsigned NOT NULL,
  `ycos02` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `imrgas` bigint(13) unsigned NOT NULL,
  `ycos04` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `imdgas` bigint(13) unsigned NOT NULL,
  `ycos06` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `imcost` bigint(13) unsigned NOT NULL,
  `ycos08` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `imogas` bigint(13) unsigned NOT NULL,
  `ycos10` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `imdtga` bigint(13) unsigned NOT NULL,
  `counmo` smallint(3) unsigned NOT NULL,
  `imimga` bigint(15) unsigned NOT NULL,
  `cod_coimpt` tinyint(2) unsigned NOT NULL,
  `cod_cotneg` tinyint(2) unsigned NOT NULL,
  `coencx` smallint(4) unsigned NOT NULL,
  `coofcx` smallint(4) unsigned NOT NULL,
  `nucone` bigint(13) unsigned NOT NULL,
  `nuprof` int(9) unsigned NOT NULL,
  `feagto` int(8) unsigned NOT NULL,
  `cod_comona` tinyint(2) unsigned NOT NULL,
  `cod_biauto` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `feaufa` int(8) unsigned NOT NULL,
  `cod_coterr` tinyint(2) unsigned NOT NULL,
  `fmpagn` int(8) unsigned NOT NULL,
  `fepgpr` int(8) unsigned NOT NULL,
  `feapli` int(8) unsigned NOT NULL,
  `coapii` varchar(3) COLLATE latin1_spanish_ci NOT NULL,
  `cospii` varchar(2) COLLATE latin1_spanish_ci NOT NULL,
  `nuclii` bigint(13) unsigned NOT NULL,
  PRIMARY KEY (`ga_gastos_id`),
  KEY `cod_ptpago` (`cod_ptpago`),
  KEY `cod_cosiga` (`cod_cosiga`),
  KEY `cod_coimpt` (`cod_coimpt`),
  KEY `cod_cotneg` (`cod_cotneg`),
  KEY `cod_comona` (`cod_comona`),
  KEY `cod_biauto` (`cod_biauto`),
  CONSTRAINT `ga_gastos_tbl_ibfk_1` FOREIGN KEY (`cod_ptpago`) REFERENCES `ptpago_tbl` (`ptpago_id`),
  CONSTRAINT `ga_gastos_tbl_ibfk_2` FOREIGN KEY (`cod_cosiga`) REFERENCES `cosiga_tbl` (`cosiga_id`),
  CONSTRAINT `ga_gastos_tbl_ibfk_3` FOREIGN KEY (`cod_coimpt`) REFERENCES `coimpt_tbl` (`coimpt_id`),
  CONSTRAINT `ga_gastos_tbl_ibfk_4` FOREIGN KEY (`cod_cotneg`) REFERENCES `cotneg_tbl` (`cotneg_id`),
  CONSTRAINT `ga_gastos_tbl_ibfk_5` FOREIGN KEY (`cod_comona`) REFERENCES `comona_tbl` (`comona_id`),
  CONSTRAINT `ga_gastos_tbl_ibfk_6` FOREIGN KEY (`cod_biauto`) REFERENCES `biauto_tbl` (`biauto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.ga_gastos_tbl: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ga_gastos_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `ga_gastos_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.lista_cuotas_multi
CREATE TABLE IF NOT EXISTS `lista_cuotas_multi` (
  `cod_coaces` int(9) unsigned NOT NULL,
  `cod_nudcom` varchar(10) COLLATE latin1_spanish_ci NOT NULL,
  `cod_cuotas` bigint(20) unsigned NOT NULL,
  `cod_validado` char(1) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cod_coaces`,`cod_nudcom`,`cod_cuotas`),
  KEY `cod_nudcom` (`cod_nudcom`),
  KEY `cod_cuotas` (`cod_cuotas`),
  KEY `cod_validado` (`cod_validado`),
  CONSTRAINT `lista_cuotas_multi_ibfk_1` FOREIGN KEY (`cod_coaces`) REFERENCES `ac_activos_tbl` (`activo_coaces_id`) ON DELETE CASCADE,
  CONSTRAINT `lista_cuotas_multi_ibfk_2` FOREIGN KEY (`cod_nudcom`) REFERENCES `e1_comunidades_tbl` (`nudcom_id`) ON DELETE CASCADE,
  CONSTRAINT `lista_cuotas_multi_ibfk_3` FOREIGN KEY (`cod_cuotas`) REFERENCES `e2_cuotas_tbl` (`e2_cuotas_id`) ON DELETE CASCADE,
  CONSTRAINT `lista_cuotas_multi_ibfk_4` FOREIGN KEY (`cod_validado`) REFERENCES `validacion_datos_tbl` (`validado_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.lista_cuotas_multi: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `lista_cuotas_multi` DISABLE KEYS */;
/*!40000 ALTER TABLE `lista_cuotas_multi` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.lista_gastos_multi
CREATE TABLE IF NOT EXISTS `lista_gastos_multi` (
  `cod_coaces` int(9) unsigned NOT NULL,
  `cod_nuprof` int(9) unsigned NOT NULL,
  `cod_gastos` bigint(20) unsigned NOT NULL,
  `cod_validado` char(1) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cod_coaces`,`cod_nuprof`,`cod_gastos`),
  KEY `cod_nuprof` (`cod_nuprof`),
  KEY `cod_gastos` (`cod_gastos`),
  KEY `cod_validado` (`cod_validado`),
  CONSTRAINT `lista_gastos_multi_ibfk_1` FOREIGN KEY (`cod_coaces`) REFERENCES `ac_activos_tbl` (`activo_coaces_id`) ON DELETE CASCADE,
  CONSTRAINT `lista_gastos_multi_ibfk_2` FOREIGN KEY (`cod_nuprof`) REFERENCES `provisiones_tbl` (`nuprof_id`) ON DELETE CASCADE,
  CONSTRAINT `lista_gastos_multi_ibfk_3` FOREIGN KEY (`cod_gastos`) REFERENCES `ga_gastos_tbl` (`ga_gastos_id`) ON DELETE CASCADE,
  CONSTRAINT `lista_gastos_multi_ibfk_4` FOREIGN KEY (`cod_validado`) REFERENCES `validacion_datos_tbl` (`validado_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.lista_gastos_multi: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `lista_gastos_multi` DISABLE KEYS */;
/*!40000 ALTER TABLE `lista_gastos_multi` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.lista_impuestos_multi
CREATE TABLE IF NOT EXISTS `lista_impuestos_multi` (
  `cod_coaces` int(9) unsigned NOT NULL,
  `cod_nurcat` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `cod_impuestos` bigint(20) unsigned NOT NULL,
  `cod_validado` char(1) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`cod_coaces`,`cod_nurcat`,`cod_impuestos`),
  KEY `cod_nurcat` (`cod_nurcat`),
  KEY `cod_impuestos` (`cod_impuestos`),
  KEY `cod_validado` (`cod_validado`),
  CONSTRAINT `lista_impuestos_multi_ibfk_1` FOREIGN KEY (`cod_coaces`) REFERENCES `ac_activos_tbl` (`activo_coaces_id`) ON DELETE CASCADE,
  CONSTRAINT `lista_impuestos_multi_ibfk_2` FOREIGN KEY (`cod_nurcat`) REFERENCES `e3_referencias_tbl` (`nurcat_id`) ON DELETE CASCADE,
  CONSTRAINT `lista_impuestos_multi_ibfk_3` FOREIGN KEY (`cod_impuestos`) REFERENCES `e4_impuestos_tbl` (`e4_impuestos_id`) ON DELETE CASCADE,
  CONSTRAINT `lista_impuestos_multi_ibfk_4` FOREIGN KEY (`cod_validado`) REFERENCES `validacion_datos_tbl` (`validado_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.lista_impuestos_multi: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `lista_impuestos_multi` DISABLE KEYS */;
/*!40000 ALTER TABLE `lista_impuestos_multi` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.provisiones_tbl
CREATE TABLE IF NOT EXISTS `provisiones_tbl` (
  `nuprof_id` int(9) unsigned NOT NULL,
  `fepfon` int(8) unsigned NOT NULL DEFAULT '0',
  `fecha_validacion` int(8) unsigned NOT NULL DEFAULT '0',
  `estado` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT 'A',
  `valor_total` bigint(20) NOT NULL DEFAULT '0',
  `num_gastos` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`nuprof_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.provisiones_tbl: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `provisiones_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `provisiones_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.ptpago_tbl
CREATE TABLE IF NOT EXISTS `ptpago_tbl` (
  `ptpago_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`ptpago_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.ptpago_tbl: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `ptpago_tbl` DISABLE KEYS */;
INSERT INTO `ptpago_tbl` (`ptpago_id`, `descripcion`) VALUES
	('1', 'APERIODICO'),
	('2', 'MENSUAL'),
	('3', 'BIMENSUAL'),
	('4', 'TRIMESTRAL'),
	('5', 'CUATRIMESTRAL'),
	('6', 'SEMESTRAL'),
	('7', 'ANUAL'),
	('8', 'VARIOS PERIODOS');
/*!40000 ALTER TABLE `ptpago_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.ternaria_tbl
CREATE TABLE IF NOT EXISTS `ternaria_tbl` (
  `ternaria_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`ternaria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.ternaria_tbl: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `ternaria_tbl` DISABLE KEYS */;
INSERT INTO `ternaria_tbl` (`ternaria_id`, `descripcion`) VALUES
	('#', 'No se tiene en cuenta'),
	('A', 'Alta'),
	('B', 'Baja'),
	('M', 'Modificación');
/*!40000 ALTER TABLE `ternaria_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.unaria_tbl
CREATE TABLE IF NOT EXISTS `unaria_tbl` (
  `unaria_id` char(1) COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`unaria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.unaria_tbl: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `unaria_tbl` DISABLE KEYS */;
INSERT INTO `unaria_tbl` (`unaria_id`, `descripcion`) VALUES
	('#', 'No se hace el control'),
	('S', 'Se hace el control');
/*!40000 ALTER TABLE `unaria_tbl` ENABLE KEYS */;


-- Volcando estructura para tabla glsl.validacion_datos_tbl
CREATE TABLE IF NOT EXISTS `validacion_datos_tbl` (
  `validado_id` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`validado_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla glsl.validacion_datos_tbl: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `validacion_datos_tbl` DISABLE KEYS */;
INSERT INTO `validacion_datos_tbl` (`validado_id`, `descripcion`) VALUES
	('E', 'Enviado'),
	('P', 'Pendiente de enviar'),
	('V', 'Validado'),
	('X', 'Error');
/*!40000 ALTER TABLE `validacion_datos_tbl` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
