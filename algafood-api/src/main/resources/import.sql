insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Mexicana');
insert into cozinha (id, nome) values (3, 'Chinesa');

insert into forma_de_pagamento (id, descricao) values (1, 'Cartão de Débito');
insert into forma_de_pagamento (id, descricao) values (2, 'Cartão de Crédito');
insert into forma_de_pagamento (id, descricao) values (3, 'Dinheiro');

insert into estado (id, nome) values (1, 'SP');
insert into estado (id, nome) values (2, 'PR');
insert into estado (id, nome) values (3, 'MG');
insert into estado (id, nome) values (4, 'RJ');

insert into cidade (id, nome, estado_id) values (1, 'São Paulo', 1);
insert into cidade (id, nome, estado_id) values (2, 'Curitiba', 2);
insert into cidade (id, nome, estado_id) values (3, 'Uberlandia', 3);
insert into cidade (id, nome, estado_id) values (4, 'Paraty', 4);
insert into cidade (id, nome, estado_id) values (5, 'Sorocaba', 1);
insert into cidade (id, nome, estado_id) values (6, 'Rio de Janeiro', 4);

insert into restaurante (id, nome, taxa_frete, cozinha_id, cidade_id, forma_de_pagamento_id) values (1, 'Nonnas Paola', 10, 2, 1, 3);
insert into restaurante (id, nome, taxa_frete, cozinha_id, cidade_id, forma_de_pagamento_id) values (2, 'Marttioni', 4, 3, 2, 2);
insert into restaurante (id, nome, taxa_frete, cozinha_id, cidade_id, forma_de_pagamento_id) values (3, 'Boi Nobre', 12, 1, 4, 1);