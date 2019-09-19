# Traveling Sales Person Problem
This project is the simulation of traveling sales person problem. This problem is a NP-hard problem. Therefore, we use MST to approach
the approximate solution. In the later part of the project, we use permutation to calculate the real shortest path.  

## Data Explanation
The data file contains a list of crime records from 1990 in Pittsburgh.  It contains three types of addresses. 
The first, which we will not use, is the street address of the crime. 
The second is the latitude and longitude of the crime (we will use these data only in Part 3 of this assignment. 
The third is the (X,Y) - coordinates of the crime. 
These (X, Y)- coordinates are from the State Plane Coordinate System. These data may be used to compute distances between vertices in South Western PA using the Pythagorean theorem. I
n Part 1, we will be using the (X, Y) pairs to compute the distance between each crime. 
To convert from feet to miles, simply multiply the computed distances by 0.00018939.


## Project Break-Down
This project is divided into 3 parts.  
###### Part 1
Approx-TSP-Tour(G,c)  
	1. Select a vertex r belongs to V[G] to be a root vertex  
	2. Compute a minimum spanning tree T for G from root r using MST-Prim(G,c,r)  
	3. Let L be the list of vertices visited in a preorder tree walk of T  
  4. Return the Hamiltonian cycle H that visits the vertices in the order L  
###### Part 2
Calculating the optimal solution for TSP using brutal force (permutation).
###### Part 3
Displaying the routes of the optimal solution and approximate solution to Google Earth.
