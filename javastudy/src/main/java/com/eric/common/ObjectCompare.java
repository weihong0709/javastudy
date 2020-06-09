package com.eric.common;

import java.util.Comparator;

public class ObjectCompare {
    /**
     *对象之间的比较
     *Comparable,同类型比较
     *Comparator：可以跟其他类型比较
     */
    public static void main(String[] args) {
        Comparable comparable;
        Comparator comparator;
    }

    class SearchResult implements Comparable<SearchResult>{
        int relativeRadio;
        long conut;
        int recentOrders;


        @Override
        public int compareTo(SearchResult o) {
            if (this.relativeRadio!=o.relativeRadio){
                return this.relativeRadio>o.relativeRadio?1:-1;
            }
            if (this.conut!=o.conut){
                return this.conut>o.conut?1:-1;
            }
            return 0;
        }
    }


    class SerarchComparator implements Comparator<SearchResult>{

        @Override
        public int compare(SearchResult o1, SearchResult o2) {
            if (o1.relativeRadio!=o2.relativeRadio){
                return o1.relativeRadio>o2.relativeRadio?1:-1;
            }
            if (o1.conut!=o2.conut){
                return o1.conut>o2.conut?1:-1;
            }
            return 0;
        }
    }
}
