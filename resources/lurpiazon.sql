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
  `id_pedido` int DEFAULT NULL,
  `id_producto` int DEFAULT NULL,
  `nombre_producto` varchar(45) NOT NULL,
  `cantidad` int NOT NULL,
  KEY `id_pedido_idx` (`id_pedido`),
  KEY `id_producto_idx` (`id_producto`),
  CONSTRAINT `id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`),
  CONSTRAINT `id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos_almacen` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
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
  `fecha` date NOT NULL,
  `total_precio` float NOT NULL,
  PRIMARY KEY (`id_pedido`),
  UNIQUE KEY `id_pedido_UNIQUE` (`id_pedido`),
  KEY `id_usuario_idx` (`id_usuario`),
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_almacen`
--

LOCK TABLES `productos_almacen` WRITE;
/*!40000 ALTER TABLE `productos_almacen` DISABLE KEYS */;
INSERT INTO `productos_almacen` VALUES (1,'camisa',20.95,1,'ropa',40),(2,'vestido',18.75,1,'ropa',20),(3,'abrigo',45.99,1,'ropa',30),(4,'falda',23.5,1,'ropa',25),(5,'sudadera',27.75,1,'ropa',35),(6,'camiseta',22.5,1,'ropa',33),(7,'jersey',29.99,1,'ropa',12),(8,'gorro',8.5,1,'ropa',35),(9,'guantes',9.85,1,'ropa',22),(10,'pantalon',32.9,1,'ropa',38),(11,'alfombra',45.75,2,'hogar',35),(12,'mesa',75.5,2,'hogar',26),(13,'estanteria',55.99,2,'hogar',38),(14,'vaso',3.5,2,'hogar',50),(15,'taza',2.75,2,'hogar',42),(16,'plato',1.9,2,'hogar',33),(17,'espejo',26.5,2,'hogar',16),(18,'lampara',35.95,2,'hogar',29),(19,'sofa',255.9,2,'hogar',30),(20,'silla',34.5,2,'hogar',20),(21,'roll',4.5,3,'alimentacion',15),(22,'heura',3.75,3,'alimentacion',28),(23,'pizza',2.55,3,'alimentacion',19),(24,'aceite',5.5,3,'alimentacion',23),(25,'vinagre',0.8,3,'alimentacion',15),(26,'empanada',8.95,3,'alimentacion',22),(27,'cafe',1.59,3,'alimentacion',42),(28,'arroz',1.29,3,'alimentacion',12),(29,'proteina',16.99,3,'alimentacion',18),(30,'avena',0.99,3,'alimentacion',32),(31,'pelota',3.2,4,'mascota',23),(32,'collar',12.5,4,'mascota',17),(33,'correa',14.95,4,'mascota',35),(34,'transportin',25.95,4,'mascota',40),(35,'champu',11.99,4,'mascota',16),(36,'pipeta',23.95,4,'mascota',30),(37,'cama',37.95,4,'mascota',22),(38,'frisbee',11.95,4,'mascota',37),(39,'comedero',9.99,4,'mascota',19),(40,'bebedero',17.5,4,'mascota',18);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,1,'admin','admin','admin','1234','admin','123','admin','12345'),(2,1,'adm','administrador','admin','12','admin','567','admino','56789'),(3,0,'pedro','pedro','pedrin','789','pepe','456','pedron','147');
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

-- Dump completed on 2023-05-05 17:55:41
