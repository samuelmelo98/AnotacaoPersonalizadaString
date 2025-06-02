package org.df.tecnologia.string.model;

import org.df.tecnologia.string.anotacoes.Formatar;
import org.df.tecnologia.string.util.UtilFormatar;

import java.io.Serializable;
/*
Autor:Samuel Melo
Data:02/06/2025 11:18:00
Objetivo: Criar anotação personalizada para aplicar ação no momento de instanciar o modelo da dados(Classe).
*/
public class User implements Serializable {
    @Formatar( upperCase = false, trim = false)
    private String nome;
    @Formatar
    private String sobreNome;

    public User(String nome, String sobreNome) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        UtilFormatar.formatarStrings(this); // Passa a instacia do objeto, para verificar os atributos.
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                '}';
    }
}
