CREATE DATABASE  IF NOT EXISTS `lurpiazon` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `lurpiazon`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: lurpiazon
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `detalles_pedidos`
--

DROP TABLE IF EXISTS `detalles_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles_pedidos` (
  `id_detalles_pedidos` int NOT NULL AUTO_INCREMENT,
  `id_pedido` int NOT NULL,
  `id_producto` int DEFAULT NULL,
  `cantidad` int NOT NULL,
  PRIMARY KEY (`id_detalles_pedidos`),
  KEY `id_pedido_idx` (`id_pedido`),
  KEY `id_producto_idx` (`id_producto`),
  CONSTRAINT `id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`),
  CONSTRAINT `id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos_almacen` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles_pedidos`
--

LOCK TABLES `detalles_pedidos` WRITE;
/*!40000 ALTER TABLE `detalles_pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalles_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int DEFAULT NULL,
  `fecha` timestamp NOT NULL,
  `total_precio` float NOT NULL,
  PRIMARY KEY (`id_pedido`),
  UNIQUE KEY `id_pedido_UNIQUE` (`id_pedido`),
  KEY `id_usuario_idx` (`id_usuario`),
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos_almacen`
--

DROP TABLE IF EXISTS `productos_almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos_almacen` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(45) NOT NULL,
  `precio` float NOT NULL,
  `id_categoria` int NOT NULL,
  `nombre_categoria` varchar(45) NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id_producto`),
  UNIQUE KEY `id_producto_UNIQUE` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_almacen`
--

LOCK TABLES `productos_almacen` WRITE;
/*!40000 ALTER TABLE `productos_almacen` DISABLE KEYS */;
INSERT INTO `productos_almacen` VALUES (1,'camisa',20.95,1,'ropa',40),(2,'vestido',18.75,1,'ropa',20),(3,'abrigo',45.99,1,'ropa',30),(4,'falda',23.5,1,'ropa',25),(5,'sudadera',27.75,1,'ropa',35),(6,'camiseta',22.5,1,'ropa',33),(7,'jersey',29.99,1,'ropa',12),(8,'gorro',8.5,1,'ropa',35),(9,'guantes',9.85,1,'ropa',22),(10,'pantalon',32.9,1,'ropa',38),(11,'alfombra',45.75,2,'hogar',35),(12,'mesa',75.5,2,'hogar',26),(13,'estanteria',55.99,2,'hogar',38),(14,'vaso',3.5,2,'hogar',50),(15,'taza',2.75,2,'hogar',42),(16,'plato',1.9,2,'hogar',33),(17,'espejo',26.5,2,'hogar',16),(18,'lampara',35.95,2,'hogar',29),(19,'sofa',255.9,2,'hogar',30),(20,'silla',34.5,2,'hogar',20),(21,'roll',4.5,3,'alimentacion',15),(22,'heura',3.75,3,'alimentacion',28),(23,'pizza',2.55,3,'alimentacion',19),(24,'aceite',5.5,3,'alimentacion',23),(25,'vinagre',0.8,3,'alimentacion',15),(26,'empanada',8.95,3,'alimentacion',22),(27,'cafe',1.59,3,'alimentacion',42),(28,'arroz',1.29,3,'alimentacion',12),(29,'proteina',16.99,3,'alimentacion',18),(30,'avena',0.99,3,'alimentacion',32),(31,'pelota',3.2,4,'mascota',23),(32,'collar',12.5,4,'mascota',17),(33,'correa',14.95,4,'mascota',35),(34,'transportin',25.95,4,'mascota',40),(35,'champu',11.99,4,'mascota',16),(36,'pipeta',23.95,4,'mascota',30),(37,'cama',37.95,4,'mascota',22),(38,'frisbee',11.95,4,'mascota',37),(39,'comedero',9.99,4,'mascota',19),(40,'bebedero',17.5,4,'mascota',18),(41,'pantalón cargo',29.99,1,'ropa',30),(42,'camiseta',12.99,1,'ropa',50),(43,'chaqueta de cuero',79.99,1,'ropa',15),(44,'vestido de noche',69.99,1,'ropa',10),(45,'jersey de lana',39.99,1,'ropa',12),(46,'camisa a rayas',24.99,1,'ropa',25),(47,'falda plisada',34.99,1,'ropa',18),(48,'chaqueta vaquera',59.99,1,'ropa',14),(49,'vestido estampado',49.99,1,'ropa',20),(50,'shorts vaqueros',19.99,1,'ropa',35),(51,'pantalones de cuero',89.99,1,'ropa',8),(52,'blusa de seda',59.99,1,'ropa',22),(53,'abrigos de invierno',129.99,1,'ropa',5),(54,'pantalones cortos',14.99,1,'ropa',40),(55,'trajes formales',199.99,1,'ropa',3),(56,'chaqueta acolchada',79.99,1,'ropa',9),(57,'vaqueros ajustados',49.99,1,'ropa',28),(58,'camisetas de algodón',9.99,1,'ropa',55),(59,'vestido veraniego',39.99,1,'ropa',16),(60,'sudadera con capucha',29.99,1,'ropa',30),(61,'juego de sábanas',39.99,2,'hogar',50),(62,'almohadas de plumas',24.99,2,'hogar',30),(63,'toallas de baño',19.99,2,'hogar',40),(64,'juego de ollas',69.99,2,'hogar',15),(65,'platos de cerámica',14.99,2,'hogar',60),(66,'cortinas opacas',29.99,2,'hogar',20),(67,'set de cubiertos',34.99,2,'hogar',25),(68,'funda nórdica',49.99,2,'hogar',10),(69,'organizador de escritorio',12.99,2,'hogar',35),(70,'espejo de pared',39.99,2,'hogar',18),(71,'juego de tazas de café',19.99,2,'hogar',45),(72,'cubiertos de acero inoxidable',29.99,2,'hogar',30),(73,'vajilla de porcelana',59.99,2,'hogar',12),(74,'almohadas de memoria',34.99,2,'hogar',22),(75,'cortina de ducha',24.99,2,'hogar',28),(76,'juego de vasos de vidrio',17.99,2,'hogar',50),(77,'colchón ortopédico',199.99,2,'hogar',5),(78,'estante flotante',29.99,2,'hogar',15),(79,'juego de cuchillos',49.99,2,'hogar',10),(80,'toallas de mano',9.99,2,'hogar',60),(81,'collar para perro',9.99,4,'mascota',50),(82,'comida para gatos',12.99,4,'mascota',30),(83,'juguete para perro',7.99,4,'mascota',40),(84,'rascador para gatos',29.99,4,'mascota',15),(85,'arena para gatos',14.99,4,'mascota',60),(86,'correa retráctil',19.99,4,'mascota',20),(87,'juguetes para pájaros',6.99,4,'mascota',25),(88,'camita para perros',34.99,4,'mascota',10),(89,'comedero automático',39.99,4,'mascota',35),(90,'juguete interactivo para gatos',16.99,4,'mascota',18),(91,'alimento para perros',21.99,4,'mascota',45),(92,'rascador para perros',26.99,4,'mascota',30),(93,'transportín para gatos',49.99,4,'mascota',12),(94,'collar para gatos',8.99,4,'mascota',22),(95,'cama para gatos',29.99,4,'mascota',28),(96,'acuario pequeño',39.99,4,'mascota',5),(97,'juguete para pájaros',7.99,4,'mascota',15),(98,'comida para peces',4.99,4,'mascota',50),(99,'caseta para perros',59.99,4,'mascota',10),(100,'arnés para perros',12.99,4,'mascota',40),(101,'arroz integral',2.99,3,'alimentacion',50),(102,'aceite de oliva virgen',5.99,3,'alimentacion',30),(103,'harina de trigo',1.99,3,'alimentacion',40),(104,'conserva de atún',3.49,3,'alimentacion',15),(105,'galletas integrales',2.49,3,'alimentacion',60),(106,'leche de almendras',3.99,3,'alimentacion',20),(107,'café molido',4.99,3,'alimentacion',25),(108,'azúcar moreno',1.49,3,'alimentacion',10),(109,'sopa de tomate enlatada',2.29,3,'alimentacion',35),(110,'yogur natural',1.99,3,'alimentacion',18),(111,'miel de abeja',4.49,3,'alimentacion',45),(112,'avena en hojuelas',2.99,3,'alimentacion',30),(113,'mermelada de fresa',2.99,3,'alimentacion',28),(114,'pasta de trigo integral',1.79,3,'alimentacion',50),(115,'aceitunas rellenas',3.49,3,'alimentacion',22),(116,'cereal de granola',3.99,3,'alimentacion',28),(117,'salsa de soja',2.99,3,'alimentacion',50),(118,'lentejas enlatadas',1.99,3,'alimentacion',15),(119,'chocolate negro',3.99,3,'alimentacion',40),(120,'aceite de coco',6.99,3,'alimentacion',12);
/*!40000 ALTER TABLE `productos_almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `is_admin` tinyint NOT NULL,
  `nombre_usuario` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `num_telf` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `cp` varchar(45) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `id_usuario_UNIQUE` (`id_usuario`),
  UNIQUE KEY `nombre_usuario_UNIQUE` (`nombre_usuario`),
  UNIQUE KEY `num_telf_UNIQUE` (`num_telf`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,1,'admin','admin','admin','1234','admin','123','admin','12345'),(2,1,'adm','administrador','admin','12','admin','567','admino','56789'),(3,0,'pedro','Pedro','Pedrin','789','C/ camelias nº24 7I','689127593','pedrin@gmail.com','14723'),(4,0,'alepipa','Alejandra','Cereijo','florida','C/ sidney nº26 7D','789729055','pipa@gmail.com','33211'),(5,0,'lider','Sara','Ramilo','capibara','C/ honolulu nº 21 5A','555321654','liderosa@gmail.com','14897'),(6,0,'bule','David','Seoane','concevigo','Rua comarca nº105 3D','666895623','rataman@gmail.com','36258'),(7,0,'nando','Fernando','Lago','filipinos','Av. argentina nº22 5A','789456123','fiuren@gmail.com','78644'),(8,0,'quelinchis','Raquel','Alvarez','apple','C/ madrid nº75 5A','654159863','insostenible@gmail.com','45678'),(9,0,'vegana','Susana','Paredes','unagi','Av. camelias nº10 6D','660897465','vegangirl@gmail.com','36214'),(10,0,'baba','Barbara','Diaz','paulmescal','C/ valdoviño nº89 7I','741852963','campo@gmail.com','98654'),(11,0,'ronaldinho','Ian','Gonzalez','cocomelon','C/ mouriscados nº105 1A','741852369','ian@gmail.com','36258'),(12,0,'joel','Pedro','Pascal','7894','C/ dorne nº76 9B','698745123','pedro@gmail.com','45213'),(13,0,'ellie','Bella','Ramsey','luciernaga','Av. isla del oso','674158239','ellie@gmail.com','12356'),(14,0,'nano','Fernando','Alonso','33','C/ asturias nº 37 3A','694521786','nano@gmail.com','33333'),(15,0,'brian','Logan','Roy','bear','Av. canada nº12 4A','651374896','roy@gmail.com','20078'),(16,0,'kendall','Jeremy','Strong','sucesor','C/ lago nº55 7I','644897765','kendall@gamil.com','36974'),(17,0,'lobato','Antonio','Lobato','magic','C/ oviedo nº14 3B','732589664','lobi@gmail.com','55789'),(18,0,'bort','Bart','Simpson','patinete','C/ falsa nº123 1A','555697891','bart@gmail.com','75144'),(19,0,'talea','Ana','Perez','gafas','Av. travesia nº90 10B','733664985','ana@gmail.com','85297'),(20,0,'chelsea','Mason','Mount','traspaso','C/ londres nº91 4D','745589613','mason@gmail.com','36211'),(21,0,'bucky','Sebastian','Stan','winter','Av. plaza nº56 7D','677853269','wintersoldier@gmail.com','98431'),(22,0,'heloise','Adele','Haenel','llamas','C/ francia nº56 1A','674155874','adele@gmail.com','45612'),(23,0,'marianne','Noemie','Merlant','retrato','C/ coruña nº 26 7D','745888669','marianne@gmail.com','36200');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-01 17:29:34
