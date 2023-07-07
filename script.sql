CREATE DATABASE ShoppingStore
USE ShoppingStore

CREATE TABLE Roles (
	RoleID varchar(3) PRIMARY KEY,
	Rolename varchar(15) NOT NULL,
	[Status] bit
)

CREATE TABLE Users (
	Username varchar(20) PRIMARY KEY,
	Fullname nvarchar(50) NOT NULL,
	[Password] varchar(12) NOT NULL,
	RoleID varchar(3) NOT NULL,
	FOREIGN KEY (RoleID) REFERENCES Roles (RoleID),
	Email varchar(50),
	[Status] bit
)

CREATE TABLE Category (
	CategoryID varchar(12) PRIMARY KEY,
	CategoryName varchar(50) NOT NULL,
	[Status] bit
)

CREATE TABLE Product (
	ProductID varchar(12) PRIMARY KEY,
	ProductName nvarchar(50) NOT NULL,
	ProductImage nvarchar(400),
	Price decimal(18, 2) NOT NULL,
	Quantity int,
	CategoryID varchar(12) NOT NULL,
	FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID),
	[Status] bit
)

CREATE TABLE Orders (
	OrderID int IDENTITY(1, 1) PRIMARY KEY,
	OrderDate date,
	[Total] decimal(18, 2),
	Username varchar(20) NOT NULL,
	FOREIGN KEY (Username) REFERENCES Users (Username),
	[Status] bit
)

CREATE TABLE OrderDetail (
	OrderDetailID int PRIMARY KEY IDENTITY(1, 1),
	Price decimal(18, 2) NOT NULL,
	Quantity int,
	OrderID int NOT NULL,
	ProductID varchar(12) NOT NULL,
	FOREIGN KEY (OrderID) REFERENCES Orders (OrderID),
	FOREIGN KEY (ProductID) REFERENCES Product (ProductID),
	[Status] bit
)
--------------------------------------------------------------------------------------------------------
INSERT INTO Roles (RoleID, Rolename, [Status])
VALUES ('US', 'User', 1)
INSERT INTO Roles (RoleID, Rolename, [Status])
VALUES ('ADP', 'Admin Product', 1)
INSERT INTO Roles (RoleID, Rolename, [Status])
VALUES ('ADU', 'Admin User', 1)

INSERT INTO Users (Username, Fullname, [Password], RoleID, Email, [Status])
VALUES ('admin1', 'Admin 1', 'admin1', 'ADU', NULL, 1)
INSERT INTO Users (Username, Fullname, [Password], RoleID, Email, [Status])
VALUES ('admin2', 'Admin 2', 'admin2', 'ADP', NULL, 1)
INSERT INTO Users (Username, Fullname, [Password], RoleID, Email, [Status])
VALUES ('longvu', N'Vũ Long', '123', 'US', 'vulongbinhduong@gmail.com', 1)
INSERT INTO Users (Username, Fullname, [Password], RoleID, Email, [Status])
VALUES ('long', 'Long', '1', 'US', 'longvse171024@fpt.edu.vn', 1)

INSERT INTO Category (CategoryID, CategoryName, [Status])
VALUES ('STA', 'Stationery', 1)
INSERT INTO Category (CategoryID, CategoryName, [Status])
VALUES ('FB', 'Food and Beverage', 1)

INSERT INTO Product (ProductID, ProductName, ProductImage, Price, Quantity, CategoryID, [Status])
VALUES ('P001', N'Bút chì gỗ Staedtler 134-HB', 'https://cdn0.fahasa.com/media/catalog/product/4/0/4007817152416.jpg', 4500.00, 20, 'STA', 1)
INSERT INTO Product (ProductID, ProductName, ProductImage, Price, Quantity, CategoryID, [Status])
VALUES ('P002', N'Bút chì gỗ 2B For Exam Artline', 'https://cdn0.fahasa.com/media/catalog/product/4/9/4974052852732_2.jpg', 12600.00, 10, 'STA', 1)
INSERT INTO Product (ProductID, ProductName, ProductImage, Price, Quantity, CategoryID, [Status])
VALUES ('P003', N'Tẩy chì học sinh Staedtler 526E40', 'https://cdn0.fahasa.com/media/catalog/product/4/0/4007817528167_1_.jpg', 4500.00, 15, 'STA', 1)
INSERT INTO Product (ProductID, ProductName, ProductImage, Price, Quantity, CategoryID, [Status])
VALUES ('P004', N'Gôm Pentel ZEH-10', 'https://cdn0.fahasa.com/media/catalog/product/i/m/image_195509_1_41782.jpg', 20000.00, 5, 'STA', 1)
INSERT INTO Product (ProductID, ProductName, ProductImage, Price, Quantity, CategoryID, [Status])
VALUES ('P005', N'Coca-cola Zero Sugar', 'https://product.hstatic.net/200000122283/product/005994541efb4efc006e5873438_0e35f4eefbb2460f8020ca6fe1650304_1024x1024_b1e30629ac854aeea36d7cd65571ae34_master.jpg', 11000.00, 10, 'FB', 1)
INSERT INTO Product (ProductID, ProductName, ProductImage, Price, Quantity, CategoryID, [Status])
VALUES ('P006', N'Coca-cola Traditional', 'https://www.coca-cola.com/content/dam/onexp/vn/home-image/coca-cola/Coca-Cola_OT%20320ml_VN-EX_Desktop.png', 8000, 10, 'FB', 1)
INSERT INTO Product (ProductID, ProductName, ProductImage, Price, Quantity, CategoryID, [Status])
VALUES ('P007', N'Sprite hương chanh', 'https://tea-3.lozi.vn/v1/ship/resized/losupply-quan-tan-phu-quan-tan-phu-ho-chi-minh-1618467447167540212-nuoc-ngot-sprite-huong-chanh-320mlx24-lon-0-1678434554?w=480&type=o', 8500, 10, 'FB', 1)
