INSERT INTO users(username,first_name,last_name,email,role,created_at,updated_at)
VALUES
    ('user1','Irena', 'Schmidt', 'irena.schmidt@gmail.com','ADMIN', '2023-09-01 13:17:40', '2023-09-01 13:17:40'),
    ('user2','Anella', 'Baumann', 'anella.baumann@gmail.com','ADMIN', '2023-08-01 10:57:40', '2023-08-01 10:57:40'),
    ('user3','Alice', 'Suss', 'alice.suss@gmail.com','USER', '2023-09-02 13:00:40', '2023-09-02 13:00:40'),
    ('user4','Anabel', 'Gwerder', 'anabel.gwerder@gmail.com','USER', '2023-07-01 18:17:40', '2023-07-01 18:17:40'),
    ('user5','Aline', 'Gunn', 'aline.gunn@gmail.com','USER', '2023-08-10 10:08:30', '2023-08-10 10:08:30'),
    ('user6','Martin', 'Jelli', 'martin.jelli@gmail.com','USER', '2023-05-05 07:00:12', '2023-05-05 07:00:12'),
    ('user7','Michael', 'Bachmann', 'michael.bachmann@gmail.com','USER', '2023-09-01 15:17:40', '2023-09-01 15:17:40'),
    ('user8','Kristian', 'Mueller', 'kristian.mueller@gmail.com','USER', '2023-08-01 10:44:40', '2023-08-01 10:44:40'),
    ('user9','Evelin', 'Giesler', 'evelin.giesler@gmail.com','USER', '2023-04-02 13:00:40', '2023-04-02 13:00:40'),
    ('user10','Flavio', 'Bashnagel', 'flavio.bashnagel@gmail.com','USER', '2023-02-01 12:17:40', '2023-02-01 12:17:40'),
    ('user11','Peter', 'Sepp', 'peter.sepp@gmail.com','USER', '2023-08-12 10:08:32', '2023-08-12 10:08:32'),
    ('user12','Andrea', 'Beeler', 'andrea.beeler@gmail.com','USER', '2023-05-05 20:00:12', '2023-05-05 20:00:12');

INSERT INTO products(seller_id,name,price,description,created_at,updated_at)
VALUES
    (1,'Notebook ACER 14'' 256 GB SSD', 579.95, 'Betriebssystem: Windows 11, Prozessor: Intel Celeron, N4500, Bildschirmauflösung: Full HD','2023-09-01 13:17:40', '2023-09-01 13:17:40'),
    (2,'Notebook ASUS 15.6'' 256 GB SSD', 699.95, 'Betriebssystem: Windows 11, Prozessor: Intel® Core™ i3-1215U, Bildschirmauflösung: FHD+', '2023-08-01 10:57:40', '2023-08-01 10:57:40'),
    (3,'Notebook APPLE 13'' 128 GB SSD', 699.95, 'Betriebssystem: MacOS 12, Prozessor: Core i5, Bildschirmauflösung: Full HD','2023-09-02 13:00:40', '2023-09-02 13:00:40'),
    (4,'Computer MEDION',499.95,'Betriebssystem: Windows 11, Prozessor: Intel® Core™ i5, Bildschirmauflösung: Keinerle','2023-07-01 18:17:40', '2023-07-01 18:17:40'),
    (4,'Computer POLAROID',499.95,'Betriebssystem: Windows 10, Prozessor: Intel Celeron, Bildschirmauflösung: Full HD','2023-08-10 10:08:30', '2023-08-10 10:08:30'),
    (2,'Monitor SAMSUNG 24''/60.96 cm',169.95,'Modell: LS24C310EAUXEN, Bildschirmauflösung: Full HD, Bildschirmauflösung in Pixel:1920x1080','2023-09-01 13:17:40', '2023-09-01 13:17:40'),
    (1,'Monitor ACER 27''/69 cm',159.95,'Modell: R272, Bildschirmauflösung: Full HD, Bildschirmauflösung in Pixel:1920x1080','2023-09-01 15:17:40', '2023-09-01 15:17:40'),
    (4,'Monitor HP 21.5''/54.6 cm',169.95,'Modell: M22f, Bildschirmauflösung: FHD+, Bildschirmauflösung in Pixel:1920x1080','2023-09-01 15:17:40', '2023-09-01 15:17:40'),
    (4,'Tablet TCL TAB 10L WIFI 10.1''/25.65 cm',159.95,'Betriebssystem: Android, Modell: TAB 10L WIFI,  Speicherkapazität (GB): 32', '2023-08-12 10:08:32', '2023-08-12 10:08:32'),
    (2,'Tablet SAMSUNG Galaxy Tab A8 10.5''/26.67 cm',259.95,'Betriebssystem: Android, Modell: Galaxy Tab A8, Speicherkapazität (GB): 32','2023-05-05 07:00:12', '2023-05-05 07:00:12'),
    (3,'Tablet APPLE iPad 7. 10.2''/25.9 cm',319.95,'Betriebssystem: IOS, Modell: iPad 7. GEN (2019, Speicherkapazität (GB): 128','2023-05-05 20:00:12', '2023-05-05 20:00:12');
