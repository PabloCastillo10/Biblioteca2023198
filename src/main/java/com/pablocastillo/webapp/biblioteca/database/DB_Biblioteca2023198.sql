USE DBBibliotecaIN5CM;





--- CATEGORIA ---
INSERT INTO Categorias (nombre_Categoria) VALUES ('Ficción');
INSERT INTO Categorias (nombre_Categoria) VALUES ('Ciencia');
INSERT INTO Categorias (nombre_Categoria) VALUES ('Historia');
INSERT INTO Categorias (nombre_Categoria) VALUES ('Matemáticas');
INSERT INTO Categorias (nombre_Categoria) VALUES ('Filosofía');
INSERT INTO Categorias (nombre_Categoria) VALUES ('Tecnología');
INSERT INTO Categorias (nombre_Categoria) VALUES ('Cocina');
INSERT INTO Categorias (nombre_Categoria) VALUES ('Deportes');
INSERT INTO Categorias (nombre_Categoria) VALUES ('Arte');
INSERT INTO Categorias (nombre_Categoria) VALUES ('Biología');

--- CLIENTE ---
INSERT INTO Clientes (dpi, nombre, apellido, telefono) VALUES (2345678901234, 'Juan', 'Pérez', '555-1234');
INSERT INTO Clientes (dpi, nombre, apellido, telefono) VALUES (1234567890123, 'Ana', 'Martínez', '555-5678');
INSERT INTO Clientes (dpi, nombre, apellido, telefono) VALUES (3456789012345, 'Luis', 'González', '555-8765');
INSERT INTO Clientes (dpi, nombre, apellido, telefono) VALUES (4567890123456, 'Sofía', 'Ramírez', '555-4321');
INSERT INTO Clientes (dpi, nombre, apellido, telefono) VALUES (5678901234567, 'Carlos', 'Mendoza', '555-6543');
INSERT INTO Clientes (dpi, nombre, apellido, telefono) VALUES (6789012345678, 'Marta', 'Castillo', '555-6789');
INSERT INTO Clientes (dpi, nombre, apellido, telefono) VALUES (7890123456789, 'José', 'López', '555-9876');
INSERT INTO Clientes (dpi, nombre, apellido, telefono) VALUES (8901234567890, 'Elena', 'Morales', '555-1357');
INSERT INTO Clientes (dpi, nombre, apellido, telefono) VALUES (9012345678901, 'Mario', 'Ortega', '555-2468');
INSERT INTO Clientes (dpi, nombre, apellido, telefono) VALUES (1012345678902, 'Paola', 'Ruiz', '555-3579');



--- EMPLEADO ---
INSERT INTO Empleados (nombre, apellido, telefono, direccion, dpi) VALUES ('Laura', 'Sánchez', '5541-1234', 'Zona 1', '9876543210123');
INSERT INTO Empleados (nombre, apellido, telefono, direccion, dpi) VALUES ('Pedro', 'Gómez', '5555-5678', 'Zona 2', '9876543210124');
INSERT INTO Empleados (nombre, apellido, telefono, direccion, dpi) VALUES ('Sara', 'Lara', '5551-8765', 'Zona 3', '9876543210125');
INSERT INTO Empleados (nombre, apellido, telefono, direccion, dpi) VALUES ('Daniel', 'Díaz', '5552-4321', 'Zona 4', '9876543210126');
INSERT INTO Empleados (nombre, apellido, telefono, direccion, dpi) VALUES ('Valeria', 'Jiménez', '5553-6543', 'Zona 5', '9876543210127');
INSERT INTO Empleados (nombre, apellido, telefono, direccion, dpi) VALUES ('Ricardo', 'Pineda', '5554-6789', 'Zona 6', '9876543210128');
INSERT INTO Empleados (nombre, apellido, telefono, direccion, dpi) VALUES ('María', 'Zúñiga', '5556-9876', 'Zona 7', '9876543210129');
INSERT INTO Empleados (nombre, apellido, telefono, direccion, dpi) VALUES ('Francisco', 'Herrera', '5557-1357', 'Zona 8', '9876543210130');
INSERT INTO Empleados (nombre, apellido, telefono, direccion, dpi) VALUES ('Lucía', 'Molina', '5558-2468', 'Zona 9', '9876543210131');
INSERT INTO Empleados (nombre, apellido, telefono, direccion, dpi) VALUES ('Fernando', 'Vargas', '5559-3579', 'Zona 10', '9876543210132');

