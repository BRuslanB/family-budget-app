ALTER TABLE expense
    DROP CONSTRAINT IF EXISTS expense_expense_category_id_fk;
ALTER TABLE expense
    ADD CONSTRAINT expense_expense_category_id_fk
        FOREIGN KEY (category_id) REFERENCES expense_category(id)
            ON DELETE SET NULL;

ALTER TABLE checks
    DROP CONSTRAINT IF EXISTS checks_income_id_fk;
ALTER TABLE checks
    ADD CONSTRAINT checks_income_id_fk
        FOREIGN KEY (income_id) REFERENCES income(id)
            ON DELETE SET NULL;

ALTER TABLE checks
    DROP CONSTRAINT IF EXISTS checks_expense_id_fk;
ALTER TABLE checks
    ADD CONSTRAINT checks_expense_id_fk
        FOREIGN KEY (expense_id) REFERENCES expense(id)
            ON DELETE SET NULL;

ALTER TABLE users_roles
    DROP CONSTRAINT IF EXISTS user_roles_id_fk;
ALTER TABLE users_roles
    ADD CONSTRAINT user_roles_id_fk
        FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE users_roles
    DROP CONSTRAINT IF EXISTS roles_user_id_fk;
ALTER TABLE users_roles
    ADD CONSTRAINT roles_user_id_fk
        FOREIGN KEY (roles_id) REFERENCES role(id);