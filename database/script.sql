USE MASTER
GO
DROP DATABASE QuanLyLinhKien
GO

CREATE DATABASE QuanLyLinhKien
GO

USE QuanLyLinhKien
GO

CREATE TABLE Employee(
	EmployeeID INT IDENTITY(1, 1) PRIMARY KEY(EmployeeID), -- ID Nhân viên
	FullName NVARCHAR(70) NOT NULL, -- Họ và tên nhân viên
	BirthDate DATE, -- Ngày sinh
	Address NVARCHAR(100), -- Địa chỉ
	Phone VARCHAR(15) NOT NULL, -- Số điện thoại
	Status INT DEFAULT 1, -- Trạng thái: 1 là còn làm, 0 là không còn làm
	UserName VARCHAR(50) NOT NULL, -- Tài khoản
	Password VARCHAR(50) NOT NULL, -- Mật khẩu
	RoleID INT NOT NULL -- ID quyền
)
GO

CREATE TABLE Role(
	RoleID INT IDENTITY(1, 1) PRIMARY KEY(RoleID), -- ID quyền
	RoleName NVARCHAR(20) NOT NULL, -- Tên quyền
	Description NVARCHAR(255), -- Mô tả quyền
)
GO

CREATE TABLE Category(
	CategoryID INT IDENTITY(1, 1) PRIMARY KEY(CategoryID), -- ID Loại
	CategoryName NVARCHAR(70) NOT NULL, -- Tên loại
	Description NVARCHAR(255), -- Mô tả loại
)
GO

CREATE TABLE Customer(
	CustomerID INT IDENTITY(1, 1) PRIMARY KEY(CustomerID), -- ID Khách Hàng
	FullName NVARCHAR(70) NOT NULL, -- Họ và tên Khách hàng
	Address NVARCHAR(100), -- Địa chỉ
	Phone VARCHAR(15) NOT NULL, -- Số điện thoại
	Status INT DEFAULT 1, -- Trạng thái: 1 là còn trong hệ thống, 0 là không còn trong hệ thống
)
GO

CREATE TABLE Product(
	ProductID INT IDENTITY(1, 1) PRIMARY KEY(ProductID), -- ID sản phẩm
	CategoryID INT NOT NULL, -- ID loại sản phẩm
	Name NVARCHAR(70), -- Tên sản phẩm
	Price MONEY DEFAULT 0, -- Giá
	Quantity INT DEFAULT 0, -- Số lượng tồn kho
	Status INT DEFAULT 1 -- Trạng thái: 1 là còn tháng, 0 là ngừng bán
)
GO

CREATE TABLE Vendor(
	VendorID INT IDENTITY(1, 1) PRIMARY KEY(VendorID), -- ID nhà cung cấp
	VendorName NVARCHAR(70), -- Tên nhà cung cấp
	Address NVARCHAR(100), -- Địa chỉ
	Phone VARCHAR(15) NOT NULL, -- Số điện thoại
	Fax VARCHAR(30) -- Số Fax
)
GO

CREATE TABLE SalesOrder(
	SalesOrderID INT IDENTITY(1, 1) PRIMARY KEY(SalesOrderID), -- ID hóa đơn
	EmployeeID INT NOT NULL, -- ID Khách hàng
	CustomerID INT NOT NULL, -- ID Nhân viên
	PromotionID INT, -- ID Khuyến mãi
	OrderDate DATE DEFAULT GETDATE(), -- Ngày lập: Mặc định là hôm nay
	OrderTime TIME DEFAULT CONVERT(TIME(0),GETDATE()), -- Giờ lập: Mặc định là ngay bây giờ
	TotalDue MONEY -- Tổng tiền = SUM(SubTotal)
)
GO

