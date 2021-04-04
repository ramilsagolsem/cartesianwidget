package com.tool.cartisianwidget.service.provider;

import com.tool.cartisianwidget.model.CartisianWidget;
import com.tool.cartisianwidget.service.CartisianWidgetService;
import com.tool.cartisianwidget.service.repository.SQLCartisianWidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(value="sql.repository", havingValue = "true")
public class SQLCartisianWidgetService implements CartisianWidgetService {

    @Autowired
    private SQLCartisianWidgetRepository repository;

    @Override
    public CartisianWidget findById(Integer id) {
        Optional<CartisianWidget> widget = repository.findById(id);
        return  widget.get();
    }

    @Override
    public List<CartisianWidget> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "zcoordinate"));
    }

    @Override
    public List<CartisianWidget> findAll(Integer row) {
        Page<CartisianWidget> pages = repository.findAll(PageRequest.of(0, row, Sort.by(Sort.Direction.ASC, "zcoordinate")));
        return pages.get().collect(Collectors.toList());
    }

    @Override
    public CartisianWidget create(CartisianWidget widget) {
        if(widget.getZcoordinate()==null){
            widget.setZcoordinate(repository.getMaxZCoordiate()+1);
        }else {
            incrementZCoordinate(widget.getZcoordinate(),null);
        }
        widget.setLastmodified(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        widget.setId(null);
        return repository.save(widget);
    }

    @Override
    public CartisianWidget update(CartisianWidget widget) {
        if(widget.getZcoordinate()==null){
            widget.setZcoordinate(repository.getMaxZCoordiate()+1);
        }else {
            incrementZCoordinate(widget.getZcoordinate(),widget.getId());
        }
        widget.setLastmodified(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        return repository.save(widget);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private void incrementZCoordinate(Integer zCoordinate, Integer filter){
        CartisianWidget widget = repository.findByZCoordinate(zCoordinate);
        if(widget!=null){
            List<CartisianWidget> widgetList = filter!=null?repository.findByZCoordinateGreaterThan(zCoordinate)
                    .stream()
                    .filter(f->!f.getId().equals(filter))
                    .collect(Collectors.toList())
                    :
                    repository.findByZCoordinateGreaterThan(zCoordinate);
            for (int i = 0; i < widgetList.size(); i++) {
                CartisianWidget widget1 = widgetList.get(i);
                Integer value = widget1.getZcoordinate()+1;
                widget1.setZcoordinate(value);
                repository.save(widget1);
                if(i< widgetList.size()-1 && !value.equals(widgetList.get(i+1).getZcoordinate())){
                    break;
                }
            }
        }
    }

}
