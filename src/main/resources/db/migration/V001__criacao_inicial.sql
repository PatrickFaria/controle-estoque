create table produto(
	id bigint not null auto_increment,
    nome varchar(60) not null,
    codigo decimal(10,2) not null unique,
    itens int not null,
    valor decimal(10,2) not null,
    
    primary key (id)
)engine=InnoDB default charset=utf8;