package com.tool.cartisianwidget.service.repository;

import com.tool.cartisianwidget.model.CartisianWidget;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CartisianWidgetRepositoyProvider implements CartisianWidgetRepository{

    private ConcurrentHashMap<Integer,CartisianWidget> widgetConcurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public CartisianWidget findById(Integer id) {
        return widgetConcurrentHashMap.get(id);
    }

    @Override
    public List<CartisianWidget> findAll() {
        return widgetConcurrentHashMap
                .values()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public CartisianWidget save(CartisianWidget widget) {
        if(widget.getzCoordinate()==null){
            widget.setzCoordinate(maxZCoordinate()+1);
        }else {
            incrementZCoordinate(widget.getzCoordinate(),null);
        }
        widget.setLastmodified(LocalDateTime.now());
        widgetConcurrentHashMap.putIfAbsent(widget.getId(),widget);
        return widget;
    }

    @Override
    public CartisianWidget update(CartisianWidget widget) {
        if(widget.getzCoordinate()==null){
            widget.setzCoordinate(maxZCoordinate()+1);
        }else {
            incrementZCoordinate(widget.getzCoordinate(),widget.getId());
        }
        widget.setLastmodified(LocalDateTime.now());
        widgetConcurrentHashMap.replace(widget.getId(),widget);
        return widget;
    }

    @Override
    public void delete(Integer id) {
        widgetConcurrentHashMap.remove(id);
    }


    private Integer maxZCoordinate(){
        List<Integer> zCoordinates = widgetConcurrentHashMap
                .values()
                .stream()
                .sorted()
                .map(m->m.getzCoordinate())
                .collect(Collectors.toList());
        return zCoordinates.get(zCoordinates.size()-1);
    }

    private void incrementZCoordinate(Integer zCoordinate, Integer filter){
        List<Map.Entry<Integer,CartisianWidget>> sortedWidget = filter!=null?widgetConcurrentHashMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .filter(f->f.getKey()!=filter)
                .collect(Collectors.toList())
                :
                widgetConcurrentHashMap
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toList());
        //System.out.println("sortedWidget "+sortedWidget);
        int index = Collections.binarySearch(sortedWidget.stream().map(m->m.getValue().getzCoordinate()).collect(Collectors.toList()),zCoordinate);
        //System.out.println("Index "+index);
        if(index>=0) {
            for (int i = index; i < sortedWidget.size(); i++) {
                //System.out.println("i "+i);
                Integer value = sortedWidget.get(i).getValue().getzCoordinate()+1;
                //System.out.println("value "+value);
                widgetConcurrentHashMap.get(sortedWidget.get(i).getKey()).setzCoordinate(value);
                if(i< sortedWidget.size()-1 && value!=sortedWidget.get(i+1).getValue().getzCoordinate()){
                    //System.out.println("break "+sortedWidget.get(i+1).getValue().getzCoordinate());
                    break;
                }
            }
        }
    }

}
