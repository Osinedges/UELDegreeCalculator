public class Grades {
    public String courseCode;
    public int level;
    public int creditWorth;
    public double grade;
    public boolean used;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCreditWorth() {
        return creditWorth;
    }

    public void setCreditWorth(int creditWorth) {
        this.creditWorth = creditWorth;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    public boolean getUsed(){
        return used;
    }
    public void setUsed(boolean used) {
        this.used = used;
    }
}
