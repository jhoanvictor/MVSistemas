create or replace package developer.pck_balanco is

  -- Author  : JHOAN
  -- Created : 01/02/2021 21:03:37
  -- Purpose : 

  type t_cursor is ref cursor;

  procedure registrar(p_conta     in developer.balanco.conta%type,
                      p_valor     in developer.balanco.valor%type,
                      p_descricao in developer.balanco.descricao%type);

end;
/
create or replace package body developer.pck_balanco is

  procedure registrar(p_conta     in developer.balanco.conta%type,
                      p_valor     in developer.balanco.valor%type,
                      p_descricao in developer.balanco.descricao%type) is

  begin
    insert into developer.balanco
      (valor, descricao, conta)
    values
      (p_valor, p_descricao, p_conta); 
  end;
end;
/
