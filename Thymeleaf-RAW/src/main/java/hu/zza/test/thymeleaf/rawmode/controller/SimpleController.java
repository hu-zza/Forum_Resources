package hu.zza.test.thymeleaf.rawmode.controller;

import hu.zza.test.thymeleaf.rawmode.RawmodeApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;

@Controller
public class SimpleController {

  @Autowired
  TemplateEngine templateEngine;

  @GetMapping("/simple")
  public String getIndex() {
    RawmodeApplication.printHeader("SimpleController");
    templateEngine.getTemplateResolvers().forEach(RawmodeApplication::printTemplateResolver);

    return "index";
  }
}
