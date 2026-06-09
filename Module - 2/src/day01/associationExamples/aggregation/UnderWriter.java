package day01.associationExamples.aggregation;

import java.util.LinkedHashSet;
import java.util.Set;

public class UnderWriter {
    private String name;
    private String employeeId;
    private Set<LoanApplication> loanApplications;
    public UnderWriter(String name, String employeeId) {
        this.name = name;
        this.employeeId = employeeId;
        this.loanApplications = new LinkedHashSet<LoanApplication>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<LoanApplication> getLoanApplications() {
        return loanApplications;
    }
    public void setLoanApplications(Set<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }



}
