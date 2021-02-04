create or replace package developer.pck_endereco is

  -- Author  : JHOAN
  -- Created : 01/02/2021 19:16:01
  -- Purpose : 

  type t_cursor is ref cursor;

  procedure buscar(p_cep     in developer.endereco.cep%type,
                   p_result out t_cursor);

  procedure salvar(p_cep         in developer.endereco.cep%type,
                   p_logradouro  in developer.endereco.logradouro%type,
                   p_numero      in developer.endereco.numero%type,
                   p_complemento in developer.endereco.complemento%type,
                   p_bairro      in developer.endereco.bairro%type,
                   p_cidade      in developer.endereco.cidade%type,
                   p_uf          in developer.endereco.uf%type,
                   p_result      out t_cursor);

  procedure atualizar(p_cep         in developer.endereco.cep%type,
                      p_logradouro  in developer.endereco.logradouro%type,
                      p_numero      in developer.endereco.numero%type,
                      p_complemento in developer.endereco.complemento%type,
                      p_result      out t_cursor);

end;
/
create or replace package body developer.pck_endereco is

  procedure buscar(p_cep     in developer.endereco.cep%type,
                   p_result out t_cursor) is
  begin
    open p_result for
      select en.cep,
             en.logradouro,
             en.numero,
             en.complemento,
             en.bairro,
             en.uf,
             en.cidade
        from developer.endereco en
       where en.cep = p_cep;
  
  end;

  procedure salvar(p_cep         in developer.endereco.cep%type,
                   p_logradouro  in developer.endereco.logradouro%type,
                   p_numero      in developer.endereco.numero%type,
                   p_complemento in developer.endereco.complemento%type,
                   p_bairro      in developer.endereco.bairro%type,
                   p_cidade      in developer.endereco.cidade%type,
                   p_uf          in developer.endereco.uf%type,
                   p_result      out t_cursor) is
  
    v_cep number;
  
  begin
  
    insert into developer.endereco
      (cep, logradouro, numero, complemento, bairro, cidade, uf)
    values
      (p_cep,
       p_logradouro,
       p_numero,
       p_complemento,
       p_bairro,
       p_cidade,
       p_uf);
  
    SELECT MAX(cep) into v_cep from developer.endereco;
  
    open p_result for
      select * from developer.endereco c where c.cep = v_cep;
  
  end;

  procedure atualizar(p_cep         in developer.endereco.cep%type,
                      p_logradouro  in developer.endereco.logradouro%type,
                      p_numero      in developer.endereco.numero%type,
                      p_complemento in developer.endereco.complemento%type,
                      p_result      out t_cursor) is
  begin
  
    update developer.endereco en
       set en.logradouro  = p_logradouro,
           en.numero      = p_numero,
           en.complemento = p_complemento
     where en.cep = p_cep;
  
    open p_result for
      select * from developer.endereco e where e.cep = p_cep;
  
  end;

end;
/
