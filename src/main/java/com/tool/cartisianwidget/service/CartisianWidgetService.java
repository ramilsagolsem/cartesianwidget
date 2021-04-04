package com.tool.cartisianwidget.service;

import com.tool.cartisianwidget.model.CartisianWidget;

import java.util.List;

public interface CartisianWidgetService {

    public CartisianWidget findById(Integer id);
    public List<CartisianWidget> findAll();
    public CartisianWidget create(CartisianWidget widget);
    public CartisianWidget update(CartisianWidget widget);
    public void delete(Integer id);
}
