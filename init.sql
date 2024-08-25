--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4 (Debian 16.4-1.pgdg120+1)
-- Dumped by pg_dump version 16.3

-- Started on 2024-08-25 16:35:41 UTC

--
-- TOC entry 223 (class 1259 OID 16429)
-- Name: bookings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bookings (
    id bigint NOT NULL,
    user_id bigint,
    car_id bigint,
    start_date date NOT NULL,
    end_date date NOT NULL,
    total_price double precision NOT NULL,
    status character varying(255) DEFAULT 'Pending'::character varying,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.bookings OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16428)
-- Name: bookings_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bookings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.bookings_id_seq OWNER TO postgres;

--
-- TOC entry 3405 (class 0 OID 0)
-- Dependencies: 222
-- Name: bookings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bookings_id_seq OWNED BY public.bookings.id;


--
-- TOC entry 215 (class 1259 OID 16394)
-- Name: car_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.car_seq OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16407)
-- Name: carmodels; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.carmodels (
    id bigint NOT NULL,
    brand character varying(100) NOT NULL,
    model character varying(100) NOT NULL,
    type character varying(50),
    seats integer,
    daily_rate double precision NOT NULL,
    image_url character varying(255),
    is_deleted boolean DEFAULT false
);


ALTER TABLE public.carmodels OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16406)
-- Name: carmodels_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.carmodels_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.carmodels_id_seq OWNER TO postgres;

--
-- TOC entry 3406 (class 0 OID 0)
-- Dependencies: 218
-- Name: carmodels_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.carmodels_id_seq OWNED BY public.carmodels.id;


--
-- TOC entry 221 (class 1259 OID 16414)
-- Name: cars; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cars (
    id bigint NOT NULL,
    model_id bigint,
    license_plate character varying(20) NOT NULL,
    color character varying(30),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    is_deleted boolean DEFAULT false
);


ALTER TABLE public.cars OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16413)
-- Name: cars_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cars_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cars_id_seq OWNER TO postgres;

--
-- TOC entry 3407 (class 0 OID 0)
-- Dependencies: 220
-- Name: cars_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cars_id_seq OWNED BY public.cars.id;


--
-- TOC entry 217 (class 1259 OID 16396)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    username character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    is_admin boolean DEFAULT false
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16395)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3408 (class 0 OID 0)
-- Dependencies: 216
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 3226 (class 2604 OID 16447)
-- Name: bookings id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings ALTER COLUMN id SET DEFAULT nextval('public.bookings_id_seq'::regclass);


--
-- TOC entry 3221 (class 2604 OID 16477)
-- Name: carmodels id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carmodels ALTER COLUMN id SET DEFAULT nextval('public.carmodels_id_seq'::regclass);


--
-- TOC entry 3223 (class 2604 OID 16493)
-- Name: cars id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cars ALTER COLUMN id SET DEFAULT nextval('public.cars_id_seq'::regclass);


--
-- TOC entry 3219 (class 2604 OID 16516)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3397 (class 0 OID 16429)
-- Dependencies: 223
-- Data for Name: bookings; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bookings (id, user_id, car_id, start_date, end_date, total_price, status, created_at) VALUES (1, 2, 3, '2024-09-10', '2024-09-15', 225, 'Confirmed', '2024-08-23 19:19:12.44504') ON CONFLICT DO NOTHING;
INSERT INTO public.bookings (id, user_id, car_id, start_date, end_date, total_price, status, created_at) VALUES (2, 3, 7, '2024-10-01', '2024-10-03', 165, 'Confirmed', '2024-08-23 19:19:12.44504') ON CONFLICT DO NOTHING;
INSERT INTO public.bookings (id, user_id, car_id, start_date, end_date, total_price, status, created_at) VALUES (3, 4, 2, '2024-10-10', '2024-10-12', 100, 'Cancelled', '2024-08-23 19:19:12.44504') ON CONFLICT DO NOTHING;
INSERT INTO public.bookings (id, user_id, car_id, start_date, end_date, total_price, status, created_at) VALUES (4, 2, 5, '2024-09-20', '2024-09-25', 300, 'Pending', '2024-08-23 19:19:12.44504') ON CONFLICT DO NOTHING;
INSERT INTO public.bookings (id, user_id, car_id, start_date, end_date, total_price, status, created_at) VALUES (5, 3, 4, '2024-09-12', '2024-09-17', 225, 'Confirmed', '2024-08-23 19:19:12.44504') ON CONFLICT DO NOTHING;
INSERT INTO public.bookings (id, user_id, car_id, start_date, end_date, total_price, status, created_at) VALUES (6, 4, 3, '2024-09-16', '2024-09-20', 225, 'Pending', '2024-08-24 16:16:43.423332') ON CONFLICT DO NOTHING;
INSERT INTO public.bookings (id, user_id, car_id, start_date, end_date, total_price, status, created_at) VALUES (7, 2, 3, '2024-09-16', '2024-09-20', 225, 'Cancelled', '2024-08-24 16:10:44.12713') ON CONFLICT DO NOTHING;
INSERT INTO public.bookings (id, user_id, car_id, start_date, end_date, total_price, status, created_at) VALUES (8, 3, 7, '2024-08-24', '2024-08-31', 440, 'Pending', '2024-08-24 18:56:53.182719') ON CONFLICT DO NOTHING;

