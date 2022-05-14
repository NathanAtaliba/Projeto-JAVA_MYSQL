package br.inatel.C207.DB;
import br.inatel.C207.Class.Paciente;
import java.sql.SQLException;
import java.util.ArrayList;
import br.inatel.C207.Class.Atendente;
public class AtendenteDB extends Database {
public AtendenteDB(){}
    public boolean insertAtendente(Atendente atendente){ // Inserindo Atendente no banco de dados
connect();    //Conectando no banco
String sql = "INSERT INTO atendenteDB(nome,idade,id,setor) VALUES(? ,? ,? , ?)";  //Comando utilizado no Sql transcrevido em TEXTO
try{                    //Comandos utlizados sem o erro
    pst = connection.prepareStatement(sql);   //preparando conexao
    pst.setInt(3,atendente.getId());   // //preparando Query para idAtendenteDB
    pst.setString(1, atendente.getNome());       //preparando Query para nome
    pst.setInt(2,atendente.getIdade());         //preparando Query para idade
    pst.setString(4,atendente.getSetor());  //preparando Query para setor
    pst.execute();    // Executar
    check = true;     // passando valor true para check (controle de erro)
}catch(SQLException e){  // SQLException (Ocorreu erro)
    System.out.println("Erro de operação: " +e.getMessage());   //Mensagem de erro
    check = false;   //Passando valor pra false para check (Controle de erro)
}
finally{    // Ao final da execuçao
try{
connection.close();   //fechar conexao
pst.close();
}catch(SQLException e){  //Se nao conseguir fechar conexao
    System.out.println("Erro ao fechar a conexao: "+e.getMessage());  // Erro de encerramento de conexao
}
}
return check;  //retornando check
}

public ArrayList<Atendente> researchAtendentes(){   //selecionar Atendente
connect();  //conectando
ArrayList<Atendente> atendentes = new ArrayList<>();  // Criando Array para guardar atendentes
String sql = "SELECT * FROM AtendenteDB";  //Comando utilizado no Sql transcrevido em TEXTO
try {
    statement = connection.createStatement();  //Criando variavel para conexao
    result = statement.executeQuery(sql);  // Criando variavel para comparar os resultados

    while(result.next()){
            Atendente atendente = new Atendente ((result.getString("nome")),(result.getInt("idade")),(result.getInt("id")),result.getString("setor")); //Criando atendente com resultados para comparar

            atendente.setId(result.getInt("id"));  //igualando o resultado do atendente com o atendente criado
        atendente.setNome(result.getString("nome"));        //igualando o resultado do atendente com o atendente criado
         atendente.setIdade(result.getInt("idade"));       //igualando o resultado do atendente com o atendente criado
        atendente.setSetor(result.getString("setor"));
        System.out.println("Nome = "+ atendente.getNome());    //mostrando os atributos do atendente
        System.out.println("Idade = "+atendente.getIdade());   //mostrando os atributos do atendente
        System.out.println("ID = "+ atendente.getId());    //mostrando os atributos do atendente
        System.out.println("----------");
        atendentes.add(atendente); // Adicionando atendente no array
    }
}catch(SQLException e){    //Se der erro
    System.out.println("Erro de operaçao: " + e.getMessage()); //mostrar mensagem de erro
}finally{  //no final faça ...
    try{
     connection.close();   //fechar execuçoes usadas pro banco
     statement.close();    //fechar execuçoes usadas pro banco
     result.close();      //fechar execuçoes usadas pro banco

    }catch(SQLException e){ //se der erro o fechamento de conexão
        System.out.println("Erro ao fechar a conexão"+ e.getMessage()); //mostrar mensagem de erro
    }
}
return atendentes;  //retornar atendente
}

public boolean updateAtendente(String nome,int id,int idade,String setor){ //atualizar dados do atendente
connect();
String sql = "UPDATE atendenteDB SET idade =? WHERE nome=? AND idAtendenteDB =? and setor =?"; //Comando utilizado no Sql transcrevido em TEXTO
try{
    pst = connection.prepareStatement(sql); //preparando conexao
    pst.setInt(1,idade);     //preparando Query para idade
    pst.setString(2,nome);   // preparando Query para nome
    pst.setInt (3,id);   //preparando Query para idAtendenteDB
    pst.setString(4,setor);
    pst.execute();  //executar
    check = true;  // variavel de controle foi para true
}catch(SQLException e){  //Se der erro
    System.out.println("Erro de operação: "+ e.getMessage());  //mostrar mensagem
    check= false;  // variavel de controle foi para false
}finally{  //ao final fechar execuçoes
    try{
        connection.close();  //fechar variavel de conexao
        pst.close();   //fechar variavel de manipulacao
    }catch(SQLException e){  // se der erro
        System.out.println("Erro ao fechar a conexão: "+e.getMessage()); // mostrar mensagem de erro
    }
}
return check; //retornar variavel de controle
}

public boolean deleteAtendente(String nome,int id,int idade, String setor){  //deletar atendentes
connect(); //conexao
String sql = "DELETE FROM atendenteDB WHERE idAtendenteDB = ? AND nome=? AND idade=? AND setor";  //Comando utilizado no Sql transcrevido em TEXTO
try{
    pst = connection.prepareStatement(sql);  //preparando conexao com texto sql
    pst.setInt(1,id);  //preparando Query para id
    pst.setString(2,nome); // preparando Query para nome
    pst.setInt(3,idade);   // preparando Query para idade
    pst.setString(4,setor);
    pst.execute();   // executar
    check = true;  //variavel de controle check

}catch(SQLException e){  // se deu erro na execuçao
    System.out.println("Erro de operação: "+ e.getMessage());  //mostrar mensagem de erro
    check = false; //colocar variavel de controle para false
}finally{  // ao final..
    try {
        connection.close();  // fechar conexao
        pst.close();  //fechar variavel de manipulacao
    }catch (SQLException e){  //se der erro
        System.out.println("Erro ao fechar a conexão "+e.getMessage());  //mostrar mensagem de erro
    }
}
return check;  //retornar variaveld e controle
}

