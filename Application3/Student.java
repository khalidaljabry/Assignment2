
package application3;

import java.util.Vector;

public class Student {
  private String id;
  private String name;
  private Vector courses; // contains all courses the student is registered in

  public String getName() {
      return this.name;
  }

  public String getId() {
      return this.id;
  }

  // constructor
  public Student(String id, String name) { 
     // initialize name and id. Also initialize the Vector
     this.id=id;
     this.name=name;
     this.courses=new Vector();
  }

  public String toString() {
     // return a string representation of a student using the following format:
     // 100234546 John McDonald
     // Registered courses: ELEE 2110, ELEE 2790, SOFE 2710, SOFE 2800, SOFE 2850
     StringBuilder string=new StringBuilder(id+" "+name);
     
     //forloop to iterate through every courses
     for(int i=0;i<courses.size();i++)
     {
         if(i==0)
         {
             string.append("Registered courses: [");
         }
         string.append(" "+courses.get(i));
         if(i==courses.size()-1)
         {
             string.append("]");
         }
     }
     
     return string.toString();
  }
  
  //function to register courses enrolled by him
  public void registerFor(Course course)
  {
    //flag
       boolean isAlreadyRegister=false;
       
       //iterate through each course to make sure the course is not already added
       for(int i=0;i<course.classList.size();i++)
       {
           //check if course is available
           if(((Student)course.classList.get(i)).getId().equals(this.id))
           {
               isAlreadyRegister=true;
               break;
           }
       }
       
       //check the flag
       if(!isAlreadyRegister)
       {
           course.classList.add(this);
           courses.add(course);
           course.dept.registerList.add(this);
       }
  }
  
  //method to check whether  or not the given student is registered for the course
  public boolean isRegisteredInCourse(Course course)
  {
      
       //iterate through each course to make sure the course is not already added
       for(int i=0;i<course.classList.size();i++)
       {
           //check if course is available
           if(((Student)course.classList.get(i)).getId().equals(this.id))
           {
               return true;
           }
       }
       
       return false;
  }
  
}
