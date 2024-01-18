--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4 (Homebrew)
-- Dumped by pg_dump version 15.4 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: maladie; Type: TABLE; Schema: public; Owner: rakharrs
--

CREATE TABLE public.maladie (
    id character(7) NOT NULL,
    designation character varying(50)
);


ALTER TABLE public.maladie OWNER TO rakharrs;

--
-- Name: maladie_symptome; Type: TABLE; Schema: public; Owner: rakharrs
--

CREATE TABLE public.maladie_symptome (
    id character(7) NOT NULL,
    id_maladie character(7),
    id_symptome character(7),
    min double precision,
    max double precision
);


ALTER TABLE public.maladie_symptome OWNER TO rakharrs;

--
-- Name: medicament; Type: TABLE; Schema: public; Owner: rakharrs
--

CREATE TABLE public.medicament (
    id character(7) NOT NULL,
    designation character varying(50),
    prix double precision
);


ALTER TABLE public.medicament OWNER TO rakharrs;

--
-- Name: patient; Type: TABLE; Schema: public; Owner: rakharrs
--

CREATE TABLE public.patient (
    id character(7) NOT NULL,
    nom character varying(50),
    datenaissance date
);


ALTER TABLE public.patient OWNER TO rakharrs;

--
-- Name: soin; Type: TABLE; Schema: public; Owner: rakharrs
--

CREATE TABLE public.soin (
    id character(7) NOT NULL,
    id_medicament character(7),
    id_symptome character(7),
    valeur double precision
);


ALTER TABLE public.soin OWNER TO rakharrs;

--
-- Name: symptome; Type: TABLE; Schema: public; Owner: rakharrs
--

CREATE TABLE public.symptome (
    id character(7) NOT NULL,
    designation character varying(50)
);


ALTER TABLE public.symptome OWNER TO rakharrs;

--
-- Data for Name: maladie; Type: TABLE DATA; Schema: public; Owner: rakharrs
--

COPY public.maladie (id, designation) FROM stdin;
MAL0000	Paludisme
MAL0001	Grippe
\.


--
-- Data for Name: maladie_symptome; Type: TABLE DATA; Schema: public; Owner: rakharrs
--

COPY public.maladie_symptome (id, id_maladie, id_symptome, min, max) FROM stdin;
MAS0000	MAL0000	SYM0000	2	5
MAS0001	MAL0000	SYM0001	1	4
\.


--
-- Data for Name: medicament; Type: TABLE DATA; Schema: public; Owner: rakharrs
--

COPY public.medicament (id, designation, prix) FROM stdin;
MED0000	Paracetamol	1000
MED0001	Doliprane	100
\.


--
-- Data for Name: patient; Type: TABLE DATA; Schema: public; Owner: rakharrs
--

COPY public.patient (id, nom, datenaissance) FROM stdin;
\.


--
-- Data for Name: soin; Type: TABLE DATA; Schema: public; Owner: rakharrs
--

COPY public.soin (id, id_medicament, id_symptome, valeur) FROM stdin;
SOI0000	MED0001	SYM0000	2
SOI0001	MED0001	SYM0001	0.5
SOI0002	MED0000	SYM0001	1
SOI0003	MED0000	SYM0003	1
\.


--
-- Data for Name: symptome; Type: TABLE DATA; Schema: public; Owner: rakharrs
--

COPY public.symptome (id, designation) FROM stdin;
SYM0000	Fievre
SYM0001	Frisson
SYM0002	Maux de tête
SYM0003	Douleurs musculaire
\.


--
-- Name: maladie maladie_pkey; Type: CONSTRAINT; Schema: public; Owner: rakharrs
--

ALTER TABLE ONLY public.maladie
    ADD CONSTRAINT maladie_pkey PRIMARY KEY (id);


--
-- Name: maladie_symptome maladie_symptome_pkey; Type: CONSTRAINT; Schema: public; Owner: rakharrs
--

ALTER TABLE ONLY public.maladie_symptome
    ADD CONSTRAINT maladie_symptome_pkey PRIMARY KEY (id);


--
-- Name: medicament medicament_pkey; Type: CONSTRAINT; Schema: public; Owner: rakharrs
--

ALTER TABLE ONLY public.medicament
    ADD CONSTRAINT medicament_pkey PRIMARY KEY (id);


--
-- Name: patient patient_pkey; Type: CONSTRAINT; Schema: public; Owner: rakharrs
--

ALTER TABLE ONLY public.patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);


--
-- Name: soin soin_pkey; Type: CONSTRAINT; Schema: public; Owner: rakharrs
--

ALTER TABLE ONLY public.soin
    ADD CONSTRAINT soin_pkey PRIMARY KEY (id);


--
-- Name: symptome symptome_pkey; Type: CONSTRAINT; Schema: public; Owner: rakharrs
--

ALTER TABLE ONLY public.symptome
    ADD CONSTRAINT symptome_pkey PRIMARY KEY (id);


--
-- Name: maladie_symptome maladie_symptome_id_maladie_fkey; Type: FK CONSTRAINT; Schema: public; Owner: rakharrs
--

ALTER TABLE ONLY public.maladie_symptome
    ADD CONSTRAINT maladie_symptome_id_maladie_fkey FOREIGN KEY (id_maladie) REFERENCES public.maladie(id);


--
-- Name: maladie_symptome maladie_symptome_id_symptome_fkey; Type: FK CONSTRAINT; Schema: public; Owner: rakharrs
--

ALTER TABLE ONLY public.maladie_symptome
    ADD CONSTRAINT maladie_symptome_id_symptome_fkey FOREIGN KEY (id_symptome) REFERENCES public.symptome(id);


--
-- Name: soin soin_id_medicament_fkey; Type: FK CONSTRAINT; Schema: public; Owner: rakharrs
--

ALTER TABLE ONLY public.soin
    ADD CONSTRAINT soin_id_medicament_fkey FOREIGN KEY (id_medicament) REFERENCES public.medicament(id);


--
-- Name: soin soin_id_symptome_fkey; Type: FK CONSTRAINT; Schema: public; Owner: rakharrs
--

ALTER TABLE ONLY public.soin
    ADD CONSTRAINT soin_id_symptome_fkey FOREIGN KEY (id_symptome) REFERENCES public.symptome(id);


--
-- PostgreSQL database dump complete
--

