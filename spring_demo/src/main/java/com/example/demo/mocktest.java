package com.example.demo;

import org.junit.Assert;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;


/**
 * @author yangxc27652
 * @date 2020/6/30
 * @description  mock测试工具
 */
public class mocktest {



    public static void main(String[] args) {


        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.add("one")).thenReturn(true);

       // RequestCheckServiceImpl service = mock(RequestCheckServiceImpl.class);
        when(mockedList.add("two")).thenReturn(false);
        when(mockedList.size()).thenReturn(1);
        Assert.assertTrue(mockedList.add("one"));
        Assert.assertFalse(mockedList.add("two"));

        System.out.println(mockedList.add("one"));
        System.out.println(mockedList.size());
        System.out.println(mockedList.add("three"));


        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello,").thenReturn("Mockito!"); // 1
        String result = i.next() + " " + i.next(); // 2
        Assert.assertEquals("Hello, Mockito!", result);
        System.out.println(result);
        doThrow(new NoSuchElementException()).when(i).next(); // 3


        List mockedList1 = mock(List.class);
        mockedList1.add("one");
        mockedList1.add("two");
        mockedList1.add("three times");
        mockedList1.add("three times");
        mockedList1.add("three times");
        when(mockedList1.size()).thenReturn(5);
        Assert.assertEquals(mockedList1.size(), 5);

        verify(mockedList1, atLeastOnce()).add("one");
        verify(mockedList1, times(1)).add("two");
        verify(mockedList1, times(2)).add("three times");
        verify(mockedList1, never()).isEmpty();

    }
}
