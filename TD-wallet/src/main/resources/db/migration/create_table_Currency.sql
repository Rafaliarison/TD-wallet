create table if not exists currency (
    currency_id serial primary key ,
    currency_name varchar(50),
    currency_code varchar(3) check ( currency_code in ('EUR', 'MGA'))
);