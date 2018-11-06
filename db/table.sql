CREATE TABLE public.m_base_station
(
  base_station_id bigserial NOT NULL,
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

CREATE TABLE public.m_mobile
(
  mobile_id bigserial NOT NULL,
  uuid character varying(255),
  last_known_x integer DEFAULT 0,
  last_known_y integer DEFAULT 0,
  CONSTRAINT mobile_pkey PRIMARY KEY (mobile_id),
  CONSTRAINT mm_uuid_unique UNIQUE (uuid)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE public.t_bs_report_detail
(
  bs_report_detail_id bigserial NOT NULL,
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