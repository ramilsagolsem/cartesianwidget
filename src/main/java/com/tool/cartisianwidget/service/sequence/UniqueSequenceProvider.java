package com.tool.cartisianwidget.service.sequence;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UniqueSequenceProvider implements SequenceGenerator {

    private AtomicInteger sequence = new AtomicInteger(1);

    @Override
    public Integer getNext() {
        return sequence.getAndIncrement();
    }
}
