package com.bawei.goodsshopping.test;

/**
 * Created by Administrator on 2016/4/4.
 *
 *
 * used to study generic    泛型
 *
 * 泛型，就是允许在定义类、接口、方法时使用类型形参，这个类型形参将在声明变量、创建对象、调用方法时动态地指定（即传入实际的类型参数，也可称为类型实参）
 */

/**
 * 创建带泛型声明的父类
 * @param <E>
 */
public class GenericTestDemo01<E> {

    private E info;

    public GenericTestDemo01() {

        /**
         * 当创建带泛型声明的自定义类，为该类定义构造器时，构造器名还是原来的类名，
         * 不要增加泛型声明。
         * 调用该构造器时却可以使用泛型形式。
         * 为泛型形参，传入实际的参数值。
         */

    }

    public void setInfo(E info){
        this.info = info;
    }
    public E getInfo(){

        return this.info;
    }
    public void info(){
        System.out.print(getInfo());
    }

    public static void main(String[] args){


        GenericTestDemo01<Number> genericStr = new GenericTestDemo01<>();
        genericStr.setInfo(0x134);
        genericStr.info();





    }


}
