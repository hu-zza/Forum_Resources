package hu.zza.test.thymeleaf.rawmode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

// https://zza.hu/*SO/a/50739141
@Configuration
public class ThymeleafConfig {

  @Bean(name = "rawTemplateEngine")
  public TemplateEngine rawTemplateEngine() {
    TemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(rawTemplateResolver());
    return templateEngine;
  }

  private ITemplateResolver rawTemplateResolver() {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.RAW);

    // https://github.com/thymeleaf/thymeleaf/issues/606
    templateResolver.setForceTemplateMode(true);

    templateResolver.setCharacterEncoding("UTF8");
    templateResolver.setCheckExistence(true);
    templateResolver.setCacheable(false);
    return templateResolver;
  }
}