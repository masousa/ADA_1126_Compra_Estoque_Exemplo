CREATE ROLE estoque LOGIN PASSWORD 'estoque';



CREATE DATABASE estoque;


ALTER DATABASE estoque OWNER TO estoque;

GRANT ALL ON DATABASE estoque TO "estoque";

