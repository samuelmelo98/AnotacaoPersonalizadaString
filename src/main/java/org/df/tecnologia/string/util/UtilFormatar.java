package org.df.tecnologia.string.util;

import org.apache.commons.lang3.text.WordUtils;
import org.df.tecnologia.string.anotacoes.Formatar;


import java.lang.reflect.Field;

public class UtilFormatar {

    public static void formatarStrings(Object obj) {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Formatar.class) && field.getType() == String.class) {
                Formatar anotacao = field.getAnnotation(Formatar.class);
                field.setAccessible(true);
                try {
                    String valor = (String) field.get(obj);
                    if (valor != null) {
                        if (anotacao.trim()) {
                            valor = valor.trim();
                        }
                        if (anotacao.upperCase()) {
                            valor = valor.toUpperCase();
                        }
                        if(anotacao.FirstUpper()){
                           // valor = StringUtil.capitalizeFirstLetter(valor);
                            valor = WordUtils.capitalize(valor);
                        }
                        field.set(obj, valor);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class StringUtil {
        public static String capitalizeFirstLetter(String input) {
            if (input == null || input.isEmpty()) {
                return input;
            }
            return input.substring(0, 1).toUpperCase() + input.substring(1);
        }
    }
}
