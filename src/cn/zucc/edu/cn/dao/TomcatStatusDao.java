package cn.zucc.edu.cn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import cn.zucc.edu.model.Memory;
import cn.zucc.edu.model.Memory_Pool;

public class TomcatStatusDao {
	 private Connection conn = null;

	    private Statement st = null;

	    private Statement st2 = null;

	    private PreparedStatement pst1 = null;
	    String dbDriver ="com.mysql.cj.jdbc.Driver"; 
	   	String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/tomcat_status?useSSL=true&serverTimezone=GMT%2B8";
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
	    public synchronized int add_connector(String name,int maxThreads,int currentThreadCount,int currentThreadsBusy,int maxTime,int processingTime,int requestCount,int errorCount,int bytesReceived,int bytesSent,int port) throws SQLException {
	    	String sql ="insert into connector(name,maxThreads,currentThreadCount,currentThreadsBusy,maxTime,processingTime,requestCount,errorCount,bytesReceived,bytesSent,time,port) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	    	PreparedStatement pst=null;
	    	int status=-1;
	    	try {
				pst = this.conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setInt(2, maxThreads);
				pst.setInt(3, currentThreadCount);
				pst.setInt(4, currentThreadsBusy);
				pst.setInt(5, maxTime);
				pst.setInt(6, processingTime);
				pst.setInt(7, requestCount);
				pst.setInt(8, errorCount);
				pst.setInt(9, bytesReceived);
				pst.setInt(10, bytesSent);
				pst.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
				pst.setInt(12, port);
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
	    	String sql="insert into memory_pool(Name,Type,Initial,Committed,Maximum,Used,Port,Time) values(?,?,?,?,?,?,?,?)";
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
				pst.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
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
	    	String sql="insert into memory(Free,Total,Max,Port,Time) values(?,?,?,?,?)";
			PreparedStatement pst=null;
	try {
		pst = this.conn.prepareStatement(sql);
		pst.setDouble(1, memory.getFree());
		pst.setDouble(2, memory.getTotal());
		pst.setDouble(3, memory.getMax());
		pst.setInt(4, memory.getPort());
		pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
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
