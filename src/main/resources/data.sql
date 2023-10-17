INSERT INTO usuario (birth, data_entrega, gender, is_editor, created_at, updated_at, dtype, cpf, desc_profile, email, last_name, link, nome, password, photo_profile, rg, portifolio)
VALUES (
           '1990-01-01',-- Exemplo de data de nascimento (birth)
           '2023-10-07', -- Exemplo de data de entrega (data_entrega)
           1, -- Exemplo de gênero (1 para masculino, 0 para feminino)
           true, -- Exemplo de is_editor (verdadeiro ou falso)
           CURRENT_TIMESTAMP, -- Exemplo de created_at
           CURRENT_TIMESTAMP, -- Exemplo de updated_at
           'tipo_usuario', -- Substitua 'tipo_usuario' pelo valor apropriado para dtype
           '12345678901', -- Exemplo de CPF
           'Descrição do perfil', -- Exemplo de desc_profile
           'usuario@email.com', -- Exemplo de e-mail
           'Sobrenome', -- Exemplo de sobrenome
           'http://example.com', -- Exemplo de link
           'Nome', -- Exemplo de nome
           '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', -- Exemplo de senha
           'http://example.com/photo.jpg', -- Exemplo de URL da foto do perfil
           '123456789', -- Exemplo de RG
           ARRAY['projeto1', 'projeto2']  -- Exemplo de array de portifolio (substitua pelos valores reais)
       );