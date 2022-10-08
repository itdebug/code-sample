package org.example.sort;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class SortTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void sort1() {
        List<User> users = Lists.newArrayList();
        User user1 = User.builder().name("张三").age(10).build();
        User user2 = User.builder().name("李四").age(5).build();
        User user3 = User.builder().name("王五").age(30).build();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        sort1(users);
        users.stream().forEach(x -> System.out.print(x.getAge() + "...."));
        System.out.println();
        System.out.println("--------stream().sorted return---------------");
        users = sort2(users);
        users.stream().forEach(x -> System.out.print(x.getAge() + "...."));
        System.out.println();
        System.out.println("--------Collections.sort---------------");
        sort3(users);
        users.stream().forEach(x -> System.out.print(x.getAge() + "...."));
    }


    public void sort1(List<User> userList) {
        /**
         * 生成新的对象，新的地址
         */
        userList.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
    }

    public List<User> sort2(List<User> userList) {
        return userList.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
    }

    public void sort3(List<User> userList) {
        Collections.sort(userList, Comparator.comparing(User::getAge));
    }
}
