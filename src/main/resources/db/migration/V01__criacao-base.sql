/*HospedeEntity*/
create table hospede (
  id INTEGER NOT NULL,
  nome VARCHAR(30) NOT NULL,
  documento VARCHAR(30) NOT NULL,
  telefone VARCHAR(15) NOT NULL,
  email VARCHAR(30),
  hospedado boolean
);

/*HospedagemEntity*/
create table hospedagem (
 	id INTEGER NOT NULL,
 	hospede INTEGER,
 	dataEntrada TIMESTAMP NOT NULL,
 	dataSaida TIMESTAMP,
 	adicionaVeiculo BOOLEAN NOT NULL,
 	valorEstadia NUMERIC(15,2)
 );
 
 /*Primary key*/
 ALTER TABLE hospede ADD PRIMARY KEY (id);
 ALTER TABLE hospedagem ADD PRIMARY KEY (id);
 
 /*Sequence*/
 create sequence hospede_seq owned by hospede.id;
 create sequence hospedagem_seq owned by hospedagem.id;
 
 /*Foreing key*/
 ALTER TABLE hospedagem  ADD CONSTRAINT hospedefk FOREIGN KEY (hospede) REFERENCES hospede(id);