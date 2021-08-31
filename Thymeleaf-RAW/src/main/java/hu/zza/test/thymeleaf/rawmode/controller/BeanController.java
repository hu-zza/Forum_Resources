package hu.zza.test.thymeleaf.rawmode.controller;

import hu.zza.test.thymeleaf.rawmode.RawmodeApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

// https://zza.hu/*SO/a/50739141
@RestController
public class BeanController {

  @Autowired
  @Qualifier("rawTemplateEngine")
  TemplateEngine templateEngineRaw;

  @GetMapping("/bean")
  public String getIndex() {
    RawmodeApplication.printHeader("BeanController");
    templateEngineRaw.getTemplateResolvers().forEach(RawmodeApplication::printTemplateResolver);

    return templateEngineRaw.process("index", new Context());
  }
}
