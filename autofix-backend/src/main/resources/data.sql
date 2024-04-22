
INSERT INTO cars (plate, bodywork, brand, engine, mileage, model, seats, year) VALUES
    ('ABCD12', 'suv', 'MarcaA', 'diesel', 12000, 'ModeloX', 5, 2021),
    ('EFGH34', 'sedan', 'MarcaB', 'gas', 23000, 'ModeloY', 5, 2019),
    ('IJKL56', 'hatchback', 'MarcaC', 'hybrid', 45000, 'ModeloZ', 4, 2018),
    ('MNOP78', 'van', 'MarcaD', 'electric', 32000, 'ModeloW', 7, 2020),
    ('QRST90', 'suv', 'MarcaE', 'diesel', 21000, 'ModeloV', 5, 2022),
    ('UVWX12', 'sedan', 'MarcaF', 'gas', 10000, 'ModeloU', 4, 2023),
    ('YZAB34', 'hatchback', 'MarcaG', 'hybrid', 5000, 'ModeloS', 4, 2024)
    ON CONFLICT (plate)
    DO UPDATE SET
    bodywork = EXCLUDED.bodywork,
    brand = EXCLUDED.brand,
    engine = EXCLUDED.engine,
    mileage = EXCLUDED.mileage,
    model = EXCLUDED.model,
    seats = EXCLUDED.seats,
    year = EXCLUDED.year;


TRUNCATE TABLE repairs RESTART IDENTITY;
INSERT INTO repairs (plate, bodywork, engine, brand, repair_type, checkin_date, finish_date, checkout_date, surch_carage, surch_mileage, disc_reg_client, disc_bonus, total_amount, mileage, repair_price, disc_mon_thu, surch_delay,iva) VALUES
                                                                                                                                                                                                                                          ('ABCD12', 'suv', 'diesel', 'MarcaA', 1, '2024-04-10T08:00:00', '2024-04-15T18:00:00', '2024-04-16T09:00:00', 0.05, 0.05, 0.25, 0.25, 500, 12100, 150, 0.1, 0.1,0),
                                                                                                                                                                                                                                          ('EFGH34', 'sedan', 'gas', 'MarcaB', 3, '2024-04-11T09:00:00', '2024-04-12T17:00:00', '2024-04-13T10:00:00', 0.075, 0.075, 0.15, 0.15, 700, 23150, 120, 0.15, 0.05,0),
                                                                                                                                                                                                                                          ('IJKL56', 'hatchback', 'hybrid', 'MarcaC', 5, '2024-04-13T08:30:00', '2024-04-19T16:30:00', '2024-04-20T11:00:00', 0.1, 0.1, 0.5, 0.5, 1100, 45120, 180, 0.2, 0.2,0),
                                                                                                                                                                                                                                          ('MNOP78', 'van', 'electric', 'MarcaD', 7, '2024-04-14T10:00:00', '2024-04-20T20:00:00', '2024-04-21T08:30:00', 0.125, 0.125, 0.3, 0.3, 900, 32250, 160, 0.25, 0.15,0),
                                                                                                                                                                                                                                          ('QRST90', 'suv', 'diesel', 'MarcaE', 2, '2024-04-15T11:15:00', '2024-04-18T14:00:00', '2024-04-19T09:45:00', 0.15, 0.15, 0.75, 0.75, 750, 21150, 200, 0.3, 0.25,0),
                                                                                                                                                                                                                                          ('UVWX12', 'sedan', 'gas', 'MarcaF', 4, '2024-04-16T07:45:00', '2024-04-22T18:15:00', '2024-04-23T10:00:00', 0, 0, 0, 0, 450, 10100, 130, 0, 0,0),
                                                                                                                                                                                                                                          ('YZAB34', 'hatchback', 'hybrid', 'MarcaG', 6, '2024-04-17T09:00:00', '2024-04-23T17:00:00', '2024-04-24T10:00:00', 0.06, 0.06, 0.1, 0.1, 620, 5200, 190, 0.05, 0.05,0),
                                                                                                                                                                                                                                          ('ABCD12', 'suv', 'diesel', 'MarcaA', 8, '2024-04-18T08:00:00', '2024-04-24T18:00:00', '2024-04-25T09:00:00', 0.025, 0.025, 0.125, 0.125, 300, 12300, 175, 0.1, 0.1,0),
                                                                                                                                                                                                                                          ('EFGH34', 'sedan', 'gas', 'MarcaB', 10, '2024-04-19T09:00:00', '2024-04-25T17:00:00', '2024-04-26T10:00:00', 0.0375, 0.0375, 0.175, 0.175, 500, 23450, 145, 0.15, 0.1,0),
                                                                                                                                                                                                                                          ('IJKL56', 'hatchback', 'hybrid', 'MarcaC', 11, '2024-04-20T08:30:00', '2024-04-26T16:30:00', '2024-04-27T11:00:00', 0.04, 0.04, 0.2, 0.2, 600, 45300, 110, 0.2, 0.15,0);


TRUNCATE TABLE prices RESTART IDENTITY;