    //////////////////////////////////////////////////////////
    public boolean insertPaciente(Paciente paciente){ // Inserindo Paciente no banco de dados
        connect();    //Conectando no banco
        String sql = "INSERT INTO pacienteDB(nomePaciente,Sintomas,idade,idPaciente,AtendenteDB_idAtendenteDB) VALUES(? ,? ,? ,?, ?)";  //Comando utilizado no Sql transcrevido em TEXTO
        try{                    //Comandos utlizados sem o erro

            pst = connection.prepareStatement(sql);   //preparando conexao
            pst.setString(1,paciente.getNomePaciente());    //preparando Query para nomePaciente
            pst.setString(2, paciente.getSintomas());        //preparando Query para Sintomas
            pst.setInt(3,paciente.getIdade());        //preparando Query para idade
            pst.setInt(4,paciente.getIdPaciente());  //preparando Query para IdPaciente
            pst.setInt(5,paciente.getAtendenteDB_idAtendenteDB());
            pst.execute();    // Executar
            check = true;     // passando valor true para check (controle de erro)
        }catch(SQLException e){  // SQLException (Ocorreu erro)
            System.out.println("Erro de operação: " +e.getMessage());   //Mensagem de erro
            check = false;   //Passando valor pra false para check (Controle de erro)
        }
        finally{    // Ao final da execuçao
            try{
                connection.close();   //fechar conexao
                pst.close();
            }catch(SQLException e){  //Se nao conseguir fechar conexao
                System.out.println("Erro ao fechar a conexao: "+e.getMessage());  // Erro de encerramento de conexao
            }
        }
        return check;  //retornando check
    }

