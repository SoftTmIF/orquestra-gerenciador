
use orquestra;

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `senha` varchar(12) NOT NULL,
  `nome_usuario` varchar(60) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `id_UNIQUE` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
INSERT INTO usuario(nome_usuario, senha) VALUES('admin','admin');

CREATE TABLE IF NOT EXISTS `projeto` (
  `id_projeto` int NOT NULL AUTO_INCREMENT,
  `nome_projeto` varchar(45) NOT NULL,
  PRIMARY KEY (`id_projeto`),
  UNIQUE KEY `id_projeto_UNIQUE` (`id_projeto`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
INSERT INTO projeto(nome_projeto) VALUES('Projeto integrador');

CREATE TABLE IF NOT EXISTS `task` (
  `id_task` int NOT NULL AUTO_INCREMENT,
  `data_de_execucao` date NOT NULL,
  `total_de_horas` decimal(4,0) NOT NULL,
  `tipo_natureza` varchar(1) NOT NULL,
  `descricao` varchar(200) NOT NULL,
  `id_usuario` int NOT NULL,
  `projeto` int,
  PRIMARY KEY (`id_task`),
  FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) on update cascade on delete cascade,
  FOREIGN KEY (`projeto`) REFERENCES `projeto` (`id_projeto`) on update cascade on delete cascade
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `usuario_projeto` (
  `usuario_r` int NOT NULL,
  `projeto_r` int NOT NULL,
  PRIMARY KEY (`usuario_r`,`projeto_r`),
  KEY `projeto_r_idx` (`projeto_r`),
  KEY `usuario_r_idx` (`usuario_r`),
  CONSTRAINT `projeto_r` FOREIGN KEY (`projeto_r`) REFERENCES `projeto` (`id_projeto`),
  CONSTRAINT `usuario_r` FOREIGN KEY (`usuario_r`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB;

