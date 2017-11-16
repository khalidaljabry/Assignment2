
package application3;

import java.util.Vector;

public class Course {
   public Department dept;
   private String title; // title of the course 
   private String code; // course code   
   private int number; // course number 
   Vector classList; // contains all students registered in this course

   public String getDept() {
       return dept.toString();
   }
  
   public String getCode() {
       return code;
   }

   public int getNumber() {
       return number;
   }

   public Course(String code, int number, Department dept, String title) {
      // also initialize the classList;
       this.code=code;
       this.number=number;
       this.title=title;
       this.dept=dept;
       classList=new Vector();
       dept.offerCourse(this);
   }

  public String toString() {
     // return a string representation of the course with the course code,
     // name, and number of people registered in the course in the following
     return (code+" "+number+" "+title+", Enrollment = "+classList.size());
  }
  
}
