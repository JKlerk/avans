# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 8.0.16)
# Database: avans
# Generation Time: 2020-02-18 14:31:31 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table artists
# ------------------------------------------------------------

DROP TABLE IF EXISTS `artists`;

CREATE TABLE `artists` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;

INSERT INTO `artists` (`id`, `name`, `description`)
VALUES
	(1,'Artiest 1','obcaecati itaque rem ad ex corrupti delectus voluptatem architecto accusamus, recusandae similique atque numquam eveniet quas beatae ratione perspiciatis modi.'),
	(2,'Artiest 2','obcaecati itaque rem ad ex corrupti delectus voluptatem architecto accusamus, recusandae similique atque numquam eveniet quas beatae ratione perspiciatis modi.'),
	(3,'Artiest 3','obcaecati itaque rem ad ex corrupti delectus voluptatem architecto accusamus, recusandae similique atque numquam eveniet quas beatae ratione perspiciatis modi.');

/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table performances
# ------------------------------------------------------------

DROP TABLE IF EXISTS `performances`;

CREATE TABLE `performances` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `time` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `date` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `stage_id` int(11) DEFAULT NULL,
  `artist_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `performances_artists` (`artist_id`),
  KEY `perfromances_stages` (`stage_id`),
  CONSTRAINT `performances_artists` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `perfromances_stages` FOREIGN KEY (`stage_id`) REFERENCES `stages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `performances` WRITE;
/*!40000 ALTER TABLE `performances` DISABLE KEYS */;

INSERT INTO `performances` (`id`, `name`, `time`, `date`, `stage_id`, `artist_id`)
VALUES
	(8,'Performance 1','20:01','2001-05-05',1,1),
	(9,'Performance 2','20:30','2001-02-20',2,2),
	(10,'Performance 3','21:20','2001-12-05',2,1);

/*!40000 ALTER TABLE `performances` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table stages
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stages`;

CREATE TABLE `stages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `stages` WRITE;
/*!40000 ALTER TABLE `stages` DISABLE KEYS */;

INSERT INTO `stages` (`id`, `name`, `description`)
VALUES
	(1,'Stage 1','obcaecati itaque rem ad ex corrupti delectus voluptatem architecto accusamus, recusandae similique atque numquam eveniet quas beatae ratione perspiciatis modi.'),
	(2,'Stage 2','obcaecati itaque rem ad ex corrupti delectus voluptatem architecto accusamus, recusandae similique atque numquam eveniet quas beatae ratione perspiciatis modi.');

/*!40000 ALTER TABLE `stages` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
