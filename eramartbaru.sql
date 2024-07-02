-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2023 at 06:38 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eramartbaru`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `totalharga` ()   BEGIN
SELECT 
SUM(keranjang.jumlah*keranjang.harga) AS total
FROM keranjang;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `idadmin` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`idadmin`, `username`, `password`) VALUES
(1, 'edoarya', '123'),
(2, 'fauzanzulfa', '123');

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `idbarang` int(10) NOT NULL,
  `idkategori` int(10) NOT NULL,
  `namabarang` varchar(50) NOT NULL,
  `harga` int(20) NOT NULL,
  `stok` int(20) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`idbarang`, `idkategori`, `namabarang`, `harga`, `stok`, `tanggal`) VALUES
(100, 10, 'OREO BLACKPINK EDITION', 25000, 9, '2022-12-24'),
(101, 11, 'ULTRA MILK CHOCOLATE', 7500, 18, '2022-12-24'),
(102, 12, 'BUKU TULIS A5', 6000, 30, '2022-12-24'),
(103, 10, 'APOLLO LAYER CAKE', 29000, 30, '2022-12-24'),
(104, 10, 'CHITATO', 12000, 15, '2022-12-24'),
(105, 10, 'LAYS NORI SEAWEED', 11500, 11, '2022-12-24'),
(106, 17, 'PIGEON BABY TRANSPARENT SOAP', 38200, 30, '2022-12-25'),
(108, 17, 'GOO N PREMIUM PANTS', 179900, 50, '2022-12-24'),
(109, 17, 'SENSI REGULAR PANTS', 78500, 9, '2022-12-24'),
(110, 17, 'MAMYPOKO DIAPERS EXTRA DRY', 229400, 25, '2022-12-24'),
(111, 17, 'KODOMO TOOTHBRUSH KIDS', 8800, 35, '2022-12-24'),
(112, 17, 'KODOMO TOOTHPASTE', 6400, 15, '2022-12-24'),
(113, 17, 'MY BABY POWDER', 21500, 15, '2022-12-24'),
(114, 12, 'CRAYON TITI 48 PCS', 65000, 10, '2022-12-24'),
(115, 12, 'PENTEL OIL PASTEL CRAYON 12 PCS', 24000, 10, '2022-12-24'),
(116, 12, 'BUKU GAMBAR A3', 12000, 25, '2022-12-24'),
(119, 12, 'PENGHAPUS', 6500, 18, '2022-12-24'),
(120, 14, 'OBH COMBI DEWASA BATUK FLU', 21100, 40, '2022-12-24'),
(121, 14, 'PARACETAMOL', 5500, 20, '2022-12-24'),
(122, 14, 'SIANTAN DEMAM TYPHUS', 43200, 40, '2022-12-24'),
(123, 14, 'SIANTAN DEMAM BERDARAH', 43100, 38, '2022-12-24'),
(124, 14, 'SARI KURMA SAHARA', 27600, 32, '2022-12-24'),
(125, 14, 'ULTRAFLU TABLET', 3600, 75, '2022-12-24'),
(126, 14, 'PANADOL SIRUP', 60300, 45, '2022-12-24'),
(127, 14, 'DECOLGEN TABLET', 2700, 90, '2022-12-24'),
(128, 14, 'TOLAK ANGIN CAIR', 20300, 20, '2022-12-24'),
(129, 12, 'ORIGAMI KERTAS LIPAT SIDU', 2500, 15, '2022-12-24'),
(130, 12, 'KUAS LUKIS', 6000, 20, '2022-12-24'),
(131, 12, 'PALET KAYU OVAL', 17000, 25, '2022-12-24'),
(132, 12, 'SNOWMAN WHITEBOARD MARKER', 5500, 60, '2022-12-24'),
(135, 11, 'FANTA STRAWBERRY', 15800, 28, '2022-12-24'),
(136, 11, 'SPRITE', 6500, 30, '2022-12-24'),
(137, 11, 'FANTA ORANGE', 6500, 30, '2022-12-24'),
(139, 11, 'SOSRO TEH BOTOL KOTAK', 17400, 30, '2022-12-24'),
(140, 11, 'TEBS', 4700, 30, '2022-12-24'),
(141, 11, 'TEH PUCUK HARUM', 3600, 70, '2022-12-24'),
(142, 11, 'TEH GELAS ORIGINAL', 3300, 30, '2022-12-24'),
(143, 11, 'FRUIT TEA BLACK CURRANT', 6600, 45, '2022-12-24'),
(144, 11, 'FRESTEA FRUTCY PASSION', 5600, 30, '2022-12-24'),
(145, 11, 'NESCAFE LATTE ARABICA', 10000, 35, '2022-12-24'),
(146, 11, 'KOPIKO COFFE LATTE', 6100, 35, '2022-12-24'),
(147, 10, 'TARO', 11900, 12, '2022-12-24'),
(148, 10, 'GOODTIME COOKIES', 8600, 38, '2022-12-24'),
(149, 10, 'ROMA KELAPA', 13000, 55, '2022-12-24'),
(150, 16, 'GOLDSTAR SOSIS BRATWUST', 50400, 30, '2022-12-24'),
(151, 16, 'GOLDEN FARM STRAIGHT CUT', 42600, 30, '2022-12-24'),
(152, 16, 'GOLDEN FARM GREEN PEAS', 26800, 40, '2022-12-24'),
(153, 16, 'SO GOOD CHICKEN STRIP', 38800, 30, '2022-12-24'),
(154, 16, 'FIESTA CHICKEN KARAGE', 65700, 35, '2022-12-24'),
(155, 16, 'SO GOOD SPICY WING', 60700, 30, '2022-12-24'),
(157, 15, 'PANTENE SHAMPOO', 50200, 24, '2022-12-24'),
(168, 12, 'PENGGARIS', 15000, 7, '2022-12-31'),
(174, 12, 'GUNTING', 20000, 20, '2022-12-31');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `idkategori` int(10) NOT NULL,
  `namakategori` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`idkategori`, `namakategori`) VALUES
(10, 'Food'),
(11, 'Beverage'),
(12, 'Stationary'),
(14, 'Pharmacy'),
(15, 'Health'),
(16, 'Fresh Food'),
(17, 'Moms & Babies');

-- --------------------------------------------------------

--
-- Table structure for table `keranjang`
--

CREATE TABLE `keranjang` (
  `idtransaksi` int(10) NOT NULL,
  `idbarang` int(10) NOT NULL,
  `namabarang` varchar(50) NOT NULL,
  `harga` int(20) NOT NULL,
  `jumlah` int(20) NOT NULL,
  `total` int(20) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `keranjang`
--
DELIMITER $$
CREATE TRIGGER `cancel` AFTER DELETE ON `keranjang` FOR EACH ROW BEGIN
UPDATE barang SET
stok = stok + OLD.jumlah
WHERE idbarang = OLD.idbarang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `cancel_2` AFTER DELETE ON `keranjang` FOR EACH ROW BEGIN
DELETE FROM transaksi
WHERE idbarang = OLD.idbarang;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `stok_habis` AFTER INSERT ON `keranjang` FOR EACH ROW BEGIN
DELETE FROM barang
WHERE stok = 0
AND
idbarang = NEW.idbarang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `idpetugas` int(10) NOT NULL,
  `namapetugas` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`idpetugas`, `namapetugas`, `email`, `alamat`, `username`, `password`, `tanggal`) VALUES
(50, 'Edo Arya', 'edoarya@gmail.com', 'Jalan Anyelir, Swarga Bara', 'edoarya', '123', '2022-12-24'),
(51, 'Fauzan Zulfa', 'fauzanzulfa@gmail.com', 'Jalan Flamboyan, Swarga Bara', 'fauzanzulfa', '456', '2022-12-24');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `idtransaksi` int(10) NOT NULL,
  `idbarang` int(10) NOT NULL,
  `namabarang` varchar(50) NOT NULL,
  `harga` int(20) NOT NULL,
  `jumlah` int(20) NOT NULL,
  `total` int(20) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`idtransaksi`, `idbarang`, `namabarang`, `harga`, `jumlah`, `total`, `tanggal`) VALUES
(12, 102, 'Buku Tulis A5', 4500, 20, 90000, '2022-12-24'),
(13, 104, 'Chitato', 12000, 5, 60000, '2022-12-24'),
(14, 105, 'Lays Nori Seaweed', 11500, 2, 23000, '2022-12-24'),
(16, 105, 'Lays Nori Seaweed', 11500, 2, 23000, '2022-12-24'),
(21, 157, 'PANTENE SHAMPOO', 50200, 1, 50200, '2022-12-31'),
(22, 109, 'SENSI REGULAR PANTS', 78500, 1, 78500, '2022-12-31'),
(24, 147, 'TARO', 11900, 3, 35700, '2022-12-31'),
(26, 119, 'PENGHAPUS', 6500, 2, 13000, '2022-12-31'),
(27, 168, 'PENGGARIS', 15000, 3, 45000, '2022-12-31'),
(29, 135, 'FANTA STRAWBERRY', 15800, 2, 31600, '2022-12-31'),
(30, 147, 'TARO', 11900, 5, 59500, '2022-12-31'),
(33, 100, 'OREO BLACKPINK EDITION', 25000, 2, 50000, '2023-01-03'),
(35, 100, 'OREO BLACKPINK EDITION', 25000, 2, 50000, '2023-01-03'),
(36, 101, 'ULTRA MILK CHOCOLATE', 7500, 2, 15000, '2023-01-03');

--
-- Triggers `transaksi`
--
DELIMITER $$
CREATE TRIGGER `keranjang` AFTER INSERT ON `transaksi` FOR EACH ROW BEGIN
INSERT INTO keranjang SET
idtransaksi = NEW.idtransaksi,
idbarang = NEW.idbarang,
namabarang = NEW.namabarang,
harga = NEW.harga,
jumlah = NEW.jumlah,
total = NEW.total,
tanggal = NEW.tanggal;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `transaksi` AFTER INSERT ON `transaksi` FOR EACH ROW BEGIN
UPDATE barang SET
stok = stok - NEW.jumlah
WHERE idbarang = NEW.idbarang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_kategoribarang`
-- (See below for the actual view)
--
CREATE TABLE `view_kategoribarang` (
`idbarang` int(10)
,`namabarang` varchar(50)
,`harga` int(20)
,`stok` int(20)
,`tanggal` date
,`namakategori` varchar(50)
);