--- LIBRO ---
INSERT INTO Libros (isbn, nombre, sinopsis, autor, editorial, disponibilidad, numero_Estanteria, cluster, categoria_id) 
VALUES ('978-3-16-148410-0', 'Cien Años de Soledad', 'Una historia épica sobre la familia Buendía', 'Gabriel García Márquez', 'Sudamericana', TRUE, 'A1', 'Literatura', 1);
INSERT INTO Libros (isbn, nombre, sinopsis, autor, editorial, disponibilidad, numero_Estanteria, cluster, categoria_id) 
VALUES ('978-0-14-118280-3', '1984', 'Un futuro distópico bajo el control total del Gran Hermano', 'George Orwell', 'Penguin', TRUE, 'B1', 'Ciencia Ficción', 2);
INSERT INTO Libros (isbn, nombre, sinopsis, autor, editorial, disponibilidad, numero_Estanteria, cluster, categoria_id) 
VALUES ('978-0-00-000000-3', 'El Principito', 'Un pequeño príncipe que viaja por diferentes planetas', 'Antoine de Saint-Exupéry', 'Gallimard', TRUE, 'C1', 'Ficción', 1);
INSERT INTO Libros (isbn, nombre, sinopsis, autor, editorial, disponibilidad, numero_Estanteria, cluster, categoria_id) 
VALUES ('978-1-234-56789-0', 'Sapiens', 'Una breve historia de la humanidad', 'Yuval Noah Harari', 'Harper', TRUE, 'D1', 'Historia', 3);
INSERT INTO Libros (isbn, nombre, sinopsis, autor, editorial, disponibilidad, numero_Estanteria, cluster, categoria_id) 
VALUES ('978-0-00-000000-4', 'El Quijote', 'Las aventuras del caballero Don Quijote de la Mancha', 'Miguel de Cervantes', 'Anaya', TRUE, 'E1', 'Ficción', 1);
INSERT INTO Libros (isbn, nombre, sinopsis, autor, editorial, disponibilidad, numero_Estanteria, cluster, categoria_id) 
VALUES ('978-0-00-000000-5', 'El origen de las especies', 'Teoría de la evolución y selección natural', 'Charles Darwin', 'Penguin', TRUE, 'F1', 'Ciencia', 2);
INSERT INTO Libros (isbn, nombre, sinopsis, autor, editorial, disponibilidad, numero_Estanteria, cluster, categoria_id) 
VALUES ('978-0-00-000000-6', 'El hombre en busca de sentido', 'La experiencia de Viktor Frankl en campos de concentración', 'Viktor Frankl', 'Herder', TRUE, 'G1', 'Psicología', 3);
INSERT INTO Libros (isbn, nombre, sinopsis, autor, editorial, disponibilidad, numero_Estanteria, cluster, categoria_id) 
VALUES ('978-0-00-000000-7', 'La Odisea', 'El viaje épico de Ulises de regreso a Ítaca', 'Homero', 'Planeta', TRUE, 'H1', 'Historia', 3);
INSERT INTO Libros (isbn, nombre, sinopsis, autor, editorial, disponibilidad, numero_Estanteria, cluster, categoria_id) 
VALUES ('978-0-00-000000-8', 'Fundamentos de Matemáticas', 'Una introducción a las matemáticas modernas', 'Richard Courant', 'McGraw-Hill', TRUE, 'I1', 'Matemáticas', 4);
INSERT INTO Libros (isbn, nombre, sinopsis, autor, editorial, disponibilidad, numero_Estanteria, cluster, categoria_id) 
VALUES ('978-0-00-000000-9', 'El arte de la guerra', 'Un tratado sobre estrategia militar', 'Sun Tzu', 'Penguin', TRUE, 'J1', 'Filosofía', 5);

--- PRESTAMO ---
INSERT INTO Prestamos (fecha_De_Prestamo, fecha_De_Devolucion, vigencia, empleado_id, cliente_dpi) VALUES ('2024-09-05', '2024-09-20', TRUE, 2, 2345678901234);
INSERT INTO Prestamos (fecha_De_Prestamo, fecha_De_Devolucion, vigencia, empleado_id, cliente_dpi) VALUES ('2024-09-01', '2024-09-15', TRUE, 1, 1234567890123);
INSERT INTO Prestamos (fecha_De_Prestamo, fecha_De_Devolucion, vigencia, empleado_id, cliente_dpi) VALUES ('2024-09-10', '2024-09-25', TRUE, 3, 3456789012345);
INSERT INTO Prestamos (fecha_De_Prestamo, fecha_De_Devolucion, vigencia, empleado_id, cliente_dpi) VALUES ('2024-09-12', '2024-09-27', TRUE, 4, 4567890123456);
INSERT INTO Prestamos (fecha_De_Prestamo, fecha_De_Devolucion, vigencia, empleado_id, cliente_dpi) VALUES ('2024-09-15', '2024-09-30', TRUE, 5, 5678901234567);
INSERT INTO Prestamos (fecha_De_Prestamo, fecha_De_Devolucion, vigencia, empleado_id, cliente_dpi) VALUES ('2024-09-17', '2024-10-02', TRUE, 6, 6789012345678);
INSERT INTO Prestamos (fecha_De_Prestamo, fecha_De_Devolucion, vigencia, empleado_id, cliente_dpi) VALUES ('2024-09-20', '2024-10-05', TRUE, 7, 7890123456789);
INSERT INTO Prestamos (fecha_De_Prestamo, fecha_De_Devolucion, vigencia, empleado_id, cliente_dpi) VALUES ('2024-09-25', '2024-10-10', TRUE, 8, 8901234567890);
INSERT INTO Prestamos (fecha_De_Prestamo, fecha_De_Devolucion, vigencia, empleado_id, cliente_dpi) VALUES ('2024-09-28', '2024-10-13', TRUE, 9, 9012345678901);
INSERT INTO Prestamos (fecha_De_Prestamo, fecha_De_Devolucion, vigencia, empleado_id, cliente_dpi) VALUES ('2024-09-30', '2024-10-15', TRUE, 10, 1012345678902);


select * from categorias;

select * from clientes;





select * from libros;

select * from empleados;

select * from prestamos;
