INSERT INTO convenio VALUES(1,'Ambulatorial',50.00);
INSERT INTO convenio VALUES(2,'Simples',100.00);
INSERT INTO convenio VALUES(3,'Master',150.00);
SELECT * FROM convenio;
UPDATE convenio SET nomeconvenio = 'Comum' WHERE ccodigo = 2;
CREATE TABLE associado(acodigo INT, nomeassociado VARCHAR(30), ccodigo INT, 
PRIMARY KEY(acodigo), 
FOREIGN KEY (ccodigo) REFERENCES convenio(ccodigo));
INSERT INTO associado VALUES(91,'André Freitas',3);
SELECT * FROM associado;
INSERT INTO associado VALUES(92,'Joao da Silva',2);
INSERT INTO associado VALUES(93,'Maria Conceicao',3);
INSERT INTO associado VALUES(94,'Ana Maria Souza',1);
INSERT INTO associado VALUES(95,'Franciso Amadeu',1);
SELECT * FROM associado WHERE ccodigo IN (
SELECT ccodigo FROM convenio WHERE nomeconvenio = 'Master');
select associado.nomeassociado, convenio.nomeconvenio from convenio, associado where 
associado.ccodigo = convenio.ccodigo and 
nomeconvenio = 'Master';
SELECT * FROM associado ORDER BY nomeassociado DESC;
INSERT INTO associado VALUES(96,'Mariana Silva',3);
INSERT INTO associado VALUES(97,'André Luis Osorio',2);
SELECT * FROM associado WHERE nomeassociado LIKE '%Maria%';
SELECT * FROM associado WHERE nomeassociado ILIKE '%MARIA%';
SELECT * FROM associado WHERE nomeassociado ILIKE '%Andre%';
SELECT * FROM convenio WHERE ccodigo NOT IN (1,2);
SELECT COUNT (*) FROM associado;
SELECT AVG(valor) FROM convenio;
SELECT ccodigo, COUNT(*) FROM associado GROUP BY ccodigo;
SELECT nomeconvenio FROM convenio WHERE valor = 
(SELECT MAX(valor) FROM convenio);
SELECT a.nomeassociado, c.nomeconvenio FROM associado a 
JOIN convenio c ON a.ccodigo = c.ccodigo ORDER BY c.nomeconvenio;
SELECT a.nomeassociado, c.nomeconvenio FROM convenio c, associado a 
WHERE a.ccodigo = c.ccodigo ORDER BY c.nomeconvenio;
SELECT a.nomeassociado, c.nomeconvenio FROM associado a 
INNER JOIN convenio c ON a.ccodigo = c.ccodigo ORDER BY c.nomeconvenio;
SELECT a.nomeassociado, c.nomeconvenio FROM associado a 
LEFT JOIN convenio c ON a.ccodigo = c.ccodigo ORDER BY c.nomeconvenio;
SELECT a.nomeassociado, c.nomeconvenio FROM associado a 
RIGHT JOIN convenio c ON a.ccodigo = c.ccodigo ORDER BY c.nomeconvenio;
SELECT a.nomeassociado, c.nomeconvenio FROM associado a 
FULL OUTER JOIN convenio c ON a.ccodigo = c.ccodigo ORDER BY c.nomeconvenio;
SELECT c.nomeconvenio, COUNT(*), SUM(c.valor) FROM convenio c, associado a 
WHERE a.ccodigo = c.ccodigo GROUP BY c.nomeconvenio;
INSERT INTO associado VALUES(98,'Cleusa Maria');
INSERT INTO convenio VALUES(4,'Outro',200.00);
SELECT c.nomeconvenio, ' Total de associados: ', count (a.acodigo) 
FROM convenio c FULL OUTER JOIN associado a ON c.ccodigo = a.ccodigo GROUP BY c.ccodigo;
