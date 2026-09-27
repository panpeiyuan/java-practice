package chap12ObjectOrientedGeneralPractice;

public class GirlFriend {
    private String name;
    private int age;
    private char gender;
    private String habits;

    public GirlFriend() {
    }

    public GirlFriend(int age, String name, char gender, String habits) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.habits = habits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
