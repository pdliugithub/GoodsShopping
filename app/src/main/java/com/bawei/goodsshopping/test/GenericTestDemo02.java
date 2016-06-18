package com.bawei.goodsshopping.test;

/**
 * Created by Administrator on 2016/4/4.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 调用方法时必须为所有的数据形参传入参数值，
 * 与调用方法不同的是，使用类、接口时也可以不为类型形参传入实际的类型形参，
 * 如果使用类、接口是没有传入实际的类型参数，系统会把，形参当成Object类型处理
 *
 */
public class GenericTestDemo02 extends GenericTestDemo01{


    /**
     * 不管为泛型的泛型参数传入哪一种类型实参，对于Java来说，他们依然被当成同一个类处理，
     * 在内存中只占用一块内存空间，因此在静态方法、静态初始化快或则静态变量的声明和初始化中不允许使用类型形参
     */
    //不能在静态变量中使用类型形参
//    static T info;

    static List list = new ArrayList();
    public static void main(String[] args){



    }

}
