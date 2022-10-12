package config;

public class CurrentCaseType {
    private static CurrentCaseType instance;
    private CaseType currentCaseType;
    private CurrentCaseType() {
    }
    public synchronized static CurrentCaseType getInstance() {
        if (instance == null) {
            instance = new CurrentCaseType();
        }
        return instance;
    }
    public synchronized void setUser(CaseType caseType) {
        this.currentCaseType = caseType;
    }
    public synchronized CaseType getCurrentCaseType() {
        return currentCaseType;
    }
}
