--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-05-01 15:44:48 IST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 41525)
-- Name: schema; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA schema;


ALTER SCHEMA schema OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 41457)
-- Name: birth_certificates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.birth_certificates (
    certificate_id integer NOT NULL,
    birth_weight double precision,
    father_name character varying(255) NOT NULL,
    mother_name character varying(255) NOT NULL,
    time_of_birth character varying(255) NOT NULL
);


ALTER TABLE public.birth_certificates OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 41464)
-- Name: certificate_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.certificate_types (
    certificate_type_id integer NOT NULL,
    description character varying(255),
    type_name character varying(255) NOT NULL
);


ALTER TABLE public.certificate_types OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 41471)
-- Name: certificates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.certificates (
    certificate_id integer NOT NULL,
    expiry_date character varying(255),
    file_path character varying(255) NOT NULL,
    issued_date character varying(255) NOT NULL,
    certificate_type_id integer,
    patient_id integer NOT NULL
);


ALTER TABLE public.certificates OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 41512)
-- Name: certificates_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.certificates_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.certificates_seq OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 41478)
-- Name: lab_reports; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lab_reports (
    certificate_id integer NOT NULL,
    test_results character varying(255) NOT NULL,
    test_type character varying(255) NOT NULL
);


ALTER TABLE public.lab_reports OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 41485)
-- Name: patients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.patients (
    patient_id integer NOT NULL,
    address character varying(255),
    admission_date character varying(255),
    contact character varying(255),
    dob character varying(255),
    father_name character varying(255),
    gender character varying(255),
    husband_name character varying(255),
    name character varying(255)
);


ALTER TABLE public.patients OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 41513)
-- Name: patients_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.patients_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.patients_seq OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 41492)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    email character varying(255) NOT NULL,
    password_hash character varying(255) NOT NULL,
    role character varying(255),
    username character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 41514)
-- Name: users_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_seq OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 41499)
-- Name: vaccination_certificates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vaccination_certificates (
    certificate_id integer NOT NULL,
    dose_number integer NOT NULL,
    next_due_date character varying(255),
    vaccine_name character varying(255) NOT NULL
);


ALTER TABLE public.vaccination_certificates OWNER TO postgres;

--
-- TOC entry 3644 (class 0 OID 41457)
-- Dependencies: 218
-- Data for Name: birth_certificates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.birth_certificates (certificate_id, birth_weight, father_name, mother_name, time_of_birth) FROM stdin;
3	678	fjk	asdf	05:55
52	5	Father	Mother	14:34
\.


--
-- TOC entry 3645 (class 0 OID 41464)
-- Dependencies: 219
-- Data for Name: certificate_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.certificate_types (certificate_type_id, description, type_name) FROM stdin;
1	Issued for birth registration	Birth Certificate
2	Issued for vaccination records	Vaccination Certificate
3	Issued for Lab Reports	Lab Reports
4	Issued for death records	Death Certificate
5	Issued for medical fitness verification	Medical Fitness Certificate
6	Issued for disability verification	Disability Certificate
7	Issued for blood donation records	Blood Donation Certificate
8	Issued for organ donation records	Organ Donation Certificate
\.


--
-- TOC entry 3646 (class 0 OID 41471)
-- Dependencies: 220
-- Data for Name: certificates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.certificates (certificate_id, expiry_date, file_path, issued_date, certificate_type_id, patient_id) FROM stdin;
3		/path/to/uploads/Student Profile edited.docx	2025-04-19	1	1
52		/path/to/uploads/Screenshot 2025-05-01 at 1.48.49 PM.png	3422-03-22	1	1
53		/path/to/uploads/Screenshot 2025-04-30 at 9.57.19 AM.png	0022-02-22	3	2
\.


--
-- TOC entry 3647 (class 0 OID 41478)
-- Dependencies: 221
-- Data for Name: lab_reports; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lab_reports (certificate_id, test_results, test_type) FROM stdin;
53	asdfadfa	asdf
\.


