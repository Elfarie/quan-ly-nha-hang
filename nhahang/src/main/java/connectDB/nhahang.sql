-- Create the database
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

-- Create the database
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

-- Table for Sections
CREATE TABLE Khu (
    MaKhu VARCHAR(50) PRIMARY KEY,
    TenKhu VARCHAR(255) NOT NULL
);

-- Table for Tables
CREATE TABLE Ban (
    MaBan VARCHAR(50) PRIMARY KEY,
    SoLuongNguoi INT NOT NULL,
    MaKhu VARCHAR(50),
    TrangThai BIT NOT NULL,
    DonGia FLOAT NOT NULL,  -- Changed from DECIMAL to FLOAT
    FOREIGN KEY (MaKhu) REFERENCES Khu(MaKhu)
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
    MaCTHD VARCHAR(50) PRIMARY KEY,
    MaHD VARCHAR(50),
    MaMon VARCHAR(50),
    SoLuong INT NOT NULL,
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
-- Insert records into Khu table
INSERT INTO Khu (MaKhu, TenKhu) VALUES ('KHU01', 'VIP');
INSERT INTO Khu (MaKhu, TenKhu) VALUES ('KHU02', 'THƯỜNG');
-- Insert a record into the Employees table (NhanVien)
INSERT INTO NhanVien (MaNV, TenNV, SoDienThoai) VALUES ('NVQL000', 'Nguyen Van A', '0123456789');

-- Insert a record into the Employee Accounts table (TK_NhanVien)
INSERT INTO TK_NhanVien (MaNV, MatKhauTK) VALUES ('NVQL000', '1');
