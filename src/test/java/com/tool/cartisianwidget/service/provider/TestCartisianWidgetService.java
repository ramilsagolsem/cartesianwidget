package com.tool.cartisianwidget.service.provider;

import com.tool.cartisianwidget.model.CartisianWidget;
import com.tool.cartisianwidget.service.CartisianWidgetService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCartisianWidgetService {

    @Autowired
    private CartisianWidgetService cartisianWidgetService;

    @Test
    public void create(){
        CartisianWidget widget1 = instantiate(null,2,4,1,6,8);
        widget1 = cartisianWidgetService.create(widget1);
        System.out.println(widget1);
    }

    @Test
    public void createAndIncrement(){
        mockData();
        CartisianWidget widget1 = instantiate(null,20,10,2,6,8);
        widget1 = cartisianWidgetService.create(widget1);
        System.out.println(widget1);
        List<CartisianWidget> widgets = cartisianWidgetService.findAll();
        System.out.println(widgets);
    }

    @Test
    public void createWithNoShift(){
        mockData2();
        System.out.println(cartisianWidgetService.findAll());
        CartisianWidget widget1 = instantiate(null,20,10,2,6,8);
        widget1 = cartisianWidgetService.create(widget1);
        System.out.println(widget1);
        List<CartisianWidget> widgets = cartisianWidgetService.findAll();
        System.out.println(widgets);
    }

    @Test
    public void createWithPartialShift(){
        mockData3();
        System.out.println(cartisianWidgetService.findAll());
        CartisianWidget widget1 = instantiate(null,20,10,2,6,8);
        widget1 = cartisianWidgetService.create(widget1);
        System.out.println(widget1);
        List<CartisianWidget> widgets = cartisianWidgetService.findAll();
        System.out.println(widgets);
    }

    @Test
    public void findbyid(){
        mockData();
        CartisianWidget widget = cartisianWidgetService.findById(1);
        System.out.println(widget);
    }

    @Test
    public void findAll(){
        mockData();
        List<CartisianWidget> widgets = cartisianWidgetService.findAll();
        System.out.println(widgets);
    }

    @Test
    public void findAllWithPagination(){
        mockData4();
        List<CartisianWidget> widgets = cartisianWidgetService.findAll(10);
        System.out.println(widgets);
    }

    @Test
    public void delete(){
        mockData();
        cartisianWidgetService.delete(2);
        List<CartisianWidget> widgets = cartisianWidgetService.findAll();
        System.out.println(widgets);
    }

    @Test
    public void update(){
        mockData();
        System.out.println(cartisianWidgetService.findAll());
        CartisianWidget widget1 = instantiate(2,30,10,2,7,8);
        widget1 = cartisianWidgetService.update(widget1);
        System.out.println(cartisianWidgetService.findById(2));
        Assert.assertEquals(widget1,cartisianWidgetService.findById(2));
    }

    @Test
    public void updateAndIncrement(){
        mockData();
        System.out.println(cartisianWidgetService.findAll());
        CartisianWidget widget1 = instantiate(4,30,10,2,7,8);
        widget1 = cartisianWidgetService.update(widget1);
        System.out.println(cartisianWidgetService.findAll());
        System.out.println(cartisianWidgetService.findById(4));
        Assert.assertEquals(widget1,cartisianWidgetService.findById(4));
    }


    private void mockData(){
        for(int i=1; i<=5; i++){
            cartisianWidgetService.create(instantiate(i,2+i,4+1,i,6,8));
        }
    }

    private void mockData2(){
        cartisianWidgetService.create(instantiate(1,2,3,1,6,8));
        cartisianWidgetService.create(instantiate(2,4,7,5,6,8));
        cartisianWidgetService.create(instantiate(3,6,9,6,6,8));
    }

    private void mockData3(){
        cartisianWidgetService.create(instantiate(1,2,3,1,6,8));
        cartisianWidgetService.create(instantiate(2,4,7,2,6,8));
        cartisianWidgetService.create(instantiate(3,6,9,4,6,8));
    }

    private void mockData4(){
        for(int i=1; i<=20; i++){
            cartisianWidgetService.create(instantiate(i,2+i,4+1,i,6,8));
        }
    }

    private CartisianWidget instantiate(Integer id, Integer x, Integer y, Integer z, Integer w, Integer h){
        CartisianWidget widget = new CartisianWidget();
        widget.setxCoordinate(x);
        widget.setyCoordinate(y);
        widget.setzCoordinate(z);
        widget.setId(id);
        widget.setWidth(w);
        widget.setHeight(h);
        return widget;
    }


}
