
package application3;

import java.util.ArrayList;
import java.util.Vector;

public class Department {
    
   private String name; // the name of school  
   private String id; // short name for courses 
   Vector courseList; // all courses offered by the department
   Vector registerList; // all students taking courses in the department.

   public String getName() {
       return name;
   }

   public String getId() {
       return id;
   }

   public Department(String name, String id) {
      // also initialize the vectors
       this.name=name;
       this.id=id;
       this.courseList=new Vector();
       this.registerList=new Vector();
   }

   public String toString() {
      // returns a string representation of department name, number 
      // of courses offered and number of students registered in the
      // department.
       return (id+": "+courseList.size()+" courses, "+registerList.size()+" students");
   }

   public void offerCourse(Course course)
   {
       //flag
       boolean isAlreadyEnrolled=false;
       
       //iterate through each course to make sure the course is not already added
       for(int i=0;i<courseList.size();i++)
       {
           //check if course is available
           if(((Course)courseList.get(i)).getNumber()==course.getNumber())
           {
               isAlreadyEnrolled=true;
               break;
           }
       }
       
       //check the flag
       if(!isAlreadyEnrolled)
       {
           courseList.add(course);
       }
   }

   //Method to display all the courses offered by the Department
   public void printCoursesOffered()
   {
       //iterate through each course to display them
       for(int i=0;i<courseList.size();i++)
       {
           System.out.println(((Course)courseList.get(i)));
       }
       
   }
   
   //Method to display all the students taking courses in this Department
   public void printStudentsByName()
   {
       //Arraylist to hold the unique students
       ArrayList<Student> uniquestudents = new ArrayList<Student>();
       //flag 
       boolean isAvail=false;
       
       //copy unique student name and id to the arraylist
       for(int i=0;i<registerList.size();i++)
       {
           isAvail=false;
           for(Student student : uniquestudents)
           {
               if(student.getId().equals(((Student)registerList.get(i)).getId()))
               {
                   isAvail=true;
                   break;
               }
           }
           
           //check if available
           if(!isAvail)
           {
               uniquestudents.add((Student)registerList.get(i));
           }
       }
       //iterate through each Student to display
       for(int i=0;i<uniquestudents.size();i++)
       {
           System.out.println(uniquestudents.get(i).getName()+" "+uniquestudents.get(i).getId());
       }
       
   }
   
   //method taht indicates whether the student is registered or not
   public boolean isStudentRegistered(Student student)
   {
       //iterate throught the registered student and check if the student is registered
       for(int i=0;i<registerList.size();i++)
       {
           //check if the student is registered
           if(((Student)registerList.get(i)).getId().equals(student.getId()))
           {
               return true;
           }
       }
       return false;
   }
   
   //method that returns the ArrayList containing all students who are registerd in the course
   public ArrayList<Student> studentsRegisteredInCourse(int code)
   {
       //ArrayList to hold the list of students registered in the given code course
       ArrayList<Student> studentList=new ArrayList<Student>();
       
       //iterate through the courses offered to get the course code
       for(int i=0;i<courseList.size();i++)
       {
           //check if the course code matches
           if(((Course)courseList.get(i)).getNumber()==(code))
           {
               //Now iterate through the student registered in this course
               Vector EnrolledStudents = ((Course)courseList.get(i)).classList;
               
               //start copying the students to the array list
               for(int j=0;j<EnrolledStudents.size();j++)
               {
                   studentList.add((Student)EnrolledStudents.get(j));
               }
           }
       }
       
       return studentList;
   }
      
   //method to print the students in the current course
   public void printStudentsRegisteredInCourse(int code)
   {
       ArrayList<Student> RegisteredStudent=studentsRegisteredInCourse(code);
       
       //start printing the list of students
       for(Student student : RegisteredStudent)
       {
           System.out.println(student.getId()+" "+student.getName());
       }
   }
      
   //method to return the largest enrolled student course
   public Course largestCourse()
   {
       Course largestCourse;
       
       largestCourse = (Course)courseList.get(0);
       
       //iterate through the course list and find the largest enrolled student course
       for(int i=0;i<courseList.size();i++)
       {
           //check if the course enrolled number of students is the largest
           if(largestCourse.classList.size()<((Course)courseList.get(i)).classList.size())
           {
               largestCourse = (Course)courseList.get(i);
           }
       }
       
       return largestCourse;
   }
   
}
