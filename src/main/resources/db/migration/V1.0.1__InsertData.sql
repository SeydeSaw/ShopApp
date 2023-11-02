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
    (3, 'Notebook APPLE 13'' 128 GB SSD', 699.95, 'Betriebssystem: MacOS 12, Prozessor: Core i5, Bildschirmauflösung: Full HD','2023-09-02 13:00:40', '2023-09-02 13:00:40');
