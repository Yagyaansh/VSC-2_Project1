Player_Pool functionality requirements : 

The player pool will contain objects of type Player
A fixed size of 10,000
Allow GMs to pick players from the pool randomly
-> A random iteration is needed
-> need to be able to get players from the pool
-> need to be able to remove players from the pool (once they have been picked) or they are too old or injured
-> need to add new rookie players to replace the retired ones

--> PLayer Pool requriement - fixed size (for now), random iteration, retrieve, delete, add

-----------------------------------------------------------------------------------------------------------------------------------------

General Manager Pool functionality requirements : 

I could not find any mention of a pool structure for GMs in the Google doc of requirements.
Why do we have this ?

-----------------------------------------------------------------------------------------------------------------------------------------

Coach_Pool functionality requirements : 

Contain the type coach
Has a fixed size of 100
Be able to pick coaches from the pool
Be able to delete coaches

--> Coach Pool requirements - fixed size (as of now), random iteration, retieve, delete

-----------------------------------------------------------------------------------------------------------------------------------------

List of Teams requirements : 

At the beginnig of the simulation a fixed number of teams will be created and added to the chosen data structure
Then random access to the teams is needed as the games are played
Teams are NOT deleted or added once the simulation starts
Might be a good idea to use a PQ with the PQ being ordered according to the win-loss ratio
This way we wont need to sort at the end of the season
The PQ will order for us automatically and we can poll and add as desired
GM also picks coaches in alphabeetical order of city names so we might need 2 data structures to make it efficient

--> Major requirement is only efficiency in retrieving information, a fixed size of teams

-----------------------------------------------------------------------------------------------------------------------------------------