-- --------------------------------------------------------

--
-- Structure for view `view_kategoribarang`
--
DROP TABLE IF EXISTS `view_kategoribarang`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_kategoribarang`  AS SELECT `barang`.`idbarang` AS `idbarang`, `barang`.`namabarang` AS `namabarang`, `barang`.`harga` AS `harga`, `barang`.`stok` AS `stok`, `barang`.`tanggal` AS `tanggal`, `kategori`.`namakategori` AS `namakategori` FROM (`barang` join `kategori` on(`barang`.`idkategori` = `kategori`.`idkategori`))  ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idadmin`);

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`idbarang`),
  ADD KEY `idkategori` (`idkategori`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`idkategori`);

--
-- Indexes for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD PRIMARY KEY (`idtransaksi`),
  ADD KEY `idbarang` (`idbarang`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`idpetugas`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`idtransaksi`),
  ADD KEY `idbarang` (`idbarang`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `idadmin` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `idbarang` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=175;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `idkategori` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `keranjang`
--
ALTER TABLE `keranjang`
  MODIFY `idtransaksi` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `petugas`
--
ALTER TABLE `petugas`
  MODIFY `idpetugas` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `idtransaksi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`idkategori`) REFERENCES `kategori` (`idkategori`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD CONSTRAINT `keranjang_ibfk_1` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
