INSERT INTO product (description,barcode,sequential,id) VALUES ('soups','1758253','12E277',1);
INSERT INTO product (description,barcode,sequential,id) VALUES ('pies','9367148','B471B1',2);
INSERT INTO product (description,barcode,sequential,id) VALUES ('cereals','9656495','662720',3);
INSERT INTO product (description,barcode,sequential,id) VALUES ('salads','6913225','463DCA',4);
INSERT INTO product (description,barcode,sequential,id) VALUES ('torta','6328636','6D7FD5',5);
INSERT INTO product (description,barcode,sequential,id) VALUES ('stews','5685439','17AF98',6);
INSERT INTO product (description,barcode,sequential,id) VALUES ('soupa','7549891','CEB653',7);
INSERT INTO product (description,barcode,sequential,id) VALUES ('salada','9723213','AE2B8B',8);
INSERT INTO product(description,barcode,sequential,id) VALUES ('pasta','8353495','301071',9);
INSERT INTO product(description,barcode,sequential,id) VALUES ('noodles','5479747','86E3A1',10);

INSERT INTO Branch (id,location) VALUES (1,'Vanuatu');
INSERT INTO Branch (id,location) VALUES (2,'Barbados');
INSERT INTO Branch (id,location) VALUES (3,'Tonga');
INSERT INTO Branch (id,location) VALUES (4,'Greece');
INSERT INTO Branch (id,location) VALUES (5,'Honduras');
INSERT INTO Branch (id,location) VALUES (6,'Namibia');

INSERT INTO Stock (product_id,branch_id,quantity,price,id) VALUES (1,4,72,71.42,1);
INSERT INTO Stock (product_id,branch_id,quantity,price,id) VALUES (2,6,59,14.40,2);
INSERT INTO Stock (product_id,branch_id,quantity,price,id) VALUES (3,3,84,91.27,3);
INSERT INTO Stock (product_id,branch_id,quantity,price,id) VALUES (4,5,43,43.81,4);
INSERT INTO Stock (product_id,branch_id,quantity,price,id) VALUES (5,6,28,97.69,5);
INSERT INTO Stock (product_id,branch_id,quantity,price,id) VALUES (6,5,41,26.13,6);
INSERT INTO Stock (product_id,branch_id,quantity,price,id) VALUES (7,1,50,39.26,7);
INSERT INTO Stock (product_id,branch_id,quantity,price,id) VALUES (8,5,46,48.21,8);
INSERT INTO Stock (product_id,branch_id,quantity,price,id) VALUES (9,4,91,26.30,9);
INSERT INTO Stock (product_id,branch_id,quantity,price,id) VALUES (10,1,31,28.05,10);

INSERT INTO client(name,identification,person_Type,id) VALUES ('Davi','60727289365','NATURAL',1);
INSERT INTO employee(name,identification,OCCUPATION_Type,id) VALUES ('Jordan S.','4547864','CASHIER',2);

INSERT INTO order_table (id,branch_id,client_id,employee_id,order_type,obs_Delivery,payment,total) VALUES (1,1,1,2,'OUTWAY','','CARD',0.0);
INSERT INTO item (id,order_id,stock_id,item_status, quantity) VALUES (1,1,3,'CANCELED',4);
