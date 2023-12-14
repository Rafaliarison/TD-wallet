create table if not exists transaction (
    transaction_id serial primary key ,
    transaction_label varchar(100),
    transaction_amount float,
    transaction_type varchar(10) check ( transaction_type in ('debit', 'credit')),
    transaction_date timestamp
);