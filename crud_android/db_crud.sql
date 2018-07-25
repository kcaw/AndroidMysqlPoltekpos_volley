-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2018 at 02:43 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_crud`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_mahasiswa`
--

CREATE TABLE `tb_mahasiswa` (
  `npm` varchar(15) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `prodi` varchar(50) NOT NULL,
  `fakultas` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_mahasiswa`
--

INSERT INTO `tb_mahasiswa` (`npm`, `nama`, `prodi`, `fakultas`) VALUES
('', '', '', ''),
('1164076', 'Imron Sumadireja', 'Diploma IV', 'Teknik Informatika'),
('1164089', 'Yusniar Nur Syarif Sidiq', 'Diploma IV', 'Teknik Informatika');

-- --------------------------------------------------------

--
-- Table structure for table `tb_ortu`
--

CREATE TABLE `tb_ortu` (
  `nik` varchar(15) NOT NULL,
  `nortu` varchar(50) NOT NULL,
  `pekerjaan` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_ortu`
--

INSERT INTO `tb_ortu` (`nik`, `nortu`, `pekerjaan`, `alamat`) VALUES
('1122', 'Rahmad S', 'Bisnis', 'Bandung');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pembayaran`
--

CREATE TABLE `tb_pembayaran` (
  `nisn` varchar(15) NOT NULL,
  `nsiswa` varchar(50) NOT NULL,
  `pilprodi` varchar(50) NOT NULL,
  `piljurusan` varchar(50) NOT NULL,
  `jumlah` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_pembayaran`
--

INSERT INTO `tb_pembayaran` (`nisn`, `nsiswa`, `pilprodi`, `piljurusan`, `jumlah`) VALUES
('1164076', 'Imron Sumadireja', 'Diploma IV', 'Teknik Informatika', 'Rp. 7.400.000'),
('1164089', 'Yusniar Nur Syarif Sidiq', 'Diploma IV', 'Teknik Informatika', 'Rp.7.400.000'),
('8282829292', 'Ijat', 'hdjak', 'hsjs', '500');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_mahasiswa`
--
ALTER TABLE `tb_mahasiswa`
  ADD PRIMARY KEY (`npm`);

--
-- Indexes for table `tb_ortu`
--
ALTER TABLE `tb_ortu`
  ADD PRIMARY KEY (`nik`);

--
-- Indexes for table `tb_pembayaran`
--
ALTER TABLE `tb_pembayaran`
  ADD PRIMARY KEY (`nisn`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
