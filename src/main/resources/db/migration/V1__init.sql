--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.12
-- Dumped by pg_dump version 9.5.12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: about; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.about (
    id bigint NOT NULL,
    creation_date bytea,
    modified_date bytea,
    about character varying(255)
);


ALTER TABLE public.about OWNER TO postgres;

--
-- Name: about_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.about_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.about_id_seq OWNER TO postgres;

--
-- Name: about_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.about_id_seq OWNED BY public.about.id;


--
-- Name: admin_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin_user (
    id bigint NOT NULL
);


ALTER TABLE public.admin_user OWNER TO postgres;

--
-- Name: answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answer (
    id bigint NOT NULL,
    creation_date bytea,
    modified_date bytea
);


ALTER TABLE public.answer OWNER TO postgres;

--
-- Name: answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.answer_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.answer_id_seq OWNER TO postgres;

--
-- Name: answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.answer_id_seq OWNED BY public.answer.id;


--
-- Name: fill_gap_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fill_gap_answer (
    comma_seprated_answer character varying(255),
    id bigint NOT NULL,
    question_id bigint
);


ALTER TABLE public.fill_gap_answer OWNER TO postgres;

--
-- Name: menue; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menue (
    id bigint NOT NULL,
    creation_date bytea,
    modified_date bytea,
    disabled boolean DEFAULT false,
    name character varying(255),
    url character varying(255)
);


ALTER TABLE public.menue OWNER TO postgres;

--
-- Name: menue_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.menue_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.menue_id_seq OWNER TO postgres;

--
-- Name: menue_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.menue_id_seq OWNED BY public.menue.id;


--
-- Name: multi_choice_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.multi_choice_answer (
    answer character varying(255),
    correct boolean,
    id bigint NOT NULL,
    question_id bigint
);


ALTER TABLE public.multi_choice_answer OWNER TO postgres;

--
-- Name: prize; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prize (
    id bigint NOT NULL,
    creation_date bytea,
    modified_date bytea,
    description character varying(255)
);


ALTER TABLE public.prize OWNER TO postgres;

--
-- Name: prize_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prize_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.prize_id_seq OWNER TO postgres;

--
-- Name: prize_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prize_id_seq OWNED BY public.prize.id;


--
-- Name: question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    creation_date bytea,
    modified_date bytea,
    question character varying(255),
    title character varying(255),
    video_path character varying(255),
    subject_id bigint
);


ALTER TABLE public.question OWNER TO postgres;

--
-- Name: question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.question_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.question_id_seq OWNER TO postgres;

--
-- Name: question_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.question_id_seq OWNED BY public.question.id;


--
-- Name: rahmania_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rahmania_role (
    id bigint NOT NULL,
    creation_date bytea,
    modified_date bytea,
    name character varying(255)
);


ALTER TABLE public.rahmania_role OWNER TO postgres;

--
-- Name: rahmania_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rahmania_role_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.rahmania_role_id_seq OWNER TO postgres;

--
-- Name: rahmania_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rahmania_role_id_seq OWNED BY public.rahmania_role.id;


--
-- Name: rahmania_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rahmania_user (
    id bigint NOT NULL,
    creation_date bytea,
    modified_date bytea,
    active boolean NOT NULL,
    email character varying(255),
    last_forget_password bytea,
    mobile_number character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    token character varying(255),
    role_id bigint
);


ALTER TABLE public.rahmania_user OWNER TO postgres;

--
-- Name: rahmania_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rahmania_user_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.rahmania_user_id_seq OWNER TO postgres;

--
-- Name: rahmania_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rahmania_user_id_seq OWNED BY public.rahmania_user.id;


--
-- Name: role_menue; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role_menue (
    role_id bigint NOT NULL,
    menue_id bigint NOT NULL
);


ALTER TABLE public.role_menue OWNER TO postgres;

--
-- Name: rule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rule (
    id bigint NOT NULL,
    creation_date bytea,
    modified_date bytea,
    description character varying(255)
);


ALTER TABLE public.rule OWNER TO postgres;

--
-- Name: rule_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rule_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.rule_id_seq OWNER TO postgres;

--
-- Name: rule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rule_id_seq OWNED BY public.rule.id;


--
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    candidate boolean NOT NULL,
    elimnated boolean NOT NULL,
    grade bigint,
    participated boolean NOT NULL,
    student_saved_answers text,
    id bigint NOT NULL
);


ALTER TABLE public.student OWNER TO postgres;