CREATE TABLE SalesOrderDetail(
	SalesOrderDetailID INT IDENTITY(1, 1),  -- ID Chi tiết hóa đơn
	SalesOrderID INT NOT NULL, -- ID hóa đơn 
	ProductID INT NOT NULL, -- ID Sản phẩm
	OrderQuantity INT DEFAULT 0, -- Số lượng đặt: mặc định là 0
	SubTotal MONEY DEFAULT 0 -- Tổng tiền đặt = OrderQuantity*Price
	PRIMARY KEY(SalesOrderDetailID, SalesOrderID, ProductID)
)
GO

CREATE TABLE PuschaseOrder(
	PuschaseOrderID INT IDENTITY(1, 1) PRIMARY KEY(PuschaseOrderID), -- ID đơn nhập hàng
	VendorID INT NOT NULL, -- ID nhà cung cấp
	EmployeeID INT NOT NULL, -- ID nhân viên
	OrderDate DATE DEFAULT GETDATE(), -- Ngày lập: Mặc định là hôm nay
	OrderTime TIME DEFAULT CONVERT(TIME(0),GETDATE()), -- Giờ lập: Mặc định là ngay bây giờ
	TotalDue MONEY -- Tổng tiền = SUM(SubTotal)
)
GO

CREATE TABLE PuschaseOrderDetail(
	PuschaseOrderDetailID INT IDENTITY(1, 1),  -- ID Chi tiết hóa đơn nhập
	PuschaseOrderID INT NOT NULL, -- ID hóa đơn nhập
	ProductID INT NOT NULL, -- ID sản phẩm
	OrderQuantity INT DEFAULT 0, -- Số lượng đặt: mặc định là 0
	SubTotal MONEY DEFAULT 0 -- Tổng tiền sản phẩm
	PRIMARY KEY(PuschaseOrderDetailID, PuschaseOrderID, ProductID)
)
GO

CREATE TABLE Promotion(
	PromotionID INT IDENTITY(1, 1) PRIMARY KEY(PromotionID),
	PromotionName NVARCHAR(100),
	PromotionPercent MONEY,
	StartDate DATE NOT NULL,
	EndDate DATE
)
GO

ALTER TABLE [dbo].[Employee]
ADD
FOREIGN KEY ([RoleID]) REFERENCES [dbo].[Role]([RoleID])
GO

ALTER TABLE [dbo].[Product]
ADD FOREIGN KEY([CategoryID]) REFERENCES [dbo].[Category]([CategoryID])
GO

ALTER TABLE [dbo].[SalesOrder]
ADD
FOREIGN KEY([EmployeeID]) REFERENCES [dbo].[Employee]([EmployeeID]),
FOREIGN KEY([CustomerID]) REFERENCES [dbo].[Customer]([CustomerID]),
FOREIGN KEY([PromotionID]) REFERENCES [dbo].[Promotion]([PromotionID])
GO

ALTER TABLE [dbo].[SalesOrderDetail]
ADD
FOREIGN KEY([SalesOrderID]) REFERENCES [dbo].[SalesOrder]([SalesOrderID]),
FOREIGN KEY([ProductID]) REFERENCES [dbo].[Product]([ProductID])
GO

ALTER TABLE [dbo].[PuschaseOrder]
ADD
FOREIGN KEY([VendorID]) REFERENCES [dbo].[Vendor]([VendorID]),
FOREIGN KEY([EmployeeID]) REFERENCES [dbo].[Employee]([EmployeeID])
GO

ALTER TABLE [dbo].[PuschaseOrderDetail]
ADD
FOREIGN KEY([PuschaseOrderID]) REFERENCES [dbo].[PuschaseOrder]([PuschaseOrderID]),
FOREIGN KEY([ProductID]) REFERENCES [dbo].[Product]([ProductID])
GO

USE [QuanLyLinhKien]
GO

-- INSERT KhachHang

INSERT INTO [dbo].[Customer]([FullName], [Address], [Phone], [Status])
VALUES(N'Lê Thị Thảo', N'982 Trường Trinh, Gò Vấp, Đà Lạt', '0891241231', 1)

INSERT INTO [dbo].[Customer]([FullName], [Address], [Phone], [Status])
VALUES(N'Trần Thanh Hi', N'312 Trường Giang, Gò Vấp, Hồ Chí Minh', '0831231235', 1)

