create TABLE IF NOT EXISTS links (
id SERIAL PRIMARY KEY,
url VARCHAR UNIQUE,
code VARCHAR UNIQUE,
total INT,
site_id INT references sites(id)
);

comment on table links is 'Данные ссылок зарегистрированных сайтов';
comment on column links.id is 'Идентификатор ссылки';
comment on column links.url is 'Ссылка';
comment on column links.code is 'Код ассоциированный со ссылкой';
comment on column links.total is 'Количество вызовов ссылки';
comment on column links.site_id is 'Идентификатор зарегистрированного сайта';