package com.service;


import com.google.gson.Gson;
import com.mapper.MessageMapper;
import com.po.Message;
import com.po.MessageVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import static sun.plugin2.util.PojoUtil.toJson;

public class insertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("content");
        if(null==content||content.equals("")) return;
        content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
        MessageVo messageVo = new MessageVo();
        Message message = new Message();
        message.setMes(content);
        messageVo.setMessage(message);

        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
        try {
            messageMapper.insertMessage(messageVo);
            int id = messageVo.getMessage().getId();

            sqlSession.commit();
            Gson gson = new Gson();
            String data = gson.toJson(id);
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
