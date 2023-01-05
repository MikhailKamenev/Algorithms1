package com.example.algorithms1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringListImplTest {
    StringListImpl stringList;

    @BeforeEach
    public void setUp() {
        this.stringList = new StringListImpl();
    }

    @Test()
    public void whenPutItemThenSizeShouldBeIncreaseInOne(){
        this.stringList.add("first");
        this.stringList.add("second");
        this.stringList.add(0, "fff");
        Assertions.assertEquals(3, this.stringList.size());
        Assertions.assertEquals("fff", this.stringList.get(0));
        Assertions.assertEquals("first", this.stringList.get(1));
    }

    @Test
    public void whenIndexOrItemAreIncorrectThrowAnException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.add(2, "fff"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.set(2, "fff"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.remove(2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.get(2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.add(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.add(0,"null"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.remove(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.remove(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.indexOf(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.lastIndexOf(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.stringList.contains(null));
    }

    @Test
    public void whenRemoveItemThenItemDisappeared() {
        String item = "nothing";
        this.stringList.add(item);
        Assertions.assertEquals(item,this.stringList.remove(item));//проверка что элемент возвращается при успешно выполненном методе
        Assertions.assertEquals(0,this.stringList.size());// проверка что при этом методе также изменяется size
        this.stringList.add("1");
        this.stringList.add("2");
        this.stringList.add("3");
        Assertions.assertEquals("2",this.stringList.remove(1));//проверка что элемент удаляется по индексу
    }

    @Test
    public void whenContainItemReturnTrue() {
        this.stringList.add("1");
        Assertions.assertTrue(this.stringList.contains("1"));
    }

    @Test
    public void whenFindItemReturnItsIndex() {
        this.stringList.add("1");
        this.stringList.add("2");
        this.stringList.add("3");
        this.stringList.add("2");
        Assertions.assertEquals(0,this.stringList.indexOf("1"));
        Assertions.assertEquals(1,this.stringList.indexOf("2"));
        Assertions.assertEquals(3,this.stringList.lastIndexOf("2"));
    }
}
