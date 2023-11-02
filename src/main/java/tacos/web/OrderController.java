package tacos.web;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Taco;
import tacos.TacoListWrapper;
import tacos.TacoOrder;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes({"tacoOrder", "sessionTacos"})
public class OrderController {

    private final OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
        log.info("OrderController initialized");
    }

    @ModelAttribute("sessionTacos")
    public TacoListWrapper getSessionTacos() {
        return new TacoListWrapper();
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        log.info("Displaying the order form page");
        model.addAttribute("tacoOrder", new TacoOrder());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
        @Valid TacoOrder order, 
        Errors errors, 
        @ModelAttribute("sessionTacos") TacoListWrapper tacoListWrapper, 
        SessionStatus sessionStatus
    ) {
        if (errors.hasErrors()) {
            log.warn("Validation errors while processing order: {}", errors.getAllErrors());
            return "orderForm";
        }

        List<Taco> sessionTacos = tacoListWrapper.getTacos(); // Use the getter to get the List<Taco>

      /*  if (sessionTacos == null || sessionTacos.isEmpty()) {
            log.warn("No tacos found in the session. Unable to process order.");
            return "redirect:/design";
        }*/

        for (Taco sessionTaco : sessionTacos) {
            order.addTaco(sessionTaco);
        }

        orderRepo.save(order);
        log.info("Order saved successfully with ID: {}", order.getId());

        sessionStatus.setComplete();

        return "redirect:/";
    }


}