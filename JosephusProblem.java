//
//
//josephusnGeniosityrapp
//////////////////////////////////////////////////
import java.util.Scanner;

class Link 
{ public int person;                           //holds the persons number
  public Link next;                            //next link on the list
//----------------------------------------------------  
  public Link (int per)                        //constructor
  { person=per;
  }
//----------------------------------------------------  
  public void display ()                       //display link
  { 
    System.out.print("\nSurvivour is " + person +" \n");
  } 
//----------------------------------------------------  
}
///////////////////////////////////////////////////
class Linklist
{
 public Link first;                            //ref to the first link in the list
//-----------------------------------------------------                                    
 public Linklist()                             //constructor
 {  first= null;                               
 }
//---------------------------------------------------- 
 public Link getfirst()                        //get the first link in the list
 {
    return first;                              //returns the first link
 }
 public boolean isempty()                      //checks if empty
 { 
   return (first == null);
 }
 //--------------------------------------------------------------------------
 public void insert (int per)                  //insert a link
 { Link  newlink = new Link(per);              //create a new link
    if (isempty())                             //check if we have any
         first=newlink;                        //if not this is the first link 
    else
     { newlink.next=first;                     //point to the first link 
       first=newlink;                          //make this the first link
     }
 
 }
 //------------------------------------------------------------------------
 public void makecircular ()                   //create a circular link
 {  Link current=first;                        //current link
   while (current.next!=null)                  //till false 
     { current=current.next;                   //move current to the last
     }
     if (current.next== null)                  //if next link point to nothing 
       current.next=first;                     //point to first 
 }
 //-------------------------------------------------------------------------------
 public Link find (int key)                    //find a link with a 'key' in it
 { Link current = first;                       //start from the first
    while (current.person != key)              //till u find it  
    {  current=current.next;                   //move to the next
    }
    return current;                            //return the link 
 }
     
 //-----------------------------------------------------------------------------------  
 public void delete (int person)               //remove a person from the list
 {  Link current=first;                        //start from the first 
    Link previous =first;
    Link spot= find (person);                  //find me the link of the person   
    
     if (current == spot)                      //if it is at the first link
      {  
         current=current.next;                 // move current to next
       
          while (current!=spot)                //till u find the last link
              { previous=current;       
                current=current.next;                     
              }
          previous.next=current.next;          //point it to the second link 
          first=current.next;                  //make the second link first
      }
    else                                       //the person is not in the first link
         {                           
          while (current!= spot)                //till u find the link
            {  previous=current;                
               current=current.next;               
            }                                   //found it
          previous.next=current.next;           //remove it/bypass it    

         }
}//method                       
}// end class
////////////////////////////////////////////////////////////////////////////////

class josephus 
{ 
  int numofppl;                                 // total number of people
  int count=3;                                  // the passing number
  Linklist clink;                                
//--------------------------------------------------------------------------- 
 public josephus ()                             //constructor
 {
  clink= new Linklist();
 }
 //--------------------------------------------------------------------------
 public void setcount(int i)                    //set the passing
 {  count=i;
 }
 //----------------------------------------------------------------------
 public int getcount ()                         // get the passing
 { return count;
 }
 //----------------------------------------------------------------------------
 public void setnumppl(int n)                   // set number of ppl
 {  numofppl=n;
 }
 //-------------------------------------------------------------------------------
 public void addppl (int num)                   //creates a link and add the person
 {  setnumppl(num);                             //calls to set the number of ppl
    for (;num>0;num--)                          // for every person     
       clink.insert(num);                       // create a link for it
      clink.makecircular();                     // after the links are created,link the last --->first
 }
 //-------------------------------------------------------------------------------------
 public void eliminate (int start)              //elimination iterator   
 {
   Link next = clink.find(start);               //create a link and assign it the starting position for the game
   Link current;                                
   while(numofppl!=1)                           //till there is one person left
    {  current=next;                            //assign the start to current 
       for (int i=0;i<getcount();i++)           //pass count amount of ppl   
           current=current.next;
         next=current.next;                     //show me the next person to start with 
         
         clink.delete(current.person);          //delete the current person
        
            numofppl--;                              //decrease the number of people
    }
       next.display();  
 }
 //--------------------------------------------------------------------------------------------
 public void accept ()                          //takes the input from user and starts the game
 {                                              
  int numppl=0;
   int count=0;
   int startat=0;
      Scanner in = new Scanner(System.in);
    do{
     System.out.print("\nPlease Enter your numbers separated by space.\nTo exit enter '0'\n");
     numppl=in.nextInt();
      if (numppl==0)                            //exit if 0 pressed
         {  System.out.print ("Theank you for using josephus's app");
           break;
         }
         startat=in.nextInt();                    //the second number
         count=in.nextInt();                      //thrid number
       addppl(numppl);
     eliminate(startat);
     clink.first=null;
      }
      while (numppl!=0 );
 }
}//end class


//////////////////////////////////////////////////////////////////////////////////
class JosephusProblem
 {
    public static void main(String[] args)
      {  josephus jo = new josephus();          //make a josephus object
             jo.accept();                       //start accepting from user
        
     }
 
 }


