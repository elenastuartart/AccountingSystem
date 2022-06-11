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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Документ закупка';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_purchase`
--

LOCK TABLES `doc_purchase` WRITE;
/*!40000 ALTER TABLE `doc_purchase` DISABLE KEYS */;
INSERT INTO `doc_purchase` VALUES ('���\0H~�R�a+)l4','2022-06-10 00:00:00',6400,43123085,'','�ʒ(�xL^��/P��_�'),('f�/F�\'@o��Z�y-�k','2022-06-10 00:00:00',15100,96487201,'','��0��B�n�T<��@'),('�N �p�O3��53<T�\r','2022-06-10 00:00:00',30000,23396703,'','����}Bݑ[�[5!'),('�h�А\ZC���0��P%','2022-06-10 00:00:00',3050,49997271,'','���M��@�dw�����'),('�\"��J0���9Tb��','2022-06-10 00:00:00',5900,85375775,'','C�@wB5��m��j');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Справочник "контрагенты"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contragent`
--

LOCK TABLES `contragent` WRITE;
/*!40000 ALTER TABLE `contragent` DISABLE KEYS */;
INSERT INTO `contragent` VALUES ('$�@!�J����C�*�','Санкт-Петербург, ул. Парковая, д. 13',66432266,'contragent08@mail.ru','Вероника Петрова','покупатель'),('�ʖU�Iw�>�h��\r�','Москва',29336972,'contragent11@mail.ru','More dolls','продавец'),('\0�[N���m^\nW�','Москва',69074207,'contragent04@mail.ru','BJD Boutique','продавец'),('C�@wB5��m��j','Москва',70816631,'contragent15@mail.ru','Ткани shop','Продавец'),('����}Bݑ[�[5!','Москва',72796914,'contragent13@mail.ru','Литейная №1','продавец'),('S��nC������ ','Реутов',18390586,'contragent10@mail.ru','Malvinafashiondoll, Марина Козлова','покупатель'),('/B�)zN8���u�','Москва',47567170,'contragent05@mail.ru','Doll shop','продавец'),('V6{B�P��ǖ�','Самара',51391538,'contragent06@mail.ru','Анна Сергеева','покупатель'),('r�jzD�6[ ','Москва',43414476,'contragent17@mail.ru','Магазин фурнитуры','Продавец'),('z��U��Lg�jvEc�','Москва',26469982,'contragent16@mail.ru','ReinStudio','Продавец'),('z�)��Ft��� 3p�T','Нижний Новгород, ул. Победы 3',75089420,'contragent12@mail.ru','Елена Сергеева','покупатель'),('��0�k6M2�y��~(�8','Москва',10714421,'contragent03@mail.ru','Doll_friends','продавец'),('�r= �[M����l4ܾ�','Москва, пр. Вернадского, д. 14',20220101,'bjd_doll_house@mail.ru','My company \"BJD DOLL HOUSE\"','продавец  '),('���M��@�dw�����','Москва',76871376,'contragent20@mail.ru','Hair_dolls_shop','Продавец'),('����7Oܫ�W�� �','Тверь',95002244,'contragent07@mail.ru','Илья Иванов','покупатель'),('��0��B�n�T<��@','Москва',62884482,'contragent01@mail.ru','Fashion dolls','продавец'),('�N>�elO^�WrG�{�','Санкт-Петербург',15845140,'contragent09@mail.ru','Ольга Певцова','покупатель'),('�ʒ(�xL^��/P��_�','Москва',71873576,'contragent18@mail.ru','Пластик для принтера shop','Продавец'),('�q��sL9�����u�','Москва',66867789,'contragent19@mail.ru','Фурнитура shop','Продавец'),('�1�3BR�(M�����','Москва',41480981,'contragent02@mail.ru','Doll doll','продавец');
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
  `amount` double NOT NULL COMMENT 'количество',
  `lineNumber` int(11) DEFAULT NULL COMMENT 'номер строки',
  `price` double NOT NULL COMMENT 'цена',
  `sum` double DEFAULT NULL COMMENT 'сумма',
  `nomenclature_id` binary(16) NOT NULL,
  `doc_purchase_id` binary(16) NOT NULL,
  `idDoc` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nomenclature_fkey_purchase` (`nomenclature_id`),
  KEY `doc_purchase_fkey` (`doc_purchase_id`),
  CONSTRAINT `doc_purchase_fkey` FOREIGN KEY (`doc_purchase_id`) REFERENCES `doc_purchase` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nomenclature_fkey_purchase` FOREIGN KEY (`nomenclature_id`) REFERENCES `nomenclature` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Табличная часть "закупка"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_part_purchase`
--

