package com.mapper;


import com.po.MessageCustom;
import com.po.MessageVo;

/*
 mapper接口:相当于dao接口，用户管理
 使用mapper代理的形式：其接口中的方法定义注意4点规范：
 （1）在对应的映射文件MessageMapper中的namespace等于mapper接口地址（也就是所谓的原始的dao地址）
 （2）方法名要和MessageMapper的代理映射文件中的statement的id一致
 （3）方法中的参数类型要和statement的paramterType的类型一致
 （4）方法中的返回参数类型要和statement的resultType类型一致
 */
public interface MessageMapper {

    public MessageCustom findMessageById(int id ) throws  Exception;

    public void insertMessage(MessageVo messageVo) throws Exception;
}


