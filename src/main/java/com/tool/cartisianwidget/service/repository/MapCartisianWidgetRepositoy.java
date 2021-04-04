package com.tool.cartisianwidget.service.repository;

import com.tool.cartisianwidget.model.CartisianWidget;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class MapCartisianWidgetRepositoy{

    private ConcurrentHashMap<Integer,CartisianWidget> widgetConcurrentHashMap = new ConcurrentHashMap<>();

    public CartisianWidget findById(Integer id) {
        return widgetConcurrentHashMap.get(id);
    }

    public List<CartisianWidget> findAll() {
        return widgetConcurrentHashMap
                .values()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<CartisianWidget> findAll(Integer row) {
        return widgetConcurrentHashMap
                .values()
                .stream()
                .sorted()
                .limit(row)
                .collect(Collectors.toList());
    }

    public CartisianWidget save(CartisianWidget widget) {
        if(widget.getZcoordinate()==null){
            widget.setZcoordinate(maxZCoordinate()+1);
        }else {
            incrementZCoordinate(widget.getZcoordinate(),null);
        }
        widget.setLastmodified(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        widgetConcurrentHashMap.putIfAbsent(widget.getId(),widget);
        return widget;
    }

    public CartisianWidget update(CartisianWidget widget) {
        if(widget.getZcoordinate()==null){
            widget.setZcoordinate(maxZCoordinate()+1);
        }else {
            incrementZCoordinate(widget.getZcoordinate(),widget.getId());
        }
        widget.setLastmodified(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        widgetConcurrentHashMap.replace(widget.getId(),widget);
        return widget;
    }

    public void delete(Integer id) {
        widgetConcurrentHashMap.remove(id);
    }


    private Integer maxZCoordinate(){
        List<Integer> zCoordinates = widgetConcurrentHashMap
                .values()
                .stream()
                .sorted()
                .map(m->m.getZcoordinate())
                .collect(Collectors.toList());
        return zCoordinates.get(zCoordinates.size()-1);
    }

    private void incrementZCoordinate(Integer zCoordinate, Integer filter){
        List<Map.Entry<Integer,CartisianWidget>> sortedWidget = filter!=null?widgetConcurrentHashMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .filter(f->!f.getKey().equals(filter))
                .collect(Collectors.toList())
                :
                widgetConcurrentHashMap
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toList());
        //System.out.println("sortedWidget "+sortedWidget);
        int index = Collections.binarySearch(sortedWidget.stream().map(m->m.getValue().getZcoordinate()).collect(Collectors.toList()),zCoordinate);
        //System.out.println("Index "+index);
        if(index>=0) {
            for (int i = index; i < sortedWidget.size(); i++) {
                //System.out.println("i "+i);
                Integer value = sortedWidget.get(i).getValue().getZcoordinate()+1;
                //System.out.println("value "+value);
                widgetConcurrentHashMap.get(sortedWidget.get(i).getKey()).setZcoordinate(value);
                if(i< sortedWidget.size()-1 && !value.equals(sortedWidget.get(i+1).getValue().getZcoordinate())){
                    //System.out.println("break "+sortedWidget.get(i+1).getValue().getZcoordinate());
                    break;
                }
            }
        }
    }

}
