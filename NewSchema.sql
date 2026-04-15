DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `Employee_ID` varchar(10) NOT NULL,
  `First_Name` tinytext NOT NULL,
  `Last_Name` tinytext NOT NULL,
  `Email` tinytext NOT NULL,
  `Phone#` varchar(12) NOT NULL,
  `Salary` decimal(8,2) unsigned NOT NULL DEFAULT '0.00',
  `Password` tinytext NOT NULL,
  `Role` tinytext NOT NULL,
  PRIMARY KEY (`Employee_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='"Employee_ID" is of type VARCHAR instead of TINYTEXT(specified in design document), because a BLOB/TEXT value cannot be a primary key. BLOB/TEXT values cause error1170. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `Item_Name` varchar(50) NOT NULL,
  `Quantity` tinyint unsigned NOT NULL,
  PRIMARY KEY (`Item_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='"Item_Name" is of type VARCHAR instead of TINYTEXT(specified in design document), because a BLOB/TEXT value cannot be a primary key. BLOB/TEXT values cause error1170. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemorder`
--

DROP TABLE IF EXISTS `itemorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itemorder` (
  `ItemOrder_NO` int NOT NULL AUTO_INCREMENT,
  `Order_NO` int NOT NULL,
  `Item_Name` varchar(45) NOT NULL,
  `Quantity` tinyint unsigned NOT NULL,
  `Price` decimal(5,2) unsigned DEFAULT NULL,
  `Notes` mediumtext NOT NULL,
  PRIMARY KEY (`ItemOrder_NO`),
  KEY `Item_Name_idx` (`Item_Name`),
  KEY `Order_NO_idx` (`Order_NO`),
  CONSTRAINT `Item_Name` FOREIGN KEY (`Item_Name`) REFERENCES `menuitem` (`Menu_Item_Name`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `Order_NO` FOREIGN KEY (`Order_NO`) REFERENCES `order` (`Order_NO`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='"Item_Name" needs to be of type VARCHAR instead of TINYTEXT(Specified in design document), because it acts as a foreign key to the MenuItem Table. Where the referenced primary key "Menu_Item_Name" is of type VARCHAR. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemorder`
--

LOCK TABLES `itemorder` WRITE;
/*!40000 ALTER TABLE `itemorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemorder` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `itemorder_BEFORE_INSERT` BEFORE INSERT ON `itemorder` FOR EACH ROW BEGIN
Set New.Price = New.Quantity * (Select Price_Per_Unit From menuitem Where Menu_Item_Name = New.Item_Name);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `menuitem`
--

DROP TABLE IF EXISTS `menuitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menuitem` (
  `Menu_Item_Name` varchar(45) NOT NULL,
  `Price_Per_Unit` decimal(4,2) unsigned NOT NULL,
  PRIMARY KEY (`Menu_Item_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='"Menu_Item_Name" is of type VARCHAR instead of TINYTEXT(specified in design document), because a BLOB/TEXT value cannot be a primary key. BLOB/TEXT values cause error1170. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menuitem`
--

LOCK TABLES `menuitem` WRITE;
/*!40000 ALTER TABLE `menuitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `menuitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `Order_NO` int NOT NULL AUTO_INCREMENT,
  `Waiter_ID` varchar(10) NOT NULL,
  `Time_Placed` timestamp NOT NULL,
  `Table` varchar(2) NOT NULL,
  `Seat#` tinyint NOT NULL,
  `Refunded` tinyint(1) NOT NULL DEFAULT '0',
  `Ready_At_Time` timestamp NULL DEFAULT NULL,
  `Total_Price` decimal(5,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`Order_NO`),
  KEY `Table_idx` (`Table`),
  KEY `Waiter_ID_idx` (`Waiter_ID`),
  CONSTRAINT `Table` FOREIGN KEY (`Table`) REFERENCES `table` (`Table_ID`) ON UPDATE CASCADE,
  CONSTRAINT `Waiter_ID` FOREIGN KEY (`Waiter_ID`) REFERENCES `employee` (`Employee_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='"Employee_ID" needs to be of type VARCHAR instead of TINYTEXT(Specified in design document), because it acts as a foreign key to the Employee Table. Where the referenced primary key "Employee_ID" in the Employee Table  is of type VARCHAR.\\n\\n"Refunded" is of type TINYINT(1) instead of BOOLEAN(specified in design document), because BOOLEAN is the same thing. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift`
--

DROP TABLE IF EXISTS `shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shift` (
  `Shift_ID` int NOT NULL AUTO_INCREMENT,
  `Employee_ID` varchar(10) NOT NULL,
  `Date` date NOT NULL,
  `Start_Time` time NOT NULL,
  `End_Time` time NOT NULL,
  PRIMARY KEY (`Shift_ID`),
  KEY `Employee_ID_idx` (`Employee_ID`),
  CONSTRAINT `Employee_ID` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='"Employee_ID" needs to be of type VARCHAR instead of TINYTEXT(Specified in design document), because it acts as a foreign key to the Employee Table. Where the referenced primary key "Employee_ID" in the Employee Table  is of type VARCHAR. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift`
--

LOCK TABLES `shift` WRITE;
/*!40000 ALTER TABLE `shift` DISABLE KEYS */;
/*!40000 ALTER TABLE `shift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table`
--

DROP TABLE IF EXISTS `table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table` (
  `Table_ID` varchar(2) NOT NULL,
  `Assigned_Employee_ID` varchar(10) DEFAULT NULL,
  `Status` tinytext NOT NULL,
  PRIMARY KEY (`Table_ID`),
  KEY `Assigned_Employee_ID_idx` (`Assigned_Employee_ID`),
  CONSTRAINT `Assigned_Employee_ID` FOREIGN KEY (`Assigned_Employee_ID`) REFERENCES `employee` (`Employee_ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='"Assigned_Employee_ID" needs to be of type VARCHAR instead of TINYTEXT(Specified in design document), because it acts as a foreign key to the Employee Table. Where the referenced primary key "Employee_ID" is of type VARCHAR. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table`
--

LOCK TABLES `table` WRITE;
/*!40000 ALTER TABLE `table` DISABLE KEYS */;
/*!40000 ALTER TABLE `table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'a_la_carte_automation_of_j'
--

--
-- Dumping routines for database 'a_la_carte_automation_of_j'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
