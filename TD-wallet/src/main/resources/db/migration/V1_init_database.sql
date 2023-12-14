create table if not exists solde (
    solde_id serial primary key ,
    solde_date timestamp,
    solde_amount float
);

create table if not exists currency (
    currency_id serial primary key ,
    currency_name varchar(50),
    currency_code varchar(3) check ( currency_code in ('EUR', 'MGA'))
);

create table if not exists account (
    account_id serial primary key ,
    account_name varchar(150),
    account_type varchar(100) check ( account_name in ('current_account', 'save_account')),
    account_currency int references currency(currency_id),
    account_solde int references solde(solde_id)
);

create table if not exists transaction (
    transaction_id serial primary key ,
    transaction_label varchar(100),
    transaction_amount float,
    transaction_type varchar(10) check ( transaction_type in ('debit', 'credit')),
    transaction_date timestamp
);