package com.itheima;

import com.itheima.entiy.Product;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;

import java.util.List;

public class test {

    private SolrServer solrServer = new HttpSolrServer("http://localhost:9080/solr/collection1");

    @Test
    public void saveOrUpdate() throws Exception {
        SolrQuery sq = new SolrQuery("*:*");
        // 设置分页开始记录数
        sq.setStart(0);
        // 设置每页显示记录数
        sq.setRows(10);
        // 执行搜索，得到查询响应对象
        QueryResponse response = solrServer.query(sq);
        System.out.println("搜索到得总数量：" +
                response.getResults().getNumFound());
        // 获取搜索结果，并转化成实体集合
        List<Product> products = response.getBeans(Product.class);
        for (Product product : products) {
            System.out.println("===华丽分割线===");
            System.out.println(product.getPid() + "\t" + product.getName()
                    + "\t" + product.getCatalogName());
        }
    }
}
