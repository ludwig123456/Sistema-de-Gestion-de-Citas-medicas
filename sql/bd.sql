SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE `cita` (
  `idCita` int(11) NOT NULL,
  `idMedico` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `dni` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `idSede` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `especialidades` (
  `especialidad` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `historiaclinica` (
  `numHistoria` int(10) NOT NULL,
  `dni` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `idCita` int(11) NOT NULL,
  `peso` float NOT NULL,
  `talla` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `idMedico` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `presion` int(11) NOT NULL,
  `pulso` int(11) NOT NULL,
  `diagnosticoBreve` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `diagnosticoCompleto` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `receta` varchar(512) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `horario` (
  `dia` int(11) NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `idmedico` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `idsede` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `medico` (
  `idmedico` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `apellido` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `especialidad` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `fecha_nac` date NOT NULL,
  `genero` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `celular` int(11) NOT NULL,
  `telefono` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `paciente` (
  `dni` int(11) NOT NULL,
  `nombres` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `fecha_nac` date NOT NULL,
  `telefono` int(11) NOT NULL,
  `celular` int(11) NOT NULL,
  `genero` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `tipoSangre` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `sedes` (
  `idSedes` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(55) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `usuario` (
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `pass` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `salt` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `tipo` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


ALTER TABLE `cita`
  ADD PRIMARY KEY (`idCita`);

ALTER TABLE `especialidades`
  ADD PRIMARY KEY (`especialidad`);

ALTER TABLE `historiaclinica`
  ADD PRIMARY KEY (`numHistoria`);

ALTER TABLE `medico`
  ADD PRIMARY KEY (`idmedico`);

ALTER TABLE `sedes`
  ADD PRIMARY KEY (`idSedes`);

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`username`);


ALTER TABLE `cita`
  MODIFY `idCita` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `historiaclinica`
  MODIFY `numHistoria` int(10) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