INSERT INTO [dbo].[Customer]([FullName], [Address], [Phone], [Status])
VALUES(N'Nguyễn Thanh Tiến', N'42 Trường Sa, Quận 12, Hồ Chí Minh', '0938471521', 1)

INSERT INTO [dbo].[Customer]([FullName], [Address], [Phone], [Status])
VALUES(N'Nguyễn Thi Thanh Nhàn', N'124 Võ Duy Dương, Quận 11, Hồ Chí Minh', '0867713557', 1)

INSERT INTO [dbo].[Customer]([FullName], [Address], [Phone], [Status])
VALUES(N'Nguyễn Hữu Tài My', N'12 Nguyễn Văn Bảo, Quận 1, Hồ Chí Minh', '0867235122', 1)

INSERT INTO [dbo].[Customer]([FullName], [Address], [Phone], [Status])
VALUES(N'Lê Trần Trung Kiên', N'477 Phạm Văn Đồng, Thủ Đức, Hồ Chí Minh', '0697172224', 1)

INSERT INTO [dbo].[Customer]([FullName], [Address], [Phone], [Status])
VALUES(N'Cả Đời Vì Em', N'999 Đóa Hoa Hồng, Thủ Đức, Hồ Chí Minh', '0396172224', 1)

INSERT INTO [dbo].[Customer]([FullName], [Address], [Phone], [Status])
VALUES(N'Đoàn Văn Hậu', N'763 Huyền Thoại, Quận 8, Hồ Chí Minh', '0123412512', 1)
GO

-- INSERT KhuyenMai
INSERT INTO [dbo].[Promotion] ([PromotionName], [PromotionPercent], [StartDate], [EndDate])
VALUES(N'Khong co khuyen mai', 0, '2023-04-05', null)
INSERT INTO [dbo].[Promotion] ([PromotionName], [PromotionPercent], [StartDate], [EndDate])
VALUES(N'Black Friday', 10, '2023-04-05', '2023-05-04')
GO

-- INSERT PhanQuyen
INSERT INTO [dbo].[Role] ([RoleName], [Description])
VALUES(N'Admin', N'Admin') -- 1

INSERT INTO [dbo].[Role] ([RoleName], [Description])
VALUES(N'Nhân viên Bán hàng', N'Nhân viên bán hàng, xem phiếu bán hàng') -- 2

INSERT INTO [dbo].[Role] ([RoleName], [Description])
VALUES(N'Nhân viên Nhập hàng', N'Nhân viên nhập hàng vào kho, xem phiếu nhập hàng') -- 3

INSERT INTO [dbo].[Role] ([RoleName], [Description])
VALUES(N'Quản lý', N'Quản lý nhân viên và xem các đơn nhập hàng, bán hàng ') -- 4
GO

-- INSERT NhanVien
-- Admin
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- admin - 1
VALUES(N'Lê Đôn Chủng', '2003-08-01', N'398 Trường Sơn, Pleiku, Gia Lai', '0867713557', 1, 'donchung', '7c4a8d09ca3762af61e59520943dc26494f8941b', 1)

