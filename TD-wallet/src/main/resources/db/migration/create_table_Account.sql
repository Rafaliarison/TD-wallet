create table if not exists account (
    account_id serial primary key ,
    account_name varchar(100) check ( account_name in ('current_account', 'save_account')),
    account_currency int references currency(currency_id),
    account_solde int references solde(solde_id)
);