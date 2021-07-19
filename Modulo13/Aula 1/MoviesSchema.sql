BEGIN;


CREATE TABLE public.movies
(
    id bigserial,
    name character varying(255) NOT NULL,
    release_date date NOT NULL,
    director_id bigint NOT NULL,
    country_id integer,
    studio_id bigint,
    PRIMARY KEY (id)
);

CREATE TABLE public.directors
(
    id bigserial,
    name character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.countries
(
    id serial,
    name character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.actors
(
    id bigserial,
    name character varying(255),
    PRIMARY KEY (id)
);

CREATE TABLE public.studios
(
    id bigserial,
    name character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.movies_actors
(
    movies_id bigserial,
    actors_id bigserial
);

ALTER TABLE public.movies
    ADD FOREIGN KEY (director_id)
    REFERENCES public.directors (id)
    NOT VALID;


ALTER TABLE public.movies
    ADD FOREIGN KEY (country_id)
    REFERENCES public.countries (id)
    NOT VALID;


ALTER TABLE public.movies
    ADD FOREIGN KEY (studio_id)
    REFERENCES public.studios (id)
    NOT VALID;


ALTER TABLE public.movies_actors
    ADD FOREIGN KEY (movies_id)
    REFERENCES public.movies (id)
    NOT VALID;


ALTER TABLE public.movies_actors
    ADD FOREIGN KEY (actors_id)
    REFERENCES public.actors (id)
    NOT VALID;

END;