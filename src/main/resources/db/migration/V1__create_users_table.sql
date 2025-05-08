CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50),               -- unique制約なし
  email VARCHAR(255) NOT NULL UNIQUE, -- メールは必ず一意
  password VARCHAR(255) NOT NULL,
  phone_number VARCHAR(20),
  icon_image VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP DEFAULT NULL
);