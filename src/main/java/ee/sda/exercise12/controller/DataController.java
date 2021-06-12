package ee.sda.exercise12.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

   @GetMapping("/cars")
   public String showCars() {
      return "cars";
   }

   @PostMapping("/cars")
   public String showCarsPost() {
      return "cars (post)";
   }

   @GetMapping("/users")
   public String showUsers() {
      return "users";
   }
}
