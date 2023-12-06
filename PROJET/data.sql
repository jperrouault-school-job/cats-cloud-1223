INSERT INTO produit (id, name, price, notable)
VALUES  ('b667e797-fa0e-4d28-954e-96c5a8cefb69', 'Le produit', 500, true);

INSERT INTO commentaire (id, text, note, produit_id)
VALUES  ('601b4d4f-68f3-4292-a509-ef53926fbee9', 'Super', 5, 'b667e797-fa0e-4d28-954e-96c5a8cefb69'),
        ('d53e0842-0f1e-46cb-b747-e8cc580cabd4', 'Pas terrible ..', 1, 'b667e797-fa0e-4d28-954e-96c5a8cefb69');