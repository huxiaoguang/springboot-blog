package main.blog.utils;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * sqlSessionFactory 单利类
 */
public class SessionFactoryUtil 
{
	private static  SqlSessionFactory sqlSessionFactory;
	
	public static SqlSessionFactory getSqlSessionFactory()
	{   
        if(sqlSessionFactory == null)
        {   
            InputStream inputStream;   
            try{   
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");   
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                
            }catch (IOException e)
            {   
                throw new RuntimeException(e.getCause());   
            }
        }
	    return sqlSessionFactory;   
	}
	
	/**
	 * 外部调用方法
	 */
	public static SqlSession openSession()
	{   
		return getSqlSessionFactory().openSession();   
	}
}
