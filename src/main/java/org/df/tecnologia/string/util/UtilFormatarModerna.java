package org.df.tecnologia.string.util;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.text.WordUtils;
import org.df.tecnologia.string.anotacoes.Formatar;

public class UtilFormatarModerna {

    public static void formatarStrings(Object obj) {
        if (obj == null) return;
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            processarCampo(obj, field);
        }
    }

    private static void processarCampo(Object obj, Field field) {
        if (!field.isAnnotationPresent(Formatar.class) || field.getType() != String.class) return;
        field.setAccessible(true);
        Formatar anotacao = field.getAnnotation(Formatar.class);
        try {
            Optional.ofNullable((String) field.get(obj))
                    .map(comporFormatacoes2(anotacao))   // aplica todas as regras de formatação
                    .ifPresent(valorFormatado -> {
                        try {
                            field.set(obj, valorFormatado);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Erro ao definir valor formatado", e);
                        }
                    });

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Erro ao acessar campo anotado", e);
        }
    }

    private static Function<String, String> comporFormatacoes(Formatar anotacao) {
        return valor -> {
            String resultado = valor;
            if (anotacao.trim()) {
                resultado = resultado.trim();
            }
            if (anotacao.upperCase()) {
                resultado = resultado.toUpperCase();
            }
            if (anotacao.FirstUpper()) {
                resultado = WordUtils.capitalizeFully(resultado); // para "mARIA sOUZA" → "Maria Souza"
            }
            return resultado;
        };
    }

    /**
     * Evita o uso dos if.
     * @param anotacao
     * @return
     */
    private static Function<String, String> comporFormatacoes2(Formatar anotacao) {
        // Lista de funções de formatação, criadas condicionalmente via Stream
        return Stream.<Function<String, String>>of(
                        anotacao.trim()       ? String::trim : null,
                        anotacao.upperCase()  ? String::toUpperCase : null,
                        anotacao.FirstUpper() ? WordUtils::capitalizeFully : null
                )
                .filter(f -> f != null) // remove os nulos (funções não habilitadas)
                .reduce(Function.identity(), Function::andThen); // encadeia tudo
    }

}
