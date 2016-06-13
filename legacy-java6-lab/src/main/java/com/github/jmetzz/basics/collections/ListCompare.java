package com.github.jmetzz.basics.collections;

import com.github.jmetzz.basics.collections.sort.HeapSortCollections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ListCompare {

    static final long CAPACITY = 100000;

    static final byte MAX_AGE = 120;

    static final String DICTIONARY = "./legacy-java6-lab/src/main/resources/dictionary.txt";

    public void fillWithDouble(List<Double> list) {

        Random r = new Random(System.currentTimeMillis());
        for (int e = 0; e < CAPACITY; ++e) {
            list.add(r.nextDouble());
        }
    }

    public void fillWithPerson(List<Person> list) {

        Scanner s;
        Random r2 = new Random(6137138350987320912L);
        List<String> wordList = new ArrayList<String>(80000);
        try {
            s = new Scanner(new File(DICTIONARY));
            while (s.hasNext())
                wordList.add(s.next());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        Random r = new Random(System.currentTimeMillis());
        for (int e = 0; e < CAPACITY; ++e) {
            Person p = new Person(wordList.get(r2.nextInt(wordList.size())),
                    r.nextInt(MAX_AGE));
            list.add(p);
        }
    }

    public <T> void test(List<T> v, ArrayList<T> al, LinkedList<T> ll, String type) {

        System.out.println("Fetch testing with type " + type);
        System.out.print("\tVector ->");
        long sTime = System.currentTimeMillis();
        for (int index = 0; index < v.size(); ++index) {
            v.get(index);
        }
        long eTime = System.currentTimeMillis() - sTime;
        System.out.println("\t\t Time: " + eTime);

        System.out.print("\tArrayList ->");
        sTime = System.currentTimeMillis();
        for (int index = 0; index < al.size(); ++index) {
            al.get(index);
        }
        eTime = System.currentTimeMillis() - sTime;
        System.out.println("\t\t Time: " + eTime);

        System.out.print("\tLinkedList ->");
        sTime = System.currentTimeMillis();
        for (int index = 0; index < ll.size(); ++index) {
            ll.get(index);
        }
        eTime = System.currentTimeMillis() - sTime;
        System.out.println("\t\t Time: " + eTime);

        System.out.println("Sort testing with type " + type);
        Collections.shuffle(v);
        Collections.shuffle(al);
        Collections.shuffle(ll);

        System.out.print("\tVector ->");
        sTime = System.currentTimeMillis();
        HeapSortCollections.heapSort(v);
        eTime = System.currentTimeMillis() - sTime;
        System.out.println("\t\t Time: " + eTime);

        System.out.print("\tArrayList ->");
        sTime = System.currentTimeMillis();
        HeapSortCollections.heapSort(al);

        eTime = System.currentTimeMillis() - sTime;
        System.out.println("\t\t Time: " + eTime);

        System.out.print("\tLinkedList ->");
        sTime = System.currentTimeMillis();
        HeapSortCollections.heapSort(ll);
        eTime = System.currentTimeMillis() - sTime;
        System.out.println("\t\t Time: " + eTime);


        System.out.println("Reverse testing with type " + type);


        System.out.print("\tVector ->");
        sTime = System.currentTimeMillis();
        Collections.reverse(v);
        eTime = System.currentTimeMillis() - sTime;
        System.out.println("\t\t Time: " + eTime);

        System.out.print("\tArrayList ->");
        sTime = System.currentTimeMillis();
        Collections.reverse(al);
        eTime = System.currentTimeMillis() - sTime;
        System.out.println("\t\t Time: " + eTime);

        System.out.print("\tLinkedList ->");
        sTime = System.currentTimeMillis();
        Collections.reverse(ll);
        eTime = System.currentTimeMillis() - sTime;
        System.out.println("\t\t Time: " + eTime);


    }


    public static void main(String[] args) {
        ListCompare comp = new ListCompare();

        Vector<Double> v = new Vector<Double>();
        comp.fillWithDouble(v);
        ArrayList<Double> al = new ArrayList<Double>(v);
        LinkedList<Double> ll = new LinkedList<Double>(v);
        comp.test(v, al, ll, "Double");
        System.out.println("======================");


    }

}

class Person implements Comparable<Person> {
    private String name = null;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

    @Override
    public int compareTo(Person p) {
        int alpha = this.name.compareTo(p.getName());
        return (alpha != 0 ? alpha : this.age - p.getAge());
    }

}
