package org.uml.funfitness.pojo;

public class ClassTable {

    private Integer classId;
    private String className;
    private String classBegin;
    private String classTime;
    private String coach;

    public ClassTable(Integer classId, String className, String classBegin, String classTime, String coach) {
        this.classId = classId;
        this.className = className;
        this.classBegin = classBegin;
        this.classTime = classTime;
        this.coach = coach;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassBegin() {
        return classBegin;
    }

    public void setClassBegin(String classBegin) {
        this.classBegin = classBegin;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
}
