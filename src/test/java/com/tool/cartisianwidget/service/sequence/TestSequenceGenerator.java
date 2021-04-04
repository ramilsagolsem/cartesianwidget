package com.tool.cartisianwidget.service.sequence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSequenceGenerator {

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Test
    public void testGetNext(){
        ExecutorService executor = Executors.newCachedThreadPool();

        try {

            // simulate 3 threads concurrent access the sequence generator
            Callable<List<Integer>> task1 = new SequenceCallable(sequenceGenerator);
            Callable<List<Integer>> task2 = new SequenceCallable(sequenceGenerator);
            Callable<List<Integer>> task3 = new SequenceCallable(sequenceGenerator);

            Future f1 = executor.submit(task1);
            Future f2 = executor.submit(task2);
            Future f3 = executor.submit(task3);

            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }

}