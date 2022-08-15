package com;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


class PgDataBaseManagementSystemImp implements PgDataBaseManagementSystem {
	int count1;
	int count;
	int capacity=1;
	int password=1234;
	Scanner scan=new Scanner(System.in);

	@Override
	public void aboutPg() {
		SunnyPg sp=new SunnyPg();
		sp.display();
	}


	@Override
	public void booking() {
		System.out.println("Enter Your Details");
		details();
		System.out.println("Enter Your Id :");
		int id=scan.nextInt();
		System.out.println("Enter Your Name :");
		String name=scan.next();
		System.out.println("Enter Your Age :");
		int age=scan.nextInt();
		System.out.println("Enter Your Address :");
		String address=scan.next();
		System.out.println("Enter your Phone Number :");
		Long phoneNo=scan.nextLong();
		System.out.println("Enter your AadharCard Number :");
		Long aadharCardNo=scan.nextLong();

		String s1=Long.toString(phoneNo);
		int count=0;
		for (int i = 0; i < s1.length(); i++) {
			count++;
		}
		String s2=Long.toString(aadharCardNo);
		int aCount=0;
		for (int i = 0; i < s2.length(); i++) {
			aCount++;
		}

		if (age>=18 && count==10 && aCount==12 ) {
			if (capacity<250) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");

					PreparedStatement ps= con.prepareStatement("insert into pg values(?,?,?,?,?,?)");
					ps.setInt(1, id);
					ps.setString(2, name);
					ps.setInt(3, age);
					ps.setString(4, address);
					ps.setLong(5, phoneNo);
					ps.setLong(6, aadharCardNo);
					ps.execute();
					con.close();	

				} catch (Exception e) {

					e.printStackTrace();

				}
                count1++;
				capacity++;
				System.out.println("Person Record Is Inserted Successfully.");

			}else {
				System.out.println("Sorry Sunny Pg Is Full........");

			}
		}
		else {
			System.out.println("Please Eneter The Currect Details");
		}

	}



	@Override
	public void removePerson() {
		System.out.println("Enter The Password");
		int pass=scan.nextInt();
		if (pass==password) {

			if (count1>0) {
				System.out.println("Enter The Person Id To Remove  ");
				int user_id=scan.nextInt();

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");

					PreparedStatement ps= con.prepareStatement("delete from pg where user_id=(?)");
					ps.setInt(1, user_id);
					ps.execute();
					System.out.println("Person Data Removed Successfully.");
					con.close();	
					
				} catch (Exception e) {
					try {
						throw new PersonNotFoundException("Person Data Not Found!"); 

					} catch ( Exception p) {

						System.out.println(p.getMessage());

					}
				}
			}else {
				System.out.println("Pg Is Empty!");
			}
		}else {
			password();
		}
	}

	@Override
	public void removeAllPersons() {
		System.out.println("Enter The Password");
		int pass=scan.nextInt();
		if (pass==password) {
				if (count > 0) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");

						java.sql.Statement st= con.createStatement();

						st.executeUpdate("truncate pg");
						count=0;
						System.out.println(" All Persons Removed Successfully.");
						con.close();
						
					} catch (Exception e) {
						try {
							throw new PersonNotFoundException("Persons Are Not Present!"); 

						} catch ( Exception p) {

							System.out.println(p.getMessage());

						}
					}
				}else {
					System.out.println("Pg Is Already Empty!");
				}
		}
		else {
			password();
		}

	}

	@Override
	public void displayPerson() {
		System.out.println("Enter The Password");
		int pass=scan.nextInt();
		if (pass==password) {
			System.out.println("Enter The Person Id To Display");
			int user_id=scan.nextInt();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");


				java.sql.Statement st= con.createStatement();
				ResultSet rs= st.executeQuery("select * from pg where user_id = "+user_id);

				if (rs.next()) {
					System.out.println("Id is : "+rs.getInt(1));
					System.out.println("Name is : "+rs.getString(2));
					System.out.println("Age is : "+rs.getInt(3));
					System.out.println("Address is : "+rs.getString(4));
					System.out.println("Phone Number is : "+rs.getLong(5));
					System.out.println("AadharCard Number is : "+rs.getLong(6));
					System.out.println("================= ");
				}else {
					System.out.println("Person Data Not Found!");
				}

				con.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else {
			password();
		}
	}

	@Override
	public void displayAllPersons() {
		System.out.println("Enter The Password");
		int pass=scan.nextInt();
		if (pass==password) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");

				java.sql.Statement st=con.createStatement();

				ResultSet rs=st.executeQuery("select * from pg");
				while (rs.next()) {
					System.out.println("Id is : "+rs.getInt(1));
					System.out.println("Name is : "+rs.getString(2));
					System.out.println("Age is : "+rs.getInt(3));
					System.out.println("Address is : "+rs.getString(4));
					System.out.println("Phone Number is : "+rs.getLong(5));
					System.out.println("AadharCard Number is : "+rs.getLong(6));
					System.out.println("================= ");
				}
				con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			password();
		}
	}

	@Override
	public void updatePerson() {
		System.out.println("Enter The Password");
		int pass=scan.nextInt();
		if (pass==password) {
			System.out.println("\n Select The Option To Update");
			System.out.println(" 1) : Name \n 2) : Age \n 3) : Address \n 4) : Phone Number\n");
			System.out.println("We Can't Able To Update The  Id  And AadharCard Number.\n");

			int choice =scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the Person id update :");
				int id =scan.nextInt();
				System.out.println("Enter The Person Name To Update.");
				String name=scan.next();

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");
					java.sql.Statement st=con.createStatement();
					st.executeUpdate("update pg set name='"+name+"' where user_id='"+id+"'");

					con.close();
					System.out.println("Person NAme Is Updated Successfully.");
				} catch (Exception e) {
					try {
						throw new PersonNotFoundException("Person Data Is Not found!"); 

					} catch ( Exception p) {

						System.out.println(p.getMessage());

					}

				}



				break;
			case 2:
				System.out.println("Enter the Person id update :");
				int id1 =scan.nextInt();
				System.out.println("Enter The Person Age To Update.");
				int age=scan.nextInt();

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");
					java.sql.Statement st=con.createStatement();
					st.executeUpdate("update pg set age='"+age+"' where user_id='"+id1+"'");

					con.close();
					System.out.println("Person Age Is Updated Successfully.");
				} catch (Exception e) {
					try {
						throw new PersonNotFoundException("Person Data Is Not found!"); 

					} catch ( Exception p) {

						System.out.println(p.getMessage());

					}

				}



				break;
			case 3:
				System.out.println("Enter the Person id update :");
				int id2 =scan.nextInt();
				System.out.println("Enter The Person Address To Update.");
				String address=scan.next();

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");
					java.sql.Statement st=con.createStatement();
					st.executeUpdate("update pg set address='"+address+"' where user_id='"+id2+"'");

					con.close();
					System.out.println("Person Address Is Updated Successfully.");
				} catch (Exception e) {
					try {
						throw new PersonNotFoundException("Person Data Is Not found!"); 

					} catch ( Exception p) {

						System.out.println(p.getMessage());

					}

				}



				break;
			case 4:
				System.out.println("Enter the Person id update :");
				int id3 =scan.nextInt();
				System.out.println("Enter The Person Phone Number To Update.");
				Long phoneNo=scan.nextLong();

				String s1=Long.toString(phoneNo);
				int count=0;
				for (int i = 0; i < s1.length(); i++) {
					count++;
				}
				if (count==10) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");
						java.sql.Statement st=con.createStatement();
						st.executeUpdate("update pg set phone_no='"+phoneNo+"' where user_id='"+id3+"'");

						con.close();
						System.out.println("Person Phone Number Is Updated Successfully.");
					} catch (Exception e) {
						try {
							throw new PersonNotFoundException("Person Data Is Not found!"); 

						} catch ( Exception p) {

							System.out.println(p.getMessage());

						}

					}
				}	else {
					System.out.println("You Have Entered Wrong Phone Number  ");
					System.out.println("Try Again!");
				}
				break;
			default:
				System.out.println("Please Select The Currect Option ");
				break;
			}

		}

		else {
			password();
		}

	}

	@Override
	public void sortPerson() {
		System.out.println("Enter The Password");
		int pass=scan.nextInt();
		if (pass==password) {
		if (count1 >0) {
			System.out.println("1). Sort Id \n2). Sort Name");
			System.out.println("Enter the option to sort");
			int choice=scan.nextInt();

			switch (choice) {
			case 1:
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");

					java.sql.Statement st= con.createStatement();
					ResultSet rs= st.executeQuery("select * from pg  order by user_id");

					while (rs.next()) {
						System.out.println("Id is : "+rs.getInt(1));
						System.out.println("Name is : "+rs.getString(2));
						System.out.println("Age is : "+rs.getInt(3));
						System.out.println("Address is : "+rs.getString(4));
						System.out.println("Phone Number is : "+rs.getLong(5));
						System.out.println("AadharCard Number is : "+rs.getLong(6));
						System.out.println("================= ");
					}

					con.close();
					rs.close();
				} catch (Exception e) {
					try {
						throw new PersonNotFoundException("Person Data Not Found!"); 

					} catch ( Exception p) {

						System.out.println(p.getMessage());

					}
				}

				break;
			case 2:
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");

					java.sql.Statement st= con.createStatement();
					ResultSet rs= st.executeQuery("select * from pg  order by name");

					while (rs.next()) {
						System.out.println("Id is : "+rs.getInt(1));
						System.out.println("Name is : "+rs.getString(2));
						System.out.println("Age is : "+rs.getInt(3));
						System.out.println("Address is : "+rs.getString(4));
						System.out.println("Phone Number is : "+rs.getLong(5));
						System.out.println("AadharCard Number is : "+rs.getLong(6));
						System.out.println("================= ");
					}

					con.close();
					rs.close();
				} catch (Exception e) {
					try {
						throw new PersonNotFoundException("Person Data Not Found!"); 

					} catch ( Exception p) {

						System.out.println(p.getMessage());

					}
				}

				break;

			default:
				System.out.println("Please Enter The Currect Option");
				break;
			}	

		}	else {
			System.out.println("Pg Is Empty!");
		}
		}
		else {
			password();
		}

	}

	@Override
	public void count() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pgdatabase","root","root");

			java.sql.Statement st= con.createStatement();
			ResultSet rs= st.executeQuery("select count(*) from pg");

			while (rs.next()) {
			  count = rs.getInt(1);
				System.out.println("Total Persons Are Present In This Pg Is : "+count);
			}

			con.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static	void password() {
		System.out.println("Enter The Currect Password!");
	}
	private static void details() {
		System.out.println("\nAge Should Must Contain >= 18");
		System.out.println("Phone Number Should Must Contain  10 Digits ");
		System.out.println("AadharCard Number Should Must Contain 12 Digits\n");
	}
}
