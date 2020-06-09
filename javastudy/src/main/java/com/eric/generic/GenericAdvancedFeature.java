package com.eric.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericAdvancedFeature {
    /*
    *
    * 泛型的协变和逆变
    * */

    public static void main(String[] args) {
        ArrayList<GrandParent> grandParents = new ArrayList<>();
        ArrayList<Parent> parents = new ArrayList<>();
        ArrayList<Children> childrens = new ArrayList<>();


        List list1 = new ArrayList();
        /*
        * <? extends Parent> 称为泛型的协变,可以赋给T和T子类的集合，上界为T,put受限，
        * 除null外，添加不了任何元素
        * */
        List<? extends Parent> list2 = list1;
        List<? extends GrandParent> list3 = list1;
        printList1(list1);
        printList1(list2);
        //取出来子类型被擦除
        Parent parrent = list2.get(0);
        //printList1(list3);
        //list2.add(new Parent("eric"));

        /*
         * <? super Parent> 称为泛型的逆变,可以赋给T和T父类的集合，下界为T，get受限
         * 可以添加元素
         * */
        List<? super Parent> list4 = list1;
        list4.add(new Parent("parent"));
        list4.add(new Children("eric"));
        //取出来类型被擦除
        Object parent = list4.get(0);
        //list4.add(new GrandParent("grandparent"));
        printList2(list4);
        printList2(list1);
        //printList2(list2);
    }

    static void printList1(List<? extends Parent> list){
        for (Parent parent:list){
            System.out.println(parent.getName());
        }
    }

    static void printList2(List<? super Parent> list){
        for (Object parent:list){
            //System.out.println(parent.getName());
        }
    }
}
