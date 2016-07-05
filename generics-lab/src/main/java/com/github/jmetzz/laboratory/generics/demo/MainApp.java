package com.github.jmetzz.laboratory.generics.demo;


public class MainApp {


    public static void main(String[] args) {
        Favorites favorites = new Favorites();

        favorites.putFavorite(String.class, "Java");
        favorites.putFavorite(Integer.class, 0xcafebabe);
        favorites.putFavorite(Class.class, ThreadLocal.class);

        String str = favorites.getFavorite(String.class);
        System.out.println(str);

        int i = favorites.getFavorite(Integer.class);
        System.out.printf("%x \n", i);

        Class<?> clazz = favorites.getFavorite(Class.class);
        System.out.println(clazz);

    }
}
