package com.tool.cartisianwidget.service.provider;

import com.tool.cartisianwidget.error.ObjectNotFoundException;
import com.tool.cartisianwidget.error.ValidationExpection;
import com.tool.cartisianwidget.model.CartisianWidget;
import com.tool.cartisianwidget.service.CartisianWidgetService;
import com.tool.cartisianwidget.service.repository.MapCartisianWidgetRepositoy;
import com.tool.cartisianwidget.service.sequence.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ConditionalOnProperty(value="sql.repository", havingValue = "false")
public class DefaultCartisianWidgetService implements CartisianWidgetService {

    @Autowired
    private MapCartisianWidgetRepositoy repository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Override
    public CartisianWidget findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<CartisianWidget> findAll() {
        return repository.findAll();
    }

    @Override
    public List<CartisianWidget> findAll(Integer row) {
        return repository.findAll(row);
    }

    @Override
    public CartisianWidget create(CartisianWidget widget) {
        if(widget.getWidth()<0 || widget.getHeight()<0){
            throw new ValidationExpection("Width and height cannot be negative");
        }
        Integer seq = sequenceGenerator.getNext();
        widget.setId(seq);
        return repository.save(widget);
    }

    @Override
    public CartisianWidget update(CartisianWidget widget) {
        if(widget.getWidth()<0 || widget.getHeight()<0){
            throw new ValidationExpection("Width and height cannot be negative");
        }
        return repository.update(widget);
    }

    @Override
    public void delete(Integer id) {
        Optional<CartisianWidget> cartisianWidget =  Optional.ofNullable(findById(id));
        if(!cartisianWidget.isPresent()){
            throw new ObjectNotFoundException(String.format("No widget found by id %s", id));
        }
        repository.delete(id);
    }
}
