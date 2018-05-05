package com.mapper;

import com.mapper.MessageMapper;
import com.po.MessageVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by Administrator on 2018/1/30.
 */
public class UserMapperTest {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void setUp() throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void insertMessage() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MessageMapper messageMapper=sqlSession.getMapper(MessageMapper.class);
        //创建包装对象
        MessageVo messageVo = new MessageVo();
        com.po.Message message = new com.po.Message();
        message.setMes("hello");
        messageVo.setMessage(message);

        System.out.println("插入前主键："+messageVo.getMessage().getId());
        messageMapper.insertMessage(messageVo);
        System.out.println("插入前主键："+messageVo.getMessage().getId());
        sqlSession.commit();
        sqlSession.close();
    }


}