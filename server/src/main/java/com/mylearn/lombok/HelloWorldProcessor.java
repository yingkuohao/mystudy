package com.mylearn.lombok;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/3/22
 * Time: ÏÂÎç4:08
 * CopyRight: taobao
 * Descrption:
 */

@SupportedAnnotationTypes("HelloWorld")
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class HelloWorldProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "hello world!");
            System.out.println("hello World!");
        }
        return true;
    }
}