INSERT INTO prices (repair_type, engine, price) VALUES
                                                    (1, 'gas', 120),
                                                    (2, 'gas', 130),
                                                    (3, 'gas', 350),
                                                    (4, 'gas', 210),
                                                    (5, 'gas', 150),
                                                    (6, 'gas', 100),
                                                    (7, 'gas', 100),
                                                    (8, 'gas', 180),
                                                    (9, 'gas', 150),
                                                    (10, 'gas', 130),
                                                    (11, 'gas', 80),
                                                    (1, 'diesel', 120),
                                                    (2, 'diesel', 130),
                                                    (3, 'diesel', 450),
                                                    (4, 'diesel', 210),
                                                    (5, 'diesel', 150),
                                                    (6, 'diesel', 120),
                                                    (7, 'diesel', 100),
                                                    (8, 'diesel', 180),
                                                    (9, 'diesel', 150),
                                                    (10, 'diesel', 140),
                                                    (11, 'diesel', 80),
                                                    (1, 'hybrid', 180),
                                                    (2, 'hybrid', 190),
                                                    (3, 'hybrid', 700),
                                                    (4, 'hybrid', 300),
                                                    (5, 'hybrid', 200),
                                                    (6, 'hybrid', 450),
                                                    (7, 'hybrid', 100),
                                                    (8, 'hybrid', 210),
                                                    (9, 'hybrid', 180),
                                                    (10, 'hybrid', 220),
                                                    (11, 'hybrid', 80),
                                                    (1, 'electric', 220),
                                                    (2, 'electric', 230),
                                                    (3, 'electric', 800),
                                                    (4, 'electric', 300),
                                                    (5, 'electric', 250),
                                                    (6, 'electric', 0),
                                                    (7, 'electric', 100),
                                                    (8, 'electric', 250),
                                                    (9, 'electric', 180),
                                                    (10, 'electric', 0),
                                                    (11, 'electric', 80);


TRUNCATE TABLE discount_reg_client RESTART IDENTITY;

INSERT INTO discount_reg_client (category, engine, discount) VALUES
                                                                 ('A', 'gas', 0.05),
                                                                 ('B', 'gas', 0.10),
                                                                 ('C', 'gas', 0.15),
                                                                 ('D', 'gas', 0.20),
                                                                 ('A', 'diesel', 0.07),
                                                                 ('B', 'diesel', 0.12),
                                                                 ('C', 'diesel', 0.17),
                                                                 ('D', 'diesel', 0.22),
                                                                 ('A', 'hybrid', 0.10),
                                                                 ('B', 'hybrid', 0.15),
                                                                 ('C', 'hybrid', 0.20),
                                                                 ('D', 'hybrid', 0.25),
                                                                 ('A', 'electric', 0.08),
                                                                 ('B', 'electric', 0.13),
                                                                 ('C', 'electric', 0.18),
                                                                 ('D', 'electric', 0.23);

TRUNCATE TABLE surcharge_mileage RESTART IDENTITY;

INSERT INTO surcharge_mileage (category, bodywork, surcharge) VALUES
                                                                  ('A', 'sedan', 0),
                                                                  ('B', 'sedan', 0.03),
                                                                  ('C', 'sedan', 0.07),
                                                                  ('D', 'sedan', 0.12),
                                                                  ('E', 'sedan', 0.20),
                                                                  ('A', 'hatchback', 0),
                                                                  ('B', 'hatchback', 0.03),
                                                                  ('C', 'hatchback', 0.07),
                                                                  ('D', 'hatchback', 0.12),
                                                                  ('E', 'hatchback', 0.20),
                                                                  ('A', 'suv', 0),
                                                                  ('B', 'suv', 0.05),
                                                                  ('C', 'suv', 0.09),
                                                                  ('D', 'suv', 0.12),
                                                                  ('E', 'suv', 0.20),
                                                                  ('A', 'pickup', 0),
                                                                  ('B', 'pickup', 0.05),
                                                                  ('C', 'pickup', 0.09),
                                                                  ('D', 'pickup', 0.12),
                                                                  ('E', 'pickup', 0.20),
                                                                  ('A', 'van', 0),
                                                                  ('B', 'van', 0.05),
                                                                  ('C', 'van', 0.09),
                                                                  ('D', 'van', 0.12),
                                                                  ('E', 'van', 0.20);

TRUNCATE TABLE surcharge_carage RESTART IDENTITY;
INSERT INTO surcharge_carage (category, bodywork, surcharge) VALUES
                                                                 ('A', 'sedan', 0),
                                                                 ('B', 'sedan', 0.05),
                                                                 ('C', 'sedan', 0.09),
                                                                 ('D', 'sedan', 0.15),
                                                                 ('A', 'hatchback', 0),
                                                                 ('B', 'hatchback', 0.05),
                                                                 ('C', 'hatchback', 0.09),
                                                                 ('D', 'hatchback', 0.15),
                                                                 ('A', 'suv', 0),
                                                                 ('B', 'suv', 0.07),
                                                                 ('C', 'suv', 0.11),
                                                                 ('D', 'suv', 0.2),
                                                                 ('A', 'pickup', 0),
                                                                 ('B', 'pickup', 0.07),
                                                                 ('C', 'pickup', 0.11),
                                                                 ('D', 'pickup', 0.2),
                                                                 ('A', 'van', 0),
                                                                 ('B', 'van', 0.07),
                                                                 ('C', 'van', 0.11),
                                                                 ('D', 'van', 0.2);

TRUNCATE TABLE discount_bonus RESTART IDENTITY;
INSERT INTO discount_bonus (brand,bonus,stock) VALUES
                                                  ('MarcaA', 22,4),
                                                  ('MarcaB', 15,6);
