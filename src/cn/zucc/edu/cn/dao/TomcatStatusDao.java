package cn.zucc.edu.cn.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Properties;

import cn.zucc.edu.model.Config;
import cn.zucc.edu.model.Connecting;
import cn.zucc.edu.model.Connector;
import cn.zucc.edu.model.Memory;
import cn.zucc.edu.model.Memory_Pool;

public class TomcatStatusDao {
	 private Connection conn = null;

	    private Statement st = null;

	    private Statement st2 = null;

	    private   String driver;
	    private   String url;
	    private   String username;
	    private   String password;
	    private static final String CONFIGNAME = "/config.properties";
	    private static Properties prop;
	    public TomcatStatusDao() {
	    	String property = System.getProperty("user.dir");
	        try {
	        	prop = new Properties();
	        	 InputStream in = new BufferedInputStream(new FileInputStream(property+CONFIGNAME));
	             prop.load(in);
	        	//InputStream fis =TomcatStatusDao.class.getClassLoader().getResourceAsStream(CONFIGNAME);   
	        	//prop.load(fis);         	
	        	Class.forName(prop.getProperty("dbDriver"));
	        	conn = DriverManager.getConnection(prop.getProperty("jdbcUrl"),prop.getProperty("dbUser"),prop.getProperty("dbPwd"));
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
	    //判断是否存在连接
	    public synchronized int Judge_connecting(String ipAddress,int port) {
	    	int status = -1;
	    	String sql = "select * from connecting where ipAddress= ? and port = ?";
	    	PreparedStatement pst=null;
	    	ResultSet rs = null;
	    	try {
	    		pst = this.conn.prepareStatement(sql);
	    		pst.setString(1, ipAddress);
	    		pst.setInt(2, port);
	    		rs=pst.executeQuery();
	    		if(rs.next())
	    			status=1;
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
	    public synchronized int add_connecting(Connecting conn) {
	    	int status = -1;
	    	String sql = "insert into connecting(ipAddress,port,status,name,last_time) values(?,?,?,?,?)";
	    	PreparedStatement pst=null;
	    	try {
	    		pst = this.conn.prepareStatement(sql);
	    		pst.setString(1, conn.getIpAddress());
	    		pst.setInt(2, conn.getPort());
	    		pst.setString(3, conn.getStatus());
	    		pst.setString(4, conn.getName());
	    		pst.setTimestamp(5, conn.getLast_time());
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
	    public synchronized int update_connecting(Connecting conn) {
	    	int status = -1;
	    	String sql = "update connecting set status = ?,last_time=? where ipAddress= ? and port = ?";
	    	PreparedStatement pst=null;
	    	try {
	    		pst = this.conn.prepareStatement(sql);
	    		pst.setString(1, conn.getStatus());
	    		pst.setTimestamp(2, conn.getLast_time());
	    		pst.setString(3, conn.getIpAddress());
	    		pst.setInt(4, conn.getPort());
	    		status = pst.executeUpdate();
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
	    public synchronized int Judge_config(String ipAddress,int port) {
	    	int status = -1;
	    	String sql = "select * from config where ipAddress= ? and port = ?";
	    	PreparedStatement pst=null;
	    	ResultSet rs = null;
	    	try {
	    		pst = this.conn.prepareStatement(sql);
	    		pst.setString(1, ipAddress);
	    		pst.setInt(2, port);
	    		rs=pst.executeQuery();
	    		if(rs.next())
	    			status=1;
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
	    public synchronized int add_config(Config config) {
	    	int status = -1;
	    	String sql = "insert into config(ipAddress,port,memory_total,memory_pool_Compressed_Class_Space_used,memory_pool_Code_Cache_used,memory_pool_Metaspace_used,memory_pool_PS_Eden_Space_used,memory_pool_PS_Old_Gen_used,memory_pool_PS_Survivor_Space_used,connector_currentThreadsCount,connector_currentThreadsBusy) values(?,?,?,?,?,?,?,?,?,?,?)";
	    	PreparedStatement pst=null;
	    	try {
	    		pst = this.conn.prepareStatement(sql);
	    		pst.setString(1, config.getIpAddress());
	    		pst.setInt(2, config.getPort());
	    		pst.setDouble(3, config.getMemory_total());
	    		pst.setDouble(4, config.getMemory_pool_Compressed_Class_Space_used());
	    		pst.setDouble(5, config.getMemory_pool_Code_Cache_used());
	    		pst.setDouble(6, config.getMemory_pool_Metaspace_used());
	    		pst.setDouble(7, config.getMemory_pool_PS_Eden_Space_used());
	    		pst.setDouble(8, config.getMemory_pool_PS_Old_Gen_used());
	    		pst.setDouble(9, config.getMemory_pool_PS_Survivor_Space_used());
	    		pst.setDouble(10, config.getConnector_currentThreadsCount());
	    		pst.setDouble(11, config.getConnector_currentThreadsBusy());
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
	    public synchronized void Judge_memory_pool(Memory_Pool memory_pool) {
	    	String sql = "select memory_pool_Compressed_Class_Space_used,memory_pool_Code_Cache_used,memory_pool_Metaspace_used,memory_pool_PS_Eden_Space_used,memory_pool_PS_Old_Gen_used,memory_pool_PS_Survivor_Space_used from config where ipAddress= ? and port = ?";
	    	PreparedStatement pst=null;
	    	ResultSet rs = null;
	    	double memory_pool_Compressed_Class_Space_used=0.8;
	    	double memory_pool_Code_Cache_used=0.8;
	    	double memory_pool_Metaspace_used =0.8;
	    	double memory_pool_PS_Eden_Space_used = 0.9;
	    	double memory_pool_PS_Old_Gen_used = 0.8;
	    	double memory_pool_PS_Survivor_Space_used = 1;
	    	try {
	    		pst = this.conn.prepareStatement(sql);
	    		pst.setString(1, memory_pool.getIpAddress());
	    		pst.setInt(2, memory_pool.getPort());
	    		rs=pst.executeQuery();
	    		if(rs.next()) {
	    			 memory_pool_Compressed_Class_Space_used=rs.getDouble(1);
	    	    	 memory_pool_Code_Cache_used=rs.getDouble(2);
	    	    	 memory_pool_Metaspace_used =rs.getDouble(3);
	    	    	 memory_pool_PS_Eden_Space_used = rs.getDouble(4);
	    	    	 memory_pool_PS_Old_Gen_used = rs.getDouble(5);
	    	    	 memory_pool_PS_Survivor_Space_used = rs.getDouble(6);
	    		}
	    	} catch (SQLException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	//出现错误
	    	double memory_pool_switch=1;
	    	if(memory_pool.getName().equals("Code Cache")) {
	    		memory_pool_switch=memory_pool_Code_Cache_used;
	    	}
	    	if(memory_pool.getName().equals("Compressed Class Space")) {
	    		memory_pool_switch=memory_pool_Compressed_Class_Space_used;
	    	}
	    	if(memory_pool.getName().equals("Metaspace")) {
	    		memory_pool_switch=memory_pool_Metaspace_used;
	    	}
	    	if(memory_pool.getName().equals("PS Eden Space")) {
	    		memory_pool_switch=memory_pool_PS_Eden_Space_used;
	    	}
	    	if(memory_pool.getName().equals("PS Old Gen")) {
	    		memory_pool_switch=memory_pool_PS_Old_Gen_used;
	    	}
	    	if(memory_pool.getName().equals("PS Survivor Space")) {
	    		memory_pool_switch=memory_pool_PS_Survivor_Space_used;
	    	}
	    	if((memory_pool.getUsed()*1.0/memory_pool.getMaximum())>memory_pool_switch) {
    			sql="insert into log(ipAddress,port,time,error_type,describe_message,read_status,send_status) values(?,?,?,?,?,?,?)";
    			try {
					pst = this.conn.prepareStatement(sql);
					pst.setString(1, memory_pool.getIpAddress());
		    		pst.setInt(2, memory_pool.getPort());
		    		pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		    		pst.setString(4, "Memory_Pool");
		    		pst.setString(5,"内存池使用警告:"+memory_pool.getName()+":"+(memory_pool.getUsed()/memory_pool.getMaximum())*100+"% > "+memory_pool_switch*100+"%");
		    		pst.setString(6, "not read");
		    		pst.setString(7, "not send");
		    		pst.executeUpdate();
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
    		}
	    	
	    }
	    public synchronized void Judge_connector(Connector connector) {
	    	String sql = "select connector_currentThreadsCount,connector_currentThreadsBusy from config where ipAddress= ? and port = ?";
	    	PreparedStatement pst=null;
	    	ResultSet rs = null;
	    	double connector_currentThreadsCount=0.8;
	    	double connector_currentThreadsBusy=0.8;
	    	//int connector_errorCount=1;
	    	try {
	    		pst = this.conn.prepareStatement(sql);
	    		pst.setString(1, connector.getIpAddress());
	    		pst.setInt(2, connector.getPort());
	    		rs=pst.executeQuery();
	    		if(rs.next()) {
	    			 connector_currentThreadsCount = rs.getDouble(1);
	    			 connector_currentThreadsBusy = rs.getDouble(2);
	    			 //connector_errorCount = rs.getInt(3);
	    			 //System.out.println(connector_currentThreadsCount+" "+connector_currentThreadsBusy+" "+connector_errorCount);
	    		}
	    	} catch (SQLException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	//System.out.println((connector.getCurrentThreadCount()*1.0/connector.getMaxThreads()));
	    	//System.out.println((connector.getCurrentThreadCount()*1.0/connector.getMaxThreads())>connector_currentThreadsCount);
	    	
	    	//出现错误
	    	if((connector.getCurrentThreadCount()*1.0/connector.getMaxThreads())>connector_currentThreadsCount) {
    			sql="insert into log(ipAddress,port,time,error_type,describe_message,read_status,send_status) values(?,?,?,?,?,?,?)";
    			try {
					pst = this.conn.prepareStatement(sql);
					pst.setString(1, connector.getIpAddress());
		    		pst.setInt(2, connector.getPort());
		    		pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		    		pst.setString(4, "Connector");
		    		pst.setString(5,"线程数量警告:"+"currentThreadsCount:"+(connector.getCurrentThreadCount()/connector.getMaxThreads())*100+"% > "+connector_currentThreadsCount*100+"%");
		    		pst.setString(6, "not read");
		    		pst.setString(7, "not send");
		    		pst.executeUpdate();
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
    		} 
	    	if((connector.getCurrentThreadsBusy()*1.0/connector.getMaxThreads())>connector_currentThreadsBusy) {
    			sql="insert into log(ipAddress,port,time,error_type,describe_message,read_status,send_status) values(?,?,?,?,?,?,?)";
    			try {
					pst = this.conn.prepareStatement(sql);
					pst.setString(1, connector.getIpAddress());
		    		pst.setInt(2, connector.getPort());
		    		pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		    		pst.setString(4, "Connector");
		    		pst.setString(5,"繁忙线程数量警告:"+"currentThreadsBusy:"+(connector.getCurrentThreadsBusy()/connector.getMaxThreads())*100+"% > "+connector_currentThreadsBusy*100+"%");
		    		pst.setString(6, "not read");
		    		pst.setString(7, "not send");
		    		pst.executeUpdate();
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
    		}
//	    	if(connector.getErrorCount()>=connector_errorCount) {
//    			sql="insert into log(ipAddress,port,time,error_type,describe_message,read_status,send_status) values(?,?,?,?,?,?,?)";
//    			try {
//					pst = this.conn.prepareStatement(sql);
//					pst.setString(1, connector.getIpAddress());
//		    		pst.setInt(2, connector.getPort());
//		    		pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//		    		pst.setString(4, "Connector");
//		    		pst.setString(5,"错误线程数量警告:"+"errorCount:"+connector.getErrorCount()+" >= "+connector_errorCount);
//		    		pst.setString(6, "not read");
//		    		pst.setString(7, "not send");
//		    		pst.executeUpdate();
//    			} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}finally {
//		    		try {
//		    			pst.close();
//		    		} catch (SQLException e) {
//		    			// TODO Auto-generated catch block
//		    			e.printStackTrace();
//		    		}
//		    	}
//    		}
	    }
	    	 public synchronized void Judge_memory(Memory memory) {
	 	    	String sql = "select memory_total from config where ipAddress= ? and port = ?";
	 	    	PreparedStatement pst=null;
	 	    	ResultSet rs = null;
	 	    	double memory_total=0.8;
	 	    	try {
	 	    		pst = this.conn.prepareStatement(sql);
	 	    		pst.setString(1, memory.getIpAddress());
	 	    		pst.setInt(2, memory.getPort());
	 	    		rs=pst.executeQuery();
	 	    		if(rs.next()) {
	 	    			memory_total = rs.getDouble(1);
	 	    		}
	 	    	} catch (SQLException e) {
	 	    		// TODO Auto-generated catch block
	 	    		e.printStackTrace();
	 	    	}
	 	    	//出现错误
	 	    	if((memory.getTotal()/memory.getMax())>memory_total) {
	     			sql="insert into log(ipAddress,port,time,error_type,describe_message,read_status,send_status) values(?,?,?,?,?,?,?)";
	     			try {
	 					pst = this.conn.prepareStatement(sql);
	 					pst.setString(1, memory.getIpAddress());
	 		    		pst.setInt(2, memory.getPort());
	 		    		pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
	 		    		pst.setString(4, "Memory");
	 		    		pst.setString(5,"内存占比警告:"+(memory.getTotal()/memory.getMax())*100+"% > "+memory_total*100+"%");
	 		    		pst.setString(6, "not read");
	 		    		pst.setString(7, "not send");
	 		    		pst.executeUpdate();
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
	     		}
	    }
}
