package com.tool.cartisianwidget.controller;

import com.tool.cartisianwidget.error.ObjectNotFoundException;
import com.tool.cartisianwidget.error.ValidationExpection;
import com.tool.cartisianwidget.model.CartisianWidget;
import com.tool.cartisianwidget.service.CartisianWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CartisianWidgetController {

    @Autowired
    private CartisianWidgetService cartisianWidgetService;

    @GetMapping("/cartisian/widget/{id}")
    public ResponseEntity<CartisianWidget> get(@PathVariable Integer id) {
        Optional<CartisianWidget> cartisianWidget =  Optional.ofNullable(cartisianWidgetService.findById(id));
        if(!cartisianWidget.isPresent()){
            throw new ObjectNotFoundException(String.format("No widget found by id %s", id));
        }
        return ResponseEntity.ok().body(cartisianWidget.get());
    }

    @GetMapping("/cartisian/widgets")
    public ResponseEntity<List<CartisianWidget>> all(@RequestParam(required = false) Integer row) {
        if(row!=null && (row > 500 || row <0)){
            throw new ValidationExpection("Maximum number of widgets which can be fetched is 500. It cannot be negative");
        }
        return ResponseEntity.ok().body(row!=null?cartisianWidgetService.findAll(row):cartisianWidgetService.findAll(10));
    }

    @DeleteMapping("/cartisian/widget/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        cartisianWidgetService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cartisian/widget")
    public ResponseEntity<CartisianWidget> create(@RequestBody CartisianWidget widget){
        return ResponseEntity.ok().body(cartisianWidgetService.create(widget));
    }

    @PutMapping("/cartisian/widget/{id}")
    public ResponseEntity<CartisianWidget> update(@RequestBody CartisianWidget widget, @PathVariable Integer id){
       Optional<CartisianWidget> cartisianWidget =  Optional.ofNullable(cartisianWidgetService.findById(id));
       if(!cartisianWidget.isPresent()){
           throw new ObjectNotFoundException(String.format("No widget found by id %s", id));
       }
       widget.setId(id);
       widget = cartisianWidgetService.update(widget);
       return ResponseEntity.ok().body(widget);
    }
}
