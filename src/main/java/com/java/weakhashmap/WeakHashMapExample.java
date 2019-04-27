package com.java.weakhashmap;

import java.util.WeakHashMap;

public class WeakHashMapExample {
    public static void main(String[] args) {
        //WeakReferene not required
        // Inside this class : Entry<K,V> extends WeakReference<Object>
        // it internally stored objects as weakreference, so if we are using WeakHashMap then we dont need explicitly define
        // WeakReference object. Otherwise in normal HashMap we need to wrap object inside WeakReference
        //In WeakHashmap, wWhen a key is discarded then its entry is automatically removed from the map ,
        // in other words, garbage collected.
        WeakHashMap<Demo, String> weakHashMap =  new WeakHashMap<>();
        Demo demo = new Demo();
        weakHashMap.put(demo, "string ");

        System.out.println(weakHashMap);

        System.gc();

        // thread sleeps for 4 sec
        try{
            Thread.sleep(4000);
        } catch (Exception ex){
            ex.printStackTrace();
        }


        System.out.println(weakHashMap);
    }

}