--
-- Name: subject; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subject (
    id bigint NOT NULL,
    creation_date bytea,
    modified_date bytea,
    name character varying(255),
    video_name character varying(255),
    video_path character varying(255)
);


ALTER TABLE public.subject OWNER TO postgres;

--
-- Name: subject_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.subject_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.subject_id_seq OWNER TO postgres;

--
-- Name: subject_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subject_id_seq OWNED BY public.subject.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.about ALTER COLUMN id SET DEFAULT nextval('public.about_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answer ALTER COLUMN id SET DEFAULT nextval('public.answer_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menue ALTER COLUMN id SET DEFAULT nextval('public.menue_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prize ALTER COLUMN id SET DEFAULT nextval('public.prize_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question ALTER COLUMN id SET DEFAULT nextval('public.question_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rahmania_role ALTER COLUMN id SET DEFAULT nextval('public.rahmania_role_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rahmania_user ALTER COLUMN id SET DEFAULT nextval('public.rahmania_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rule ALTER COLUMN id SET DEFAULT nextval('public.rule_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject ALTER COLUMN id SET DEFAULT nextval('public.subject_id_seq'::regclass);


--
-- Data for Name: about; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.about (id, creation_date, modified_date, about) FROM stdin;
\.


--
-- Name: about_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.about_id_seq', 1, false);


--
-- Data for Name: admin_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admin_user (id) FROM stdin;
1
\.


--
-- Data for Name: answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.answer (id, creation_date, modified_date) FROM stdin;
\.


--
-- Name: answer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.answer_id_seq', 1, false);


--
-- Data for Name: fill_gap_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fill_gap_answer (comma_seprated_answer, id, question_id) FROM stdin;
\.


--
-- Data for Name: menue; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.menue (id, creation_date, modified_date, disabled, name, url) FROM stdin;
1	\N	\N	f	الرئيسية	/
2	\N	\N	f	نبذة عن المسابقة	/about
3	\N	\N	f	شروط المسابقة	/constraints
4	\N	\N	f	كتيب المسابقة	/book
5	\N	\N	f	مواد المسابقة	/subjects
6	\N	\N	f	جوائز المسابقة	/prizes
7	\N	\N	f	أسئلة المسابقة	/test
8	\N	\N	f	التصحيح	/correct
9	\N	\N	f	مواد المسابقة	/editSubjects
10	\N	\N	f	شروط المسابقة	/editConstraint
300	\N	\N	f	اعداد جوائز المسابقة	/editPrizes
301	\N	\N	f	اعداد النبذة 	/editAbout
\.


--
-- Name: menue_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.menue_id_seq', 1, false);


--
-- Data for Name: multi_choice_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.multi_choice_answer (answer, correct, id, question_id) FROM stdin;
\.


--
-- Data for Name: prize; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prize (id, creation_date, modified_date, description) FROM stdin;
\.


--
-- Name: prize_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prize_id_seq', 1, false);


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.question (type, id, creation_date, modified_date, question, title, video_path, subject_id) FROM stdin;
\.


--
-- Name: question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.question_id_seq', 1, false);


--
-- Data for Name: rahmania_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rahmania_role (id, creation_date, modified_date, name) FROM stdin;
1	\N	\N	admin
2	\N	\N	student
3	\N	\N	anonymous
\.


--
-- Name: rahmania_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rahmania_role_id_seq', 1, false);


--
-- Data for Name: rahmania_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rahmania_user (id, creation_date, modified_date, active, email, last_forget_password, mobile_number, name, password, token, role_id) FROM stdin;
1	\N	\N	t	\N	\N	0567926657	بهاء احمد بيومي	$2a$10$m550MS8fu7jUgpQKZbSzU.I5EPGeKps.8CN4zM8LJxg2ViyJOeIfG	\N	1
\.


--
-- Name: rahmania_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rahmania_user_id_seq', 1, false);


--
-- Data for Name: role_menue; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role_menue (role_id, menue_id) FROM stdin;
1	9
1	10
1	8
2	1
2	2
2	3
2	4
2	5
2	6
2	7
3	1
3	2
3	3
3	6
1	301
1	300
\.


--
-- Data for Name: rule; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rule (id, creation_date, modified_date, description) FROM stdin;
\.


--
-- Name: rule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rule_id_seq', 1, false);


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (candidate, elimnated, grade, participated, student_saved_answers, id) FROM stdin;
\.


--
-- Data for Name: subject; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subject (id, creation_date, modified_date, name, video_name, video_path) FROM stdin;
\.


--
-- Name: subject_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subject_id_seq', 1, false);


--
-- Name: about_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.about
    ADD CONSTRAINT about_pkey PRIMARY KEY (id);


--
-- Name: admin_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_user
    ADD CONSTRAINT admin_user_pkey PRIMARY KEY (id);


--
-- Name: answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (id);


--
-- Name: fill_gap_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fill_gap_answer
    ADD CONSTRAINT fill_gap_answer_pkey PRIMARY KEY (id);


--
-- Name: menue_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menue
    ADD CONSTRAINT menue_pkey PRIMARY KEY (id);


--
-- Name: multi_choice_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multi_choice_answer
    ADD CONSTRAINT multi_choice_answer_pkey PRIMARY KEY (id);


--
-- Name: prize_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prize
    ADD CONSTRAINT prize_pkey PRIMARY KEY (id);


--
-- Name: question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- Name: rahmania_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rahmania_role
    ADD CONSTRAINT rahmania_role_pkey PRIMARY KEY (id);


--
-- Name: rahmania_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rahmania_user
    ADD CONSTRAINT rahmania_user_pkey PRIMARY KEY (id);


--
-- Name: role_menue_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_menue
    ADD CONSTRAINT role_menue_pkey PRIMARY KEY (role_id, menue_id);


--
-- Name: rule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rule
    ADD CONSTRAINT rule_pkey PRIMARY KEY (id);


--
-- Name: student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- Name: subject_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject
    ADD CONSTRAINT subject_pkey PRIMARY KEY (id);


--
-- Name: uk_d219l8ubcf9pwx09go7av12t5; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rahmania_user
    ADD CONSTRAINT uk_d219l8ubcf9pwx09go7av12t5 UNIQUE (mobile_number);


--
-- Name: uk_p1jgir6qcpmqnxt4a8105wsot; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subject
    ADD CONSTRAINT uk_p1jgir6qcpmqnxt4a8105wsot UNIQUE (name);


--
-- Name: fk1flclsmqt8h9adrfdg8crr5p5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fill_gap_answer
    ADD CONSTRAINT fk1flclsmqt8h9adrfdg8crr5p5 FOREIGN KEY (id) REFERENCES public.answer(id);


--
-- Name: fk8hrfsdvev1asbsa4ri4sb2cki; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multi_choice_answer
    ADD CONSTRAINT fk8hrfsdvev1asbsa4ri4sb2cki FOREIGN KEY (question_id) REFERENCES public.question(id);


--
-- Name: fkbb7ci1anjapenq3vnwnfwksca; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_menue
    ADD CONSTRAINT fkbb7ci1anjapenq3vnwnfwksca FOREIGN KEY (role_id) REFERENCES public.rahmania_role(id);


--
-- Name: fkc085ydubwecop7x874h4qgfo9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multi_choice_answer
    ADD CONSTRAINT fkc085ydubwecop7x874h4qgfo9 FOREIGN KEY (id) REFERENCES public.answer(id);


--
-- Name: fkehcc73aiiiighafwuo7x9fskm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_menue
    ADD CONSTRAINT fkehcc73aiiiighafwuo7x9fskm FOREIGN KEY (menue_id) REFERENCES public.menue(id);


--
-- Name: fkkfvh71q42645g7p9cgxjygbgc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT fkkfvh71q42645g7p9cgxjygbgc FOREIGN KEY (subject_id) REFERENCES public.subject(id);


--
-- Name: fkkvs2x5d4ndt0x62yxv6hc12jj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_user
    ADD CONSTRAINT fkkvs2x5d4ndt0x62yxv6hc12jj FOREIGN KEY (id) REFERENCES public.rahmania_user(id);


--
-- Name: fklkvhkpaawk378fl5td1cyqlx7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rahmania_user
    ADD CONSTRAINT fklkvhkpaawk378fl5td1cyqlx7 FOREIGN KEY (role_id) REFERENCES public.rahmania_role(id);


--
-- Name: fko9u2di3t5n6jces6v54tps5ql; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fill_gap_answer
    ADD CONSTRAINT fko9u2di3t5n6jces6v54tps5ql FOREIGN KEY (question_id) REFERENCES public.question(id);


--
-- Name: fkor3cmwomnsc4bbeax3nd081i0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fkor3cmwomnsc4bbeax3nd081i0 FOREIGN KEY (id) REFERENCES public.rahmania_user(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
