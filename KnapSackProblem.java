// Knapsack problem using java and recursion
//this program takes upto 25 array of weights
//and evaluate the best suitable comination of weights that match the desired weight
///////////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

class Knapsack
{
    private int[] intarr;
    private int [] backup_sack;
    private int [] sackArray;
    private int i_count;
    private int capacity;
    private int sackArrayCounter;
    private int passThrough;
    private int Iterator;
    private ArrayList solution;
    private ArrayList sack_list;
    private ArrayList pre_SolutionsFound;
//////////////////////////////////////////////////////////////////////////////////////
  //constractor
    public Knapsack(int[] paramArray)
    {
        intarr = paramArray;
        backup_sack = intarr;
        sackArray = new int[paramArray.length];
         
        i_count = 0;
        
        capacity = intarr[0];

        sackArrayCounter = 0;
        passThrough = 0;
        Iterator = 0;
        solution = new ArrayList();

        sack_list = new ArrayList();
        pre_SolutionsFound = new ArrayList();

        for(int i = 1; i < intarr.length; i++)
        {
            sack_list.add(intarr[i]);
        }


        //Begin the recursive problem.
        CheckForSums(capacity, sack_list);

    }
///////////////////////////////////////////////////////////////////////////////////////
    public void CheckForSums(int targetValue, ArrayList intarr)
    {
        int numberRead = (Integer) intarr.get(i_count);
        targetValue = ComputeTarget();

        i_count++;
      //base cases
        if(numberRead > capacity)
        {
            intarr.remove(i_count);
            i_count--;
        }

        if(numberRead <= targetValue)
        {
            AddToSolution(numberRead);
            CheckForPossibleSolution();
            //Add the item to the solution  
        }

       

        if(i_count == intarr.size())
        {
            passThrough++;

            i_count = passThrough + 1;
            RemoveOneFromSolution();    
        }

        //Advance forward one position
        if(passThrough == intarr.size() - 1)
        {
            i_count = 0;
            passThrough = 0;
            intarr = RebuildArrayList(intarr);

            for(int i = 0; i < Iterator; i++)
            {
                intarr.remove(0);
            }

            Iterator++;

            ResetSolutionArray();
        }

        if(Iterator == this.intarr.length - 2)
        {
            
            return;
        }

        CheckForSums(targetValue, intarr);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////
    public void ResetSolutionArray()
    {
        sackArrayCounter = 0;

        for(int i = 0; i < sackArray.length; i++)
        {
            sackArray[i] = 0;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    public void CheckForPossibleSolution()
    {
        if(SumOfSolutionsFound() == capacity)
        {
            PrintFoundSolution();
            RemoveDownToBaseNumber();
        }

      }
/////////////////////////////////////////////////////////////////////////////////////////////////
    public void RemoveOneFromSolution()
    {
        if(sackArrayCounter > 1)
        {
            sackArrayCounter--;
        }

        if(sackArrayCounter > 1)
        {
            sackArray[sackArrayCounter] = 0;
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void RemoveDownToBaseNumber()
    {
        while(sackArrayCounter > 1)
        {
            sackArrayCounter--;
            sackArray[sackArrayCounter] =0;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////
    public int SumOfSolutionsFound()
    {
        int sumOfSolutions = 0;

        for(int i = 0; i < sackArray.length; i++)
        {
            sumOfSolutions += sackArray[i];
        }
        return sumOfSolutions;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Integer> RebuildArrayList(ArrayList<Integer> paramList)
    {
        paramList = new ArrayList();

        for(int i = 1; i < intarr.length; i++)
        {
            paramList.add(intarr[i]);
        }
        return paramList;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void PrintFoundSolution()
    {
        StringBuilder toMessageBox = new StringBuilder();
        System.out.print("\nSoution: ");
        toMessageBox.append("Solution: ");

        for(int i = 0; i < sackArray.length; i++)
        {   if(sackArray[i]!=0)
            {System.out.print(sackArray[i] + " ");
            toMessageBox.append(sackArray[i] + " ");
            }
        }
        String finishedMessage = toMessageBox.toString();

        boolean displayCurrentSolution = true;

        for(int i = 0; i < pre_SolutionsFound.size(); i++)
        {
            String previousSolution = pre_SolutionsFound.get(i).toString();
            if(finishedMessage.equals(previousSolution))
            {
                displayCurrentSolution = false;
            }
        }

        pre_SolutionsFound.add(finishedMessage);

        if(displayCurrentSolution == true)
        {
            solution.add(finishedMessage);

            JOptionPane.showMessageDialog(null,  finishedMessage, 
                    "Solution for target: " + capacity, JOptionPane.INFORMATION_MESSAGE);
        }
    }



//////////////////////////////////////////////////////////////////////////////////////////////////
    public void AddToSolution(int value)
    {
        sackArray[sackArrayCounter] = value;
        sackArrayCounter++;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////
    public int ComputeTarget()
    {
        int sumOfSolutions = 0;

        for(int i = 0; i < sackArray.length; i++)
        {
            sumOfSolutions += sackArray[i];
        }

        int numbersNeededToReachMainGoal = capacity - sumOfSolutions;
        return numbersNeededToReachMainGoal;
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////


public class KnapSackProblem
{
    public static void main(String[] args)
    {
        

        int[] intarry;
   //accept from user using joptionpane
        String userInput = JOptionPane.showInputDialog("Enter a list of numbers delimited by a single space.");

        String[] splitElements = userInput.split("\\s+");

        intarry = new int[splitElements.length];

        for(int i = 0; i < intarry.length; i++)
        {
            intarry[i] = Integer.parseInt(splitElements[i]);
        }
 // create and obect and start the recursion
        Knapsack recObj = new Knapsack(intarry);

    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////