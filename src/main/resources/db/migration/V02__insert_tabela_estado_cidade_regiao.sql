INSERT INTO regiao (sigla, nome) VALUES 
  ('N', 'Norte'), 
  ('NE', 'Nordeste'),
  ('SE', 'Sudeste'),
  ('S', 'Sul'),
  ('CO', 'Centro-Oeste');

INSERT INTO estado (regiao_id, uf, nome) VALUES 
  (1, 'AM', 'Amazonas'),
  (1, 'RR', 'Roraima'),
  (1, 'AP', 'Amapá'),
  (1, 'PA', 'Pará'),
  (1, 'TO', 'Tocantins'),
  (1, 'RO', 'Rondônia'),
  (1, 'AC', 'Acre'),

  (2, 'MA', 'Maranhão'),
  (2, 'PI', 'Piauí'),
  (2, 'CE', 'Ceará'),
  (2, 'PE', 'Pernambuco'),
  (2, 'PB', 'Paraíba'),
  (2, 'SE', 'Sergipe'),
  (2, 'AL', 'Alagoas'),
  (2, 'BA', 'Bahia'),
  (2, 'RN', 'Rio Grande do Norte'), 

  (3, 'SP', 'São Paulo'),
  (3, 'ES', 'Espírito Santo'),
  (3, 'MG', 'Minas Gerais'),
  (3, 'RJ', 'Rio de Janeiro'),

  (4, 'PR', 'Paraná'),
  (4, 'RS', 'Rio Grande do Sul'),
  (4, 'SC', 'Santa Catarina'),

  (5, 'MT', 'Mato Grosso'),
  (5, 'MS', 'Mato Grosso do Sul'),
  (5, 'GO', 'Goiás');

INSERT INTO cidade (estado_id, nome ) VALUES
  (1, 'Manaus'),
  (2, 'Boa Vista'),
  (3, 'Macapá'),
  (4, 'Belém'),
  (5, 'Palmas'),
  (6, 'Porto Velho'),
  (7, 'Rio Branco'),

  (8, 'São Luís'),
  (9, 'Teresina'),
  (10 , 'Fortaleza'),
  (11 , 'Recife'),
  (12 , 'João Pessoa'),
  (13 , 'Aracaju'),
  (14, 'Maceió'),
  (15 , 'Salvador'),
  (16, 'Natal'),

  (17, 'São Paulo'),
  (18, 'Vitória'),
  (19, 'Belo Horizonte'),
  (20, 'Rio de Janeiro'),

  (21, 'Curitiba'),
  (22, 'Porto Alegre'),
  (23, 'Florianópolis'),

  (24, 'Cuiabá'),
  (25, 'Campo Grande'),
  (26, 'Goiânia');
