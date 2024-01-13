package com.example.opentelemtrydemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {
    private final OrderRepository orderRepository;

    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id) {
       log.info("[findById] get order by id {}",id);
        return this.orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: %d".formatted(id)));
    }
    @GetMapping("/all")
    public List<Order> findAll() {
        log.info("[findAll]");
        log.info("another log");
        return this.orderRepository.findAll();
    }
}
