use souninja;

insert into cliente_regime_tributario(id, nome) values (1, 'Simples Nacional'), (2, 'Lucro Presumido');

insert into anexo_tipo(id, nome, aliquota_imposto) values (1, 'Comércio', 6), (2, 'Indústria', 8.5), (3, 'Prestação de serviços', 11);

insert into imposto_tipo(id, nome) values (1, 'Simples Nacional'), (2, 'Imposto de Renda'), (3, 'ISS'), (4, 'Cofins');