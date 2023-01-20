# ************************************************************
# Sequel Pro SQL dump
# Versión 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.1.37-MariaDB)
# Base de datos: chat
# Tiempo de Generación: 2019-03-06 20:39:12 +0000
#
# DESARROLLADO POR XCHEKO51X
#
# ************************************************************


CREATE DATABASE `chat`;

USE `chat`;

# Volcado de tabla mensajes
# ------------------------------------------------------------

CREATE TABLE `mensajes` (
  `idMensaje` int(11) NOT NULL AUTO_INCREMENT,
  `mensaje` varchar(100) NOT NULL,
  `usuarioOrigen` varchar(30) NOT NULL,
  `usuarioDestino` varchar(30) NOT NULL,
  PRIMARY KEY (`idMensaje`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


# Volcado de tabla usuarios
# ------------------------------------------------------------

CREATE TABLE `usuarios` (
  `usuario` varchar(30) NOT NULL,
  `contrasena` varchar(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `usuarios` (`usuario`, `contrasena`, `nombre`) VALUES ('TODOS','TODOS','TODOS');
INSERT INTO `usuarios` (`usuario`, `contrasena`, `nombre`) VALUES ('usuario1','1234','Usuario Uno');
INSERT INTO `usuarios` (`usuario`, `contrasena`, `nombre`) VALUES ('usuario2','4321','Usuario Dos');