--
-- TOC entry 3648 (class 0 OID 41485)
-- Dependencies: 222
-- Data for Name: patients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.patients (patient_id, address, admission_date, contact, dob, father_name, gender, husband_name, name) FROM stdin;
1	address	3333-03-23	12345	1998-02-23	parent	male	\N	patient 1
2	address	3333-03-23	12345	1998-02-23	parent	male	\N	patient 1
3	address	3333-03-23	12345	1998-02-23	parent	male	\N	patient 1
4	address	3333-03-23	12345	1998-02-23	parent	male	\N	patient 1
\.


--
-- TOC entry 3649 (class 0 OID 41492)
-- Dependencies: 223
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, email, password_hash, role, username) FROM stdin;
\.


--
-- TOC entry 3650 (class 0 OID 41499)
-- Dependencies: 224
-- Data for Name: vaccination_certificates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vaccination_certificates (certificate_id, dose_number, next_due_date, vaccine_name) FROM stdin;
\.


--
-- TOC entry 3659 (class 0 OID 0)
-- Dependencies: 225
-- Name: certificates_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.certificates_seq', 101, true);


--
-- TOC entry 3660 (class 0 OID 0)
-- Dependencies: 226
-- Name: patients_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.patients_seq', 51, true);


--
-- TOC entry 3661 (class 0 OID 0)
-- Dependencies: 227
-- Name: users_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_seq', 1, false);


--
-- TOC entry 3478 (class 2606 OID 41463)
-- Name: birth_certificates birth_certificates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.birth_certificates
    ADD CONSTRAINT birth_certificates_pkey PRIMARY KEY (certificate_id);


--
-- TOC entry 3480 (class 2606 OID 41470)
-- Name: certificate_types certificate_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.certificate_types
    ADD CONSTRAINT certificate_types_pkey PRIMARY KEY (certificate_type_id);


--
-- TOC entry 3484 (class 2606 OID 41477)
-- Name: certificates certificates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.certificates
    ADD CONSTRAINT certificates_pkey PRIMARY KEY (certificate_id);


--
-- TOC entry 3486 (class 2606 OID 41484)
-- Name: lab_reports lab_reports_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lab_reports
    ADD CONSTRAINT lab_reports_pkey PRIMARY KEY (certificate_id);


--
-- TOC entry 3488 (class 2606 OID 41491)
-- Name: patients patients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_pkey PRIMARY KEY (patient_id);


--
-- TOC entry 3482 (class 2606 OID 41507)
-- Name: certificate_types uk633r3rkn2eultu3irsnpeo01a; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.certificate_types
    ADD CONSTRAINT uk633r3rkn2eultu3irsnpeo01a UNIQUE (type_name);


--
-- TOC entry 3490 (class 2606 OID 41509)
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 3492 (class 2606 OID 41511)
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 3494 (class 2606 OID 41498)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3496 (class 2606 OID 41505)
-- Name: vaccination_certificates vaccination_certificates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vaccination_certificates
    ADD CONSTRAINT vaccination_certificates_pkey PRIMARY KEY (certificate_id);


--
-- TOC entry 3497 (class 2606 OID 41515)
-- Name: certificates fkdjt60pih8jxg789sqpq6tn91k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.certificates
    ADD CONSTRAINT fkdjt60pih8jxg789sqpq6tn91k FOREIGN KEY (certificate_type_id) REFERENCES public.certificate_types(certificate_type_id);


--
-- TOC entry 3498 (class 2606 OID 41520)
-- Name: certificates fkrmwp3n0q5kxg257whgmgjkde0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.certificates
    ADD CONSTRAINT fkrmwp3n0q5kxg257whgmgjkde0 FOREIGN KEY (patient_id) REFERENCES public.patients(patient_id);


-- Completed on 2025-05-01 15:44:48 IST

--
-- PostgreSQL database dump complete
--

