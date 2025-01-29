-- Create the database (run this separately before the rest of the script)
CREATE DATABASE "FootprintDB";

-- Connect to the database (this is done outside the script, e.g., via psql or your client)

-- Drop tables if they exist
DROP TABLE IF EXISTS "Answermodel";
DROP TABLE IF EXISTS "Result";
DROP TABLE IF EXISTS "Transportmodel";
DROP TABLE IF EXISTS "UserProperty";

-- Create UserProperty table
CREATE TABLE "UserProperty" (
                                "User_id" SERIAL PRIMARY KEY,
                                "Username" VARCHAR(255) NOT NULL,
                                "Password" VARCHAR(255) NOT NULL,
                                "Email" VARCHAR(255) NOT NULL,
                                "Firstname" VARCHAR(255) NOT NULL,
                                "Lastname" VARCHAR(255) NOT NULL
);

-- Create Transportmodel table
CREATE TABLE "Transportmodel" (
                                  "T_id" SERIAL PRIMARY KEY,
                                  "Transportname" VARCHAR(255) NOT NULL,
                                  "Fuel_factor" INTEGER NOT NULL,
                                  "Emission_factor" INTEGER NOT NULL
);

-- Create Result table
CREATE TABLE "Result" (
                          "Id" SERIAL PRIMARY KEY,
                          "CO_PER_DAY" DECIMAL(10,2) NOT NULL,
                          "CO_PER_MONTH" DECIMAL(10,2) NOT NULL,
                          "CO_PER_YEAR" DECIMAL(10,2) NOT NULL
);

-- Create Answermodel table
CREATE TABLE "Answermodel" (
                               "Answer_id" SERIAL PRIMARY KEY,
                               "Date" DATE NOT NULL,
                               "Time" TIME NOT NULL,
                               "Transportmethod" INTEGER REFERENCES "Transportmodel"("T_id"),
                               "Distance" INTEGER NOT NULL,
                               "Passenger_count" INTEGER NOT NULL,
                               "User" INTEGER REFERENCES "UserProperty"("User_id")
);

-- Insert Data For UserProperty
INSERT INTO "UserProperty" ("Username", "Password", "Email", "Firstname", "Lastname") VALUES
                                                                                          ('hbedell0', 'mY8<md7|quNlS', 'hbedell0@blogspot.com', 'Hill', 'Bedell'),
                                                                                          ('sflanaghan1', 'pW6#H7,(_<AfsJ', 'sflanaghan1@zimbio.com', 'Stoddard', 'Flanaghan'),
                                                                                          ('ktinson2', 'dU3*b54sS!Ce', 'ktinson2@tamu.edu', 'Krystalle', 'Tinson'),
                                                                                          ('pridge3', 'xV4)JKhAJo*aN', 'pridge3@mediafire.com', 'Phylis', 'Ridge'),
                                                                                          ('gemeline4', 'zN1>Pdy(Fa+H', 'gemeline4@samsung.com', 'Glyn', 'Emeline'),
                                                                                          ('mcurdell5', 'pI8|Hho3!COv~/', 'mcurdell5@theglobeandmail.com', 'Morissa', 'Curdell'),
                                                                                          ('rlabatie6', 'oN2/8F~c', 'rlabatie6@bloglovin.com', 'Roxine', 'La Batie'),
                                                                                          ('jquant7', 'mH2''TtA>I"c', 'jquant7@washington.edu', 'Jase', 'Quant'),
                                                                                          ('pgrisenthwaite8', 'zR9/tsN.9HW(Q}e', 'pgrisenthwaite8@usatoday.com', 'Pietrek', 'Grisenthwaite'),
                                                                                          ('rwason9', 'sJ0(?+EA9(l5', 'rwason9@who.int', 'Regine', 'Wason');

-- Insert Data For Transportmodel
INSERT INTO "Transportmodel" ("Transportname", "Fuel_factor", "Emission_factor") VALUES
                                                                                     ('Car', 1, 1),
                                                                                     ('Plane', 2, 2),
                                                                                     ('Train', 3, 3),
                                                                                     ('Ship', 4, 4);

-- Insert Data For Result
INSERT INTO "Result" ("CO_PER_DAY", "CO_PER_MONTH", "CO_PER_YEAR") VALUES
                                                                       (5.84, 30.32, 219.41),
                                                                       (2.87, 20.3, 595.77),
                                                                       (2.41, 3.15, 616.09),
                                                                       (3.87, 54.8, 35.4),
                                                                       (5.46, 33.68, 263.11),
                                                                       (7.37, 63.06, 368.94),
                                                                       (4.77, 33.2, 925.38),
                                                                       (3.19, 8.28, 572.77),
                                                                       (5.08, 38.19, 980.48),
                                                                       (3.67, 37.72, 930.15),
                                                                       (1.17, 89.85, 575.29),
                                                                       (3.08, 58.44, 513.09),
                                                                       (4.66, 70.44, 856.01),
                                                                       (6.15, 31.71, 139.49),
                                                                       (2.03, 64.5, 742.84),
                                                                       (6.3, 51.92, 654.81),
                                                                       (3.43, 81.84, 780.66),
                                                                       (7.27, 72.34, 475.16),
                                                                       (2.12, 2.22, 212.15),
                                                                       (6.61, 39.77, 863.08);

-- Insert Data For Answermodel
INSERT INTO "Answermodel" ("Date", "Time", "Transportmethod", "Distance", "Passenger_count", "User") VALUES
                                                                                                         ('2024-05-15', '06:07:00', 4, 5058, 12, 9),
                                                                                                         ('2025-01-09', '19:01:00', 2, 8163, 1, 2),
                                                                                                         ('2024-06-26', '16:27:00', 2, 486, 13, 1),
                                                                                                         ('2024-07-31', '01:13:00', 1, 7484, 10, 4),
                                                                                                         ('2024-05-25', '07:34:00', 2, 663, 14, 6),
                                                                                                         ('2024-10-04', '04:12:00', 2, 1925, 15, 2),
                                                                                                         ('2025-01-13', '02:44:00', 3, 8563, 7, 9),
                                                                                                         ('2024-12-20', '00:02:00', 2, 4426, 8, 10),
                                                                                                         ('2025-01-13', '14:38:00', 1, 6117, 19, 3),
                                                                                                         ('2024-04-05', '03:51:00', 4, 8246, 17, 3),
                                                                                                         ('2024-06-25', '13:15:00', 3, 1348, 9, 10),
                                                                                                         ('2024-07-26', '20:12:00', 4, 1844, 1, 1),
                                                                                                         ('2024-02-19', '03:31:00', 3, 3000, 3, 5),
                                                                                                         ('2024-08-07', '23:53:00', 1, 932, 10, 2),
                                                                                                         ('2024-11-04', '14:47:00', 1, 2714, 11, 9),
                                                                                                         ('2024-04-20', '02:54:00', 4, 1577, 13, 6),
                                                                                                         ('2024-12-04', '20:53:00', 4, 2183, 17, 2),
                                                                                                         ('2024-09-13', '21:41:00', 2, 6601, 7, 8),
                                                                                                         ('2024-06-07', '04:45:00', 4, 4484, 10, 10),
                                                                                                         ('2024-06-10', '14:20:00', 1, 1701, 12, 5);