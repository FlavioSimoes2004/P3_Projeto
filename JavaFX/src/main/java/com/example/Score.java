package com.example;

public class Score {
    public static Integer[] topFiveScore = {null, null, null, null, null};
    public static String[] topFiveUsername = {null, null, null, null, null};

    public static void compareNewScore(String username, int score){
        if(username.equals(""))
        {
            username = "Sem nome";
        }

        boolean switched = false;

        for(int i = 0; i < topFiveScore.length; i++)
        {
            Integer value = topFiveScore[i];
            if(value == null)
            {
                topFiveScore[i] = score;
                topFiveUsername[i] = username;
                break;
            }
            else
            {
                if(score > value && switched == false)
                {
                    int aux = topFiveScore[i];
                    topFiveScore[i] = score;
                    score = aux;

                    String copy = topFiveUsername[i];
                    topFiveUsername[i] = username;
                    username = copy;

                    switched = true;
                }
                else if(score >= value && switched == true)
                {
                    int aux = topFiveScore[i];
                    topFiveScore[i] = score;
                    score = aux;

                    String copy = topFiveUsername[i];
                    topFiveUsername[i] = username;
                    username = copy;
                }
            }
        }
    }

    public static String getText(int pos){
        String txt = topFiveUsername[pos] + ": " + topFiveScore[pos];

        return txt;
    }
}