package com.hgd.ebp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.hgd.ebp.domain.Book;


@Repository
@Scope("singleton")
public class GuessLikeDao {
	private static final String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    private static final String HOST = "192.168.1.200:10021";
    private static final String URL = "jdbc:hive://" + HOST + "/default";
    private static final String GUESS_BY_CLASS = "select bid,count(*) as total "
		    			+ "from orderlist a join orders b on a.oid = b.oid where b.uid = ? "
		    			+ "group by bid order by total desc limit 3";
    private static final String GUESS_BY_GENDER = "select bid,count(*) as total "
			    		+ " from orderlist a join (select c.oid from orders c join (select user.uid "
						+ "from user where gender = ?) d on c.uid = d.uid) b on a.oid = b.oid "
						+ "group by bid order by bid desc limit 3";
    private static final String SELECT_BOOK = "select * from book where bid = ?";
    private static final String SELECT_GEBDER ="select gender from user where uid = ?";
    
    private static Connection conn;
    
    public GuessLikeDao(){
    	 try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(URL, "", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    

	public List<Integer> selectOnClass(Integer uid) {
		
        PreparedStatement stmt = null;
        ResultSet results = null;
        List<Integer> list= new ArrayList<Integer>();
        try {
			stmt = conn.prepareStatement(GUESS_BY_CLASS);
			stmt.setInt(1,uid);
            results = stmt.executeQuery();
            
            System.out.println(stmt.toString()); 
            while (results.next()) {
            	int bid = results.getInt(1);
            	list.add(bid);
            }
            results.close();
            stmt.close();
            			
		}catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (results != null) {
                try { results.close(); } catch (SQLException e) {}
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) {}
            }
            
        }
		
        return list;
}

	public List<Integer> selectOnGender(String gender) {
        PreparedStatement stmt = null;
        ResultSet results = null;
        List<Integer> list= new ArrayList<Integer>();
        try {
			stmt = conn.prepareStatement(GUESS_BY_GENDER);
			stmt.setString(1,gender);
			System.out.println(stmt.toString()); 
            results = stmt.executeQuery();
            while (results.next()) {
            	int bid = results.getInt(1);
            	list.add(bid);
            }
            results.close();
            stmt.close();
            			
		}catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (results != null) {
                try { results.close(); } catch (SQLException e) {}
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) {}
            }
           
        }
		
        return list;
	}

	public String selectGender(Integer uid) {
		PreparedStatement stmt = null;
        ResultSet results = null;
        String gender = null;
        try {
			stmt = conn.prepareStatement(SELECT_GEBDER);
			stmt.setInt(1,uid);
            results = stmt.executeQuery();
            results.next();
            gender = results.getString(1);
            results.close();
            stmt.close();
            			
		}catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (results != null) {
                try { results.close(); } catch (SQLException e) {}
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) {}
            }
         /*   if (conn != null) {
                try { conn.close(); } catch (SQLException e) {}
            }*/
        }
		
        return gender;
	}

	public Book selectBook(int bid) {
		PreparedStatement stmt = null;
        ResultSet results = null;
        Book book = new Book();
        try {
			stmt = conn.prepareStatement(SELECT_BOOK);
			stmt.setInt(1,bid);
            results = stmt.executeQuery();
            while (results.next()) {
            	String bname = results.getString(3);
            	String image = results.getString(4);
            	String author = results.getString(5);
            	String descp = results.getString(6);
            	double price = results.getDouble(7);
            	String publisher = results.getString(8);
            	String publishTime = results.getString(9);
            	book = new Book(bid,bname,image,author,descp,price,publisher,publishTime);
            }
            results.close();
            stmt.close();
            			
		}catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (results != null) {
                try { results.close(); } catch (SQLException e) {}
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) {}
            }
          /*  if (conn != null) {
                try { conn.close(); } catch (SQLException e) {}
            }*/
        }
		
        return book;
	}
    
	public void close(){
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
    
}
