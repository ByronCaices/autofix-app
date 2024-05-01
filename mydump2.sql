--
-- PostgreSQL database dump
--

-- Dumped from database version 12.18 (Ubuntu 12.18-1.pgdg22.04+1)
-- Dumped by pg_dump version 12.18 (Ubuntu 12.18-1.pgdg22.04+1)

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
-- Name: cars; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.cars (
    plate character varying(255) NOT NULL,
    bodywork character varying(255),
    brand character varying(255),
    engine character varying(255),
    mileage bigint,
    model character varying(255),
    seats integer,
    year integer
);


ALTER TABLE public.cars OWNER TO postgres;

--
-- Name: discount_bonus; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.discount_bonus (
    id bigint NOT NULL,
    bonus integer,
    brand character varying(255),
    stock integer
);


ALTER TABLE public.discount_bonus OWNER TO postgres;

--
-- Name: discount_bonus_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.discount_bonus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discount_bonus_id_seq OWNER TO postgres;

--
-- Name: discount_bonus_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.discount_bonus_id_seq OWNED BY public.discount_bonus.id;


--
-- Name: discount_reg_client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.discount_reg_client (
    id bigint NOT NULL,
    category character varying(255),
    discount real NOT NULL,
    engine character varying(255)
);


ALTER TABLE public.discount_reg_client OWNER TO postgres;

--
-- Name: discount_reg_client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.discount_reg_client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discount_reg_client_id_seq OWNER TO postgres;

--
-- Name: discount_reg_client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.discount_reg_client_id_seq OWNED BY public.discount_reg_client.id;


--
-- Name: prices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.prices (
    id bigint NOT NULL,
    engine character varying(255) NOT NULL,
    price integer NOT NULL,
    repair_type integer NOT NULL
);


ALTER TABLE public.prices OWNER TO postgres;

--
-- Name: prices_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prices_id_seq OWNER TO postgres;

--
-- Name: prices_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prices_id_seq OWNED BY public.prices.id;


--
-- Name: repairs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.repairs (
    id bigint NOT NULL,
    bodywork character varying(255),
    brand character varying(255),
    checkin_date timestamp(6) without time zone,
    checkout_date timestamp(6) without time zone,
    disc_bonus real NOT NULL,
    disc_mon_thu real NOT NULL,
    disc_reg_client real NOT NULL,
    engine character varying(255),
    finish_date timestamp(6) without time zone,
    iva real NOT NULL,
    mileage bigint,
    plate character varying(255),
    repair_code character varying(255),
    repair_price real NOT NULL,
    repair_type integer,
    surch_carage real NOT NULL,
    surch_delay real NOT NULL,
    surch_mileage real NOT NULL,
    total_amount real NOT NULL
);


ALTER TABLE public.repairs OWNER TO postgres;

--
-- Name: repairs_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.repairs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.repairs_id_seq OWNER TO postgres;

--
-- Name: repairs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.repairs_id_seq OWNED BY public.repairs.id;


--
-- Name: surcharge_carage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.surcharge_carage (
    id bigint NOT NULL,
    bodywork character varying(255),
    category character varying(255),
    surcharge real NOT NULL
);


ALTER TABLE public.surcharge_carage OWNER TO postgres;

--
-- Name: surcharge_carage_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.surcharge_carage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.surcharge_carage_id_seq OWNER TO postgres;

--
-- Name: surcharge_carage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.surcharge_carage_id_seq OWNED BY public.surcharge_carage.id;


--
-- Name: surcharge_mileage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.surcharge_mileage (
    id bigint NOT NULL,
    bodywork character varying(255),
    category character varying(255),
    surcharge real NOT NULL
);


ALTER TABLE public.surcharge_mileage OWNER TO postgres;

--
-- Name: surcharge_mileage_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.surcharge_mileage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.surcharge_mileage_id_seq OWNER TO postgres;

--
-- Name: surcharge_mileage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.surcharge_mileage_id_seq OWNED BY public.surcharge_mileage.id;


--
-- Name: discount_bonus id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_bonus ALTER COLUMN id SET DEFAULT nextval('public.discount_bonus_id_seq'::regclass);


--
-- Name: discount_reg_client id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_reg_client ALTER COLUMN id SET DEFAULT nextval('public.discount_reg_client_id_seq'::regclass);


--
-- Name: prices id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prices ALTER COLUMN id SET DEFAULT nextval('public.prices_id_seq'::regclass);


--
-- Name: repairs id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.repairs ALTER COLUMN id SET DEFAULT nextval('public.repairs_id_seq'::regclass);


--
-- Name: surcharge_carage id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surcharge_carage ALTER COLUMN id SET DEFAULT nextval('public.surcharge_carage_id_seq'::regclass);


--
-- Name: surcharge_mileage id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surcharge_mileage ALTER COLUMN id SET DEFAULT nextval('public.surcharge_mileage_id_seq'::regclass);


