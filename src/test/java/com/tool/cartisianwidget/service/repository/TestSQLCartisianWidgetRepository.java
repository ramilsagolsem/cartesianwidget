package com.tool.cartisianwidget.service.repository;

import com.tool.cartisianwidget.model.CartisianWidget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSQLCartisianWidgetRepository {

    @Autowired
    private SQLCartisianWidgetRepository repository;

    @Test
    public void getMaxZCoordiate(){
        Integer max = repository.getMaxZCoordiate();
        System.out.println(max);
    }

    @Test
    public void findByZCoordinateGreaterThan(){
        List<CartisianWidget> widgetList = repository.findByZCoordinateGreaterThan(2);
        System.out.println(widgetList);
    }

    @Test
    public void findByZCoordinate(){
        CartisianWidget widget = repository.findByZCoordinate(2);
        System.out.println(widget);
    }
}
