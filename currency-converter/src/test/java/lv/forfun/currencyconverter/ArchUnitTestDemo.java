package lv.forfun.currencyconverter;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;
import lv.forfun.currencyconverter.api.Regex;


@AnalyzeClasses(packages = "lv.forfun.currencyconverter")
public class ArchUnitTestDemo {

    @ArchTest
    void apiClassesShouldEndWithRequestOrResponseOrDto(JavaClasses classes) {
        ClassesShouldConjunction rule = ArchRuleDefinition.classes()
                .that().resideInAPackage("..api..")
                .and().areNotNestedClasses()
                .and().areNotEnums()
                .and().doNotHaveSimpleName(Regex.class.getSimpleName())
                .should().haveSimpleNameEndingWith("Request")
                .orShould().haveSimpleNameEndingWith("Response")
                .orShould().haveSimpleNameEndingWith("Dto");
        rule.check(classes);
    }
}
