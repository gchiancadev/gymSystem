INSERT INTO matriculas (aluno_id, data_encerramento, dia_vencimento, status)
VALUES (1, CURRENT_DATE + INTERVAL '90 days', 10, 'ATIVA');

INSERT INTO matriculas (aluno_id, data_encerramento, dia_vencimento, status)
VALUES (4, CURRENT_DATE + INTERVAL '60 days', 15, 'ATIVA');


INSERT INTO matriculas_modalidades (
    matricula_id,
    modalidades_id,
    plano_id,
    data_inicio
)
SELECT
    m.id,
    mo.id,
    p.id,
    CURRENT_DATE + INTERVAL '90 days'
FROM matriculas m
    JOIN modalidades mo ON mo.nome = 'Musculação'
    JOIN planos p ON p.modalidade_id = mo.id AND p.nome = 'Mensal'
WHERE m.aluno_id = 1;


INSERT INTO matriculas_modalidades (
    matricula_id,
    modalidades_id,
    graduacoes_id,
    plano_id,
    data_inicio
)
SELECT
    m.id,
    mo.id,
    g.id,
    p.id,
    CURRENT_DATE + INTERVAL '60 days'
FROM matriculas m
    JOIN modalidades mo ON mo.nome = 'Jiu-Jitsu'
    JOIN graduacoes g ON g.modalidade_id = mo.id AND g.nome = 'Faixa Branca'
    JOIN planos p ON p.modalidade_id = mo.id AND p.nome = 'Mensal'
WHERE m.aluno_id = 4;


INSERT INTO faturas_matriculas (
    matricula_id,
    data_vencimento,
    valor,
    data_pagamento,
    status
)
SELECT
    m.id,
    CURRENT_DATE - INTERVAL '60 days',
    120,
    CURRENT_DATE - INTERVAL '55 days',
    'PAGA'
FROM matriculas m
WHERE m.aluno_id = 1;

INSERT INTO faturas_matriculas (
    matricula_id,
    data_vencimento,
    valor,
    data_pagamento,
    status
)
SELECT
    m.id,
    CURRENT_DATE - INTERVAL '30 days',
    120,
    CURRENT_DATE - INTERVAL '29 days',
    'PAGA'
FROM matriculas m
WHERE m.aluno_id = 1;

INSERT INTO faturas_matriculas (
    matricula_id,
    data_vencimento,
    valor,
    status
)
SELECT
    m.id,
    CURRENT_DATE - INTERVAL '10 days',
    120,
    'ABERTA'
FROM matriculas m
WHERE m.aluno_id = 1;


INSERT INTO faturas_matriculas (
    matricula_id,
    data_vencimento,
    valor,
    data_pagamento,
    status
)
SELECT
    m.id,
    CURRENT_DATE - INTERVAL '30 days',
    180,
    CURRENT_DATE - INTERVAL '28 days',
    'PAGA'
FROM matriculas m
WHERE m.aluno_id = 4;

INSERT INTO faturas_matriculas (
    matricula_id,
    data_vencimento,
    valor,
    status
)
SELECT
    m.id,
    CURRENT_DATE - INTERVAL '15 days',
    120,
    'ABERTA'
FROM matriculas m
WHERE m.aluno_id = 4;