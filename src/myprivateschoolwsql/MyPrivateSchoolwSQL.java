package myprivateschoolwsql;

import dao.AssignmentDao;
import dao.AttendsDao;
import dao.CourseDao;
import dao.HasDao;
import dao.PutDao;
import dao.StudentDao;
import dao.TeachesDao;
import dao.TrainerDao;
import dao.UserDao;
import java.util.Scanner;

public class MyPrivateSchoolwSQL {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean checkU = UserDao.checkUser(input);
        if (checkU == true) {
            menu(input);
            input.close();
        }
    }

    public static void menu(Scanner input) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|                                      |");
        System.out.println("|      Welcome to Private School       |");
        System.out.println("|                                      |");
        System.out.println("++++++++++++++++++++++++++++++++++++++++");

        String userChoice;

        do {
            System.out.println("==================MAIN MENU==================");
            System.out.println("Please choose from the options below:");
            System.out.println("1.Add data");
            System.out.println("2.Show data");
            System.out.println("0.Exit");

            userChoice = input.nextLine();

            switch (userChoice) {
                case "1": {
                    addMenu(input);
                    break;
                }
                case "2": {
                    showMenu(input);
                    break;
                }

                case "0": {
                    break;
                }
                default:
                    System.out.println("Wrong choice. Try again.");
            }

        } while (!userChoice.equals("0"));
    }

    public static void addMenu(Scanner input) {
        String userChoice = "";
        do {
            System.out.println("==================ADD MENU==================");
            System.out.println("Please choose from the options below:");
            System.out.println("1.Add Students");
            System.out.println("2.Add Trainers");
            System.out.println("3.Add Assignments");
            System.out.println("4.Add Courses");
            System.out.println("5.Students Per Course");
            System.out.println("6.Trainers Per Course");
            System.out.println("7.Assignments Per Student Per Course");
            System.out.println("0.Exit to main menu");
            userChoice = input.nextLine();
            switch (userChoice) {

                case "1": {
                    StudentDao.insertStudent(input);
                    break;
                }
                case "2": {
                    TrainerDao.insertTrainer(input);
                    break;
                }
                case "3": {
                    AssignmentDao.insertAssignment(input);
                    break;
                }
                case "4": {
                    CourseDao.insertCourse(input);
                    break;
                }
                case "5": {
                    AttendsDao.insertIntoAttends(input);
                    break;
                }
                case "6": {
                    TeachesDao.insertIntoTeaches(input);
                    break;
                }
                case "7": {

                    break;
                }
                case "0": {
                    break;
                }
                default:
                    System.out.println("Wrong choice. Try again.");
            }
        } while (!userChoice.equals("0"));
        return;
    }

    public static void showMenu(Scanner input) {
        String userChoise = "";
        do {
            System.out.println("==================SHOW MENU==================");
            System.out.println("Please choose from the options below:");
            System.out.println("1.Show Students");
            System.out.println("2.Show Trainers");
            System.out.println("3.Show Assignments");
            System.out.println("4.Show Courses");
            System.out.println("5.Show Students per Course");
            System.out.println("6.Show Trainers per Course");
            System.out.println("7.Show Assignments per Course");
            System.out.println("8.Show Assignments per Course per Student");
            System.out.println("9.Show Students belonging to more more than one course");
            System.out.println("0.Exit to main menu");
            userChoise = input.nextLine();
            switch (userChoise) {

                case "1": {
                    StudentDao.showStudents();
                    break;
                }
                case "2": {
                    TrainerDao.showTrainers();
                    break;
                }
                case "3": {
                    AssignmentDao.showAssignments();
                    break;
                }
                case "4": {
                    CourseDao.showCourses();
                    break;
                }
                case "5": {
                    AttendsDao.showAttends();
                    break;
                }
                case "6": {
                    TeachesDao.showTeaches();
                    break;
                }
                case "7": {
                    PutDao.showPut();
                    break;
                }
                case "8": {
                    HasDao.showAssignmentsPerCoursePerStudent();
                    break;
                }
                case "9": {
                    AttendsDao.showManyCourses();
                    break;
                }

                case "0": {
                    break;
                }
                default:
                    System.out.println("Wrong choice. Try again");

            }
        } while (!userChoise.equals("0"));

    }

}
