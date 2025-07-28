package com.example.designmodel.Iterator;

import java.util.Iterator;

public class DrivingRecoeder implements Iterable<String>{

    private int index =-1;
    private String[] recorders = new String[10];

    public void append(String recorde){
        if (index ==9) {
            index =0;
        } else {
            index++;
        }
        recorders[index] = recorde;
    }

    @Override
    public Iterator<String> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<String> {
        int cursor = index;
        int loopCount =0;

        @Override
        public boolean hasNext() {
            return loopCount < 10;
        }

        @Override
        public String next() {
            int i = cursor;
            if (cursor == 0) {
                cursor =9;
            } else {
                cursor--;
            }
            loopCount++;
            return recorders[i];
        }
    }
}
