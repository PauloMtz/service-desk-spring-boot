create table enderecos (
    id  bigserial not null, 
    bairro varchar(100) not null, 
    cep varchar(9) not null, 
    cidade varchar(100) not null, 
    complemento varchar(255), 
    logradouro varchar(255) not null, 
    estado varchar(2) not null, 
    primary key (id)
);