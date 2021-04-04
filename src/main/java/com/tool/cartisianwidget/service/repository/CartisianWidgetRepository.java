package com.tool.cartisianwidget.service.repository;

import com.tool.cartisianwidget.model.CartisianWidget;

import java.util.List;

public interface CartisianWidgetRepository {

    CartisianWidget findById(Integer id);
    List<CartisianWidget> findAll();
    CartisianWidget save(CartisianWidget widget);
    CartisianWidget update(CartisianWidget widget);
    void delete(Integer id);
}