--
-- Data for Name: cars; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cars (plate, bodywork, brand, engine, mileage, model, seats, year) FROM stdin;
FFGH65	sedan	Nissan	diesel	23456	xfc	4	2020
PTKJ85	hatchback	hyundai	gas	28000	creta	4	2021
ABCD12	suv	MarcaA	diesel	12000	ModeloX	5	2021
EFGH34	sedan	MarcaB	gas	23000	ModeloY	5	2019
IJKL56	hatchback	MarcaC	hybrid	45000	ModeloZ	4	2018
MNOP78	van	MarcaD	electric	32000	ModeloW	7	2020
QRST90	suv	MarcaE	diesel	21000	ModeloV	5	2022
UVWX12	sedan	MarcaF	gas	10000	ModeloU	4	2023
YZAB34	hatchback	MarcaG	hybrid	5000	ModeloS	4	2024
LSJD82	van	chevy	diesel	23456	n300	4	2020
XXXX11	pickup	nissan	diesel	34567	qash	4	2015
HHHH88	van	Chevrolet	gas	10000	n400	4	2020
\.


--
-- Data for Name: discount_bonus; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.discount_bonus (id, bonus, brand, stock) FROM stdin;
1	22000	MarcaA	4
3	20000	nissan	3
\.


--
-- Data for Name: discount_reg_client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.discount_reg_client (id, category, discount, engine) FROM stdin;
1	A	0.05	gas
2	B	0.1	gas
3	C	0.15	gas
4	D	0.2	gas
5	A	0.07	diesel
6	B	0.12	diesel
7	C	0.17	diesel
8	D	0.22	diesel
9	A	0.1	hybrid
10	B	0.15	hybrid
11	C	0.2	hybrid
12	D	0.25	hybrid
13	A	0.08	electric
14	B	0.13	electric
15	C	0.18	electric
16	D	0.23	electric
\.


--
-- Data for Name: prices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prices (id, engine, price, repair_type) FROM stdin;
1	gas	120	1
2	gas	130	2
3	gas	350	3
4	gas	210	4
5	gas	150	5
6	gas	100	6
7	gas	100	7
8	gas	180	8
9	gas	150	9
10	gas	130	10
11	gas	80	11
12	diesel	120	1
13	diesel	130	2
14	diesel	450	3
15	diesel	210	4
16	diesel	150	5
17	diesel	120	6
18	diesel	100	7
19	diesel	180	8
20	diesel	150	9
21	diesel	140	10
22	diesel	80	11
23	hybrid	180	1
24	hybrid	190	2
25	hybrid	700	3
26	hybrid	300	4
27	hybrid	200	5
28	hybrid	450	6
29	hybrid	100	7
30	hybrid	210	8
31	hybrid	180	9
32	hybrid	220	10
33	hybrid	80	11
34	electric	220	1
35	electric	230	2
36	electric	800	3
37	electric	300	4
38	electric	250	5
39	electric	0	6
40	electric	100	7
41	electric	250	8
42	electric	180	9
43	electric	0	10
44	electric	80	11
\.


--
-- Data for Name: repairs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.repairs (id, bodywork, brand, checkin_date, checkout_date, disc_bonus, disc_mon_thu, disc_reg_client, engine, finish_date, iva, mileage, plate, repair_code, repair_price, repair_type, surch_carage, surch_delay, surch_mileage, total_amount) FROM stdin;
1	suv	MarcaA	2024-04-10 08:00:00	2024-04-16 09:00:00	0.25	0.1	0.25	diesel	2024-04-15 18:00:00	0	12100	ABCD12	\N	150	1	0.05	0.1	0.05	500
2	sedan	MarcaB	2024-04-11 09:00:00	2024-04-13 10:00:00	0.15	0.15	0.15	gas	2024-04-12 17:00:00	0	23150	EFGH34	\N	120	3	0.075	0.05	0.075	700
3	hatchback	MarcaC	2024-04-13 08:30:00	2024-04-20 11:00:00	0.5	0.2	0.5	hybrid	2024-04-19 16:30:00	0	45120	IJKL56	\N	180	5	0.1	0.2	0.1	1100
4	van	MarcaD	2024-04-14 10:00:00	2024-04-21 08:30:00	0.3	0.25	0.3	electric	2024-04-20 20:00:00	0	32250	MNOP78	\N	160	7	0.125	0.15	0.125	900
5	suv	MarcaE	2024-04-15 11:15:00	2024-04-19 09:45:00	0.75	0.3	0.75	diesel	2024-04-18 14:00:00	0	21150	QRST90	\N	200	2	0.15	0.25	0.15	750
6	sedan	MarcaF	2024-04-16 07:45:00	2024-04-23 10:00:00	0	0	0	gas	2024-04-22 18:15:00	0	10100	UVWX12	\N	130	4	0	0	0	450
7	hatchback	MarcaG	2024-04-17 09:00:00	2024-04-24 10:00:00	0.1	0.05	0.1	hybrid	2024-04-23 17:00:00	0	5200	YZAB34	\N	190	6	0.06	0.05	0.06	620
8	suv	MarcaA	2024-04-18 08:00:00	2024-04-25 09:00:00	0.125	0.1	0.125	diesel	2024-04-24 18:00:00	0	12300	ABCD12	\N	175	8	0.025	0.1	0.025	300
9	sedan	MarcaB	2024-04-19 09:00:00	2024-04-26 10:00:00	0.175	0.15	0.175	gas	2024-04-25 17:00:00	0	23450	EFGH34	\N	145	10	0.0375	0.1	0.0375	500
10	hatchback	MarcaC	2024-04-20 08:30:00	2024-04-27 11:00:00	0.2	0.2	0.2	hybrid	2024-04-26 16:30:00	0	45300	IJKL56	\N	110	11	0.04	0.15	0.04	600
19	van	Chevrolet	2024-04-28 03:02:06.788	2024-05-02 07:11:00	0	0	13000	gas	2024-04-29 06:30:00	25935	12345	HHHH88	HHHH8812345	130000	10	0	19500	0	162435
36	van	Chevrolet	2024-04-28 17:31:13.346	\N	0	0	0	gas	\N	27417	12345	HHHH88	HHHH8812345	130000	10	0	0	14300	171717
37	van	Chevrolet	2024-04-19 10:00:00	\N	0	0	0	gas	\N	25308	23456	HHHH88	HHHH8823456	120000	1	0	0	13200	158508
38	van	Chevrolet	2024-04-19 10:00:00	2024-04-24 10:00:00	0	0	6000	gas	2024-04-22 10:00:00	26448	23456	HHHH88	HHHH8823456	120000	1	0	12000	13200	165648
39	van	Chevrolet	2024-04-15 10:00:00	\N	0	12000	18000	gas	\N	18696	10000	HHHH88	HHHH8810000	120000	1	0	0	8400	117096
20	van	Chevrolet	2024-04-28 03:10:57.562	\N	0	0	35000	gas	\N	67165	12345	HHHH88	HHHH8812345	350000	3	0	0	38500	420665
32	van	Chevrolet	2024-04-28 03:52:42.095	\N	0	0	7500	gas	\N	30210	12345	HHHH88	HHHH8812345	150000	5	0	0	16500	189210
35	van	Chevrolet	2024-04-28 04:08:21.332	\N	0	0	5000	gas	\N	20140	12345	HHHH88	HHHH8812345	100000	6	0	0	11000	126140
\.