--
-- TOC entry 3393 (class 0 OID 16407)
-- Dependencies: 219
-- Data for Name: carmodels; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.carmodels (id, brand, model, type, seats, daily_rate, image_url, is_deleted) VALUES (1, 'Toyota', 'Camry', 'Sedan', 5, 50, 'https://www.toyota.com.my/content/dam/malaysia/models/camry/overview/toyota-my-camry-overview-1200x1200.jpg', false) ON CONFLICT DO NOTHING;
INSERT INTO public.carmodels (id, brand, model, type, seats, daily_rate, image_url, is_deleted) VALUES (2, 'Honda', 'Civic', 'Sedan', 5, 45, 'https://www.log.com.tr/wp-content/uploads/2021/11/yeni-honda-civic-3-300x169.jpg', false) ON CONFLICT DO NOTHING;
INSERT INTO public.carmodels (id, brand, model, type, seats, daily_rate, image_url, is_deleted) VALUES (3, 'Ford', 'Escape', 'SUV', 5, 60, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTF_OOEwp0FQLQP11Ez5tnqvudih6ZkvjrPYg&s', false) ON CONFLICT DO NOTHING;
INSERT INTO public.carmodels (id, brand, model, type, seats, daily_rate, image_url, is_deleted) VALUES (4, 'Chevrolet', 'Malibu', 'Sedan', 5, 55, 'https://hips.hearstapps.com/hmg-prod/images/2019-chevrolet-malibu-rs-117-1568289288.jpg?crop=0.720xw:0.882xh;0.139xw,0.0916xh&resize=768:*', false) ON CONFLICT DO NOTHING;
INSERT INTO public.carmodels (id, brand, model, type, seats, daily_rate, image_url, is_deleted) VALUES (5, 'Toyota', 'Corolla', 'Sedan', 5, 50, NULL, false) ON CONFLICT DO NOTHING;



--
-- TOC entry 3395 (class 0 OID 16414)
-- Dependencies: 221
-- Data for Name: cars; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cars (id, model_id, license_plate, color, created_at, is_deleted) VALUES (1, 1, 'ABC-1234', 'White', '2024-08-23 19:18:10.491706', false) ON CONFLICT DO NOTHING;
INSERT INTO public.cars (id, model_id, license_plate, color, created_at, is_deleted) VALUES (2, 1, 'DEF-5678', 'Black', '2024-08-23 19:18:10.491706', false) ON CONFLICT DO NOTHING;
INSERT INTO public.cars (id, model_id, license_plate, color, created_at, is_deleted) VALUES (3, 2, 'GHI-9012', 'Red', '2024-08-23 19:18:10.491706', false) ON CONFLICT DO NOTHING;
INSERT INTO public.cars (id, model_id, license_plate, color, created_at, is_deleted) VALUES (4, 2, 'JKL-3456', 'Blue', '2024-08-23 19:18:10.491706', false) ON CONFLICT DO NOTHING;
INSERT INTO public.cars (id, model_id, license_plate, color, created_at, is_deleted) VALUES (5, 3, 'MNO-7890', 'Silver', '2024-08-23 19:18:10.491706', false) ON CONFLICT DO NOTHING;
INSERT INTO public.cars (id, model_id, license_plate, color, created_at, is_deleted) VALUES (6, 3, 'PQR-1234', 'Gray', '2024-08-23 19:18:10.491706', false) ON CONFLICT DO NOTHING;
INSERT INTO public.cars (id, model_id, license_plate, color, created_at, is_deleted) VALUES (7, 4, 'STU-5678', 'Green', '2024-08-23 19:18:10.491706', false) ON CONFLICT DO NOTHING;
INSERT INTO public.cars (id, model_id, license_plate, color, created_at, is_deleted) VALUES (8, 4, 'VWX-9012', 'Black', '2024-08-23 19:18:10.491706', false) ON CONFLICT DO NOTHING;


--
-- TOC entry 3391 (class 0 OID 16396)
-- Dependencies: 217
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, username, email, password, is_admin) VALUES (1, 'Admin', 'admin@example.com', '$2a$10$yQb.efdWUqZNiOQRLSuZVOawlbT9Z1uvXQjlv0nzbNDYk5UV83JNa', true) ON CONFLICT DO NOTHING;
INSERT INTO public.users (id, username, email, password, is_admin) VALUES (2, 'User1', 'user@example.com', '$2a$10$yQb.efdWUqZNiOQRLSuZVOawlbT9Z1uvXQjlv0nzbNDYk5UV83JNa', false) ON CONFLICT DO NOTHING;
INSERT INTO public.users (id, username, email, password, is_admin) VALUES (3, 'User2', 'user2@example.com', '$2a$10$yQb.efdWUqZNiOQRLSuZVOawlbT9Z1uvXQjlv0nzbNDYk5UV83JNa', false) ON CONFLICT DO NOTHING;
INSERT INTO public.users (id, username, email, password, is_admin) VALUES (4, 'User3', 'user3@example.com', '$2a$10$yQb.efdWUqZNiOQRLSuZVOawlbT9Z1uvXQjlv0nzbNDYk5UV83JNa', false) ON CONFLICT DO NOTHING;

