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

CREATE SEQUENCE IF NOT EXISTS public.discount_bonus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE IF NOT EXISTS public.discount_bonus_id_seq OWNER TO postgres;

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

CREATE SEQUENCE IF NOT EXISTS public.discount_reg_client_id_seq
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
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.orders (
    repair_code character varying(255) NOT NULL,
    bodywork character varying(255),
    engine character varying(255),
    plate character varying(255),
    disc_bonus real NOT NULL,
    total_amount real NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

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

CREATE SEQUENCE IF NOT EXISTS public.prices_id_seq
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

CREATE SEQUENCE IF NOT EXISTS public.repairs_id_seq
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

CREATE SEQUENCE IF NOT EXISTS public.surcharge_carage_id_seq
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

CREATE SEQUENCE IF NOT EXISTS public.surcharge_mileage_id_seq
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
PTKJ85	hatchback	hyundai	gas	28000	creta	4	2021
HHHH88	van	chevrolet	gas	18000	n400	4	2020
LSJD82	van	chevy	diesel	23456	n300	4	2020
KHEA18	pickup	nissan	gas	70000	qashqai	4	2021
DUKO66	sedan	chevrolet	diesel	66000	Camaro	2	2015
FEID33	suv	chevrolet	diesel	78500	RXF	4	2018
\.


--
-- Data for Name: discount_bonus; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.discount_bonus (id, bonus, brand, stock) FROM stdin;
4	10000	chevrolet	6
3	20000	nissan	2
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
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (repair_code, bodywork, engine, plate, disc_bonus, total_amount) FROM stdin;
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
54	van	Chevrolet	2024-05-02 16:04:49.97879	2024-05-06 16:04:49.97879	0	0	9000	gas	2024-05-03 16:04:49.97879	39330	12000	HHHH88	HHHH8812000	180000	8	0	27000.002	9000	246330
67	sedan	Chevrolet	2024-05-03 01:16:53.528849	2024-05-08 11:11:00	0	0	0	diesel	2024-05-03 10:00:00	29640	90000	DUKO66	DUKO6690000	120000	1	6000	30000	0	185640
41	van	Chevrolet	2024-05-02 10:06:04.829088	\N	0	13000	6500	gas	\N	22724	10000	HHHH88	HHHH8810000	130000	2	0	0	9100	142324
42	van	Chevrolet	2024-05-02 10:06:56.017391	\N	0	35000	17500	gas	\N	61180	10000	HHHH88	HHHH8810000	350000	3	0	0	24500	383180
44	van	Chevrolet	2024-05-02 10:12:39.758684	\N	0	15000	7500	gas	\N	26220	7000	HHHH88	HHHH887000	150000	5	0	0	10500	164220
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

SELECT pg_catalog.setval('public.discount_bonus_id_seq', 4, true);


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

SELECT pg_catalog.setval('public.repairs_id_seq', 91, true);


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
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (repair_code);


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

