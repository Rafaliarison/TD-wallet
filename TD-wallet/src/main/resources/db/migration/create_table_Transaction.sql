create table if not exists transaction (
    transaction_id serial primary key ,
    account_id int references account(account_id),
    transaction_label varchar(100),
    transaction_amount DECIMAL(10,2),
    transaction_type varchar(10) check ( transaction_type in ('debit', 'credit')),
    transaction_date timestamp
);
