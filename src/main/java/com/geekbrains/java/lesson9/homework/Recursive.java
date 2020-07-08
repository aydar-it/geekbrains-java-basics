package com.geekbrains.java.lesson9.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Recursive extends RecursiveTask<Integer> {
    private final int[] data;
    private final int start;
    private final int end;

    public Recursive(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start < 10_000_000) {
            return getMaxInArray();
        } else {
            List<Recursive> subtasks = createSubtasks();
            for (Recursive subtask : subtasks) {
                subtask.fork();
            }
            int[] newData = new int[subtasks.size()];
            for (int i = 0; i < subtasks.size(); i++) {
                newData[i] = subtasks.get(i).join();
            }
//            for (int i = 0; i < subtasks.size(); i++) {
//                newData[i] = subtasks.get(i).compute();
//            }
            return new Recursive(newData, 0, newData.length).compute();
        }
    }

    private int getMaxInArray() {
        int result = data[start];
        for (int i = start; i < end; i++) {
            if (data[i] > result) {
                result = data[i];
            }
        }
        return result;
    }

    private List<Recursive> createSubtasks() {
        return new ArrayList<>(Arrays.asList(
                new Recursive(data, start, (end - start) / 2 + start),
                new Recursive(data, (end - start) / 2 + start, end)
        ));
    }

}
