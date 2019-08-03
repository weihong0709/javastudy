package com.eric.stream;



import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Optional为值得容器，使用Optional可以避免我们一直以来的空指针烦恼
 */
public class OptionalTest {

    /**
     * 测试创建Optional方法
     */
    @Test()
    public void testCreate(){
        Optional<String> optional = Optional.of("test");
        assertEquals("test",optional.get());
        //不能为空,为空抛出空指针异常
        //Optional<String> optionalNull = Optional.of(null);
        //empty创建的是空的optional，ofNullable方法可以把null值创建为空的optional
        Optional<String > empty = Optional.empty();
        Optional<String> optionalNull = Optional.ofNullable(null);
        //空的容器通过get方法去获取值会抛出NoSuchElementException异常
        //empty.get();
        assertThrows(NoSuchElementException.class,()-> empty.get());
        //optionalNull.get();
        assertThrows(NoSuchElementException.class,()-> optionalNull.get());
        assertFalse(optionalNull.isPresent());
        assertFalse(empty.isPresent());
    }

    /**
     * 创建
     */
    @Test
    public void testGet(){
        Optional<String> optional = Optional.of("test");
        Optional<String> optionalNull = Optional.ofNullable(null);

        assertEquals("test",optional.get());
        //获取Optional中的值，不存在，直接返回"temp"
        assertEquals("temp",optionalNull.orElse("temp"));
        assertEquals("temp2",optionalNull.orElseGet(()->"temp2"));
        optionalNull.orElseThrow(()->new RuntimeException("error"));
    }
}
