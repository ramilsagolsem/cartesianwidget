package com.tool.cartisianwidget.service.sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class SequenceCallable implements Callable<List<Integer>> {

    private SequenceGenerator sequenceGenerator;

    public SequenceCallable(SequenceGenerator sequenceGenerator){
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public List<Integer> call() throws Exception {
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(100);
            ids.add(sequenceGenerator.getNext());
        }
        return ids;
    }
}
