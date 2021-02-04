-- Create table
create table ENDERECO
(
  cep         VARCHAR2(8) not null,
  logradouro  VARCHAR2(255) not null,
  numero      NUMBER,
  complemento VARCHAR2(255),
  bairro      VARCHAR2(255) not null,
  cidade      VARCHAR2(100) not null,
  uf          VARCHAR2(2) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create unique index ENDERECO_CEP_UINDEX on ENDERECO (CEP)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table ENDERECO
  add constraint ENDERECO_PK primary key (CEP);
