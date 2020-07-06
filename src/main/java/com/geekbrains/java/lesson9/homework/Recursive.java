package com.geekbrains.java.lesson9.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class Recursive extends RecursiveTask<Integer> {
    private final int[] data;

    public Recursive(int[] data) {
        this.data = data;
    }

    @Override
    protected Integer compute() {
        if (data.length < 10_000) {
            return getMaxInArray(data);
        } else {
            List<Recursive> subtasks = createSubtasks();
            for (Recursive subtask : subtasks) {
                subtask.fork();
            }
            int[] newData = new int[subtasks.size()];
            for (int i = 0; i < subtasks.size(); i++) {
                newData[i] = subtasks.get(i).join();
            }
            return new Recursive(newData).compute();
        }
    }

    private int getMaxInArray(int[] data) {
        return IntStream.of(data).max().orElseThrow(() -> new RuntimeException("Empty array"));
    }

    private List<Recursive> createSubtasks() {
        int len;

        len = data.length / 4;
        return new ArrayList<>(Arrays.asList(
                new Recursive(Arrays.copyOfRange(data, 0, len)),
                new Recursive(Arrays.copyOfRange(data, len, len * 2)),
                new Recursive(Arrays.copyOfRange(data, len * 2, len * 3)),
                new Recursive(Arrays.copyOfRange(data, len * 3, data.length))
        ));
    }

}
