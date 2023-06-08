create table clientes (
    id  bigserial not null, 
    ativo boolean not null, 
    cpf varchar(11) not null, 
    data_nascimento DATE not null, 
    email varchar(100) not null, 
    nome varchar(60) not null, 
    sexo varchar(1) not null, 
    telefone varchar(15) not null, 
    endereco_id bigserial not null,
    foreign key (endereco_id) references enderecos (id), 
    primary key (id)
);
