package org.df.tecnologia.string.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
* Interface para criar a anotação.
* */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Formatar {
    boolean trim() default true;
    boolean upperCase() default false;
    boolean FirstUpper() default true;
}
