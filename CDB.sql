PGDMP         ;                z           Casotto    14.4    14.4                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16741    Casotto    DATABASE     e   CREATE DATABASE "Casotto" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';
    DROP DATABASE "Casotto";
                postgres    false            ?            1259    16848    attrezzatura    TABLE     o   CREATE TABLE public.attrezzatura (
    postocodice integer NOT NULL,
    tipo_attrez character varying(255)
);
     DROP TABLE public.attrezzatura;
       public         heap    postgres    false            ?            1259    16749    bibita    TABLE     ?   CREATE TABLE public.bibita (
    id character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    isalcolic boolean NOT NULL,
    quantita integer,
    prezzo real
);
    DROP TABLE public.bibita;
       public         heap    postgres    false            ?            1259    16852    gioco    TABLE     ?   CREATE TABLE public.gioco (
    id text NOT NULL,
    nome text NOT NULL,
    prezzo double precision NOT NULL,
    maxpartecipa integer
);
    DROP TABLE public.gioco;
       public         heap    postgres    false            ?            1259    16742    piatto    TABLE     ?   CREATE TABLE public.piatto (
    id text NOT NULL,
    nome text NOT NULL,
    prezzo real NOT NULL,
    portata text NOT NULL,
    disponibili integer
);
ALTER TABLE ONLY public.piatto ALTER COLUMN portata SET STORAGE PLAIN;
    DROP TABLE public.piatto;
       public         heap    postgres    false            ?            1259    16859    sport    TABLE     }   CREATE TABLE public.sport (
    "ID" text NOT NULL,
    "Nome" text NOT NULL,
    "Prezzo" real,
    "MaxPersone" integer
);
    DROP TABLE public.sport;
       public         heap    postgres    false                       0    16848    attrezzatura 
   TABLE DATA           @   COPY public.attrezzatura (postocodice, tipo_attrez) FROM stdin;
    public          postgres    false    211   ?       ?          0    16749    bibita 
   TABLE DATA           G   COPY public.bibita (id, nome, isalcolic, quantita, prezzo) FROM stdin;
    public          postgres    false    210                    0    16852    gioco 
   TABLE DATA           ?   COPY public.gioco (id, nome, prezzo, maxpartecipa) FROM stdin;
    public          postgres    false    212   ?       ?          0    16742    piatto 
   TABLE DATA           H   COPY public.piatto (id, nome, prezzo, portata, disponibili) FROM stdin;
    public          postgres    false    209   ?                 0    16859    sport 
   TABLE DATA           E   COPY public.sport ("ID", "Nome", "Prezzo", "MaxPersone") FROM stdin;
    public          postgres    false    213   ~       r           2606    16865    sport Sport_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.sport
    ADD CONSTRAINT "Sport_pkey" PRIMARY KEY ("ID");
 <   ALTER TABLE ONLY public.sport DROP CONSTRAINT "Sport_pkey";
       public            postgres    false    213            n           2606    16755    bibita bibita_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.bibita
    ADD CONSTRAINT bibita_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.bibita DROP CONSTRAINT bibita_pkey;
       public            postgres    false    210            p           2606    16858    gioco gioco_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.gioco
    ADD CONSTRAINT gioco_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.gioco DROP CONSTRAINT gioco_pkey;
       public            postgres    false    212            l           2606    16867    piatto piatto_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.piatto
    ADD CONSTRAINT piatto_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.piatto DROP CONSTRAINT piatto_pkey;
       public            postgres    false    209                   x?????? ? ?      ?   w   x??M? ??;' ???]Z?&=???Z*?B%????L2Ì??????A??`=??SnM?$)U0??-?K>?le?4????\Q???F???q}69Dݥl??ܭu??ᡉ?"??         K   x?30?????/????4ҳ??4?20??/?K?WH?Tp*-*JL??4?42?20?t?L??L?4?3?43?????? r0?      ?   ?   x?-?;
?0??S?B????"??H??D"P?FV??>?{0?<kn?Z????1̄???ࢬs??l4?J?S?Ͳ???\??|??L:f?tK)?͌3??Q???u??_YF??
???(?¤F)?)?         >   x?30?tN?I???44?44?20?tJ,?N-?4?ps%&'g?????er?r?p??qqq v     