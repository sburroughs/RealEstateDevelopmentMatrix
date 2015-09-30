package com.fingerprint.nestwood.matrix.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by SBurroug on 9/27/2015.
 */
public abstract class MatrixDAO {

    Logger logger = LogManager.getLogger(MatrixDAO.class);

    private ComboPooledDataSource cpds;

    protected MatrixDAO() throws IOException, PropertyVetoException {

        Properties properties = loadProperties();
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        if (null != url && null != username && null != password) {
            instantiateConnectionPool(url, username, password);
        } else {
            throw new IOException("Unable to configure MatrixDAO with properties file");
        }
    }


    /**
     * Requires following properties:<br>
     * <ul>
     * <li>url</li>
     * <li>username</li>
     * <li>password</li>
     * </ul>
     *
     * @return
     * @throws IOException
     */
    private Properties loadProperties() throws IOException {

        String resourceName = "matrix_config.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties prop = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            prop.load(resourceStream);
        }

        return prop;
    }

    private void instantiateConnectionPool(String url, String username, String password) throws PropertyVetoException {

        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl(url);
        cpds.setUser(username);
        cpds.setPassword(password);
    }


    protected Connection getConnection() throws SQLException {
        logger.debug("Retrieving Connection from Connection Pool");
        return cpds.getConnection();
    }


}
