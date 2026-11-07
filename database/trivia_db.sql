-- phpMyAdmin SQL Dump
-- version 4.7.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 07-11-2026 a las 18:37:43
-- Versión del servidor: 5.6.34
-- Versión de PHP: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `trivia_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `animes`
--

CREATE TABLE `animes` (
  `id` int(11) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `enunciado` varchar(500) NOT NULL,
  `opcion_a` varchar(255) NOT NULL,
  `opcion_b` varchar(255) NOT NULL,
  `opcion_c` varchar(255) DEFAULT NULL,
  `opcion_d` varchar(255) DEFAULT NULL,
  `respuesta_correcta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `animes`
--

INSERT INTO `animes` (`id`, `tipo`, `enunciado`, `opcion_a`, `opcion_b`, `opcion_c`, `opcion_d`, `respuesta_correcta`) VALUES
(1, 'OPCION_MULTIPLE', 'En Dragon Ball Z, ¿cómo se llama la fusión de Goku y Vegeta mediante los zarcillos Pothala?', 'Gogeta', 'Vegito', 'Gotenks', 'Xeno', 1),
(2, 'VERDADERO_FALSO', 'En \'Death Note\', ¿el cuaderno mata a cualquier persona cuyo nombre sea escrito en él?', 'Verdadero', 'Falso', NULL, NULL, 0),
(3, 'OPCION_MULTIPLE', '¿Cuál es el sueño principal de Monkey D. Luffy en \'One Piece\'?', 'Ser el mejor espadachín', 'Encontrar el One Piece y ser el Rey de los Piratas', 'Destruir a la Marina', 'Conquistar el mundo', 1),
(4, 'VERDADERO_FALSO', 'En \'Naruto\', el zorro de nueve colas está sellado dentro del cuerpo de Naruto Uzumaki.', 'Verdadero', 'Falso', NULL, NULL, 0),
(5, 'OPCION_MULTIPLE', '¿Cómo se llama la academia o instituto al que asisten los héroes en \'Boku no Hero Academia\'?', 'Karasuno', 'U.A. High School', 'Shibusen', 'Academia Ninja', 1),
(6, 'VERDADERO_FALSO', 'En \'Attack on Titan\' (Shingeki no Kyojin), Eren Jaeger se unió a la Legión de Reconocimiento.', 'Verdadero', 'Falso', NULL, NULL, 0),
(7, 'OPCION_MULTIPLE', '¿Qué fruta del diablo comió Luffy en su infancia?', 'Mera Mera no Mi', 'Gomu Gomu no Mi', 'Hito Hito no Mi', 'Ope Ope no Mi', 1),
(8, 'VERDADERO_FALSO', 'En \'Demon Slayer\' (Kimetsu no Yaiba), Tanjiro busca una cura para que su hermana Nezuko vuelva a ser humana.', 'Verdadero', 'Falso', NULL, NULL, 0),
(9, 'OPCION_MULTIPLE', '¿Cómo se llama el shinigami principal que acompaña a Light Yagami en \'Death Note\'?', 'Rem', 'Ryuk', 'Gelus', 'Sidoh', 1),
(10, 'VERDADERO_FALSO', 'En \'Fullmetal Alchemist\', los hermanos Elric intentaron revivir a su madre mediante la alquimia.', 'Verdadero', 'Falso', NULL, NULL, 0),
(11, 'OPCION_MULTIPLE', '¿Cuál es el nombre del protagonista de \'Tokyo Ghoul\' que se convierte en mitad ghoul?', 'Ken Kaneki', 'Light Yagami', 'Eren Jaeger', 'Tanjiro Kamado', 0),
(12, 'VERDADERO_FALSO', 'En \'Pokémon\', el primer pokémon que recibe Ash Ketchum de parte del Profesor Oak es Charmander.', 'Verdadero', 'Falso', NULL, NULL, 1),
(13, 'OPCION_MULTIPLE', '¿Qué deporte practican los protagonistas del anime \'Haikyuu!!\'?', 'Fútbol', 'Básquetbol', 'Voleibol', 'Béisbol', 2),
(14, 'VERDADERO_FALSO', 'En \'Dragon Ball\', las Esferas del Dragón en la Tierra son un total de siete.', 'Verdadero', 'Falso', NULL, NULL, 0),
(15, 'OPCION_MULTIPLE', '¿Cómo se llama el protagonista calvo de \'One Punch Man\' capaz de derrotar enemigos de un solo golpe?', 'Genos', 'Saitama', 'Bang', 'Garou', 1),
(16, 'VERDADERO_FALSO', 'En \'Sailor Moon\', la verdadera identidad de la protagonista es Serena Tsukino.', 'Verdadero', 'Falso', NULL, NULL, 0),
(17, 'OPCION_MULTIPLE', '¿Qué espada legendaria utiliza Zoro en \'One Piece\' al inicio de su aventura?', 'Wado Ichimonji', 'Enma', 'Yubashiri', 'Shusui', 0),
(18, 'VERDADERO_FALSO', 'En \'Hunter x Hunter\', Gon Freecss busca convertirse en un Cazador profesional para encontrar a su padre.', 'Verdadero', 'Falso', NULL, NULL, 0),
(19, 'OPCION_MULTIPLE', '¿Cuál es el nombre de la aldea natal de Naruto Uzumaki?', 'Aldea Oculta de la Arena', 'Aldea Oculta de la Hoja (Konoha)', 'Aldea Oculta de la Neblina', 'Aldea Oculta de las Rocas', 1),
(20, 'VERDADERO_FALSO', 'En \'Bleach\', Ichigo Kurosaki obtiene los poderes de un Shinigami gracias a Rukia Kuchiki.', 'Verdadero', 'Falso', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dibujos_animados`
--

CREATE TABLE `dibujos_animados` (
  `id` int(11) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `enunciado` varchar(500) NOT NULL,
  `opcion_a` varchar(255) NOT NULL,
  `opcion_b` varchar(255) NOT NULL,
  `opcion_c` varchar(255) DEFAULT NULL,
  `opcion_d` varchar(255) DEFAULT NULL,
  `respuesta_correcta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `dibujos_animados`
--

INSERT INTO `dibujos_animados` (`id`, `tipo`, `enunciado`, `opcion_a`, `opcion_b`, `opcion_c`, `opcion_d`, `respuesta_correcta`) VALUES
(1, 'OPCION_MULTIPLE', '¿Cómo se llama la ciudad submarina ficticia donde vive Bob Esponja?', 'Fondo de Bikini', 'Atlantis', 'Ciudad Coral', 'Puerto Pelícano', 0),
(2, 'VERDADERO_FALSO', '¿Los Simpson viven en la ciudad ficticia de Springfield?', 'Verdadero', 'Falso', NULL, NULL, 0),
(3, 'OPCION_MULTIPLE', '¿Cómo se llama el fiel perro compañero de Mickey Mouse?', 'Goofy', 'Pluto', 'Snoopy', 'Scooby-Doo', 1),
(4, 'VERDADERO_FALSO', 'En la serie \'Tom y Jerry\', Jerry es un gato travieso.', 'Verdadero', 'Falso', NULL, NULL, 1),
(5, 'OPCION_MULTIPLE', '¿Cuál es el nombre del laboratorio secreto del genio niño Dexter en \'El laboratorio de Dexter\'?', 'Laboratorio Central', 'Su laboratorio oculto bajo su habitación', 'La Cueva', 'El Búnker', 1),
(6, 'VERDADERO_FALSO', 'En \'Los Picapiedra\', la familia vive en la prehistórica ciudad de Piedradura.', 'Verdadero', 'Falso', NULL, NULL, 0),
(7, 'OPCION_MULTIPLE', '¿Qué superhéroes infantiles están compuestos por Bombón, Burbuja y Bellota?', 'Las chicas superpoderosas', 'Los Jóvenes Titanes', 'Los Padrinos Mágicos', 'El Clan del Barrio', 0),
(8, 'VERDADERO_FALSO', 'En \'Phineas y Ferb\', el ornitorrinco mascota de los chicos se llama Perry y es un agente secreto.', 'Verdadero', 'Falso', NULL, NULL, 0),
(9, 'OPCION_MULTIPLE', '¿Cómo se llama el niño dueño de Scooby-Doo que comparte con él una gran obsesión por la comida?', 'Fred', 'Daphne', 'Shaggy', 'Velma', 2),
(10, 'VERDADERO_FALSO', 'En \'Avatar: La leyenda de Aang\', el protagonista Aang pertenece a la Tribu Agua.', 'Verdadero', 'Falso', NULL, NULL, 1),
(11, 'OPCION_MULTIPLE', '¿Qué personaje de Looney Tunes es un conejo sumamente astuto que siempre come zanahorias?', 'Pato Lucas', 'Bugs Bunny', 'Piolín', 'Tasmania', 1),
(12, 'VERDADERO_FALSO', 'En \'Hey Arnold!\', el protagonista Arnold tiene la cabeza con una forma característica parecida a un balón de fútbol americano.', 'Verdadero', 'Falso', NULL, NULL, 0),
(13, 'OPCION_MULTIPLE', '¿Cómo se llama el pez amigo y compañero de aventuras de Bob Esponja en Fondo de Bikini?', 'Calamardo', 'Don Cangrejo', 'Patricio Estrella', 'Arenita', 2),
(14, 'VERDADERO_FALSO', 'En \'Coraje, el perro cobarde\', el perro vive con sus dueños Eustacio y Muriel en medio de una zona desolada llamada Ningún Lugar.', 'Verdadero', 'Falso', NULL, NULL, 0),
(15, 'OPCION_MULTIPLE', '¿Cuál es el nombre del científico loco alcohólico que viaja por dimensiones junto a su nieto Morty?', 'Rick Sánchez', 'Profesor Hubert', 'Dr. Emmett Brown', 'Dexter', 0),
(16, 'VERDADERO_FALSO', 'En \'Gravity Falls\', los hermanos gemelos que pasan el verano con su tío abuelo Stan se llaman Dipper y Mabel.', 'Verdadero', 'Falso', NULL, NULL, 0),
(17, 'OPCION_MULTIPLE', '¿Cómo se llama el oso perezoso o amigo anaranjado de Gumball en \'El increíble mundo de Gumball\'?', 'Darwin', 'Tobias', 'Banana Joe', 'Bobert', 0),
(18, 'VERDADERO_FALSO', 'En \'Los Supersónicos\', la familia vive en el pasado en la época de las cavernas.', 'Verdadero', 'Falso', NULL, NULL, 1),
(19, 'OPCION_MULTIPLE', '¿Qué artefacto mágico otorgan las hadas Cosmo y Wanda al niño Timmy Turner en \'Los Padrinos Mágicos\'?', 'Una varita infinita', 'Poder concederle deseos cumpliendo reglas', 'Un libro de hechizos', 'Un anillo mágico', 1),
(20, 'VERDADERO_FALSO', 'En \'Batman: La serie animada\', el archienemigo principal del héroe es el Guasón (Joker).', 'Verdadero', 'Falso', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `futbol_argentino`
--

CREATE TABLE `futbol_argentino` (
  `id` int(11) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `enunciado` varchar(500) NOT NULL,
  `opcion_a` varchar(255) NOT NULL,
  `opcion_b` varchar(255) NOT NULL,
  `opcion_c` varchar(255) DEFAULT NULL,
  `opcion_d` varchar(255) DEFAULT NULL,
  `respuesta_correcta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `futbol_argentino`
--

INSERT INTO `futbol_argentino` (`id`, `tipo`, `enunciado`, `opcion_a`, `opcion_b`, `opcion_c`, `opcion_d`, `respuesta_correcta`) VALUES
(1, 'OPCION_MULTIPLE', '¿Qué club argentino ganó la Copa Libertadores en el año 1986?', 'Boca Juniors', 'River Plate', 'Independiente', 'Estudiantes', 1),
(2, 'VERDADERO_FALSO', '¿La Bombonera es el estadio oficial del club Atlético Boca Juniors?', 'Verdadero', 'Falso', NULL, NULL, 0),
(3, 'OPCION_MULTIPLE', '¿Qué equipo es conocido popularmente como \'El Rojo\' en el fútbol argentino?', 'San Lorenzo', 'Racing Club', 'Independiente', 'Huracán', 2),
(4, 'VERDADERO_FALSO', 'El estadio Monumental pertenece al club River Plate.', 'Verdadero', 'Falso', NULL, NULL, 0),
(5, 'OPCION_MULTIPLE', '¿Cuál es el máximo goleador histórico de la Selección Argentina de Fútbol?', 'Gabriel Batistuta', 'Diego Maradona', 'Lionel Messi', 'Sergio Agüero', 2),
(6, 'VERDADERO_FALSO', 'Boca Juniors e Independiente disputan el clásico de Avellaneda.', 'Verdadero', 'Falso', NULL, NULL, 1),
(7, 'OPCION_MULTIPLE', '¿Qué equipo de la provincia de Santa Fe es apodado \'El Canalla\'?', 'Newell\'s Old Boys', 'Rosario Central', 'Colón', 'Unión', 1),
(8, 'VERDADERO_FALSO', 'Talleres de Córdoba es un club originario de la provincia de Córdoba.', 'Verdadero', 'Falso', NULL, NULL, 0),
(9, 'OPCION_MULTIPLE', '¿En qué año la Selección Argentina ganó su primer Mundial de la mano de Mario Kempes?', '1978', '1986', '1990', '2022', 0),
(10, 'VERDADERO_FALSO', 'Diego Maradona debutó profesionalmente en la Primera División con la camiseta de Argentinos Juniors.', 'Verdadero', 'Falso', NULL, NULL, 0),
(11, 'OPCION_MULTIPLE', '¿Qué club ganó el Campeonato de Primera División invicto en el año 1967 (Metropolitano)?', 'Racing Club', 'Estudiantes de La Plata', 'San Lorenzo', 'Boca Juniors', 2),
(12, 'VERDADERO_FALSO', 'El clásico rival de Rosario Central es Newell\'s Old Boys.', 'Verdadero', 'Falso', NULL, NULL, 0),
(13, 'OPCION_MULTIPLE', '¿Qué equipo es apodado \'El Ciclón\'?', 'San Lorenzo', 'Vélez Sarsfield', 'Huracán', 'Gimnasia', 0),
(14, 'VERDADERO_FALSO', 'Racing Club e Independiente comparten el barrio de Avellaneda y sus estadios están muy cerca.', 'Verdadero', 'Falso', NULL, NULL, 0),
(15, 'OPCION_MULTIPLE', '¿Quién era el director técnico de la Selección Argentina en el Mundial de Qatar 2022?', 'Marcelo Bielsa', 'Lionel Scaloni', 'Jorge Sampaoli', 'Alejandro Sabella', 1),
(16, 'VERDADERO_FALSO', 'Estudiantes de La Plata ganó tres Copas Libertadores consecutivas en la década de 1960.', 'Verdadero', 'Falso', NULL, NULL, 0),
(17, 'OPCION_MULTIPLE', '¿Cómo se le denomina tradicionalmente al enfrentamiento entre Boca Juniors y River Plate?', 'El Clásico de Avellaneda', 'El Clásico de Cuyo', 'El Superclásico', 'El Derbi Nacional', 2),
(18, 'VERDADERO_FALSO', 'San Lorenzo de Almagro juega de local en el estadio Nuevo Gasómetro.', 'Verdadero', 'Falso', NULL, NULL, 0),
(19, 'OPCION_MULTIPLE', '¿Qué club argentino es conocido como \'El Fortín\'?', 'Vélez Sarsfield', 'Lanús', 'Banfield', 'Tigre', 0),
(20, 'VERDADERO_FALSO', 'La Selección Argentina ganó la Copa del Mundo en México 1986 con Diego Maradona como gran figura.', 'Verdadero', 'Falso', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `futbol_europeo`
--

CREATE TABLE `futbol_europeo` (
  `id` int(11) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `enunciado` varchar(500) NOT NULL,
  `opcion_a` varchar(255) NOT NULL,
  `opcion_b` varchar(255) NOT NULL,
  `opcion_c` varchar(255) DEFAULT NULL,
  `opcion_d` varchar(255) DEFAULT NULL,
  `respuesta_correcta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `futbol_europeo`
--

INSERT INTO `futbol_europeo` (`id`, `tipo`, `enunciado`, `opcion_a`, `opcion_b`, `opcion_c`, `opcion_d`, `respuesta_correcta`) VALUES
(1, 'OPCION_MULTIPLE', '¿Qué equipo ostenta el récord histórico de más títulos de la UEFA Champions League?', 'AC Milan', 'FC Barcelona', 'Real Madrid', 'Liverpool', 2),
(2, 'VERDADERO_FALSO', '¿Lionel Messi ganó la Champions League vistiendo la camiseta del Paris Saint-Germain (PSG)?', 'Verdadero', 'Falso', NULL, NULL, 1),
(3, 'OPCION_MULTIPLE', '¿En qué país se disputa la Serie A, liga donde juegan equipos como Juventus, Inter y Milan?', 'España', 'Inglaterra', 'Italia', 'Alemania', 2),
(4, 'VERDADERO_FALSO', 'El Bayern Múnich es uno de los clubes más laureados de la Bundesliga de Alemania.', 'Verdadero', 'Falso', NULL, NULL, 0),
(5, 'OPCION_MULTIPLE', '¿Cuál es el estadio oficial del Real Madrid Club de Fútbol?', 'Camp Nou', 'Wembley', 'Santiago Bernabéu', 'Anfield', 2),
(6, 'VERDADERO_FALSO', 'El FC Barcelona juega sus partidos de local en el estadio de San Mamés.', 'Verdadero', 'Falso', NULL, NULL, 1),
(7, 'OPCION_MULTIPLE', '¿Qué equipo de la Premier League inglesa es conocido como \'Los Diablos Rojos\'?', 'Manchester City', 'Manchester United', 'Arsenal', 'Chelsea', 1),
(8, 'VERDADERO_FALSO', 'El Liverpool Football Club tiene como famoso lema la frase \'You\'ll Never Walk Alone\'.', 'Verdadero', 'Falso', NULL, NULL, 0),
(9, 'OPCION_MULTIPLE', '¿Quién es el máximo goleador histórico de la UEFA Champions League?', 'Lionel Messi', 'Robert Lewandowski', 'Cristiano Ronaldo', 'Karim Benzema', 2),
(10, 'VERDADERO_FALSO', 'El Paris Saint-Germain (PSG) pertenece a la liga de fútbol de Francia (Ligue 1).', 'Verdadero', 'Falso', NULL, NULL, 0),
(11, 'OPCION_MULTIPLE', '¿Qué equipo ganó el histórico triplete (Liga, Copa y Champions League) bajo la dirección de Pep Guardiola en 2009?', 'Real Madrid', 'FC Barcelona', 'Bayern Múnich', 'Inter de Milán', 1),
(12, 'VERDADERO_FALSO', 'La Premier League de Inglaterra es considerada por muchos la liga nacional más competitiva del mundo.', 'Verdadero', 'Falso', NULL, NULL, 0),
(13, 'OPCION_MULTIPLE', '¿Cómo se llama el derbi de la ciudad de Londres que enfrenta al Arsenal y al Tottenham Hotspur?', 'Derbi de Merseyside', 'Derbi del Norte de Londres', 'Derbi de Múnich', 'Clásico español', 1),
(14, 'VERDADERO_FALSO', 'El Borussia Dortmund es conocido por su famosa tribuna amarilla llamada \'Muro Amarillo\'.', 'Verdadero', 'Falso', NULL, NULL, 0),
(15, 'OPCION_MULTIPLE', '¿Qué club italiano ganó el triplete en el año 2010 dirigido por José Mourinho?', 'AC Milan', 'Juventus', 'Inter de Milán', 'Roma', 2),
(16, 'VERDADERO_FALSO', 'El Ajax de Ámsterdam es un equipo histórico perteneciente a la liga de los Países Bajos (Eredivisie).', 'Verdadero', 'Falso', NULL, NULL, 0),
(17, 'OPCION_MULTIPLE', '¿En qué ciudad juega sus partidos de local el Atlético de Madrid?', 'Barcelona', 'Sevilla', 'Madrid', 'Valencia', 2),
(18, 'VERDADERO_FALSO', 'El trofeo de la UEFA Champions League es popularmente conocido como \'La Orejona\'.', 'Verdadero', 'Falso', NULL, NULL, 0),
(19, 'OPCION_MULTIPLE', '¿Cuál de los siguientes equipos pertenece a la liga de Portugal?', 'Porto', 'Ajax', 'Celtic', 'Galatasaray', 0),
(20, 'VERDADERO_FALSO', 'La selección de fútbol de Italia ganó la Eurocopa disputada en el año 2021.', 'Verdadero', 'Falso', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

CREATE TABLE `peliculas` (
  `id` int(11) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `enunciado` varchar(500) NOT NULL,
  `opcion_a` varchar(255) NOT NULL,
  `opcion_b` varchar(255) NOT NULL,
  `opcion_c` varchar(255) DEFAULT NULL,
  `opcion_d` varchar(255) DEFAULT NULL,
  `respuesta_correcta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`id`, `tipo`, `enunciado`, `opcion_a`, `opcion_b`, `opcion_c`, `opcion_d`, `respuesta_correcta`) VALUES
(1, 'OPCION_MULTIPLE', '¿Cómo se llama el tiburón de la clásica película de suspense dirigida por Steven Spielberg en 1975?', 'Megalodón', 'Bruce', 'Tiburcio', 'Jaws', 1),
(2, 'VERDADERO_FALSO', '¿En la trilogía de \'El Señor de los Anillos\', el Anillo Único fue destruido en el Monte del Destino?', 'Verdadero', 'Falso', NULL, NULL, 0),
(3, 'OPCION_MULTIPLE', '¿Qué director ganó el Óscar a Mejor Director por \'Titanic\'?', 'Steven Spielberg', 'James Cameron', 'Christopher Nolan', 'Quentin Tarantino', 1),
(4, 'VERDADERO_FALSO', '¿En Matrix, la pastilla roja hace que Neo despierte en el mundo real?', 'Verdadero', 'Falso', NULL, NULL, 0),
(5, 'OPCION_MULTIPLE', '¿Cuál es el nombre del león protagonista en \'El Rey León\'?', 'Kovu', 'Mufasa', 'Simba', 'Scar', 2),
(6, 'VERDADERO_FALSO', '¿En \'Volver al Futuro\', el auto utilizado como máquina del tiempo es un DeLorean?', 'Verdadero', 'Falso', NULL, NULL, 0),
(7, 'OPCION_MULTIPLE', '¿Cómo se llama el androide asesino en la primera película de \'Terminator\'?', 'T-1000', 'T-800', 'Cyberdyne', 'Skynet', 1),
(8, 'VERDADERO_FALSO', '¿En \'Jurassic Park\', los dinosaurios son clonados a partir de ADN extraído de un mosquito fosilizado?', 'Verdadero', 'Falso', NULL, NULL, 0),
(9, 'OPCION_MULTIPLE', '¿Qué actor interpreta a Jack Dawson en \'Titanic\'?', 'Brad Pitt', 'Tom Cruise', 'Leonardo DiCaprio', 'Matt Damon', 2),
(10, 'VERDADERO_FALSO', '¿En la saga de \'Harry Potter\', la casa de Hogwarts a la que pertenece Draco Malfoy es Gryffindor?', 'Verdadero', 'Falso', NULL, NULL, 1),
(11, 'OPCION_MULTIPLE', '¿Cómo se llama el archienemigo de Darth Vader en \'Star Wars\' antes de caer al Lado Oscuro?', 'Anakin Skywalker', 'Obi-Wan Kenobi', 'Luke Skywalker', 'Palpatine', 0),
(12, 'VERDADERO_FALSO', '¿En \'Gladiador\', el emperador que asesina a Marco Aurelio se llama Cómodo?', 'Verdadero', 'Falso', NULL, NULL, 0),
(13, 'OPCION_MULTIPLE', '¿Cuál es la ciudad donde se desarrolla principalmente la película \'Spider-Man\' de Marvel?', 'Los Ángeles', 'Chicago', 'Nueva York', 'Boston', 2),
(14, 'VERDADERO_FALSO', '¿En \'Buscando a Nemo\', Nemo es un pez payaso?', 'Verdadero', 'Falso', NULL, NULL, 0),
(15, 'OPCION_MULTIPLE', '¿Qué objeto mágico coleccionan los Vengadores en las películas de Marvel para chasquear los dedos?', 'Las Gemas del Infinito', 'Las Reliquias de la Muerte', 'El Anillo Único', 'La Piedra Filosofal', 0),
(16, 'VERDADERO_FALSO', '¿En \'Avatar\', el nombre del planeta habitado por los Na\'vi es Pandora?', 'Verdadero', 'Falso', NULL, NULL, 0),
(17, 'OPCION_MULTIPLE', '¿Cómo se llama el ogro protagonista de la famosa película animada de DreamWorks?', 'Fiona', 'Burro', 'Shrek', 'Lord Farquaad', 2),
(18, 'VERDADERO_FALSO', '¿En \'Ratatouille\', el sueño de Remy es convertirse en un famoso pintor?', 'Verdadero', 'Falso', NULL, NULL, 1),
(19, 'OPCION_MULTIPLE', '¿Qué instrumento musical toca el personaje de Rose en el bar de tercera clase en \'Titanic\'?', 'Violín', 'Piano', 'Flauta', 'Ninguno, solo baila', 3),
(20, 'VERDADERO_FALSO', '¿En \'Indiana Jones\', el protagonista le teme a las serpientes?', 'Verdadero', 'Falso', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ranking`
--

CREATE TABLE `ranking` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `puntaje` int(11) NOT NULL DEFAULT '0',
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ranking`
--

INSERT INTO `ranking` (`id`, `id_usuario`, `puntaje`, `fecha`) VALUES
(1, 1, 10, '2026-11-07 06:25:20'),
(2, 1, 0, '2026-11-07 06:33:09'),
(3, 2, 30, '2026-11-07 06:37:26'),
(4, 1, 10, '2026-11-07 16:55:59'),
(5, 1, 40, '2026-11-07 16:57:26'),
(6, 1, 60, '2026-11-07 17:18:50'),
(7, 1, 20, '2026-11-07 17:32:10'),
(8, 1, 30, '2026-11-07 17:32:38');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `series`
--

CREATE TABLE `series` (
  `id` int(11) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `enunciado` varchar(500) NOT NULL,
  `opcion_a` varchar(255) NOT NULL,
  `opcion_b` varchar(255) NOT NULL,
  `opcion_c` varchar(255) DEFAULT NULL,
  `opcion_d` varchar(255) DEFAULT NULL,
  `respuesta_correcta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `series`
--

INSERT INTO `series` (`id`, `tipo`, `enunciado`, `opcion_a`, `opcion_b`, `opcion_c`, `opcion_d`, `respuesta_correcta`) VALUES
(1, 'OPCION_MULTIPLE', '¿Cuál es el nombre del profesor de química que se convierte en narcotraficante en Breaking Bad?', 'Jesse Pinkman', 'Gustavo Fring', 'Walter White', 'Saul Goodman', 2),
(2, 'VERDADERO_FALSO', 'En la serie \'Game of Thrones\', ¿la casa Stark tiene como lema \'Se acerca el invierno\'?', 'Verdadero', 'Falso', NULL, NULL, 0),
(3, 'OPCION_MULTIPLE', '¿Cómo se llama el pueblo ficticio donde ocurren los extraños sucesos de \'Stranger Things\'?', 'Hawkins', 'Riverdale', 'Sunnydale', 'Gravity Falls', 0),
(4, 'VERDADERO_FALSO', '¿En \'The Walking Dead\', el protagonista principal que despierta en un hospital es Rick Grimes?', 'Verdadero', 'Falso', NULL, NULL, 0),
(5, 'OPCION_MULTIPLE', '¿Qué banda criminal lidera el Profesor en la serie española \'La Casa de Papel\'?', 'Los Dalíes', 'La Resistencia', 'Los Bandidos', 'No tienen nombre oficial', 1),
(6, 'VERDADERO_FALSO', '¿En \'Friends\', Ross Geller es paleontólogo?', 'Verdadero', 'Falso', NULL, NULL, 0),
(7, 'OPCION_MULTIPLE', '¿Cuál es el nombre del bar donde se reúnen los protagonistas de \'How I Met Your Mother\'?', 'Central Perk', 'MacLaren\'s Pub', 'Moe\'s Tavern', 'Monk\'s Diner', 1),
(8, 'VERDADERO_FALSO', 'En \'The Big Bang Theory\', ¿Sheldon Cooper trabaja como físico experimental?', 'Verdadero', 'Falso', NULL, NULL, 1),
(9, 'OPCION_MULTIPLE', '¿Cómo se llama el asesino serial forense de la serie \'Dexter\'?', 'Dexter Morgan', 'Joe Goldberg', 'Norman Bates', 'Hannibal Lecter', 0),
(10, 'VERDADERO_FALSO', 'En \'Prison Break\', Michael Scofield se tatúa los planos de la cárcel en el cuerpo para rescatar a su hermano.', 'Verdadero', 'Falso', NULL, NULL, 0),
(11, 'OPCION_MULTIPLE', '¿Qué bebida energizante o café adora el agente Dale Cooper en \'Twin Peaks\'?', 'Té verde', 'Café negro y pastel de cereza', 'Cerveza de raíz', 'Chocolate caliente', 1),
(12, 'VERDADERO_FALSO', 'En \'Peaky Blinders\', la familia Shelby opera principalmente en la ciudad de Londres.', 'Verdadero', 'Falso', NULL, NULL, 1),
(13, 'OPCION_MULTIPLE', '¿Cómo se llama el lema de la familia Lannister en \'Game of Thrones\'?', 'Crece nuestro furor', 'Un Lannister siempre paga sus deudas', 'Fuego y Sangre', 'Nuestro es el invierno', 1),
(14, 'VERDADERO_FALSO', 'En \'Los Simpsons\', aunque es una serie animada, es considerada una de las comedias televisivas más longevas de la historia.', 'Verdadero', 'Falso', NULL, NULL, 0),
(15, 'OPCION_MULTIPLE', '¿Qué instrumento toca Lisa Simpson?', 'Saxo', 'Flauta', 'Violín', 'Guitarra', 0),
(16, 'VERDADERO_FALSO', 'En \'El Juego del Calamar\', el juego inicial que elimina a los perdedores se llama \'Luz roja, luz verde\'.', 'Verdadero', 'Falso', NULL, NULL, 0),
(17, 'OPCION_MULTIPLE', '¿Cómo se llama el protagonista de la serie \'You\' que trabaja en librerías y es un acosador?', 'Joe Goldberg', 'Dan Humphrey', 'Ted Mosby', 'Barney Stinson', 0),
(18, 'VERDADERO_FALSO', 'En \'Grey\'s Anatomy\', el hospital principal donde transcurre la serie se llama Princeton-Plainsboro.', 'Verdadero', 'Falso', NULL, NULL, 1),
(19, 'OPCION_MULTIPLE', '¿Cuál es el verdadero nombre de Eleven en \'Stranger Things\'?', 'Jane Hopper', 'Max Mayfield', 'Nancy Wheeler', 'Joyce Byers', 0),
(20, 'VERDADERO_FALSO', 'En \'Vikings\', Ragnar Lothbrok afirma ser descendiente del dios Odín.', 'Verdadero', 'Falso', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre_usuario`, `password`) VALUES
(1, 'Dylan', '1234'),
(2, 'Delfina', '123'),
(3, 'Alex', '188');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `animes`
--
ALTER TABLE `animes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `dibujos_animados`
--
ALTER TABLE `dibujos_animados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `futbol_argentino`
--
ALTER TABLE `futbol_argentino`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `futbol_europeo`
--
ALTER TABLE `futbol_europeo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ranking`
--
ALTER TABLE `ranking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_usuario_ranking` (`id_usuario`);

--
-- Indices de la tabla `series`
--
ALTER TABLE `series`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre_usuario` (`nombre_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `animes`
--
ALTER TABLE `animes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `dibujos_animados`
--
ALTER TABLE `dibujos_animados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `futbol_argentino`
--
ALTER TABLE `futbol_argentino`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `futbol_europeo`
--
ALTER TABLE `futbol_europeo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `ranking`
--
ALTER TABLE `ranking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `series`
--
ALTER TABLE `series`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ranking`
--
ALTER TABLE `ranking`
  ADD CONSTRAINT `fk_usuario_ranking` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
