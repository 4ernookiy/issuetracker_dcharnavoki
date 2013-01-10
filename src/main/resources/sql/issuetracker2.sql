-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Янв 08 2013 г., 13:08
-- Версия сервера: 5.5.25
-- Версия PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `issuetracker2`
--
CREATE DATABASE IF NOT EXISTS issuetracker2;
USE issuetracker2;
-- --------------------------------------------------------

--
-- Структура таблицы `build`
--

CREATE TABLE IF NOT EXISTS `build` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Дамп данных таблицы `build`
--

INSERT INTO `build` (`id`, `projectId`, `description`) VALUES
(1, 1, '1.0.0'),
(2, 1, '1.1.0'),
(3, 2, '1.0.0'),
(8, 3, '1.0.0'),
(9, 1, '1.2.0');

-- --------------------------------------------------------

--
-- Структура таблицы `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `issueId` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `text` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Дамп данных таблицы `comment`
--

INSERT INTO `comment` (`id`, `issueId`, `user`, `date`, `text`) VALUES
(5, 2, 1, '2013-01-07 15:27:59', 'Рекомендую просмотреть generic dao подход:\r\nhttp://www.ibm.com/developerworks/ru/library/j-genericdao/'),
(6, 2, 2, '2013-01-07 15:31:39', 'это не мой проект. я передал назначение на другого исполнителя');

-- --------------------------------------------------------

--
-- Структура таблицы `issue`
--

CREATE TABLE IF NOT EXISTS `issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `createdBy` int(11) NOT NULL,
  `modifiedBy` int(11) NOT NULL,
  `summary` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  `resolution` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `priority` int(11) NOT NULL,
  `project` int(11) NOT NULL,
  `build` int(11) NOT NULL,
  `assigned` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `issue`
--

INSERT INTO `issue` (`id`, `createDate`, `modifyDate`, `createdBy`, `modifiedBy`, `summary`, `description`, `status`, `resolution`, `type`, `priority`, `project`, `build`, `assigned`) VALUES
(1, '2013-01-07 15:50:57', '2013-01-07 15:50:57', 2, 2, 'нет событий о приближении поезда', 'при вступлении поезда на второй блок участок , программа не выдает сообщение в систему ГИД', 1, NULL, 1, 4, 2, 3, 1),
(2, '2013-01-07 15:31:39', '2013-01-07 15:31:39', 2, 2, 'архитектура раобты с бд', 'налицо большое количество копипаста', 1, NULL, 1, 3, 3, 8, 3),
(3, '2013-01-07 15:26:13', '2013-01-07 15:26:13', 1, 1, 'Нет возможности отослать данные по резервному каналу', 'не происходит переключение на резервный канал, в результате посылка по резервному каналу IPX ', 1, NULL, 1, 1, 1, 9, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `priority`
--

CREATE TABLE IF NOT EXISTS `priority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Дамп данных таблицы `priority`
--

INSERT INTO `priority` (`id`, `description`) VALUES
(1, 'Critical'),
(2, 'Major'),
(3, 'Important'),
(4, 'Minor');

-- --------------------------------------------------------

--
-- Структура таблицы `project`
--

CREATE TABLE IF NOT EXISTS `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `manager` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Дамп данных таблицы `project`
--

INSERT INTO `project` (`id`, `name`, `description`, `manager`) VALUES
(1, 'IS', 'получение таблиц импульсов', 1),
(2, 'SM', 'Обработка событий', 2),
(3, 'Visualisation', 'Visualisation', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `resolution`
--

CREATE TABLE IF NOT EXISTS `resolution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `resolution`
--

INSERT INTO `resolution` (`id`, `description`) VALUES
(1, 'Fixed'),
(2, 'Invalid'),
(3, 'Wontfix'),
(4, 'Worksforme');

-- --------------------------------------------------------

--
-- Структура таблицы `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Дамп данных таблицы `status`
--

INSERT INTO `status` (`id`, `description`) VALUES
(1, 'New'),
(2, 'Assigned'),
(3, 'In Progress'),
(4, 'Resolved'),
(5, 'Closed'),
(6, 'Reopened');

-- --------------------------------------------------------

--
-- Структура таблицы `type`
--

CREATE TABLE IF NOT EXISTS `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `type`
--

INSERT INTO `type` (`id`, `description`) VALUES
(1, 'Cosmetic'),
(2, 'bug'),
(3, 'Feature'),
(4, 'Performance');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(128) NOT NULL,
  `lastName` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `role` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `firstName`, `lastName`, `email`, `role`, `password`) VALUES
(1, 'Ivan', 'Sidoroff', 'i@tut.by', 'ADMIN', 'c4ca4238a0b923820dcc509a6f75849b'),
(2, 'Petr', 'Petrov', 'p@tut.by', 'USER', 'c4ca4238a0b923820dcc509a6f75849b'),
(3, 'Daniel', 'Danik', 'd@tut.by', 'USER', 'c4ca4238a0b923820dcc509a6f75849b');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
