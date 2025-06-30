-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-06-2025 a las 04:56:22
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bce_java`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actuaciones`
--

CREATE TABLE `actuaciones` (
  `id_actuacion` int(11) NOT NULL,
  `oficina_id` int(11) NOT NULL,
  `tipo_actuacion_id` int(11) NOT NULL,
  `numero_actuacion` varchar(20) NOT NULL,
  `fecha_emision` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actuacion_balance`
--

CREATE TABLE `actuacion_balance` (
  `id_actuacion_balance` int(11) NOT NULL,
  `actuacion_id` int(11) NOT NULL,
  `balance_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actuacion_rendicion`
--

CREATE TABLE `actuacion_rendicion` (
  `id_actuacion_rendicion` int(11) NOT NULL,
  `actuacion_id` int(11) NOT NULL,
  `rendicion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `balances`
--

CREATE TABLE `balances` (
  `id_balance` int(11) NOT NULL,
  `jurisdiccion_id` int(11) NOT NULL,
  `numero_expediente` varchar(15) NOT NULL,
  `trimestre` int(11) NOT NULL,
  `año` int(11) NOT NULL,
  `fecha_provisoria` date NOT NULL,
  `fecha_definitiva` varchar(10) DEFAULT NULL,
  `cantidad_rendiciones` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jurisdicciones`
--

CREATE TABLE `jurisdicciones` (
  `id_jurisdiccion` int(11) NOT NULL,
  `nombre` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `jurisdicciones`
--

INSERT INTO `jurisdicciones` (`id_jurisdiccion`, `nombre`) VALUES
(1, 'Ministerio de Seguridad'),
(2, 'Ministerio de Educación');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `oficinas`
--

CREATE TABLE `oficinas` (
  `id_oficina` int(11) NOT NULL,
  `nombre` varchar(300) NOT NULL,
  `nivel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `oficinas`
--

INSERT INTO `oficinas` (`id_oficina`, `nombre`, `nivel`) VALUES
(1, 'Delegación Fiscal', 5),
(2, 'Fiscalía General', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pase_balance`
--

CREATE TABLE `pase_balance` (
  `id_pase_balance` int(11) NOT NULL,
  `balance_id` int(11) NOT NULL,
  `oficina_origen_id` int(11) NOT NULL,
  `oficina_destino_id` int(11) DEFAULT NULL,
  `fecha_pase_elevado` date DEFAULT NULL,
  `fecha_pase_recibido` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rendiciones`
--

CREATE TABLE `rendiciones` (
  `id_rendicion` int(11) NOT NULL,
  `balance_id` int(11) NOT NULL,
  `concepto` varchar(300) NOT NULL,
  `numero_rendicion` int(11) NOT NULL,
  `expediente_jurisdiccional` varchar(14) NOT NULL,
  `reparticion` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_actuacion`
--

CREATE TABLE `tipo_actuacion` (
  `id_tipo_actuacion` int(11) NOT NULL,
  `nombre` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actuaciones`
--
ALTER TABLE `actuaciones`
  ADD PRIMARY KEY (`id_actuacion`),
  ADD KEY `actuaciones.fk_oficina` (`oficina_id`),
  ADD KEY `actuaciones.fk_tipo` (`tipo_actuacion_id`);

--
-- Indices de la tabla `actuacion_balance`
--
ALTER TABLE `actuacion_balance`
  ADD PRIMARY KEY (`id_actuacion_balance`),
  ADD KEY `actuacion_balance.fk_balance` (`balance_id`),
  ADD KEY `actuacion_balance.fk_actuacion` (`actuacion_id`);

--
-- Indices de la tabla `actuacion_rendicion`
--
ALTER TABLE `actuacion_rendicion`
  ADD PRIMARY KEY (`id_actuacion_rendicion`),
  ADD KEY `actuacion_rendicion.fk_rendicion` (`rendicion_id`),
  ADD KEY `actuacion_rendicion.fk_actuacion` (`actuacion_id`);

--
-- Indices de la tabla `balances`
--
ALTER TABLE `balances`
  ADD PRIMARY KEY (`id_balance`),
  ADD UNIQUE KEY `jurisdiccion_id` (`jurisdiccion_id`,`trimestre`,`año`),
  ADD UNIQUE KEY `numero_expediente` (`numero_expediente`,`jurisdiccion_id`);

--
-- Indices de la tabla `jurisdicciones`
--
ALTER TABLE `jurisdicciones`
  ADD PRIMARY KEY (`id_jurisdiccion`);

--
-- Indices de la tabla `oficinas`
--
ALTER TABLE `oficinas`
  ADD PRIMARY KEY (`id_oficina`);

--
-- Indices de la tabla `pase_balance`
--
ALTER TABLE `pase_balance`
  ADD PRIMARY KEY (`id_pase_balance`),
  ADD KEY `pase_balance.fk_balance` (`balance_id`),
  ADD KEY `pase_balance.fk_oficina_origen` (`oficina_origen_id`),
  ADD KEY `pase_balance.fk_oficina_destino` (`oficina_destino_id`);

--
-- Indices de la tabla `rendiciones`
--
ALTER TABLE `rendiciones`
  ADD PRIMARY KEY (`id_rendicion`),
  ADD KEY `rendiciones.fk_balance` (`balance_id`);

--
-- Indices de la tabla `tipo_actuacion`
--
ALTER TABLE `tipo_actuacion`
  ADD PRIMARY KEY (`id_tipo_actuacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actuaciones`
--
ALTER TABLE `actuaciones`
  MODIFY `id_actuacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `actuacion_balance`
--
ALTER TABLE `actuacion_balance`
  MODIFY `id_actuacion_balance` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `actuacion_rendicion`
--
ALTER TABLE `actuacion_rendicion`
  MODIFY `id_actuacion_rendicion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `balances`
--
ALTER TABLE `balances`
  MODIFY `id_balance` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `jurisdicciones`
--
ALTER TABLE `jurisdicciones`
  MODIFY `id_jurisdiccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `oficinas`
--
ALTER TABLE `oficinas`
  MODIFY `id_oficina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pase_balance`
--
ALTER TABLE `pase_balance`
  MODIFY `id_pase_balance` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `rendiciones`
--
ALTER TABLE `rendiciones`
  MODIFY `id_rendicion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_actuacion`
--
ALTER TABLE `tipo_actuacion`
  MODIFY `id_tipo_actuacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actuaciones`
--
ALTER TABLE `actuaciones`
  ADD CONSTRAINT `actuaciones.fk_oficina` FOREIGN KEY (`oficina_id`) REFERENCES `oficinas` (`id_oficina`),
  ADD CONSTRAINT `actuaciones.fk_tipo` FOREIGN KEY (`tipo_actuacion_id`) REFERENCES `tipo_actuacion` (`id_tipo_actuacion`);

--
-- Filtros para la tabla `actuacion_balance`
--
ALTER TABLE `actuacion_balance`
  ADD CONSTRAINT `actuacion_balance.fk_actuacion` FOREIGN KEY (`actuacion_id`) REFERENCES `actuaciones` (`id_actuacion`),
  ADD CONSTRAINT `actuacion_balance.fk_balance` FOREIGN KEY (`balance_id`) REFERENCES `balances` (`id_balance`);

--
-- Filtros para la tabla `actuacion_rendicion`
--
ALTER TABLE `actuacion_rendicion`
  ADD CONSTRAINT `actuacion_rendicion.fk_actuacion` FOREIGN KEY (`actuacion_id`) REFERENCES `actuaciones` (`id_actuacion`),
  ADD CONSTRAINT `actuacion_rendicion.fk_rendicion` FOREIGN KEY (`rendicion_id`) REFERENCES `rendiciones` (`id_rendicion`);

--
-- Filtros para la tabla `balances`
--
ALTER TABLE `balances`
  ADD CONSTRAINT `balance.fk_jurisdiccion` FOREIGN KEY (`jurisdiccion_id`) REFERENCES `jurisdicciones` (`id_jurisdiccion`);

--
-- Filtros para la tabla `pase_balance`
--
ALTER TABLE `pase_balance`
  ADD CONSTRAINT `pase_balance.fk_balance` FOREIGN KEY (`balance_id`) REFERENCES `balances` (`id_balance`),
  ADD CONSTRAINT `pase_balance.fk_oficina_destino` FOREIGN KEY (`oficina_destino_id`) REFERENCES `oficinas` (`id_oficina`),
  ADD CONSTRAINT `pase_balance.fk_oficina_origen` FOREIGN KEY (`oficina_origen_id`) REFERENCES `oficinas` (`id_oficina`);

--
-- Filtros para la tabla `rendiciones`
--
ALTER TABLE `rendiciones`
  ADD CONSTRAINT `rendiciones.fk_balance` FOREIGN KEY (`balance_id`) REFERENCES `balances` (`id_balance`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
