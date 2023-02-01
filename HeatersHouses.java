/* Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses... Every house can be warmed, as long as the house is within the heater's warm radius range... Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses... Notice that all the heaters follow your radius standard, and the warm radius will the same...
 * Eg 1: houses = [1,2,3]                             heaters = [2]               Output = 1
 * Eg 2: houses = [1,2,3,4]                           heaters = [1,4]             Output = 1
 * Eg 3: houses = [1,3,5]                             heaters = [2]               Output = 3
 * Eg 4: houses = [1,1,1,99,999,999]                  heaters = [499,500,501]     Output = 498
 * Eg 5: houses = [1,2,3,4,5,6,7]                     heaters = [3,6]             Output = 2
 */
import java.util.*;
public class HeatersHouses
{
    public int MinimumRadiusOfHeaters(int heaters[], int houses[])
    {
        int j = 0, min = 0, max = 0;
        if((heaters.length < 1) || (houses.length < 1)) // If either Heaters or Houses are not available...
            return -1;
        if(heaters.length == 1)     // Base Condition - if we have only one Heater...
        {
            for(int i = 0; i < houses.length; i++)
            {
                min = Math.min(1000000, Math.abs(houses[i] - heaters[0]));
                max = Math.max(max, min);    // Returning the farthest distance of the House from the given Heater...
            }
            return max;    // The farthest house distance is the required radius in this case...
        }
        Arrays.sort(heaters);    // Sorting the Heaters array in ascending order...
        Arrays.sort(houses);     // Sorting the Houses array in ascending order...
        for(int i = 0; i < houses.length; i++)
        {
            if(houses[i] <= heaters[0])     // If we have Heater only on right side of the House...
                min = Math.min(1000000, Math.abs(houses[i] - heaters[0]));
            else if(houses[i] > heaters[j])
            {
                if(j < heaters.length - 1 && houses[i] > heaters[j+1])  // If we have Heaters on both sides of the House...
                    j++;
                if(j < heaters.length - 1)    // If the current Heater is not the last Heater...
                    min = Math.min(Math.abs(houses[i] - heaters[j]), Math.abs(houses[i] - heaters[j+1]));
                if(j == heaters.length - 1)   // If we have Heater only on the left side of the House...
                    min = Math.min(1000000, Math.abs(houses[i] - heaters[j]));
            }
            max = Math.max(max, min);        // Getting the max of the min distance of the Houses so far...
        }
        return max;    // Returning the required heat radius...
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter the number of Houses : ");
        x = sc.nextInt();
        int houses[] = new int[x];
        for(int i = 0; i < houses.length; i++)
        {
            System.out.print("Enter the X coordinate of "+(i+1)+" th House : ");
            houses[i] = sc.nextInt();
        }
        System.out.print("Enter the number of Heaters available : ");
        x = sc.nextInt();
        int heat[] = new int[x];
        for(int i =0; i < heat.length; i++)
        {
            System.out.print("Enter the X coordinate of "+(i+1)+" th Heater : ");
            heat[i] = sc.nextInt();
        }
        HeatersHouses heatershouses = new HeatersHouses();     // Object creation...
        System.out.println("The Minimum Heat Radius Required : "+heatershouses.MinimumRadiusOfHeaters(heat, houses));
        sc.close();
    }
}

// Time Complexity  - O(n log n) time...
// Space Complexity - O(1) space...

/* DEDUCTIONS :- 
 * 1. Since we are finding the minimum heat radius, we sort the houses and heaters both in ascending order...
 * 2. The problem is now divided into three cases, as Houses with heaters to right, Houses with heaters to both right and left, houses with heaters to left...
 * 3. We find the minimum distance of each house from the Heater and then find the maximum of the minimum distance of the Houses...
*/