set foreign_key_checks=0;

delete from produto;

insert ignore into produto (id, nome, codigo, itens, valor) values (1,'Produto 1',1234, 2, 1000);
insert ignore into produto (id, nome, codigo, itens, valor) values (2,'Produto 2',12345, 2, 1000);
insert ignore into produto (id, nome, codigo, itens, valor) values (3,'Produto 3', 4567, 2, 1000);