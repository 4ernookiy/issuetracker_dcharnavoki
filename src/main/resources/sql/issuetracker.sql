-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Дек 04 2012 г., 18:05
-- Версия сервера: 5.5.25
-- Версия PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `issuetracker`
--

-- --------------------------------------------------------

--
-- Структура таблицы `builds`
--

CREATE TABLE IF NOT EXISTS `builds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `description` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Дамп данных таблицы `builds`
--

INSERT INTO `builds` (`id`, `projectId`, `description`) VALUES
(1, 1, '1.0.0'),
(11, 8, '1.0.0'),
(12, 9, '1.0.0');

-- --------------------------------------------------------

--
-- Структура таблицы `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `issueId` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Дамп данных таблицы `comments`
--

INSERT INTO `comments` (`id`, `issueId`, `user`, `date`, `text`) VALUES
(4, 6, 2, '2012-12-04 00:00:00', 'да и я с ней продолбался долго..... нужно исправлять'),
(5, 6, 3, '2012-12-04 00:00:00', 'В тз написано, что не требуется посылка по резервному каналу , разбирайтесь с тем , что состовлял тз');

-- --------------------------------------------------------

--
-- Структура таблицы `issues`
--

CREATE TABLE IF NOT EXISTS `issues` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `createdBy` int(11) NOT NULL,
  `modifyDate` datetime NOT NULL,
  `modifyBy` int(11) NOT NULL,
  `summary` text NOT NULL,
  `description` text NOT NULL,
  `statusId` int(11) NOT NULL,
  `resolutionId` int(11) NOT NULL,
  `priorityId` int(11) NOT NULL,
  `typeId` int(11) NOT NULL,
  `projectId` int(11) NOT NULL,
  `buildId` int(11) NOT NULL,
  `assignedId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Дамп данных таблицы `issues`
--

INSERT INTO `issues` (`id`, `createDate`, `createdBy`, `modifyDate`, `modifyBy`, `summary`, `description`, `statusId`, `resolutionId`, `priorityId`, `typeId`, `projectId`, `buildId`, `assignedId`) VALUES
(4, '2012-12-04 00:00:00', 1, '2012-12-04 00:00:00', 1, 'нет событий о приближении поезда', 'при вступлении поезда на второй блок участок , программа не выдает сообщение в систему ГИД', 1, 0, 3, 2, 8, 11, 0),
(5, '2012-12-04 00:00:00', 1, '2012-12-04 00:00:00', 1, 'архитектура раобты с бд', 'налицо большое количество копипаста', 1, 0, 4, 1, 9, 12, 0),
(6, '2012-12-04 00:00:00', 1, '2012-12-04 00:00:00', 1, 'Нет возможности отослать данные по резервному каналу', 'не происходит переключение на резервный канал, в результате посылка по резервному каналу IPX не возможна', 5, 3, 2, 2, 1, 1, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `priorities`
--

CREATE TABLE IF NOT EXISTS `priorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `priorities`
--

INSERT INTO `priorities` (`id`, `description`) VALUES
(1, 'Critical'),
(2, 'Major'),
(3, 'Important'),
(4, 'Minor');

-- --------------------------------------------------------

--
-- Структура таблицы `projects`
--

CREATE TABLE IF NOT EXISTS `projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` text NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Дамп данных таблицы `projects`
--

INSERT INTO `projects` (`id`, `name`, `description`, `userId`) VALUES
(1, 'IS', 'получение таблиц импульсов', 2),
(8, 'SM', 'Обработка событий', 2),
(9, 'Visualisation', 'Отрисовка графических объектов', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `resolutions`
--

CREATE TABLE IF NOT EXISTS `resolutions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `resolutions`
--

INSERT INTO `resolutions` (`id`, `description`) VALUES
(1, 'Fixed'),
(2, 'Invalid'),
(3, 'Wontfix'),
(4, 'Worksforme');

-- --------------------------------------------------------

--
-- Структура таблицы `statuses`
--

CREATE TABLE IF NOT EXISTS `statuses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(32) NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Дамп данных таблицы `statuses`
--

INSERT INTO `statuses` (`id`, `description`) VALUES
(1, 'New'),
(2, 'Assigned'),
(3, 'In Progress'),
(4, 'Resolved'),
(5, 'Closed'),
(6, 'Reopened');

-- --------------------------------------------------------

--
-- Структура таблицы `types`
--

CREATE TABLE IF NOT EXISTS `types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `types`
--

INSERT INTO `types` (`id`, `description`) VALUES
(1, 'Cosmetic'),
(2, 'Bug'),
(3, 'Feature'),
(4, 'Performance');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(48) NOT NULL,
  `lastName` varchar(48) NOT NULL,
  `email` varchar(64) NOT NULL,
  `role` int(11) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `firstName`, `lastName`, `email`, `role`, `password`) VALUES
(1, 'Ivan', 'Sidoroff', 'i@tut.by', 0, 'c4ca4238a0b923820dcc509a6f75849b'),
(2, 'Petr', 'Petrov', 'p@tut.by', 1, 'c4ca4238a0b923820dcc509a6f75849b'),
(3, 'Daniel', 'Danek', 'd@tut.by', 1, 'c4ca4238a0b923820dcc509a6f75849b');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
