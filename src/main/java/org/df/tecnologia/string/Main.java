package org.df.tecnologia.string;

import org.df.tecnologia.string.model.User;

public class Main {

    public static void main(String[] args) {
        User user = new User("         samuel        ", "silva");
        System.out.println("Nome:" + user.getNome() + "\n" + "Sobre nome:" + user.getSobreNome());

    }
}