--
-- Data for Name: surcharge_carage; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.surcharge_carage (id, bodywork, category, surcharge) FROM stdin;
1	sedan	A	0
2	sedan	B	0.05
3	sedan	C	0.09
4	sedan	D	0.15
5	hatchback	A	0
6	hatchback	B	0.05
7	hatchback	C	0.09
8	hatchback	D	0.15
9	suv	A	0
10	suv	B	0.07
11	suv	C	0.11
12	suv	D	0.2
13	pickup	A	0
14	pickup	B	0.07
15	pickup	C	0.11
16	pickup	D	0.2
17	van	A	0
18	van	B	0.07
19	van	C	0.11
20	van	D	0.2
\.


--
-- Data for Name: surcharge_mileage; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.surcharge_mileage (id, bodywork, category, surcharge) FROM stdin;
1	sedan	A	0
2	sedan	B	0.03
3	sedan	C	0.07
4	sedan	D	0.12
5	sedan	E	0.2
6	hatchback	A	0
7	hatchback	B	0.03
8	hatchback	C	0.07
9	hatchback	D	0.12
10	hatchback	E	0.2
11	suv	A	0
12	suv	B	0.05
13	suv	C	0.09
14	suv	D	0.12
15	suv	E	0.2
16	pickup	A	0
17	pickup	B	0.05
18	pickup	C	0.09
19	pickup	D	0.12
20	pickup	E	0.2
21	van	A	0
22	van	B	0.05
23	van	C	0.09
24	van	D	0.12
25	van	E	0.2
\.


--
-- Name: discount_bonus_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.discount_bonus_id_seq', 3, true);


--
-- Name: discount_reg_client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.discount_reg_client_id_seq', 16, true);


--
-- Name: prices_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prices_id_seq', 44, true);


--
-- Name: repairs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.repairs_id_seq', 39, true);


--
-- Name: surcharge_carage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.surcharge_carage_id_seq', 20, true);


--
-- Name: surcharge_mileage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.surcharge_mileage_id_seq', 25, true);


--
-- Name: cars cars_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (plate);


--
-- Name: discount_bonus discount_bonus_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_bonus
    ADD CONSTRAINT discount_bonus_pkey PRIMARY KEY (id);


--
-- Name: discount_reg_client discount_reg_client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discount_reg_client
    ADD CONSTRAINT discount_reg_client_pkey PRIMARY KEY (id);


--
-- Name: prices prices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prices
    ADD CONSTRAINT prices_pkey PRIMARY KEY (id);


--
-- Name: repairs repairs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.repairs
    ADD CONSTRAINT repairs_pkey PRIMARY KEY (id);


--
-- Name: surcharge_carage surcharge_carage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surcharge_carage
    ADD CONSTRAINT surcharge_carage_pkey PRIMARY KEY (id);


--
-- Name: surcharge_mileage surcharge_mileage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.surcharge_mileage
    ADD CONSTRAINT surcharge_mileage_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

