package org.larinde.epay.ds;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class AbstractIntegrationTest {
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void populateTestData(){
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("test_data.sql"));
		
		Connection connection = DataSourceUtils.getConnection(dataSource);
		try {
			populator.populate(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (connection!=null) {
				DataSourceUtils.releaseConnection(connection, dataSource);
			}
		}
	}

}