    public ArrayList<Paciente> researchPacientes(){   //selecionar Atendente
        connect();  //conectando
        ArrayList<Paciente> pacientes = new ArrayList<>();  // Criando Array para guardar atendentes
        String sql = "SELECT * FROM PacienteDB";  //Comando utilizado no Sql transcrevido em TEXTO
        try {
            statement = connection.createStatement();  //Criando variavel para conexao
            result = statement.executeQuery(sql);  // Criando variavel para comparar os resultados

            while(result.next()){
                Paciente paciente = new Paciente ((result.getString("nomePaciente")),(result.getString("sintomas")),(result.getInt("idade")),result.getInt("idPaciente"),result.getInt("AtendenteDB_idAtendenteDB")); //Criando atendente com resultados para comparar

                paciente.setNomePaciente(result.getString("nomePaciente"));  //igualando o resultado do atendente com o atendente criado
                paciente.setSintomas(result.getString("sintomas"));       //igualando o resultado do atendente com o atendente criado
                paciente.setIdade(result.getInt("idade"));       //igualando o resultado do atendente com o atendente criado
                paciente.setIdPaciente(result.getInt("idPaciente")) ;
                System.out.println("Nome = "+ paciente.getNomePaciente());    //mostrando os atributos do paciente
                System.out.println("Idade = "+paciente.getIdade());   //mostrando os atributos do paciente
                System.out.println("ID = "+ paciente.getIdPaciente());    //mostrando os atributos do paciente
                System.out.println("Sintomas = "+ paciente.getSintomas());
                System.out.println("----------");
                pacientes.add(paciente); // Adicionando paciente no array
            }
        }catch(SQLException e){    //Se der erro
            System.out.println("Erro de operaçao: " + e.getMessage()); //mostrar mensagem de erro
        }finally{  //no final faça ...
            try{
                connection.close();   //fechar execuçoes usadas pro banco
                statement.close();    //fechar execuçoes usadas pro banco
                result.close();      //fechar execuçoes usadas pro banco

            }catch(SQLException e){ //se der erro o fechamento de conexão
                System.out.println("Erro ao fechar a conexão"+ e.getMessage()); //mostrar mensagem de erro
            }
        }
        return pacientes;  //retornar atendente
    }

    public boolean updatePaciente(String nomePaciente,String sintomas,int idade,int idPaciente,int AtendenteDB_idAtendenteDB){ //atualizar dados do atendente
        connect();
        String sql = "UPDATE pacienteDB SET idade =? WHERE nomePaciente=? AND idAtendenteDB =? AND sintomas=? AND AtendenteDB_idAtendenteDB=?"; //Comando utilizado no Sql transcrevido em TEXTO
        try{
            pst = connection.prepareStatement(sql); //preparando conexao
            pst.setInt(1,idade);     //preparando Query para idade
            pst.setString(2,nomePaciente);   // preparando Query para nomePaciente
            pst.setInt (3,idPaciente);   //preparando Query para idPacienteDB
            pst.setString(4,sintomas); //preparando Query para sintomas
            pst.setInt(5,AtendenteDB_idAtendenteDB);
            pst.execute();  //executar
            check = true;  // variavel de controle foi para true
        }catch(SQLException e){  //Se der erro
            System.out.println("Erro de operação: "+ e.getMessage());  //mostrar mensagem
            check= false;  // variavel de controle foi para false
        }finally{  //ao final fechar execuçoes
            try{
                connection.close();  //fechar variavel de conexao
                pst.close();   //fechar variavel de manipulacao
            }catch(SQLException e){  // se der erro
                System.out.println("Erro ao fechar a conexão: "+e.getMessage()); // mostrar mensagem de erro
            }
        }
        return check; //retornar variavel de controle
    }

    public boolean deletePaciente(String nomePaciente,int sintomas,int idade,int idPaciente){  //deletar atendentes
        connect(); //conexao
        String sql = "DELETE FROM pacienteDB WHERE idPaciente = ? AND nomePaciente=? AND idade=? AND sintomas=?";  //Comando utilizado no Sql transcrevido em TEXTO
        try{
            pst = connection.prepareStatement(sql);  //preparando conexao com texto sql
            pst.setInt(1,idPaciente);  //preparando Query para idAtendenteDB
            pst.setString(2,nomePaciente); // preparando Query para nome
            pst.setInt(3,idade);   // preparando Query para idade
            pst.setInt(4,sintomas);
            pst.execute();   // executar
            check = true;  //variavel de controle check

        }catch(SQLException e){  // se deu erro na execuçao
            System.out.println("Erro de operação: "+ e.getMessage());  //mostrar mensagem de erro
            check = false; //colocar variavel de controle para false
        }finally{  // ao final..
            try {
                connection.close();  // fechar conexao
                pst.close();  //fechar variavel de manipulacao
            }catch (SQLException e){  //se der erro
                System.out.println("Erro ao fechar a conexão "+e.getMessage());  //mostrar mensagem de erro
            }
        }
        return check;  //retornar variaveld e controle
    }

}

