-- Database: "curso-jsp"

-- DROP DATABASE "curso-jsp";

CREATE DATABASE "curso-jsp"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;

- Table: public.usuario

-- DROP TABLE public.usuario;

CREATE TABLE public.usuario
(
  login character varying,
  senha character varying,
  id bigint NOT NULL DEFAULT nextval('serialuser'::regclass),
  nome character varying
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario
  OWNER TO postgres;



ALTER TABLE public.usuario ADD COLUMN nome character varying;