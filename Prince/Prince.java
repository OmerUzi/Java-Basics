* This function return if there is a way for the prince to save the princess 
     * at each step the prince can climb 1 level or go down 2 level.
     * @param drm  array of integer that represent the Digital Roof Map 
     * @return the minimum number of steps the prince have to do to save the princess,
     * return -1 if there is no way.
     */
    public static int prince(int[][] drm, int i, int j)
    {

        if(drm[i][j]==-1) return 1; // prince arrive to the evil
        // avoid repeat a cell
        int temp = drm[i][j];
        drm[i][j] =  Integer.MIN_VALUE;
        //declartion 
        int up = -1, down = -1, left = -1, right = -1;
        //make a step
        if(isStepValid(i,j, temp, drm, "up"))  up = prince(drm, i - 1,j); //step up
        if(isStepValid(i,j, temp, drm, "down"))  down = prince(drm, i + 1,j); //step down
        if(isStepValid(i,j, temp, drm, "left"))  left = prince(drm, i,j - 1); //step left
        if(isStepValid(i,j, temp, drm, "right"))  right = prince(drm, i,j + 1); //step right
        drm[i][j] = temp;
        // couldn't find a path
        if (up == -1 && down == -1 && left == -1 && right == -1)
            return -1;
        //return the shortest way but not -1
        if(up == -1) up = Integer.MAX_VALUE;
        if(down == -1) down = Integer.MAX_VALUE;
        if(left == -1) left = Integer.MAX_VALUE;
        if(right == -1) right = Integer.MAX_VALUE;
        return 1 + Math.min(Math.min(up,down), Math.min(right, left));

    }

    private static boolean isStepValid(int i, int j,int temp,int[][] drm, String direction) // cheack if  the step is valid
    {   
        boolean isValid = false;
        switch(direction){
            case "up":
                isValid = (i > 0) && ((drm[i - 1][j] == -1) || ((temp - drm[i - 1][j] <= 2) && (temp - drm[i - 1][j] >= -1)));
                break;//step up
            case "down":
                isValid = (i < drm.length - 1) && ((drm[i + 1][j] == -1) || ((temp - drm[i + 1][j] >= -1) && (temp - drm[i + 1][j] <= 2)));
                break;//step down
            case "left":
                isValid = (j > 0) && ((drm[i][j - 1] == -1) || ((temp - drm[i][j - 1] >= -1) && (temp - drm[i][j - 1] <= 2)));
                break;//step left
            case "right":
                isValid = (j < drm.length - 1) && ((drm[i][j + 1] == -1) || ((temp - drm[i][j + 1] >= -1) && (temp - drm[i][j + 1] <= 2))); 
                break;//step left
        }
        return isValid;

    }
    
}
