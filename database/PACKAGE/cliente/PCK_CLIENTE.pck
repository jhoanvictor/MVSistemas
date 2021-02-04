create or replace package developer.PCK_CLIENTE is

  -- Author  : JHOAN
  -- Created : 04/02/2021 15:03:14
  -- Purpose : 

  type t_cursor is ref cursor;

  procedure buscarCliente(p_cliente in developer.cliente.documento%type,
                          p_result  out t_cursor);

  procedure salvarCliente(p_documento         in developer.cliente.documento%type,
                          p_nomeRazao         in varchar2,
                          p_sobrenomeFantasia in varchar2,
                          p_cep               in developer.cliente.cep%type,
                          p_telefone          in developer.cliente.telefone%type,
                          p_tipoCliente       in developer.cliente.tipo_cliente%type);

end PCK_CLIENTE;
/
create or replace package body developer.PCK_CLIENTE is

  procedure buscarCliente(p_cliente in developer.cliente.documento%type,
                          p_result  out t_cursor) is
  
    v_documento         developer.cliente.documento%type;
    v_nomeRazao         varchar2(100);
    v_sobrenomeFantasia varchar2(100);
    v_cep               developer.cliente.cep%type;
    v_telefone          developer.cliente.telefone%type;
    v_tipoCliente       developer.cliente.tipo_cliente%type;
  
  begin
  
    begin
      select p.documento, p.cep, p.telefone, p.tipo_cliente
        into v_documento, v_cep, v_telefone, v_tipoCliente
        from developer.cliente p
       where p.documento = p_cliente;
    end;
  
    begin
      if (v_tipoCliente = 1) then
        select p.nome, p.sobrenome
          into v_nomeRazao, v_sobrenomeFantasia
          from developer.cliente_pf p
         where p.cpf = v_documento;
      else
        select p.razaosocial, p.nomefantasia
          into v_nomeRazao, v_sobrenomeFantasia
          from developer.cliente_pj p
         where p.cnpj = v_documento;
      end if;
    end;
  
    open p_result for
      select v_documento         as documento,
             v_nomeRazao         as nomeRazao,
             v_sobrenomeFantasia as sobrenomeFantasia,
             v_cep               as cep,
             v_telefone          as telefone,
             v_tipoCliente       as tipoCliente
        from dual;
  
  end;

  procedure salvarCliente(p_documento         in developer.cliente.documento%type,
                          p_nomeRazao         in varchar2,
                          p_sobrenomeFantasia in varchar2,
                          p_cep               in developer.cliente.cep%type,
                          p_telefone          in developer.cliente.telefone%type,
                          p_tipoCliente       in developer.cliente.tipo_cliente%type) is
  
  begin
    insert into developer.cliente
      (documento, cep, telefone, tipo_cliente)
    values
      (p_documento, p_cep, p_telefone, p_tipoCliente);
  
    begin
      if (p_tipoCliente = 1) then
        insert into developer.cliente_pf
          (cpf, nome, sobrenome)
        values
          (p_documento, p_nomeRazao, p_sobrenomeFantasia);
      else
        insert into developer.cliente_pj
          (cnpj, razaosocial, nomefantasia)
        values
          (p_documento, p_nomeRazao, p_sobrenomeFantasia);
      end if;
    end;
  
  end;

end;
/
