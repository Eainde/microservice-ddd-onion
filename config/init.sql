SELECT 'CREATE DATABASE eainde'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'eainde')\gexec