package com.Customer.Updation.service;

import com.Customer.Updation.models.CustomerUpdateResponse;
import com.Customer.Updation.repo.CustomerUpdateRepository;
import com.Customer.Updation.CustomerUpdateApplication;
import com.Customer.Updation.models.CustomerUpdateDetail;
/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.LOGGER;*/

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class CustomerUpdateService {

	@Autowired
	private CustomerUpdateResponse CustomerUpdateResponse;
	
	@Autowired
	private CustomerUpdateRepository customerRepository;

	private static final Logger LOGGER = LogManager.getLogger(CustomerUpdateService.class);

	/*
	 * public Connection DBconnection() throws IOException {
	 * 
	 * Properties props = new Properties(); FileInputStream in = new
	 * FileInputStream("src/main/resources/application.properties"); props.load(in);
	 * in.close(); Connection con = null; String url =
	 * props.getProperty("spring.datasource.url"); String username =
	 * props.getProperty("spring.datasource.username"); String password =
	 * props.getProperty("spring.datasource.password");
	 * 
	 * try { con = DriverManager.getConnection(url, username, password); } catch
	 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * return con;
	 * 
	 * }
	 */

	public CustomerUpdateResponse Updation(CustomerUpdateDetail Customer_dtl) throws SQLException, IOException {
		LOGGER.info("CustomerUpdation : Inside Customer Updation Service");

		boolean Flag = true;

		if ((Customer_dtl.getUser_name()).length() == 0) {
			LOGGER.info("User name cannot be blank");
			CustomerUpdateResponse.setMessage("User name cannot be blank");
			Flag = false;
		} else if ((Customer_dtl.getFirst_name()).length() == 0) {
			LOGGER.info("customer name cannot be blank");
			CustomerUpdateResponse.setMessage("Customer name cannot be blank");
			Flag = false;
		} else if ((Customer_dtl.getEmail()).length() == 0) {
			LOGGER.info("email cannot be blank");
			CustomerUpdateResponse.setMessage("Email cannot be blank");
			Flag = false;
		} else if ((Customer_dtl.getPan()).length() == 0) {
			LOGGER.info("PAN cannot be blank");
			CustomerUpdateResponse.setMessage("PAN number cannot be blank");
			Flag = false;
		} else if ((Customer_dtl.getDob()).length() == 0) {
			LOGGER.info("DOB cannot be blank");
			CustomerUpdateResponse.setMessage("DOB cannot be blank");
			Flag = false;
		} else if ((Customer_dtl.getAddress()).length() == 0) {
			LOGGER.info("address name cannot be blank");
			CustomerUpdateResponse.setMessage("Address cannot be blank");
			Flag = false;
		}
		if (Flag == true) {
			try {
				/*
				 * Connection con = null; con = DBconnection();
				 */
				/*
				 * String check_query =
				 * "select count(*) from customer_dtl where user_name = ? ";
				 * java.sql.PreparedStatement preparedStmt = con.prepareStatement(check_query);
				 * preparedStmt.setString(1, Customer_dtl.getUser_name()); ResultSet rs =
				 * preparedStmt.executeQuery();
				 */
				int n = 0;
				/*
				 * if (rs.next()) { n = rs.getInt(1); }
				 */
				n = customerRepository.custUserNameCount(Customer_dtl.getUser_name());

				if (n > 0) {

					
					/*
					 * String update_query =
					 * " update customer_dtl set first_name = ? , address = ? , email = ? , pan = ? , dob = ? ,updatedby = ? , updatedat = ? where user_name = ? "
					 * ;
					 * 
					 * java.sql.PreparedStatement preparedStmt_update =
					 * con.prepareStatement(update_query); java.sql.Timestamp date = new
					 * java.sql.Timestamp(new java.util.Date().getTime());
					 * preparedStmt_update.setString(1, Customer_dtl.getFirst_name());
					 * preparedStmt_update.setString(2, Customer_dtl.getAddress());
					 * preparedStmt_update.setString(3, Customer_dtl.getEmail());
					 * preparedStmt_update.setString(4, Customer_dtl.getPan());
					 * preparedStmt_update.setString(5, Customer_dtl.getDob());
					 * preparedStmt_update.setString(6, Customer_dtl.getUser_name());
					 * preparedStmt_update.setTimestamp(7, date); preparedStmt_update.setString(8,
					 * Customer_dtl.getUser_name());
					 */
					/*
					 * try { preparedStmt_update.execute(); con.close();
					 * LOGGER.info("The cutomer detail is updated");
					 * CustomerUpdateResponse.setMessage("The cutomer detail is updated"); } catch
					 * (SQLException e) { LOGGER.info("Exception:",e);
					 * CustomerUpdateResponse.setMessage("error");
					 * 
					 * }
					 */
					 try {
					java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
					customerRepository.updateCustomerDetails(Customer_dtl.getUser_name(), Customer_dtl.getFirst_name(), Customer_dtl.getAddress(), Customer_dtl.getEmail(), Customer_dtl.getPan(), Customer_dtl.getDob(), Customer_dtl.getUser_name(), date);
					LOGGER.info("The cutomer detail is updated");
					CustomerUpdateResponse.setMessage("The cutomer detail is updated");
					 }catch (Exception e) {
						 CustomerUpdateResponse.setMessage("error");
					}
				}

				else {

					CustomerUpdateResponse.setMessage("Customer Does not Exixts");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return CustomerUpdateResponse;

	}

}