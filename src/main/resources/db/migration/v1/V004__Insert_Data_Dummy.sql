INSERT INTO hero (first_name, last_name, hero_name, hero_power)
VALUES 
('Carol Susan Jane','Danvers','Capitana Marvel','Brilla y vuela'),
('Steve','Rogers','Capitán América','Fuerza aumentada y escudo molón'),
('Bruce','Wayne','Batman','Ser rico'),
('Clarck','Kent','Superman','Fuerza, vuelo e invulnerabilidad'),
('Toni','Stark','Ironman','Ser casi tan rico como Batman'),
('Bruce','Banner','Hulk','Aplasta');

INSERT INTO mission (instructions)
VALUES
('Capturar al Doctor Doom'),
('Acompañar a La Cosa a la pedicura'),
('Detener a Ultrón'),
('Traer un café para el Doctor Strange'),
('Estrenar serie en Netflix'),
('Ir al cine a ver DeadPool'),
('Salvar el mundo'),
('Tomar un brunch'),
('Hacerse doble agente de Hydra'),
('Crear un meme gracioso en un ascensor'),
('Manifestarse a favor de alguna minoría');

INSERT INTO hero_mission (heroid, missionid)
VALUES
(1,1),
(1,2),
(2,4);