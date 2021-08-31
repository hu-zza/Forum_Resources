package hu.zza.test.thymeleaf.rawmode;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
public class RawmodeApplication {
  private static ApplicationContext applicationContext;
  private static ThymeleafProperties thymeleafProperties;

  public static void main(String[] args) {
    applicationContext = SpringApplication.run(RawmodeApplication.class, args);

    thymeleafProperties =
        (ThymeleafProperties)
            applicationContext.getBean(
                "spring.thymeleaf-org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties");

    printBeanNames();
    printThymeleafProperties();
    printTemplateResolvers();
  }

  public static void printHeader(String title) {
    System.out.printf("%n%s%n%s%n%n", "-".repeat(30), title);
  }

  private static void printBeanNames() {
    printHeader("Beans");
    Arrays.stream(applicationContext.getBeanDefinitionNames())
        .sorted()
        .forEach(System.out::println);
  }

  private static void printThymeleafProperties() {
    printHeader("Thymeleaf properties");
    System.out.printf(
        "Mode: %s%nEnabled: %b%nOrder: %d%nViews: %s%nExcluded views: %s%n%n",
        thymeleafProperties.getMode(),
        thymeleafProperties.isEnabled(),
        thymeleafProperties.getTemplateResolverOrder(),
        Arrays.toString(thymeleafProperties.getViewNames()),
        Arrays.toString(thymeleafProperties.getExcludedViewNames()));
  }

  private static void printTemplateResolvers() {
    printHeader("Template resolvers");
    printDefaultTemplateResolver();
    printResolversOfTemplateEngine();
  }

  private static void printDefaultTemplateResolver() {
    System.out.println("Default template resolver:");
    printTemplateResolver(applicationContext.getBean("defaultTemplateResolver"));
  }

  private static void printResolversOfTemplateEngine() {
    System.out.println("Resolvers of template engine:");
    var templateEngine = (TemplateEngine) applicationContext.getBean("templateEngine");
    templateEngine.getTemplateResolvers().forEach(RawmodeApplication::printTemplateResolver);

    System.out.println();
  }

  private static void printTemplateResolver(Object templateResolver) {
    printTemplateResolver((ITemplateResolver) templateResolver);
  }

  public static void printTemplateResolver(ITemplateResolver templateResolver) {
    if (templateResolver instanceof AbstractConfigurableTemplateResolver) {
      var resolver = (AbstractConfigurableTemplateResolver) templateResolver;

      printResolverData(
          resolver.getOrder(),
          resolver.getName(),
          resolver.getTemplateMode().toString(),
          resolver.getForceTemplateMode());
    } else {
      printResolverData(templateResolver.getOrder(), templateResolver.getName(), null, null);
    }
  }

  private static void printResolverData(Integer order, String name, String mode, Boolean forced) {
    System.out.printf("[%d] %s  | Mode: %s  | Force: %b%n%n", order, name, mode, forced);
  }
}