LOCK TABLES `table_part_purchase` WRITE;
/*!40000 ALTER TABLE `table_part_purchase` DISABLE KEYS */;
INSERT INTO `table_part_purchase` VALUES ('�5ᴮKG�B}�՝:',3,1,800,2400,'AqX�G0��\n�F\"5','�\"��J0���9Tb��','�\"��J0���9Tb��'),('��wc^H\n��JleH?a',7,1,700,4900,'yjV�YB����`ͦ9','f�/F�\'@o��Z�y-�k','f�/F�\'@o��Z�y-�k'),('>��s\"�Hޤ,/��j�',4,1,600,2400,'.{gɣI[�����[�t','���\0H~�R�a+)l4','���\0H~�R�a+)l4'),('P�$�h�K���b\nS\nC',9,1,2000,18000,'�YW��I�Hq�/Ŷq','�N �p�O3��53<T�\r','�N �p�O3��53<T�\r'),('o�\n/�oL}� (���%�',5,1,200,1000,'< ��E��z��u','�h�А\ZC���0��P%','�h�А\ZC���0��P%'),('��L[�I�@���',4,2,200,800,'Â��Mʧ�u`8�','�h�А\ZC���0��P%','�h�А\ZC���0��P%'),('�s�H��E^���B�Ŗ',12,3,400,4800,'<6��=GE�����','f�/F�\'@o��Z�y-�k','f�/F�\'@o��Z�y-�k'),('����N�G��I�ZN�',5,2,300,1500,'��k�Gr��@��6','�\"��J0���9Tb��','�\"��J0���9Tb��'),('�L}��\ZFH�\0�U�A�',5,3,250,1250,'5	��C\0O����e)|N�','�h�А\ZC���0��P%','�h�А\ZC���0��P%'),('�(�)�DI�M3�||��',4,3,500,2000,'�x{�G�C��E� ���','�\"��J0���9Tb��','�\"��J0���9Tb��'),('ƫF�DlI鈺e/`�?�',5,2,800,4000,'XT崰I@����iF','���\0H~�R�a+)l4','���\0H~�R�a+)l4'),('�GA��9G���l�\Z',8,2,1500,12000,'�\"\'ӁDӴ�/kG��','�N �p�O3��53<T�\r','�N �p�O3��53<T�\r'),('�w���IG�����\r]#!',6,2,900,5400,'s=&�_N��ڲ9���','f�/F�\'@o��Z�y-�k','f�/F�\'@o��Z�y-�k');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Справочник "Стадии производства"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_stages`
--

LOCK TABLES `production_stages` WRITE;
/*!40000 ALTER TABLE `production_stages` DISABLE KEYS */;
INSERT INTO `production_stages` VALUES ('\r�f\rLΩ7xc\r>XC',64239158,'изготовление деталей для сборки куклы','литье деталей'),('�v���A��gVX��',44320909,'приклеивание кожи/др материала на подошву, фиксирование на колодке, финальная отделка','изготовление обуви'),('K� �\'�C>�v�,i�',89280188,' ','шлифовка деталей'),('f�K��\\H��)j>��',34706467,' ','изготовление парика'),('iir�9aKQ�:��\Z���',97793844,' описание','шлифовка деталей'),('k�>��RJ;��M��mY',10222942,'описание','изготовление одежды'),('v��P��K��juk�Xi}',98039286,' ','изготовление каркаса для парика'),('�&����Bh�NA��)�',62753609,'макияж, роспись тела по желанию заказчика/по шаблону ','роспись куклы'),('��s.?Dd�D�\Z\'$R!',52813835,'роспись по желанию заказчика','художественная роспись'),('��p\0T�B��`�@>oC�',41601841,' ','укладка парика'),('�n=��PJ��y�W�xJ',70581890,' ','сборка куклы'),('��8آ/@��F���0�',10996343,' ','печать подошвы для обуви');
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
  `amount` double NOT NULL COMMENT 'количество',
  `lineNumber` int(11) DEFAULT NULL COMMENT 'номер строки',
  `price` double NOT NULL COMMENT 'цена',
  `sum` double DEFAULT NULL COMMENT 'сумма',
  `nomenclature_id` binary(16) NOT NULL,
  `doc_sale_id` binary(16) NOT NULL,
  `idDoc` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nomenclature_fkey_products` (`nomenclature_id`),
  KEY `doc_sale_fkey` (`doc_sale_id`),
  CONSTRAINT `doc_sale_fkey` FOREIGN KEY (`doc_sale_id`) REFERENCES `doc_sale` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `nomenclature_fkey_products` FOREIGN KEY (`nomenclature_id`) REFERENCES `nomenclature` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Табличная часть "Список товаров" для реализации';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_part_list_of_products`
--

