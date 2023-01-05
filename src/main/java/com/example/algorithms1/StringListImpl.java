package com.example.algorithms1;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] data;
    private int size=0;

    public StringListImpl() {
        this.data = new String[]{};
    }

    @Override//Добавление элемента в конец массива
    public String add(String item) {
        checkItemIsNull(item);
        grow();
        size++;
        this.data[this.data.length-1]=item;
        return item;
    }

    @Override//добавление элемента по индексу, со сдвигом вправо
    public String add(int index, String item) {
        checkItemIsNull(item);
        checkIndex(index);
        grow();
        size++;
        System.arraycopy(this.data,index,this.data,index+1,this.data.length-index-1);
        this.data[index] = item;
        return item;
    }


    @Override // добавление элемента по индексу с удалением предыдущего элемента
    public String set(int index, String item) {
        checkItemIsNull(item);
        checkIndex(index);
        this.data[index] = item;
        return item;
    }


    @Override//удаление всех элементов, соответствующих передаваемому значению
    //наверняка существует более изящное решение, но имеем что имеем
    public String remove(String item) {
        checkItemIsNull(item);
        //если массив состоит из одного элемента и его надо удалить
        if (this.data.length == 1&&contains(item)) {
            decrease();}
        for (int i = 0; i < this.data.length; i++) {//итерируемся по массиву
            do {
                //если элемент, который надо удалить находится в конце массива, то просто копируем массив,
                // уменьшая размер на 1. для остальных случаев (элемент в начале или в середине) - следующее if
                if
                (this.data[this.data.length - 1].equals(item)) {
                    decrease();
                }
                // найденный удаляем элемент при помощи копирования массива в тот массив же со сдвигом влево
                else if
                (this.data[i].equals(item)) {
                    System.arraycopy(this.data, i + 1, this.data, i, this.data.length - i - 1);
                    decrease();//чтобы не было копирования элемента в конце
                }
            } while (this.data[i].matches(item));//выполнять действие, пока в массиве есть заданный элемент
        }
        return item;
    }

    @Override//удаление элемента по индексу
    public String remove(int index) {
        checkIndex(index);
        String removedItem = this.data[index];//новая переменная чтобы вернуть удаленный элемент
        if (index == this.data.length - 1) {
            decrease();
        }//если индекс - последний в списке, то удаляем обычным
        // копированием массива с уменьшением количества ячеек
        else {System.arraycopy(this.data,index+1,this.data,index,this.data.length-index-1);
        decrease();}//в остальных случаях - копированием со смещением
        return removedItem;
    }

    @Override//проверка на наличие элемента в списке
    public boolean contains(String item) {
        checkItemIsNull(item);
        for (String datum : this.data) {
            if (datum.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override//найти индекс элемента, если таковой есть в списке (итерация с начала списка)
    public int indexOf(String item) {
        checkItemIsNull(item);
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override//найти индекс элемента, если таковой есть в списке (итерация с конца списка)
    public int lastIndexOf(String item) {
        checkItemIsNull(item);
        for (int i = this.data.length-1; i > 0; i--) {
            if (this.data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override//получить элемент по индексу
    public String get(int index) {
        checkIndex(index);
        return this.data[index];
    }

    @Override//отредактировал метод equals класса Object
    public boolean equals(StringList otherList) {
        if (this == otherList) return true;
        if (otherList == null || getClass() != otherList.getClass()) return false;
        StringListImpl list = (StringListImpl) otherList;
        return size == list.size && Arrays.equals(data, list.data);
    }

    @Override
    public int size() {//подробности в методе grow()
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.data.length == 0;
    }

    @Override//удаление всех элементов структуры данных
    public void clear() {
        this.data = Arrays.copyOf(this.data, 0);
        size = this.data.length;
    }

    @Override//объект класса StringListImpl в объект класса Array
    public String[] toArray() {
        return Arrays.copyOf(this.data,this.data.length);
    }

    @Override//распечатать массив
    public String toString() {
        return Arrays.toString(data);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.data.length) {
            throw new IllegalArgumentException("Указан неверный индекс элемента");
        }
    }

    private void checkItemIsNull(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Объект списка не может быть null");
        }
    }
    private void grow() {
        this.data = Arrays.copyOf(this.data, this.data.length + 1);
    }

    private void decrease() {
        this.data = Arrays.copyOf(this.data, this.data.length - 1);
        size--;
    }
}
