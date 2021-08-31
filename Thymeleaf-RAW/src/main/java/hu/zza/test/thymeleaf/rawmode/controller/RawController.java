package hu.zza.test.thymeleaf.rawmode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@RestController
public class RawController {

  @GetMapping("/raw")
  public String getIndex() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();

    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("templates/");

    templateResolver.setCacheable(false);
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("RAW");

    // https://github.com/thymeleaf/thymeleaf/issues/606
    templateResolver.setForceTemplateMode(true);

    templateEngine.setTemplateResolver(templateResolver);

    System.out.printf("%n%s%nRawController%n%nMode: %s%nForce: %b%n%n",
        "-".repeat(30),
        templateResolver.getTemplateMode(),
        templateResolver.getForceTemplateMode());

    return templateEngine.process("index", new Context());
  }
}
