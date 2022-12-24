package group24.piazzapanic.levelElements.stations;

public class CuttingStation extends Station{
    // TODO
    // The Ingredient class implements an incrementalCut() function
    // which increases its cut value by 1/20 (i.e. cutting take 20 seconds)
    // You need to implement a function that checks if the user interacts
    // with the cutting station for the full 20 seconds
    // and if not, cancels the cutting operation. pseudocode below:
    /*
    while (user is pressing the interaction button):
        cuttingProgress = Ingredient.getCuttingProgress
        if cutting progress is -1, the item cannot be cut, exit.
        if cutting progress is 1, the item is cut:
            call Ingredient.cuttingComplete()
            exit
        else:
            call Ingredient.incrementalCut
            wait one second
            repeat the loop.

     */
}
