INSERT INTO users(username,first_name,last_name,password,email,role,created_at,updated_at)
VALUES
    ('user1','Irena', 'Schmidt','$2a$04$vT/wEEy0MZ3u0XBzzmSk5.COGoQdejXE9jpKwveT57I1OoTuAORV.', 'irena.schmidt@gmail.com','ADMIN', '2023-09-01 13:17:40', '2023-09-01 13:17:40'),
    ('user2','Anella', 'Baumann','$2a$04$vT/wEEy0MZ3u0XBzzmSk5.COGoQdejXE9jpKwveT57I1OoTuAORV.', 'anella.baumann@gmail.com','USER', '2023-08-01 10:57:40', '2023-08-01 10:57:40'),
    ('user3','Alice', 'Suss', '$2a$04$vT/wEEy0MZ3u0XBzzmSk5.COGoQdejXE9jpKwveT57I1OoTuAORV.','alice.suss@gmail.com','USER', '2023-09-02 13:00:40', '2023-09-02 13:00:40');

INSERT INTO products(seller_id,name,price,description,created_at,updated_at)
VALUES
    (1,'Notebook ACER 14'' 256 GB SSD', 579.95, 'Betriebssystem: Windows 11, Prozessor: Intel Celeron, N4500, Bildschirmauflösung: Full HD','2023-09-01 13:17:40', '2023-09-01 13:17:40'),
    (2,'Notebook ASUS 15.6'' 256 GB SSD', 699.95, 'Betriebssystem: Windows 11, Prozessor: Intel® Core™ i3-1215U, Bildschirmauflösung: FHD+', '2023-08-01 10:57:40', '2023-08-01 10:57:40'),
    (3,'Notebook APPLE 13'' 128 GB SSD', 699.95, 'Betriebssystem: MacOS 12, Prozessor: Core i5, Bildschirmauflösung: Full HD','2023-09-02 13:00:40', '2023-09-02 13:00:40'),
    (3,'Computer MEDION',499.95,'Betriebssystem: Windows 11, Prozessor: Intel® Core™ i5, Bildschirmauflösung: Keinerle','2023-07-01 18:17:40', '2023-07-01 18:17:40');

INSERT INTO carts(CLIENT_ID, STATUS, CREATED_AT, UPDATED_AT)
VALUES
    (2, 'ACTIVE', '2023-08-01 10:57:40', '2023-08-01 10:57:40');