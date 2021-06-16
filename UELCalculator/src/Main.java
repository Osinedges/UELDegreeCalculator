import java.io.*;
import java.util.*;

public class Main extends Grades {
    public static void main(String[] args) {
        List<Grades> allGrades = new ArrayList<Grades>();
        double finalYearGrade;
        double levelSixGrade;
        double levelFiveGrade;
        String degreeClassification;

        System.out.println("If grades.txt is not filled out, please close this app and fill it out carefully, remember just the final grade number.");
        try {
            allGrades = readFile();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("You messed up somewhere,probably text file issue or something idk.");
        }
        // note to myself, continue bulk of code here, do something with grades, try using the two functions you created :D.
        GradeAndList mainLocalGradeAndList = levelSixGradeCalculator(allGrades);
        levelSixGrade = mainLocalGradeAndList.getGrade();
        levelFiveGrade = levelFiveGradeCalculator(mainLocalGradeAndList.getAllGrades());
        finalYearGrade = (levelFiveGrade * 0.2) + (levelSixGrade * 0.8);
        System.out.println("Level Six Overall Grade Is: " + levelSixGrade);
        System.out.println("Level Five Overall Grade Is: " + levelFiveGrade);
        if (finalYearGrade >= 70){
            degreeClassification = "First-Class Honours";
        }
        else if (finalYearGrade <70 && finalYearGrade >= 60){
            degreeClassification = "Upper Second-Class Honours";
        }
        else if (finalYearGrade <60 && finalYearGrade >= 50){
            degreeClassification = "Lower Second-Class Honours";
        }
        else if (finalYearGrade <50 && finalYearGrade >= 40){
            degreeClassification = "Third-Class Honours";
        }
        else
            degreeClassification = "Fail";
        System.out.println("Your final year grade is: " + finalYearGrade + "%" + "\nThis will give you a  " + degreeClassification + "!");
    }


    public static List<Grades> readFile() throws Exception{
        File file = new File("resources/grades.txt");
        Scanner sc = new Scanner(file);
        List<Grades> allGrades = new ArrayList<>();
        sc.nextLine();
        while(sc.hasNextLine()){
            String currentLine;
            Grades currentGrade = new Grades();
            currentLine = sc.nextLine();
            List<String> items = Arrays.asList(currentLine.split(","));
            currentGrade.setCourseCode(items.get(0));
            currentGrade.setLevel(Integer.parseInt(items.get(1)));
            currentGrade.setCreditWorth(Integer.parseInt(items.get(2)));
            currentGrade.setGrade(Double.parseDouble(items.get(3)));
            currentGrade.setUsed(false);
            allGrades.add(currentGrade);
        }
        return allGrades;

    }

    public static GradeAndList levelSixGradeCalculator(List<Grades> allGrades){
        // this will work out your GPA for 20% of your degree classification
        List<Grades> chosenLevelSix = new ArrayList<>();
        GradeAndList returnedGrades = new GradeAndList();
        int totalCreditsConsumed = 0;
        int totalModulesUsed = 0;
        double gpaLevelSix = 0.00;
        Collections.sort(allGrades, Comparator.comparingDouble(Grades ::getGrade).reversed());

        for (int j = 0; j < allGrades.size(); j++) {
            System.out.println(allGrades.get(j).grade);
            if(allGrades.get(j).creditWorth == 45 && (allGrades.get(j).level == 6)){
                chosenLevelSix.add(allGrades.get(j));
                allGrades.get(j).setUsed(true);
                totalCreditsConsumed = (totalCreditsConsumed + 45);
                totalModulesUsed = totalModulesUsed + 1;
                gpaLevelSix = gpaLevelSix + allGrades.get(j).grade;
            }
        }
        for (int i = 0; i < allGrades.size(); i++){
            if (allGrades.get(i).level == 6 && !allGrades.get(i).used && totalCreditsConsumed < 90){
                gpaLevelSix = allGrades.get(i).getGrade() + gpaLevelSix;
                totalModulesUsed = totalModulesUsed + 1;
                totalCreditsConsumed = totalCreditsConsumed + allGrades.get(i).creditWorth;
                allGrades.get(i).setUsed(true);
            }
        }

        gpaLevelSix = gpaLevelSix / totalModulesUsed;
        returnedGrades.setAllGrades(allGrades);
        returnedGrades.setGrade(gpaLevelSix);
        return returnedGrades;
    }
    public static double levelFiveGradeCalculator(List<Grades> allGrades) {
        // this will work out your GPA for 20% of your degree classification
        int creditCounter = 0;
        int totalModulesUsed = 0;
        double gpaLevelFive = 0;
        allGrades.sort(Comparator.comparingDouble(Grades::getGrade).reversed());
        for (int i = 0; i < allGrades.size(); i++) {
            if (!allGrades.get(i).getUsed() && creditCounter < 90){
                gpaLevelFive = gpaLevelFive + allGrades.get(i).getGrade();
                creditCounter = creditCounter + allGrades.get(i).getCreditWorth();
                totalModulesUsed = totalModulesUsed + 1;
            }
        }
        return (gpaLevelFive / totalModulesUsed);
    }
}