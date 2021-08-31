package hu.zza.test.thymeleaf.rawmode;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
public class RawmodeApplication {
	private static ApplicationContext applicationContext;
	private static ThymeleafProperties thymeleafProperties;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(RawmodeApplication.class, args);

		thymeleafProperties =
				(ThymeleafProperties) applicationContext.getBean(
						"spring.thymeleaf-org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties");

		printBeanNames();
		printThymeleafProperties();
		printTemplateResolvers();
	}

	private static void printBeanNames() {
		System.out.printf("%n%n%s%nBeans%n%n", "-".repeat(30));
		Arrays.stream(applicationContext.getBeanDefinitionNames())
				.sorted()
				.forEach(System.out::println);
	}

	private static void printThymeleafProperties() {

		System.out.printf(
				"%n%s%nThymeleaf properties%n%nMode: %s%nEnabled: %b%nOrder: %d%nViews: %s%nExcluded views: %s%n%n",
				"-".repeat(30),
				thymeleafProperties.getMode(),
				thymeleafProperties.isEnabled(),
				thymeleafProperties.getTemplateResolverOrder(),
				Arrays.toString(thymeleafProperties.getViewNames()),
				Arrays.toString(thymeleafProperties.getExcludedViewNames()));
	}

	private static void printTemplateResolvers() {
    System.out.printf("%n%s%nTemplate resolvers%n%n", "-".repeat(30));
		printDefaultTemplateResolver();
		printResolversOfTemplateEngine();
	}

	private static void printDefaultTemplateResolver() {
    System.out.println("Default template resolver:");
		printTemplateResolver(
				applicationContext.getBean("defaultTemplateResolver"));
	}

	private static void printResolversOfTemplateEngine() {
    System.out.println("Resolvers of template engine:");
		var templateEngine = (TemplateEngine) applicationContext.getBean("templateEngine");
		templateEngine.getTemplateResolvers()
				.forEach(RawmodeApplication::printTemplateResolver);

    System.out.println();
	}

	private static void printTemplateResolver(Object templateResolver) {
		printTemplateResolver((ITemplateResolver) templateResolver);
	}

	private static void printTemplateResolver(ITemplateResolver templateResolver) {

		if (templateResolver instanceof SpringResourceTemplateResolver) {
			var springResolver = (SpringResourceTemplateResolver) templateResolver;
      System.out.printf("[%d] %s Mode: %s %n",
					springResolver.getOrder(),
					springResolver.getName(),
					springResolver.getTemplateMode());
		} else {
			System.out.printf("[%d] %s (%s)%n",
					templateResolver.getOrder(),
					templateResolver.getName(),
					templateResolver.getClass().getName());
		}

    System.out.println();
	}
}
