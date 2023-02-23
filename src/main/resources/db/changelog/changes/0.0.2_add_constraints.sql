ALTER TABLE budget
    DROP CONSTRAINT IF EXISTS budget_type_income_id_fk;
ALTER TABLE budget
    ADD CONSTRAINT budget_type_income_id_fk
        FOREIGN KEY (type_income_id) REFERENCES type_income(id)
            ON DELETE SET NULL;

ALTER TABLE purchase
    DROP CONSTRAINT IF EXISTS purchase_type_expense_id_fk;
ALTER TABLE purchase
    ADD CONSTRAINT purchase_type_expense_id_fk
        FOREIGN KEY (type_expense_id) REFERENCES type_expense(id)
            ON DELETE SET NULL;

ALTER TABLE checks
    DROP CONSTRAINT IF EXISTS checks_type_receipt_id_fk;
ALTER TABLE checks
    ADD CONSTRAINT checks_type_receipt_id_fk
        FOREIGN KEY (type_receipt_id) REFERENCES type_receipt(id)
            ON DELETE SET NULL;

ALTER TABLE type_expense
    DROP CONSTRAINT IF EXISTS type_expense_category_expense_id_fk;
ALTER TABLE type_expense
    ADD CONSTRAINT type_expense_category_expense_id_fk
        FOREIGN KEY (category_expense_id) REFERENCES category_expense(id)
            ON DELETE SET NULL;

ALTER TABLE budget_checks
    DROP CONSTRAINT IF EXISTS budget_checks_id_fk;
ALTER TABLE budget_checks
    ADD CONSTRAINT budget_checks_id_fk
        FOREIGN KEY (budget_id) REFERENCES budget;

ALTER TABLE budget_checks
    DROP CONSTRAINT IF EXISTS checks_budget_id_fk;
ALTER TABLE budget_checks
    ADD CONSTRAINT checks_budget_id_fk
        FOREIGN KEY (checks_id) REFERENCES checks;

ALTER TABLE purchase_checks
    DROP CONSTRAINT IF EXISTS purchase_checks_id_fk;
ALTER TABLE purchase_checks
    ADD CONSTRAINT purchase_checks_id_fk
        FOREIGN KEY (purchase_id) REFERENCES purchase;

ALTER TABLE purchase_checks
    DROP CONSTRAINT IF EXISTS checks_purchase_id_fk;
ALTER TABLE purchase_checks
    ADD CONSTRAINT checks_purchase_id_fk
        FOREIGN KEY (checks_id) REFERENCES checks;

ALTER TABLE users_roles
    DROP CONSTRAINT IF EXISTS user_roles_id_fk;
ALTER TABLE users_roles
    ADD CONSTRAINT user_roles_id_fk
        FOREIGN KEY (user_id) REFERENCES users;

ALTER TABLE users_roles
    DROP CONSTRAINT IF EXISTS roles_user_id_fk;
ALTER TABLE users_roles
    ADD CONSTRAINT roles_user_id_fk
        FOREIGN KEY (roles_id) REFERENCES role;