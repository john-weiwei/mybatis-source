package com.cn.test;

import com.cn.dao.CommonMapper;
import com.cn.dao.TUser1Mapper;
import com.cn.datasource.DataSourceUtil;
import com.cn.pojo.ConsultConfigArea;
import com.cn.pojo.ConsultContract;
import com.cn.pojo.ConsultContractCardInfo;
import com.cn.pojo.ConsultIdCardInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Author:ZhangWeiWei
 * @Date:2021/3/17
 * @Description:
 */
public class MybatisTest {

    @Test
    public void test1() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        System.out.println(sqlSession.selectList("cn.enjoy.dao.CommonMapper.queryAreaByAreaCode", new HashMap<>()));
        //得到的是代理对象
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
        System.out.println(mapper.queryAreaByAreaCodeValue("HN1"));
//        sqlSession.commit();
    }

    @Test
    public void test2() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, DataSourceUtil.getDs1());
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(CommonMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.selectList("cn.enjoy.dao.CommonMapper.queryAreaByAreaCode", new HashMap<>());
    }

    @Test
    public void association() {
        CommonMapper mapper = getSqlSession().getMapper(CommonMapper.class);
        System.out.println(mapper.queryContractbyCardId());
    }

    @Test
    public void collection() {
        CommonMapper mapper = getSqlSession().getMapper(CommonMapper.class);
//        List<ConsultContractCardInfo> consultContractCardInfos = mapper.queryContractbyCardId();
        List<ConsultContractCardInfo> consultContractCardInfos = mapper.queryContract();
        System.out.println(consultContractCardInfos.get(0));
    }

    public SqlSession getSqlSession() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    public SqlSession getSqlSessionAutocommit() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        return sqlSession;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    public SqlSession getBatchSqlSession() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        return sqlSession;
    }

    @Test
    public void resultType() {
        CommonMapper mapper = getSqlSession().getMapper(CommonMapper.class);
        List<ConsultIdCardInfo> consultIdCardInfos = mapper.queryCardIdInfo();
        System.out.println(consultIdCardInfos);
    }

    @Test
    public void resultByAreaCode() {
        CommonMapper mapper = getSqlSession().getMapper(CommonMapper.class);
        List<ConsultConfigArea> consultIdCardInfos = mapper.queryLikeCode("HN1");
        System.out.println(consultIdCardInfos);
    }

    @Test
    public void mapParam() {
        CommonMapper mapper = getSqlSession().getMapper(CommonMapper.class);
        Map map = new HashMap();
        map.put("psptId", 456979432);
        System.out.println(mapper.queryUserByPsptId(map));
    }

    @Test
    public void AnnoParam() {
        CommonMapper mapper = getSqlSession().getMapper(CommonMapper.class);
//        System.out.println(mapper.queryUserByPsptIdParam("456979432"));
        System.out.println(mapper.queryRecordCountByPsptIdParam("456979432"));
    }

    @Test
    public void objParam() {
        CommonMapper mapper = getSqlSession().getMapper(CommonMapper.class);
        ConsultIdCardInfo info = new ConsultIdCardInfo();
        info.setPsptId("456979432");
        System.out.println(mapper.queryUserByPsptIdObj(info));
    }

    @Test
    public void useGeneratedKey() {
        SqlSession sqlSession = getSqlSession();
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
        ConsultContract consultContract = new ConsultContract();
        consultContract.setActiveTime("2021-3-9");
        consultContract.setContractCode("JK1");
        consultContract.setPsptId("456979433");
        consultContract.setState(1);
        System.out.println(mapper.saveContract(consultContract));
        System.out.println(consultContract.getContractId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectKey() {
        SqlSession sqlSession = getSqlSession();
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
        ConsultContract consultContract = new ConsultContract();
        consultContract.setActiveTime("2021-3-10");
        consultContract.setContractCode("ER");
        consultContract.setPsptId("456979432");
        consultContract.setState(1);
        System.out.println(mapper.saveContractSelectKey(consultContract));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void batchInsert() {
        SqlSession sqlSession = getBatchSqlSession();
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
        Long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ConsultContract consultContract = new ConsultContract();
            consultContract.setActiveTime("2021-3-10");
            consultContract.setContractCode("ER");
            consultContract.setPsptId("456979432");
            consultContract.setState(1);
            System.out.println(mapper.saveContractSelectKey(consultContract));
        }
        sqlSession.commit();
        sqlSession.close();
        System.out.println(System.currentTimeMillis() - t1);
    }

    @Test
    public void foreachbatchInsert() {
        SqlSession sqlSession = getSqlSession();
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
        Long t1 = System.currentTimeMillis();
        List<ConsultContract> consultContracts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ConsultContract consultContract = new ConsultContract();
            consultContract.setActiveTime("2021-3-10");
            consultContract.setContractCode("ER");
            consultContract.setPsptId("456979432");
            consultContract.setState(1);
            consultContracts.add(consultContract);
        }
        mapper.saveContracts(consultContracts);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(System.currentTimeMillis() - t1);
    }

    @Test
    public void oneLevelCache() {
        CommonMapper mapper = getSqlSession().getMapper(CommonMapper.class);
        System.out.println("=========第一次查询==========");
        System.out.println(mapper.queryAreaByAreaCode(new HashMap()));
        System.out.println("=========第二次查询==========");
        System.out.println(mapper.queryAreaByAreaCode(new HashMap()));
    }

    @Test
    public void oneLevelCacheT() {
        CommonMapper mapper = getSqlSession().getMapper(CommonMapper.class);
        System.out.println("=========第一次查询==========");
        System.out.println(mapper.queryAreaByAreaCode(new HashMap()));
        System.out.println("=========第二次查询==========");
        System.out.println(getSqlSession().getMapper(CommonMapper.class).queryAreaByAreaCode(new HashMap()));
    }

    @Test
    public void twoLevelCacheOneSqlSessionFactory() {
        HashMap hashMap = new HashMap();
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
        System.out.println("=========第一次查询==========");
        System.out.println(mapper.queryAreaByAreaCode(hashMap));
        System.out.println("=========第二次查询==========");
        System.out.println(mapper.queryAreaByAreaCode(hashMap));
        //这里会序列化到二级缓存
        sqlSession.commit();
        sqlSession.close();

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        System.out.println("=========另外的sqlSession查询第一次查询==========");
        System.out.println(sqlSession1.getMapper(CommonMapper.class).queryAreaByAreaCode(hashMap));
        sqlSession1.commit();
        sqlSession1.close();
    }

    @Test
    public void twoLevelCache() {
        HashMap hashMap = new HashMap();
        SqlSession sqlSession = getSqlSession();
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
        System.out.println("=========第一次查询==========");
        System.out.println(mapper.queryAreaByAreaCode(hashMap));
        System.out.println("=========第二次查询==========");
        System.out.println(mapper.queryAreaByAreaCode(hashMap));
        //这里会序列化到二级缓存
        sqlSession.commit();
        sqlSession.close();

        SqlSession sqlSession1 = getSqlSession();
        System.out.println("=========另外的sqlSession查询第一次查询==========");
        System.out.println(sqlSession1.getMapper(CommonMapper.class).queryAreaByAreaCode(hashMap));
        sqlSession1.commit();
        sqlSession1.close();
    }

    @Test
    public void sqlInjection() {
        SqlSession sqlSession = getSqlSessionAutocommit();
        TUser1Mapper mapper = sqlSession.getMapper(TUser1Mapper.class);
        System.out.println(mapper.selectByUserName("'' union select 1,2 from information_schema.tables;"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testArray() {
        int n = 1/2;
        System.out.println(n);
    }

}