-- Bán hàng
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- ban hang - 2
VALUES(N'Trần Thi Thanh Tuyền', '2003-06-09', N'26, Ấp Phước Hòa, Xã Phước Hưng, Huyện An Phú, Tỉnh An Giang', '0396172224', 1, 'thanhtuyen', '7c4a8d09ca3762af61e59520943dc26494f8941b', 2)
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- ban hang - 3
VALUES(N'Ngô Thị Bảo Châu', '1996-06-27', N'29 Ngô Thiến, TP.Gia Định, Tỉnh Bạc Liêu', '0814524512', 1, 'baochau', '7c4a8d09ca3762af61e59520943dc26494f8941b', 2)
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- ban hang - 4
VALUES(N'Lê Thành Đạt', '1999-04-12', N'29 Ngô Thiến, TP.Gia Định, Tỉnh Bạc Liêu', '0123456789', 1, 'thanhdat', '7c4a8d09ca3762af61e59520943dc26494f8941b', 2)
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- ban hang - 5
VALUES(N'Nguyễn Nhường Em', '2000-01-12', N'293 Trần Thái Tổ, Nghệ Tĩnh, Nghệ An', '0120981246', 1, 'nhuongem', '7c4a8d09ca3762af61e59520943dc26494f8941b', 2)
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- ban hang - 6
VALUES(N'Thái Lê Na', '2000-01-13', N'293 Trần Thái Tổ, Nghệ Tĩnh, Nghệ An', '0481928512', 1, 'lena', '7c4a8d09ca3762af61e59520943dc26494f8941b', 2)
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- ban hang - 7
VALUES(N'Naruto', '2003-01-12', N'291 Làng Lá, Anime', '0987278987', 1, 'naruto', '7c4a8d09ca3762af61e59520943dc26494f8941b', 2)

-- Nhập hàng
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- nhap hang - 8
VALUES(N'Phan Thị Huỳnh Thư', '2003-01-01', N'12 Nguyễn Văn Bảo, Phường 4, Quận Gò Vấp, Hồ Chí Minh', '0914151252', 1, 'huynhthu', '7c4a8d09ca3762af61e59520943dc26494f8941b', 3)
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- nhap hang - 9
VALUES(N'Trần Văn Ơn', '1998-01-01', N'281 La La La, Phường 7, Quận 1, Hồ Chí Minh', '0192847123', 1, 'vanon', '7c4a8d09ca3762af61e59520943dc26494f8941b', 3)
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- nhap hang - 10
VALUES(N'Lê Thị Ni Na', '2001-05-29', N'412 Nguyễn An, Phường 4, Quận Gò Vấp, Hồ Chí Minh', '0987656823', 1, 'nina', '7c4a8d09ca3762af61e59520943dc26494f8941b', 3)
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- nhap hang - 11
VALUES(N'Nguyễn Văn Tiến', '2003-04-12', N'124 IaSao, Phường 2, Quận Word, Hồ Chí Minh', '0987656182', 1, 'vantien', '7c4a8d09ca3762af61e59520943dc26494f8941b', 3)

-- Quản lí
INSERT INTO [dbo].[Employee]([FullName], [BirthDate], [Address], [Phone], [Status], UserName, Password, RoleID) -- quan li - 12
VALUES(N'Nguyễn Minh Hải', '2003-02-02', N'12 Nguyễn Văn Bảo, Phường 4, Quận Gò Vấp, Hồ Chí Minh', '0914151451', 1,'minhhai', '7c4a8d09ca3762af61e59520943dc26494f8941b', 4)
GO

-- INSERT NhaCungCap
INSERT INTO [dbo].[Vendor] ([VendorName], [Address], [Phone], [Fax]) -- 1
VALUES('Asus',N'471 An Dương Vương, Đà Lạt','0391825123', '+84 (8) 3623 3818')
INSERT INTO [dbo].[Vendor] ([VendorName], [Address], [Phone], [Fax]) -- 2
VALUES('Gigabyte',N'918 Phạm Văn Đồng, Vũng Tàu','0381847191', '+84 (8) 1251 1516')
INSERT INTO [dbo].[Vendor] ([VendorName], [Address], [Phone], [Fax]) -- 3
VALUES('Intel',N'124 Văn Lang, Cà Mau','0485127971', '+84 (9) 1111 1111')
INSERT INTO [dbo].[Vendor] ([VendorName], [Address], [Phone], [Fax]) -- 4
VALUES('G.Skill',N'4124 Văn Lang, Cà Mau','0485127971', '+84 (9) 2222 2222')
GO

