CREATE TABLE IF NOT EXISTS links (
id SERIAL PRIMARY KEY,
url VARCHAR,
code VARCHAR,
total INT,
site_id INT references sites(id)
);