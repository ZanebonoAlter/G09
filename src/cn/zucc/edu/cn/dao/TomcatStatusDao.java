package cn.zucc.edu.cn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import cn.zucc.edu.model.Connector;
import cn.zucc.edu.model.Memory;
import cn.zucc.edu.model.Memory_Pool;

public class TomcatStatusDao {
	 private Connection conn = null;

	    private Statement st = null;

	    private Statement st2 = null;

	    private PreparedStatement pst1 = null;
	    String dbDriver ="com.mysql.cj.jdbc.Driver"; 
	   	String jdbcUrl = "jdbc:mysql://localhost:3306/tomcat_status?useSSL=true&serverTimezone=GMT%2B8";
	   	String dbUser = "root";
	   	String dbPwd = "oracle";
	    public TomcatStatusDao() {
	        try {
	        	Class.forName(this.dbDriver);
	        	conn = DriverManager.getConnection(this.jdbcUrl,this.dbUser,this.dbPwd);
	            st = conn.createStatement();
	            st2 = conn.createStatement();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    public synchronized void release() {
	        if (st != null) {
	            try {
	                st.close();
	            } catch (Exception e) {
	            }
	        }
	        if (st2 != null) {
	            try {
	                st2.close();
	            } catch (Exception e) {
	            }
	        }

	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (Exception e) {
	            }
	        }

	    }
	    public synchronized int add_connector(Connector connector) throws SQLException {
	    	String sql ="insert into connector(name,maxThreads,currentThreadCount,currentThreadsBusy,maxTime,processingTime,requestCount,errorCount,bytesReceived,bytesSent,time,port,ipAddress) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    	PreparedStatement pst=null;
	    	int status=-1;
	    	try {
				pst = this.conn.prepareStatement(sql);
				pst.setString(1, connector.getName());
				pst.setInt(2, connector.getMaxThreads());
				pst.setInt(3, connector.getCurrentThreadCount());
				pst.setInt(4, connector.getCurrentThreadsBusy());
				pst.setLong(5, connector.getMaxTime());
				pst.setLong(6, connector.getProcessingTime());
				pst.setInt(7, connector.getRequestCount());
				pst.setInt(8, connector.getErrorCount());
				pst.setLong(9, connector.getBytesReceived());
				pst.setLong(10, connector.getBytesSent());
				pst.setTimestamp(11, connector.getTime());
				pst.setInt(12, connector.getPort());
				pst.setString(13, connector.getIpAddress());
				status=pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				pst.close();
			}
	    	return status;
	    }
	    public synchronized int add_memory_pool(Memory_Pool memory_pool) {
	    	int status=-1;
	    	String sql="insert into memory_pool(Name,Type,Initial,Committed,Maximum,Used,Port,Time,ipAddress) values(?,?,?,?,?,?,?,?,?)";
	    			PreparedStatement pst=null;
	    	try {
				pst = this.conn.prepareStatement(sql);
				pst.setString(1, memory_pool.getName());
				pst.setString(2, memory_pool.getType());
				pst.setDouble(3, memory_pool.getInitial());
				pst.setDouble(4, memory_pool.getCommitted());
				pst.setDouble(5, memory_pool.getMaximum());
				pst.setDouble(6, memory_pool.getUsed());
				pst.setInt(7, memory_pool.getPort());
				pst.setTimestamp(8, memory_pool.getTime());
				pst.setString(9, memory_pool.getIpAddress());
				status=pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	return status;
	    }
	    public synchronized int add_memory(Memory memory) {
	    	int status=-1;
	    	String sql="insert into memory(Free,Total,Max,Port,Time,ipAddress) values(?,?,?,?,?,?)";
			PreparedStatement pst=null;
	try {
		pst = this.conn.prepareStatement(sql);
		pst.setDouble(1, memory.getFree());
		pst.setDouble(2, memory.getTotal());
		pst.setDouble(3, memory.getMax());
		pst.setInt(4, memory.getPort());
		pst.setTimestamp(5, memory.getTime());
		pst.setString(6, memory.getIpAddress());
		status=pst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	    	return status;
	    }
}