-- INSERT LoaiSanPham
INSERT INTO [dbo].[Category]([CategoryName] ,[Description]) -- 1
VALUES(N'Mainboard', N'Mainboard chính là bo mạch chủ, là một bảng mạch có tác dụng rất quan trọng đối với máy tính.') -- 1
INSERT INTO [dbo].[Category]([CategoryName] ,[Description])
VALUES(N'CPU', N'Central Processing Unit là bộ xử lý trung tâm hoặc bộ vi xử lý.') -- 2
INSERT INTO [dbo].[Category]([CategoryName] ,[Description])
VALUES(N'VGA', N'Card đồ họa, card màn hình.') -- 3
INSERT INTO [dbo].[Category]([CategoryName] ,[Description])
VALUES(N'RAM', N'Ram là bộ nhớ lưu trữ tạm thời trong máy tính.') -- 4
GO


-- INSERT SanPham
INSERT INTO [dbo].[Product] ([CategoryID],[Name], [Price], [Quantity], [Status]) -- 1
VALUES (1 , N'Mainboard Asus TUF Gaming B660M-Plus D4', 3999000 , 10 , 1)
INSERT INTO [dbo].[Product] ([CategoryID],[Name], [Price], [Quantity], [Status]) -- 2
VALUES (1 , N'Mainboard Asus ROG STRIX Z690-I Gaming wifi', 9499000 , 20 , 1)
INSERT INTO [dbo].[Product] ([CategoryID],[Name], [Price], [Quantity], [Status]) -- 3
VALUES (2 , N'CPU Intel Core i3-10100F', 1709100 , 15 , 1)
INSERT INTO [dbo].[Product] ([CategoryID],[Name], [Price], [Quantity], [Status]) -- 4
VALUES (2 , N'CPU Intel Core i5-10100F', 1909100 , 10 , 1)
INSERT INTO [dbo].[Product] ([CategoryID],[Name], [Price], [Quantity], [Status]) -- 5
VALUES (3 , N'Card đồ hoạ Asus DUAL RTX 3060', 9999000 , 10 , 1)
INSERT INTO [dbo].[Product] ([CategoryID],[Name], [Price], [Quantity], [Status]) -- 6
VALUES (3 , N'Card màn hình Asus TUF-GTX1660TI', 5499000 , 10 , 1)
INSERT INTO [dbo].[Product] ([CategoryID],[Name], [Price], [Quantity], [Status]) -- 7
VALUES (4 , N'RAM G.SKILL Trident 16G', 1999000 , 10 , 1)
INSERT INTO [dbo].[Product] ([CategoryID],[Name], [Price], [Quantity], [Status]) -- 8
VALUES (4 , N'RAM G.SKILL Trident Z RGB', 3599000 , 10 , 1)
INSERT INTO [dbo].[Product] ([CategoryID],[Name], [Price], [Quantity], [Status]) -- 9
VALUES (4 , N'RAM G.Skill 8GB DDR4 2800', 799000 , 10 , 1)
GO

-- Trigger trên bảng SalesOrderDetail

CREATE TRIGGER insertSalesOrderDetail
ON [dbo].[SalesOrderDetail]
AFTER INSERT
AS
	DECLARE @ProductID INT
	DECLARE @Qty INT, @Quantity INT
	SELECT @ProductID = ProductID, @Qty = OrderQuantity from inserted
	IF NOT EXISTS (SELECT *FROM Product WHERE ProductID = @ProductID)
		ROLLBACK

	UPDATE [dbo].[Product]
	SET Quantity = Quantity - @Qty
	WHERE ProductID = @productID
GO

-- Trigger trên bảng PurchaseOrderDetail
CREATE TRIGGER  insertPurchaseOrderDetail
ON [dbo].[PuschaseOrderDetail]
AFTER INSERT
AS
	DECLARE @ProductID INT
	DECLARE @Quantity INT
	SELECT @ProductID = ProductID, @Quantity = OrderQuantity from inserted
	if NOT EXISTS (SELECT * FROM Product WHERE ProductID = @ProductID)
		ROLLBACK

	UPDATE [dbo].[Product]
	SET Quantity = Quantity + @Quantity
	WHERE ProductID = @ProductID
GO
