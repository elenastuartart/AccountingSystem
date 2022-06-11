CREATE DATABASE  IF NOT EXISTS `study_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `study_db`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: study_db
-- ------------------------------------------------------
-- Server version	5.5.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `doc_purchase`
--

DROP TABLE IF EXISTS `doc_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_purchase` (
  `id` binary(16) NOT NULL,
  `date` datetime NOT NULL,
  `finalSum` double DEFAULT NULL,
  `number` int(11) NOT NULL,
  `pometkaProvedeniya` bit(1) NOT NULL,
  `contragent_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `contragent_fkey_doc` (`contragent_id`),
  CONSTRAINT `contragent_fkey_doc` FOREIGN KEY (`contragent_id`) REFERENCES `contragent` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–î–æ–∫—É–º–µ–Ω—Ç –∑–∞–∫—É–ø–∫–∞';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_purchase`
--

LOCK TABLES `doc_purchase` WRITE;
/*!40000 ALTER TABLE `doc_purchase` DISABLE KEYS */;
INSERT INTO `doc_purchase` VALUES ('Â‘Ì\0H~†Ràa+)l4','2022-06-10 00:00:00',6400,43123085,'','ı í(»xL^âÔ/PŒ¡_°'),('f¢/Fö\'@o§ÏZ…y-—k','2022-06-10 00:00:00',15100,96487201,'','›ß0òóB‡≠nÂT<Òƒ@'),('∑N ‘p∞O3ä™53<TÈ\r','2022-06-10 00:00:00',30000,23396703,'','ãóˆÿ}B›ë[…[5!'),('¡hÏ–ê\ZCóù˜0—›P%','2022-06-10 00:00:00',3050,49997271,'','™–¿M˜¯@±dwå®˚ÉÁ'),('√\"ºïJ0ñ§©9TbíÕ','2022-06-10 00:00:00',5900,85375775,'','Cÿ@wB5Ø≠mËÜ‹j');
/*!40000 ALTER TABLE `doc_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contragent`
--

DROP TABLE IF EXISTS `contragent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contragent` (
  `id` binary(16) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `code` int(11) NOT NULL,
  `contact_person` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `type_KA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–°–ø—Ä–∞–≤–æ—á–Ω–∏–∫ "–∫–æ–Ω—Ç—Ä–∞–≥–µ–Ω—Ç—ã"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contragent`
--

LOCK TABLES `contragent` WRITE;
/*!40000 ALTER TABLE `contragent` DISABLE KEYS */;
INSERT INTO `contragent` VALUES ('$á@!…J≤´á˜Cö*ƒ','–°–∞–Ω–∫—Ç-–ü–µ—Ç–µ—Ä–±—É—Ä–≥, —É–ª. –ü–∞—Ä–∫–æ–≤–∞—è, –¥. 13',66432266,'contragent08@mail.ru','–í–µ—Ä–æ–Ω–∏–∫–∞ –ü–µ—Ç—Ä–æ–≤–∞','–ø–æ–∫—É–ø–∞—Ç–µ–ª—å'),('ò ñU¬Iw°>’hóë\r«','–ú–æ—Å–∫–≤–∞',29336972,'contragent11@mail.ru','More dolls','–ø—Ä–æ–¥–∞–≤–µ—Ü'),('\0à[NÆö¥m^\nW¡','–ú–æ—Å–∫–≤–∞',69074207,'contragent04@mail.ru','BJD Boutique','–ø—Ä–æ–¥–∞–≤–µ—Ü'),('Cÿ@wB5Ø≠mËÜ‹j','–ú–æ—Å–∫–≤–∞',70816631,'contragent15@mail.ru','–¢–∫–∞–Ω–∏ shop','–ü—Ä–æ–¥–∞–≤–µ—Ü'),('ãóˆÿ}B›ë[…[5!','–ú–æ—Å–∫–≤–∞',72796914,'contragent13@mail.ru','–õ–∏—Ç–µ–π–Ω–∞—è ‚Ññ1','–ø—Ä–æ–¥–∞–≤–µ—Ü'),('SÊ—nCÍ∂Ë”¡ﬁˆ ','–†–µ—É—Ç–æ–≤',18390586,'contragent10@mail.ru','Malvinafashiondoll, –ú–∞—Ä–∏–Ω–∞ –ö–æ–∑–ª–æ–≤–∞','–ø–æ–∫—É–ø–∞—Ç–µ–ª—å'),('/Bå)zN8øÕÍuÀ','–ú–æ—Å–∫–≤–∞',47567170,'contragent05@mail.ru','Doll shop','–ø—Ä–æ–¥–∞–≤–µ—Ü'),('V6{BÓΩPÃÏ«ñí','–°–∞–º–∞—Ä–∞',51391538,'contragent06@mail.ru','–ê–Ω–Ω–∞ –°–µ—Ä–≥–µ–µ–≤–∞','–ø–æ–∫—É–ø–∞—Ç–µ–ª—å'),('rˆjzDÖ6[ ','–ú–æ—Å–∫–≤–∞',43414476,'contragent17@mail.ru','–ú–∞–≥–∞–∑–∏–Ω —Ñ—É—Ä–Ω–∏—Ç—É—Ä—ã','–ü—Ä–æ–¥–∞–≤–µ—Ü'),('zë¿U˛ÜLg≥jvEc¬','–ú–æ—Å–∫–≤–∞',26469982,'contragent16@mail.ru','ReinStudio','–ü—Ä–æ–¥–∞–≤–µ—Ü'),('z«)®ÓFt™”Î 3pÙT','–ù–∏–∂–Ω–∏–π –ù–æ–≤–≥–æ—Ä–æ–¥, —É–ª. –ü–æ–±–µ–¥—ã 3',75089420,'contragent12@mail.ru','–ï–ª–µ–Ω–∞ –°–µ—Ä–≥–µ–µ–≤–∞','–ø–æ–∫—É–ø–∞—Ç–µ–ª—å'),('ë∂0˜k6M2∏y„«~(Œ8','–ú–æ—Å–∫–≤–∞',10714421,'contragent03@mail.ru','Doll_friends','–ø—Ä–æ–¥–∞–≤–µ—Ü'),('ùr= √[Mæπäæl4‹æ†','–ú–æ—Å–∫–≤–∞, –ø—Ä. –í–µ—Ä–Ω–∞–¥—Å–∫–æ–≥–æ, –¥. 14',20220101,'bjd_doll_house@mail.ru','My company \"BJD DOLL HOUSE\"','–ø—Ä–æ–¥–∞–≤–µ—Ü  '),('™–¿M˜¯@±dwå®˚ÉÁ','–ú–æ—Å–∫–≤–∞',76871376,'contragent20@mail.ru','Hair_dolls_shop','–ü—Ä–æ–¥–∞–≤–µ—Ü'),('Æâ·¿7O‹´€W›Ëß Å','–¢–≤–µ—Ä—å',95002244,'contragent07@mail.ru','–ò–ª—å—è –ò–≤–∞–Ω–æ–≤','–ø–æ–∫—É–ø–∞—Ç–µ–ª—å'),('›ß0òóB‡≠nÂT<Òƒ@','–ú–æ—Å–∫–≤–∞',62884482,'contragent01@mail.ru','Fashion dolls','–ø—Ä–æ–¥–∞–≤–µ—Ü'),('ÌN>ÎelO^úWrGî{ñ','–°–∞–Ω–∫—Ç-–ü–µ—Ç–µ—Ä–±—É—Ä–≥',15845140,'contragent09@mail.ru','–û–ª—å–≥–∞ –ü–µ–≤—Ü–æ–≤–∞','–ø–æ–∫—É–ø–∞—Ç–µ–ª—å'),('ı í(»xL^âÔ/PŒ¡_°','–ú–æ—Å–∫–≤–∞',71873576,'contragent18@mail.ru','–ü–ª–∞—Å—Ç–∏–∫ –¥–ª—è –ø—Ä–∏–Ω—Ç–µ—Ä–∞ shop','–ü—Ä–æ–¥–∞–≤–µ—Ü'),('˜qœîsL9µ€≈Œ·u¥','–ú–æ—Å–∫–≤–∞',66867789,'contragent19@mail.ru','–§—É—Ä–Ω–∏—Ç—É—Ä–∞ shop','–ü—Ä–æ–¥–∞–≤–µ—Ü'),('˚1Û≤3BRø(MÙ≥ÆΩÿ','–ú–æ—Å–∫–≤–∞',41480981,'contragent02@mail.ru','Doll doll','–ø—Ä–æ–¥–∞–≤–µ—Ü');
/*!40000 ALTER TABLE `contragent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_part_purchase`
--

DROP TABLE IF EXISTS `table_part_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_part_purchase` (
  `id` binary(16) NOT NULL,
  `amount` double NOT NULL COMMENT '–∫–æ–ª–∏—á–µ—Å—Ç–≤–æ',
  `lineNumber` int(11) DEFAULT NULL COMMENT '–Ω–æ–º–µ—Ä —Å—Ç—Ä–æ–∫–∏',
  `price` double NOT NULL COMMENT '—Ü–µ–Ω–∞',
  `sum` double DEFAULT NULL COMMENT '—Å—É–º–º–∞',
  `nomenclature_id` binary(16) NOT NULL,
  `doc_purchase_id` binary(16) NOT NULL,
  `idDoc` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nomenclature_fkey_purchase` (`nomenclature_id`),
  KEY `doc_purchase_fkey` (`doc_purchase_id`),
  CONSTRAINT `doc_purchase_fkey` FOREIGN KEY (`doc_purchase_id`) REFERENCES `doc_purchase` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nomenclature_fkey_purchase` FOREIGN KEY (`nomenclature_id`) REFERENCES `nomenclature` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–¢–∞–±–ª–∏—á–Ω–∞—è —á–∞—Å—Ç—å "–∑–∞–∫—É–ø–∫–∞"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_part_purchase`
--

LOCK TABLES `table_part_purchase` WRITE;
/*!40000 ALTER TABLE `table_part_purchase` DISABLE KEYS */;
INSERT INTO `table_part_purchase` VALUES ('”5·¥ÆKGßB}’’ù:',3,1,800,2400,'AqXŸG0™ó\nÍF\"5','√\"ºïJ0ñ§©9TbíÕ','√\"ºïJ0ñ§©9TbíÕ'),('üùwc^H\nò˛JleH?a',7,1,700,4900,'yjV˛YBµ∂Õ»`Õ¶9','f¢/Fö\'@o§ÏZ…y-—k','f¢/Fö\'@o§ÏZ…y-—k'),('>πûs\"◊Hﬁ§,/õ÷jÔ',4,1,600,2400,'.{g…£I[¥Ωìä≥[ t','Â‘Ì\0H~†Ràa+)l4','Â‘Ì\0H~†Ràa+)l4'),('P∏$íhÁKô™˚b\nS\nC',9,1,2000,18000,'ìYW˛õIüHqÏ/≈∂q','∑N ‘p∞O3ä™53<TÈ\r','∑N ‘p∞O3ä™53<TÈ\r'),('o¶\n/¿oL}í (ßŒ»%”',5,1,200,1000,'< ˝˜EúêzÌ—u','¡hÏ–ê\ZCóù˜0—›P%','¡hÏ–ê\ZCóù˜0—›P%'),('î¯L[‡Ià@“ß˛',4,2,200,800,'√Ç«ïM ßØu`8ô','¡hÏ–ê\ZCóù˜0—›P%','¡hÏ–ê\ZCóù˜0—›P%'),('†sØHÇ˜E^∂ªÅBê≈ñ',12,3,400,4800,'<6“„=GE≤Àæº¡','f¢/Fö\'@o§ÏZ…y-—k','f¢/Fö\'@o§ÏZ…y-—k'),('®˜ıÆNéGÒµıI˚ZN§',5,2,300,1500,'âäkèGrïÁ@åÀ6','√\"ºïJ0ñ§©9TbíÕ','√\"ºïJ0ñ§©9TbíÕ'),('∂L}®Î\ZFHç\0™UÊA∆',5,3,250,1250,'5	øßC\0Oı∞∏æe)|Nû','¡hÏ–ê\ZCóù˜0—›P%','¡hÏ–ê\ZCóù˜0—›P%'),('√(Æ)ËªDIóM3ô||ˇÇ',4,3,500,2000,'Àx{œGõC˛íEΩ ˜Ÿ≥','√\"ºïJ0ñ§©9TbíÕ','√\"ºïJ0ñ§©9TbíÕ'),('∆´FùDlIÈà∫e/`É?Ÿ',5,2,800,4000,'XTÂ¥∞I@°ÅÖûiF','Â‘Ì\0H~†Ràa+)l4','Â‘Ì\0H~†Ràa+)l4'),('œGAîø9G˛¥∂l–\Z',8,2,1500,12000,'Ï\"\'”ÅD”¥Ï´/kG–œ','∑N ‘p∞O3ä™53<TÈ\r','∑N ‘p∞O3ä™53<TÈ\r'),('˜w¢˙øIG˘ñ≠çˇ\r]#!',6,2,900,5400,'s=&Ôè_NÇ°⁄≤9Ô„·','f¢/Fö\'@o§ÏZ…y-—k','f¢/Fö\'@o§ÏZ…y-—k');
/*!40000 ALTER TABLE `table_part_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production_stages`
--

DROP TABLE IF EXISTS `production_stages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `production_stages` (
  `id` binary(16) NOT NULL,
  `code` int(11) NOT NULL,
  `description_stage` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–°–ø—Ä–∞–≤–æ—á–Ω–∏–∫ "–°—Ç–∞–¥–∏–∏ –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_stages`
--

LOCK TABLES `production_stages` WRITE;
/*!40000 ALTER TABLE `production_stages` DISABLE KEYS */;
INSERT INTO `production_stages` VALUES ('\rÆf\rLŒ©7xc\r>XC',64239158,'–∏–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –¥–µ—Ç–∞–ª–µ–π –¥–ª—è —Å–±–æ—Ä–∫–∏ –∫—É–∫–ª—ã','–ª–∏—Ç—å–µ –¥–µ—Ç–∞–ª–µ–π'),(' v•ÕÌAòîgVXœû',44320909,'–ø—Ä–∏–∫–ª–µ–∏–≤–∞–Ω–∏–µ –∫–æ–∂–∏/–¥—Ä –º–∞—Ç–µ—Ä–∏–∞–ª–∞ –Ω–∞ –ø–æ–¥–æ—à–≤—É, —Ñ–∏–∫—Å–∏—Ä–æ–≤–∞–Ω–∏–µ –Ω–∞ –∫–æ–ª–æ–¥–∫–µ, —Ñ–∏–Ω–∞–ª—å–Ω–∞—è –æ—Ç–¥–µ–ª–∫–∞','–∏–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –æ–±—É–≤–∏'),('Kñ ç\'ÁC>õv¢,i‚Ñ',89280188,' ','—à–ª–∏—Ñ–æ–≤–∫–∞ –¥–µ—Ç–∞–ª–µ–π'),('fàKˆã\\Hî˝)j>Åë',34706467,' ','–∏–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –ø–∞—Ä–∏–∫–∞'),('iir–9aKQ≤:©∫\ZìŒ‹',97793844,' –æ–ø–∏—Å–∞–Ω–∏–µ','—à–ª–∏—Ñ–æ–≤–∫–∞ –¥–µ—Ç–∞–ª–µ–π'),('k¬>Ü–RJÕæ™£M¯≥mY',10222942,'–æ–ø–∏—Å–∞–Ω–∏–µ','–∏–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –æ–¥–µ–∂–¥—ã'),('vå◊P’ÊKñºjuk∑Xi}',98039286,' ','–∏–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –∫–∞—Ä–∫–∞—Å–∞ –¥–ª—è –ø–∞—Ä–∏–∫–∞'),('Ö&ΩªÆ™BhìNAèˇ)ÌΩ',62753609,'–º–∞–∫–∏—è–∂, —Ä–æ—Å–ø–∏—Å—å —Ç–µ–ª–∞ –ø–æ –∂–µ–ª–∞–Ω–∏—é –∑–∞–∫–∞–∑—á–∏–∫–∞/–ø–æ —à–∞–±–ª–æ–Ω—É ','—Ä–æ—Å–ø–∏—Å—å –∫—É–∫–ª—ã'),('øÏs.?DdÇDî\Z\'$R!',52813835,'—Ä–æ—Å–ø–∏—Å—å –ø–æ –∂–µ–ª–∞–Ω–∏—é –∑–∞–∫–∞–∑—á–∏–∫–∞','—Ö—É–¥–æ–∂–µ—Å—Ç–≤–µ–Ω–Ω–∞—è —Ä–æ—Å–ø–∏—Å—å'),('Ÿ‹p\0TÍBòé`†@>oC®',41601841,' ','—É–∫–ª–∞–¥–∫–∞ –ø–∞—Ä–∏–∫–∞'),('„n=˝¸PJïïyÅWòxJ',70581890,' ','—Å–±–æ—Ä–∫–∞ –∫—É–∫–ª—ã'),('˚Á8ÿ¢/@ΩØF‡∆Ò0ï',10996343,' ','–ø–µ—á–∞—Ç—å –ø–æ–¥–æ—à–≤—ã –¥–ª—è –æ–±—É–≤–∏');
/*!40000 ALTER TABLE `production_stages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_part_list_of_products`
--

DROP TABLE IF EXISTS `table_part_list_of_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_part_list_of_products` (
  `id` binary(16) NOT NULL,
  `amount` double NOT NULL COMMENT '–∫–æ–ª–∏—á–µ—Å—Ç–≤–æ',
  `lineNumber` int(11) DEFAULT NULL COMMENT '–Ω–æ–º–µ—Ä —Å—Ç—Ä–æ–∫–∏',
  `price` double NOT NULL COMMENT '—Ü–µ–Ω–∞',
  `sum` double DEFAULT NULL COMMENT '—Å—É–º–º–∞',
  `nomenclature_id` binary(16) NOT NULL,
  `doc_sale_id` binary(16) NOT NULL,
  `idDoc` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nomenclature_fkey_products` (`nomenclature_id`),
  KEY `doc_sale_fkey` (`doc_sale_id`),
  CONSTRAINT `doc_sale_fkey` FOREIGN KEY (`doc_sale_id`) REFERENCES `doc_sale` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `nomenclature_fkey_products` FOREIGN KEY (`nomenclature_id`) REFERENCES `nomenclature` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–¢–∞–±–ª–∏—á–Ω–∞—è —á–∞—Å—Ç—å "–°–ø–∏—Å–æ–∫ —Ç–æ–≤–∞—Ä–æ–≤" –¥–ª—è —Ä–µ–∞–ª–∏–∑–∞—Ü–∏–∏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_part_list_of_products`
--

LOCK TABLES `table_part_list_of_products` WRITE;
/*!40000 ALTER TABLE `table_part_list_of_products` DISABLE KEYS */;
INSERT INTO `table_part_list_of_products` VALUES ('@ıi?D‘C&¶˙àt©„4Ò',1,1,4000,4000,'Ê,úQCóG°√∂âÛÖ≠Ï','j„ò8\nŸFπâ—zú  rÍ','j„ò8\nŸFπâ—zú  rÍ'),('Z˘Len⁄A[ä	zE‰J‹-',1,1,50000,50000,'Åcqy%¶NﬁªÚYÁF&Ç','G≥·<ıâCAµÔp€>\n§','G≥·<ıâCAµÔp€>\n§'),('f&™ÚT∑Mê´º=ëòF',1,1,99.999,99.999,'∂\"%ãnYJü ¿Êpë£ˇ','Æ±ﬁ~\\F∞1≈Î#ó·','Æ±ﬁ~\\F∞1≈Î#ó·'),('äf≤‰„LH”Ç±„¬^˝0',1,3,5000,5000,'\'∏å~≠˘L¿±⁄ƒ∑pÖÁ+','G≥·<ıâCAµÔp€>\n§','G≥·<ıâCAµÔp€>\n§'),('öHë‚>L!∫Óœª]˘h≈',1,1,50000,50000,':OEN•L¢¶”9¿˛ˆR','L3ΩıœK|ç€C∑˙¬öö','L3ΩıœK|ç€C∑˙¬öö'),('õâ∏˚7OÅB»ª-\n',1,2,50000,50000,'cñ;Û¯ƒB™≠^∫1;LÊt','j„ò8\nŸFπâ—zú  rÍ','j„ò8\nŸFπâ—zú  rÍ'),('¨ØŸ	–2L’ànõª9„',1,2,10000,10000,'—, \\{BN≥|åçL','Q“˚;*ÈK.†äe¡≠RÃ','Q“˚;*ÈK.†äe¡≠RÃ'),('Ωå»jM™∏çx8R[',1,3,3000,3000,'Uπß4ßI}óA˛4ë6Óõ','j„ò8\nŸFπâ—zú  rÍ','j„ò8\nŸFπâ—zú  rÍ'),('À=}(w\nK>∫æñu~’j',1,2,5000,5000,'G%∫´¢GiªèÃ“Ω0›','G≥·<ıâCAµÔp€>\n§','G≥·<ıâCAµÔp€>\n§'),('’\rå◊VˆF)¥ŒÖ™í°',2,1,2000,4000,'\rÂ{˜TiGUå\n©ÜÂwË','Q“˚;*ÈK.†äe¡≠RÃ','Q“˚;*ÈK.†äe¡≠RÃ'),('’ﬁ:VH¸á1>.öˆO',1,2,8000,8000,'ß=â˝}G¬õ¯Ø#å8_¿','L3ΩıœK|ç€C∑˙¬öö','L3ΩıœK|ç€C∑˙¬öö');
/*!40000 ALTER TABLE `table_part_list_of_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nomenclature`
--

DROP TABLE IF EXISTS `nomenclature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nomenclature` (
  `id` binary(16) NOT NULL,
  `article_number` int(11) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `code` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `subcategory` varchar(255) DEFAULT NULL,
  `contragent_id` binary(16) NOT NULL,
  `idKontragent` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `contragent_fkey` (`contragent_id`),
  CONSTRAINT `contragent_fkey` FOREIGN KEY (`contragent_id`) REFERENCES `contragent` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nomenclature`
--

LOCK TABLES `nomenclature` WRITE;
/*!40000 ALTER TABLE `nomenclature` DISABLE KEYS */;
INSERT INTO `nomenclature` VALUES (':OEN•L¢¶”9¿˛ˆR',29443114,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',29443114,'–ú–æ–ª–¥ –°–æ—Ñ—å—è, —Å–∫–∏–Ω \"Tan\", —à—Ç','–ö—É–∫–ª–∞ –±–ª–∞–Ω–∫','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('Uπß4ßI}óA˛4ë6Óõ',83814807,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',83814807,'–ü–∞—Ä–∏–∫ –ø—Ä—è–º–æ–π –∏–∑ –∞–ª—å–ø–∞–∫–∏, –¥–ª–∏–Ω–Ω—ã–π, —à—Ç ','–ü–∞—Ä–∏–∫–∏','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('v‰õ¯õEü\nâ§Xê',77360920,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',77360920,'–¢—Ä–µ—Å—Å—ã –∏–∑ –∞–ª—å–ø–∞–∫–∏ –ø—Ä—è–º—ã–µ, 20 —Å–º –¥–ª–∏–Ω–∞, 1 –º','–ò–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –ø–∞—Ä–∏–∫–æ–≤','™–¿M˜¯@±dwå®˚ÉÁ','™–¿M˜¯@±dwå®˚ÉÁ'),('Cg?ÎíH‰®\Z#e{)æ',61397400,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',61397400,'–ë–æ—Å–æ–Ω–æ–∂–∫–∏ –±–µ–ª—ã–µ —Å–æ —Å—Ç—Ä–∞–∑–∞–º–∏','–æ–±—É–≤—å','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('\rÂ{˜TiGUå\n©ÜÂwË',47125568,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',47125568,'–ö–æ–ª–æ–¥–∫–∏ –¥–ª—è –æ–±—É–≤–∏','—Ç–æ–≤–∞—Ä—ã –¥–ª—è –º–∞—Å—Ç–µ—Ä–æ–≤','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('vé…oC@ü£ÜaqH',76196015,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',76196015,'–°–ø—Ä–µ–π –¥–ª—è —É–∫–ª–∞–¥–∫–∏, 1 —à—Ç','–∏–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –ø–∞—Ä–∏–∫–æ–≤','™–¿M˜¯@±dwå®˚ÉÁ','™–¿M˜¯@±dwå®˚ÉÁ'),('XTÂ¥∞I@°ÅÖûiF',60789220,'–¢–æ–≤–∞—Ä—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',60789220,'ABS-–ø–ª–∞—Å—Ç–∏–∫ –¥–ª—è –ø—Ä–∏–Ω—Ç–µ—Ä–∞, —É–ø','–ø–µ—á–∞—Ç—å –º–æ–¥–µ–ª–µ–π','ı í(»xL^âÔ/PŒ¡_°','ı í(»xL^âÔ/PŒ¡_°'),('’d‡†lMñ§súì‡∂C',56180394,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',56180394,'–ú–æ–ª–Ω–∏—è –¥–ª—è –æ–¥–µ–∂–¥—ã 5 —Å–º, —à—Ç','–ò–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –æ–¥–µ–∂–¥—ã','rˆjzDÖ6[ ','rˆjzDÖ6[ '),('\Z9¬≥æKTí¬H(U:±•',30139040,'–¢–æ–≤–∞—Ä—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',30139040,'–¢—Ä–µ—Å—Å—ã –∏–∑ –∞–Ω–≥–æ—Ä—Å–∫–æ–π –∫–æ–∑—ã 15 —Å–º –≤–æ–ª–Ω–∏—Å—Ç—ã–µ, 1 –º','–ò–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –ø–∞—Ä–∏–∫–æ–≤','™–¿M˜¯@±dwå®˚ÉÁ','™–¿M˜¯@±dwå®˚ÉÁ'),('\Zs)Ä}_CD¨ˆ˝~ïå',50728878,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',50728878,'–ñ–≥—É—Ç –±–µ–ª—ã–π 5–º–º, –Ø–ø–æ–Ω–∏—è, –º','—Å–±–æ—Ä–∫–∞ –º–æ–¥–µ–ª–µ–π','ë∂0˜k6M2∏y„«~(Œ8','ë∂0˜k6M2∏y„«~(Œ8'),('AqXŸG0™ó\nÍF\"5',22700628,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',22700628,'–ê—Ç–ª–∞—Å —Å–∏–Ω–∏–π, –º','—Ç–∫–∞–Ω—å','Cÿ@wB5Ø≠mËÜ‹j','Cÿ@wB5Ø≠mËÜ‹j'),('\'∏å~≠˘L¿±⁄ƒ∑pÖÁ+',36723583,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',36723583,'–ü–∞—Ä–∏–∫ —Å—Ç—Ä–∏–∂–∫–∞ –ø–∏–∫—Å–∏ –∏–∑ –∞–ª—å–ø–∞–∫–∏, —à—Ç','–ü–∞—Ä–∏–∫–∏','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('(œ_(D¸ãÄgF\0Òq',5733676,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',5733676,'–¢—É—Ñ–ª–∏ –Ω–∞ –±–∞–ª–µ—Ç–Ω—É—é —Å—Ç–æ–ø—É –º–∞—Ç–æ–≤—ã–µ, —à—Ç','–û–±—É–≤—å','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('.{g…£I[¥Ωìä≥[ t',80047027,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',80047027,'–ü–ª–∞—Å—Ç–∏–∫ –¥–ª—è 3D –ø—Ä–∏–Ω—Ç–µ—Ä–∞','–ø–µ—á–∞—Ç—å –º–æ–¥–µ–ª–µ–π','ı í(»xL^âÔ/PŒ¡_°','ı í(»xL^âÔ/PŒ¡_°'),('0Hf∏¡ûAœÇ\0\nÛ»> π',70525288,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',70525288,'–ö–æ–º–ø–ª–µ–∫—Ç –±–µ–ª—å—è, —à—Ç','–û–¥–µ–∂–¥–∞','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('1_∞ÒuGPº“>ò:ìé›',97950039,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',97950039,'–ë–∏—Å–µ—Ä –∫—Ä–∞—Å–Ω—ã–π 2–º–º, –Ø–ø–æ–Ω–∏—è, —É–ø','—Ñ—É—Ä–Ω–∏—Ç—É—Ä–∞ –¥–ª—è —à–∏—Ç—å—è','˜qœîsL9µ€≈Œ·u¥','˜qœîsL9µ€≈Œ·u¥'),('5	øßC\0Oı∞∏æe)|Nû',86694250,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',86694250,'–¢—Ä–µ—Å—Å—ã –∞–ª—å–ø–∞–∫–∏, –º','–∏–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –ø–∞—Ä–∏–∫–æ–≤','™–¿M˜¯@±dwå®˚ÉÁ','™–¿M˜¯@±dwå®˚ÉÁ'),('9 ‹ÀªÀN¢ùøƒ˛U÷.¨',62952377,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',62952377,'–ö—Ä—é—á–∫–∏ –º–µ–ª–∫–∏–µ –¥–ª—è –ø–µ—Ä–µ—Ç—è–∂–∫–∏, —à—Ç','—Å–±–æ—Ä–∫–∞ –∫—É–∫–ª—ã','rˆjzDÖ6[ ','rˆjzDÖ6[ '),('< ˝˜EúêzÌ—u',82223836,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',82223836,'–®–µ—Ä—Å—Ç—å –∞–Ω–≥–æ—Ä—Å–∫–æ–π –∫–æ–∑—ã, 100 –≥—Ä','–ò–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –ø–∞—Ä–∏–∫–æ–≤','™–¿M˜¯@±dwå®˚ÉÁ','™–¿M˜¯@±dwå®˚ÉÁ'),('<6“„=GE≤Àæº¡',31433907,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',31433907,'–ö–æ–∂–∞ —á–µ—Ä–Ω–∞—è –æ–±—É–≤–Ω–∞—è —Ç–æ–Ω–∫–∞—è, –º','–∏–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –æ–±—É–≤–∏','›ß0òóB‡≠nÂT<Òƒ@','›ß0òóB‡≠nÂT<Òƒ@'),('G%∫´¢GiªèÃ“Ω0›',14285148,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',14285148,'–•—É–¥–æ–∂–µ—Å—Ç–≤–µ–Ω–Ω–∞—è —Ä–æ—Å–ø–∏—Å—å, –ª–∏—Ü–æ, —à—Ç','—É—Å–ª—É–≥–∏','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('cñ;Û¯ƒB™≠^∫1;LÊt',14024916,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',14024916,'–ú–æ–ª–¥ –ú–µ–ª–∞–Ω–∏—è, —Å–∫–∏–Ω \"Ivory\", —à—Ç','–ö—É–∫–ª–∞ –±–ª–∞–Ω–∫','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('s=&Ôè_NÇ°⁄≤9Ô„·',57940439,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',57940439,'–ö–æ–∂–∞ –±–µ–∂–µ–≤–∞—è, –º','–ò–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –æ–±—É–≤–∏','›ß0òóB‡≠nÂT<Òƒ@','›ß0òóB‡≠nÂT<Òƒ@'),('yjV˛YBµ∂Õ»`Õ¶9',36241607,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',36241607,'–ö–æ–∂–∞ —á–µ—Ä–Ω–∞—è –ø—Ä–µ–º–∏—É–º, –º','–ò–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –æ–¥–µ–∂–¥—ã','›ß0òóB‡≠nÂT<Òƒ@','›ß0òóB‡≠nÂT<Òƒ@'),('Åcqy%¶NﬁªÚYÁF&Ç',87128280,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',87128280,'–ú–æ–ª–¥ –î–∂–µ–∫–∏, —Å–∫–∏–Ω \"Normal\", —à—Ç','–∫—É–∫–ª–∞ –±–ª–∞–Ω–∫','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('àHÃ†:ÎI‡®≥LJÕ˘ú',93217418,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',93217418,'–ü–∞—Ä–∏–∫ –æ–º–±—Ä–µ –≤–æ–ª–Ω–∏—Å—Ç—ã–π, —à—Ç','–ø–∞—Ä–∏–∫–∏','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('âäkèGrïÁ@åÀ6',4982084,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',4982084,'–®–µ–ª–∫ –±–µ–∂–µ–≤—ã–π, –º','–¢–∫–∞–Ω—å','Cÿ@wB5Ø≠mËÜ‹j','Cÿ@wB5Ø≠mËÜ‹j'),('ìYW˛õIüHqÏ/≈∂q',34263107,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',34263107,'–ü–æ–ª–∏—É—Ä–µ—Ç–∞–Ω —Ü–≤–µ—Ç \"Normal\", –∫–≥','–õ–∏—Ç—å–µ –º–æ–¥–µ–ª–µ–π','ãóˆÿ}B›ë[…[5!','ãóˆÿ}B›ë[…[5!'),('ù∂Wß9‡D∞Â9(ˆ$7\0',67911562,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',67911562,'–°–µ—Ä–µ–∂–∫–∏ —Å–µ—Ä–µ–±—Ä—è–Ω—ã–µ —Å —Ñ–∏–∞–Ω–∏—Ç–∞–º–∏, —à—Ç','–£–∫—Ä–∞—à–µ–Ω–∏—è','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('¢6 É”Ô@$É˛a‚x‰‹',11139624,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',11139624,'–ü–∞—Ä–∏–∫ –≤–æ–ª–Ω–∏—Å—Ç—ã–π –∏–∑ –∞–ª—å–ø–∞–∫–∏, —Å—Ä. –¥–ª–∏–Ω–∞, —à—Ç','–ü–∞—Ä–∏–∫–∏','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('ß=â˝}G¬õ¯Ø#å8_¿',37100597,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',37100597,'–ö—É—Ä—Ç–∫–∞ –∫–æ—Å—É—Ö–∞ –∏–∑ –ø—Ä–µ–º–∏—É–º –∫–æ–∂–∏, —à—Ç','–û–¥–µ–∂–¥–∞','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('≤Ì⁄ÔúOJp´ú¿Ω+ñ/',78870381,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',78870381,'–ë–æ—Ç–∏–Ω–∫–∏ –Ω–∞ –∫–∞–±–ª—É—á–Ω—É—é —Å—Ç–æ–ø—É, —à—Ç','–û–±—É–≤—å','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('¥ÇN¥Œ@À∞léjè',85964100,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',85964100,'–°—Ç–µ–Ω–¥ –¥–ª—è –∫—É–∫–æ–ª —Å –ø–æ–¥—Å–≤–µ—Ç–∫–æ–π','–ê–∫—Å–µ—Å—Å—É–∞—Ä—ã','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('∂\"%ãnYJü ¿Êpë£ˇ',75735004,' ',20220101,'–ü–ï–†–í–û–ù–ê–ß–ê–õ–¨–ù–´–ô –ü–†–ò–•–û–î –î/–°',' ','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('ªÇL≈GcÅ…fnv«]™',48255858,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',48255858,'–ü–æ–ª–∏—É—Ä–µ—Ç–∞–Ω –±–µ–∂–µ–≤—ã–π —Ç–æ–Ω –∫–∞—Ä–∞–º–µ–ª—å, –∫–≥','–ª–∏—Ç—å–µ –º–æ–¥–µ–ª–µ–π','ãóˆÿ}B›ë[…[5!','ãóˆÿ}B›ë[…[5!'),('√Ç«ïM ßØu`8ô',93266499,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',93266499,'–õ–æ–∫–æ–Ω—ã –∞–Ω–≥–æ—Ä—Å–∫–æ–π –∫–æ–∑—ã, 100 –≥—Ä','–∏–∑–≥–æ—Ç–æ–≤–ª–µ–Ω–∏–µ –ø–∞—Ä–∏–∫–æ–≤','˚1Û≤3BRø(MÙ≥ÆΩÿ','˚1Û≤3BRø(MÙ≥ÆΩÿ'),('Àx{œGõC˛íEΩ ˜Ÿ≥',67002384,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',67002384,'–®–µ–ª–∫ —Å–∏–Ω–∏–π, –º','–¢–∫–∞–Ω—å','Cÿ@wB5Ø≠mËÜ‹j','Cÿ@wB5Ø≠mËÜ‹j'),('—, \\{BN≥|åçL',74849493,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',74849493,'–ú–∞–Ω–µ–∫–µ–Ω','—Ç–æ–≤–∞—Ä—ã –¥–ª—è –º–∞—Å—Ç–µ—Ä–æ–≤','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('—ã˚ò>\nCuï∆ˇˇ€ı',66116432,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',66116432,'–ë–∞—Ç–∏—Å—Ç —Ä–æ–∑–æ–≤—ã–π, –º','—Ç–∫–∞–Ω—å','Cÿ@wB5Ø≠mËÜ‹j','Cÿ@wB5Ø≠mËÜ‹j'),('‘Å†1%%JÈã;£ÁtìˆË',44577570,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',44577570,'–ü–∞—Ä–∏–∫ –∏–∑ –∞–ª—å–ø–∞–∫–∏ —Ä–æ–∑–æ–≤—ã–π –ø—Ä—è–º–æ–π, —à—Ç','–ø–∞—Ä–∏–∫–∏','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('‘ÆÙD‡^A∏R]Û\'´@—',73779353,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',73779353,'–ë–∞–ª–µ—Ç–∫–∏ –Ω–∞ –ø–ª–æ—Å–∫—É—é —Å—Ç–æ–ø—É, —à—Ç','–û–±—É–≤—å','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('ŸÑ˙ùŸ8JN∫Iqƒ¶w‘',27941802,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',27941802,'–°—Ç—Ä–∞–∑—ã —Å–≤–∞—Ä–æ–≤—Å–∫–∏, —É–ø','—Ñ—É—Ä–Ω–∏—Ç—É—Ä–∞ –¥–ª—è —à–∏—Ç—å—è –∏ —É–∫—Ä–∞—à–µ–Ω–∏–π','rˆjzDÖ6[ ','rˆjzDÖ6[ '),('Ê,úQCóG°√∂âÛÖ≠Ï',24882154,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',24882154,'–¢—É—Ñ–ª–∏ –Ω–∞ –∫–∞–±–ª—É—á–Ω—É—é —Å—Ç–æ–ø—É, —à—Ç','–æ–±—É–≤—å','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('Ï\"\'”ÅD”¥Ï´/kG–œ',75613925,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',75613925,'–ü–æ–ª–∏—É—Ä–µ—Ç–∞–Ω —Ç–æ–Ω Ivory, –∫–≥','–ª–∏—Ç—å–µ –º–æ–¥–µ–ª–µ–π','ãóˆÿ}B›ë[…[5!','ãóˆÿ}B›ë[…[5!'),('Ò6·æR5J8†Ñó≤G«#-',81905731,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',81905731,'–ó–∞—Å—Ç–µ–∂–∫–∏ –∫—Ä—É–≥–ª—ã–µ, —à—Ç','—Ñ—É—Ä–Ω–∏—Ç—É—Ä–∞','˜qœîsL9µ€≈Œ·u¥','˜qœîsL9µ€≈Œ·u¥'),('ˆƒ”Y	@Ê∫=É◊üö»',79204007,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',79204007,'–î–æ–ø. –∫–∏—Å—Ç–∏ –ø–µ—Ä—á–∞—Ç–∫–∏, —à—Ç','–ê–∫—Å–µ—Å—Å—É–∞—Ä—ã','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†'),('˚,—VFJ^õS£‡ˆç',69833727,'–ú–∞—Ç–µ—Ä–∏–∞–ª—ã –¥–ª—è –ø—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–∞',69833727,'–ö—Ä—É–∂–µ–≤–æ —Ç–æ–Ω–∫–æ–µ –±–µ–ª–æ–µ, –º','—Ç–∫–∞–Ω—å','/Bå)zN8øÕÍuÀ','/Bå)zN8øÕÍuÀ'),('˝;¡ÖÜL&≠PÑ‹êU',74403825,'–¢–æ–≤–∞—Ä—ã –Ω–∞ —Ä–µ–∞–ª–∏–∑–∞—Ü–∏—é',74403825,'–•—É–¥–æ–∂–µ—Å—Ç–≤–µ–Ω–Ω–∞—è —Ä–æ—Å–ø–∏—Å—å, —Ç–µ–ª–æ, —à—Ç','—É—Å–ª—É–≥–∏','ùr= √[Mæπäæl4‹æ†','ùr= √[Mæπäæl4‹æ†');
/*!40000 ALTER TABLE `nomenclature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc_manufacture`
--

DROP TABLE IF EXISTS `doc_manufacture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_manufacture` (
  `id` binary(16) NOT NULL,
  `date` datetime NOT NULL,
  `number` int(11) NOT NULL,
  `pometkaProvedeniya` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–î–æ–∫—É–º–µ–Ω—Ç "–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_manufacture`
--

LOCK TABLES `doc_manufacture` WRITE;
/*!40000 ALTER TABLE `doc_manufacture` DISABLE KEYS */;
INSERT INTO `doc_manufacture` VALUES ('|[H±´I≤®iLÊ(Ô','2022-06-10 00:00:00',13316875,''),('uÅÛ/‚HK•ïÚﬁ˛∑Gã-','2022-06-10 00:00:00',69950924,''),('ß(Yü®˙NC§8˚Æ*∏Vr','2022-06-10 00:00:00',79728973,''),('Øæ]”ç´H¡π∑õAw»F©','2022-06-10 00:00:00',46291383,''),('‘	ääÌI\"ä4N¿fÛu','2022-06-11 00:00:00',16057444,'');
/*!40000 ALTER TABLE `doc_manufacture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc_sale`
--

DROP TABLE IF EXISTS `doc_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_sale` (
  `id` binary(16) NOT NULL,
  `date` datetime NOT NULL,
  `finalSum` double DEFAULT NULL,
  `number` int(11) NOT NULL,
  `pometkaProvedeniya` bit(1) NOT NULL,
  `contragent_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `contragent_fkey_sale` (`contragent_id`),
  CONSTRAINT `contragent_fkey_sale` FOREIGN KEY (`contragent_id`) REFERENCES `contragent` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–î–æ–∫—É–º–µ–Ω—Ç "–†–µ–∞–ª–∏–∑–∞—Ü–∏—è"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_sale`
--

LOCK TABLES `doc_sale` WRITE;
/*!40000 ALTER TABLE `doc_sale` DISABLE KEYS */;
INSERT INTO `doc_sale` VALUES ('G≥·<ıâCAµÔp€>\n§','2022-06-12 00:00:00',60000,63460945,'','$á@!…J≤´á˜Cö*ƒ'),('L3ΩıœK|ç€C∑˙¬öö','2022-06-11 00:00:00',58000,8947997,'','SÊ—nCÍ∂Ë”¡ﬁˆ '),('Q“˚;*ÈK.†äe¡≠RÃ','2022-06-11 00:00:00',14000,79829347,'','ò ñU¬Iw°>’hóë\r«'),('j„ò8\nŸFπâ—zú  rÍ','2022-06-11 00:00:00',57000,53842240,'','Æâ·¿7O‹´€W›Ëß Å'),('Æ±ﬁ~\\F∞1≈Î#ó·','2022-05-08 13:29:11',99.999,20220101,'','ùr= √[Mæπäæl4‹æ†');
/*!40000 ALTER TABLE `doc_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register_calculation`
--

DROP TABLE IF EXISTS `register_calculation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register_calculation` (
  `id` binary(16) NOT NULL,
  `date` datetime NOT NULL,
  `sum` double NOT NULL,
  `contragent_id` binary(16) NOT NULL,
  `idDoc` binary(16) NOT NULL,
  `typeDoc` varchar(255) NOT NULL,
  `idKontragent` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `contragent_reg_fkey` (`contragent_id`),
  CONSTRAINT `contragent_reg_fkey` FOREIGN KEY (`contragent_id`) REFERENCES `contragent` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–†–µ–≥–∏—Å—Ç—Ä –≤–∑–∞–∏–º–æ—Ä–∞—Å—á–µ—Ç—ã';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register_calculation`
--

LOCK TABLES `register_calculation` WRITE;
/*!40000 ALTER TABLE `register_calculation` DISABLE KEYS */;
INSERT INTO `register_calculation` VALUES ('“˝û“FÉùˆph¢_-','2022-06-10 00:00:00',-30000,'ãóˆÿ}B›ë[…[5!','∑N ‘p∞O3ä™53<TÈ\r','–ó–∞–∫—É–ø–∫–∞','ãóˆÿ}B›ë[…[5!'),('/ùA≈ÕNwÉ(2`Â†œ','2022-06-10 00:00:00',-6400,'ı í(»xL^âÔ/PŒ¡_°','Â‘Ì\0H~†Ràa+)l4','–ó–∞–∫—É–ø–∫–∞','ı í(»xL^âÔ/PŒ¡_°'),('L≥*åÙüI#≤úpƒºO⁄>','2022-06-10 00:00:00',-15100,'›ß0òóB‡≠nÂT<Òƒ@','f¢/Fö\'@o§ÏZ…y-—k','–ó–∞–∫—É–ø–∫–∞','›ß0òóB‡≠nÂT<Òƒ@'),('ï-wYxG)öl!\"ö™ˇ','2022-06-11 00:00:00',14000,'ò ñU¬Iw°>’hóë\r«','Q“˚;*ÈK.†äe¡≠RÃ','–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','ò ñU¬Iw°>’hóë\r«'),('õcU¯u‰C•®˛xú#ä:‰','2022-06-10 00:00:00',-5900,'Cÿ@wB5Ø≠mËÜ‹j','√\"ºïJ0ñ§©9TbíÕ','–ó–∞–∫—É–ø–∫–∞','Cÿ@wB5Ø≠mËÜ‹j'),('∂∑_…òDA˘º°¢ê‡GH','2022-06-12 00:00:00',60000,'$á@!…J≤´á˜Cö*ƒ','G≥·<ıâCAµÔp€>\n§','–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','$á@!…J≤´á˜Cö*ƒ'),('æbA©©pÈŒ\Z‹Ô','2022-06-11 00:00:00',57000,'Æâ·¿7O‹´€W›Ëß Å','j„ò8\nŸFπâ—zú  rÍ','–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','Æâ·¿7O‹´€W›Ëß Å'),('… G-¨CÿÇÿ\'ﬁ◊ä≈\0','2022-05-08 13:29:11',9999999999.999,'ùr= √[Mæπäæl4‹æ†','Æ±ﬁ~\\F∞1≈Î#ó·','–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','ùr= √[Mæπäæl4‹æ†'),('Õ\\ÓÊ\"πKOíä/~èJ–-','2022-06-11 00:00:00',58000,'SÊ—nCÍ∂Ë”¡ﬁˆ ','L3ΩıœK|ç€C∑˙¬öö','–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','SÊ—nCÍ∂Ë”¡ﬁˆ '),('ÛƒOäŒ¬B˛º˚ùä©4∞','2022-06-10 00:00:00',-3050,'™–¿M˜¯@±dwå®˚ÉÁ','¡hÏ–ê\ZCóù˜0—›P%','–ó–∞–∫—É–ø–∫–∞','™–¿M˜¯@±dwå®˚ÉÁ');
/*!40000 ALTER TABLE `register_calculation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register_costprice`
--

DROP TABLE IF EXISTS `register_costprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register_costprice` (
  `id` binary(16) NOT NULL,
  `amount` double NOT NULL,
  `date` datetime NOT NULL,
  `idDoc` binary(16) NOT NULL,
  `idNom` binary(16) NOT NULL,
  `profit` double NOT NULL,
  `profitByUnit` double NOT NULL,
  `sumCostprice` double NOT NULL,
  `sumSale` double NOT NULL,
  `typeDoc` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–†–µ–≥–∏—Å—Ç—Ä —Å–µ–±–µ—Å—Ç–æ–∏–º–æ—Å—Ç—å –ø—Ä–æ–¥—É–∫—Ü–∏–∏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register_costprice`
--

LOCK TABLES `register_costprice` WRITE;
/*!40000 ALTER TABLE `register_costprice` DISABLE KEYS */;
INSERT INTO `register_costprice` VALUES ('y*˙àxO¨ö:ÿzı¨ùﬂ',1,'2022-06-11 00:00:00','L3ΩıœK|ç€C∑˙¬öö','ß=â˝}G¬õ¯Ø#å8_¿',7642.8,7642.8,357.1,8000,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è'),('‰ ˘å∑LÍëıL=Bfû',1,'2022-06-11 00:00:00','L3ΩıœK|ç€C∑˙¬öö',':OEN•L¢¶”9¿˛ˆR',49045.4,49045.4,954.5,50000,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è'),('(¢róq2LVèª⁄Lâ=«<',1,'2022-06-11 00:00:00','j„ò8\nŸFπâ—zú  rÍ','Ê,úQCóG°√∂âÛÖ≠Ï',3880,3880,120,4000,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è'),('+ˇ∑™H%K»ìxù≤˝Íú',1,'2022-06-12 00:00:00','G≥·<ıâCAµÔp€>\n§','G%∫´¢GiªèÃ“Ω0›',4918.75,4918.75,81.25,5000,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è'),('3Õs· ∞Là∞›±À†˘Â',1,'2022-06-11 00:00:00','Q“˚;*ÈK.†äe¡≠RÃ','—, \\{BN≥|åçL',9918.75,9918.75,81.25,10000,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è'),('?”ÿS©ÙJ∂∞˚Óa	˝L',1,'2022-06-12 00:00:00','G≥·<ıâCAµÔp€>\n§','\'∏å~≠˘L¿±⁄ƒ∑pÖÁ+',4950,4950,50,5000,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è'),('U≠Û∏øAÍ©ÿ,p¸Õ¬e',2,'2022-06-11 00:00:00','Q“˚;*ÈK.†äe¡≠RÃ','\rÂ{˜TiGUå\n©ÜÂwË',3760,1880,240,4000,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è'),('®5Ü?JRê\rÖ<æâ∏',1,'2022-06-11 00:00:00','j„ò8\nŸFπâ—zú  rÍ','Uπß4ßI}óA˛4ë6Óõ',2950,2950,50,3000,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è'),('∂!õ/Hë¢h@Û´;Zﬁ',1,'2022-06-12 00:00:00','G≥·<ıâCAµÔp€>\n§','Åcqy%¶NﬁªÚYÁF&Ç',49045.46,49045.46,954.54,50000,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è'),('Óe	IËÉIS∏Pí9¬Æ:m',1,'2022-06-11 00:00:00','j„ò8\nŸFπâ—zú  rÍ','cñ;Û¯ƒB™≠^∫1;LÊt',49918.75,49918.75,81.25,50000,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è');
/*!40000 ALTER TABLE `register_costprice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_part_material_consuption`
--

DROP TABLE IF EXISTS `table_part_material_consuption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_part_material_consuption` (
  `id` binary(16) NOT NULL,
  `amount` double NOT NULL,
  `lineNumber` int(11) DEFAULT NULL,
  `nomenclature_id` binary(16) NOT NULL,
  `production_stages_id` binary(16) NOT NULL,
  `doc_manufacture_id` binary(16) NOT NULL,
  `idDoc` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nomenclature_fkey_mat_cons` (`nomenclature_id`),
  KEY `doc_manufacture_fkey` (`doc_manufacture_id`),
  KEY `pr_stages_fke` (`production_stages_id`),
  CONSTRAINT `doc_manufacture_fkey` FOREIGN KEY (`doc_manufacture_id`) REFERENCES `doc_manufacture` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `nomenclature_fkey_mat_cons` FOREIGN KEY (`nomenclature_id`) REFERENCES `nomenclature` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `pr_stages_fke` FOREIGN KEY (`production_stages_id`) REFERENCES `production_stages` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–¢–∞–±–ª–∏—á–Ω–∞—è —á–∞—Å—Ç—å "—Ä–∞—Å—Ö–æ–¥ –º–∞—Ç–µ—Ä–∏–∞–ª–æ–≤"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_part_material_consuption`
--

LOCK TABLES `table_part_material_consuption` WRITE;
/*!40000 ALTER TABLE `table_part_material_consuption` DISABLE KEYS */;
INSERT INTO `table_part_material_consuption` VALUES ('\Z$\"MdìL–ò!w\\æãµ\"',1,1,'.{g…£I[¥Ωìä≥[ t','\rÆf\rLŒ©7xc\r>XC','‘	ääÌI\"ä4N¿fÛu','‘	ääÌI\"ä4N¿fÛu'),('ôdÏÌK»Ωﬁh˜s∫e\r',1,1,'XTÂ¥∞I@°ÅÖûiF','˚Á8ÿ¢/@ΩØF‡∆Ò0ï','|[H±´I≤®iLÊ(Ô','|[H±´I≤®iLÊ(Ô'),('!›%YÜ‚HËû≥~#`9',1,1,'AqXŸG0™ó\nÍF\"5','k¬>Ü–RJÕæ™£M¯≥mY','uÅÛ/‚HK•ïÚﬁ˛∑Gã-','uÅÛ/‚HK•ïÚﬁ˛∑Gã-'),('767T]ÉC1±·\n˛lÁ◊µ',1,2,'ìYW˛õIüHqÏ/≈∂q','\rÆf\rLŒ©7xc\r>XC','‘	ääÌI\"ä4N¿fÛu','‘	ääÌI\"ä4N¿fÛu'),('@{Tøl¯LÇÄw	gÖ⁄*X',2,2,'yjV˛YBµ∂Õ»`Õ¶9','k¬>Ü–RJÕæ™£M¯≥mY','uÅÛ/‚HK•ïÚﬁ˛∑Gã-','uÅÛ/‚HK•ïÚﬁ˛∑Gã-'),('VQÅ—\'}C†OñBÀm§Y',1,3,'âäkèGrïÁ@åÀ6','k¬>Ü–RJÕæ™£M¯≥mY','uÅÛ/‚HK•ïÚﬁ˛∑Gã-','uÅÛ/‚HK•ïÚﬁ˛∑Gã-'),('i˚‹[ÊâOò‘r´ôÙ÷',1,2,'.{g…£I[¥Ωìä≥[ t','˚Á8ÿ¢/@ΩØF‡∆Ò0ï','|[H±´I≤®iLÊ(Ô','|[H±´I≤®iLÊ(Ô'),('Å◊∞`’Ex¨øD<Dà\0°',2,1,'\Z9¬≥æKTí¬H(U:±•','fàKˆã\\Hî˝)j>Åë','ß(Yü®˙NC§8˚Æ*∏Vr','ß(Yü®˙NC§8˚Æ*∏Vr'),('ú¯˝\';ÿG^î4Œ]º~ß',1,3,'<6“„=GE≤Àæº¡',' v•ÕÌAòîgVXœû','|[H±´I≤®iLÊ(Ô','|[H±´I≤®iLÊ(Ô'),('ûYwÍ>LÔûû˜≥=∏pa',3,1,'ìYW˛õIüHqÏ/≈∂q','\rÆf\rLŒ©7xc\r>XC','Øæ]”ç´H¡π∑õAw»F©','Øæ]”ç´H¡π∑õAw»F©'),('üﬂÉ«SG“µ–ﬁ∑\rsÁx',3,2,'Ï\"\'”ÅD”¥Ï´/kG–œ','\rÆf\rLŒ©7xc\r>XC','Øæ]”ç´H¡π∑õAw»F©','Øæ]”ç´H¡π∑õAw»F©'),('Ø9^˘tREåõiI\0DÛ%',4,4,'’d‡†lMñ§súì‡∂C','k¬>Ü–RJÕæ™£M¯≥mY','uÅÛ/‚HK•ïÚﬁ˛∑Gã-','uÅÛ/‚HK•ïÚﬁ˛∑Gã-'),(' =ÑÉáKpèüˆ¿a⁄',2,2,'5	øßC\0Oı∞∏æe)|Nû','fàKˆã\\Hî˝)j>Åë','ß(Yü®˙NC§8˚Æ*∏Vr','ß(Yü®˙NC§8˚Æ*∏Vr');
/*!40000 ALTER TABLE `table_part_material_consuption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register_products_in_stock`
--

DROP TABLE IF EXISTS `register_products_in_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register_products_in_stock` (
  `id` binary(16) NOT NULL,
  `amount` double NOT NULL,
  `idDoc` binary(16) NOT NULL,
  `sum` double NOT NULL,
  `typeDoc` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `idNom` binary(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–ó–∞–ø–∏—Å—å —Ä–µ–≥–∏—Å—Ç—Ä–∞ –¢–æ–≤–∞—Ä—ã–ù–∞–°–∫–ª–∞–¥–∞—Ö';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register_products_in_stock`
--

LOCK TABLES `register_products_in_stock` WRITE;
/*!40000 ALTER TABLE `register_products_in_stock` DISABLE KEYS */;
INSERT INTO `register_products_in_stock` VALUES ('	2T™ñ]B‰ÇÈíÉ·å',-1,'Æ±ﬁ~\\F∞1≈Î#ó·',0,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-05-08 13:29:11','∂\"%ãnYJü ¿Êpë£ˇ'),('•–†^ÕBi£-≠≈Ÿ¸',4,'√\"ºïJ0ñ§©9TbíÕ',2000,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','Àx{œGõC˛íEΩ ˜Ÿ≥'),('¡m◊ã[F>ü[À\'>ñ',-1,'G≥·<ıâCAµÔp€>\n§',-954.54,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-06-12 00:00:00','Åcqy%¶NﬁªÚYÁF&Ç'),('ˆëÌbN≠àÜ|º∫',-1,'|[H±´I≤®iLÊ(Ô',-800,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','XTÂ¥∞I@°ÅÖûiF'),('\"-–‚ÕπI§ﬂÿƒÇÛ7Â',-1,'j„ò8\nŸFπâ—zú  rÍ',-81.25,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-06-11 00:00:00','cñ;Û¯ƒB™≠^∫1;LÊt'),('#ûáû.L˙î∫=äƒ®',7,'f¢/Fö\'@o§ÏZ…y-—k',4900,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','yjV˛YBµ∂Õ»`Õ¶9'),('(d*‘PN\0û∫≈Îà ´ˆ',5,'|[H±´I≤®iLÊ(Ô',600,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','\rÂ{˜TiGUå\n©ÜÂwË'),('-ÌLÜÅ@KlÄ7pmF≥ƒ’',6,'f¢/Fö\'@o§ÏZ…y-—k',5400,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','s=&Ôè_NÇ°⁄≤9Ô„·'),('1∞uîWhK£∏Ó£.^òÕ',5,'ß(Yü®˙NC§8˚Æ*∏Vr',250,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','\'∏å~≠˘L¿±⁄ƒ∑pÖÁ+'),(';]Èw†íO‚é{∆n§',12,'‘	ääÌI\"ä4N¿fÛu',975,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-11 00:00:00','—, \\{BN≥|åçL'),('?fëóœ>Bî∏%°ïò^æ',5,'|[H±´I≤®iLÊ(Ô',600,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','Ê,úQCóG°√∂âÛÖ≠Ï'),('C§ΩI]¿Nn¢\'ß1íE|',-2,'Q“˚;*ÈK.†äe¡≠RÃ',-240,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-06-11 00:00:00','\rÂ{˜TiGUå\n©ÜÂwË'),('NJ\ZÕ∂CUÇ#oã8˙rO',8,'∑N ‘p∞O3ä™53<TÈ\r',12000,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','Ï\"\'”ÅD”¥Ï´/kG–œ'),('Tê¬øÙA.¨>h|9ûÒ-',-1,'G≥·<ıâCAµÔp€>\n§',-50,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-06-12 00:00:00','\'∏å~≠˘L¿±⁄ƒ∑pÖÁ+'),('TòQeÙCxî∫êz◊ºÇ',-1,'|[H±´I≤®iLÊ(Ô',-400,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','<6“„=GE≤Àæº¡'),('\\å!WëH2áiê;Ïò',5,'Â‘Ì\0H~†Ràa+)l4',4000,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','XTÂ¥∞I@°ÅÖûiF'),('n°∑∑h‡Iyå°{}\'>l',-3,'Øæ]”ç´H¡π∑õAw»F©',-6000,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','ìYW˛õIüHqÏ/≈∂q'),('qq?ÃTFx¢À6Í„Øû',-1,'L3ΩıœK|ç€C∑˙¬öö',-357.1,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-06-11 00:00:00','ß=â˝}G¬õ¯Ø#å8_¿'),('qÇÿO˘H0©√{á2‡ö\'',10,'‘	ääÌI\"ä4N¿fÛu',812.5,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-11 00:00:00','G%∫´¢GiªèÃ“Ω0›'),('rEv˚©KCΩ‘}4˙≤}',-1,'uÅÛ/‚HK•ïÚﬁ˛∑Gã-',-800,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','AqXŸG0™ó\nÍF\"5'),('t/ƒi÷EW≠4[Nô=Â',-1,'L3ΩıœK|ç€C∑˙¬öö',-954.5,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-06-11 00:00:00',':OEN•L¢¶”9¿˛ˆR'),('~ëSFUENß®ºÆ¡#\'',5,'Øæ]”ç´H¡π∑õAw»F©',4772.7,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','Åcqy%¶NﬁªÚYÁF&Ç'),('Ü«√û>Nmì*∑NÉ›û',-3,'Øæ]”ç´H¡π∑õAw»F©',-4500,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','Ï\"\'”ÅD”¥Ï´/kG–œ'),('Ü‰Ó6î©E©º¿-]Tßı',-2,'uÅÛ/‚HK•ïÚﬁ˛∑Gã-',-1400,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','yjV˛YBµ∂Õ»`Õ¶9'),('èfTÚNZàH¬‚Ê=†?',-1,'G≥·<ıâCAµÔp€>\n§',-81.25,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-06-12 00:00:00','G%∫´¢GiªèÃ“Ω0›'),('ûWﬁÏ«ôCjÆ¢\'õ|’YV',5,'√\"ºïJ0ñ§©9TbíÕ',1500,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','âäkèGrïÁ@åÀ6'),('¢ áEûKm∫ÂFæˆ≥',-1,'|[H±´I≤®iLÊ(Ô',-600,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','.{g…£I[¥Ωìä≥[ t'),('ßmÌÂvƒO∏û5jÂ*U≠',3,'√\"ºïJ0ñ§©9TbíÕ',2400,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','AqXŸG0™ó\nÍF\"5'),('ßñ8BPJº∂âK©ÊÕ}˘',4,'Â‘Ì\0H~†Ràa+)l4',2400,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','.{g…£I[¥Ωìä≥[ t'),('®[ˆ¸∆€FÓ•\nıyëªå',-1,'Q“˚;*ÈK.†äe¡≠RÃ',-81.25,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-06-11 00:00:00','—, \\{BN≥|åçL'),('®˛Òœ<–Mã®ù\\©m…S',10,'‘	ääÌI\"ä4N¿fÛu',812.5,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-11 00:00:00','cñ;Û¯ƒB™≠^∫1;LÊt'),('´AÉäpôIlÅ˛+`W∑Ú',12,'f¢/Fö\'@o§ÏZ…y-—k',4800,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','<6“„=GE≤Àæº¡'),('Æ™9ÃÕE§ÈÁúâ¨zÍ',9,'∑N ‘p∞O3ä™53<TÈ\r',18000,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','ìYW˛õIüHqÏ/≈∂q'),('±IŸïHÔDÆï\rŒ∞¢ˇ',-1,'‘	ääÌI\"ä4N¿fÛu',-2000,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-11 00:00:00','ìYW˛õIüHqÏ/≈∂q'),('≥h†”ªI;Ü–É∆*å°ã',-2,'ß(Yü®˙NC§8˚Æ*∏Vr',0,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','\Z9¬≥æKTí¬H(U:±•'),('√áRTßJÓè’mÚ≥I¡l',5,'|[H±´I≤®iLÊ(Ô',600,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','‘ÆÙD‡^A∏R]Û\'´@—'),('ƒb«÷$O1¶ƒ5‚t',5,'¡hÏ–ê\ZCóù˜0—›P%',1250,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','5	øßC\0Oı∞∏æe)|Nû'),('œﬁ˝Ÿ]»Hƒ°~\rπΩrì',5,'¡hÏ–ê\ZCóù˜0—›P%',1000,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','< ˝˜EúêzÌ—u'),('“55ÇaH‰πçK¢îCúv',6,'Øæ]”ç´H¡π∑õAw»F©',5727.2,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00',':OEN•L¢¶”9¿˛ˆR'),('ÿ≤\\\'…ISøÎáNDîh',4,'uÅÛ/‚HK•ïÚﬁ˛∑Gã-',1428.5,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','0Hf∏¡ûAœÇ\0\nÛ»> π'),('€»ˆE7◊@hüˆJ∂mÿ',-4,'uÅÛ/‚HK•ïÚﬁ˛∑Gã-',0,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','’d‡†lMñ§súì‡∂C'),('ﬂÀÅØF∏Fg∂ÜR∏√Ë(',-1,'j„ò8\nŸFπâ—zú  rÍ',-50,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-06-11 00:00:00','Uπß4ßI}óA˛4ë6Óõ'),('ÊJ[ﬁ=yLRµ˝œﬁ˙	±',-2,'ß(Yü®˙NC§8˚Æ*∏Vr',-500,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','5	øßC\0Oı∞∏æe)|Nû'),('ËJ}-\"•@öúóë@∂L',-1,'‘	ääÌI\"ä4N¿fÛu',-600,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-11 00:00:00','.{g…£I[¥Ωìä≥[ t'),('Ï˝?&≥R@^ö€–zó–yI',5,'ß(Yü®˙NC§8˚Æ*∏Vr',250,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','Uπß4ßI}óA˛4ë6Óõ'),('Ì›¶üÔóB\0ãˇU≈ƒ',3,'uÅÛ/‚HK•ïÚﬁ˛∑Gã-',1071.4,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','ß=â˝}G¬õ¯Ø#å8_¿'),('ÓÉQ´DÓóa“*—‰W—',4,'¡hÏ–ê\ZCóù˜0—›P%',800,'–ó–∞–∫—É–ø–∫–∞','2022-06-10 00:00:00','√Ç«ïM ßØu`8ô'),('ÔCmUwD‹ø2}ù•ü≤Á',-1,'j„ò8\nŸFπâ—zú  rÍ',-120,'–†–µ–∞–ª–∏–∑–∞—Ü–∏—è','2022-06-11 00:00:00','Ê,úQCóG°√∂âÛÖ≠Ï'),('Ò9Zµv–G>•õj◊\'$èo',-1,'uÅÛ/‚HK•ïÚﬁ˛∑Gã-',-300,'–ü—Ä–æ–∏–∑–≤–æ–¥—Å—Ç–≤–æ','2022-06-10 00:00:00','âäkèGrïÁ@åÀ6');
/*!40000 ALTER TABLE `register_products_in_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_part_produced_of_products`
--

DROP TABLE IF EXISTS `table_part_produced_of_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_part_produced_of_products` (
  `id` binary(16) NOT NULL,
  `amount` double NOT NULL,
  `lineNumber` int(11) DEFAULT NULL,
  `nomenclature_id` binary(16) NOT NULL,
  `doc_manufacture_id` binary(16) NOT NULL,
  `idDoc` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nomenclature_fkey_produced` (`nomenclature_id`),
  KEY `doc_manufacture_fkkey` (`doc_manufacture_id`),
  CONSTRAINT `doc_manufacture_fkkey` FOREIGN KEY (`doc_manufacture_id`) REFERENCES `doc_manufacture` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `nomenclature_fkey_produced` FOREIGN KEY (`nomenclature_id`) REFERENCES `nomenclature` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='–¢–∞–±–ª–∏—á–Ω–∞—è —á–∞—Å—Ç—å "–ü—Ä–æ–∏–∑–≤–µ–¥–µ–Ω–æ —Ç–æ–≤–∞—Ä–æ–≤"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_part_produced_of_products`
--

LOCK TABLES `table_part_produced_of_products` WRITE;
/*!40000 ALTER TABLE `table_part_produced_of_products` DISABLE KEYS */;
INSERT INTO `table_part_produced_of_products` VALUES (')uqX≥rG=≥[{rØ.y¸',5,1,'\'∏å~≠˘L¿±⁄ƒ∑pÖÁ+','ß(Yü®˙NC§8˚Æ*∏Vr','ß(Yü®˙NC§8˚Æ*∏Vr'),(':ö›$ïK¡∫`∞Áy›',5,1,'Ê,úQCóG°√∂âÛÖ≠Ï','|[H±´I≤®iLÊ(Ô','|[H±´I≤®iLÊ(Ô'),('CÙÕrlJKßä[K¯TæÔ',4,1,'0Hf∏¡ûAœÇ\0\nÛ»> π','uÅÛ/‚HK•ïÚﬁ˛∑Gã-','uÅÛ/‚HK•ïÚﬁ˛∑Gã-'),('Q#«Nz≤LiØ	ﬁ\'\0∏',6,1,':OEN•L¢¶”9¿˛ˆR','Øæ]”ç´H¡π∑õAw»F©','Øæ]”ç´H¡π∑õAw»F©'),('^€]⁄ø\rLwç€π∏∏¥',3,2,'ß=â˝}G¬õ¯Ø#å8_¿','uÅÛ/‚HK•ïÚﬁ˛∑Gã-','uÅÛ/‚HK•ïÚﬁ˛∑Gã-'),('hˆ5®â>B”ék¥jÙ@!',12,1,'—, \\{BN≥|åçL','‘	ääÌI\"ä4N¿fÛu','‘	ääÌI\"ä4N¿fÛu'),('r®˜:ëÂHﬂñ\rëÏB]ÉÍ',5,2,'Åcqy%¶NﬁªÚYÁF&Ç','Øæ]”ç´H¡π∑õAw»F©','Øæ]”ç´H¡π∑õAw»F©'),('Æê™ä68AË¥…¿tl NU',5,2,'\rÂ{˜TiGUå\n©ÜÂwË','|[H±´I≤®iLÊ(Ô','|[H±´I≤®iLÊ(Ô'),('÷çjçr™Oˆ•¯ÜX>âbÑ',10,2,'cñ;Û¯ƒB™≠^∫1;LÊt','‘	ääÌI\"ä4N¿fÛu','‘	ääÌI\"ä4N¿fÛu'),('‡˘MKHoó∞ ¢¶ıÇ¬',10,3,'G%∫´¢GiªèÃ“Ω0›','‘	ääÌI\"ä4N¿fÛu','‘	ääÌI\"ä4N¿fÛu'),('ÈÏ◊DBÕNõî!‚eÈ&Í‚',5,3,'‘ÆÙD‡^A∏R]Û\'´@—','|[H±´I≤®iLÊ(Ô','|[H±´I≤®iLÊ(Ô'),('T· lAl¶!Co\0¯0',5,2,'Uπß4ßI}óA˛4ë6Óõ','ß(Yü®˙NC§8˚Æ*∏Vr','ß(Yü®˙NC§8˚Æ*∏Vr');
/*!40000 ALTER TABLE `table_part_produced_of_products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-11 21:29:04
