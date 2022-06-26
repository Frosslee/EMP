import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

import javax.swing.tree.TreeNode;

class employeeDemo {

    public static void main(String[] args) throws Exception {
        int choice = -1;
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        File file = new File("employee.txt");
        ArrayList<employee> al = new ArrayList<employee>();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ListIterator<employee> li = null;
        Date DOB = null;
        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            al = (ArrayList<employee>) ois.readObject();
            ois.close();
        }
        do {
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employee");
            System.out.println("3. search Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Update Employee");
            System.out.println("6. Sort Employees by Employee number");
            System.out.println("7. Sort Employees by Salary");
            System.out.println("8. Search Employees by DOB");
            System.out.println("9. Print Departments");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = s.nextInt();

            switch (choice) {
                case 1: // Add Employee
                    System.out.println("Enter how many employees you want to add: ");
                    System.out.println("-----------------------------------------------------");
                    int n = s.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.println("Enter Employee Number: ");
                        int empno = s.nextInt();
                        System.out.println("Enter Employee Name: ");
                        String ename = s1.next();
                        System.out.println("Enter Employee Surname: ");
                        String esurname = s1.next();
                        System.out.print("Employee DOB format(dd-MM-yyyy):");
                        String date = s1.next();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        DOB = null;
                        Boolean cat = false;
                        while (cat == false) {
                            try {
                                DOB = dateFormat.parse(date);
                                cat = true;
                            } catch (ParseException e) {

                                System.out.println("Please use numbers dont Use Nov or Novermber (string values)");
                                e.printStackTrace();
                            }
                        }

                        System.out.println("Enter Employee role: ");
                        String role = s1.next();
                        System.out.println("Enter Employee Reporting To: ");
                        String reportingto = s1.next();
                        System.out.println("Enter Employee Salary: ");
                        int salary = s.nextInt();
                        employee e = new employee(empno, ename, esurname, DOB, role, reportingto, salary);
                        al.add(new employee(empno, ename, esurname, DOB, role, reportingto, salary));
                    }
                    //
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(al);
                    oos.close();

                    break;

                case 2: // display all employees
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<employee>) ois.readObject();
                        ois.close();
                        System.out.println("-----------------------------------------------------");
                        li = al.listIterator();
                        while (li.hasNext()) {
                            System.out.println(li.next());
                            System.out.println("-----------------------------------------------------");
                        }
                    } else {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("File does not exist");
                        System.out.println("-----------------------------------------------------");
                    }
                    break;
                case 3: // Search Employee
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<employee>) ois.readObject();
                        ois.close();
                        boolean found = false;
                        System.out.println("Enter Employee Number: ");
                        int empno = s.nextInt();
                        li = al.listIterator();
                        while (li.hasNext()) {
                            employee e = (employee) li.next();
                            if (e.empno == empno) {
                                System.out.println("-----------------------------------------------------");
                                System.out.println("Employee found");
                                System.out.println("-----------------------------------------------------");
                                System.out.println("Employee number:" + " " + e.empno);
                                System.out.println("Employee name:" + " " + e.ename);
                                System.out.println("Employee surname:" + " " + e.esurname);
                                System.out.println("Employee DOB:" + " " + e.DOB);
                                System.out.println("Employee role:" + " " + e.role);
                                System.out.println("Employee report to:" + " " + e.reportingto);
                                System.out.println("Employee salary:" + " " + e.salary);
                                System.out.println("-----------------------------------------------------");
                                found = true;
                            }

                        }
                        if (!found) {
                            System.out.println("-----------------------------------------------------");
                            System.out.println("Employee not found");
                            System.out.println("-----------------------------------------------------");
                        }
                    }
                    break;

                case 4: // Delete Employee
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<employee>) ois.readObject();
                        ois.close();

                        boolean found = false;
                        System.out.println("Enter Employee Number to delete: ");
                        int empno = s.nextInt();
                        li = al.listIterator();

                        while (li.hasNext()) {
                            employee e = (employee) li.next();
                            if (e.empno == empno) {
                                li.remove();
                                System.out.println("-----------------------------------------------------");
                                found = true;
                            }

                        }
                        if (found) {
                            oos = new ObjectOutputStream(new FileOutputStream(file));
                            oos.writeObject(al);
                            oos.close();
                            System.out.println("Record deleted");
                        } else {
                            System.out.println("-----------------------------------------------------");
                            System.out.println("Record not found");
                            System.out.println("-----------------------------------------------------");
                        }
                        System.out.println("-----------------------------------------------------");

                    } else {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("File not found");
                        System.out.println("-----------------------------------------------------");

                    }

                    break;
                case 5: // Update Employee
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<employee>) ois.readObject();
                        ois.close();

                        boolean found = false;
                        System.out.println("Enter Employee Number to update: ");
                        int empno = s.nextInt();
                        System.out.println("-----------------------------------------------------");
                        li = al.listIterator();

                        while (li.hasNext()) {
                            employee e = (employee) li.next();
                            if (e.empno == empno) {
                                System.out.print("Enter Employee Name: ");
                                String ename = s1.nextLine();
                                System.out.print("Enter Employee Surname: ");
                                String esurname = s.nextLine();
                                System.out.print("Enter Employee role: ");
                                String role = s.nextLine();
                                System.out.print("Enter Employee Reporting To: ");
                                String reportingto = s.nextLine();
                                System.out.print("Enter Employee Salary: ");
                                int salary = s.nextInt();

                                li.set(new employee(empno, ename, esurname, DOB, role, reportingto, salary));

                                System.out.println("-----------------------------------------------------");
                                found = true;
                            }

                        }
                        if (found) {
                            oos = new ObjectOutputStream(new FileOutputStream(file));
                            oos.writeObject(al);
                            oos.close();
                            System.out.println("Record deleted");
                        } else {
                            System.out.println("-----------------------------------------------------");
                            System.out.println("Record not found");
                            System.out.println("-----------------------------------------------------");
                        }
                        System.out.println("-----------------------------------------------------");

                    } else {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("File not found");
                        System.out.println("-----------------------------------------------------");

                    }

                    break;

                case 6: // Sort all employees on screen and file by emp number
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<employee>) ois.readObject();
                        ois.close();

                        Collections.sort(al, new Comparator<employee>() {
                            public int compare(employee e1, employee e2) {

                                return e1.empno - e2.empno;
                            }
                        });
                        oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(al);
                        oos.close();

                        System.out.println("-----------------------------------------------------");
                        li = al.listIterator();
                        while (li.hasNext()) {

                            System.out.println(li.next());
                            System.out.println("-----------------------------------------------------");
                        }
                    } else {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("File does not exist");
                        System.out.println("-----------------------------------------------------");
                    }
                    break;
                case 7: // Sort all employees on screen by salary acending
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<employee>) ois.readObject();
                        ois.close();

                        Collections.sort(al, new Comparator<employee>() {
                            public int compare(employee e1, employee e2) {

                                return e2.salary - e1.salary;
                            }
                        });

                        System.out.println("-----------------------------------------------------");
                        li = al.listIterator();
                        while (li.hasNext()) {

                            System.out.println(li.next());
                            System.out.println("-----------------------------------------------------");
                        }
                    } else {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("File does not exist");
                        System.out.println("-----------------------------------------------------");
                    }
                    break;

                case 8: // search by date
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<employee>) ois.readObject();
                        ois.close();

                        // boolean found = false;
                        System.out.println("Enter DOB format(dd-MM-yyyy): ");
                        String date = s1.next();
                        SimpleDateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        DOB = DateFormat.parse(date);
                        DOB = null;
                        Boolean flag2 = false;
                        // trying to get the date from the user.
                        do {
                            try {
                                DOB = DateFormat.parse(date);
                                if (DOB == null) {
                                    System.out.println("-----------------------------------------------------");
                                    System.out.println("Invalid Date Format");
                                    System.out.println("-----------------------------------------------------");
                                } else if (DOB.after(new Date())) {
                                    System.out.print(date);
                                    flag2 = true;
                                }
                            } catch (ParseException e) {

                                System.out.println("Please dont use numbers Use Nov or Novermber (string values)");
                                e.printStackTrace();
                            }
                        } while (flag2 != true);
                    } else {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("File does not exist");
                        System.out.println("-----------------------------------------------------");
                    }
                    break;

                case 9: // tree map to display role sorted by role
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<employee>) ois.readObject();
                        ois.close();

                        TreeMap<String, ArrayList<employee>> tm = new TreeMap<String, ArrayList<employee>>();
                        li = al.listIterator();
                        while (li.hasNext()) {
                            employee e = (employee) li.next();
                            if (tm.containsKey(e.role)) {
                                tm.get(e.role).add(e);
                            } else {
                                ArrayList<employee> al1 = new ArrayList<employee>();
                                al1.add(e);
                                tm.put(e.role, al1);
                            }
                        }
                        Set<String> keys = tm.keySet();
                        for (String key : keys) {
                           
                            System.out.println("Role: " + key);
                            System.out.println("------------");
                            li = tm.get(key).listIterator();
                            while (li.hasNext()) {
                      
                                
                                System.out.println(li.next());
                              
                            }
                        }
                    } else {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("File does not exist");
                        System.out.println("-----------------------------------------------------");
                    }

                    break;

                case 0: // Exit
                    System.out.println("Exiting...");
                    break;

            }
        } while (choice != 0);
        s.close();
        s1.close();
    }
}