--
-- TOC entry 3409 (class 0 OID 0)
-- Dependencies: 222
-- Name: bookings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bookings_id_seq', 8, true);


--
-- TOC entry 3410 (class 0 OID 0)
-- Dependencies: 215
-- Name: car_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.car_seq', 1, false);


--
-- TOC entry 3411 (class 0 OID 0)
-- Dependencies: 218
-- Name: carmodels_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.carmodels_id_seq', 5, true);


--
-- TOC entry 3412 (class 0 OID 0)
-- Dependencies: 220
-- Name: cars_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cars_id_seq', 8, true);


--
-- TOC entry 3413 (class 0 OID 0)
-- Dependencies: 216
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 4, true);


--
-- TOC entry 3242 (class 2606 OID 16449)
-- Name: bookings bookings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (id);


--
-- TOC entry 3236 (class 2606 OID 16479)
-- Name: carmodels carmodels_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carmodels
    ADD CONSTRAINT carmodels_pkey PRIMARY KEY (id);


--
-- TOC entry 3238 (class 2606 OID 16422)
-- Name: cars cars_license_plate_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_license_plate_key UNIQUE (license_plate);


--
-- TOC entry 3240 (class 2606 OID 16495)
-- Name: cars cars_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);


--
-- TOC entry 3230 (class 2606 OID 16405)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 3232 (class 2606 OID 16518)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3234 (class 2606 OID 16403)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- TOC entry 3244 (class 2606 OID 16496)
-- Name: bookings bookings_car_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_car_id_fkey FOREIGN KEY (car_id) REFERENCES public.cars(id) ON DELETE CASCADE;


--
-- TOC entry 3245 (class 2606 OID 16519)
-- Name: bookings bookings_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- TOC entry 3243 (class 2606 OID 16506)
-- Name: cars cars_model_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_model_id_fkey FOREIGN KEY (model_id) REFERENCES public.carmodels(id) ON DELETE CASCADE;


-- Completed on 2024-08-25 16:35:41 UTC

--
-- PostgreSQL database dump complete
--

