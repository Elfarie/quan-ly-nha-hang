-- Create the database if it doesn't already exist
IF EXISTS (SELECT name FROM sys.databases WHERE name = 'RestaurantManagement')
BEGIN
    DROP DATABASE RestaurantManagement;
END
GO

CREATE DATABASE RestaurantManagement;
GO

-- Use the newly created database
USE RestaurantManagement;
GO

-- Table for Menu Items
CREATE TABLE MonAn (
    MaMon VARCHAR(50) PRIMARY KEY,
    TenMon VARCHAR(255) NOT NULL,
    DonGia FLOAT NOT NULL  -- Changed from DECIMAL to FLOAT
);

-- Table for Employees
CREATE TABLE NhanVien (
    MaNV VARCHAR(50) PRIMARY KEY,
    TenNV VARCHAR(255) NOT NULL,
    SoDienThoai VARCHAR(15)
);

-- Table for Customers
CREATE TABLE KhachHang (
    MaKH VARCHAR(50) PRIMARY KEY,
    TenKH VARCHAR(255) NOT NULL,
    SoDienThoai VARCHAR(15)
);

-- Table for Tables
CREATE TABLE Ban (
    MaBan VARCHAR(50) PRIMARY KEY,
    SoLuongNguoi INT NOT NULL,
    TrangThai BIT NOT NULL
);

-- Table for Invoices
CREATE TABLE HoaDon (
    MaHD VARCHAR(50) PRIMARY KEY,
    MaNV VARCHAR(50),  -- Changed from INT to VARCHAR
    MaKH VARCHAR(50),  -- Changed from INT to VARCHAR
    NgayLapHD DATE NOT NULL,
    Thue FLOAT,  -- Changed from DECIMAL to FLOAT
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
);

-- Table for Invoice Details
CREATE TABLE ChiTiet_HoaDon (
    MaHD VARCHAR(50),
    MaMon VARCHAR(50),
    SoLuong INT NOT NULL,
    PRIMARY KEY (MaHD, MaMon),  -- Composite primary key
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
    FOREIGN KEY (MaMon) REFERENCES MonAn(MaMon)
);

-- Table for Reservation Details
CREATE TABLE ChiTiet_PhieuDatBan (
    MaCTPhieu VARCHAR(50) PRIMARY KEY,
    MaBan VARCHAR(50),
    MaPhieu VARCHAR(50),
    FOREIGN KEY (MaBan) REFERENCES Ban(MaBan),
    FOREIGN KEY (MaPhieu) REFERENCES HoaDon(MaHD)
);

-- Table for Employee Accounts
CREATE TABLE TK_NhanVien (
    MaNV VARCHAR(50) PRIMARY KEY,
    MatKhauTK VARCHAR(255) NOT NULL,
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

-- Insert records into MonAn table
INSERT INTO MonAn (MaMon, TenMon, DonGia) VALUES
('M001', 'Pho Bo', 50000),
('M002', 'Bun Cha', 40000),
('M003', 'Com Tam', 45000),
('M004', 'Banh Mi', 30000),
('M005', 'Bun Rieu', 35000),
('M006', 'Mi Quang', 55000),
('M007', 'Lau Thai', 250000),
('M008', 'Goi Cuon', 30000),
('M009', 'Ca Kho To', 60000),
('M010', 'Canh Chua', 55000);

-- Insert records into NhanVien table
INSERT INTO NhanVien (MaNV, TenNV, SoDienThoai) VALUES
('NV001', 'Tran Van B', '0912345678'),
('NV002', 'Le Thi C', '0923456789'),
('NV003', 'Pham Van D', '0934567890'),
('NV004', 'Nguyen Thi E', '0945678901'),
('NV005', 'Hoang Van F', '0956789012'),
('NV006', 'Vu Thi G', '0967890123'),
('NV007', 'Dang Van H', '0978901234'),
('NV008', 'Bui Thi I', '0989012345'),
('NV009', 'Do Van J', '0990123456'),
('NV010', 'Ngo Thi K', '0901234567');

-- Insert records into KhachHang table
INSERT INTO KhachHang (MaKH, TenKH, SoDienThoai) VALUES
('KH001', 'Pham Van L', '0812345678'),
('KH002', 'Do Thi M', '0823456789'),
('KH003', 'Nguyen Van N', '0834567890'),
('KH004', 'Tran Thi O', '0845678901'),
('KH005', 'Le Van P', '0856789012'),
('KH006', 'Hoang Thi Q', '0867890123'),
('KH007', 'Vu Van R', '0878901234'),
('KH008', 'Dang Thi S', '0889012345'),
('KH009', 'Bui Van T', '0890123456'),
('KH010', 'Ngo Thi U', '0801234567');

-- Insert records into Ban table
INSERT INTO Ban (MaBan, SoLuongNguoi, TrangThai) VALUES
('B001', 4, 1),
('B002', 2, 0),
('B003', 6, 1),
('B004', 4, 0),
('B005', 8, 1),
('B006', 2, 1),
('B007', 10, 0),
('B008', 6, 1),
('B009', 4, 1),
('B010', 2, 0);
-- Insert a record into the Employees table (NhanVien)
INSERT INTO NhanVien (MaNV, TenNV, SoDienThoai) VALUES ('NVQL000', 'Nguyen Van A', '0123456789');

-- Insert a record into the Employee Accounts table (TK_NhanVien)
INSERT INTO TK_NhanVien (MaNV, MatKhauTK) VALUES ('NVQL000', '1');