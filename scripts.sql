-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: service_desk
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `atendimentos`
--

DROP TABLE IF EXISTS `atendimentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atendimentos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_atendimento` datetime(6) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `ordem_servico_id` bigint DEFAULT NULL,
  `recebimento_id` bigint DEFAULT NULL,
  `tecnico_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK70lc62qmpfe6c4pxtcdpecrkd` (`ordem_servico_id`),
  KEY `FK3nib9vvu7mpn4gcknal416fk` (`recebimento_id`),
  KEY `FKh83l3isikfpd1s7tf5ua2lm8d` (`tecnico_id`),
  CONSTRAINT `FK3nib9vvu7mpn4gcknal416fk` FOREIGN KEY (`recebimento_id`) REFERENCES `recebimentos` (`id`),
  CONSTRAINT `FK70lc62qmpfe6c4pxtcdpecrkd` FOREIGN KEY (`ordem_servico_id`) REFERENCES `ordens_servico` (`id`),
  CONSTRAINT `FKh83l3isikfpd1s7tf5ua2lm8d` FOREIGN KEY (`tecnico_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atendimentos`
--

LOCK TABLES `atendimentos` WRITE;
/*!40000 ALTER TABLE `atendimentos` DISABLE KEYS */;
INSERT INTO `atendimentos` VALUES (1,'2023-06-22 12:49:05.061927','Foi feita a troca do cabeçote','Feito limpeza geral no equipamento',1,8,3),(2,'2023-06-22 12:51:50.914265','Foi trocado o teclado','Feito limpeza interna',5,6,3),(3,'2023-07-02 10:51:45.128512','Foi trocada a placa-mãe','A placa custou R$ 200,00',3,10,3),(4,'2023-06-22 13:07:15.883805','Feito a troca do encaixe do conector','Feito limpeza interna',7,12,3);
/*!40000 ALTER TABLE `atendimentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancelamentos`
--

DROP TABLE IF EXISTS `cancelamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cancelamentos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_cancelamento` datetime(6) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `recebimento_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3nb8nxgkym6m4p5fm9vabevlr` (`recebimento_id`),
  KEY `FKj17d1qfcy2fuxnejwex33ov8u` (`usuario_id`),
  CONSTRAINT `FK3nb8nxgkym6m4p5fm9vabevlr` FOREIGN KEY (`recebimento_id`) REFERENCES `recebimentos` (`id`),
  CONSTRAINT `FKj17d1qfcy2fuxnejwex33ov8u` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancelamentos`
--

LOCK TABLES `cancelamentos` WRITE;
/*!40000 ALTER TABLE `cancelamentos` DISABLE KEYS */;
INSERT INTO `cancelamentos` VALUES (1,'2023-07-02 10:47:08.498978','Cancelado no recebimento','Cliente desistiu',2,3),(2,'2023-07-02 10:47:50.954769','Cancelado no atendimento','Cliente achou o serviço caro',1,3),(3,'2023-07-02 20:30:32.665580','Cliente desistiu','Cancelado no recebimento',5,2);
/*!40000 ALTER TABLE `cancelamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) NOT NULL,
  `data_nascimento` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `endereco_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7wflw78ibh162cmq12ii6ffly` (`cpf`),
  UNIQUE KEY `UK_1c96wv36rk2hwui7qhjks3mvg` (`email`),
  KEY `FK6insj35866kfg0wdbvp9ogti4` (`endereco_id`),
  CONSTRAINT `FK6insj35866kfg0wdbvp9ogti4` FOREIGN KEY (`endereco_id`) REFERENCES `enderecos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11014 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'62588813437','1997-03-31','bruno_moraes@yahoo.com','Bruno Matheus Moraes','M','(21) 2071-6221',1),(2,'42957158167','1996-03-15','jessica_aparecida_novaes@htomail.com','Jéssica Aparecida Novaes','F','(61) 98700-4296',2),(3,'21218635240','1989-11-25','viniciusmariobernardes@indaseg.com.br','Vinicius Mário Lucas Bernardes','M','(38) 3651-8223',3),(4,'03085042245','1995-09-21','tiagobernardocavalcanti@achievecidadenova.com.br','Tiago Bernardo Cavalcanti','M','(82) 3735-6724',4),(5,'73374923160','2001-12-13','cesar_gomes@br.festo.com','César Diogo Gomes','M','(63) 2985-3955',5),(6,'49798119576','1995-05-13','bento.vieira@fundasa.com.br','Bento Marcos Vieira','M','(82) 98925-2108',6),(7,'48493931527','1999-10-09','renata.ayla.bernardes@jglima.com.br','Renata Ayla Sônia Bernardes','F','(68) 98104-8154',7),(8,'20968948596','1998-03-29','elaine.peixoto@balloons.com.br','Elaine Vanessa Olivia Peixoto','F','(91) 3905-7797',8),(9,'30086822780','2000-10-03','isabelle.cruz@queirozgalvao.com','Isabelle Gabrielly Maria da Cruz','F','(71) 98716-3677',9),(10,'38408979558','2001-03-13','heitor.vieira@dyna.com.br','Heitor Anderson Vieira','M','(96) 2650-2196',10),(11,'25196490620','1992-10-10','noah.fernandes@stembalagens.com.br','Noah Danilo Fernandes','M','(92) 3574-9983',11),(12,'83679984561','1995-01-02','anderson.almada@unipsicotaubate.com.br','Anderson Lucas Almada','M','(86) 2893-8240',12),(14,'46121422254','1990-12-10','lucia.rezende@technocut.com.br','Lúcia Louise Fabiana Rezende','F','(82) 98727-5163',14),(15,'91959066307','1987-01-12','lara.alves@parkhotel.com.br','Lara Joana Alves','F','(22) 3664-8549',15),(16,'82274183252','1999-08-11','livia.moreira81@vnews.com.br','Lívia Eliane Márcia Moreira','F','(84) 99373-8710',16),(17,'52272254276','2000-03-19','claudio.caldeira@kframe.com.br','Cláudio Marcos Caldeira','M','(68) 98329-7579',17),(18,'70340026065','1997-07-23','lorena.lima@juliosimoes.com.br','Lorena Betina Lima','F','(48) 99486-7192',18),(19,'60779910745','1988-09-10','felipe.cruz@fulltransport.com.br','Felipe Leandro da Cruz','M','(47) 98769-7555',19),(20,'63201989746','1999-09-05','clara.pires@aquino.com.br','Clara Analu Jéssica Pires','F','(68) 98931-1778',20),(21,'92888882094','1993-01-11','noah.mendes@gasparalmeida.com','Noah Márcio Mendes','M','(41) 98342-3019',21),(22,'84134906504','1989-06-11','alicia.vieira@yoma.com.br','Alícia Agatha Marli Vieira','F','(85) 3586-6583',22),(23,'65541785111','1991-04-15','rodrigo.neves@vilasites.com.br','Rodrigo Bruno Heitor das Neves','M','(82) 98975-6762',23),(24,'75410118359','1985-10-11','nathan.galvao@bhcervejas.com.br','Nathan Bento Gael Galvão','M','(21) 3737-7677',24),(25,'69257125700','1999-03-10','bruno.souza@directnet.com.br','Bruno Ruan Souza','M','(92) 99726-4355',25),(26,'45754718608','2001-10-23','analu.bernardes@luxafit.com.br','Analu Agatha Nina Bernardes','F','(61) 3517-9829',26),(27,'59157829144','1986-01-21','heloise.santos@lavabit.com','Heloise Brenda dos Santos','F','(68) 99937-5044',27),(28,'77927204890','2000-11-11','sueli.araujo@ggm.com.br','Sueli Aparecida Betina Araújo','F','(33) 2872-0787',28),(29,'38175785985','1999-12-10','benicio.dias@fclar.net.br','Benício Hugo Marcelo Dias','M','(31) 98479-4002',29),(30,'67288327000','2001-10-03','ryan.goncalves@construtor.com.br','Ryan Renato Gonçalves','M','(31) 98479-4002',30),(31,'05866915960','1995-02-23','luiz.moraes77@teadit.com.br','Luiz Tomás Moraes','M','(51) 99608-2111',31),(32,'14182180739','2002-01-22','henry.assis84@danzarin.com.br','Henry Erick Assis','M','(96) 99720-5549',32),(11013,'22948794226','2002-10-22','fabiana.aparicio@sicredi.com.br','Fabiana Mirella Aparício','F','(16) 3564-6965',13);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devolucoes`
--

DROP TABLE IF EXISTS `devolucoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devolucoes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_devolucao` datetime(6) DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `valor` decimal(9,2) NOT NULL DEFAULT '0.00',
  `atendimento_id` bigint DEFAULT NULL,
  `cancelamento_id` bigint DEFAULT NULL,
  `recebimento_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgh6aok707u7gu58xhpg94t40f` (`atendimento_id`),
  KEY `FK1bpo1b73c3b7t3r1fi4jg7lsd` (`cancelamento_id`),
  KEY `FK3wt6jib45ivllyjumqrnlruij` (`recebimento_id`),
  KEY `FKppumg9roukfhkj54tb9o2ymfp` (`usuario_id`),
  CONSTRAINT `FK1bpo1b73c3b7t3r1fi4jg7lsd` FOREIGN KEY (`cancelamento_id`) REFERENCES `cancelamentos` (`id`),
  CONSTRAINT `FK3wt6jib45ivllyjumqrnlruij` FOREIGN KEY (`recebimento_id`) REFERENCES `recebimentos` (`id`),
  CONSTRAINT `FKgh6aok707u7gu58xhpg94t40f` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimentos` (`id`),
  CONSTRAINT `FKppumg9roukfhkj54tb9o2ymfp` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucoes`
--

LOCK TABLES `devolucoes` WRITE;
/*!40000 ALTER TABLE `devolucoes` DISABLE KEYS */;
INSERT INTO `devolucoes` VALUES (1,'2023-07-02 11:26:48.975413','Atendimento cancelado',0.00,NULL,2,1,3),(2,'2023-07-02 11:27:52.331475','Recebimento cancelado',0.00,NULL,1,2,2),(3,'2023-07-02 11:28:52.227622','Foi cobrado 30 reais pela limpeza',250.00,2,NULL,6,2),(4,'2023-07-02 11:36:04.204141','',100.00,1,NULL,7,3);
/*!40000 ALTER TABLE `devolucoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enderecos`
--

DROP TABLE IF EXISTS `enderecos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enderecos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bairro` varchar(100) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) NOT NULL,
  `estado` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enderecos`
--

LOCK TABLES `enderecos` WRITE;
/*!40000 ALTER TABLE `enderecos` DISABLE KEYS */;
INSERT INTO `enderecos` VALUES (1,'Comercial','68925055','Santana','Ap. 105','Avenida Amapá, 111','AP'),(2,'Paranoá','71572310','Brasília','Ap. 118','Quadra 23 Conjunto J, 140','DF'),(3,'Centro','39400001','Montes Claros','Casa B','Rua Doutor Santos, 1.890','MG'),(4,'Farol','57055190','Maceió','','Rua Clementino do Monte, 100','AL'),(5,'Plano Diretor Sul','77017212','Palmas','','ACSO 80 Alameda 2, 565','TO'),(6,'Rio Novo','57070546','Maceió','','Rua Amanda Virna, 536','AL'),(7,'Areal','69906089','Rio Branco','','Rua do Areal, 335','AC'),(8,'Jardim Atlântico','68627484','Paragominas','Casa A','Avenida Júlio Kubitschek, 675','PA'),(9,'Jardim Marco Zero','68903195','Macapá','Ap 101','Rua da Redenção, 1001','AP'),(10,'Jardim Atlântico','68627484','Paragominas','','Avenida Júlio Kubitschek, 999','PA'),(11,'Parque 10 de Novembro','69050672','Manaus','Casa B','Rua Pedro Dias Leme, 220','AM'),(12,'Centro','64200440','Parnaíba','','Praça Coronel Osório, 1009','PI'),(13,'Parque Industrial Avelino Alves Palma','14077270','Ribeirão Preto','Ap 502','Rua Bruno Malfará, 1001','SP'),(14,'Jacintinho','57040675','Maceió','','Travessa Nova, 35A','AL'),(15,'Jardim Excelsior','28915110','Cabo Frio','','Rua Capri, 115','RJ'),(16,'Redinha','59122039','Natal','','Rua Alto das Dunas, 200','RN'),(17,'Loteamento Jardim São Francisco','69902109','Rio Branco','','Alameda A, 555','AC'),(18,'Pantanal','88040270','Florianópolis','','Rua Rosa, 515','SC'),(19,'Vila Nova','89237267','Joinville','Ap 2','Rua Gerhard Dietrich Barkemeyer, 514','SC'),(20,'Conjunto Tucumã','69919748','Rio Branco','','Rua Malta, 10','AC'),(21,'Parque da Fonte','83050590','São José dos Pinhais','','Rua Osvaldo da Silva Bassu, 157','PR'),(22,'Novo Oriente','61919240','Maracanaú','','Rua Fernão Dias, 115','CE'),(23,'Gruta de Lourdes','57052720','Maceió','','Rua Arquiteto Asdrúbal Sarmento, 44','AL'),(24,'Guaratiba','23026440','Rio de Janeiro','','Rua Pedra do Indaiá, 115','RJ'),(25,'Alvorada','69043004','Manaus','','Rua Camafen, 1115','AM'),(26,'Guará I','71020671','Brasília','Ap 12','QE 11 Área Especial G, 112','DF'),(27,'Eldorado','69902494','Rio Branco','','Rua Independência, 200','AC'),(28,'Planalto','35054180','Governador Valadares','Ap 301','Avenida A, 117','MG'),(29,'Buritis','30575145','Belo Horizonte','Casa B','Rua José Barcellos, 990','MG'),(30,'Aeroporto','49037293','Aracaju','','Rua Maria Francisca Cavalcante, S/N','SE'),(31,'Roselândia','93351327','Novo Hamburgo','','Rua Cenira Voltz, 456','RS'),(32,'São Lázaro','68908560','Macapá','','Avenida Armando Tupan Alves de Abreu, 112','AP');
/*!40000 ALTER TABLE `enderecos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordens_servico`
--

DROP TABLE IF EXISTS `ordens_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordens_servico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_ordem_servico` datetime(6) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `numero_os` varchar(255) DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `valor` decimal(9,2) DEFAULT '0.00',
  `recebimento_id` bigint DEFAULT NULL,
  `tecnico_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8i2ns78gm6le4ht95l14ssfs4` (`recebimento_id`),
  KEY `FKropsj2ufri24001462gp6mmcf` (`tecnico_id`),
  CONSTRAINT `FK8i2ns78gm6le4ht95l14ssfs4` FOREIGN KEY (`recebimento_id`) REFERENCES `recebimentos` (`id`),
  CONSTRAINT `FKropsj2ufri24001462gp6mmcf` FOREIGN KEY (`tecnico_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordens_servico`
--

LOCK TABLES `ordens_servico` WRITE;
/*!40000 ALTER TABLE `ordens_servico` DISABLE KEYS */;
INSERT INTO `ordens_servico` VALUES (1,'2023-06-22 13:03:34.092503','Não imprime','202306221001','Tampa quebrada',150.00,1,3),(2,'2023-06-20 19:53:45.001620','Não liga','202306201003','Troca do cabo de energia',100.00,3,3),(3,'2023-06-20 19:55:13.649633','Não funciona','202306201010','Será verificado',120.00,10,2),(4,'2023-06-20 19:55:54.471507','Não está fazendo leitura','202306201002','Deve ser analfabeto',180.00,2,2),(5,'2023-06-20 19:56:34.750452','Algumas teclas estão falhando','202306201006','Será trocado o teclado',220.00,6,2),(6,'2023-06-22 13:04:23.527822','Não liga','202306221009','O cabo de alimentação está quebrado',130.00,9,3),(7,'2023-07-02 10:49:49.941414','Tela quebrada','202307021007','Sem cabo de alimentação',100.00,7,2);
/*!40000 ALTER TABLE `ordens_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `perfil` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (1,'ADMIN'),(2,'TECN'),(3,'ATEND');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recebimentos`
--

DROP TABLE IF EXISTS `recebimentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recebimentos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_recebimento` datetime(6) DEFAULT NULL,
  `equipamento` varchar(60) NOT NULL,
  `marca` varchar(60) DEFAULT NULL,
  `modelo` varchar(60) DEFAULT NULL,
  `num_serie` varchar(20) NOT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_apov6nbms9vo4pg106lfatlq3` (`num_serie`),
  KEY `FKfusnhcm82eenvfcs95057rac8` (`cliente_id`),
  KEY `FK5ulofngqwbelr7r5j9dfbracn` (`usuario_id`),
  CONSTRAINT `FK5ulofngqwbelr7r5j9dfbracn` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKfusnhcm82eenvfcs95057rac8` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recebimentos`
--

LOCK TABLES `recebimentos` WRITE;
/*!40000 ALTER TABLE `recebimentos` DISABLE KEYS */;
INSERT INTO `recebimentos` VALUES (1,'2023-06-11 10:00:54.000000','Impressora XPTO','XPTO','XPTO Serie X','xxx0-00115-21.1','Cabo de alimentação danificado','DEVOLVE_EQUIPAMENTO',24,2),(2,'2023-06-11 10:05:54.000000','Scanner XPTO','XPTO','XPTO Serie X1','xxx0-00135-21.1','Sem cabo de alimentação','DEVOLVE_EQUIPAMENTO',28,2),(3,'2023-06-11 10:10:54.000000','Filmadora XPTO','XPTO','XPTO Serie X0','xxx0-00115-22.1','Lente danificada','ABRE_ORDEM_SERVICO',14,2),(4,'2023-06-11 10:15:54.000000','Celular XPTO','XPTO','XPTO Serie X','xxx0-00315-21.1','Sem carregador','RECEBE_EQUIPAMENTO',1,2),(5,'2023-06-11 10:20:54.000000','Notebook XPTO','XPTO','XPTO Serie X','xxx0-00415-21.1','Sem observação','CANCELA_ATENDIMENTO',19,2),(6,'2023-06-11 10:25:54.000000','Micro computador XPTO','XPTO','XPTO Serie X','xxx0-00515-21.1','Teste','DEVOLVE_EQUIPAMENTO',25,2),(7,'2023-06-11 10:32:54.000000','Câmera XPTO','XPTO','XPTO Serie X','xxx0-00615-21.1','Dono imbecil','DEVOLVE_EQUIPAMENTO',4,2),(8,'2023-06-11 10:37:54.000000','Impressora XPTO','XPTO','XPTO Serie X','xxx0-00115-41.1','Cara de lata','EFETUA_ATENDIMENTO',11,2),(9,'2023-06-11 10:42:54.000000','Monitor XPTO','XPTO','XPTO Serie X','xxx0-00115-51.1','Cachorro fez cocô na tela','ABRE_ORDEM_SERVICO',17,2),(10,'2023-06-11 10:47:54.000000','Video XPTO','XPTO','XPTO Serie X','xxx0-00115-61.1','Nada a acrescentar','EFETUA_ATENDIMENTO',15,2),(11,'2023-06-11 10:52:54.000000','Audio XPTO','XPTO','XPTO Serie X','xxx0-00115-71.1','Viche Maria','RECEBE_EQUIPAMENTO',32,2),(12,'2023-06-11 10:56:54.000000','Conector XPTO','XPTO','XPTO Serie X','xxx0-00115-81.1','Arre égua','RECEBE_EQUIPAMENTO',9,2);
/*!40000 ALTER TABLE `recebimentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_perfil`
--

DROP TABLE IF EXISTS `usuario_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_perfil` (
  `usuario_id` bigint NOT NULL,
  `perfil_id` bigint NOT NULL,
  KEY `FK22cgfn0obntlvqyfn33pyk24d` (`perfil_id`),
  KEY `FK46v67aocrgergt30mf76suhgm` (`usuario_id`),
  CONSTRAINT `FK22cgfn0obntlvqyfn33pyk24d` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`),
  CONSTRAINT `FK46v67aocrgergt30mf76suhgm` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_perfil`
--

LOCK TABLES `usuario_perfil` WRITE;
/*!40000 ALTER TABLE `usuario_perfil` DISABLE KEYS */;
INSERT INTO `usuario_perfil` VALUES (1,1),(2,3),(3,2);
/*!40000 ALTER TABLE `usuario_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,_binary '','admin@mail.com','Maria Rita de Souza','$2a$10$fCryhyWH8UZeU5IY8t.q1OTWQs83nNnobeHhQRbH8TxgVAVMUtDW.'),(2,_binary '','atend@mail.com','Ribamar de Sousa e Silva','$2a$10$A/RNukPU0TRnobAQ2mKIBObtx.ZQZRvL.V2WIV3Om2Cd0MQ0H2Lay'),(3,_binary '','tecn@mail.com','José Maria da Silva','$2a$10$yseK7Z9ZbZW2Gc2sV1ek7.Msh4TrQUwXU6Bbx2YrfCJLxT4XvyePe');
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

-- Dump completed on 2023-07-02 20:31:23
