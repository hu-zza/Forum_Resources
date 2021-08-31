package hu.zza.test.thymeleaf.rawmode.controller;

import hu.zza.test.thymeleaf.rawmode.RawmodeApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

// https://zza.hu/*SO/a/59110591
@RestController
public class RawController {

  @GetMapping("/raw")
  public String getIndex() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();

    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.RAW);

    // https://github.com/thymeleaf/thymeleaf/issues/606
    templateResolver.setForceTemplateMode(true);

    templateResolver.setCharacterEncoding("UTF8");
    templateResolver.setCheckExistence(true);
    templateResolver.setCacheable(false);

    templateEngine.setTemplateResolver(templateResolver);

    RawmodeApplication.printHeader("RawController");
    RawmodeApplication.printTemplateResolver(templateResolver);

    return templateEngine.process("index", new Context());
  }
}
