CREATE TABLE IF NOT EXISTS heroe_mission (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    heroeid int NOT NULL,
    missionid int NOT NULL,
    CONSTRAINT FK_Hero FOREIGN KEY (heroeid) REFERENCES Heroe(id),
    CONSTRAINT FK_Mission FOREIGN KEY (missionid) REFERENCES Mission(id)
);


