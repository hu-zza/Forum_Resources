package hu.zza.test.thymeleaf.rawmode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

  @GetMapping("/simple")
  public String getIndex() {
    return "index";
  }
}
