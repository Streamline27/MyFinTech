package lv.forfun.currencyconverter;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;
import lv.forfun.currencyconverter.api.Regex;
import org.springframework.data.repository.Repository;


@AnalyzeClasses(packages = "lv.forfun.currencyconverter")
public class ArchitecturalConventionTests {

    @ArchTest
    void apiClassesShouldFollowConventions(JavaClasses classes) {
        ArchRuleDefinition.classes()
                .that().resideInAPackage("..api..")
                .and().areNotNestedClasses()
                .and().areNotEnums()
                .and().doNotHaveSimpleName(Regex.class.getSimpleName())
                .should().haveSimpleNameEndingWith("Request")
                .orShould().haveSimpleNameEndingWith("Response")
                .orShould().haveSimpleNameEndingWith("Dto")
                .check(classes);
    }

    @ArchTest
    void repositoryClassesShouldFollowConventions(JavaClasses classes) {
        ArchRuleDefinition.classes()
                .that().areAssignableTo(Repository.class)
                .should().beAnnotatedWith(org.springframework.stereotype.Repository.class)
                .andShould().haveSimpleNameEndingWith("Repository")
                .andShould().resideInAnyPackage("lv.forfun.currencyconverter.domain..")
                .check(classes);
    }
}
