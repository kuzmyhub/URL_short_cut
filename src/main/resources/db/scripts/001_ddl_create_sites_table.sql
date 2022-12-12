create TABLE IF NOT EXISTS sites (
id SERIAL PRIMARY KEY,
name VARCHAR UNIQUE,
login VARCHAR UNIQUE,
password VARCHAR,
registration BOOLEAN
);

comment on table sites is 'Данные зарегестрированных сайтов';
comment on column sites.id is 'Идентификатор сайта';
comment on column sites.name is 'Доменное имя сайта HTTP://DOMAIN.DOMAIN';
comment on column sites.login is 'Логин сайта';
comment on column sites.password is 'Пароль сайта';