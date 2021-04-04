package com.tool.cartisianwidget.service;

import com.tool.cartisianwidget.model.CartisianWidget;

import java.util.List;

public interface CartisianWidgetService {

    CartisianWidget findById(Integer id);
    List<CartisianWidget> findAll();
    List<CartisianWidget> findAll(Integer row);
    CartisianWidget create(CartisianWidget widget);
    CartisianWidget update(CartisianWidget widget);
    void delete(Integer id);
}
