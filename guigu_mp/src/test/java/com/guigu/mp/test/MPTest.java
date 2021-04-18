package com.guigu.mp.test;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.mp.MPApplication;
import com.guigu.mp.mapper.UserMapper;
import com.guigu.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MPTest {

    @Autowired
    public UserMapper userMapper;

    @Test
    public void test1() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {

        User user = new User();
        int num = 100;
        Random random = new Random();
        String randomString = "abcdefghijklmnopqrstuvwxyz";
        int nameLength = 6;
        for (int i = 0; i < num; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < nameLength; j++) {
                sb.append(randomString.charAt(random.nextInt(16)));
            }
            user.setName(sb.toString());
            user.setAge(random.nextInt(num + 1));
            user.setEmail(random.nextInt() + "@qq.com");
            int result = userMapper.insert(user);
            System.out.println(result); //影响的行数
            System.out.println(user); //id自动回填
        }

        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }

    @Test
    public void testUpdate() {
        User user = userMapper.selectById(Long.parseLong("1381437849127199333"));
        user.setName("LWB");
        userMapper.updateById(user);
    }

    @Test
    public void selectUserTest() {
        User user = userMapper.selectById(Long.parseLong("1381437849127199333"));
        System.out.println(user);

        List<Long> list = new ArrayList<Long>();
        list.add(1381437849127199333l);
        list.add(1381437849127199331l);
        list.add(1381437849127199335l);
        userMapper.selectBatchIds(list);
    }

    @Test
    public void selectByWrapperTest() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("age", 55);
        userMapper.selectList(wrapper);
    }

    @Test
    public void selectByPageTest() {
        Page<User> page = new Page<User>(1,5);
        userMapper.selectPage(page,null);

        List<User> records = page.getRecords();
        records.forEach(System.err::println);
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getPages());//总页数
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.hasPrevious());//是否有上一页
    }

    @Test
    public void deleteUserTest() {
        userMapper.deleteById(1381437849127199333l);
    }
}