LOCK TABLES `table_part_list_of_products` WRITE;
/*!40000 ALTER TABLE `table_part_list_of_products` DISABLE KEYS */;
INSERT INTO `table_part_list_of_products` VALUES ('@�i?D�C&���t��4�',1,1,4000,4000,'�,�QC�G�ö���','j�8\n�F���z�  r�','j�8\n�F���z�  r�'),('Z�Len�A[�	zE�J�-',1,1,50000,50000,'�cqy%�N޻�Y�F&�','G��<��CA��p�>\n�','G��<��CA��p�>\n�'),('f&��T�M���=��F',1,1,99.999,99.999,'�\"%�nYJ����p���','���~\\F�1��#��','���~\\F�1��#��'),('�f���LHӂ���^�0',1,3,5000,5000,'\'��~��L���ķp��+','G��<��CA��p�>\n�','G��<��CA��p�>\n�'),('�H��>L!��ϻ]�h�',1,1,50000,50000,':OEN�L���9���R','L3���K|��C���','L3���K|��C���'),('����7O�BȻ-\n',1,2,50000,50000,'c�;���B��^�1;L�t','j�8\n�F���z�  r�','j�8\n�F���z�  r�'),('���	�2LՈn��9�',1,2,10000,10000,'�, \\{BN�|��L','Q��;*�K.��e��R�','Q��;*�K.��e��R�'),('���jM���x8R[',1,3,3000,3000,'U��4�I}�A�4�6�','j�8\n�F���z�  r�','j�8\n�F���z�  r�'),('�=}(w\nK>���u~�j',1,2,5000,5000,'G%���Gi���ҽ0�','G��<��CA��p�>\n�','G��<��CA��p�>\n�'),('�\r��V�F)������',2,1,2000,4000,'\r�{�TiGU�\n���w�','Q��;*�K.��e��R�','Q��;*�K.��e��R�'),('��:VH��1>.��O',1,2,8000,8000,'�=��}G��#�8_�','L3���K|��C���','L3���K|��C���');
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
INSERT INTO `nomenclature` VALUES (':OEN�L���9���R',29443114,'Товары на реализацию',29443114,'Молд Софья, скин \"Tan\", шт','Кукла бланк','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('U��4�I}�A�4�6�',83814807,'Товары на реализацию',83814807,'Парик прямой из альпаки, длинный, шт ','Парики','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('v���E�\n��X�',77360920,'Материалы для производства',77360920,'Трессы из альпаки прямые, 20 см длина, 1 м','Изготовление париков','���M��@�dw�����','���M��@�dw�����'),('Cg?�H�\Z#e{)�',61397400,'Товары на реализацию',61397400,'Босоножки белые со стразами','обувь','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('\r�{�TiGU�\n���w�',47125568,'Товары на реализацию',47125568,'Колодки для обуви','товары для мастеров','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('v��oC@���aqH',76196015,'Материалы для производства',76196015,'Спрей для укладки, 1 шт','изготовление париков','���M��@�dw�����','���M��@�dw�����'),('XT崰I@����iF',60789220,'Товары для производства',60789220,'ABS-пластик для принтера, уп','печать моделей','�ʒ(�xL^��/P��_�','�ʒ(�xL^��/P��_�'),('�d�lM��s���C',56180394,'Материалы для производства',56180394,'Молния для одежды 5 см, шт','Изготовление одежды','r�jzD�6[ ','r�jzD�6[ '),('\Z9³�KT��H(U:��',30139040,'Товары для производства',30139040,'Трессы из ангорской козы 15 см волнистые, 1 м','Изготовление париков','���M��@�dw�����','���M��@�dw�����'),('\Zs)�}_CD���~��',50728878,'Материалы для производства',50728878,'Жгут белый 5мм, Япония, м','сборка моделей','��0�k6M2�y��~(�8','��0�k6M2�y��~(�8'),('AqX�G0��\n�F\"5',22700628,'Материалы для производства',22700628,'Атлас синий, м','ткань','C�@wB5��m��j','C�@wB5��m��j'),('\'��~��L���ķp��+',36723583,'Товары на реализацию',36723583,'Парик стрижка пикси из альпаки, шт','Парики','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('(�_(D���gF\0�q',5733676,'Товары на реализацию',5733676,'Туфли на балетную стопу матовые, шт','Обувь','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('.{gɣI[�����[�t',80047027,'Материалы для производства',80047027,'Пластик для 3D принтера','печать моделей','�ʒ(�xL^��/P��_�','�ʒ(�xL^��/P��_�'),('0Hf���Aς\0\n��>ʹ',70525288,'Товары на реализацию',70525288,'Комплект белья, шт','Одежда','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('1_��uGP��>�:���',97950039,'Материалы для производства',97950039,'Бисер красный 2мм, Япония, уп','фурнитура для шитья','�q��sL9�����u�','�q��sL9�����u�'),('5	��C\0O����e)|N�',86694250,'Материалы для производства',86694250,'Трессы альпаки, м','изготовление париков','���M��@�dw�����','���M��@�dw�����'),('9 �˻�N�����U�.�',62952377,'Материалы для производства',62952377,'Крючки мелкие для перетяжки, шт','сборка куклы','r�jzD�6[ ','r�jzD�6[ '),('< ��E��z��u',82223836,'Материалы для производства',82223836,'Шерсть ангорской козы, 100 гр','Изготовление париков','���M��@�dw�����','���M��@�dw�����'),('<6��=GE�����',31433907,'Материалы для производства',31433907,'Кожа черная обувная тонкая, м','изготовление обуви','��0��B�n�T<��@','��0��B�n�T<��@'),('G%���Gi���ҽ0�',14285148,'Товары на реализацию',14285148,'Художественная роспись, лицо, шт','услуги','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('c�;���B��^�1;L�t',14024916,'Товары на реализацию',14024916,'Молд Мелания, скин \"Ivory\", шт','Кукла бланк','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('s=&�_N��ڲ9���',57940439,'Материалы для производства',57940439,'Кожа бежевая, м','Изготовление обуви','��0��B�n�T<��@','��0��B�n�T<��@'),('yjV�YB����`ͦ9',36241607,'Материалы для производства',36241607,'Кожа черная премиум, м','Изготовление одежды','��0��B�n�T<��@','��0��B�n�T<��@'),('�cqy%�N޻�Y�F&�',87128280,'Товары на реализацию',87128280,'Молд Джеки, скин \"Normal\", шт','кукла бланк','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('�H̠:�Iਲ਼LJ���',93217418,'Товары на реализацию',93217418,'Парик омбре волнистый, шт','парики','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('��k�Gr��@��6',4982084,'Материалы для производства',4982084,'Шелк бежевый, м','Ткань','C�@wB5��m��j','C�@wB5��m��j'),('�YW��I�Hq�/Ŷq',34263107,'Материалы для производства',34263107,'Полиуретан цвет \"Normal\", кг','Литье моделей','����}Bݑ[�[5!','����}Bݑ[�[5!'),('��W�9�D��9(�$7\0',67911562,'Товары на реализацию',67911562,'Сережки серебряные с фианитами, шт','Украшения','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('�6ʃ��@$��a�x��',11139624,'Товары на реализацию',11139624,'Парик волнистый из альпаки, ср. длина, шт','Парики','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('�=��}G��#�8_�',37100597,'Товары на реализацию',37100597,'Куртка косуха из премиум кожи, шт','Одежда','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('����OJp����+�/',78870381,'Товары на реализацию',78870381,'Ботинки на каблучную стопу, шт','Обувь','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('��N��@˰l��j�',85964100,'Товары на реализацию',85964100,'Стенд для кукол с подсветкой','Аксессуары','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('�\"%�nYJ����p���',75735004,' ',20220101,'ПЕРВОНАЧАЛЬНЫЙ ПРИХОД Д/С',' ','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('���L�Gc��fnv�]�',48255858,'Материалы для производства',48255858,'Полиуретан бежевый тон карамель, кг','литье моделей','����}Bݑ[�[5!','����}Bݑ[�[5!'),('Â��Mʧ�u`8�',93266499,'Материалы для производства',93266499,'Локоны ангорской козы, 100 гр','изготовление париков','�1�3BR�(M�����','�1�3BR�(M�����'),('�x{�G�C��E� ���',67002384,'Материалы для производства',67002384,'Шелк синий, м','Ткань','C�@wB5��m��j','C�@wB5��m��j'),('�, \\{BN�|��L',74849493,'Товары на реализацию',74849493,'Манекен','товары для мастеров','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('ы��>\nCu������',66116432,'Материалы для производства',66116432,'Батист розовый, м','ткань','C�@wB5��m��j','C�@wB5��m��j'),('ԁ�1%%J�;��t���',44577570,'Товары на реализацию',44577570,'Парик из альпаки розовый прямой, шт','парики','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('Ԯ�D�^A�R]�\'�@�',73779353,'Товары на реализацию',73779353,'Балетки на плоскую стопу, шт','Обувь','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('ل���8JN�IqĦw�',27941802,'Материалы для производства',27941802,'Стразы сваровски, уп','фурнитура для шитья и украшений','r�jzD�6[ ','r�jzD�6[ '),('�,�QC�G�ö���',24882154,'Товары на реализацию',24882154,'Туфли на каблучную стопу, шт','обувь','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('�\"\'ӁDӴ�/kG��',75613925,'Материалы для производства',75613925,'Полиуретан тон Ivory, кг','литье моделей','����}Bݑ[�[5!','����}Bݑ[�[5!'),('�6�R5J8����G�#-',81905731,'Материалы для производства',81905731,'Застежки круглые, шт','фурнитура','�q��sL9�����u�','�q��sL9�����u�'),('���Y	@�=�ן��',79204007,'Товары на реализацию',79204007,'Доп. кисти перчатки, шт','Аксессуары','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�'),('�,�VFJ^�S����',69833727,'Материалы для производства',69833727,'Кружево тонкое белое, м','ткань','/B�)zN8���u�','/B�)zN8���u�'),('�;���L&�P�ܐU',74403825,'Товары на реализацию',74403825,'Художественная роспись, тело, шт','услуги','�r= �[M����l4ܾ�','�r= �[M����l4ܾ�');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Документ "Производство"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_manufacture`
--

LOCK TABLES `doc_manufacture` WRITE;
/*!40000 ALTER TABLE `doc_manufacture` DISABLE KEYS */;
INSERT INTO `doc_manufacture` VALUES ('|[H��I��iL�(�','2022-06-10 00:00:00',13316875,''),('u��/�HK������G�-','2022-06-10 00:00:00',69950924,''),('�(Y���NC�8��*�Vr','2022-06-10 00:00:00',79728973,''),('��]Ӎ�H����Aw�F�','2022-06-10 00:00:00',46291383,''),('�	���I\"�4N�f�u','2022-06-11 00:00:00',16057444,'');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Документ "Реализация"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_sale`
--

LOCK TABLES `doc_sale` WRITE;
/*!40000 ALTER TABLE `doc_sale` DISABLE KEYS */;
INSERT INTO `doc_sale` VALUES ('G��<��CA��p�>\n�','2022-06-12 00:00:00',60000,63460945,'','$�@!�J����C�*�'),('L3���K|��C���','2022-06-11 00:00:00',58000,8947997,'','S��nC������ '),('Q��;*�K.��e��R�','2022-06-11 00:00:00',14000,79829347,'','�ʖU�Iw�>�h��\r�'),('j�8\n�F���z�  r�','2022-06-11 00:00:00',57000,53842240,'','����7Oܫ�W�� �'),('���~\\F�1��#��','2022-05-08 13:29:11',99.999,20220101,'','�r= �[M����l4ܾ�');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Регистр взаиморасчеты';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register_calculation`
--

LOCK TABLES `register_calculation` WRITE;
/*!40000 ALTER TABLE `register_calculation` DISABLE KEYS */;
INSERT INTO `register_calculation` VALUES ('����F���ph�_-','2022-06-10 00:00:00',-30000,'����}Bݑ[�[5!','�N �p�O3��53<T�\r','Закупка','����}Bݑ[�[5!'),('/�A��Nw�(2`��','2022-06-10 00:00:00',-6400,'�ʒ(�xL^��/P��_�','���\0H~�R�a+)l4','Закупка','�ʒ(�xL^��/P��_�'),('L�*���I#��pļO�>','2022-06-10 00:00:00',-15100,'��0��B�n�T<��@','f�/F�\'@o��Z�y-�k','Закупка','��0��B�n�T<��@'),('�-wYxG)�l!\"���','2022-06-11 00:00:00',14000,'�ʖU�Iw�>�h��\r�','Q��;*�K.��e��R�','Реализация','�ʖU�Iw�>�h��\r�'),('�cU�u�C���x�#�:�','2022-06-10 00:00:00',-5900,'C�@wB5��m��j','�\"��J0���9Tb��','Закупка','C�@wB5��m��j'),('��_ɘDA������GH','2022-06-12 00:00:00',60000,'$�@!�J����C�*�','G��<��CA��p�>\n�','Реализация','$�@!�J����C�*�'),('�bA��p��\Z��','2022-06-11 00:00:00',57000,'����7Oܫ�W�� �','j�8\n�F���z�  r�','Реализация','����7Oܫ�W�� �'),('� G-�C؂�\'�׊�\0','2022-05-08 13:29:11',9999999999.999,'�r= �[M����l4ܾ�','���~\\F�1��#��','Реализация','�r= �[M����l4ܾ�'),('�\\��\"�KO��/~�J�-','2022-06-11 00:00:00',58000,'S��nC������ ','L3���K|��C���','Реализация','S��nC������ '),('��O���B������4�','2022-06-10 00:00:00',-3050,'���M��@�dw�����','�h�А\ZC���0��P%','Закупка','���M��@�dw�����');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Регистр себестоимость продукции';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register_costprice`
--

LOCK TABLES `register_costprice` WRITE;
/*!40000 ALTER TABLE `register_costprice` DISABLE KEYS */;
INSERT INTO `register_costprice` VALUES ('y*��xO��:�z����',1,'2022-06-11 00:00:00','L3���K|��C���','�=��}G��#�8_�',7642.8,7642.8,357.1,8000,'Реализация'),('� ���L��L=Bf�',1,'2022-06-11 00:00:00','L3���K|��C���',':OEN�L���9���R',49045.4,49045.4,954.5,50000,'Реализация'),('(�r�q2LV���L�=�<',1,'2022-06-11 00:00:00','j�8\n�F���z�  r�','�,�QC�G�ö���',3880,3880,120,4000,'Реализация'),('+���H%Kȓx����',1,'2022-06-12 00:00:00','G��<��CA��p�>\n�','G%���Gi���ҽ0�',4918.75,4918.75,81.25,5000,'Реализация'),('3�s�ʰL��ݱˠ��',1,'2022-06-11 00:00:00','Q��;*�K.��e��R�','�, \\{BN�|��L',9918.75,9918.75,81.25,10000,'Реализация'),('?��S��J����a	�L',1,'2022-06-12 00:00:00','G��<��CA��p�>\n�','\'��~��L���ķp��+',4950,4950,50,5000,'Реализация'),('U���A��,p���e',2,'2022-06-11 00:00:00','Q��;*�K.��e��R�','\r�{�TiGU�\n���w�',3760,1880,240,4000,'Реализация'),('�5�?JR�\r�<���',1,'2022-06-11 00:00:00','j�8\n�F���z�  r�','U��4�I}�A�4�6�',2950,2950,50,3000,'Реализация'),('�!�/H��h@�;Z�',1,'2022-06-12 00:00:00','G��<��CA��p�>\n�','�cqy%�N޻�Y�F&�',49045.46,49045.46,954.54,50000,'Реализация'),('�e	I�IS�P�9®:m',1,'2022-06-11 00:00:00','j�8\n�F���z�  r�','c�;���B��^�1;L�t',49918.75,49918.75,81.25,50000,'Реализация');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Табличная часть "расход материалов"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_part_material_consuption`
--

LOCK TABLES `table_part_material_consuption` WRITE;
/*!40000 ALTER TABLE `table_part_material_consuption` DISABLE KEYS */;
INSERT INTO `table_part_material_consuption` VALUES ('\Z$\"Md�LИ!w\\���\"',1,1,'.{gɣI[�����[�t','\r�f\rLΩ7xc\r>XC','�	���I\"�4N�f�u','�	���I\"�4N�f�u'),('�d��KȽ�h�s�e\r',1,1,'XT崰I@����iF','��8آ/@��F���0�','|[H��I��iL�(�','|[H��I��iL�(�'),('!�%Y��H螳~#`9',1,1,'AqX�G0��\n�F\"5','k�>��RJ;��M��mY','u��/�HK������G�-','u��/�HK������G�-'),('767T]�C1��\n�l�׵',1,2,'�YW��I�Hq�/Ŷq','\r�f\rLΩ7xc\r>XC','�	���I\"�4N�f�u','�	���I\"�4N�f�u'),('@{T�l�L��w	g��*X',2,2,'yjV�YB����`ͦ9','k�>��RJ;��M��mY','u��/�HK������G�-','u��/�HK������G�-'),('VQ��\'}C�O�B�m�Y',1,3,'��k�Gr��@��6','k�>��RJ;��M��mY','u��/�HK������G�-','u��/�HK������G�-'),('i��[�O��r����',1,2,'.{gɣI[�����[�t','��8آ/@��F���0�','|[H��I��iL�(�','|[H��I��iL�(�'),('���`�Ex��D<D�\0�',2,1,'\Z9³�KT��H(U:��','f�K��\\H��)j>��','�(Y���NC�8��*�Vr','�(Y���NC�8��*�Vr'),('���\';�G^�4�]�~�',1,3,'<6��=GE�����','�v���A��gVX��','|[H��I��iL�(�','|[H��I��iL�(�'),('�Yw�>L��=�pa',3,1,'�YW��I�Hq�/Ŷq','\r�f\rLΩ7xc\r>XC','��]Ӎ�H����Aw�F�','��]Ӎ�H����Aw�F�'),('�߃�SGҵ�޷\rs�x',3,2,'�\"\'ӁDӴ�/kG��','\r�f\rLΩ7xc\r>XC','��]Ӎ�H����Aw�F�','��]Ӎ�H����Aw�F�'),('�9^�tRE��iI\0D�%',4,4,'�d�lM��s���C','k�>��RJ;��M��mY','u��/�HK������G�-','u��/�HK������G�-'),('�=���Kp����a�',2,2,'5	��C\0O����e)|N�','f�K��\\H��)j>��','�(Y���NC�8��*�Vr','�(Y���NC�8��*�Vr');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Запись регистра ТоварыНаСкладах';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register_products_in_stock`
--

LOCK TABLES `register_products_in_stock` WRITE;
/*!40000 ALTER TABLE `register_products_in_stock` DISABLE KEYS */;
INSERT INTO `register_products_in_stock` VALUES ('	2T��]B�钃�',-1,'���~\\F�1��#��',0,'Реализация','2022-05-08 13:29:11','�\"%�nYJ����p���'),('�Р^�Bi�-����',4,'�\"��J0���9Tb��',2000,'Закупка','2022-06-10 00:00:00','�x{�G�C��E� ���'),('�m׋[F>�[�\'>�',-1,'G��<��CA��p�>\n�',-954.54,'Реализация','2022-06-12 00:00:00','�cqy%�N޻�Y�F&�'),('���bN���|��',-1,'|[H��I��iL�(�',-800,'Производство','2022-06-10 00:00:00','XT崰I@����iF'),('\"-��͹I���Ă�7�',-1,'j�8\n�F���z�  r�',-81.25,'Реализация','2022-06-11 00:00:00','c�;���B��^�1;L�t'),('#���.L���=�Ĩ',7,'f�/F�\'@o��Z�y-�k',4900,'Закупка','2022-06-10 00:00:00','yjV�YB����`ͦ9'),('(d*�PN\0����ʫ�',5,'|[H��I��iL�(�',600,'Производство','2022-06-10 00:00:00','\r�{�TiGU�\n���w�'),('-�L��@Kl�7pmF���',6,'f�/F�\'@o��Z�y-�k',5400,'Закупка','2022-06-10 00:00:00','s=&�_N��ڲ9���'),('1�u�WhK���.^��',5,'�(Y���NC�8��*�Vr',250,'Производство','2022-06-10 00:00:00','\'��~��L���ķp��+'),(';]�w��O�{��n�',12,'�	���I\"�4N�f�u',975,'Производство','2022-06-11 00:00:00','�, \\{BN�|��L'),('?f���>B��%���^�',5,'|[H��I��iL�(�',600,'Производство','2022-06-10 00:00:00','�,�QC�G�ö���'),('C��I]�Nn�\'�1�E|',-2,'Q��;*�K.��e��R�',-240,'Реализация','2022-06-11 00:00:00','\r�{�TiGU�\n���w�'),('NJ\ZͶCU�#o�8�rO',8,'�N �p�O3��53<T�\r',12000,'Закупка','2022-06-10 00:00:00','�\"\'ӁDӴ�/kG��'),('T�¿�A.�>h|9��-',-1,'G��<��CA��p�>\n�',-50,'Реализация','2022-06-12 00:00:00','\'��~��L���ķp��+'),('T�Qe�Cx���z׼�',-1,'|[H��I��iL�(�',-400,'Производство','2022-06-10 00:00:00','<6��=GE�����'),('\\�!W�H2�i�;�',5,'���\0H~�R�a+)l4',4000,'Закупка','2022-06-10 00:00:00','XT崰I@����iF'),('n���h�Iy��{}\'>l',-3,'��]Ӎ�H����Aw�F�',-6000,'Производство','2022-06-10 00:00:00','�YW��I�Hq�/Ŷq'),('qq?�TFx��6�㯞',-1,'L3���K|��C���',-357.1,'Реализация','2022-06-11 00:00:00','�=��}G��#�8_�'),('q��O�H0��{�2��\'',10,'�	���I\"�4N�f�u',812.5,'Производство','2022-06-11 00:00:00','G%���Gi���ҽ0�'),('rEv��KC��}4��}',-1,'u��/�HK������G�-',-800,'Производство','2022-06-10 00:00:00','AqX�G0��\n�F\"5'),('t/�i�EW�4[N�=�',-1,'L3���K|��C���',-954.5,'Реализация','2022-06-11 00:00:00',':OEN�L���9���R'),('~�SFUEN�����#\'',5,'��]Ӎ�H����Aw�F�',4772.7,'Производство','2022-06-10 00:00:00','�cqy%�N޻�Y�F&�'),('��Þ>Nm�*�N�ݞ',-3,'��]Ӎ�H����Aw�F�',-4500,'Производство','2022-06-10 00:00:00','�\"\'ӁDӴ�/kG��'),('���6��E���-]T��',-2,'u��/�HK������G�-',-1400,'Производство','2022-06-10 00:00:00','yjV�YB����`ͦ9'),('��fT�NZ�H���=�?',-1,'G��<��CA��p�>\n�',-81.25,'Реализация','2022-06-12 00:00:00','G%���Gi���ҽ0�'),('�W��ǙCj��\'�|�YV',5,'�\"��J0���9Tb��',1500,'Закупка','2022-06-10 00:00:00','��k�Gr��@��6'),('�ʇE�Km��F���',-1,'|[H��I��iL�(�',-600,'Производство','2022-06-10 00:00:00','.{gɣI[�����[�t'),('�m��v�O��5j�*U�',3,'�\"��J0���9Tb��',2400,'Закупка','2022-06-10 00:00:00','AqX�G0��\n�F\"5'),('��8BPJ���K���}�',4,'���\0H~�R�a+)l4',2400,'Закупка','2022-06-10 00:00:00','.{gɣI[�����[�t'),('�[����F�\n�y���',-1,'Q��;*�K.��e��R�',-81.25,'Реализация','2022-06-11 00:00:00','�, \\{BN�|��L'),('����<�M���\\�m�S',10,'�	���I\"�4N�f�u',812.5,'Производство','2022-06-11 00:00:00','c�;���B��^�1;L�t'),('�A��p�Il��+`W��',12,'f�/F�\'@o��Z�y-�k',4800,'Закупка','2022-06-10 00:00:00','<6��=GE�����'),('��9��E��眉�z�',9,'�N �p�O3��53<T�\r',18000,'Закупка','2022-06-10 00:00:00','�YW��I�Hq�/Ŷq'),('�IٕH�D��\rΰ��',-1,'�	���I\"�4N�f�u',-2000,'Производство','2022-06-11 00:00:00','�YW��I�Hq�/Ŷq'),('�h�ӻI;�Ѓ�*���',-2,'�(Y���NC�8��*�Vr',0,'Производство','2022-06-10 00:00:00','\Z9³�KT��H(U:��'),('ÇRT�J��m�I�l',5,'|[H��I��iL�(�',600,'Производство','2022-06-10 00:00:00','Ԯ�D�^A�R]�\'�@�'),('�b���$O1��5�t',5,'�h�А\ZC���0��P%',1250,'Закупка','2022-06-10 00:00:00','5	��C\0O����e)|N�'),('����]�Hġ~\r��r�',5,'�h�А\ZC���0��P%',1000,'Закупка','2022-06-10 00:00:00','< ��E��z��u'),('�55�aH乍K��C�v',6,'��]Ӎ�H����Aw�F�',5727.2,'Производство','2022-06-10 00:00:00',':OEN�L���9���R'),('ز\\\'�IS��ND�h',4,'u��/�HK������G�-',1428.5,'Производство','2022-06-10 00:00:00','0Hf���Aς\0\n��>ʹ'),('���E7�@h��J�m�',-4,'u��/�HK������G�-',0,'Производство','2022-06-10 00:00:00','�d�lM��s���C'),('�ˁ�F�Fg��R���(',-1,'j�8\n�F���z�  r�',-50,'Реализация','2022-06-11 00:00:00','U��4�I}�A�4�6�'),('�J[�=yLR�����	�',-2,'�(Y���NC�8��*�Vr',-500,'Производство','2022-06-10 00:00:00','5	��C\0O����e)|N�'),('�J}-\"�@�����@�L',-1,'�	���I\"�4N�f�u',-600,'Производство','2022-06-11 00:00:00','.{gɣI[�����[�t'),('��?&�R@^���z��yI',5,'�(Y���NC�8��*�Vr',250,'Производство','2022-06-10 00:00:00','U��4�I}�A�4�6�'),('�ݦ��B\0��U��',3,'u��/�HK������G�-',1071.4,'Производство','2022-06-10 00:00:00','�=��}G��#�8_�'),('�Q�D�a�*��W�',4,'�h�А\ZC���0��P%',800,'Закупка','2022-06-10 00:00:00','Â��Mʧ�u`8�'),('�CmUwDܿ2}�����',-1,'j�8\n�F���z�  r�',-120,'Реализация','2022-06-11 00:00:00','�,�QC�G�ö���'),('�9Z�v�G>��j�\'$�o',-1,'u��/�HK������G�-',-300,'Производство','2022-06-10 00:00:00','��k�Gr��@��6');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Табличная часть "Произведено товаров"';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_part_produced_of_products`
--

LOCK TABLES `table_part_produced_of_products` WRITE;
/*!40000 ALTER TABLE `table_part_produced_of_products` DISABLE KEYS */;
INSERT INTO `table_part_produced_of_products` VALUES (')uqX�rG=�[{r�.y�',5,1,'\'��~��L���ķp��+','�(Y���NC�8��*�Vr','�(Y���NC�8��*�Vr'),(':��$�K��`��y�',5,1,'�,�QC�G�ö���','|[H��I��iL�(�','|[H��I��iL�(�'),('C��rlJK��[K�T��',4,1,'0Hf���Aς\0\n��>ʹ','u��/�HK������G�-','u��/�HK������G�-'),('Q#�Nz�Li�	�\'\0�',6,1,':OEN�L���9���R','��]Ӎ�H����Aw�F�','��]Ӎ�H����Aw�F�'),('^�]ڿ\rLw�۹���',3,2,'�=��}G��#�8_�','u��/�HK������G�-','u��/�HK������G�-'),('h�5��>Bӎk�j�@!',12,1,'�, \\{BN�|��L','�	���I\"�4N�f�u','�	���I\"�4N�f�u'),('r��:��Hߖ\r��B]��',5,2,'�cqy%�N޻�Y�F&�','��]Ӎ�H����Aw�F�','��]Ӎ�H����Aw�F�'),('����68A���tl NU',5,2,'\r�{�TiGU�\n���w�','|[H��I��iL�(�','|[H��I��iL�(�'),('֍j�r�O����X>�b�',10,2,'c�;���B��^�1;L�t','�	���I\"�4N�f�u','�	���I\"�4N�f�u'),('��MKHo��ʢ����',10,3,'G%���Gi���ҽ0�','�	���I\"�4N�f�u','�	���I\"�4N�f�u'),('���DB�N��!�e�&��',5,3,'Ԯ�D�^A�R]�\'�@�','|[H��I��iL�(�','|[H��I��iL�(�'),('�T��lAl�!Co\0�0',5,2,'U��4�I}�A�4�6�','�(Y���NC�8��*�Vr','�(Y���NC�8��*�Vr');
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
