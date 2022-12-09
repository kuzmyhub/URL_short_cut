CREATE TABLE IF NOT EXISTS sites (
id SERIAL PRIMARY KEY,
name VARCHAR UNIQUE,
login VARCHAR UNIQUE,
password VARCHAR,
registration BOOLEAN
);

COMMENT ON TABLE sites IS 'Данные зарегестрированных сайтов';
COMMENT ON COLUMN sites.id IS 'Идентификатор сайта';
COMMENT ON COLUMN sites.name IS 'Доменное имя сайта HTTP://DOMAIN.DOMAIN';
COMMENT ON COLUMN sites.login IS 'Логин сайта';
COMMENT ON COLUMN sites.password IS 'Пароль сайта';