CREATE TABLE plano (
idPlano SERIAL PRIMARY KEY,
status integer DEFAULT 1,
nome varchar(50)
);

CREATE TABLE especialidade (
idEspecialidade SERIAL PRIMARY KEY,
status integer DEFAULT 1,
nome varchar(50)
);

CREATE TABLE usuario (
idUsuario SERIAL PRIMARY KEY,
nome varchar(50),
status integer DEFAULT 1,
senha varchar(20),
email varchar(50),
foto VARCHAR(200)
);

CREATE TABLE estabelecimento (
idEstabelecimento SERIAL PRIMARY KEY,
status integer DEFAULT 1,
nome varchar(50),
coordenadaY integer,
endereco varchar(100),
coordenadaX integer,
foto VARCHAR(200),
notaTotal integer,
totalAtendimento integer,
totalHigiene integer,
totalTempo integer,
totalEquipamento integer
);
CREATE TABLE horario_abertura
(
domingo time without time zone,
segunda time without time zone,
terca time without time zone,
quarta time without time zone,
quinta time without time zone,
sexta time without time zone,
sabado time without time zone,
idestabelecimento integer,
FOREIGN KEY(idEstabelecimento) REFERENCES estabelecimento (idEstabelecimento)
);

CREATE TABLE horario_fechamento
(
domingo time without time zone,
segunda time without time zone,
terca time without time zone,
quarta time without time zone,
quinta time without time zone,
sexta time without time zone,
sabado time without time zone,
idestabelecimento integer,
FOREIGN KEY(idEstabelecimento) REFERENCES estabelecimento (idEstabelecimento)
);

CREATE TABLE aceita (
idEstabelecimento integer,
idPlano integer,
FOREIGN KEY(idEstabelecimento) REFERENCES estabelecimento (idEstabelecimento),
FOREIGN KEY(idPlano) REFERENCES plano (idPlano)
);

CREATE TABLE tem (
idEspecialidade integer,
idEstabelecimento integer,
FOREIGN KEY(idEspecialidade) REFERENCES especialidade (idEspecialidade),
FOREIGN KEY(idEstabelecimento) REFERENCES estabelecimento (idEstabelecimento)
);

CREATE TABLE avalia (
idAvalia SERIAL PRIMARY KEY,
atendimento integer,
higiene integer,
qualidadeEquipamento integer,
tempoEspera integer,
comentario text,
idEstabelecimento integer,
idUsuario integer,
status integer DEFAULT 1,
FOREIGN KEY(idEstabelecimento) REFERENCES estabelecimento (idEstabelecimento),
FOREIGN KEY(idUsuario) REFERENCES usuario (idUsuario)
);
CREATE OR REPLACE FUNCTION FN_ATUALIZAR_NOTAS()
RETURNS TRIGGER AS
$$
BEGIN
	update estabelecimento set notatotal = (select (AVG(atendimento)+AVG(higiene)+AVG(qualidadeequipamento)+AVG(tempoespera))/4
	from avalia where idestabelecimento = estabelecimento.idestabelecimento);
	update estabelecimento set totalatendimento = (select AVG(atendimento) from avalia where idestabelecimento = estabelecimento.idestabelecimento);
	update estabelecimento set totalhigiene = (select AVG(higiene) from avalia where idestabelecimento = estabelecimento.idestabelecimento);
	update estabelecimento set totaltempo = (select AVG(tempoespera) from avalia where idestabelecimento = estabelecimento.idestabelecimento);
	update estabelecimento set totalequipamento = (select AVG(qualidadeequipamento) from avalia where idestabelecimento = estabelecimento.idestabelecimento);

  RETURN NEW;
END;
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER TG_ATUALIZAR_NOTAS_INSERT
AFTER INSERT ON avalia
EXECUTE PROCEDURE FN_ATUALIZAR_NOTAS();

CREATE TRIGGER TG_ATUALIZAR_NOTAS_UPDATE
AFTER UPDATE ON avalia
EXECUTE PROCEDURE FN_ATUALIZAR_NOTAS();