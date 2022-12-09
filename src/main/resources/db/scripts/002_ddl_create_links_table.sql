CREATE TABLE IF NOT EXISTS links (
id SERIAL PRIMARY KEY,
url VARCHAR UNIQUE,
code VARCHAR UNIQUE,
total INT,
site_id INT references sites(id)
);

COMMENT ON TABLE links IS 'Данные ссылок зарегистрированных сайтов';
COMMENT ON COLUMN links.id IS 'Идентификатор ссылки';
COMMENT ON COLUMN links.url IS 'Ссылка';
COMMENT ON COLUMN links.code IS 'Код ассоциированный со ссылкой';
COMMENT ON COLUMN links.total IS 'Количество вызовов ссылки';
COMMENT ON COLUMN links.site_id IS 'Идентификатор зарегистрированного сайта';