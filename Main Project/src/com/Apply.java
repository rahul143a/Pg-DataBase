package com;

import java.util.Scanner;

public class Apply {
	public static void main(String[] args) {
		
		PgDataBaseManagementSystem pg=new PgDataBaseManagementSystemImp();
		
		Scanner scan=new Scanner(System.in);
		
		while(true) {
	       display();
		System.out.println("Enter The Option Which You Like ");
	     int choice =scan.nextInt();
	     switch (choice) {
		case 1:
			pg.aboutPg();
			break;
            case 2:
			  pg.booking();
			break;
            case 3:
    			pg.removePerson();
    			break;
            case 4:
    			pg.removeAllPersons();
    			break;
            case 5:
    			pg.displayPerson();
    			break;
            case 6:
    			pg.displayAllPersons();
    			break;
            case 7:
    			pg.updatePerson();
    			break;
            case 8:
    			pg.sortPerson();
    			break;
            case 9:
            	pg.count();
    			break;
            case 10:
            	System.out.println("Thank You!");
    			System.exit(0);
    			break;

		default:
			System.out.println(" You Have Entered The Wrong Choice");
			System.out.println(" Please Enter The Currect Choice\n ");
			break;
		}
		}
	}
    private static void   display() {
		System.out.println("Welcome To Sunny Pg");
		System.out.println("1). About Pg \n2). Booking \n3). Remove Person \n4). Remove All Persons");
		System.out.println("5). Display Person \n6). Display All Person \n7). Update Person ");
		System.out.println("8). Sort Persons \n9). Count \n10). Exit");
 }
}
