public class Employee {

    private String name;
    private String position;
    private String office;
    private String startDate;
    private int age;
    private int salary;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getOffice() {
        return office;
    }

    public String getPosition() {
        return position;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setAge(String age) {
        this.age = Integer.parseInt(age);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(String salary) {
        this.salary = Integer.parseInt(salary.replace("$", "")
                .replace("/y", "")
                .replace(",", ""));
    }
}
