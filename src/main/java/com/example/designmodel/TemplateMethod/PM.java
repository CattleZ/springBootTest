package com.example.designmodel.TemplateMethod;

public abstract class PM {
    public abstract String analyze();
    public abstract String design(String project);
    public abstract String develop(String project);
    public abstract Boolean test(String project);
    public abstract void deploy(String project);
    public String projectManage() {
        String requirements = analyze();
        String design = design(requirements);
        do{
            design = develop( design);
        }while (!test(design));
        deploy(design);
        return "FINISH";
    }
}
