CREATE TABLE public.m_base_station
(
  base_station_id bigint NOT NULL DEFAULT nextval('m_base_station_base_station_id_seq'::regclass),
  uuid character varying(255),
  name character varying(255),
  x_value integer DEFAULT 0,
  y_value integer DEFAULT 0,
  detection_radius double precision DEFAULT 0.0,
  CONSTRAINT base_station_id_pkey PRIMARY KEY (base_station_id),
  CONSTRAINT mbs_id_unique UNIQUE (uuid)
)
WITH (
  OIDS=FALSE
);

INSERT INTO public.m_base_station( uuid, name, x_value, y_value, detection_radius)
    VALUES 	('b1', 'base Station 1', 4, 8, 20),
	   		('b2', 'base Station 2', 11, 7, 30),
	  		('b3', 'base Station 3', 16, 17, 35);


CREATE TABLE public.m_mobile
(
  mobile_id bigint NOT NULL DEFAULT nextval('mobile_mobile_id_seq'::regclass),
  uuid character varying(255),
  last_known_x integer DEFAULT 0,
  last_known_y integer DEFAULT 0,
  CONSTRAINT mobile_pkey PRIMARY KEY (mobile_id),
  CONSTRAINT mm_uuid_unique UNIQUE (uuid)
)
WITH (
  OIDS=FALSE
);

INSERT INTO public.m_mobile(uuid, last_known_x, last_known_y)
    VALUES 	('m1', 0, 0),
	   		('m2', 0, 0);

CREATE TABLE public.t_bs_report_detail
(
  bs_report_detail_id bigint NOT NULL DEFAULT nextval('t_bs_report_detail_bs_report_detail_id_seq'::regclass),
  base_station_id bigint NOT NULL,
  mobile_id bigint NOT NULL,
  distance double precision DEFAULT 0.0,
  "timestamp" timestamp without time zone,
  CONSTRAINT bs_report_detail_id_pkey PRIMARY KEY (bs_report_detail_id),
  CONSTRAINT fk_mbs_tbrd_base_station_id FOREIGN KEY (base_station_id)
      REFERENCES public.m_base_station (base_station_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_mm_tbrd_mobile_id FOREIGN KEY (mobile_id)
      REFERENCES public.m_mobile (mobile_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

INSERT INTO public.t_bs_report_detail(base_station_id, mobile_id, distance, "timestamp")
    VALUES 	(1, 2, 5, '2018-10-03 01:01:12'),
	    	(2, 2, 5, '2018-10-03 01:01:12'),
	    	(3, 2, 10, '2018-10-03 01:01:12');