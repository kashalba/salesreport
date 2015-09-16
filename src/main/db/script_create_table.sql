CREATE TABLE Product
(
id int NOT NULL,
NAME varchar(25) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE SalesEntry
(
SalesId int NOT NULL,
DateofSale timestamp NOT NULL,
ProductID int NOT NULL,
SaleAmount int NOT NULL,
PRIMARY KEY (SalesId),
FOREIGN KEY (ProductID) REFERENCES Product(id)
);